//package com.doubleq.xm6leefunz.main_code.ui.about_contacts;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.alibaba.fastjson.JSON;
//import com.bumptech.glide.Glide;
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.doubleq.model.DataAddQunDetails;
//import com.doubleq.xm6leefunz.R;
//import com.doubleq.xm6leefunz.about_base.AppConfig;
//import com.doubleq.xm6leefunz.about_base.BaseActivity;
//import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
//import com.doubleq.xm6leefunz.about_utils.HelpUtils;
//import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_add.AddGoodGroupActivity;
//import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter.GroupMemberAdapter;
//import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_search.DataSearch;
//import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_top_add.QunCodeActivity;
//import com.doubleq.xm6leefunz.about_utils.IntentUtils;
//import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//import jp.wasabeef.glide.transformations.CropCircleTransformation;
//
///**
// * 位置：好友资料界面  (添加)v
// * 暂时无用
// */
//public class GroupDetailsActivity extends BaseActivity {
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
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//    DataSearch dataSearch = null;
//
//    @Override
//    protected void initBaseView() {
//        super.initBaseView();
//        includeTopTvTital.setText("群聊资料");
//        incluTvRight.setVisibility(View.GONE);
//        includeTopIvMore.setVisibility(View.GONE);
//        groupDataRecyc.setHasFixedSize(true);
//        groupDataRecyc.setNestedScrollingEnabled(false);
//        groupDataRecyc.setLayoutManager(new GridLayoutManager(this, 5));
//        Intent intent = getIntent();
//        if (intent!=null)
//        {
//            String id = intent.getStringExtra(AppConfig.GROUP_ID);
//            if (!StrUtils.isEmpty(id))
//                sendWeb(SplitWeb.searchDetailInfo(id));
//        }
//    }
//    @Override
//    public void receiveResultMsg(String responseText) {
//        super.receiveResultMsg(responseText);
//        String s = HelpUtils.backMethod(responseText);
//        switch (s)
//        {
//            case "searchDetailInfo":
//                DataAddQunDetails dataAddQunDetails = JSON.parseObject(responseText, DataAddQunDetails.class);
//                DataAddQunDetails.RecordBean record = dataAddQunDetails.getRecord();
//                if (record!=null)
//                {
//                    DataAddQunDetails.RecordBean.GroupDetailInfoBean group_detail_info = record.getGroupDetailInfo();
//                    if (group_detail_info!=null)
//                    {
//                        List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupInfoBean> group_info = group_detail_info.getGroupInfo();
//                        if (group_info.size()>0)
//                        {
//                            DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupInfoBean groupInfoBean = group_info.get(0);
//                            initUI(groupInfoBean);
//                        }
//                        List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> group_user_info = group_detail_info.getGroupUserInfo();
//                        if (group_user_info.size()>0)
//                        {
//                            initAdapter(group_user_info);
//                        }
//                        DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupNoticeBean group_notice = group_detail_info.getGroupNotice();
//                        if (group_notice!=null)
//                        {
//                            initNotice(group_notice);
//                        }
//                    }
//                }
//                break;
//        }
//    }
//
//    private void initNotice( DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupNoticeBean group_notice ) {
//        if (!StrUtils.isEmpty(group_notice.getNoticeContent()))
//            groupDataTvGonggao.setText(group_notice.getNoticeContent());
//    }
//
//    List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> mList =new ArrayList<>();
//    List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> mList2 =new ArrayList<>();
//    private void initAdapter( List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> group_user_info) {
////        mList.clear();
//        mList2.clear();
////        mList.addAll(group_user_info);
////        mList.addAll(group_user_info);
////        mList.addAll(group_user_info);
////        mList.addAll(group_user_info);
////        int size = mList.size();
//        if (group_user_info.size()>9)
//        {
//            for (int i=0;i<=9;i++)
//            {
//                mList2.add(i,group_user_info.get(i));
//            }
//        }else
//        {
//            mList2.addAll(group_user_info);
//            mList2.add(group_user_info.get(0));
//        }
//        groupDataTvChatnum.setText("群成员("+group_user_info.size()+")");
//        GroupMemberAdapter groupMemberAdapter = new GroupMemberAdapter(this, mList2, false);
//        groupDataRecyc.setAdapter(groupMemberAdapter);
//        groupMemberAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                if (position==(mList2.size()-1))
//                {
////                    点击跳转群成员列表
//                    IntentUtils.JumpTo(GroupTeamActivity.class);
//                }else {
//                    IntentUtils.JumpTo(FriendDataAddActivity.class);
//                }
//            }
//        });
//        groupMemberAdapter.notifyDataSetChanged();
//    }
//    private void initUI(DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupInfoBean groupInfoBean) {
//        Glide.with(this).load(groupInfoBean.getGroupHeadImg())
//                .bitmapTransform(new CropCircleTransformation(GroupDetailsActivity.this))
//                .error(R.drawable.qun_head)
//                .crossFade(1000).into(groupDataIvHead);
//        groupDataTvName.setText(groupInfoBean.getGroupName());
//    }
//    @Override
//    protected int getLayoutView() {
//        return R.layout.activity_group_data_add;
//    }
//    @OnClick({R.id.group_data_iv_head, R.id.group_data_lin_intogrouplist, R.id.group_data_tv_addgroup,R.id.group_data_tv_name})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.group_data_iv_head:
//                break;
//            case R.id.group_data_lin_intogrouplist:
//                IntentUtils.JumpTo(GroupTeamActivity.class);
//                break;
//            case R.id.group_data_tv_addgroup:
//                IntentUtils.JumpTo(AddGoodGroupActivity.class);
//                break;
//            case R.id.group_data_tv_name:
//                IntentUtils.JumpTo(QunCodeActivity.class);
//                break;
//        }
//    }
//}
