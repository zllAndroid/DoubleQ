package com.doubleq.xm6leefunz.about_chat.chat_group.sub_group;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.doubleq.model.DataCreatGroupChat;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_chat.chat_group.sub_group.about_intent_data.IntentDataInvitation;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter.CreatGroupChatAdapter;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_custom.LetterBar;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

//位置：邀请好友进群
public class InvitationGroupChatActivity extends BaseActivity {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.inclu_tv_right)
    TextView includeTopTvRight;
    @BindView(R.id.include_top_lin_back)
    LinearLayout includeTopLin;
    @BindView(R.id.tv_abc)
    TextView mTvAbc;

    @BindView(R.id.group_team_letter)
    LetterBar mLetterBar;
    @BindView(R.id.creat_exlist_friend)
    ExpandableListView mExList;
    @BindView(R.id.invitation_lin_nodata)
    LinearLayout invitationLinNodata;
    @BindView(R.id.invitation_tv_nodata)
    TextView invitationTvNodata;
    @BindView(R.id.invitation_lin_list)
    LinearLayout invitationLinList;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int getLayoutView() {
        return R.layout.activity_invitation_group_chat;
    }

    String groupId;
    String groupType;

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));
        includeTopTvRight.setVisibility(View.VISIBLE);
        includeTopTvRight.setText("确定");
        Intent intent = getIntent();
        if (intent != null) {
            IntentDataInvitation dataInvitation = (IntentDataInvitation) intent.getSerializableExtra(AppConfig.GROUP_ID);
            String groupTital = dataInvitation.getGroupTital();
            includeTopTvTital.setText(groupTital);
            groupType = dataInvitation.getGroupType();
            groupId = dataInvitation.getGroupId();
            initABC2();
//           邀请
            if (AppConfig.GROUP_QUZHU.equals(groupType)) {
                sendWeb(SplitWeb.groupInvitationfFriend(groupId));
            } else {
//                删除
                sendWeb(SplitWeb.getGroupWebInfo());
            }
        }
    }

    List<String> ABCList = new ArrayList<>();

    public void initABC2() {
        ABCList.clear();
//        ABCList.add("");
//        ABCList.add("⇧");
        for (char i = 'A'; i <= 'Z'; i++) {
            ABCList.add(i + "");
        }
        runnable = new Runnable() {
            public void run() {
                mTvAbc.setVisibility(View.INVISIBLE);
            }
        };
        mLetterBar.setonTouchLetterListener(new LetterBar.onTouchLetterListener() {
            @Override
            public void onTouuchDown(String letter) {
                mTvAbc.removeCallbacks(runnable);
                mTvAbc.setVisibility(View.VISIBLE);
                mTvAbc.setText(letter);
                if (letter.equals("⇧")) {
                    mExList.setSelection(0);
//                    linearLayoutManager.scrollToPositionWithOffset(0, 0);
//                    mRecyclerView.scrollToPosition(0);
//                    mRecyclerView.setSelection(0);
                    return;
                }
                for (int i = 0; i < mFriendList.size(); i++) {
                    //获取所有城市的首字母
                    String firstLetter = getFirstABC
                            (mFriendList.get(i).getGroupName());
                    if (letter.equals(firstLetter)) {
                        mExList.setSelectedGroup(i);
                        break;
                    }
                }
            }

            @Override
            public void onTouchUp() {
                mTvAbc.postDelayed(runnable, 1000);
            }
        });
    }

    public String getFirstABC(String pinyin) {
        String upperCase = pinyin.substring(0, 1).toUpperCase();
        return upperCase;
    }

    //    大列表选中的数据
    List<String> mList = new ArrayList<>();
    List<DataCreatGroupChat.RecordBean.FriendListBean> mFriendList = new ArrayList<>();

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "groupInvitationfFriend":
            case "getGroupWebInfo":
                DataCreatGroupChat dataCreatGroupChat = JSON.parseObject(responseText, DataCreatGroupChat.class);
                DataCreatGroupChat.RecordBean record = dataCreatGroupChat.getRecord();
                if (record != null) {
                    List<DataCreatGroupChat.RecordBean.FriendListBean> friend_list = record.getFriendList();
                    if (friend_list.size() > 0) {
                        if (StrUtils.isEmpty(friend_list.get(0).getGroupName())) {
                            friend_list.remove(0);
                        }
                        mFriendList.addAll(friend_list);
                        if (mFriendList.size() == 0) {
                            invitationLinNodata.setVisibility(View.VISIBLE);
                            invitationTvNodata.setText("暂无新成员数据");
                            invitationLinList.setVisibility(View.GONE);
                        } else {
                            invitationLinNodata.setVisibility(View.GONE);
                            invitationLinList.setVisibility(View.VISIBLE);
                            initAdapter(friend_list);
                        }
                    }

                }
                break;
//                邀请入群
            case "groupInvitationf":
                DialogUtils.showDialogOne("邀请群成员成功", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
                        AppManager.getAppManager().finishActivity(InvitationGroupChatActivity.this);
                    }
                });

                break;
            case "delGroupMember":
                DialogUtils.showDialogOne("删除群成员成功", new DialogUtils.OnClickSureListener() {
                    @Override
                    public void onClickSure() {
                        AppManager.getAppManager().finishActivity(InvitationGroupChatActivity.this);
                    }
                });

                break;
        }
    }

    CreatGroupChatAdapter creatGroupChatAdapter = null;

    private void initAdapter(final List<DataCreatGroupChat.RecordBean.FriendListBean> friend_list) {
        creatGroupChatAdapter = new CreatGroupChatAdapter(this, friend_list);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mExList.setAdapter(creatGroupChatAdapter);
        mExList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                //设置点击不关闭（不收回）
                v.setClickable(true);
                return true;
            }
        });
        for (int i = 0; i < creatGroupChatAdapter.getGroupCount(); i++) {
            mExList.expandGroup(i);
        }
        creatGroupChatAdapter.setOnMyLinChangeListener(new CreatGroupChatAdapter.OnMyLinChangeListener() {
            @Override
            public void onCheckedChanged(String friendId, boolean isChecked) {
                if (isChecked) {
                    if (!mList.contains(friendId))
                        mList.add(friendId);
                } else {
                    if (mList.contains(friendId))
                        mList.remove(friendId);
                }
                List<String> checkString = creatGroupChatAdapter.getCheckString();


                Log.e("checkChat", "friendId=" + friendId + isChecked + "++++" + mList.toString() + "---" + checkString.toString());
            }
        });
//        mExList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
//                DataCreatGroupChat.RecordBean.FriendListBean.GroupListBean groupListBean = friend_list.get(groupPosition).getGroupList().get(childPosition);
////                String userId = mFriendList.get(groupPosition).getGroupList().get(childPosition).getUserId();
////                IntentUtils.JumpToHaveOne(FriendDataActivity.class,"id",userId);
//                Log.e("checkChat","groupListBean="+groupListBean.getNickName());
//
//                return false;
//            }
//        });
        creatGroupChatAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.seach_iv_close, R.id.inclu_tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//                点击确定
            case R.id.inclu_tv_right:
                List<String> checkString = creatGroupChatAdapter.getCheckString();
                if (checkString.size() > 0) {
//                    String check[]= new String[checkData.size()];
                    String checkChat = "";
                    for (int i = 0; i < checkString.size(); i++) {
                        if (i == 0) {
                            checkChat += checkString.get(i);
                        } else {
                            checkChat += "," + checkString.get(i);
                        }
                    }
                    if (groupType.equals(AppConfig.GROUP_QUZHU)) {
                        sendWebHaveDialog(SplitWeb.groupInvitationf(groupId, checkChat)
                                , "邀请中...", "邀请好友入群成功");
                    } else {
                        sendWebHaveDialog(SplitWeb.delGroupMember(groupId, checkChat)
                                , "删除中...", "删除群成员成功");
                    }
                } else {
                    ToastUtil.show("请选择成员");
                }
                break;
        }
    }

}
