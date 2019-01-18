package com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_notice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.doubleq.model.DataNews;
import com.doubleq.model.DataNoticeDetails;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_chat.ChatActivity;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.TimeUtil;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.CusChatData;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.CusHomeRealmData;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmChatHelper;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmHomeHelper;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.FriendDataAddActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.NoticeActivity;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * 位置：好友添加回馈界面
 */
public class NoticeDetailsActivity extends BaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.include_top_iv_more)
    ImageView includeTopIvMore;
    @BindView(R.id.inclu_tv_right)
    TextView incluTvRight;
    @BindView(R.id.notice_tv_name)
    TextView mTvName;
    @BindView(R.id.notice_iv_qrcode)
    ImageView noticeIvQrcode;
    @BindView(R.id.notice_iv_head)
    ImageView mIvHead;
    @BindView(R.id.notice_tv_num)
    TextView noticeTvNum;
    @BindView(R.id.notice_tv_sign)
    TextView noticeTvSign;
    @BindView(R.id.notice_tv_remark)
    TextView noticeTvRemark;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    RealmHomeHelper realmHelper;
    RealmChatHelper realmChatHelper;
    @Override
    protected void initBaseView() {
        super.initBaseView();
        realmHelper = new RealmHomeHelper(this);
        realmChatHelper = new RealmChatHelper(this);
        includeTopTvTital.setText("好友资料");
        Intent intent = getIntent();
        item= (DataNews.RecordBean.ListInfoBean)intent.getSerializableExtra("id");
        sendWeb(SplitWeb.messageDetail(item.getId()));
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
//          拒绝好友请求
            case "refuseFriend":
                DialogUtils.isShow();
//                DialogUtils.showDialogOne("拒绝请求成功", new DialogUtils.OnClickSureListener() {
//                    @Override
//                    public void onClickSure() {
                ToastUtil.show("拒绝请求成功");
                        AppManager.getAppManager().finishActivity();
//                    }
//                });
                break;
//                同意
            case "agreeFriend":
//                final CusHomeRealmData cusJumpChatData = new CusHomeRealmData();
//                cusJumpChatData.setHeadImg(item.getHeadImg());
//                cusJumpChatData.setFriendId(item.getSendUserId());
//                cusJumpChatData.setNickName(item.getNickName());
//                cusJumpChatData.setMsg("新添加的好友");
//                cusJumpChatData.setTime(TimeUtil.getTime());
//                cusJumpChatData.setNum(0);
//                realmHelper.addRealmMsg(cusJumpChatData);
//                dealAgreeFriend();
                DialogUtils.isShow();
//                DialogUtils.showDialogOne("同意好友请求成功", new DialogUtils.OnClickSureListener() {
//                    @Override
//                    public void onClickSure() {
//                        AppManager.getAppManager().finishActivity(NoticeDetailsActivity.this);
//                        AppManager.getAppManager().finishActivity(NoticeActivity.class);
//                    }
//                });
                ToastUtil.show("同意好友请求成功");
                AppManager.getAppManager().finishActivity(NoticeDetailsActivity.this);
                AppManager.getAppManager().finishActivity(NoticeActivity.class);

                break;
//                消息通知详情页面接口
            case "messageDetail":
                DataNoticeDetails dataNoticeDetails = JSON.parseObject(responseText, DataNoticeDetails.class);
                DataNoticeDetails.RecordBean record = dataNoticeDetails.getRecord();
                if(record!=null)
                {
                    DataNoticeDetails.RecordBean.UserDetailInfoBean userDetailInfo = record.getUserDetailInfo();

                    if (userDetailInfo!=null)
                    {
                        initData(userDetailInfo);
                    }
                }
                break;

        }

    }
    private void initData(DataNoticeDetails.RecordBean.UserDetailInfoBean userDetailInfo) {
        Glide.with(this).load(userDetailInfo.getHeadImg())
                .bitmapTransform(new CropCircleTransformation(this))
                .error(R.drawable.mine_head)
               .into(mIvHead);
        mTvName.setText(userDetailInfo.getNickName());
        noticeTvRemark.setText("备注:"+userDetailInfo.getRemark());
    }


    @Override
    protected int getLayoutView() {
        return R.layout.activity_notice_details;
    }

    DataNews.RecordBean.ListInfoBean item;
    @OnClick({R.id.notice_iv_qrcode, R.id.notice_tv_jujue, R.id.notice_tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.notice_iv_qrcode:
                break;
            case R.id.notice_tv_jujue:
                sendWeb(SplitWeb.refuseFriend(item.getId(),"1"));
                break;
            case R.id.notice_tv_ok:
                sendWeb(SplitWeb.agreeFriend(item.getId()));
                break;
        }
    }
}
