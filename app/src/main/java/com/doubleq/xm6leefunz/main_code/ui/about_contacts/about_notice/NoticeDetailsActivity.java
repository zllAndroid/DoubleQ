package com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_notice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.doubleq.model.DataNews;
import com.doubleq.model.DataNoticeDetail;
import com.doubleq.model.DataNoticeDetails;
import com.doubleq.model.find_friend.DataDiscoveryFriendCircle;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmChatHelper;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmHomeHelper;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.NoticeActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter.FriendNoticeDetailsAdapter;
import com.doubleq.xm6leefunz.main_code.ui.about_discovery.FriendCircleActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_discovery.about_discovery_fragment.DiscoveryFriendCircleAdapter;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity.ChangeInfoWindow;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * 位置：好友添加回馈界面
 */
public class NoticeDetailsActivity extends BaseActivity implements ChangeInfoWindow.OnAddContantClickListener {

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
    @BindView(R.id.include_top_lin_background)
    LinearLayout includeTopLinBackground;
    @BindView(R.id.notice_detail_recyc)
    RecyclerView mRecyclerView;
    @BindView(R.id.notice_details_lin_main)
    LinearLayout mLinMain;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    RealmHomeHelper realmHelper;
    RealmChatHelper realmChatHelper;
    FriendNoticeDetailsAdapter friendNoticeDetailsAdapter = null;
    List<DataNoticeDetail> mList = new ArrayList<>();

    @Override
    protected void initBaseView() {
        super.initBaseView();
        realmHelper = new RealmHomeHelper(this);
        realmChatHelper = new RealmChatHelper(this);
        includeTopTvTital.setText("好友资料");
        includeTopLinBackground.setBackgroundColor(getResources().getColor(R.color.app_theme));

        mRecyclerView.setLayoutManager(new GridLayoutManager(NoticeDetailsActivity.this,1));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(NoticeDetailsActivity.this));
//        friendNoticeDetailsAdapter = new FriendNoticeDetailsAdapter(this, mList);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//        mRecyclerView.setAdapter(friendNoticeDetailsAdapter);
        initAdapter(mList);
//        friendNoticeDetailsAdapter.notifyDataSetChanged();

        Intent intent = getIntent();
        item = (DataNews.RecordBean.ListInfoBean) intent.getSerializableExtra("id");
        sendWeb(SplitWeb.messageDetail(item.getId()));
    }

    String reply = null;
    private void initAdapter(List<DataNoticeDetail> mList) {
//        DataNoticeDetail dataNoticeDetail = new DataNoticeDetail();
//        dataNoticeDetail.setNoticeDetail("第一次请求添加好友！");
//        DataNoticeDetail dataNoticeDetail2 = new DataNoticeDetail();
//        dataNoticeDetail2.setNoticeDetail("第二次请求添加好友！");
//        DataNoticeDetail dataNoticeDetail3 = new DataNoticeDetail();
//        dataNoticeDetail3.setNoticeDetail("第三次请求添加好友！");
//        mList.add(dataNoticeDetail);
//        mList.add(dataNoticeDetail2);
//        mList.add(dataNoticeDetail3);


//        if (mList.size() > 3) {
//            mList.remove(0);
//            friendNoticeDetailsAdapter.notifyItemChanged(0);
//            DataNoticeDetail dataNoticeDetail4 = new DataNoticeDetail();
//            dataNoticeDetail4.setNoticeDetail(reply);
//            mList.add(dataNoticeDetail4);
////            friendNoticeDetailsAdapter.notifyDataSetChanged();
//        }
        friendNoticeDetailsAdapter = new FriendNoticeDetailsAdapter(NoticeDetailsActivity.this,mList );
//        增加分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(friendNoticeDetailsAdapter);
        friendNoticeDetailsAdapter.notifyDataSetChanged();
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
                if (record != null) {
                    DataNoticeDetails.RecordBean.UserDetailInfoBean userDetailInfo = record.getUserDetailInfo();

                    if (userDetailInfo != null) {
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
//        noticeTvRemark.setText("备注:" + userDetailInfo.getRemark());
    }


    @Override
    protected int getLayoutView() {
        return R.layout.activity_notice_details;
    }

    DataNews.RecordBean.ListInfoBean item;

    @OnClick({R.id.notice_iv_qrcode, R.id.notice_tv_jujue, R.id.notice_tv_ok, R.id.notice_tv_reply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.notice_iv_qrcode:
                break;
            case R.id.notice_tv_jujue:
                sendWeb(SplitWeb.refuseFriend(item.getId(), "1"));
                break;
            case R.id.notice_tv_ok:
                sendWeb(SplitWeb.agreeFriend(item.getId()));
                break;
            case R.id.notice_tv_reply:
                doReply();
                break;
        }
    }

    private void doReply() {
        ChangeInfoWindow changeInfoWindow = new ChangeInfoWindow(NoticeDetailsActivity.this, "回复", "");
        changeInfoWindow.showAtLocation(mLinMain, Gravity.CENTER, 0, 0);
        changeInfoWindow.setOnAddpopClickListener(this);
    }

    @Override
    public void onSure(String contant) {
        reply = contant;
        if (mList.size() == 3) {
            mList.remove(0);
            friendNoticeDetailsAdapter.notifyItemChanged(0);
            DataNoticeDetail dataNoticeDetail4 = new DataNoticeDetail();
            dataNoticeDetail4.setNoticeDetail(reply);
            mList.add(dataNoticeDetail4);
            Log.e("remarkInfo","-------------------------------备注信息000 = "+mList.get(0).getNoticeDetail());
            Log.e("remarkInfo","-------------------------------备注信息222 = "+mList.get(2).getNoticeDetail());
//            friendNoticeDetailsAdapter.notifyDataSetChanged();
        }
        else {
            DataNoticeDetail dataNoticeDetail4 = new DataNoticeDetail();
            String reply = "来自神秘国度的阿三啊：" + this.reply;
            dataNoticeDetail4.setNoticeDetail(reply);
            mList.add(dataNoticeDetail4);
            Log.e("remarkInfo","-------------------------------备注信息333 = "+mList.get(0).getNoticeDetail());
        }
        friendNoticeDetailsAdapter = new FriendNoticeDetailsAdapter(NoticeDetailsActivity.this,mList );
//        增加分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(friendNoticeDetailsAdapter);
        friendNoticeDetailsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCancle() {

    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // TODO: add setContentView(...) invocation
//        ButterKnife.bind(this);
//    }

}
