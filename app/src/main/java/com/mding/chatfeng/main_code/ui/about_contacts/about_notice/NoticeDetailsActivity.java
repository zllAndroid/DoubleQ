package com.mding.chatfeng.main_code.ui.about_contacts.about_notice;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.mding.chatfeng.about_utils.ImageUtils;
import com.mding.model.DataNews;
import com.mding.model.DataNoticeDetails;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.about_utils.about_realm.new_home.RealmChatHelper;
import com.mding.chatfeng.about_utils.about_realm.new_home.RealmHomeHelper;
import com.mding.chatfeng.main_code.ui.about_contacts.PersonData;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.main_code.ui.about_contacts.about_contacts_adapter.FriendNoticeDetailsAdapter;
import com.mding.chatfeng.main_code.ui.about_contacts.about_search.DataSearch;
import com.mding.chatfeng.main_code.ui.about_personal.about_activity.ChangeInfoWindow;
import com.mding.chatfeng.main_code.ui.about_personal.about_activity.MyAccountActivity;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
    @BindView(R.id.notice_tv_reply)
    TextView noticeTvReply;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    RealmHomeHelper realmHelper;
    RealmChatHelper realmChatHelper;
    FriendNoticeDetailsAdapter friendNoticeDetailsAdapter = null;
    List<DataNoticeDetails.RecordBean.UserDetailInfoBean.RemarkBean> mList = new ArrayList<>();

    @Override
    protected void initBaseView() {
        super.initBaseView();
        realmHelper = new RealmHomeHelper(this);
        realmChatHelper = new RealmChatHelper(this);
        includeTopTvTital.setText("好友资料");
        includeTopLinBackground.setBackgroundColor(getResources().getColor(R.color.app_theme));

        mRecyclerView.setLayoutManager(new GridLayoutManager(NoticeDetailsActivity.this, 1));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(NoticeDetailsActivity.this));
//        friendNoticeDetailsAdapter = new FriendNoticeDetailsAdapter(this, mList);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//        mRecyclerView.setAdapter(friendNoticeDetailsAdapter);
//        friendNoticeDetailsAdapter.notifyDataSetChanged();
        Intent intent = getIntent();
        item = (DataNews.RecordBean.ListInfoBean) intent.getSerializableExtra("id");
        sendWeb(SplitWeb.getSplitWeb().messageDetail(item.getId()));
        initAdapter(mList);
    }

    private void initAdapter(List<DataNoticeDetails.RecordBean.UserDetailInfoBean.RemarkBean> mList) {
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
        friendNoticeDetailsAdapter = new FriendNoticeDetailsAdapter(NoticeDetailsActivity.this, mList);
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
//                AppManager.getAppManager().finishActivity(NoticeActivity.class);

                break;
//                消息通知详情页面接口
            case "messageDetail":
                DataNoticeDetails dataNoticeDetail = JSON.parseObject(responseText, DataNoticeDetails.class);
                DataNoticeDetails.RecordBean record = dataNoticeDetail.getRecord();
                Log.e("messageDetail", "----------------------------前------------------------------------");
                if (record != null) {
                    Log.e("messageDetail", "----------------------------record != null------------------------------------");
                    DataNoticeDetails.RecordBean.UserDetailInfoBean userDetailInfo = record.getUserDetailInfo();
                    if (userDetailInfo != null) {
                        Log.e("messageDetail", "----------------------------initData（）------------------------------------");
                        initData(dataNoticeDetail);
                    }
                }
                break;
            case "messageReply":
                if (mList.size() == 3) {  //备注信息等于三条时
                    Log.e("messageDetail", "------------------------备注 > 3-----------------------recordBean");
                    mList.remove(0);
                    friendNoticeDetailsAdapter.notifyItemChanged(0);
                    // 括号
//                    List<DataNoticeDetails.RecordBean.UserDetailInfoBean.RemarkBean> remarkBean = userDetailInfo.getRemark();
//                    String message = friendsName + "：" + msg;
//                    remarkBean.get(2).setMessage(friendsName + "：" + msg);
//                    mList.add(2, remarkBean.get(2));
                    // 括号
                    if (!StrUtils.isEmpty(reply)){
                        mList.get(2).setMessage(friendsName + "：" + reply);
                    }else{
                        mLinMain.setBackground(getResources().getDrawable(R.drawable.friend_data_bg));
                    }
                    friendNoticeDetailsAdapter.notifyDataSetChanged();

                } else {
                    if (!StrUtils.isEmpty(reply)){
                        mList.get(mList.size()-1).setMessage(friendsName + "：" + reply);
                    }else{
                        mLinMain.setBackground(getResources().getDrawable(R.drawable.friend_data_bg));
                    }
                    friendNoticeDetailsAdapter.notifyDataSetChanged();
                }
                friendNoticeDetailsAdapter = new FriendNoticeDetailsAdapter(NoticeDetailsActivity.this, mList);
                mRecyclerView.setAdapter(friendNoticeDetailsAdapter);
                friendNoticeDetailsAdapter.notifyDataSetChanged();

//                if (mList.size() == 3) {
//                    mList.remove(0);
//                    friendNoticeDetailsAdapter.notifyItemChanged(0);
//                    DataNoticeDetails dataNoticeDetails = new DataNoticeDetails();
//                    DataNoticeDetails.RecordBean recordBean = dataNoticeDetails.getRecord();
//                    if (recordBean != null) {
//                        DataNoticeDetails.RecordBean.UserDetailInfoBean userDetailInfoBean = recordBean.getUserDetailInfo();
//                        if (userDetailInfoBean != null) {
//                            List<DataNoticeDetails.RecordBean.UserDetailInfoBean.RemarkBean> remarkBean = userDetailInfoBean.getRemark();
//                            remarkBean.get(2).setMessage("我：" + reply);
////                            mList.add(2, remarkBean.get(2));
//                            friendNoticeDetailsAdapter.notifyDataSetChanged();
//                        }
//                    }
//                } else {
//                    DataNoticeDetails dataNoticeDetails = new DataNoticeDetails();
//                    DataNoticeDetails.RecordBean recordBean = dataNoticeDetails.getRecord();
//                    if (recordBean != null) {
//                        DataNoticeDetails.RecordBean.UserDetailInfoBean userDetailInfoBean = recordBean.getUserDetailInfo();
//                        if (userDetailInfoBean != null) {
//                            List<DataNoticeDetails.RecordBean.UserDetailInfoBean.RemarkBean> remarkBean = userDetailInfoBean.getRemark();
//                            remarkBean.get(mList.size()-1).setMessage("我：" + reply);
//                            mList.add(remarkBean.get(mList.size()-1));
//                            friendNoticeDetailsAdapter.notifyDataSetChanged();
//                        }
//                    }
//                }
//                friendNoticeDetailsAdapter = new FriendNoticeDetailsAdapter(NoticeDetailsActivity.this, mList);
////        增加分割线
////        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//                mRecyclerView.setAdapter(friendNoticeDetailsAdapter);
//                friendNoticeDetailsAdapter.notifyDataSetChanged();
                break;
        }
    }

    String friendsName;
    private void initData(DataNoticeDetails dataNoticeDetails) {
        DataNoticeDetails.RecordBean recordBean = dataNoticeDetails.getRecord();
        if (recordBean != null){
            DataNoticeDetails.RecordBean.UserDetailInfoBean userDetailInfo = recordBean.getUserDetailInfo();
            ImageUtils.useBase64(NoticeDetailsActivity.this, mIvHead, userDetailInfo.getHeadImg());
//            Glide.with(this).load(userDetailInfo.getHeadImg())
//                    .bitmapTransform(new CropCircleTransformation(this))
//                    .error(R.drawable.first_head_nor)
//                    .into(mIvHead);
            mTvName.setText(userDetailInfo.getNickName());
//        TODO  添加帐号
            noticeTvNum.setText(userDetailInfo.getWxSno());
            String sign = StrUtils.isEmpty(userDetailInfo.getPersonaSignature())?"暂未设置签名":userDetailInfo.getPersonaSignature();
            noticeTvSign.setText(sign);
            friendsName = userDetailInfo.getNickName();

            dataSearch = new DataSearch();
            dataSearch.setName(userDetailInfo.getNickName());
            dataSearch.setQrcode(userDetailInfo.getQrcode());
            dataSearch.setHeadImg(userDetailInfo.getHeadImg());
            dataSearch.setPersonaSignature(userDetailInfo.getPersonaSignature());

            List<DataNoticeDetails.RecordBean.UserDetailInfoBean.RemarkBean> remarkBeanList = userDetailInfo.getRemark();
//        位置0是最新的消息
            String msg = remarkBeanList.get(0).getMessage();
            mList = remarkBeanList;
            Log.e("messageDetail", "-----------------------第0条消息----------------------" + msg);
            if (msg != null) {
                if (remarkBeanList.size() == 3) {  //备注信息等于三条时
                    Log.e("messageDetail", "------------------------备注条数 > 3-----------------------recordBean");
                    mList.remove(0);
                    friendNoticeDetailsAdapter.notifyItemChanged(0);
//                    DataNoticeDetails dataNoticeDetails = new DataNoticeDetails();
//                    DataNoticeDetails.RecordBean recordBean = dataNoticeDetails.getRecord();
//                    if (recordBean != null) {
//                        DataNoticeDetails.RecordBean.UserDetailInfoBean userDetailInfoBean = recordBean.getUserDetailInfo();
//                        if (userDetailInfoBean != null) {
                    List<DataNoticeDetails.RecordBean.UserDetailInfoBean.RemarkBean> remarkBean = userDetailInfo.getRemark();
//                    String message = friendsName + "：" + msg;
//                    remarkBean.get(2).setMessage(friendsName + "：" + msg);
//                    mList.add(2, remarkBean.get(2));
                    if (!StrUtils.isEmpty(msg)){
                        mList.get(2).setMessage(friendsName + "：" + msg);
                    }else{
                        mLinMain.setBackground(getResources().getDrawable(R.drawable.friend_data_bg));
                    }
                    friendNoticeDetailsAdapter.notifyDataSetChanged();
//                        }
//                    }
                } else {  // 备注信息小于三条时
//                    DataNoticeDetails dataNoticeDetails = new DataNoticeDetails();
//                    DataNoticeDetails.RecordBean recordBean = dataNoticeDetails.getRecord();
//                    if (recordBean != null) {
//                        Log.e("messageDetail", "------------------------备注小于三条-----------------------recordBean");
//                        DataNoticeDetails.RecordBean.UserDetailInfoBean userDetailInfoBean = recordBean.getUserDetailInfo();
//                        if (userDetailInfoBean != null) {
//                    List<DataNoticeDetails.RecordBean.UserDetailInfoBean.RemarkBean> remarkBean = userDetailInfo.getRemark();
//                    Log.e("messageDetail", "------------------------备注小于三条------------------------remarkBean = " + remarkBean.get(mList.size()-1).getMessage());
//                    mList.add(mList.get(mList.size()-1));
//                    Log.e("messageDetail", "------------------------备注小于三条------------------------mList = " + mList.get(mList.size()-1).getMessage());
                    if (!StrUtils.isEmpty(msg)){
                        mList.get(mList.size()-1).setMessage(friendsName + "：" + msg);
                    }else{
                        mLinMain.setBackground(getResources().getDrawable(R.drawable.friend_data_bg));
                    }
                    friendNoticeDetailsAdapter.notifyDataSetChanged();
//                    friendNoticeDetailsAdapter.setData(mList.size()-1, remarkBean.get(mList.size()-1));
//                        }
//                    }
                }
                friendNoticeDetailsAdapter = new FriendNoticeDetailsAdapter(NoticeDetailsActivity.this, mList);
//        增加分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
                mRecyclerView.setAdapter(friendNoticeDetailsAdapter);
                friendNoticeDetailsAdapter.notifyDataSetChanged();
            } else {  // 若无备注信息
                mLinMain.setBackground(getResources().getDrawable(R.drawable.friend_data_bg));
                noticeTvReply.setVisibility(View.GONE);
            }
        }

    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_notice_details;
    }

    DataNews.RecordBean.ListInfoBean item;
    DataSearch dataSearch = null;

    @OnClick({R.id.notice_iv_qrcode, R.id.notice_tv_jujue, R.id.notice_tv_ok, R.id.notice_tv_reply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.notice_iv_qrcode:
                if (NoDoubleClickUtils.isDoubleClick()) {
                    if (dataSearch != null) {
                        PersonData personData = new PersonData();
                        personData.setHeadImg(dataSearch.getHeadImg());
                        personData.setName(dataSearch.getName());
                        personData.setScanTital("扫一扫,添加" + dataSearch.getName() + "为好友");
                        personData.setTital("好友二维码");

//                        if (dataSearch.getId() != null) {
//                            String string = type + "_xm6leefun_" + dataSearch.getId();
//                            Log.e("qrcode", "----------FriendDataAddActivity--------------" + string);
//                            personData.setQrCode(string);
//                        }
                        personData.setQrCode(dataSearch.getQrcode());
                        IntentUtils.JumpToHaveObj(MyAccountActivity.class, MyAccountActivity.TITAL_NAME, personData);
                    }
                }
                break;
            case R.id.notice_tv_jujue:
                sendWeb(SplitWeb.getSplitWeb().refuseFriend(item.getId(), "1"));
                break;
            case R.id.notice_tv_ok:
                sendWeb(SplitWeb.getSplitWeb().agreeFriend(item.getId()));
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

    String reply;
    @Override
    public void onSure(String content) {
        if (content != null) {
            reply = content;
            sendWeb(SplitWeb.getSplitWeb().messageReply(item.getId(), content));
        } else {
            ToastUtil.show("回复内容不能为空！");
        }
    }

    @Override
    public void onCancle() {

    }

}
