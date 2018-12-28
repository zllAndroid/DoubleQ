package com.doubleq.xm6leefunz.main_code.ui.about_contacts;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.doubleq.model.CusJumpChatData;
import com.doubleq.model.DataMyFriend;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_chat.ChatActivity;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmHomeHelper;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity.ChangeInfoWindow;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity.MyAccountActivity;
import com.example.zhouwei.library.CustomPopWindow;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.rance.chatui.util.Constants;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * 位置：联系人-好友资料-界面（好友资料）
 */
public class FriendDataActivity extends BaseActivity implements ChangeInfoWindow.OnAddContantClickListener {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.include_top_iv_more)
    ImageView includeTopIvMore;
    @BindView(R.id.inclu_tv_right)
    TextView incluTvRight;
    @BindView(R.id.fd_tv_name)
    TextView mTvName;
    @BindView(R.id.fd_tv_contant)
    TextView fdTvContant;
    @BindView(R.id.fd_tv_fenzu)
    TextView fdTvFenzu;
    @BindView(R.id.fd_tv_gesign)
    TextView fdTvGesign;
    @BindView(R.id.fd_tv_send_msg)
    TextView fdTvSendMsg;
    @BindView(R.id.fd_tv_send_call)
    TextView fdTvSendCall;
    @BindView(R.id.fd_iv_head)
    ImageView mIvHead;
    @BindView(R.id.gf_lin_top)
    LinearLayout mLinMain;
    @BindView(R.id.fd_tv_beizhu)
    TextView fdTvBeizhu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    String FriendId;

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("好友资料");
        incluTvRight.setVisibility(View.GONE);
        includeTopIvMore.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        FriendId = intent.getStringExtra("id");
        sendWeb(SplitWeb.getFriendInfo(FriendId));

        realmHelper = new RealmHomeHelper(this);
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

    RealmHomeHelper realmHelper;

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
                        AppManager.getAppManager().finishActivity(FriendDataActivity.this);
                    }
                });
                break;
            case "shieldFriend":
                DialogUtils.showDialogOne("屏蔽好友成功", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
                        AppManager.getAppManager().finishActivity(FriendDataActivity.this);
                    }
                });
                break;
            case "friendRemarkName"://修改备注成功
                fdTvBeizhu.setText("(" + contant + ")");
                break;
        }
    }

    DataMyFriend.RecordBean dataRecord;

    private void initDataFriend(String responseText) {

        DataMyFriend dataMyFriend = JSON.parseObject(responseText, DataMyFriend.class);

        DataMyFriend.RecordBean record = dataMyFriend.getRecord();
        if (record != null) {
            dataRecord = record;
            Glide.with(this).load(record.getHeadImg())
                    .bitmapTransform(new CropCircleTransformation(FriendDataActivity.this))
                    .crossFade(1000).into(mIvHead);
            String signText = StrUtils.isEmpty(record.getPersonaSignature()) ? "暂未设置签名" : record.getPersonaSignature();
            fdTvGesign.setText(signText);
            fdTvFenzu.setText(record.getGroupName() + "");
            fdTvContant.setText(record.getWxSno());
            mTvName.setText(record.getNickName());
            String beizhuText = StrUtils.isEmpty(record.getRemarkName()) ? "暂未设置备注" : "("+ record.getRemarkName() +")";
            Log.e("remarkName","----------remarkName----------"+beizhuText);
//            mTvName.setText(nameText);
            fdTvBeizhu.setText(beizhuText);
        }
    }

    View mView;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_friend_data;
    }

    CustomPopWindow popWindow;

    @OnClick({R.id.include_top_iv_more, R.id.fd_iv_qrcode, R.id.fd_iv_head, R.id.fd_tv_send_msg, R.id.fd_lin_fenzu, R.id.fd_lin_name, R.id.fd_tv_send_call})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            顶部点点点按钮
            case R.id.include_top_iv_more:
                if (popWindow == null)
                    popWindow = new CustomPopWindow.PopupWindowBuilder(FriendDataActivity.this)
                            .setView(mView)
                            .setFocusable(true)
                            .setOutsideTouchable(true)
                            .size(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                            .setAnimationStyle(R.style.AnimDown) // 添加自定义显示和消失动画
                            .create()
                            .showAsDropDown(includeTopIvMore, 0, 0);
                else
                    popWindow.showAsDropDown(includeTopIvMore, 0, 0);
                break;
//                二维码按钮
            case R.id.fd_iv_qrcode:
                if (dataRecord != null) {
                    PersonData personData = new PersonData();
                    personData.setHeadImg(dataRecord.getHeadImg());
                    personData.setName(dataRecord.getNickName());
                    personData.setScanTital("扫一扫,添加" + dataRecord.getNickName() + "为好友");
                    personData.setTital("好友二维码");
                    personData.setQrCode(dataRecord.getQrcode());
                    IntentUtils.JumpToHaveObj(MyAccountActivity.class, MyAccountActivity.TITAL_NAME, personData);
                }
                break;
//                头像按钮
            case R.id.fd_iv_head:

                break;
//                发送消息
            case R.id.fd_tv_send_msg:
                if (NoDoubleClickUtils.isDoubleClick()) {
                    if (dataRecord != null) {
                        CusJumpChatData cusJumpChatData = new CusJumpChatData();
                        cusJumpChatData.setFriendHeader(dataRecord.getHeadImg());
                        cusJumpChatData.setFriendId(dataRecord.getFriendId());
                        cusJumpChatData.setFriendName(dataRecord.getNickName());
                        realmHelper.addRealmMsg(cusJumpChatData);
                        IntentUtils.JumpToHaveObj(ChatActivity.class, Constants.KEY_FRIEND_HEADER, cusJumpChatData);
                    }
                }
                break;
//                分组按钮
            case R.id.fd_lin_fenzu:
                if (NoDoubleClickUtils.isDoubleClick()) {
                    Intent intent = new Intent(this, ChooseGroupActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("string", FriendId);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, AppConfig.FRIEND_DATA_GROUP_REQUEST);
                }
                break;
//                修改备注
            case R.id.fd_lin_name:
                if (NoDoubleClickUtils.isDoubleClick())
                    doChangeName();
                break;
            case R.id.fd_tv_send_call:
                if (NoDoubleClickUtils.isDoubleClick()) {

                }
                break;
        }
    }

    private void doChangeName() {

        ChangeInfoWindow changeInfoWindow = new ChangeInfoWindow(FriendDataActivity.this, "修改备注", fdTvBeizhu.getText().toString().trim());
        changeInfoWindow.showAtLocation(mLinMain, Gravity.CENTER, 0, 0);
        changeInfoWindow.setOnAddpopClickListener(this);
    }


    String ids = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == AppConfig.FRIEND_ADD_GROUP_RESULT) {
            if (requestCode == AppConfig.FRIEND_DATA_GROUP_REQUEST) {
                String name = data.getStringExtra(ChooseGroupActivity.CHOOSE_NAME);
                String id = data.getStringExtra(ChooseGroupActivity.CHOOSE_ID);
                fdTvFenzu.setText(name);
                ids = id;
                //设置结果显示框的显示数值
            }
        }
    }

    String contant = null;

    @Override
    public void onSure(String contant) {
        this.contant = contant;
        sendWeb(SplitWeb.friendRemarkName(FriendId, contant));
    }

    @Override
    public void onCancle() {

    }


}
