package com.doubleq.xm6leefunz.about_chat.chat_group;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.doubleq.model.DataAddQunDetails;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_chat.chat_group.sub_group.GroupChatSetActivity;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.FriendDataActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.GroupTeamActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter.GroupMemberAdapter;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_search.DataSearch;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_top_add.QunCodeActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity.ChangeInfoActivity;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * 位置：群聊详情
 */
public class GroupChatDetailsActivity extends BaseActivity {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.inclu_tv_right)
    TextView incluTvRight;
    @BindView(R.id.include_top_iv_more)
    ImageView includeTopIvMore;
    @BindView(R.id.group_data_iv_head)
    ImageView groupDataIvHead;
    @BindView(R.id.group_data_tv_name)
    TextView groupDataTvName;
    @BindView(R.id.group_data_tv_chatnum)
    TextView groupDataTvChatnum;
    @BindView(R.id.group_data_recyc)
    RecyclerView groupDataRecyc;
    @BindView(R.id.group_data_tv_gonggao)
    TextView groupDataTvGonggao;
    @BindView(R.id.group_details_lin_have_set)
    LinearLayout groupDetailsLinHaveSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    DataSearch dataSearch = null;
    static String groupId;

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("群聊资料");
        incluTvRight.setVisibility(View.GONE);
        includeTopIvMore.setVisibility(View.VISIBLE);
        groupDataRecyc.setHasFixedSize(true);
        groupDataRecyc.setNestedScrollingEnabled(false);
        groupDataRecyc.setLayoutManager(new GridLayoutManager(this, 5));
        Intent intent = getIntent();
        if (intent != null) {
            groupId = intent.getStringExtra(AppConfig.GROUP_ID);
            if (!StrUtils.isEmpty(groupId))
                sendWeb(SplitWeb.searchDetailInfo(groupId));
        }
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String s = HelpUtils.backMethod(responseText);
        switch (s) {
            case "searchDetailInfo":
                DataAddQunDetails dataAddQunDetails = JSON.parseObject(responseText, DataAddQunDetails.class);
                DataAddQunDetails.RecordBean record = dataAddQunDetails.getRecord();
                if (record != null) {
                    DataAddQunDetails.RecordBean.GroupDetailInfoBean group_detail_info = record.getGroupDetailInfo();


                    DataAddQunDetails.RecordBean.GroupDetailInfoBean.UserInfoBean userInfo = group_detail_info.getUserInfo();
                    if (userInfo!=null)
                    {
                        initUserInfo(userInfo);
                    }


                    if (group_detail_info != null) {
//                        List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupInfoBean> group_info = group_detail_info.getGroupInfo();
                        DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupInfoBean groupInfo = group_detail_info.getGroupInfo();
                        if (groupInfo!=null) {
                            initUI(groupInfo);
                        }
                        List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> group_user_info = group_detail_info.getGroupUserInfo();
                        if (group_user_info.size() > 0) {
                            initAdapter(group_user_info);
                        }
                        DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupNoticeBean group_notice = group_detail_info.getGroupNotice();
                        if (group_notice != null) {
                            initNotice(group_notice);
                        }


                    }
                }
                break;
        }
    }

    private void initUserInfo(DataAddQunDetails.RecordBean.GroupDetailInfoBean.UserInfoBean userInfo) {
//        userInfo.getIdentityType()
//        群员身份   1：群主  2：管理员    3：普通群员
        switch (userInfo.getIdentityType())
        {
            case "1":
                groupDetailsLinHaveSet.setVisibility(View.VISIBLE);
                break;
            case "2":
                groupDetailsLinHaveSet.setVisibility(View.VISIBLE);
                break;
            case "3":
                groupDetailsLinHaveSet.setVisibility(View.GONE);
                break;
        }


    }
    private void initNotice(DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupNoticeBean group_notice) {
        if (!StrUtils.isEmpty(group_notice.getNoticeContent()))
            groupDataTvGonggao.setText(group_notice.getNoticeContent());
    }

    List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> mList = new ArrayList<>();
    List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> mList2 = new ArrayList<>();

    private void initAdapter(List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> group_user_info) {
//        mList.clear();
        mList2.clear();
//        mList.addAll(group_user_info);
//        mList.addAll(group_user_info);
//        mList.addAll(group_user_info);
//        mList.addAll(group_user_info);
//        int size = mList.size();
        if (group_user_info.size() > 9) {
            for (int i = 0; i <= 9; i++) {
                mList2.add(i, group_user_info.get(i));
            }
        } else {
            mList2.addAll(group_user_info);
            mList2.add(group_user_info.get(0));
        }
        groupDataTvChatnum.setText("群成员(" + group_user_info.size() + ")");
        GroupMemberAdapter groupMemberAdapter = new GroupMemberAdapter(this, mList2, false);
        groupDataRecyc.setAdapter(groupMemberAdapter);
        groupMemberAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean item = (DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean) adapter.getItem(position);

                if (position == (mList2.size() - 1)) {
//                    点击跳转群成员列表
                    if (groupId != null) {
                        IntentUtils.JumpToHaveOne(GroupTeamActivity.class, GroupTeamActivity.GROUP_ID, groupId);
                    }
                } else {
                    //                        好友关系 1是未添加 2是已添加
//群成员关系  1是未添加 2是已添加 3是自己
                    switch (item.getIsRelation()) {
                        case "1":
                            IntentUtils.JumpToHaveTwo(FriendDataGroupMemberActivity.class, FriendDataGroupMemberActivity.FRIENG_ID_KEY, item.getUserId(), FriendDataGroupMemberActivity.GROUP_ID_KEY, groupId);
                            break;
                        case "2":
                            IntentUtils.JumpToHaveOne(FriendDataActivity.class, "id", item.getUserId());
                            break;
                        case "3":
                            IntentUtils.JumpTo(ChangeInfoActivity.class);
//                            ToastUtil.show("这是你自己啊");
                            //        自己
                            break;
                    }
                }
            }
        });
        groupMemberAdapter.notifyDataSetChanged();
    }

    private void initUI(DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupInfoBean groupInfoBean) {
        Glide.with(this).load(groupInfoBean.getGroupHeadImg())
                .bitmapTransform(new CropCircleTransformation(GroupChatDetailsActivity.this))
                .error(R.drawable.qun_head)
                .crossFade(1000).into(groupDataIvHead);
        groupDataTvName.setText(groupInfoBean.getGroupName());
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_group_details;
    }

    @OnClick({R.id.group_data_iv_head, R.id.group_data_lin_intogrouplist, R.id.group_data_tv_name})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.group_data_iv_head:
                break;
            case R.id.group_data_lin_intogrouplist:
                if (groupId != null)
                    IntentUtils.JumpToHaveOne(GroupTeamActivity.class, GroupTeamActivity.GROUP_ID, groupId);
                break;
            case R.id.group_data_tv_name:
                IntentUtils.JumpTo(QunCodeActivity.class);
                break;
        }
    }

    @OnClick({R.id.group_details_lin_set, R.id.group_details_lin_add_type, R.id.group_details_lin_group_notice, R.id.group_details_lin_chat_old, R.id.group_details_lin_del_chat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.group_details_lin_set:
                IntentUtils.JumpToHaveOne(GroupChatSetActivity.class,"id",groupId);
                break;
            case R.id.group_details_lin_add_type:
                break;
//            case R.id.group_details_lin_have_set:
//                break;
            case R.id.group_details_lin_group_notice:
                break;
            case R.id.group_details_lin_chat_old:
                break;
            case R.id.group_details_lin_del_chat:
                break;
        }
    }
}
