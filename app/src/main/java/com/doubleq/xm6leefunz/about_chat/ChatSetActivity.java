package com.doubleq.xm6leefunz.about_chat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.doubleq.model.DataChatFriendInfo;
import com.doubleq.model.DataMyFriend;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.about_utils.ZXingUtils;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmChatHelper;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmHomeHelper;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.PersonData;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity.MyAccountActivity;
import com.example.zhouwei.library.CustomPopWindow;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.suke.widget.SwitchButton;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;


public class ChatSetActivity extends BaseActivity {

    @BindView(R.id.include_top_iv_back)
    ImageView includeTopIvBack;
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTopTvTitle;
    @BindView(R.id.include_top_iv_more)
    ImageView includeTopIvMore;
    @BindView(R.id.inclu_tv_right)
    TextView incluTvRight;
    @BindView(R.id.fd_tv_name)
    TextView mTvName;
    @BindView(R.id.fd_tv_contant)
    TextView fdTvContant;
    @BindView(R.id.fd_tv_gesign)
    TextView fdTvGesign;
    @BindView(R.id.fd_iv_head)
    ImageView mIvHead;
//    @BindView(R.id.chatset_zhiding_chat)
//    SwitchButton chatsetZhidingChat;
    @BindView(R.id.chatset_msg_miandarao)
    SwitchButton chatsetMsgMiandarao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_chat_set;
    }

    String FriendId;
    RealmHomeHelper realmHelper;
    RealmChatHelper realmChatHelper;
    View mView;
    CustomPopWindow popWindow;

    String type = "1";
    @Override
    protected void initBaseView() {
        super.initBaseView();
//
        includeTopIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManager.getAppManager().finishActivity();
            }
        });
        includeTopTopTvTitle.setText("聊天设置");
        incluTvRight.setVisibility(View.GONE);
        includeTopIvMore.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        FriendId = intent.getStringExtra("FriendId");
        sendWeb(SplitWeb.getFriendInfo(FriendId));

        realmHelper = new RealmHomeHelper(this);
        realmChatHelper = new RealmChatHelper(this);
        mView = LayoutInflater.from(this).inflate(R.layout.pop_good_friend, null);
        mView.findViewById(R.id.pop_tv_pingbi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtil.show("点击了屏蔽");
                DialogUtils.showDialog("是否屏蔽此好友？", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
                        sendWebHaveDialog(SplitWeb.shieldFriend(FriendId, "2"), "正在屏蔽...", "屏蔽成功");
                    }
                });
                if (popWindow != null)
                    popWindow.dissmiss();
            }
        });
        mView.findViewById(R.id.pop_tv_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtil.show("点击了删除");
                DialogUtils.showDialog("是否确定删除该好友？", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
                        sendWebHaveDialog(SplitWeb.deleteFriend(FriendId), "正在删除...", "删除成功");
                    }
                });
                if (popWindow != null)
                    popWindow.dissmiss();
            }
        });
    }

    @OnClick({R.id.include_top_iv_more, R.id.fd_iv_qrcode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.include_top_iv_more:
                if (popWindow == null) {
                    popWindow = new CustomPopWindow.PopupWindowBuilder(ChatSetActivity.this)
                            .setView(mView)
                            .setFocusable(true)
                            .setOutsideTouchable(true)
                            .size(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                            .setAnimationStyle(R.style.AnimDown) // 添加自定义显示和消失动画
                            .create()
                            .showAsDropDown(includeTopIvMore, 0, 0);
                } else
                    popWindow.showAsDropDown(includeTopIvMore, 0, 0);
                break;
            case R.id.fd_iv_qrcode:
                if (dataRecord != null) {
                    PersonData personData = new PersonData();
                    personData.setHeadImg(dataRecord.getHeadImg());
                    personData.setName(dataRecord.getNickName());
                    personData.setScanTital("扫一扫,添加" + dataRecord.getNickName() + "为好友");
                    personData.setTital("好友二维码");

                    if (FriendId != null){
                        String string = type + "_xm6leefun_" + FriendId;
                        Log.e("qrcode","----------chatSetActivity--------------"+string);
//                        Bitmap bitmap = ZXingUtils.createQRImage(string,300,300);
//                        Drawable drawable = new BitmapDrawable(bitmap);
//                        Log.e("qrcode","-------record.getQrcode()---------"+drawable);
//                        qrcodeIvQrcode.setBackground(drawable);
                        personData.setQrCode(string);
                    }

                    IntentUtils.JumpToHaveObj(MyAccountActivity.class, MyAccountActivity.TITAL_NAME, personData);
                }
                break;
        }
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
//            获取好友数据
            case "getFriendInfo":
                initDataFriend(responseText);
                break;
            case "deleteFriend":
                DialogUtils.showDialogOne("删除好友成功", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
                        realmHelper.deleteRealmMsg(FriendId);
                        realmChatHelper.deleteRealmMsg(FriendId);
                        Intent intent2 = new Intent();
                        intent2.putExtra("id",FriendId);
                        intent2.setAction("del.refreshMsgFragment");
                        sendBroadcast(intent2);
                        AppManager.getAppManager().finishActivity(ChatSetActivity.this);
                        AppManager.getAppManager().finishActivity(ChatActivity.class);
                    }
                });
                break;
            case "shieldFriend":
                DialogUtils.showDialogOne("屏蔽好友成功", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
                        AppManager.getAppManager().finishActivity(ChatSetActivity.this);
                    }
                });
                break;

        }
    }

    DataChatFriendInfo.RecordBean dataRecord;

    private void initDataFriend(String responseText) {
        DataChatFriendInfo dataChatFriendInfo = JSON.parseObject(responseText, DataChatFriendInfo.class);
        DataChatFriendInfo.RecordBean record = dataChatFriendInfo.getRecord();
        if (record != null) {
            dataRecord = record;
            Glide.with(this).load(record.getHeadImg())
                    .bitmapTransform(new CropCircleTransformation(ChatSetActivity.this))
                    .crossFade(1000).into(mIvHead);
            String signText = StrUtils.isEmpty(record.getPersonaSignature()) ? "暂未设置签名" : record.getPersonaSignature();
            fdTvGesign.setText(signText);
            fdTvContant.setText("(" + record.getWxSno() + ")");
//            若有设置备注，仅显示备注；若无备注则显示昵称
            String nameText = StrUtils.isEmpty(record.getRemarkName()) ? record.getNickName() : record.getRemarkName();
            mTvName.setText(nameText);
//            mTvName.setText(record.getNickName() + "(" + record.getRemarkName() + ")");
            chatsetMsgMiandarao.setChecked(record.getShieldType().equals("2"));
        }


    }
}
