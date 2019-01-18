package com.doubleq.xm6leefunz.about_chat.chat_group;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.doubleq.model.DataAddQunDetails;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_chat.chat_group.group_realm.CusDataGroupChat;
import com.doubleq.xm6leefunz.about_chat.chat_group.group_realm.RealmGroupChatHeaderHelper;
import com.doubleq.xm6leefunz.about_chat.chat_group.sub_group.AddGroupWayActivity;
import com.doubleq.xm6leefunz.about_chat.chat_group.sub_group.EditGroupCardActivity;
import com.doubleq.xm6leefunz.about_chat.chat_group.sub_group.GroupChatSetActivity;
import com.doubleq.xm6leefunz.about_chat.chat_group.sub_group.GroupNoticeActivity;
import com.doubleq.xm6leefunz.about_chat.chat_group.sub_group.GrouperEscActivity;
import com.doubleq.xm6leefunz.about_chat.chat_group.sub_group.InvitationGroupChatActivity;
import com.doubleq.xm6leefunz.about_chat.chat_group.sub_group.about_intent_data.IntentDataInvitation;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.about_utils.about_file.HeadFileUtils;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmHomeHelper;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.FriendDataActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.GroupTeamActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter.GroupMemberQunzhuAdapter;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_search.DataSearch;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_top_add.QunCodeActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity.ChangeInfoActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity.ChangeInfoWindow;
import com.example.zhouwei.library.CustomPopWindow;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.NoDoubleClickUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * 位置：群聊资料
 */
public class GroupChatDetailsActivity extends BaseActivity implements ChangeInfoWindow.OnAddContantClickListener {
    @BindView(R.id.include_top_lin_background)
    LinearLayout includeTopLinBackground;
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.inclu_tv_right)
    TextView incluTvRight;
    @BindView(R.id.include_top_iv_more)
    ImageView includeTopIvMore;
    @BindView(R.id.group_data_iv_head)
    ImageView groupDataIvHead;
    @BindView(R.id.group_data_tv_chatnum)
    TextView groupDataTvChatnum;
    @BindView(R.id.group_data_recyc)
    RecyclerView groupDataRecyc;
    @BindView(R.id.group_data_tv_gonggao)
    TextView groupDataTvGonggao;
    @BindView(R.id.group_details_lin_have_set)
    LinearLayout groupDetailsLinHaveSet;
    @BindView(R.id.group_details_lin_main)
    LinearLayout mLinMain;
    @BindView(R.id.group_data_tv_mine_name)
    TextView groupDataTvMineName;
    @BindView(R.id.group_data_lin_myGroupCard)
    LinearLayout groupDataLinMyGroupCard;
    @BindView(R.id.group_details_lin_group_notice)
    LinearLayout groupDetailsLinGroupNotice;
    @BindView(R.id.group_details_tv_group_id)
    TextView groupDetailsTvGroupId;
    @BindView(R.id.group_details_iv_edit)
    ImageView groupDetailsIvEdit;
    @BindView(R.id.group_details_lin_name)
    LinearLayout groupDetailsLinName;
    @BindView(R.id.group_details_tv_name)
    TextView groupDetailsTvName;

    private RealmGroupChatHeaderHelper realmGroupChatHeaderHelper;

    DataSearch dataSearch = null;
    static String groupId;
    static String groupName;

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("群聊资料");
        incluTvRight.setVisibility(View.GONE);
        includeTopIvMore.setVisibility(View.VISIBLE);
        includeTopLinBackground.setBackgroundColor(getResources().getColor(R.color.app_theme));

        groupDataRecyc.setHasFixedSize(true);
        groupDataRecyc.setNestedScrollingEnabled(false);
        groupDataRecyc.setLayoutManager(new GridLayoutManager(this, 5));

        realmGroupChatHeaderHelper = new RealmGroupChatHeaderHelper(this);

        Intent intent = getIntent();
        if (intent != null) {
            groupId = intent.getStringExtra(AppConfig.GROUP_ID);
            if (!StrUtils.isEmpty(groupId)){
//                IntentUtils.JumpToHaveOne(GroupTeamActivity.class,"groupId",groupId);
                sendWeb(SplitWeb.searchDetailInfo(groupId));
            }
        }
        initRightPop();

    }

  public  static   boolean isFirst = false;

    @Override
    protected void onResume() {
        super.onResume();
        if (isFirst) {
            if (!StrUtils.isEmpty(groupId))
                sendWeb(SplitWeb.searchDetailInfo(groupId));
        }
        isFirst = true;
    }

    View mView;

    private void initRightPop() {
        mView = LayoutInflater.from(this).inflate(R.layout.pop_group_details_esc, null);
        mView.findViewById(R.id.pop_btn_esc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtil.show("点击了屏蔽");
                if (popWindow != null)
                    popWindow.dissmiss();
                if (isGrouper) {
                    IntentUtils.JumpToHaveTwo(GrouperEscActivity.class, AppConfig.GROUPER_ESC, groupId, AppConfig.GROUPER_NAME, groupName);
                    AppManager.getAppManager().finishActivity(GroupChatDetailsActivity.this);
                } else {
                    DialogUtils.showDialog("是否确认退出群聊", new DialogUtils.OnClickSureListener() {
                        @Override
                        public void onClickSure() {
                            sendWebHaveDialog(SplitWeb.outGroupChat(groupId), "正在退出...", "退出成功");
                        }
                    });
                }
            }
        });
        mView.findViewById(R.id.pop_btn_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popWindow != null)
                    popWindow.dissmiss();
            }
        });
        mView.findViewById(R.id.pop_v_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popWindow != null)
                    popWindow.dissmiss();
            }
        });
    }

    boolean isGrouper = false;//    是否群主

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
                    if (group_detail_info != null) {
                        DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupInfoBean groupInfo = group_detail_info.getGroupInfo();
                        DataAddQunDetails.RecordBean.GroupDetailInfoBean.UserInfoBean userInfo = group_detail_info.getUserInfo();
                        if (userInfo != null) {
                            initUserInfo(userInfo);
                            String identityType = userInfo.getIdentityType();
                            isGrouper = identityType.equals("3") ? false : true;
                            Log.e("isGrouper","------------------------"+isGrouper);
                            if (isGrouper) {
                                groupDetailsIvEdit.setVisibility(View.VISIBLE);
                                groupDetailsLinName.setClickable(true);
                            } else {
                                groupDetailsIvEdit.setVisibility(View.GONE);
                                groupDetailsLinName.setClickable(false);
                            }
                            List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> group_user_info = group_detail_info.getGroupUserInfo();
                            if (group_user_info.size() > 0) {
                                initListHead(group_user_info);
                                initAdapter(group_user_info, isGrouper);
                            }
                        }

                        if (groupInfo != null) {
                            initUI(groupInfo);
                        }
                        DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupNoticeBean group_notice = group_detail_info.getGroupNotice();
                        if (group_notice != null) {
                            initNotice(group_notice);
                        }
                    }
                }
                break;
            case "outGroupChat":
                DialogUtils.showDialogOne("退出群聊成功", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
                        RealmHomeHelper realmHomeHelper = new RealmHomeHelper(GroupChatDetailsActivity.this);
                        realmHomeHelper.deleteRealmMsg(groupId);
                        Intent intent2 = new Intent();
                        intent2.putExtra("id", groupId);
                        intent2.setAction("del.refreshMsgFragment");
                        sendBroadcast(intent2);
                        AppManager.getAppManager().finishActivity(GroupChatDetailsActivity.this);
                        AppManager.getAppManager().finishActivity(ChatGroupActivity.class);
                        overridePendingTransition(0,0);
                    }
                });

                break;

            case "":

                break;
        }
    }

    //存储头像到本地
    private void initListHead(List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> group_user_info) {
        for (int i = 0; i < group_user_info.size(); i++) {
            final String headImg = group_user_info.get(i).getHeadImg();
            final String friendId = group_user_info.get(i).getUserId();
            final String modified = group_user_info.get(i).getModified();
//            group_user_info.get(i).get
            CusDataGroupChat cusDataGroupChat = realmGroupChatHeaderHelper.queryGroupChat(groupId);
            if (cusDataGroupChat != null) {
                String time = cusDataGroupChat.getTime();
                if (time != null)
                    if (!modified.equals(time)) {
                        if (!StrUtils.isEmpty(headImg))
                            Glide.with(this)
                                    .load(headImg)
                                    .downloadOnly(new SimpleTarget<File>() {
                                        @Override
                                        public void onResourceReady(final File resource, GlideAnimation<? super File> glideAnimation) {
//                                    这里拿到的resource就是下载好的文件，
                                            File file = HeadFileUtils.saveImgPath(resource, AppConfig.TYPE_GROUP_CHAT, friendId, modified);
                                            realmGroupChatHeaderHelper.updateHeadPath(friendId, file.toString(), headImg, modified);
                                        }
                                    });
                    }
            } else {
                if (!StrUtils.isEmpty(headImg))
                    Glide.with(this)
                            .load(headImg)
                            .downloadOnly(new SimpleTarget<File>() {
                                @Override
                                public void onResourceReady(final File resource, GlideAnimation<? super File> glideAnimation) {
//                                    这里拿到的resource就是下载好的文件，
                                    File file = HeadFileUtils.saveImgPath(resource, AppConfig.TYPE_GROUP_CHAT, friendId, modified);
                                    CusDataGroupChat linkFriend = new CusDataGroupChat();
                                    linkFriend.setHeadImg(headImg);
                                    linkFriend.setFriendId(friendId);
                                    linkFriend.setTime(modified);
                                    linkFriend.setImgPath(file.toString());
                                    realmGroupChatHeaderHelper.addRealmGroupChat(linkFriend);
                                }
                            });
            }
        }
    }

    //设置普通群成员与群主之间的界面差异
    private void initUserInfo(DataAddQunDetails.RecordBean.GroupDetailInfoBean.UserInfoBean userInfo) {

        groupDataTvMineName.setText(userInfo.getCarteName());
//        userInfo.getIdentityType()
//        群员身份   1：群主  2：管理员    3：普通群员
        switch (userInfo.getIdentityType()) {
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

    private void initAdapter(List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> group_user_info, final boolean isGrouper) {
//        mList.clear();
        mList2.clear();
        if (group_user_info.size() > 9) {
            for (int i = 0; i <= 9; i++) {
                mList2.add(i, group_user_info.get(i));
            }
        } else {
            mList2.addAll(group_user_info);
            mList2.add(group_user_info.get(0));
            if (isGrouper)
                mList2.add(group_user_info.get(0));
        }
        groupDataTvChatnum.setText("群成员(" + group_user_info.size() + ")");
        GroupMemberQunzhuAdapter groupMemberAdapter = new GroupMemberQunzhuAdapter(this, mList2, true, isGrouper);
        groupDataRecyc.setAdapter(groupMemberAdapter);
        groupMemberAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean item = (DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean) adapter.getItem(position);
                IntentDataInvitation intentDataInvitation = new IntentDataInvitation();
                intentDataInvitation.setGroupId(groupId);
                if (position == (mList2.size() - 1)) {
                    // 点击跳转群成员列表
                    if (groupId != null) {
//如果是群主，则最后一个是删除群成员，如果是群成员，最后一个是邀请
                        if (!isGrouper) {
                            intentDataInvitation.setGroupTital("邀请新成员");
                            intentDataInvitation.setGroupType(AppConfig.GROUP_QUZHU);
//                            邀请新成员
                            IntentUtils.JumpToHaveObj(InvitationGroupChatActivity.class, AppConfig.GROUP_ID, intentDataInvitation);
                        } else {
                            intentDataInvitation.setGroupTital("删除成员");
                            intentDataInvitation.setGroupType(AppConfig.GROUP_MEMBER);
//                            删除群成员
                            IntentUtils.JumpToHaveObj(InvitationGroupChatActivity.class, AppConfig.GROUP_ID, intentDataInvitation);
                        }
                    }
//                    如果是群主，倒数第二个是添加
                } else if ((position == (mList2.size() - 2)) && (isGrouper)) {
                    intentDataInvitation.setGroupTital("邀请新成员");
                    intentDataInvitation.setGroupType(AppConfig.GROUP_QUZHU);
//                            邀请新成员
                    IntentUtils.JumpToHaveObj(InvitationGroupChatActivity.class, AppConfig.GROUP_ID, intentDataInvitation);

                } else {
                    //好友关系 1是未添加 2是已添加
                    //群成员关系  1是未添加 2是已添加 3是自己
                    if (item != null) {
                        Log.e("Relation", "-----------------------" + item.getIsRelation());
                        switch (item.getIsRelation()) {
                            case "1":
                                //                            跳转陌生人显示界面
                                IntentUtils.JumpToHaveTwo(FriendDataGroupMemberActivity.class, FriendDataGroupMemberActivity.FRIENG_ID_KEY, item.getUserId(), FriendDataGroupMemberActivity.GROUP_ID_KEY, groupId);
                                break;
                            case "2":
                                //                            ImageView imageView = view.findViewById(R.id.item_iv_group_member_head);
                                //
                                //
                                //                            imageView.setDrawingCacheEnabled(true);
                                //                            Bitmap bitmap=imageView.getDrawingCache();
                                //                            imageView.setDrawingCacheEnabled(false);
                                //                            好友，跳转好友界面
                                IntentUtils.JumpToHaveOne(FriendDataActivity.class, "id", item.getUserId());
                                break;
                            case "3":
                                // 自己，跳转个人资料界面
                                IntentUtils.JumpTo(ChangeInfoActivity.class);
                                break;
                        }
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
                .into(groupDataIvHead);
        groupDetailsTvGroupId.setText("(" + groupInfoBean.getGroupSno() + ")");
        groupDetailsTvName.setText(groupInfoBean.getGroupName());
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_group_details;
    }

    CustomPopWindow popWindow;

    @OnClick({R.id.group_data_iv_head, R.id.group_data_lin_intogrouplist, R.id.include_top_iv_more, R.id.group_data_lin_myGroupCard,
            R.id.group_details_lin_group_notice})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.group_data_iv_head:
//                Intent intent_notice = new Intent(GroupChatDetailsActivity.this, GroupNoticeActivity.class);
                IntentUtils.JumpTo(GroupNoticeActivity.class);
                break;
            case R.id.group_data_lin_intogrouplist:
                if (groupId != null)
                    IntentUtils.JumpToHaveOne(GroupTeamActivity.class, GroupTeamActivity.GROUP_ID, groupId);
                break;
            case R.id.include_top_iv_more:
                if (popWindow == null)
                    popWindow = new CustomPopWindow.PopupWindowBuilder(GroupChatDetailsActivity.this)
                            .setView(mView)
                            .setFocusable(true)
                            .setOutsideTouchable(true)
                            .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                            .setAnimationStyle(0) // 添加自定义显示和消失动画
                            .create()
                            .showAsDropDown(mLinMain, 0, 0);
                else
                    popWindow.showAsDropDown(mLinMain, 0, 0);
                break;
            case R.id.group_data_lin_myGroupCard:
                if (NoDoubleClickUtils.isDoubleClick()) {
                    Intent intent = new Intent(GroupChatDetailsActivity.this, EditGroupCardActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("groupofId", groupId);
                    if (groupDataTvMineName.getText() != null)
                        bundle.putString("cardName", groupDataTvMineName.getText().toString());
                    intent.putExtras(bundle);
                    startActivityForResult(intent, AppConfig.EDIT_GROUP_CARD_REQUEST);
                }
                break;
            case R.id.group_details_lin_group_notice:
                if (NoDoubleClickUtils.isDoubleClick()) {
                    Intent intent_notice = new Intent(GroupChatDetailsActivity.this, GroupNoticeActivity.class);
                    Bundle bundle_notice = new Bundle();
                    bundle_notice.putString("groupId", groupId);
                    bundle_notice.putBoolean("isGrouper", isGrouper);
                    if (groupDataTvGonggao != null)
                        bundle_notice.putString("content", groupDataTvGonggao.getText().toString());
                    intent_notice.putExtras(bundle_notice);
                    startActivityForResult(intent_notice, AppConfig.EDIT_GROUP_NOTICE_REQUEST);
                }
                break;
        }
    }

    String result;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == AppConfig.EDIT_GROUP_CARD_RESULT) {
            if (requestCode == AppConfig.EDIT_GROUP_CARD_REQUEST) {
                result = data.getExtras().getString("myGroupCard");
                Log.e("myGroupCard", "-----------------------------" + result);
                groupDataTvMineName.setText(result);
            }
        }else if (requestCode == AppConfig.EDIT_GROUP_NOTICE_REQUEST)
        {
            if (resultCode == AppConfig.EDIT_GROUP_NOTICE_RESULT)
            {
                String msg = data.getExtras().getString(GroupNoticeActivity.GROUP_NOTICES);
                groupDataTvGonggao.setText(msg);
            }
        }

    }

    @OnClick({R.id.group_details_lin_set, R.id.group_details_lin_add_type, R.id.group_details_lin_group_notice, R.id.group_details_lin_chat_old, R.id.group_details_lin_del_chat,
            R.id.include_top_iv_zhuanfa, R.id.group_details_iv_qrcode, R.id.group_details_lin_name, R.id.group_data_iv_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.group_details_lin_set:
                IntentUtils.JumpToHaveOne(GroupChatSetActivity.class, "groupId", groupId);
                break;
            case R.id.group_details_lin_add_type:
                IntentUtils.JumpTo(AddGroupWayActivity.class);
                break;
            case R.id.group_details_lin_group_notice:

                break;
            case R.id.group_details_lin_chat_old:

                break;
            case R.id.group_details_lin_del_chat:

                break;
            case R.id.include_top_iv_zhuanfa:

                break;
            case R.id.group_details_iv_qrcode:
                IntentUtils.JumpTo(QunCodeActivity.class);
                break;
            case R.id.group_details_lin_name:
                doChangeName();
                break;
            case R.id.group_data_iv_head:

                break;
        }
    }

    // 修改群名
    private void doChangeName() {
        ChangeInfoWindow changeInfoWindow = new ChangeInfoWindow(GroupChatDetailsActivity.this, "修改群名", groupDetailsTvName.getText().toString().trim());
        changeInfoWindow.showAtLocation(mLinMain, Gravity.CENTER, 0, 0);
        changeInfoWindow.setOnAddpopClickListener(this);

    }

    String contant = null;
    //0 修改群名   1 修改群头像
    String isChangeName = "0";

    @Override
    public void onSure(String contant) {
        this.contant = contant;
        switch (isChangeName) {
            case "0":  //群名
                sendWeb(SplitWeb.upNickName(contant));
                break;
            case "1":  //群头像
                sendWeb(SplitWeb.upUserSno(contant));
                break;

        }
    }

    @Override
    public void onCancle() {

    }


}
