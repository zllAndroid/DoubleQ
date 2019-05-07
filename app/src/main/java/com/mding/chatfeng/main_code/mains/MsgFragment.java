package com.mding.chatfeng.main_code.mains;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mding.chatfeng.about_application.BaseApp;
import com.mding.chatfeng.about_application.BaseApplication;
import com.mding.chatfeng.about_broadcastreceiver.MainTabNumEvent;
import com.mding.chatfeng.about_broadcastreceiver.MsgHomeEvent;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.main_code.ui.about_contacts.about_search.SearchActivity;
import com.mding.model.CusJumpChatData;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_broadcastreceiver.NetEvent;
import com.mding.chatfeng.about_broadcastreceiver.NetReceiver;
import com.mding.chatfeng.about_chat.ChatActivity;
import com.mding.chatfeng.about_chat.chat_group.ChatGroupActivity;
import com.mding.chatfeng.about_chat.cus_data_group.CusJumpGroupChatData;
import com.mding.chatfeng.about_custom.WrapContentLinearLayoutManager;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.about_utils.NetUtils;
import com.mding.chatfeng.about_utils.TimeUtil;
import com.mding.chatfeng.about_utils.about_realm.new_home.CusHomeRealmData;
import com.mding.chatfeng.about_utils.about_realm.new_home.RealmHomeHelper;
import com.mding.chatfeng.main_code.ui.about_contacts.about_swipe.SwipeItemLayout;
import com.mding.chatfeng.main_code.ui.about_message.GroupAssistantActivity;
import com.mding.chatfeng.main_code.ui.about_message.about_message_adapter.MsgAdapter;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.BaseFragment;
import com.mding.chatfeng.main_code.mains.top_pop.MyDialogFragment;
import com.mding.chatfeng.main_code.mains.top_pop.data_bus.BusDataGroupOrFriend;
import com.mding.model.DataChatGroupPop;
import com.mding.model.DataChatPop;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
import com.rance.chatui.util.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static com.mding.chatfeng.about_utils.IntentUtils.JumpTo;

/**
 * 项目：DoubleQ
 * 文件描述：主界面MsgFragment之消息页面
 * 作者：zll
 * 修改者：ljj
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
//        initReceiver();

//        网络连接状态的广播接收
        initNetReceive();
        initRecycScroll();
    }

    private void initRecycScroll() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //调用方法
                if (msgAdapter!=null)
                {
                    msgAdapter.colseBGASwipeItemLayout();
//                    ToastUtil.show("滚动关闭哦");
                }
            }
        });
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
    public void onEventMsgThread(MsgHomeEvent event) {
        String id = event.getId();
        String type=event.getType();
        String message=event.getMessage();
        dealMsgBroReceiver(id,message,type);
//   发送     EventBus.getDefault().post(new MessageEvent(message.getResponseText()));
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NetEvent event) {
        try {
            mLinNet.setVisibility(event.isNet ? View.GONE : View.VISIBLE);
            BaseApp.mIChatRequst.initData();//调用重新连接
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static final  String Tag="Msgfragment";

    static RealmHomeHelper realmHelper;

    class RealmThread extends Thread {
        @Override
        public void run() {
            RealmHomeHelper  realmHelper = new RealmHomeHelper(getActivity());
            if (mList.size()==0) {
                List<CusHomeRealmData> cusHomeRealmData = realmHelper.queryAllmMsg();
                if (cusHomeRealmData != null && cusHomeRealmData.size() != 0) {
                    mList.clear();
                    mList.addAll(cusHomeRealmData);
//                    addListMethon(cusHomeRealmData);
                }else
                {
                    return;
                }
            }
            Message message = new Message();
            message.what = AppConfig.WHAT_REALM_INITADAPTER;
            message.obj="123";
            handler.sendMessage(message);
            AppConfig.logs("-----------------------handler-----------发送----------------------");

        }

    }
    LongTimeTask myTask=null;
    private void initRealmData() {
        if (myTask==null) {
            myTask = new LongTimeTask();
            myTask.execute();
        }
    }

    private class LongTimeTask extends AsyncTask<List<CusHomeRealmData>,Void,List<CusHomeRealmData>>
    {
        @Override
        protected void onPostExecute(List<CusHomeRealmData> o) {
            super.onPostExecute(o);
//            initAdapter();
            AppConfig.logs("-----------------------onPostExecute-----------接收----------------------");
            Message message = new Message();
            message.what=AppConfig.WHAT_REALM_INITADAPTER;
            mHandlers.sendMessage(message);
            myTask=null;
            return;
        }
        @Override
        protected List<CusHomeRealmData> doInBackground(List<CusHomeRealmData>... lists) {
            AppConfig.logs("-----------------------doInBackground-----------执行----------------------");

            RealmHomeHelper  realmHelper = new RealmHomeHelper(getActivity());
            if (mList.size()==0) {
                List<CusHomeRealmData> cusHomeRealmData = realmHelper.queryAllmMsg();
                if (cusHomeRealmData != null && cusHomeRealmData.size() != 0) {
                    mList.clear();
                    mList.addAll(cusHomeRealmData);
//                    addListMethon(cusHomeRealmData);
                }else
                {
                    return null;
                }
            }
            return null;
        }
    }
    @Override
    protected void onFragmentHandleMessage(Message msg) {
        super.onFragmentHandleMessage(msg);

        switch (msg.what)
        {
//           刷新adapter
            case AppConfig.WHAT_REALM_INITADAPTER:
                initAdapter();
                AppConfig.logs("-----------------------handler-----------接收----------------------");
                break;
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
    }

    @Override
    protected String setFragmentTital() {
        return "消息";
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    private void dealMsgBroReceiver(String id,String msg,String action) {
        if (realmHelper == null) {
            realmHelper = new RealmHomeHelper(getActivity());
        }
        if (action.equals(AppConfig.MSG_ACTION_REFRESH))
        {
            initRefresh(id,msg);
        }
        if (action.equals(AppConfig.MSG_ADD_REFRESH))
//        if (action.equals("add.refreshMsgFragment"))
        {
            initRealmData();
        }
        if (action.equals("assistant.refreshMsgFragment"))
        {
//            initAssistant(intent);
        }
        else if (action.equals(AppConfig.MSG_ZERO_REFRESH))
//        else if (action.equals("zero.refreshMsgFragment"))
        {
//            initZeroNum(id,msg);
            refreshMsg(id,msg);
//            initRefresh(id,msg);
        }
        else  if (action.equals(AppConfig.MSG_ZLL_REFRESH))
//        else  if (action.equals("zll.refreshMsgFragment"))
        {
            refreshMsg(id,msg);
        }
        else  if (action.equals(AppConfig.MSG_DEL_REFRESH))
//        else  if (action.equals("del.refreshMsgFragment"))
        {
            initDel();
        }
        else if (action.equals("action_dialog")){

        }
        else if (action.equals(AppConfig.ACTION_UP_GROUP_NAME)){
            initGroupName(id,msg);
        }
        else if (action.equals(AppConfig.LINK_GROUP_DEL_ACTION)){
            initDel();
        }
        else if (action.equals(AppConfig.LINK_FRIEND_DEL_ACTION)){
            initDel();
        }
        if (mRecyclerView!=null)
            mRecyclerView.smoothScrollToPosition(0);
        sendBroadcast();
    }
    @Override
    protected void searchClickEvent() {
        JumpTo(SearchActivity.class);
    }

    private void initGroupName(String groupId,String message) {
//        String groupId = intent.getStringExtra("id");
//        String groupName = intent.getStringExtra("groupName");
        List<CusHomeRealmData> cusHomeRealmData = realmHelper.queryAllRealmMsg();
        CusHomeRealmData homeRealmData = realmHelper.queryAllRealmChat(groupId);
        if ( mList.size()==0&&cusHomeRealmData.size()!=0)
        {
            mList.clear();
            addListMethon(cusHomeRealmData);
            twoAdapter();
        }
        if (mList.size()!=0)
            for (int i=0;i<mList.size();i++)
            {
                if (mList.get(i).getTotalId().equals(groupId+SplitWeb.getSplitWeb().getUserId()+""))
                {
                    if (homeRealmData!=null) {
                        mList.set(i, homeRealmData);
                        if (msgAdapter!=null)
                            msgAdapter.notifyItemChanged(i);
                    }
                }
            }
    }

    private void initDel() {
        List<CusHomeRealmData> cusHomeRealmData = realmHelper.queryAllmMsg();
        try {
            mList.clear();
            addListMethon(cusHomeRealmData);
            twoAdapter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void refreshMsg(String id,String message) {
        List<CusHomeRealmData> cusHomeRealmData = realmHelper.queryAllRealmMsg();
        if (cusHomeRealmData.size()!=0)
        {
            mList.clear();
            mList.addAll(cusHomeRealmData);
//            addListMethon(cusHomeRealmData);
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
               /* Intent intent2 = new Intent();
                intent2.putExtra("num", numData+"");
                intent2.setAction("action.refreshMain");
                getActivity().sendBroadcast(intent2);*/
                EventBus.getDefault().post(new MainTabNumEvent(numData,AppConfig.MAIN_TAB_ONE));
            }
        }
    }

    private void initZeroNum(String id,String message) {
        CusHomeRealmData homeRealmData = realmHelper.queryAllRealmChat(id);
        if (homeRealmData!=null) {
            realmHelper.updateNumZero(id);//更新首页聊天界面数据（未读消息数目）
        }
        if (!StrUtils.isEmpty(message)) {

            List<CusHomeRealmData> cusHomeRealmData = realmHelper.queryAllmMsg();
            mList.clear();
            Log.e("MyApplication", "Refresh=" + cusHomeRealmData.size());
            if (mList.size() == 0 && cusHomeRealmData.size() != 0) {
                addListMethon(cusHomeRealmData);
                twoAdapter();
            }
        }
    }

    private void initRefresh(String id,String message) {
//        String message = intent.getStringExtra("message");
//        String id = intent.getStringExtra("id");
        if (!StrUtils.isEmpty(message))
        {
            List<CusHomeRealmData> cusHomeRealmData = realmHelper.queryAllmMsg();
            CusHomeRealmData homeRealmData = realmHelper.queryAllRealmChat(id );
            if (mList.size()==0&&cusHomeRealmData.size()!=0)
            {
                mList.clear();
                mList.addAll(cusHomeRealmData);
//                addListMethon(cusHomeRealmData);
                twoAdapter();
            }
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
                    if (mList.get(i).getTotalId().equals(id+SplitWeb.getSplitWeb().getUserId()+""))
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
                        }
                        return;
                    }
                }
            if (homeRealmData!=null) {
                msgAdapter.addData(homeRealmData);
                msgAdapter.notifyItemChanged(0);
            }
        }
    }
    List<CusHomeRealmData> mList =new ArrayList<>();
    MsgAdapter msgAdapter =null;
    public  static  boolean isZero=false;
    public  static  boolean mIsRefreshing=false;
    public  void addListMethon(List<CusHomeRealmData> realmData)
    {
        if (realmData.size()==0)
        {
            return;
        }
        mList.addAll(realmData);

//        for (int i=0;i<realmData.size();i++)
//        {
//            String assistantType = realmData.get(i).getAssistantType();
//            String mTy = realmData.get(i).getType();
//            if (mTy != null && assistantType != null&&mTy.equals("2") && assistantType.equals("2")) {
//            }else {
//                mList.add(realmData.get(i));
//            }
//        }
        mIsRefreshing=true;
    }
    private void twoAdapter() {
        if (msgAdapter==null) {
            msgAdapter = new MsgAdapter(getActivity(), mList, mItemTouchListener);
            mRecyclerView.setAdapter(msgAdapter);
//            上拉加载
            msgAdapter.setUpFetchListener(new BaseQuickAdapter.UpFetchListener() {
                @Override
                public void onUpFetch() {

                }
            });
        }
        if(msgAdapter!=null)
        {
            msgAdapter.notifyDataSetChanged();
            if (mRecyclerView != null)
                mRecyclerView.smoothScrollToPosition(0);
            sendBroadcast();
        }
    }
    private void initAdapter() {
        if (msgAdapter==null) {
            msgAdapter = new MsgAdapter(getActivity(), mList, mItemTouchListener);
            mRecyclerView.setAdapter(msgAdapter);
            msgAdapter.notifyDataSetChanged();
            mRecyclerView.smoothScrollToPosition(0);
            sendBroadcast();
            msgAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    if(msgAdapter.getSwipeLayoutIsOpen())
                    {
                        // 关闭删除按钮
                        msgAdapter.colseBGASwipeItemLayout();
                        return;
                    }
                    item = (CusHomeRealmData) adapter.getItem(position);
                    switch (view.getId()) {
                        case R.id.item_msg_re:
                            if (item.getTotalId().equals(AppConfig.GroupAssistant)) {
                                // 跳转群助手
                                IntentUtils.JumpTo(GroupAssistantActivity.class);
                            } else {
                                clickItem(adapter,position);
                            }
                            break;
                        //点击编辑，弹出聊天窗口
                        case R.id.item_tv_click_ok:
                            type = item.getType();
                            FragmentManager childFragmentManager = getChildFragmentManager();
                            MyDialogFragment myDialogFragment = new MyDialogFragment(item.getFriendId(), type);
                            myDialogFragment.show(childFragmentManager, "show");
                            break;
                    }

                }
            });
        }else
        {
//            if (RecyclerView.SCROLL_STATE_IDLE!=mRecyclerView.getScrollState()||!mRecyclerView.isComputingLayout())
//            msgAdapter.notifyDataSetChanged();
        }
    }

    private void clickItem(BaseQuickAdapter adapter,int position) {
        CusHomeRealmData item = (CusHomeRealmData) adapter.getItem(position);
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
            sendWeb(SplitWeb.getSplitWeb().privateSendInterface(item.getFriendId()));
            CusJumpChatData cusJumpChatData = new CusJumpChatData();
            // 点击进入详情后，消息个数清零
            // 好友
            cusJumpChatData.setFriendHeader(item.getHeadImg());
            cusJumpChatData.setFriendId(item.getFriendId());
//                  cusJumpChatData.setFriendName(name);
            cusJumpChatData.setFriendName(item.getNickName());
            cusJumpChatData.setFriendRemarkName(remarkName);
            cusJumpChatData.setFriendGroupName(groupingName);
            IntentUtils.JumpToHaveObj(ChatActivity.class, Constants.KEY_FRIEND_HEADER, cusJumpChatData);

        }else {
            sendWeb(SplitWeb.getSplitWeb().groupSendInterface(item.getFriendId()));

            CusJumpGroupChatData cusJumpGroupChatData = new CusJumpGroupChatData();
            //跳转群组
            cusJumpGroupChatData.setGroupId(item.getFriendId());
            cusJumpGroupChatData.setGroupName(item.getNickName());
            MyLog.e("msgFragment","-----------------------------------" + item.getNickName() + "++++++" + remarkName);
            cusJumpGroupChatData.setIdentifyType(identityType);
            cusJumpGroupChatData.setCardName(carteName);
            cusJumpGroupChatData.setDisturbType(disturbType);
//                            cusJumpGroupChatData.setGroupName(item.getNickName());
            IntentUtils.JumpToHaveObj(ChatGroupActivity.class, Constants.KEY_FRIEND_HEADER, cusJumpGroupChatData);
        }
    }
    CusHomeRealmData item;
    //订阅方法，接收到服务器返回事件处理
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BusDataGroupOrFriend messageInfo) {
        if (!StrUtils.isEmpty(messageInfo.getMsg())&&item!=null) {
            if (type.equals("1")) {
                BaseApplication.getApp().sendData(SplitWeb.getSplitWeb().privateSend(item.getFriendId(), messageInfo.getMsg(), "1", TimeUtil.getTime()));
//                BaseApplication.getApp().sendData(SplitWeb.getSplitWeb().privateSend(item.getFriendId(), messageInfo.getMsg(), "1", TimeUtil.getTime()));
            }
            else {
                BaseApplication.getApp().sendData(SplitWeb.getSplitWeb().groupSend(item.getFriendId(), messageInfo.getMsg(), "1", TimeUtil.getTime()));
//                BaseApplication.getApp().sendData(SplitWeb.getSplitWeb().groupSend(item.getFriendId(), messageInfo.getMsg(), "1", TimeUtil.getTime()));
            }
        }
    }
    String type="1";
    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            realmHelper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (mReceiver!=null)
                getActivity().unregisterReceiver(mReceiver);
//            if (mRefreshBroadcastReceiver!=null)
//                getActivity().unregisterReceiver(mRefreshBroadcastReceiver);
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

    String remarkName;
    String groupingName;
    String disturbType;
    String carteName;
    String identityType;
    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method){
            case "privateSendInterface":
                CusJumpChatData cusJumpChatData = new CusJumpChatData();
                DataChatPop dataChatPop = JSON.parseObject(responseText, DataChatPop.class);
                DataChatPop.RecordBean recordBean = dataChatPop.getRecord();
                if (recordBean != null){
//                  name = StrUtils.isEmpty(recordBean.getRemarkName()) ? recordBean.getNickName() : recordBean.getRemarkName();
                    remarkName = recordBean.getRemarkName();
                    groupingName = recordBean.getGroupName();
                }
                break;
            case "groupSendInterface":
                CusJumpGroupChatData cusJumpGroupChatData = new CusJumpGroupChatData();
                DataChatGroupPop dataChatGroupPop = JSON.parseObject(responseText, DataChatGroupPop.class);
                DataChatGroupPop.RecordBean groupRecordBean = dataChatGroupPop.getRecord();
                if (groupRecordBean != null){
                    DataChatGroupPop.RecordBean.GroupDetailInfoBean groupDetailInfoBean = groupRecordBean.getGroupDetailInfo();
                    if (groupDetailInfoBean != null){
                        DataChatGroupPop.RecordBean.GroupDetailInfoBean.GroupInfoBean groupInfoBean = groupDetailInfoBean.getGroupInfo();
                        DataChatGroupPop.RecordBean.GroupDetailInfoBean.UserInfoBean userInfoBean = groupDetailInfoBean.getUserInfo();
                        if (groupInfoBean != null){
                            remarkName = groupInfoBean.getGroupName();
                        }
                        if (userInfoBean != null){
                            disturbType = userInfoBean.getDisturbType();
                            carteName = userInfoBean.getCarteName();
                            identityType = userInfoBean.getIdentityType();
                        }
                    }
                }
//                IntentUtils.JumpToHaveObj(ChatGroupActivity.class, Constants.KEY_FRIEND_HEADER, cusJumpGroupChatData);
                break;
        }
    }
}
