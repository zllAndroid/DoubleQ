package com.doubleq.xm6leefunz.about_chat.chat_group;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.doubleq.model.CusJumpChatData;
import com.doubleq.model.DataGroupMemberInfo;
import com.doubleq.model.DataMyFriend;
import com.doubleq.model.DataScanFirendRequest;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_chat.ChatActivity;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmHomeHelper;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.PersonData;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_add.AddGoodFriendActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_search.DataSearch;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity.MyAccountActivity;
import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.rance.chatui.util.Constants;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * 位置：群成员详情
 * zll
 */
public class FriendDataGroupMemberActivity extends BaseActivity {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.include_top_iv_more)
    ImageView includeTopIvMore;
    @BindView(R.id.data_iv_head)
    ImageView mIvHead;
    @BindView(R.id.inclu_tv_right)
    TextView incluTvRight;
    @BindView(R.id.fda_tv_name)
    TextView mTvName;
    @BindView(R.id.fda_tv_num)
    TextView fdaTvNum;
    @BindView(R.id.fda_tv_sign)
    TextView fdaTvSign;
    @BindView(R.id.fd_tv_add_friend)
    TextView fdTvAddFriend;


    public static final String FRIENG_ID_KEY = "friendId";
    public static final String GROUP_ID_KEY = "groupId";
    public static final String IS_FRIEND = "2";
    public static final String NOT_FRIEND = "1";

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    DataSearch dataSearch = null;

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("好友资料");
        incluTvRight.setVisibility(View.GONE);
        includeTopIvMore.setVisibility(View.GONE);

//        realmHelper = new RealmHomeHelper(this);

        Intent intent = getIntent();
        if (intent != null) {
//            dataSearch = (DataSearch) intent.getSerializableExtra("dataSearch");
//            if (dataSearch!=null) {
//                mTvName.setText(dataSearch.getName());
//                fdaTvNum.setText(dataSearch.getSno());
//                fdaTvSign.setText(dataSearch.getName());
//                Glide.with(this).load(dataSearch.getHeadImg())
//                        .bitmapTransform(new CropCircleTransformation(FriendDataGroupMemberActivity.this))
//                        .into(mIvHead);
//            }else {
            String friendId = intent.getStringExtra(FRIENG_ID_KEY);
            String groupId = intent.getStringExtra(GROUP_ID_KEY);

            sendWeb(SplitWeb.getGroupMemberInfo(friendId, groupId));
            sendWeb(SplitWeb.addFriendQrCode(friendId));
//                sendWebHaveDialog(SplitWeb.getGroupMemberInfo(friendId,groupId),"搜索好友信息中...","获取成功");
//                sendWebHaveDialog(SplitWeb.getFriendInfo(id),"搜索好友信息中...","获取成功");
//            }
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
            case "getGroupMemberInfo":

                initDataMember(responseText);
                break;
            case "addFriendQrCode":
                DataScanFirendRequest dataScanFirendRequest = JSON.parseObject(responseText, DataScanFirendRequest.class);
                DataScanFirendRequest.RecordBean record = dataScanFirendRequest.getRecord();
                if (record != null) {
                    mTvName.setText(record.getNickName());
                    fdaTvNum.setText(record.getWxSno());
                    String signText = StrUtils.isEmpty(record.getPersonaSignature()) ? "暂未签名" : record.getPersonaSignature();
                    fdaTvSign.setText(signText);
                    Glide.with(this).load(record.getHeadImg())
                            .bitmapTransform(new CropCircleTransformation(FriendDataGroupMemberActivity.this))
                            .into(mIvHead);

                    dataSearch = new DataSearch();
                    dataSearch.setSno(record.getWxSno());
                    dataSearch.setName(record.getNickName());
                    dataSearch.setId(record.getFriendId());
                    dataSearch.setHeadImg(record.getHeadImg());
                }
                break;



        }

    }

    private void initDataMember(String responseText) {
//        DataGroupMemberInfo
        DataGroupMemberInfo dataGroupMemberInfo = JSON.parseObject(responseText, DataGroupMemberInfo.class);
        DataGroupMemberInfo.RecordBean record = dataGroupMemberInfo.getRecord();
        if (record != null) {
            mTvName.setText(record.getNickName());
            fdaTvNum.setText(record.getWxSno());
//            String signText= StrUtils.isEmpty(record.get())?"暂未签名":record.getPersonaSignature();
//            fdaTvSign.setText(signText);
            Glide.with(this).load(record.getHeadImg())
                    .bitmapTransform(new CropCircleTransformation(FriendDataGroupMemberActivity.this))
                    .into(mIvHead);
        }


    }

    DataMyFriend.RecordBean dataRecord;

    private void initDataFriend(String responseText) {

        DataMyFriend dataMyFriend = JSON.parseObject(responseText, DataMyFriend.class);

        DataMyFriend.RecordBean record = dataMyFriend.getRecord();
        if (record != null) {
            dataRecord = record;
            Glide.with(this).load(record.getHeadImg())
                    .bitmapTransform(new CropCircleTransformation(FriendDataGroupMemberActivity.this))
                    .into(mIvHead);
            String signText = StrUtils.isEmpty(record.getPersonaSignature()) ? "暂未设置签名" : record.getPersonaSignature();
            fdaTvSign.setText(signText);
//            fdaTvFenzu.setText(record.getGroupName() + "");
            fdaTvNum.setText(record.getWxSno());
            mTvName.setText(record.getNickName());
            String beizhuText = StrUtils.isEmpty(record.getRemarkName()) ? "暂未设置备注" : record.getRemarkName();
//            mTvName.setText(nameText);
//            fdTvBeizhu.setText(beizhuText);
        }
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_friend_data_group_member;
    }

//    RealmHomeHelper realmHelper;
    @OnClick({R.id.include_top_iv_more, R.id.data_iv_qrcode, R.id.data_iv_head, R.id.fd_tv_add_friend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            顶部点点点按钮
            case R.id.include_top_iv_more:
                break;
//                二维码按钮
            case R.id.data_iv_qrcode:
                if (NoDoubleClickUtils.isDoubleClick()) {
                    if (dataSearch != null) {
                        PersonData personData = new PersonData();
                        personData.setHeadImg(dataSearch.getHeadImg());
                        personData.setName(dataSearch.getName());
                        personData.setScanTital("扫一扫,添加" + dataSearch.getName() + "为好友");
                        personData.setTital("好友二维码");
                        personData.setQrCode(dataSearch.getQrcode());
                        IntentUtils.JumpToHaveObj(MyAccountActivity.class, MyAccountActivity.TITAL_NAME, personData);
                    }
                }
                break;
//                头像按钮
            case R.id.data_iv_head:
                break;
//                添加好友
            case R.id.fd_tv_add_friend:
                if (NoDoubleClickUtils.isDoubleClick())
                    IntentUtils.JumpToHaveObj(AddGoodFriendActivity.class, AddGoodFriendActivity.DataKey, dataSearch);
                break;
////                发送消息
//            case R.id.fd_tv_send_msg:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    if (dataRecord != null) {
//                        CusJumpChatData cusJumpChatData = new CusJumpChatData();
//                        cusJumpChatData.setFriendHeader(dataRecord.getHeadImg());
//                        cusJumpChatData.setFriendId(dataRecord.getFriendId());
//                        cusJumpChatData.setFriendName(dataRecord.getNickName());
//                        realmHelper.addRealmMsg(cusJumpChatData);
//                        IntentUtils.JumpToHaveObj(ChatActivity.class, Constants.KEY_FRIEND_HEADER, cusJumpChatData);
//                    }
//                }
//                break;
////                发送语音
//            case R.id.fd_tv_send_call:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//
//                }
//                break;
        }
    }

}
