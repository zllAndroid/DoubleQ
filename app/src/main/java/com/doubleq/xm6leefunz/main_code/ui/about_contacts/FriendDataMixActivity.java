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
import com.doubleq.model.DataScanFirendRequest;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_chat.ChatActivity;
import com.doubleq.xm6leefunz.about_chat.FullImageActivity;
import com.doubleq.xm6leefunz.about_utils.GlideCacheUtil;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmChatHelper;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmHomeHelper;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_add.AddGoodFriendActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_link_realm.RealmLinkFriendHelper;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_search.DataSearch;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity.ChangeInfoWindow;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity.MyAccountActivity;
import com.example.zhouwei.library.CustomPopWindow;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
import com.rance.chatui.enity.FullImageInfo;
import com.rance.chatui.util.Constants;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * 位置：好友资料界面  (添加)  还未添加的页面
 */
public class FriendDataMixActivity extends BaseActivity implements ChangeInfoWindow.OnAddContantClickListener {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.include_top_iv_more)
    ImageView includeTopIvMore;
    @BindView(R.id.fd_iv_head)
    ImageView mIvHead;
    @BindView(R.id.inclu_tv_right)
    TextView incluTvRight;
    @BindView(R.id.fd_tv_name)
    TextView mTvName;
    @BindView(R.id.fd_tv_contant)
    TextView fdaTvNum;
    @BindView(R.id.fd_tv_gesign)
    TextView fdaTvSign;
    @BindView(R.id.fd_iv_qrcode)
    ImageView dataIvQrcode;
    @BindView(R.id.include_top_lin_background)
    LinearLayout includeTopLinBackground;
    //    @BindView(R.id.include_top_lin_back)
//    LinearLayout includeTopLinBack;
//    @BindView(R.id.changeinfo_iv_count)
//    ImageView changeinfoIvCount;
    @BindView(R.id.fd_lin_send)
    LinearLayout fdLinSend;
    @BindView(R.id.fda_tv_add)
    TextView fdaTvAdd;
    @BindView(R.id.fd_lin_name)
    LinearLayout fdLinName;
    @BindView(R.id.fd_tv_beizhu)
    TextView fdTvBeizhu;
    @BindView(R.id.fd_lin_fenzu)
    LinearLayout fdLinFenzu;
    @BindView(R.id.gf_lin_top)
    LinearLayout gfLinTop;
    @BindView(R.id.fd_tv_fenzu)
    TextView fdTvFenzu;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    DataSearch dataSearch = null;
    String type = "1";
    //    public static final String FRIENG_ID_KEY = "friendId";
    String FriendId;
    String esc;
    View mView;

    RealmHomeHelper realmHelper;
    RealmChatHelper realmChatHelper;
    RealmLinkFriendHelper realmLinkFriendHelper;

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("好友资料");
        incluTvRight.setVisibility(View.GONE);
        includeTopIvMore.setVisibility(View.GONE);
        includeTopLinBackground.setBackgroundColor(getResources().getColor(R.color.app_theme));


        Intent intent = getIntent();
        if (intent != null) {
            FriendId = intent.getStringExtra("id");
//            String id = intent.getStringExtra("id");
            esc = intent.getStringExtra("esc");
            Log.e("qrCode_scan_id", "-----------mix--------------" + FriendId);
//            sendWebHaveDialog(SplitWeb.addFriendQrCode(id), "搜索好友信息中...", "获取成功");
            sendWeb(SplitWeb.addFriendQrCode(FriendId));
        }
        realmHelper = new RealmHomeHelper(this);
        realmChatHelper = new RealmChatHelper(this);
        realmLinkFriendHelper = new RealmLinkFriendHelper(this);

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
        mView.findViewById(R.id.pop_tv_dongtai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NoDoubleClickUtils.isDoubleClick())
                    IntentUtils.JumpTo(DongTaiSetActivity.class);
                if (popWindow != null)
                    popWindow.dissmiss();
            }
        });

    }

    DataScanFirendRequest.RecordBean dataRecord;
    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            //            获取好友数据
            case "addFriendQrCode":
                DataScanFirendRequest dataScanFirendRequest = JSON.parseObject(responseText, DataScanFirendRequest.class);
                DataScanFirendRequest.RecordBean record = dataScanFirendRequest.getRecord();
                if (record != null) {
                    dataRecord = record;
                    if (record.getIsRelation() == 2) { //  2 是好友  1 非好友
                        includeTopIvMore.setVisibility(View.VISIBLE);
                        mTvName.setText(record.getNickName());
                        fdaTvNum.setText(record.getWxSno());
                        String RemarkText = StrUtils.isEmpty(record.getRemarkName()) ? "暂未设置备注" : record.getRemarkName();
                        fdTvBeizhu.setText(RemarkText);
                        remarkName = record.getRemarkName();
//                        Log.e("qrCode","---------------mix-------------------record.getRemarkName()="+record.getRemarkName());
//                        Log.e("qrCode","----------------mix------------------RemarkText="+RemarkText);
                        String signText = StrUtils.isEmpty(record.getPersonaSignature()) ? "暂未设置签名" : record.getPersonaSignature();
                        fdaTvSign.setText(signText);
//                        Log.e("qrCode","---------------mix-------------------signText="+signText);
//                        Log.e("qrCode","-----------------mix-----------------record.getPersonaSignature()="+record.getPersonaSignature());
                        //  好友分组
                        fdTvFenzu.setText(record.getGroupName());
                        Glide.with(this).load(record.getHeadImg())
                                .bitmapTransform(new CropCircleTransformation(FriendDataMixActivity.this))
                                .into(mIvHead);
                    } else {
                        fdLinName.setVisibility(View.GONE);
                        fdLinSend.setVisibility(View.GONE);
                        fdaTvAdd.setVisibility(View.VISIBLE);
                        fdLinFenzu.setVisibility(View.GONE);

                        mTvName.setText(record.getNickName());
                        fdaTvNum.setText(record.getWxSno());
                        String signText = StrUtils.isEmpty(record.getPersonaSignature()) ? "暂未设置签名" : record.getPersonaSignature();
                        fdaTvSign.setText(signText);
                        Glide.with(this).load(record.getHeadImg())
                                .bitmapTransform(new CropCircleTransformation(FriendDataMixActivity.this))
                                .into(mIvHead);
                    }


//                    mTvName.setText(record.getNickName());
//                    fdaTvNum.setText(record.getWxSno());
//                    String signText = StrUtils.isEmpty(record.getPersonaSignature()) ? "暂未签名" : record.getPersonaSignature();
//                    fdaTvSign.setText(signText);
//                    Glide.with(this).load(record.getHeadImg())
//                            .bitmapTransform(new CropCircleTransformation(FriendDataMixActivity.this))
//                            .into(mIvHead);
//
                    dataSearch = new DataSearch();
                    dataSearch.setSno(record.getWxSno());
                    dataSearch.setName(record.getNickName());
                    dataSearch.setId(record.getFriendId());
                    dataSearch.setHeadImg(record.getHeadImg());
//                    dataSearch.setSign(record.getPersonaSignature());
                }
                break;
            case "friendRemarkName"://修改备注成功
                if (contant.equals(""))
                    fdTvBeizhu.setText("暂未设置备注");
                else
                    fdTvBeizhu.setText(contant + "");
                break;
            case "deleteFriend":
//                DialogUtils.showDialogOne("删除好友成功", new DialogUtils.OnClickSureListener() {
//                @Override
//                public void onClickSure() {
                realmHelper.deleteRealmMsg(FriendId);
                realmChatHelper.deleteRealmMsg(FriendId);
                Intent intent2 = new Intent();
                intent2.putExtra("id", FriendId);
                intent2.setAction("del.refreshMsgFragment");
                sendBroadcast(intent2);
                AppManager.getAppManager().finishActivity(FriendDataMixActivity.this);
                if (esc != null && esc.equals("esc")) {
                    AppManager.getAppManager().finishActivity(ChatActivity.class);
                }
                ToastUtil.show("删除好友成功！");
//                }
//            });
                break;
            case "shieldFriend":
                DialogUtils.showDialogOne("屏蔽好友成功", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
                        AppManager.getAppManager().finishActivity(FriendDataMixActivity.this);
                    }
                });
                break;


        }

    }

    DataMyFriend.RecordBean dataRecord1;

    private void initDataFriend(String responseText) {

        DataMyFriend dataMyFriend = JSON.parseObject(responseText, DataMyFriend.class);

        DataMyFriend.RecordBean record = dataMyFriend.getRecord();
        if (record != null) {
            dataRecord1 = record;
            fdLinName.setVisibility(View.VISIBLE);
            fdLinSend.setVisibility(View.VISIBLE);
            fdLinFenzu.setVisibility(View.VISIBLE);
            fdaTvAdd.setVisibility(View.GONE);

            String imgPath = realmLinkFriendHelper.queryLinkFriendReturnImgPath(FriendId);
            if (imgPath != null) {
                Glide.with(this).load(imgPath)
                        .bitmapTransform(new CropCircleTransformation(FriendDataMixActivity.this))
                        .into(mIvHead);
            } else {
                Glide.with(this).load(record.getHeadImg())
                        .error(R.drawable.mine_head)
                        .bitmapTransform(new CropCircleTransformation(FriendDataMixActivity.this))
                        .into(mIvHead);
            }
//            Glide.with(this).load(record.getHeadImg())
//                    .bitmapTransform(new CropCircleTransformation(FriendDataMixActivity.this))
//                    .into(mIvHead);

            String signText = StrUtils.isEmpty(record.getPersonaSignature()) ? "暂未设置签名" : record.getPersonaSignature();
            fdaTvSign.setText(signText);
//            fdaTvFenzu.setText(record.getGroupName() + "");
            fdaTvNum.setText(record.getWxSno());
            mTvName.setText(record.getNickName());
            String beizhuText = StrUtils.isEmpty(record.getRemarkName()) ? "暂未设置备注" : record.getRemarkName();
            remarkName = record.getRemarkName();
//            mTvName.setText(nameText);
            fdTvBeizhu.setText(beizhuText);
        }
    }
    String remarkName;
    @Override
    protected int getLayoutView() {
        return R.layout.activity_friend_data_mix;
    }

    CustomPopWindow popWindow;

    @OnClick({R.id.include_top_iv_more, R.id.fd_iv_qrcode, R.id.fd_iv_head, R.id.fd_tv_send_call, R.id.fd_tv_send_msg, R.id.fda_tv_add, R.id.fd_lin_name, R.id.fd_lin_fenzu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            顶部点点点按钮
            case R.id.include_top_iv_more:
                if (popWindow == null)
                    popWindow = new CustomPopWindow.PopupWindowBuilder(FriendDataMixActivity.this)
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
                if (NoDoubleClickUtils.isDoubleClick()) {
                    if (dataSearch != null) {
                        PersonData personData = new PersonData();
                        personData.setHeadImg(dataSearch.getHeadImg());
                        personData.setName(dataSearch.getName());
                        personData.setScanTital("扫一扫,添加" + dataSearch.getName() + "为好友");
                        personData.setTital("好友二维码");

                        if (dataSearch.getId() != null) {
                            String string = type + "_xm6leefun_" + dataSearch.getId();
//                            Log.e("qrcode", "----------mix--------------" + string);
                            personData.setQrCode(string);
                        }
                        IntentUtils.JumpToHaveObj(MyAccountActivity.class, MyAccountActivity.TITAL_NAME, personData);
                    }
                }
                break;
//                头像按钮
            case R.id.fd_iv_head:
                int location[] = new int[2];
                view.getLocationOnScreen(location);
                FullImageInfo fullImageInfo = new FullImageInfo();
                fullImageInfo.setLocationX(location[0]);
                fullImageInfo.setLocationY(location[1]);
                fullImageInfo.setWidth(view.getWidth());
                fullImageInfo.setHeight(view.getHeight());
                GlideCacheUtil.getInstance().clearImageAllCache(this);
                String imgPath = realmLinkFriendHelper.queryLinkFriendReturnImgPath(FriendId);
//                List<String> fileName = FilePath.getLinkImgPath();
                if (imgPath!=null)
                {
//                    String path=fileName.get(fileName.size()-1);
                    fullImageInfo.setImageUrl(imgPath);
                    EventBus.getDefault().postSticky(fullImageInfo);
                    startActivity(new Intent(this, FullImageActivity.class));
                    this.overridePendingTransition(0, 0);
                }
                break;
//                选择分组
            case R.id.fd_lin_fenzu:
                if (NoDoubleClickUtils.isDoubleClick()) {
                    Intent intent = new Intent(this, ChooseGroupActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("FriendId", FriendId);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, AppConfig.FRIEND_DATA_GROUP_REQUEST);
                }
                break;
//                发送消息
            case R.id.fda_tv_add:
                if (NoDoubleClickUtils.isDoubleClick())
                    IntentUtils.JumpToHaveObj(AddGoodFriendActivity.class, AddGoodFriendActivity.DataKey, dataSearch);
//                dataSearch.getName()
//                Log.e("qrCode", "----------mix-----dataSearch.getName()--------------" + dataSearch.getName());
                break;
            case R.id.fd_tv_send_call:

                break;
            case R.id.fd_tv_send_msg:
                if (NoDoubleClickUtils.isDoubleClick()) {
                    if (dataRecord != null) {
                        CusJumpChatData cusJumpChatData = new CusJumpChatData();
                        cusJumpChatData.setFriendHeader(dataRecord.getHeadImg());
                        cusJumpChatData.setFriendId(dataRecord.getFriendId());
                        cusJumpChatData.setFriendName(dataRecord.getNickName());
                        realmHelper.addRealmMsg(cusJumpChatData);
                        if (esc != null && esc.equals("esc")) {
//                            AppManager.getAppManager().finishActivity(ChatActivity.class);
//                            IntentUtils.JumpToHaveObj(ChatActivity.class, Constants.KEY_FRIEND_HEADER, cusJumpChatData);
                            AppManager.getAppManager().finishActivity(FriendDataMixActivity.this);
                        } else
                            IntentUtils.JumpToHaveObj(ChatActivity.class, Constants.KEY_FRIEND_HEADER, cusJumpChatData);
                    }
                }
                break;

            case R.id.fd_lin_name:
                if (NoDoubleClickUtils.isDoubleClick())
                    doChangeName();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == AppConfig.FRIEND_ADD_GROUP_RESULT) {
            if (requestCode == AppConfig.FRIEND_DATA_GROUP_REQUEST) {
                String name = data.getStringExtra(ChooseGroupActivity.CHOOSE_NAME);
                String id = data.getStringExtra(ChooseGroupActivity.CHOOSE_ID);
                fdTvFenzu.setText(name);
                //设置结果显示框的显示数值
            }
        }
    }

    private void doChangeName() {
        ChangeInfoWindow changeInfoWindow;
        if (remarkName.equals("")){
            changeInfoWindow = new ChangeInfoWindow(FriendDataMixActivity.this, "修改备注", remarkName);
            changeInfoWindow.showAtLocation(gfLinTop, Gravity.CENTER, 0, 0);
        }
        else {
            changeInfoWindow = new ChangeInfoWindow(FriendDataMixActivity.this, "修改备注", fdTvBeizhu.getText().toString().trim());
            changeInfoWindow.showAtLocation(gfLinTop, Gravity.CENTER, 0, 0);
        }

//        ChangeInfoWindow changeInfoWindow = new ChangeInfoWindow(FriendDataMixActivity.this, "修改备注", fdTvBeizhu.getText().toString().trim());
//        changeInfoWindow.showAtLocation(gfLinTop, Gravity.CENTER, 0, 0);
        changeInfoWindow.setOnAddpopClickListener(this);
    }

    String contant = null;
    @Override
    public void onSure(String contant) {
        this.contant = contant;
        remarkName = contant;
        sendWeb(SplitWeb.friendRemarkName(FriendId, contant));
    }

    @Override
    public void onCancle() {

    }
}
