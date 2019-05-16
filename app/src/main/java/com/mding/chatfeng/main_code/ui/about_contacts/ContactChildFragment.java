package com.mding.chatfeng.main_code.ui.about_contacts;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_application.BaseApplication;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.BaseFragment;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_broadcastreceiver.LinkChangeEvent;
import com.mding.chatfeng.about_broadcastreceiver.MainTabNumEvent;
import com.mding.chatfeng.about_broadcastreceiver.NetEvent;
import com.mding.chatfeng.about_chat.chat_group.GroupChatDetailsActivity;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.about_utils.MyJsonUtils;
import com.mding.chatfeng.about_utils.about_realm.new_home.RealmHomeHelper;
import com.mding.chatfeng.main_code.ui.about_contacts.about_contacts_adapter.LinkFriendAdapter;
import com.mding.chatfeng.main_code.ui.about_contacts.about_contacts_adapter.LinkGroupAdapter;
import com.mding.chatfeng.main_code.ui.about_contacts.about_custom.LetterBar;
import com.mding.model.DataLinkGroupList;
import com.mding.model.DataLinkManList;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 联系人
 */
public class ContactChildFragment extends BaseFragment {
    private ExpandableListView mExListViewFriend;
    private ExpandableListView mExListViewGroup;
    public ContactChildFragment() {}
    View view;
    int typeWho;
    RealmHomeHelper realmHelper;
    ACache aCache;
    boolean isFriend =true;
    boolean isGroup =true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        typeWho = bundle.getInt("position");
        String text = (String) bundle.get("text");
        aCache =  ACache.get(getActivity());
        realmHelper = new RealmHomeHelper(getActivity());
        if (view==null) {
            if (typeWho == 0) {
                if (isFriend) {
//                初始化好友列表
                    view = inflater.inflate(R.layout.fragment_friend, container, false);
                    initFriendUI(view);
//                    isFriend =false;
                }
            }
            else if (typeWho == 1)
            {
                if (isGroup) {
                    MyLog.e("ContactChildFragment","isGroup="+isGroup);
//                初始化群组列表
                    view = inflater.inflate(R.layout.fragment_group, container, false);
                    initGroupUI(view);
//                    isGroup=false;
                }
            }
        }
        return view;
    }
    //   清除abc显示问题
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ModelbyMissABC modelbyMissABC){
        if (modelbyMissABC!=null)
        {
            int missWho = modelbyMissABC.getMissWho();
            switch (missWho)
            {
                case 0://好友列表界面时，清除群列表的字母，下同
                    if (tv_abc_group!=null)
                        tv_abc_group.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    if (tv_abc!=null)
                        tv_abc.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventLinkThread(LinkChangeEvent event) {
        String type = event.getType();
        switch (type)
        {
            case AppConfig.LINK_FRIEND_ADD_ACTION:
                initFriendWs();
                break;
            case AppConfig.LINK_FRIEND_DEL_ACTION:
                initFriendWs();
                break;
            case AppConfig.LINK_GROUP_ADD_ACTION:
                initGroupWs();
                break;
            case AppConfig.LINK_GROUP_DEL_ACTION:
                initGroupWs();
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MainTabNumEvent event) {
        int num = event.getNum();
        String type = event.getType();
        if (type.equals(AppConfig.MAIN_TAB_TWO)) {
            if (mTvFriendNews == null) {
                return;
            }
            if (num > 0) {
                mTvFriendNews.setVisibility(View.VISIBLE);
                mTvFriendNews.setText(num + "");
            } else
                mTvFriendNews.setVisibility(View.INVISIBLE);
        }
    }
    private BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action)
            {
                case AppConfig.LINK_FRIEND_ADD_ACTION:
//                    initFriendWs();
                    MsgSendHandler(AppConfig.LINKMAN_FRIEND_WS);
                    break;
                case AppConfig.LINK_FRIEND_DEL_ACTION:
//                    initFriendWs();
                    MsgSendHandler(AppConfig.LINKMAN_FRIEND_WS);
                    break;
                case AppConfig.LINK_GROUP_ADD_ACTION:
//                    initGroupWs();
                    MsgSendHandler(AppConfig.LINKMAN_GROUP_WS);
                    break;
                case AppConfig.LINK_GROUP_DEL_ACTION:
//                    initGroupWs();
                    MsgSendHandler(AppConfig.LINKMAN_GROUP_WS);
                    break;
            }
        }
    };
    public  void MsgSendHandler(int mWhat)
    {
        Message message = new Message();
        message.what=mWhat;
        mHandlers.sendMessage(message);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            if (mRefreshBroadcastReceiver!=null)
                getActivity().unregisterReceiver(mRefreshBroadcastReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TextView tv_abc;
    private TextView tv_abc_group;
    private LetterBar letterBar;
    private LetterBar mManaBar;
    private Runnable runnable;
    //    private Runnable runnableFriend=null;
//    private Runnable runnableGroup=null;
    private LinearLayout titleView;
    private LayoutInflater inflater;
//    RealmMsgInfoTotalHelper realmMsgInfoTotalHelper;

    TextView mTvFriendNews;
    // 初始化好友列表
    private void initFriendUI(View view) {
        letterBar = (LetterBar) view.findViewById(R.id.frag_letter_friend);
        tv_abc = (TextView) view.findViewById(R.id.tv_abc);
        inflater = LayoutInflater.from(getActivity());
        mExListViewFriend = view.findViewById(R.id.frag_exlist_friend);
        titleView = (LinearLayout) inflater.inflate(R.layout.item_friend_header, null);//得到头部的布局
        mExListViewFriend.addHeaderView(titleView);//添加头部
        mTvFriendNews = titleView.findViewById(R.id.link_tv_friend_news);
        int num = (int)SPUtils.get(getActivity(), AppConfig.LINKMAN_FRIEND_NUM, 0);
        if (num>0)
        {
            if (mTvFriendNews!=null) {
                mTvFriendNews.setVisibility(View.VISIBLE);
                mTvFriendNews.setText(num + "");
            }
        }else {
            if (mTvFriendNews!=null) {
                mTvFriendNews.setVisibility(View.INVISIBLE);
            }
        }
        titleView.findViewById(R.id.link_lin_news).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.put(getActivity(), AppConfig.LINKMAN_FRIEND_NUM,0);
                EventBus.getDefault().post(new MainTabNumEvent(0,AppConfig.MAIN_TAB_TWO));
                IntentUtils.JumpTo(NoticeActivity.class);
            }
        });
        titleView.findViewById(R.id.link_lin_fenzu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.JumpToHaveOne(GroupManageActivity.class,GroupManageActivity.ManagerType,"1");
            }
        });
//        初始化右边导航字母
        initABC();
//        初始化好友分组适配器
        initFriendAdapter();
//        广播
        initFriendWs();
    }
    //    这是好友数据源
    List<DataLinkManList.RecordBean.FriendListBean> mFriendList=new ArrayList<>();
    //    好友适配器
    LinkFriendAdapter mlinkFriend=null;
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEventMainThread(NetEvent event) {
        if (!event.isNet)
        {
//            无网络默认显示缓存
            initNoNet();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        //判断是好友还是群组页面，0好友，1群组
//        initResume();
    }
    private void initNoNet() {
        switch (typeWho)
        {
            case  0:
//                判断是否缓存，是则取出使用，否则去请求
                if (aCache!=null)
                {
                    String asString = BaseApplication.getaCache().getAsString(AppAllKey.FRIEND_DATA);
                    if (!StrUtils.isEmpty(asString))
                    {
                        sendTask(asString, "no");
                    }
                }
//                预先设置适配器，以便显示头部布局
                break;
            case 1:
                if (aCache!=null)
                {
                    String asString = BaseApplication.getaCache().getAsString(AppAllKey.GROUD_DATA);
                    if (!StrUtils.isEmpty(asString))
                    {
                        sendGroupTask(asString, "no");
                    }
                }
                break;
        }
    }
    LinkManLongTimeTask myTask=null;
    GroupLongTimeTask myGroupTask=null;
    @SuppressLint("HandlerLeak")
    private void initGroupWs() {
        if (mHandlers==null)
            mHandlers = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg != null)
                        initHanderMsg(msg);
                }
            };
        String asString = BaseApplication.getaCache().getAsString(AppAllKey.GROUD_DATA);
        if (!StrUtils.isEmpty(asString))
        {
            sendGroupTask(asString, "no");
        }else
            sendWeb(SplitWeb.getSplitWeb().getGroupManage());
    }
    public Handler mHandlers=null;
    @SuppressLint("HandlerLeak")
    private void initFriendWs() {
        if (mHandlers==null)
            mHandlers = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg != null)
                        initHanderMsg(msg);
                }
            };
        String asString = BaseApplication.getaCache().getAsString(AppAllKey.FRIEND_DATA);
        if (!StrUtils.isEmpty(asString))
        {
            sendTask(asString, "no");
        }else
            sendWeb(SplitWeb.getSplitWeb().getFriendList());
    }

//    @Override
//    protected void onFragmentHandleMessage(Message msg) {
//        super.onFragmentHandleMessage(msg);
//        initHanderMsg(msg);
//
//    }

    private void initHanderMsg(Message msg) {
        switch (msg.what)
        {
            case AppConfig.LINKMAN_FRIEND:
                try {
                    initFriendAdapter();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                AppConfig.logs("-----------------------主线程----------好友列表接收----------------------");
                myTask=null;
                break;
            case AppConfig.LINKMAN_GROUP:
                try {

                    initGroupAdapter();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                AppConfig.logs("-----------------------主线程-----------群列表接收----------------------");
                myGroupTask=null;
                break;
            case AppConfig.LINKMAN_FRIEND_WS:
                initFriendWs();
                break;
            case AppConfig.LINKMAN_GROUP_WS:
                initGroupWs();
                break;
        }
    }

    private class LinkManLongTimeTask extends AsyncTask<String,Void,String>
    {
        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);
            Message message = new Message();
            message.what=AppConfig.LINKMAN_FRIEND;
            mHandlers.sendMessage(message);

//            initFriendAdapter();
            MyLog.e("LongTimeTask","----------------------oonPostExecute---------好友列表--接收---------------------");

            myTask=null;
            return;
        }
        @Override
        protected String doInBackground(String... lists) {
            MyLog.e("LongTimeTask","---------------------doInBackground--------好友列表---执行--------------------");
            initDataFriend(lists[0],lists[1]);
            return null;
        }
    }
    private class GroupLongTimeTask extends AsyncTask<String,Void,String>
    {
        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);
//            initGroupAdapter();
            Message message = new Message();
            message.what=AppConfig.LINKMAN_GROUP;
            mHandlers.sendMessage(message);
            MyLog.e("LongTimeTask","----------------------onPostExecute-----------群列表接收---------------------");

            myGroupTask=null;
            return;
        }
        @Override
        protected String doInBackground(String... lists) {
            MyLog.e("LongTimeTask","----------------------doInBackground-----------群列表执行---------------------");
            initDataGroup(lists[0],lists[1]);
            return null;
        }
    }
    //群组数据
    public static List<DataLinkGroupList.RecordBean.GroupInfoListBean> mGroupList = new ArrayList<>();
    /**
     * websocket 数据返回处理
     * @param responseText
     */
    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method)
        {
//            获取好友列表
            case "getFriendList":
                sendTask(responseText, USE_WS);
                break;

//            获取群组列表
            case "getGroupManage":
                sendGroupTask(responseText, USE_WS);
                break;
        }
    }

    private void sendGroupTask(String responseText, String useWs) {
        if (myGroupTask == null) {
            myGroupTask = new GroupLongTimeTask();
            myGroupTask.execute(responseText, useWs);
        }
    }

    private void sendTask(String responseText, String useWs) {
        if (myTask == null) {
            myTask = new LinkManLongTimeTask();
            myTask.execute(responseText, useWs);
        }
    }

    /**
     * 好友列表数据处理
     * @param responseText
     */

    private void initDataFriend(String responseText,String isWs) {
        DataLinkManList.RecordBean record;
        List<DataLinkManList.RecordBean.FriendListBean> friend_list;
        if (isWs.equals(USE_WS)) {
            DataLinkManList dataLinkManList = JSON.parseObject(responseText, DataLinkManList.class);
            record = dataLinkManList.getRecord();
        }else
        {
            record = JSON.parseObject(responseText, DataLinkManList.RecordBean.class);
        }
        friend_list = record.getFriendList();
        if (friend_list.size()>0)
        {
            initFriendRecord(record,friend_list,isWs);
        }else if (friend_list.size()==0)
        {
            mFriendList.clear();
        }
    }


    //好友record部分的解析
    private void initFriendRecord(DataLinkManList.RecordBean record,List<DataLinkManList.RecordBean.FriendListBean> friend_list,String isWs) {
//        final List<DataLinkManList.RecordBean.FriendListBean> friend_list = record.getFriendList();
        mFriendList.clear();
        if (friend_list.size()>0)
        {
            try {
                for (int i = 0; i<friend_list.size(); i++)
                {
                    String userId = friend_list.get(i).getGroupList().get(0).getUserId();
                    String groupName = friend_list.get(i).getGroupName();
                    if (friend_list.get(i).getType().equals("1"))
                    {
                        if (StrUtils.isEmpty(userId)) {
                            friend_list.get(i).getGroupList().remove(0);
                        }
                        if (StrUtils.isEmpty(groupName)) {
                            friend_list.remove(i);
                        }
                    }
//                    dealFriendRealm(friend_list, i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            mFriendList.addAll(friend_list);
            if(isWs.equals(USE_WS)) {
                DataLinkManList.RecordBean recordBean = new DataLinkManList.RecordBean();
                recordBean.setFriendList(friend_list);
                String json = MyJsonUtils.toChangeJson(recordBean);
                aCache.remove(AppAllKey.FRIEND_DATA);
                aCache.put(AppAllKey.FRIEND_DATA, json);
            }
//            dealFriendRequestRealm();
        }
//        if (mlinkFriend!=null)
//            mlinkFriend.notifyDataSetChanged();
    }
    public  static  final String USE_WS="yes";

    //    好友适配器
    private void initFriendAdapter() {
        if (mlinkFriend==null) {
            mlinkFriend = new LinkFriendAdapter(getActivity(), mFriendList);
        }
        if (mExListViewFriend!=null)
            mExListViewFriend.setAdapter(mlinkFriend);

        mlinkFriend.notifyDataSetChanged();
        if (mExListViewFriend!=null)
            mExListViewFriend.setGroupIndicator(null);
        if (mExListViewFriend!=null)
            mExListViewFriend.setOnGroupClickListener((parent, v, groupPosition, id) -> {
                if (mFriendList.get(groupPosition).getType().equals("2")) {
                    //设置点击不关闭（不收回）
                    v.setClickable(true);
                    return true;
                }else
                    return false;
            });
        if (mExListViewFriend!=null)
            mExListViewFriend.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
                DataLinkManList.RecordBean.FriendListBean.GroupListBean groupListBean = mFriendList.get(groupPosition).getGroupList().get(childPosition);
                String userId = mFriendList.get(groupPosition).getGroupList().get(childPosition).getUserId();
                IntentUtils.JumpToHaveOne(FriendDataMixActivity.class,"id",userId);
                return false;
            });

        dealFriendRequestRealm();
        mlinkFriend.notifyDataSetChanged();
    }
    private void dealFriendRequestRealm() {
        if (mlinkFriend!=null)
            for(int i = 0; i < mlinkFriend.getGroupCount(); i++) {
                if (mFriendList.get(i).getType().equals("2")) {
                    if (mExListViewFriend!=null)
                        mExListViewFriend.expandGroup(i);
                }
            }
    }

    /**
     * 群组列表数据处理
     * @param responseText
     */
    private void initDataGroup(String responseText,String isWs) {
        DataLinkGroupList.RecordBean record1;
        if (isWs.equals(USE_WS)) {
            DataLinkGroupList dataLinkGroupList = JSON.parseObject(responseText, DataLinkGroupList.class);
            record1 = dataLinkGroupList.getRecord();
        }
        else {
            record1 = JSON.parseObject(responseText, DataLinkGroupList.RecordBean.class);
        }
        if (record1!=null)
        {
            final List<DataLinkGroupList.RecordBean.GroupInfoListBean> group_info_list = record1.getGroupInfoList();
            mGroupList.clear();
            if (group_info_list.size()>0)
            {
                try {
                    for (int i = 0; i<group_info_list.size(); i++)
                    {
                        String userId = group_info_list.get(i).getGroupList().get(0).getGroupOfId();
                        String groupName = group_info_list.get(i).getGroupName();
                        if (group_info_list.get(i).getType().equals("1"))
                        {
                            if (StrUtils.isEmpty(userId)) {
                                group_info_list.get(i).getGroupList().remove(0);
                            }
                            if (StrUtils.isEmpty(groupName)) {
                                group_info_list.remove(i);
                            }
                        }
//                        dealGroupRealm(group_info_list,i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mGroupList.addAll(group_info_list);
                if(isWs.equals(USE_WS)) {
                    DataLinkGroupList.RecordBean recordBean = new DataLinkGroupList.RecordBean();
                    recordBean.setGroupInfoList(group_info_list);
                    String json = MyJsonUtils.toChangeJson(recordBean);
                    aCache.remove(AppAllKey.GROUD_DATA);
                    aCache.put(AppAllKey.GROUD_DATA, json);
                }
//                dealGroupRuquest();
            }
//            if (mGroupAdapter!=null)
//                mGroupAdapter.notifyDataSetChanged();
        }
    }

    LinkGroupAdapter mGroupAdapter=null;
    private void initGroupAdapter() {
        if (mGroupAdapter==null)
            mGroupAdapter = new LinkGroupAdapter(getActivity(),mGroupList);
        if (mExListViewGroup!=null) {
            mExListViewGroup.setAdapter(mGroupAdapter);
            mExListViewGroup.setGroupIndicator(null);
            mExListViewGroup.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v,
                                            int groupPosition, long id) {
                    if (mGroupList.get(groupPosition).getType().equals("2")) {
                        //设置点击不关闭（不收回）
                        v.setClickable(true);
                        return true;
                    } else
                        return false;
                }
            });
            mExListViewGroup.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
//                String group_name = mGroupList.get(groupPosition).getGroupList().get(childPosition).getNickName();
                DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean groupListBean = mGroupList.get(groupPosition).getGroupList().get(childPosition);

                IntentUtils.JumpToHaveTwo(GroupChatDetailsActivity.class, AppConfig.GROUP_ID, groupListBean.getGroupOfId(), AppConfig.IS_CHATGROUP_TYPE, "FALSE");
//                        ToastUtil.show("组别"+(groupPosition+1)+"点击了子"+group_name);
                return false;
            });
        }
        dealGroupRuquest();

        mGroupAdapter.notifyDataSetChanged();
    }

    private void dealGroupRuquest() {
        if (mGroupAdapter!=null)
            for(int i = 0; i < mGroupList.size(); i++) {
                if (mGroupList.get(i).getType().equals("2"))
                {
                    if (mExListViewGroup!=null)
                        mExListViewGroup.expandGroup(i);
                }
            }
    }

    public String getFirstABC(String pinyin)
    {
        if(pinyin.length()==0)
        {
            return pinyin;
        }
        String upperCase = pinyin.substring(0,1).toUpperCase();
        return upperCase;
    }
    List<String> ABCList= new ArrayList<>();

    public void initABC()
    {
        ABCList.clear();
        for (char i='A';i<='Z';i++)
        {
            ABCList.add(i+"");
        }
        ABCList.add("#");
        runnable = () -> tv_abc.setVisibility(View.INVISIBLE);
        letterBar.setonTouchLetterListener(new LetterBar.onTouchLetterListener() {
            @Override
            public void onTouchDown(String letter) {
                tv_abc.removeCallbacks(runnable);
                tv_abc.setVisibility(View.VISIBLE);
                tv_abc.setText(letter);
                if (tv_abc_group!=null)
                {
                    tv_abc_group.setVisibility(View.INVISIBLE);
                }

                if (letter.equals("⇧"))
                {
                    mExListViewFriend.setSelection(0);
                    return;
                }
                for (int i = 0; i < mFriendList.size(); i++) {
                    //获取所有城市的首字母
                    if (mFriendList.get(i).getType().equals("2")) {
                        String firstLetter = getFirstABC
                                (mFriendList.get(i).getGroupName());
                        if (firstLetter.equals("~"))
                        {
                            firstLetter="#";
                        }
                        if (letter.equals(firstLetter)) {
                            mExListViewFriend.setSelectedGroup(i);
                            break;
                        }
                    }
                }
            }
            @Override
            public void onTouchUp() { tv_abc.postDelayed(runnable, 200); }
        });
    }
    public void initABCByGroup()
    {
        ABCList.clear();
        for (char i='A';i<='Z';i++)
        {
            ABCList.add(i+"");
        }
        ABCList.add("#");
//        if (runnableGroup==null)
        runnable = () -> tv_abc_group.setVisibility(View.INVISIBLE);
        mManaBar.setonTouchLetterListener(new LetterBar.onTouchLetterListener() {
            @Override
            public void onTouchDown(String letter) {
                Log.e("runnable","----------------------->>"+letter);
                tv_abc_group.removeCallbacks(runnable);
                tv_abc_group.setVisibility(View.VISIBLE);
                tv_abc_group.setText(letter);
                if (tv_abc!=null)
                {
                    tv_abc.setVisibility(View.INVISIBLE);
                }
                if (letter.equals("⇧"))
                {
                    mExListViewGroup.setSelection(0);
                    return;
                }
                try {
//                    容易下标越界
                    for (int i = 0; i < mGroupList.size(); i++) {
                        if (mGroupList.get(i).getType().equals("2")) {
                            //获取所有城市的首字母
                            String firstLetter = getFirstABC
                                    (mGroupList.get(i).getGroupName());
                            if (firstLetter.equals("~"))
                            {
                                firstLetter="#";
                            }
                            if (letter.equals(firstLetter)) {
                                mExListViewGroup.setSelectedGroup(i);
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onTouchUp() {
                tv_abc_group.postDelayed(runnable, 200);
                Log.e("runnable","----------runnable----------------------");
            }
        });
    }
    private void initGroupUI(View view) {
        mManaBar = (LetterBar) view.findViewById(R.id.mana_let);
        mExListViewGroup = view.findViewById(R.id.frag_listview_mana);
        tv_abc_group = (TextView) view.findViewById(R.id.tv_group_abc);

        inflater = LayoutInflater.from(getActivity());
        titleView = (LinearLayout) inflater.inflate(R.layout.item_linkman_header, null);//得到头部的布局
//        mCities_list = (ListView) footView.findViewById(R.id.list_city);
        mExListViewGroup.addHeaderView(titleView);//添加头部

        titleView.findViewById(R.id.link_lin_news).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//跳转到群分组列表
                IntentUtils.JumpTo(NoticeActivity.class);
            }
        });
        titleView.findViewById(R.id.link_lin_fenzu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.JumpToHaveOne(GroupManageActivity.class,GroupManageActivity.ManagerType,"2");
//                IntentUtils.JumpTo(GroupManageActivity.class);
            }
        });
        //显示右边字母列表
        initABCByGroup();
        initGroupAdapter();
        initGroupWs();
//        initAdapterGroup();
    }



}
