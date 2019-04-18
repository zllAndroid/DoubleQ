package com.mding.chatfeng.main_code.ui.about_message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_chat.chat_group.ChatGroupActivity;
import com.mding.chatfeng.about_chat.chat_group.GroupChatDetailsActivity;
import com.mding.chatfeng.about_chat.cus_data_group.CusJumpGroupChatData;
import com.mding.chatfeng.about_custom.WrapContentLinearLayoutManager;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.about_utils.about_realm.new_home.CusHomeRealmData;
import com.mding.chatfeng.about_utils.about_realm.new_home.RealmHomeHelper;
import com.mding.chatfeng.main_code.ui.about_contacts.about_swipe.SwipeItemLayout;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.rance.chatui.util.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class GroupAssistantActivity extends BaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.group_ass_recyc)
    RecyclerView mRecyclerView;
    GroupAssistantMsgAdapter  groupAssistantMsgAdapter;
    List<CusHomeRealmData> mList =new ArrayList<>();
    static RealmHomeHelper realmHelper;
    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTital.setText("群助手");
        if (realmHelper==null)
            realmHelper = new RealmHomeHelper(this);
        initData();
        initReceiver();
//        initAdapter();
    }
    private void initRefresh(Intent intent) {
        String message = intent.getStringExtra("message");
        String id = intent.getStringExtra("id");
        if (!StrUtils.isEmpty(message))
        {
            List<CusHomeRealmData> cusHomeRealmData = realmHelper.queryAllmMsg();
            final   CusHomeRealmData homeRealmData = realmHelper.queryAllRealmChat(id );
            if (cusHomeRealmData.size()>0)
            {
                mList.clear();
                for (int i=0;i<cusHomeRealmData.size();i++)
                {
                    String assistantType = cusHomeRealmData.get(i).getAssistantType();
                    String mTy = cusHomeRealmData.get(i).getType();

                    if (mTy!=null&&assistantType!=null)
                        if (mTy.equals("2")&&assistantType.equals("2"))
                        {
                            mList.add(cusHomeRealmData.get(i));
                        }
                }
                initAdapter();
            }
            if (mList.size()!=0)
                for (int i=0;i<mList.size();i++)
                {
                    if (mList.get(i).getTotalId().equals(id+ SplitWeb.getSplitWeb().getUserId()+""))
                    {
                        if (i==0)
                        {
                            mList.remove(0);
                            mList.add(0,homeRealmData);
                            groupAssistantMsgAdapter.notifyItemChanged(0);
                            return;
                        }
                        if (groupAssistantMsgAdapter!=null)
                        {
//                            mRecyclerView.getItemAnimator().setChangeDuration(0);// 通过设置动画执行时间为0来解决闪烁问题
                            groupAssistantMsgAdapter.removeData(i);
                            groupAssistantMsgAdapter.addData(homeRealmData);
                            mRecyclerView.smoothScrollToPosition(0);
//                            realmHelper.deleteRealmMsg(id+SplitWeb.getSplitWeb().USER_ID);
                        }
                        return;
                    }
                }
            if (homeRealmData!=null) {
                groupAssistantMsgAdapter.addData(homeRealmData);
                groupAssistantMsgAdapter.notifyItemChanged(0);
            }
        }
    }
    IntentFilter intentFilter;
    //广播接收消息推送
    private void initReceiver() {
        if (intentFilter == null) {
            intentFilter = new IntentFilter();
            intentFilter.addAction("action.refreshMsgFragment");
            intentFilter.addAction("zll.refreshMsgFragment");
            intentFilter.addAction("zero.refreshMsgFragment");
//            intentFilter.addAction("del.refreshMsgFragment");
//            intentFilter.addAction("action.dialog");
            intentFilter.addAction(GroupChatDetailsActivity.ACTION_UP_GROUP_NAME);
            intentFilter.addAction(AppConfig.LINK_GROUP_DEL_ACTION);
            registerReceiver(mRefreshBroadcastReceiver, intentFilter);
        }
    }
    public BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            dealMsgBroReceiver(intent);
        }
    };
    private void dealMsgBroReceiver(Intent intent) {
        String action = intent.getAction();
        if (realmHelper == null) {
            realmHelper = new RealmHomeHelper(this);
        }
        if (action.equals("action.refreshMsgFragment")) {
            initRefresh(intent);
        }
        if (action.equals("zero.refreshMsgFragment")) {
            initRefresh(intent);
        }
        if (action.equals("zll.refreshMsgFragment"))
        {
            initData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRefreshBroadcastReceiver!=null)
            unregisterReceiver(mRefreshBroadcastReceiver);
    }
    private void initData() {
        List<CusHomeRealmData> cusHomeRealmData = realmHelper.queryAllmMsg();
        if (cusHomeRealmData.size()>0)
        {
            mList.clear();
            for (int i=0;i<cusHomeRealmData.size();i++)
            {
                String assistantType = cusHomeRealmData.get(i).getAssistantType();
                String mTy = cusHomeRealmData.get(i).getType();

                if (mTy!=null&&assistantType!=null)
                    if (mTy.equals("2")&&assistantType.equals("2"))
                    {
                        mList.add(cusHomeRealmData.get(i));
                    }
            }
            initAdapter();
        }
    }
    CusHomeRealmData item;
    private void initAdapter() {
        mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this));
        mRecyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));
        mRecyclerView.getItemAnimator().setChangeDuration(0);// 通过设置动画执行时间为0来解决闪烁问题
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        if (groupAssistantMsgAdapter==null)
            groupAssistantMsgAdapter = new GroupAssistantMsgAdapter(this,mList);
        mRecyclerView.setAdapter(groupAssistantMsgAdapter);
        groupAssistantMsgAdapter.notifyDataSetChanged();
        groupAssistantMsgAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                item = (CusHomeRealmData) adapter.getItem(position);
                    //跳转群组
                    item.setNum(0);
                    realmHelper.updateNumZero(item.getFriendId());
                    groupAssistantMsgAdapter.notifyItemChanged(position);
//                            mRecyclerView.smoothScrollToPosition(0);
                    CusJumpGroupChatData cusJumpGroupChatData = new CusJumpGroupChatData();
                    cusJumpGroupChatData.setGroupId(item.getFriendId());
                    cusJumpGroupChatData.setGroupName(item.getNickName());
                    IntentUtils.JumpToHaveObj(ChatGroupActivity.class, Constants.KEY_FRIEND_HEADER, cusJumpGroupChatData);
            }
        });
    }
    @Override
    protected int getLayoutView() {
        return R.layout.activity_group_assistant;
    }

}
