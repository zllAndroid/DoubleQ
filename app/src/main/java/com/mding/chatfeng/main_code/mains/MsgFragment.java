package com.mding.chatfeng.main_code.mains;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mding.chatfeng.main_code.ui.about_contacts.about_search.SearchActivity;
import com.mding.model.CusJumpChatData;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_broadcastreceiver.NetEvent;
import com.mding.chatfeng.about_broadcastreceiver.NetReceiver;
import com.mding.chatfeng.about_chat.ChatActivity;
import com.mding.chatfeng.about_chat.chat_group.ChatGroupActivity;
import com.mding.chatfeng.about_chat.chat_group.GroupChatDetailsActivity;
import com.mding.chatfeng.about_chat.cus_data_group.CusJumpGroupChatData;
import com.mding.chatfeng.about_custom.WrapContentLinearLayoutManager;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.about_utils.NetUtils;
import com.mding.chatfeng.about_utils.TimeUtil;
import com.mding.chatfeng.about_utils.about_realm.new_home.CusHomeRealmData;
import com.mding.chatfeng.about_utils.about_realm.new_home.RealmHomeHelper;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.RealmMsgInfoTotalHelper;
import com.mding.chatfeng.main_code.ui.about_contacts.about_swipe.SwipeItemLayout;
import com.mding.chatfeng.main_code.ui.about_message.GroupAssistantActivity;
import com.mding.chatfeng.main_code.ui.about_message.about_message_adapter.MsgAdapter;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.BaseFragment;
import com.mding.chatfeng.about_base.MyApplication;
import com.mding.chatfeng.main_code.mains.top_pop.MyDialogFragment;
import com.mding.chatfeng.main_code.mains.top_pop.data_bus.BusDataGroupOrFriend;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
import com.rance.chatui.util.Constants;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static com.mding.chatfeng.about_utils.IntentUtils.JumpTo;

/**
 * 项目：DoubleQ
 * 文件描述：主界面FindFragment之消息页面
 * 作者：zll
 */
public class MsgFragment extends BaseFragment {
    public MsgFragment() {}
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(true);
    }
    View view =null;
    RecyclerView mRecyclerView;
    LinearLayout mLinTop;
//    FragmentTopBarLayout mFgTopBar;
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
////        if (view==null) {
//        view = inflater.inflate(R.layout.fragment_msg, container, false);
////        }
//        initFriend(view);
//        initRealmData();
////        首页消息广播处理
//        initReceiver();
//
////        网络连接状态的广播接收
//        initNetReceive();
//
//        return view;
//    }

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_msg;
    }

    @Override
    protected void initBaseUI(View view) {
        super.initBaseUI(view);
        view = getTopBarView();
        initFriend(view);
        initRealmData();
//        首页消息广播处理
        initReceiver();

//        网络连接状态的广播接收
        initNetReceive();


    }

    @Override
    public void onStart() {
        super.onStart();
//        mFgTopBar.setTop(getActivity());
    }

    private NetReceiver mReceiver;
    IntentFilter mFilter=null;
    private void initNetReceive() {
        if (mFilter==null) {
            Log.e(Tag,"new NetReceive");
            mFilter = new IntentFilter();
            mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            mReceiver = new NetReceiver();
            getActivity().registerReceiver(mReceiver, mFilter);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NetEvent event) {
        try {
            mLinNet.setVisibility(event.isNet ? View.GONE : View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static final  String Tag="Msgfragment";
    IntentFilter intentFilter;
    //广播接收消息推送
    private void initReceiver() {
        if (intentFilter == null) {
            Log.e(Tag,"new IntentFilter");
            intentFilter = new IntentFilter();
            intentFilter.addAction("action.refreshMsgFragment");
            intentFilter.addAction("zll.refreshMsgFragment");
            intentFilter.addAction("add.refreshMsgFragment");
            intentFilter.addAction("zero.refreshMsgFragment");
            intentFilter.addAction("del.refreshMsgFragment");
            intentFilter.addAction("action.dialog");
            intentFilter.addAction("assistant.refreshMsgFragment");
            intentFilter.addAction(GroupChatDetailsActivity.ACTION_UP_GROUP_NAME);
            intentFilter.addAction(AppConfig.LINK_GROUP_DEL_ACTION);
            getActivity().registerReceiver(mRefreshBroadcastReceiver, intentFilter);
        }
    }

    static RealmHomeHelper realmHelper;
    static RealmMsgInfoTotalHelper realmMsgInfoTotalHelper;
    private void initRealmData() {
        Log.e(Tag,"realmHelper="+realmHelper+",-----realmMsgInfoTotalHelper="+ realmMsgInfoTotalHelper +",---mList.size()="+mList.size());
        if (realmHelper==null)
            realmHelper = new RealmHomeHelper(getActivity());
        if (realmMsgInfoTotalHelper ==null)
            realmMsgInfoTotalHelper = new RealmMsgInfoTotalHelper(getActivity());
        if (mList.size()==0) {
            List<CusHomeRealmData> cusHomeRealmData = realmHelper.queryAllmMsg();
            if (cusHomeRealmData != null && cusHomeRealmData.size() != 0) {
                mList.clear();
                addListMethon(cusHomeRealmData);
                initAdapter();
            }
        }else {
            initAdapter();
        }
    }

    LinearLayout mLinNet;
    //    ConfirmPopWindow confirmPopWindow=null;
    private void initFriend(final  View view) {
        TextView tv_title = view.findViewById(R.id.include_frag_tv_title);
        mLinNet = view.findViewById(R.id.frag_home_lin_net);
//        mFgTopBar = view.findViewById(R.id.fg_top_bar);
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

        mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getActivity()));
        mRecyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getActivity()));
        mRecyclerView.getItemAnimator().setChangeDuration(0);// 通过设置动画执行时间为0来解决闪烁问题

        Log.e(Tag,"initMsgUI");
//        initTop();
    }
//    private void initTop() {
//        mFgTopBar.setOnRightClick (new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new ConfirmPopWindow(getActivity()).showAtBottom(view.findViewById(R.id.include_frag_img_add));
//            }
//        });
//        mFgTopBar.setOnSearchClick(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                IntentUtils.JumpTo(LoadDataActivity.class);
//            }
//        });
//        mFgTopBar.setTitle("消息");
//    }

    @Override
    protected String setFragmentTital() {
        return "消息";
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    public  BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            dealMsgBroReceiver(intent);
        }
    };

    private void dealMsgBroReceiver(Intent intent) {
        String action = intent.getAction();
        if (realmHelper == null) {
            realmHelper = new RealmHomeHelper(getActivity());
        }
        if (action.equals("action.refreshMsgFragment"))
        {
            initRefresh(intent);
        }
        if (action.equals("add.refreshMsgFragment"))
        {
            initRealmData();
        }
        if (action.equals("assistant.refreshMsgFragment"))
        {
            initAssistant(intent);
        }
        else if (action.equals("zero.refreshMsgFragment"))
        {
            initZeroNum(intent);
        }
        else  if (action.equals("zll.refreshMsgFragment"))
        {
            refreshMsg(intent);
        }
        else  if (action.equals("del.refreshMsgFragment"))
        {
            initDel(intent);
        }
        else if (action.equals("action_dialog")){

        }
        else if (action.equals(GroupChatDetailsActivity.ACTION_UP_GROUP_NAME)){
            initGroupName(intent);
        }
        else if (action.equals(AppConfig.LINK_GROUP_DEL_ACTION)){
            initDel(intent);
        }
        if (mRecyclerView!=null)
            mRecyclerView.smoothScrollToPosition(0);
        sendBroadcast();
    }
    @Override
    protected void searchClickEvent() {
        JumpTo(SearchActivity.class);
    }
    private void initAssistant(Intent intent) {
//        String message = intent.getStringExtra("message");
        String num = intent.getStringExtra("num");
        CusHomeRealmData cusHomeRealmData1 = new CusHomeRealmData();
        cusHomeRealmData1.setGroupNumMsg(num);

        List<CusHomeRealmData> cusHomeRealmData = realmHelper.queryAllRealmMsg();
        for (int i=0;i<cusHomeRealmData.size();i++)
        {
//            String totalId = realmData.get(i).getTotalId();//群助手id
            String assistantType = cusHomeRealmData.get(i).getAssistantType();
            String mTy = cusHomeRealmData.get(i).getType();

            if (mTy != null && assistantType != null&&mTy.equals("2") && assistantType.equals("2")) {


            }else {
//                mList.add(cusHomeRealmData.get(i));
                mList.set(i,cusHomeRealmData1);
                if (msgAdapter!=null)
                    msgAdapter.notifyItemChanged(i);
            }
//            if (mList.size()>0)
//            {
//                for (int j=0;j<mList.size();j++)
//                {
//                    String totalId = cusHomeRealmData.get(j).getTotalId();
//                    if (totalId.equals(AppConfig.GroupAssistant))
//                    {
//                        mList.set(j,cusHomeRealmData1);
//                        if (msgAdapter!=null)
//                            msgAdapter.notifyItemChanged(j);
//                    }
//                }
//            }

        }
    }

    private void initGroupName(Intent intent) {
        String groupId = intent.getStringExtra("id");
        String groupName = intent.getStringExtra("groupName");
        List<CusHomeRealmData> cusHomeRealmData = realmHelper.queryAllRealmMsg();
        CusHomeRealmData homeRealmData = realmHelper.queryAllRealmChat(groupId);
        Log.e("MyApplication","Refresh="+cusHomeRealmData.size());
        if ( mList.size()==0&&cusHomeRealmData.size()!=0)
        {
            mList.clear();
            addListMethon(cusHomeRealmData);
            twoAdapter();
        }
        if (mList.size()!=0)
            for (int i=0;i<mList.size();i++)
            {
                if (mList.get(i).getTotalId().equals(groupId+SplitWeb.getUserId()+""))
                {
                    if (homeRealmData!=null) {
                        mList.set(i, homeRealmData);
                        if (msgAdapter!=null)
                            msgAdapter.notifyItemChanged(i);
                    }
//                    if (i==0)
//                    {
//                        mList.remove(0);
//                        mList.add(0,homeRealmData);
//                        msgAdapter.notifyItemChanged(0);
//
//                        return;
//                    }
//                    if (msgAdapter!=null)
//                    {
////                            mRecyclerView.getItemAnimator().setChangeDuration(0);// 通过设置动画执行时间为0来解决闪烁问题
//                        msgAdapter.removeData(i);
//                        msgAdapter.addData(homeRealmData);
//                        mRecyclerView.smoothScrollToPosition(0);
////                            realmHelper.deleteRealmMsg(id+SplitWeb.USER_ID);
//                    }
//                    return;
//        if (homeRealmData!=null) {
//            msgAdapter.addData(homeRealmData);
//            msgAdapter.notifyItemChanged(0);
//        }
                }
            }
    }

    private void initDel(Intent intent) {
        List<CusHomeRealmData> cusHomeRealmData = realmHelper.queryAllmMsg();
        Log.e("home","initDel="+cusHomeRealmData.size());
        try {
            mList.clear();
            addListMethon(cusHomeRealmData);
            twoAdapter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void refreshMsg(Intent intent) {
        List<CusHomeRealmData> cusHomeRealmData = realmHelper.queryAllRealmMsg();
        Log.e("MyApplication","Refresh="+cusHomeRealmData.size());
        if (cusHomeRealmData.size()!=0)
        {
            mList.clear();
            addListMethon(cusHomeRealmData);
            twoAdapter();
        }
    }
    int num=-1;
    private void sendBroadcast() {
        if(msgAdapter!=null)
        {
            int numData = msgAdapter.getNumData();
            if (isZero||num!=numData)
            {
                if (numData!=0)
                    num=numData;
                Intent intent2 = new Intent();
                intent2.putExtra("num", numData+"");
                intent2.setAction("action.refreshMain");
                getActivity().sendBroadcast(intent2);
            }
        }
    }

    public  void initZeroNum(Intent intent) {
        String id = intent.getStringExtra("id");
        CusHomeRealmData homeRealmData = realmHelper.queryAllRealmChat(id);
        if (homeRealmData!=null) {
            realmHelper.updateNumZero(id);//更新首页聊天界面数据（未读消息数目）
        }
        String message = intent.getStringExtra("message");
        if (!StrUtils.isEmpty(message)) {

            List<CusHomeRealmData> cusHomeRealmData = realmHelper.queryAllmMsg();
            mList.clear();
            Log.e("MyApplication", "Refresh=" + cusHomeRealmData.size());
            if (mList.size() == 0 && cusHomeRealmData.size() != 0) {
                addListMethon(cusHomeRealmData);
//                mList.addAll(cusHomeRealmData);
                twoAdapter();
//                msgAdapter.notifyDataSetChanged();
            }
//            initRefreshZero(intent);
        }
    }
    private void initRefresh(Intent intent) {
        String message = intent.getStringExtra("message");
        String id = intent.getStringExtra("id");
        if (!StrUtils.isEmpty(message))
        {
            List<CusHomeRealmData> cusHomeRealmData = realmHelper.queryAllmMsg();
            CusHomeRealmData homeRealmData = realmHelper.queryAllRealmChat(id );
            if ( mList.size()==0&&cusHomeRealmData.size()!=0)
            {
                mList.clear();
                addListMethon(cusHomeRealmData);
//                mList.addAll(cusHomeRealmData);
                twoAdapter();
//                msgAdapter.notifyDataSetChanged();
            }
//            if (mList.contains(id+SplitWeb.USER_ID+""))
            if (mList.size()!=0)
                for (int i=0;i<mList.size();i++)
                {
                    String assistantType = mList.get(i).getAssistantType();
                    String mTy = mList.get(i).getType();
                    if (mTy != null && assistantType != null)
                        if (mTy.equals("2") && assistantType.equals("2")) {
                            mList.remove(i);
                            if (msgAdapter!=null)
                                msgAdapter.notifyItemChanged(i);
                            return;
                        }
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
    public  static  boolean isZero=false;
    public  static  boolean mIsRefreshing=false;
    public  void addListMethon(List<CusHomeRealmData> realmData)
    {
//        mRecyclerView.setOnTouchListener(
//                new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        if (mIsRefreshing) {
//                            return true;
//                        } else {
//                            return false;
//                        }
//                    }
//                }
//        );

        if (realmData.size()==0)
        {
            return;
        }
        for (int i=0;i<realmData.size();i++)
        {
//            String totalId = realmData.get(i).getTotalId();//群助手id
            String assistantType = realmData.get(i).getAssistantType();
            String mTy = realmData.get(i).getType();
            if (mTy != null && assistantType != null&&mTy.equals("2") && assistantType.equals("2")) {
            }else {
                mList.add(realmData.get(i));
            }
        }
        mIsRefreshing=true;
    }
    private void twoAdapter() {
        if (msgAdapter==null) {
            msgAdapter = new MsgAdapter(getActivity(), mList, mItemTouchListener);
            mRecyclerView.setAdapter(msgAdapter);
        }
        if(msgAdapter!=null)
        {
            msgAdapter.notifyDataSetChanged();
            mRecyclerView.smoothScrollToPosition(0);
            sendBroadcast();
        }
    }
    private void initAdapter() {
//        for (int i=0;i<mList.size();i++)
//        {
//            String totalId = mList.get(i).getTotalId();
//            if (!totalId.equals(AppConfig.GroupAssistant)) {
//                String assistantType = mList.get(i).getAssistantType();
//                String mTy = mList.get(i).getType();
//
//                if (mTy != null && assistantType != null)
//                    if (mTy.equals("2") && assistantType.equals("2")) {
//                        mList.remove(i);
//                        if (msgAdapter!=null)
//                            msgAdapter.notifyItemChanged(i);
//                    }
//            }
//        }
//        mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getActivity()));
//        mRecyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getActivity()));
//        mRecyclerView.getItemAnimator().setChangeDuration(0);// 通过设置动画执行时间为0来解决闪烁问题
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        if (msgAdapter==null) {
            Log.e(Tag, "msgAdapter=" + msgAdapter);
            msgAdapter = new MsgAdapter(getActivity(), mList, mItemTouchListener);
            mRecyclerView.setAdapter(msgAdapter);
            msgAdapter.notifyDataSetChanged();
            mRecyclerView.smoothScrollToPosition(0);
            sendBroadcast();
            msgAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    item = (CusHomeRealmData) adapter.getItem(position);
                    switch (view.getId()) {
                        case R.id.item_msg_re:
                            if (item.getTotalId().equals(AppConfig.GroupAssistant)) {
                                // 跳转群助手
                                IntentUtils.JumpTo(GroupAssistantActivity.class);
                            } else {
                                clickItem(position);
                            }
                            break;
                        //点击编辑，弹出聊天窗口
                        case R.id.item_tv_click_ok:
                            type = item.getType();
                            FragmentManager childFragmentManager = getChildFragmentManager();
                            MyDialogFragment myDialogFragment = new MyDialogFragment(item.getFriendId(), type);
                            myDialogFragment.show(childFragmentManager, "show");
//                        chatWindow = new MsgChatWindow(getActivity(), item.getFriendId());
//                        chatWindow.showAtLocation(mLinTop, Gravity.BOTTOM, 0,0);
//                        ToastUtil.show("点击编辑");
                            break;
                    }
                }
            });
        }
    }

    private void clickItem(int position) {
        int num = item.getNum();
        if (num>0)
        {
            item.setNum(0);
            isZero=true;
            sendBroadcast();
        }else {
            isZero=false;
        }
        if (item.getType().equals("1")) {
            // 点击进入详情后，消息个数清零
            item.setNum(0);
            realmHelper.updateNumZero(item.getFriendId());
            if (msgAdapter!=null)
                msgAdapter.notifyItemChanged(position);
//            mRecyclerView.smoothScrollToPosition(0);
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
//            mRecyclerView.smoothScrollToPosition(0);
            CusJumpGroupChatData cusJumpGroupChatData = new CusJumpGroupChatData();
            cusJumpGroupChatData.setGroupId(item.getFriendId());
            cusJumpGroupChatData.setGroupName(item.getNickName());
            IntentUtils.JumpToHaveObj(ChatGroupActivity.class, Constants.KEY_FRIEND_HEADER, cusJumpGroupChatData);
        }
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
            realmMsgInfoTotalHelper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        getActivity().stopService(intent_service);
//        try {
//            if (mRefreshBroadcastReceiver!=null)
//                getActivity().unregisterReceiver(mRefreshBroadcastReceiver);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            if (mReceiver!=null)
                getActivity().unregisterReceiver(mReceiver);
            if (mRefreshBroadcastReceiver!=null)
                getActivity().unregisterReceiver(mRefreshBroadcastReceiver);
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
