package com.doubleq.xm6leefunz.main_code.ui.about_contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.doubleq.model.DataAddQunDetails;
import com.doubleq.model.DataScanGroupRequest;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_add.AddGoodGroupActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter.GroupMemberAdapter;
//import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter.GroupMemberQrCodeAdapter;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_search.DataSearch;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_top_add.QunCodeActivity;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_top_add.QunCodeActivity.TITAL_NAME;

/**
 * 位置：群聊资料界面  (添加中，未入群)
 */
public class GroupDataAddActivity extends BaseActivity{
//    @BindView(R.id.include_top_tv_tital)
//    TextView includeTopTvTital;
//    @BindView(R.id.inclu_tv_right)
//    TextView incluTvRight;
//    @BindView(R.id.include_top_iv_more)
//    ImageView includeTopIvMore;
//    @BindView(R.id.group_data_iv_head)
//    ImageView groupDataIvHead;
//    @BindView(R.id.group_data_tv_name)
//    TextView groupDataTvName;
//    @BindView(R.id.group_data_tv_chatnum)
//    TextView groupDataTvChatnum;
//    @BindView(R.id.group_data_recyc)
//    RecyclerView groupDataRecyc;
//    @BindView(R.id.group_data_tv_gonggao)
//    TextView groupDataTvGonggao;
//    @BindView(R.id.include_top_lin_background)
//    LinearLayout includeTopLinBackground;
//    @BindView(R.id.group_data_lin_intogrouplist)
//    LinearLayout groupDataLinIntogrouplist;
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////    }
//
//    DataSearch dataSearch = null;
//    String type = "2";
//
//    //    String groupId;
//    @Override
//    protected void initBaseView() {
//        super.initBaseView();
//        includeTopTvTital.setText("群聊资料");
//        incluTvRight.setVisibility(View.GONE);
//        includeTopIvMore.setVisibility(View.GONE);
//        includeTopLinBackground.setBackgroundColor(getResources().getColor(R.color.app_theme));
//
//        groupDataRecyc.setHasFixedSize(true);
//        groupDataRecyc.setNestedScrollingEnabled(false);
//        groupDataRecyc.setLayoutManager(new GridLayoutManager(this, 5));
//        Intent intent = getIntent();
//        if (intent != null) {
////            dataSearch = (DataSearch)intent.getSerializableExtra(AppConfig.GROUP_ADDKEY);
//            groupSno = intent.getStringExtra(AppConfig.GROUP_SNO);
////            if (dataSearch!=null)
////            {
////                groupDataTvName.setText(dataSearch.getName());
////                Glide.with(this).load(dataSearch.getHeadImg())
////                        .bitmapTransform(new CropCircleTransformation(GroupDataAddActivity.this))
////                        .error(R.drawable.qun_head)
//////                .crossFade(1000)
////                        .into(groupDataIvHead);
////
////            }
////                if (!StrUtils.isEmpty(dataSearch.getId()))
////                    sendWeb(SplitWeb.searchDetailInfo(dataSearch.getId()));
////            else {
//            sendWeb(SplitWeb.addGroupOfQrCode(groupSno));
////                }
//        }
//    }
//
//    String groupSno;
//    @Override
//    public void receiveResultMsg(String responseText) {
//        super.receiveResultMsg(responseText);
//        String s = HelpUtils.backMethod(responseText);
//        switch (s) {
////            case "searchDetailInfo":
////                DataAddQunDetails dataAddQunDetails = JSON.parseObject(responseText, DataAddQunDetails.class);
////                DataAddQunDetails.RecordBean record = dataAddQunDetails.getRecord();
////                if (record != null) {
////                    DataAddQunDetails.RecordBean.GroupDetailInfoBean groupInfo = record.getGroupDetailInfo();
////                    if (groupInfo != null) {
////                        DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupInfoBean group_info = groupInfo.getGroupInfo();
////                        if (group_info != null) {
////                            initUI(group_info);
////                        }
////                        List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> group_user_info = groupInfo.getGroupUserInfo();
////                        if (group_user_info.size() > 0) {
////                            initAdapter(group_user_info);
////                        }
////                        DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupNoticeBean group_notice = groupInfo.getGroupNotice();
////                        if (group_notice != null) {
////                            initNotice(group_notice);
////                        }
////                    }
////                }
////                break;
//
//            case "addGroupOfQrCode":
//                DataScanGroupRequest dataScanGroupRequest = JSON.parseObject(responseText, DataScanGroupRequest.class);
//                DataScanGroupRequest.RecordBean recordBean = dataScanGroupRequest.getRecord();
//                if (recordBean != null) {
//                    DataScanGroupRequest.RecordBean.GroupDetailInfoBean groupInfo = recordBean.getGroupDetailInfo();
//                    if (groupInfo != null) {
//                        DataScanGroupRequest.RecordBean.GroupDetailInfoBean.GroupInfoBean group_info = groupInfo.getGroupInfo();
//                        if (group_info != null) {
//                            dataRecord = group_info;
//                            initQrCodeUI(group_info);
//                        }
//                        List<DataScanGroupRequest.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> group_user_info = groupInfo.getGroupUserInfo();
//                        if (group_user_info.size() > 0) {
//                            initQrCodeAdapter(group_user_info);
//                        }
//                        DataScanGroupRequest.RecordBean.GroupDetailInfoBean.GroupNoticeBean group_notice = groupInfo.getGroupNotice();
//                        if (group_notice != null) {
//                            initQrCodeNotice(group_notice);
//                        }
//                    }
//                }
//                break;
//        }
//    }
//
////    private void initNotice(DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupNoticeBean group_notice) {
////        if (!StrUtils.isEmpty(group_notice.getNoticeContent()))
////            groupDataTvGonggao.setText(group_notice.getNoticeContent());
////    }
////
////    List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> mList = new ArrayList<>();
////    List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> mList2 = new ArrayList<>();
////
////    private void initAdapter(List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> group_user_info) {
//////        mList.clear();
////        mList2.clear();
//////        mList.addAll(group_user_info);
//////        mList.addAll(group_user_info);
//////        mList.addAll(group_user_info);
//////        mList.addAll(group_user_info);
//////        int size = mList.size();
////        if (group_user_info.size() > 9) {
////            for (int i = 0; i <= 9; i++) {
////                mList2.add(i, group_user_info.get(i));
////            }
////        } else {
////            mList2.addAll(group_user_info);
////            mList2.add(group_user_info.get(0));
////        }
////        groupDataTvChatnum.setText("群成员(" + group_user_info.size() + ")");
////        GroupMemberAdapter groupMemberAdapter = new GroupMemberAdapter(this, mList2, false);
////        groupDataRecyc.setAdapter(groupMemberAdapter);
////        groupMemberAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
////            @Override
////            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
////                DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean item = (DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean) adapter.getItem(position);
////                if (position == (mList2.size() - 1)) {
//////                    点击跳转群成员列表
//////                    IntentUtils.JumpToHaveOne(GroupTeamActivity.class, GroupTeamActivity.GROUP_ID, groupId);
//////                    IntentUtils.JumpTo(GroupTeamActivity.class);
////                } else {
////                    IntentUtils.JumpToHaveOne(FriendDataAddActivity.class, "id", item.getUserId());
////                }
////            }
////        });
////        groupMemberAdapter.notifyDataSetChanged();
////    }
////
////    private void initUI(DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupInfoBean groupInfoBean) {
////        Glide.with(this).load(groupInfoBean.getGroupHeadImg())
////                .bitmapTransform(new CropCircleTransformation(GroupDataAddActivity.this))
////                .error(R.drawable.qun_head)
//////                .crossFade(1000)
////                .into(groupDataIvHead);
////        groupDataTvName.setText(groupInfoBean.getGroupName());
////    }
//
//    private void initQrCodeNotice(DataScanGroupRequest.RecordBean.GroupDetailInfoBean.GroupNoticeBean group_notice) {
//        if (!StrUtils.isEmpty(group_notice.getId()))
//            groupDataTvGonggao.setText("入群后即可查看");
//        else
//            groupDataTvGonggao.setText("暂无公告");
//    }
//
//    List<DataScanGroupRequest.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> mList3 = new ArrayList<>();
//
//    private void initQrCodeAdapter(List<DataScanGroupRequest.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> group_user_info) {
//        mList3.clear();
//        if (group_user_info.size() > 9) {
//            for (int i = 0; i <= 9; i++) {
//                mList3.add(i, group_user_info.get(i));
//            }
//        } else {
//            mList3.addAll(group_user_info);
//            mList3.add(group_user_info.get(0));
//        }
//        groupDataTvChatnum.setText("群成员(" + group_user_info.size() + ")");
//        GroupMemberQrCodeAdapter groupMemberAdapter = new GroupMemberQrCodeAdapter(this, mList3, false);
//        groupDataRecyc.setAdapter(groupMemberAdapter);
//        groupMemberAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                DataScanGroupRequest.RecordBean.GroupDetailInfoBean.GroupUserInfoBean item = (DataScanGroupRequest.RecordBean.GroupDetailInfoBean.GroupUserInfoBean) adapter.getItem(position);
//                if (position == (mList3.size() - 1)) {
////                    点击跳转群成员列表
//                    DialogUtils.showDialog("入群后即可查看全部群成员！");
////                    IntentUtils.JumpToHaveOne(GroupTeamActivity.class, GroupTeamActivity.GROUP_ID, groupId);
////                    IntentUtils.JumpTo(GroupTeamActivity.class);
//                } else {
//                    IntentUtils.JumpToHaveOne(FriendDataAddActivity.class, "id", item.getUserId());
//                }
//            }
//        });
//        groupMemberAdapter.notifyDataSetChanged();
//    }
//
//    private void initQrCodeUI(DataScanGroupRequest.RecordBean.GroupDetailInfoBean.GroupInfoBean groupInfoBean) {
//        Glide.with(this).load(groupInfoBean.getGroupHeadImg())
//                .bitmapTransform(new CropCircleTransformation(GroupDataAddActivity.this))
//                .error(R.drawable.qun_head)
////                .crossFade(1000)
//                .into(groupDataIvHead);
//        Log.e("qrCode", "-------------groupDataAdd------------------" + groupInfoBean.getGroupName());
//        groupDataTvName.setText(groupInfoBean.getGroupName());
//
//        dataSearch = new DataSearch();
//        dataSearch.setQrcode(groupInfoBean.getGroupQrcode());
//        dataSearch.setHeadImg(groupInfoBean.getGroupHeadImg());
//        dataSearch.setName(groupInfoBean.getGroupName());
//        dataSearch.setId(groupInfoBean.getId());
//        dataSearch.setSno(groupInfoBean.getGroupSno());
//    }
//
//    DataScanGroupRequest.RecordBean.GroupDetailInfoBean.GroupInfoBean dataRecord;
//    @Override
//    protected int getLayoutView() {
//        return R.layout.activity_group_data_add;
//    }
//
//    @OnClick({R.id.group_data_iv_head, R.id.group_data_lin_intogrouplist, R.id.group_data_tv_addgroup, R.id.group_data_lin_name})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.group_data_iv_head:
//                break;
//            case R.id.group_data_lin_intogrouplist:
//                DialogUtils.showDialog("您还不是本群成员，加入本群即可查看群信息！");
////                if (groupId!=null)
////                    IntentUtils.JumpToHaveOne(GroupTeamActivity.class,GroupTeamActivity.GROUP_ID,groupId);
////                IntentUtils.JumpTo(GroupTeamActivity.class);
//                break;
//            case R.id.group_data_tv_addgroup:
////                IntentUtils.JumpTo(GroupDataAddActivity.class);
//                IntentUtils.JumpToHaveObj(AddGoodGroupActivity.class, AppConfig.GROUP_ADDKEY, dataSearch);
////                sendWeb(SplitWeb.addGroupOf(groupSno,""));
//
//                break;
//            case R.id.group_data_lin_name:
//                if (NoDoubleClickUtils.isDoubleClick()) {
//                    if (dataRecord != null) {
//                        PersonData personData = new PersonData();
//                        personData.setHeadImg(dataRecord.getGroupHeadImg());
//                        personData.setName(dataRecord.getGroupName());
//                        personData.setScanTital("扫一扫二维码，加入群聊");
//                        personData.setTital("群聊二维码");
//                        personData.setQrCode(dataRecord.getGroupQrcode());
//                        IntentUtils.JumpToHaveObj(QunCodeActivity.class, AppConfig.GROUP_INFO, personData);
//                    }
//                }
//                break;
//        }
//    }
//
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        // TODO: add setContentView(...) invocation
////        ButterKnife.bind(this);
////    }
}
