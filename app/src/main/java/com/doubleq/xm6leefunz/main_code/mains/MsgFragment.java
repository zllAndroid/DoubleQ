package com.doubleq.xm6leefunz.main_code.mains;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.doubleq.model.CusJumpChatData;
import com.doubleq.model.DataHomeMsg;
import com.doubleq.model.DataHomeMsgNew;
import com.doubleq.model.DataJieShou;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.BaseFragment;
import com.doubleq.xm6leefunz.about_base.MyApplication;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_broadcastreceiver.NetEvent;
import com.doubleq.xm6leefunz.about_broadcastreceiver.NetReceiver;
import com.doubleq.xm6leefunz.about_chat.ChatActivity;
import com.doubleq.xm6leefunz.about_chat.ChatNewsWindow;
import com.doubleq.xm6leefunz.about_chat.chat_group.ChatGroupActivity;
import com.doubleq.xm6leefunz.about_chat.cus_data_group.CusJumpGroupChatData;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.about_utils.NetUtils;
import com.doubleq.xm6leefunz.about_utils.TimeUtil;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.CusHomeRealmData;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmHomeHelper;
import com.doubleq.xm6leefunz.about_utils.about_realm.realm_data.CusDataRealmMsg;
import com.doubleq.xm6leefunz.about_utils.about_realm.RealmHelper;
import com.doubleq.xm6leefunz.main_code.mains.top_pop.ConfirmPopWindow;
import com.doubleq.xm6leefunz.main_code.mains.top_pop.MsgChatWindow;
import com.doubleq.xm6leefunz.main_code.mains.top_pop.MyDialogFragment;
import com.doubleq.xm6leefunz.main_code.mains.top_pop.data_bus.BusDataGroupOrFriend;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_link_realm.RealmLinkFriendHelper;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_search.SearchActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_swipe.SwipeItemLayout;
import com.doubleq.xm6leefunz.main_code.ui.about_message.about_message_adapter.MsgAdapter;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
import com.rance.chatui.util.Constants;
import com.zll.websocket.MyWebSocketService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.Nullable;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class MsgFragment extends BaseFragment {
    public MsgFragment() {
    }

    View view =null;
    RecyclerView mRecyclerView;
    LinearLayout mLinTop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        if (view==null) {
        view = inflater.inflate(R.layout.fragment_msg, container, false);
//        }
        initFriend(view);
        initRealmData();
//        sendWeb(SplitWeb.getUserRelation());
        initReceiver();
        initNetReceive();
//        EventBus.getDefault().register(getActivity());
        return view;
    }
    private NetReceiver mReceiver;
    private void initNetReceive() {
        mReceiver = new NetReceiver();
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        getActivity().registerReceiver(mReceiver, mFilter);

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NetEvent event) {
//        ToastUtil.show(""+event.isNet);
        mLinNet.setVisibility(event.isNet ? View.GONE : View.VISIBLE);
//        if (event.isNet)
//        {
//
//        }
//        setNetState(event.isNet());
    }

    //广播接收消息推送
    private void initReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action.refreshMsgFragment");
        getActivity().registerReceiver(mRefreshBroadcastReceiver, intentFilter);

        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("zll.refreshMsgFragment");
        getActivity().registerReceiver(mRefreshBroadcastReceiver, intentFilter2);

        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction("zero.refreshMsgFragment");
        getActivity().registerReceiver(mRefreshBroadcastReceiver, intentFilter3);
        IntentFilter intentFilter4 = new IntentFilter();
        intentFilter4.addAction("del.refreshMsgFragment");
        getActivity().registerReceiver(mRefreshBroadcastReceiver, intentFilter4);
    }

    RealmHomeHelper realmHelper;
    RealmLinkFriendHelper realmLinkFriendHelper;
    private void initRealmData() {
        realmHelper = new RealmHomeHelper(getActivity());
        realmLinkFriendHelper = new RealmLinkFriendHelper(getActivity());
        if (mList.size()==0) {
            List<CusHomeRealmData> cusHomeRealmData = realmHelper.queryAllmMsg();
            if (cusHomeRealmData != null && cusHomeRealmData.size() != 0) {
                mList.clear();
                mList.addAll(cusHomeRealmData);
                initAdapter();
            }
        }else {
            initAdapter();
        }
    }

    LinearLayout mLinNet;
    private void initFriend(final  View view) {
        view.findViewById(R.id.include_frag_img_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ConfirmPopWindow(getActivity()).showAtBottom(view.findViewById(R.id.include_frag_img_add));
            }
        });
        view.findViewById(R.id.include_frag_img_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.JumpTo(SearchActivity.class);
            }
        });
        TextView tv_title = view.findViewById(R.id.include_frag_tv_title);
        mLinNet = view.findViewById(R.id.frag_home_lin_net);
        tv_title.setText("消息");
        mRecyclerView = view.findViewById(R.id.frag_home_recyc);
        mLinTop = view.findViewById(R.id.msg_lin_top);
        mLinNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                设置网络
                NetUtils.startToSettings(getActivity());
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    private BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("action.refreshMsgFragment"))
            {
                initRefresh(intent);
            }
            else if (action.equals("zero.refreshMsgFragment"))
            {
                initZeroNum(intent);
//                initRefresh(intent);
            }
            else  if (action.equals("zll.refreshMsgFragment"))
            {
                refreshMsg(intent);
            }
            else  if (action.equals("del.refreshMsgFragment"))
            {
                initDel(intent);
            }
            if (mRecyclerView!=null)
                mRecyclerView.smoothScrollToPosition(0);
            sendBroadcast();
        }
    };

    private void initDel(Intent intent) {
        String id = intent.getStringExtra("id");
//        realmHelper.deleteRealmMsg(id);
        List<CusHomeRealmData> cusHomeRealmData = realmHelper.queryAllmMsg();
        Log.e("home","initDel="+cusHomeRealmData.size());
        if (cusHomeRealmData.size()!=0)
        {
            mList.clear();
            mList.addAll(cusHomeRealmData);
            initAdapter();
        }
    }

    private void refreshMsg(Intent intent) {
        String id = intent.getStringExtra("id");
        List<CusHomeRealmData> cusHomeRealmData = realmHelper.queryAllRealmMsg();
        CusHomeRealmData homeRealmData = realmHelper.queryAllRealmChat(id );
        Log.e("MyApplication","Refresh="+cusHomeRealmData.size());
        if ( mList.size()==0&&cusHomeRealmData.size()!=0)
        {
            mList.clear();
            mList.addAll(cusHomeRealmData);
            initAdapter();
        }

    }

    private void sendBroadcast() {
        if(msgAdapter!=null)
        {
            int numData = msgAdapter.getNumData();
            Intent intent2 = new Intent();
            intent2.putExtra("num", numData+"");
            intent2.setAction("action.refreshMain");
            getActivity().sendBroadcast(intent2);

        }
    }

    private void initZeroNum(Intent intent) {
        String id = intent.getStringExtra("id");
        CusHomeRealmData homeRealmData = realmHelper.queryAllRealmChat(id);
        if (homeRealmData!=null) {
            realmHelper.updateNumZero(id);//更新首页聊天界面数据（未读消息数目）
        }
        initRefresh(intent);

    }

    private void initRefresh(Intent intent) {
        String message = intent.getStringExtra("message");
        String id = intent.getStringExtra("id");
        if (!StrUtils.isEmpty(message))
        {
            if (realmHelper==null)
            {
                realmHelper = new RealmHomeHelper(getActivity());
            }
            List<CusHomeRealmData> cusHomeRealmData = realmHelper.queryAllmMsg();
            CusHomeRealmData homeRealmData = realmHelper.queryAllRealmChat(id );
            Log.e("MyApplication","Refresh="+cusHomeRealmData.size());
            if ( mList.size()==0&&cusHomeRealmData.size()!=0)
            {
                mList.clear();
                mList.addAll(cusHomeRealmData);
                initAdapter();
//                msgAdapter.notifyDataSetChanged();
            }
//            if (mList.contains(id+SplitWeb.USER_ID+""))
            if (mList.size()!=0)
                for (int i=0;i<mList.size();i++)
                {
                    if (mList.get(i).getTotalId().equals(id+SplitWeb.getUserId()+""))
                    {
                        if (i==0)
                        {
                            mList.remove(0);
                            mList.add(0,homeRealmData);
                            msgAdapter.notifyItemChanged(0);

                            return;
                        }
                        if (msgAdapter!=null)
                        {
//                            mRecyclerView.getItemAnimator().setChangeDuration(0);// 通过设置动画执行时间为0来解决闪烁问题
                            msgAdapter.removeData(i);
                            msgAdapter.addData(homeRealmData);
                            mRecyclerView.smoothScrollToPosition(0);
//                            realmHelper.deleteRealmMsg(id+SplitWeb.USER_ID);
                        }
                        Log.e("MyApplication","Refresh="+cusHomeRealmData.size());
                        return;
                    }
                }
            if (homeRealmData!=null) {
                msgAdapter.addData(homeRealmData);
                msgAdapter.notifyItemChanged(0);
            }
//            else
//            {
//                mList.add(0,homeRealmData);
//            }
        }
    }


    List<CusHomeRealmData> mList =new ArrayList<>();

    MsgAdapter msgAdapter =null;

    private void initAdapter() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getActivity()));
        mRecyclerView.getItemAnimator().setChangeDuration(0);// 通过设置动画执行时间为0来解决闪烁问题
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        if (msgAdapter==null)
            msgAdapter = new MsgAdapter(getActivity(),mList,mItemTouchListener);
        mRecyclerView.setAdapter(msgAdapter);
        msgAdapter.notifyDataSetChanged();
        mRecyclerView.smoothScrollToPosition(0);
        sendBroadcast();
        msgAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                item = (CusHomeRealmData) adapter.getItem(position);
                switch (view.getId())
                {
                    case R.id.item_msg_re:
                        Log.e("item","type"+item.getType());
                        if (item.getType().equals("1")) {

                            //                            点击进入详情后，消息个数清零
//                            mList.remove(position);
                            item.setNum(0);
                            realmHelper.updateNumZero(item.getFriendId());
                            msgAdapter.notifyItemChanged(position);
                            mRecyclerView.smoothScrollToPosition(0);
                            // 好友
                            CusJumpChatData cusJumpChatData = new CusJumpChatData();
                            cusJumpChatData.setFriendHeader(item.getHeadImg());
                            cusJumpChatData.setFriendId(item.getFriendId());
                            cusJumpChatData.setFriendName(item.getNickName());
                            IntentUtils.JumpToHaveObj(ChatActivity.class, Constants.KEY_FRIEND_HEADER, cusJumpChatData);

                        }else {
                            //跳转群组
                            item.setNum(0);
                            realmHelper.updateNumZero(item.getFriendId());
                            msgAdapter.notifyItemChanged(position);
                            mRecyclerView.smoothScrollToPosition(0);
                            CusJumpGroupChatData cusJumpGroupChatData = new CusJumpGroupChatData();
                            cusJumpGroupChatData.setGroupId(item.getFriendId());
                            cusJumpGroupChatData.setGroupName(item.getNickName());
                            IntentUtils.JumpToHaveObj(ChatGroupActivity.class, Constants.KEY_FRIEND_HEADER, cusJumpGroupChatData);
                        }
                        break;
//                        点击编辑，弹出聊天窗口
                    case R.id.item_tv_click_ok:
                        type = item.getType();
                        FragmentManager childFragmentManager = getChildFragmentManager();
                        MyDialogFragment myDialogFragment = new MyDialogFragment(item.getFriendId(),type);
                        myDialogFragment.show(childFragmentManager,"show");

//                        chatWindow = new MsgChatWindow(getActivity(), item.getFriendId());
//                        chatWindow.showAtLocation(mLinTop, Gravity.BOTTOM, 0,0);
//                        ToastUtil.show("点击编辑");
                        break;
                }
            }
        });
    }
    CusHomeRealmData item;
    //订阅方法，接收到服务器返回事件处理
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BusDataGroupOrFriend messageInfo) {
        if (!StrUtils.isEmpty(messageInfo.getMsg())&&item!=null) {
            if (type.equals("1")) {
                MyApplication.getmConnectManager().sendText(SplitWeb.privateSend(item.getFriendId(), messageInfo.getMsg(), "1", TimeUtil.getTime()));
            }
            else {
                MyApplication.getmConnectManager().sendText(SplitWeb.groupSend(item.getFriendId(), messageInfo.getMsg(), "1", TimeUtil.getTime()));
            }
        }
    }
    String type="1";
    //
    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            realmHelper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (mRefreshBroadcastReceiver!=null)
                getActivity().unregisterReceiver(mRefreshBroadcastReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (mReceiver!=null)
                getActivity().unregisterReceiver(mReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    MsgAdapter.ItemTouchListener mItemTouchListener = new MsgAdapter.ItemTouchListener() {
        @Override
        public void onLeftMenuClick(View view, int positions, String WaybillNum) {
            ToastUtil.show("删除成功");
            CusHomeRealmData item = msgAdapter.getItem(positions);
            realmHelper.deleteRealmMsg(item.getFriendId());
            msgAdapter.delItem(positions);
            sendBroadcast();
        }
    };
}
