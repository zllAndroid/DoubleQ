package com.mding.chatfeng.main_code.ui.about_contacts.about_group_team;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_application.BaseApplication;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_chat.ui.PopupList;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.main_code.mains.MainActivity;
import com.mding.chatfeng.main_code.ui.about_contacts.FriendDataMixActivity;
import com.mding.chatfeng.main_code.ui.about_contacts.about_contacts_adapter.GroupTeamMemberAdapter;
import com.mding.chatfeng.main_code.ui.about_contacts.about_custom.LetterBar;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.CusDataFriendUser;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.RealmFriendUserHelper;
import com.mding.chatfeng.main_code.ui.about_personal.about_activity.ChangeInfoActivity;
import com.mding.model.DataGroupMember;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

//位置：群聊成员
public class GroupTeamActivity extends BaseActivity {
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.inclu_tv_right)
    TextView includeTopTvRight;
    @BindView(R.id.include_top_lin_back)
    LinearLayout includeTopLin;
    @BindView(R.id.tv_abc)
    TextView mTvAbc;
    @BindView(R.id.group_team_expan)
    ExpandableListView mExpanList;
    @BindView(R.id.group_team_letter)
    LetterBar mLetterBar;
    ACache mACache = null;
    private Runnable runnable;
    String MEMBER_LIST = "member_list";
    String memberListString;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_group_team;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("群聊成员");
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));
        initGroup();
        initIntent();
    }
    public  static  String GROUP_ID="groupId";
    String groupId;
    private void initIntent() {
        Intent intent = getIntent();
        if (intent!=null) {
            groupId = intent.getStringExtra(GROUP_ID);
            if (groupId != null){
                memberListString = BaseApplication.getaCache().getAsString(groupId + SplitWeb.getSplitWeb().USER_ID+MEMBER_LIST);
                if (StrUtils.isEmpty(memberListString)){
                    sendWeb(SplitWeb.getSplitWeb().getGroupMemberList(groupId));
                }else {
                    dealMemberList(memberListString);
                    sendWeb(SplitWeb.getSplitWeb().getGroupMemberList(groupId,verificationMD5));
                }
            }
        }
    }
    private void initGroup() {
//        initUI();
        initABC2();

    }

    private ArrayList<DataGroupMember.RecordBean.MemberListBean> allCusList = new ArrayList<>();


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
                try {
                    if (mTvAbc!=null)
                        mTvAbc.setVisibility(View.INVISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        mLetterBar.setonTouchLetterListener(new LetterBar.onTouchLetterListener() {
            @Override
            public void onTouchDown(String letter) {
                mTvAbc.removeCallbacks(runnable);
                mTvAbc.setVisibility(View.VISIBLE);
                mTvAbc.setText(letter);
                if (letter.equals("⇧"))
                {
                    mExpanList.setSelection(0);
                    return;
                }
                for (int i = 0; i < allCusList.size(); i++) {
//                for (int i = 1; i < allCusList.size(); i++) {
                    //获取所有城市的首字母
                    String firstLetter = getFirstABC
                            (allCusList.get(i).getGroupName());
//                        String firstLetter = getFirstABC
//                                (allCusList.get(i).getGroupName());
                    if (firstLetter.equals("~"))
                    {
                        firstLetter="#";
                    }
                    if (letter.equals(firstLetter)) {
                        mExpanList.setSelectedGroup(i);
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

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "getGroupMemberList":
                BaseApplication.getaCache().put(groupId + SplitWeb.getSplitWeb().USER_ID+MEMBER_LIST, responseText);

                dealMemberList(responseText);
                break;
        }
    }
    String verificationMD5;
    private void dealMemberList(String responseText) {
        DataGroupMember dataGroupMember = JSON.parseObject(responseText, DataGroupMember.class);
        DataGroupMember.RecordBean record = dataGroupMember.getRecord();
        List<DataGroupMember.RecordBean.MemberListBean> memberList = record.getMemberList();
        allCusList.clear();
        allCusList.addAll(memberList);
        verificationMD5 = record.getVerificationMD5();
        dealMemberRealm();
//                String groupName = record.getMemberList().get(0).getGroupName();
//                List<DataGroupMember.RecordBean.MemberListBean.GroupListBean> groupList = record.getMemberList().get(0).getGroupList();
//                initABCByGroup();
        initGroupTeamAdapter();
        BaseApplication.getaCache().put(groupId + SplitWeb.getSplitWeb().USER_ID+MEMBER_LIST, responseText);
    }

    private void dealMemberRealm() {
        for (int i=0;i<allCusList.size();i++)
        {
            List<DataGroupMember.RecordBean.MemberListBean.GroupListBean> groupList = allCusList.get(i).getGroupList();
            for (int j=0;j<groupList.size();j++)
            {
                String memberId = groupList.get(j).getMemberId();
                String imgPath = getFriendUserHelper().queryLinkFriendReturnImgPath(memberId);
                if (StrUtils.isEmpty(imgPath))
                {
                    CusDataFriendUser cusDataFriendUser = new CusDataFriendUser();
                    cusDataFriendUser.setFriendId(memberId);
                    cusDataFriendUser.setHeadImgBase64(groupList.get(j).getHeadImg());
                    cusDataFriendUser.setName(groupList.get(j).getNickName());
                    cusDataFriendUser.setRemarkName(groupList.get(j).getNickName());
                    //TODO 用户信息存库（用户表）
                    getFriendUserHelper().updateAllOrAdd(memberId,cusDataFriendUser);//添加或者更新（存在则更新，不存在则增加）
                }

            }
        }

    }
    RealmFriendUserHelper friendUserHelper;
    private  RealmFriendUserHelper  getFriendUserHelper(){
        if (friendUserHelper==null)
            friendUserHelper = new RealmFriendUserHelper(this);
        return  friendUserHelper;
    }
    GroupTeamMemberAdapter groupTeamAdapter;
    private void initGroupTeamAdapter() {
        if (groupTeamAdapter==null)
            groupTeamAdapter = new GroupTeamMemberAdapter(GroupTeamActivity.this, allCusList);
        mExpanList.setAdapter(groupTeamAdapter);
        groupTeamAdapter.notifyDataSetChanged();
        for (int i=0; i<allCusList.size(); i++)
        {
            mExpanList.expandGroup(i);
        }
        mExpanList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                //设置点击不关闭（不收回）
                v.setClickable(true);
                return true;
            }
        });

        mExpanList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                DataLinkManList.RecordBean.FriendListBean.GroupListBean groupListBean = mFriendList.get(groupPosition).getGroupList().get(childPosition);

                String memberId = allCusList.get(groupPosition).getGroupList().get(childPosition).getMemberId();
//                DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean item = (DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean) adapter.getItem(position);
                DataGroupMember.RecordBean.MemberListBean.GroupListBean item = (DataGroupMember.RecordBean.MemberListBean.GroupListBean) groupTeamAdapter.getChild(groupPosition,childPosition);
                //好友关系 1是未添加 2是已添加
                //群成员关系  1是未添加 2是已添加 3是自己
                if (item != null) {
                    Log.e("Relation", "-------------------groupTeam----------------------item.getIsRelation()=" + item.getIsRelation());
                    switch (item.getIsRelation()) {
                        case 1:
//                            跳转陌生人显示界面
//                            IntentUtils.JumpToHaveTwo(FriendDataGroupMemberActivity.class, FriendDataGroupMemberActivity.FRIENG_ID_KEY, memberId, FriendDataGroupMemberActivity.GROUP_ID_KEY, groupId);
//                            IntentUtils.JumpToHaveTwo(FriendDataGroupMemberActivity.class, FriendDataGroupMemberActivity.FRIENG_ID_KEY, memberId, FriendDataGroupMemberActivity.GROUP_ID_KEY, groupId);
//                            break;
                        case 2:
                            IntentUtils.JumpToHaveOne(FriendDataMixActivity.class, "id", memberId);
//                            IntentUtils.JumpToHaveOne(FriendDataActivity.class, "id", memberId);
                            break;
                        case 3:
                            // 自己，跳转个人资料界面
                            IntentUtils.JumpTo(ChangeInfoActivity.class);
                            break;
                    }
                }
//                IntentUtils.JumpToHaveOne(FriendDataActivity.class,"id",memberId);
                return false;
            }
        });

    }

    public int positions;

}
