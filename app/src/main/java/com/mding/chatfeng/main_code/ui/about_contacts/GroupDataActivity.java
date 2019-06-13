//package com.mding.chatfeng.main_code.ui.about_contacts;
//
//import android.content.Intent;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.alibaba.fastjson.JSON;
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.mding.chatfeng.R;
//import com.mding.chatfeng.about_base.AppConfig;
//import com.mding.chatfeng.about_base.BaseActivity;
//import com.mding.chatfeng.about_base.web_base.SplitWeb;
//import com.mding.chatfeng.about_chat.chat_group.ChatGroupActivity;
//import com.mding.chatfeng.about_chat.chat_group.FriendDataGroupMemberActivity;
//import com.mding.chatfeng.about_utils.HelpUtils;
//import com.mding.chatfeng.about_utils.ImageUtils;
//import com.mding.chatfeng.about_utils.IntentUtils;
//import com.mding.chatfeng.main_code.ui.about_contacts.about_contacts_adapter.GroupMemberAdapter;
//import com.mding.chatfeng.main_code.ui.about_contacts.about_group_team.GroupTeamActivity;
//import com.mding.chatfeng.main_code.ui.about_contacts.about_search.DataSearch;
//import com.mding.chatfeng.main_code.ui.about_contacts.about_top_add.QunCodeActivity;
//import com.mding.chatfeng.main_code.ui.about_personal.about_activity.ChangeInfoActivity;
//import com.mding.model.DataAddQunDetails;
//import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
//import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//
//public class GroupDataActivity extends BaseActivity {
//    @BindView(R.id.include_top_tv_tital)
//    TextView includeTopTvTital;
//    @BindView(R.id.include_top_lin_background)
//    LinearLayout includeTopLinBackground;
//    @BindView(R.id.group_data_iv_head)
//    ImageView groupDataIvHead;
//    @BindView(R.id.group_data_tv_name)
//    TextView groupDataTvName;
//    @BindView(R.id.group_data_lin_intogrouplist)
//    LinearLayout groupDataLinIntogrouplist;
//    @BindView(R.id.group_data_tv_gonggao)
//    TextView groupDataTvGonggao;
//    @BindView(R.id.group_data_tv_chatnum)
//    TextView groupDataTvChatnum;
//    @BindView(R.id.group_data_recyc)
//    RecyclerView groupDataRecyc;
//
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////    }
//
////    String groupSno;
//    DataSearch dataSearch = null;
//    @Override
//    protected int getLayoutView() {
//        return R.layout.activity_group_data;
//    }
//
//    @Override
//    protected void initBaseView() {
//        super.initBaseView();
//        includeTopTvTital.setText("群聊资料");
//        includeTopLinBackground.setBackgroundColor(getResources().getColor(R.color.app_theme));
//
//        groupDataRecyc.setHasFixedSize(true);
//        groupDataRecyc.setNestedScrollingEnabled(false);
//        groupDataRecyc.setLayoutManager(new GridLayoutManager(this, 5));
//
//        Intent intent = getIntent();
//        if (intent != null) {
////            groupSno = intent.getStringExtra(AppConfig.GROUP_SNO);
//            groupId = intent.getStringExtra(AppConfig.GROUP_ID);
////            Log.e("qrCode","--------------groupData-------initBaseView---------groupId="+groupId);
////            sendWeb(SplitWeb.getSplitWeb().addGroupOfQrCode(groupSno));
//            sendWeb(SplitWeb.getSplitWeb().searchDetailInfo(groupId));
//        }
//    }
//
//    String groupId;
//    @Override
//    public void receiveResultMsg(String responseText) {
//        super.receiveResultMsg(responseText);
//        String method = HelpUtils.backMethod(responseText);
//        switch (method) {
////            case "addGroupOfQrCode":
////                DataScanGroupRequest dataScanGroupRequest = JSON.parseObject(responseText, DataScanGroupRequest.class);
////                DataScanGroupRequest.RecordBean recordBean = dataScanGroupRequest.getRecord();
////                if (recordBean != null) {
////                    DataScanGroupRequest.RecordBean.GroupDetailInfoBean groupInfo = recordBean.getGroupDetailInfo();
////                    if (groupInfo != null) {
////                        DataScanGroupRequest.RecordBean.GroupDetailInfoBean.GroupInfoBean group_info = groupInfo.getGroupInfo();
////                        if (group_info != null) {
////                            groupId = group_info.getId();
////                            initQrCodeUI(group_info);
////                        }
////                        List<DataScanGroupRequest.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> group_user_info = groupInfo.getGroupUserInfo();
////                        if (group_user_info.size() > 0) {
////                            initQrCodeAdapter(group_user_info);
////                        }
////                        DataScanGroupRequest.RecordBean.GroupDetailInfoBean.GroupNoticeBean group_notice = groupInfo.getGroupNotice();
////                        if (group_notice != null) {
////                            initQrCodeNotice(group_notice);
////                        }
////                    }
////                }
////                break;
//            case "searchDetailInfo":
//                DataAddQunDetails dataAddQunDetails = JSON.parseObject(responseText, DataAddQunDetails.class);
//                DataAddQunDetails.RecordBean record = dataAddQunDetails.getRecord();
//                if (record != null) {
//                    DataAddQunDetails.RecordBean.GroupDetailInfoBean groupInfo = record.getGroupDetailInfo();
//                    if (groupInfo != null) {
//                        DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupInfoBean group_info = groupInfo.getGroupInfo();
//                        if (group_info != null) {
//                            dataRecord = group_info;
//                            initUI(group_info);
//                        }
//                        List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> group_user_info = groupInfo.getGroupUserInfo();
//                        if (group_user_info.size() > 0) {
//                            initAdapter(group_user_info);
//                        }
//                        DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupNoticeBean group_notice = groupInfo.getGroupNotice();
//                        if (group_notice != null) {
//                            initNotice(group_notice);
//                        }
//                    }
//                }
//                break;
//        }
//    }
//
//    private void initNotice(DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupNoticeBean group_notice) {
//        if (!StrUtils.isEmpty(group_notice.getNoticeContent()))
//            groupDataTvGonggao.setText(group_notice.getNoticeContent());
//    }
//
//    List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> mList2 = new ArrayList<>();
//    private void initAdapter(List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> group_user_info) {
////        mList.clear();
//        mList2.clear();
//        if (group_user_info.size() > 9) {
//            for (int i = 0; i <= 9; i++) {
//                mList2.add(i, group_user_info.get(i));
//            }
//        } else {
//            mList2.addAll(group_user_info);
//            mList2.add(group_user_info.get(0));
//        }
//        groupDataTvChatnum.setText("群成员(" + group_user_info.size() + ")");
//        GroupMemberAdapter groupMemberAdapter = new GroupMemberAdapter(this, mList2, false);
//        groupDataRecyc.setAdapter(groupMemberAdapter);
//        groupMemberAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean item = (DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean) adapter.getItem(position);
//                if (position == (mList2.size() - 1)) {
////                    点击跳转群成员列表
//                    if (groupId != null){
//                        IntentUtils.JumpToHaveOne(GroupTeamActivity.class, GroupTeamActivity.GROUP_ID, groupId);
//                    }
//                }
//                else {
//                    //好友关系 1是未添加 2是已添加
//                    //群成员关系  1是未添加 2是已添加 3是自己
//                    if (item != null) {
//                        Log.e("Relation", "---------------groupChatDetails----------------------item.getIsRelation()=" + item.getIsRelation());
//                        switch (item.getIsRelation()) {
//                            case "1":
//                                //                            跳转陌生人显示界面
//                                IntentUtils.JumpToHaveTwo(FriendDataGroupMemberActivity.class, FriendDataGroupMemberActivity.FRIENG_ID_KEY, item.getUserId(), FriendDataGroupMemberActivity.GROUP_ID_KEY, groupId);
//                                break;
//                            case "2":
//                                //                            好友，跳转好友界面
//                                IntentUtils.JumpToHaveOne(FriendDataActivity.class, "id", item.getUserId());
//                                break;
//                            case "3":
//                                // 自己，跳转个人资料界面
//                                IntentUtils.JumpTo(ChangeInfoActivity.class);
//                                break;
//                        }
//                    }
////                    IntentUtils.JumpToHaveOne(FriendDataAddActivity.class, "id", item.getUserId());
//                }
//            }
//        });
//        groupMemberAdapter.notifyDataSetChanged();
//    }
//
//    private void initUI(DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupInfoBean groupInfoBean) {
//        String groupHeadImg = groupInfoBean.getGroupHeadImg();
////        ImageUtils.useBase64WithError(GroupDataActivity.this, groupDataIvHead, groupHeadImg.substring(0, groupHeadImg.indexOf("_")), R.drawable.qun_head);
//        ImageUtils.useBase64WithError(GroupDataActivity.this, groupDataIvHead, groupInfoBean.getGroupHeadImg(), R.drawable.qun_head);
////        Glide.with(this).load(groupInfoBean.getGroupHeadImg())
////                .bitmapTransform(new CropCircleTransformation(GroupDataActivity.this))
////                .error(R.drawable.qun_head)
//////                .crossFade(1000)
////                .into(groupDataIvHead);
//        groupDataTvName.setText(groupInfoBean.getGroupName());
//
//        dataSearch = new DataSearch();
//        dataSearch.setName(groupInfoBean.getGroupName());
//        dataSearch.setId(groupInfoBean.getId());
//
////        dataRecord.setGroupHeadImg(groupInfoBean.getGroupHeadImg());
////        dataRecord.setGroupName(groupInfoBean.getGroupName());
//    }
//
//
////    private void initQrCodeNotice(DataScanGroupRequest.RecordBean.GroupDetailInfoBean.GroupNoticeBean group_notice) {
////        if (!StrUtils.isEmpty(group_notice.getId()))
////            groupDataTvGonggao.setText("入群后即可查看");
////        else
////            groupDataTvGonggao.setText("暂无公告");
////    }
////
////    List<DataScanGroupRequest.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> mList3 = new ArrayList<>();
////
////    @SuppressLint("SetTextI18n")
////    private void initQrCodeAdapter(List<DataScanGroupRequest.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> group_user_info) {
////        mList3.clear();
////        if (group_user_info.size() > 9) {
////            for (int i = 0; i <= 9; i++) {
////                mList3.add(i, group_user_info.get(i));
////            }
////        } else {
////            mList3.addAll(group_user_info);
////            mList3.add(group_user_info.get(0));
////        }
////        groupDataTvChatnum.setText("群成员(" + group_user_info.size() + ")");
////        GroupMemberQrCodeAdapter groupMemberAdapter = new GroupMemberQrCodeAdapter(this, mList3, false);
////        groupDataRecyc.setAdapter(groupMemberAdapter);
////        groupMemberAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
////            @Override
////            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
////                DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean item = (DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean) adapter.getItem(position);
////                if (position == (mList3.size() - 1)) {
//////                    点击跳转群成员列表
////                    if (groupId != null){
////                        Log.e("qrCode","-------groupData------------groupId="+groupId);
////                        IntentUtils.JumpToHaveOne(GroupTeamActivity.class, GroupTeamActivity.GROUP_ID, groupId);
////                    }
//////                    IntentUtils.JumpTo(GroupTeamActivity.class);
////                } else {
////                    if (item != null){
////                        Log.e("qrCode","---------groupData----------item.getUserId()="+item.getUserId());
////                        IntentUtils.JumpToHaveOne(FriendDataAddActivity.class, "id", item.getUserId());
////                    }
////                }
////            }
////        });
////        groupMemberAdapter.notifyDataSetChanged();
////    }
////
////    private void initQrCodeUI(DataScanGroupRequest.RecordBean.GroupDetailInfoBean.GroupInfoBean groupInfoBean) {
////        Glide.with(this).load(groupInfoBean.getGroupHeadImg())
////                .bitmapTransform(new CropCircleTransformation(GroupDataActivity.this))
////                .error(R.drawable.qun_head)
//////                .crossFade(1000)
////                .into(groupDataIvHead);
////        Log.e("qrCode", "-------------groupData------------------" + groupInfoBean.getGroupName());
////        groupDataTvName.setText(groupInfoBean.getGroupName());
////
////        Log.e("qrCode","---------------groupData------------------groupInfoBean.getGroupName()="+groupInfoBean.getGroupName());
////        Log.e("qrCode","---------------groupData------------------groupInfoBean.getId()="+groupInfoBean.getId());
////        dataSearch.setName(groupInfoBean.getGroupName());
////        dataSearch.setId(groupInfoBean.getId());
////    }
//
//    DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupInfoBean dataRecord;
//    @OnClick(R.id.group_data_lin_name)
//    public void onViewClicked() {
//        if (NoDoubleClickUtils.isDoubleClick()) {
//            //  跳转到群二维码界面
//            if (dataRecord != null) {
//                PersonData personData = new PersonData();
//                personData.setHeadImg(dataRecord.getGroupHeadImg());
//                personData.setName(dataRecord.getGroupName());
//                personData.setScanTital(getResources().getString(R.string.qrcode_add_group));
//                personData.setTital(getResources().getString(R.string.qrcode_title_group));
//                personData.setQrCode(dataRecord.getGroupQrcode());
//                IntentUtils.JumpToHaveObj(QunCodeActivity.class, AppConfig.GROUP_INFO, personData);
//            }
//        }
//    }
//
//    @OnClick({R.id.group_data_tv_gonggao, R.id.group_data_tv_send_msg,R.id.group_data_lin_intogrouplist})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.group_data_tv_gonggao:
////                IntentUtils.JumpToHaveOne(GroupNoticeActivity.class,"groupId",groupId);
//                break;
//            case R.id.group_data_tv_send_msg:
//                //  跳转到群聊界面
//                IntentUtils.JumpToHaveObj(ChatGroupActivity.class, "dataSearch", dataSearch);
//                break;
//            case R.id.group_data_lin_intogrouplist:
//                if (groupId != null){
//                    IntentUtils.JumpToHaveOne(GroupTeamActivity.class, GroupTeamActivity.GROUP_ID, groupId);
//                }
//                break;
//        }
//    }
//
//}
