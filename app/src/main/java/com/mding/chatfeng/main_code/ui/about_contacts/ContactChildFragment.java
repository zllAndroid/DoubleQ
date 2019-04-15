package com.mding.chatfeng.main_code.ui.about_contacts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.BaseFragment;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_broadcastreceiver.NetEvent;
import com.mding.chatfeng.about_chat.chat_group.GroupChatDetailsActivity;
import com.mding.chatfeng.about_chat.cus_data_group.CusJumpGroupChatData;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.about_utils.about_file.HeadFileUtils;
import com.mding.chatfeng.about_utils.about_realm.new_home.CusHomeRealmData;
import com.mding.chatfeng.about_utils.about_realm.new_home.RealmHomeHelper;
import com.mding.chatfeng.main_code.ui.about_contacts.about_contacts_adapter.LinkFriendAdapter;
import com.mding.chatfeng.main_code.ui.about_contacts.about_contacts_adapter.LinkGroupAdapter;
import com.mding.chatfeng.main_code.ui.about_contacts.about_custom.LetterBar;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.CusDataLinkFriend;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.RealmMsgInfoTotalHelper;
import com.mding.model.DataLinkGroupList;
import com.mding.model.DataLinkManList;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        typeWho = bundle.getInt("position");
        String text = (String) bundle.get("text");
        aCache =  ACache.get(getActivity());
        realmMsgInfoTotalHelper = new RealmMsgInfoTotalHelper(getActivity());
        realmHelper = new RealmHomeHelper(getActivity());
        if (view==null) {
            if (typeWho == 0) {
//                初始化好友列表
                view = inflater.inflate(R.layout.fragment_friend, container, false);
                initFriendUI(view);
            }
            else if (typeWho == 1)
            {
//                初始化群组列表
                view = inflater.inflate(R.layout.fragment_group,container,false);
                initGroupUI(view);
            }
        }
        initBroc();
        return view;
    }
    private void initBroc() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action.addFriend");
        intentFilter.addAction(AppConfig.LINK_FRIEND_ADD_ACTION);
        intentFilter.addAction(AppConfig.LINK_FRIEND_DEL_ACTION);
        intentFilter.addAction(AppConfig.LINK_GROUP_ADD_ACTION);
        intentFilter.addAction(AppConfig.LINK_GROUP_DEL_ACTION);
        getActivity().registerReceiver(mRefreshBroadcastReceiver, intentFilter);
    }
    private BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action)
            {
                case "action.addFriend":
                    int num = intent.getIntExtra("num",0);
                    if (mTvFriendNews==null)
                    {
                        return;
                    }
                    if (num>0 )
                    {
                        mTvFriendNews.setVisibility(View.VISIBLE);
                        mTvFriendNews.setText(num+"");
                    }else
                        mTvFriendNews.setVisibility(View.INVISIBLE);
                    break;
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
    };

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
    private LinearLayout titleView;
    private LayoutInflater inflater;
    RealmMsgInfoTotalHelper realmMsgInfoTotalHelper;

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
                Intent intent = new Intent();
                intent.putExtra("num", 0);
                intent.setAction("action.addFriend");
                getActivity().sendBroadcast(intent);
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
                    String asString = aCache.getAsString(AppAllKey.FRIEND_DATA);
                    if (!StrUtils.isEmpty(asString))
                    {
                        initDataFriend(asString,false);
//                        return;
                    }
                }
//                预先设置适配器，以便显示头部布局
//                initFriendAdapter();
//                sendWeb(SplitWeb.getFriendList());
                break;
            case 1:
                if (aCache!=null)
                {
                    String asString = aCache.getAsString(AppAllKey.GROUD_DATA);
                    if (!StrUtils.isEmpty(asString))
                    {
                        initDataGroup(asString,false);
//                        return;
                    }
                }
//                initGroupAdapter();
//                sendWeb(SplitWeb.getGroupManage());
                break;
        }
    }
    private void initResume() {
        switch (typeWho)
        {
            case  0:

//                预先设置适配器，以便显示头部布局
//                initFriendAdapter();
                initFriendWs();
                break;
            case 1:
//                initGroupAdapter();
                initGroupWs();
                break;
        }
    }

    private void initGroupWs() {
        if (aCache==null)
            aCache =  ACache.get(getActivity());
        String asString = aCache.getAsString(AppAllKey.GROUD_DATA);
        if (!StrUtils.isEmpty(asString))
        {
            Log.e("initDataGroup",asString);
            initDataGroup(asString,false);
            return;
        }
        sendWeb(SplitWeb.getGroupManage());
    }

    private void initFriendWs() {
        if (aCache==null)
            aCache =  ACache.get(getActivity());
        String asString = aCache.getAsString(AppAllKey.FRIEND_DATA);
        Log.e("FRIEND_DATA","asString="+asString);
        if (!StrUtils.isEmpty(asString))
        {
            initDataFriend(asString,false);
            return;
        }
        sendWeb(SplitWeb.getFriendList());
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
                initDataFriend(responseText,true);
                break;

//            获取群组列表
            case "getGroupManage":
                initDataGroup(responseText,true);
                break;
        }
    }
    /**
     * 好友列表数据处理
     * @param responseText
     */
    DataLinkManList.RecordBean record;
    private void initDataFriend(String responseText,boolean isWs) {
        List<DataLinkManList.RecordBean.FriendListBean> friend_list;
        if (isWs) {
            DataLinkManList dataLinkManList = JSON.parseObject(responseText, DataLinkManList.class);
            record = dataLinkManList.getRecord();
        }else
        {
            record = JSON.parseObject(responseText, DataLinkManList.RecordBean.class);
        }
        friend_list = record.getFriendList();
        if (friend_list.size()>0)
        {
            initFriendRecord( friend_list,isWs);
        }else if (friend_list.size()==0)
        {
            mFriendList.clear();
            if (mlinkFriend!=null)
                mlinkFriend.notifyDataSetChanged();
        }
    }
    //好友record部分的解析
    private void initFriendRecord(List<DataLinkManList.RecordBean.FriendListBean> friend_list,boolean isWs) {
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
                    dealFriendRealm(friend_list, i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            mFriendList.addAll(friend_list);
            if(isWs) {
                String json = JSON.toJSON(record).toString();
                aCache.remove(AppAllKey.FRIEND_DATA);
                aCache.put(AppAllKey.FRIEND_DATA, json);
            }
            dealFriendRequestRealm();
        }
        if (mlinkFriend!=null)
            mlinkFriend.notifyDataSetChanged();
    }

    private void dealFriendRealm(List<DataLinkManList.RecordBean.FriendListBean> friend_list, int i) {
        if (friend_list.get(i).getType().equals("2"))
        {
            List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = friend_list.get(i).getGroupList();
            for (int j=0;j<groupList.size();j++) {
                final String modified = groupList.get(j).getModified();
                final String friendId = groupList.get(j).getUserId();
                final String headImg = groupList.get(j).getHeadImg();
                CusDataLinkFriend cusDataLinkFriend = realmMsgInfoTotalHelper.queryLinkFriend(friendId);
                if (StrUtils.isEmpty(headImg))
                {
                    return;
                }
                if (cusDataLinkFriend!=null) {

                    String time = cusDataLinkFriend.getTime();
                    if ( modified!=null&&!modified.equals(time))
                    {
                        setGlideData(true,true,modified, friendId, headImg);
                    }
//                boolean equals = modified.equals(time);
//                setGlideData(!equals,false,modified, friendId, headImg);
                }else {
                    setGlideData(false,true,modified, friendId, headImg);
                }
//                if (cusDataLinkFriend != null&&!StrUtils.isEmpty(headImg)) {
//                    String time = cusDataLinkFriend.getTime();
//                    boolean equals = modified.equals(time);
//                    setGlideData(!equals,true,modified, friendId, headImg);
//                }
            }
        }
    }

    //    好友适配器
    private void initFriendAdapter() {
        if (mlinkFriend==null) {
            mlinkFriend = new LinkFriendAdapter(getActivity(), mFriendList);
            mExListViewFriend.setAdapter(mlinkFriend);
        }

        mlinkFriend.notifyDataSetChanged();
        mExListViewFriend.setGroupIndicator(null);
        mExListViewFriend.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                if (mFriendList.get(groupPosition).getType().equals("2")) {
                    //设置点击不关闭（不收回）
                    v.setClickable(true);
                    return true;
                }else
                    return false;
            }
        });

        mExListViewFriend.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                DataLinkManList.RecordBean.FriendListBean.GroupListBean groupListBean = mFriendList.get(groupPosition).getGroupList().get(childPosition);
                String userId = mFriendList.get(groupPosition).getGroupList().get(childPosition).getUserId();
                IntentUtils.JumpToHaveOne(FriendDataMixActivity.class,"id",userId);
                return false;
            }
        });
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
    private void initDataGroup(String responseText,boolean isWs) {
        DataLinkGroupList.RecordBean record1;
        if (isWs) {
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
                        dealGroupRealm(group_info_list,i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mGroupList.addAll(group_info_list);
                if(isWs) {
                    String json = JSON.toJSON(record1).toString();
                    aCache.remove(AppAllKey.GROUD_DATA);
                    aCache.put(AppAllKey.GROUD_DATA, json);
                }
                dealGroupRuquest();
            }
            if (mGroupAdapter!=null)
                mGroupAdapter.notifyDataSetChanged();
        }
    }

    private void dealGroupRealm(List<DataLinkGroupList.RecordBean.GroupInfoListBean> group_info_list,int i) {
        if (group_info_list.get(i).getType().equals("2"))
        {
            List<DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean> groupList = group_info_list.get(i).getGroupList();

            for (int j=0;j<groupList.size();j++)
            {
                final String modified = groupList.get(j).getModified();
                final String friendId = groupList.get(j).getGroupOfId();
                final String headImg = groupList.get(j).getHeadImg();
                CusDataLinkFriend cusDataLinkFriend = realmMsgInfoTotalHelper.queryLinkFriend(friendId);
                if (StrUtils.isEmpty(headImg))
                {
                    return;
                }
                if (cusDataLinkFriend!=null) {

                    String time = cusDataLinkFriend.getTime();
                    if ( modified!=null&&!modified.equals(time))
                    {
                        setGlideData(true,false,modified, friendId, headImg);
                    }
//                boolean equals = modified.equals(time);
//                setGlideData(!equals,false,modified, friendId, headImg);
                }else {
                    setGlideData(false,false,modified, friendId, headImg);
                }
//                if (cusDataLinkFriend!=null) {
//                    String time = cusDataLinkFriend.getTime();
//                    if (StrUtils.isEmpty(headImg))
//                    {
//                        return;
//                    }
//                    boolean equals = modified.equals(time);
//                    setGlideData(!equals,false,modified, friendId, headImg);
//                }
            }
        }
    }
    private void setGlideData(final boolean isSame,final boolean isFriend,final String modified, final String friendId, final String headImg) {
        Glide.with(this)
                .load(headImg)
                .downloadOnly(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(final File resource, GlideAnimation<? super File> glideAnimation) {
//                                    这里拿到的resource就是下载好的文件，
                        File file = HeadFileUtils.saveImgPath(resource, AppConfig.TYPE_FRIEND, friendId, modified);
                        if (isSame)
                            realmMsgInfoTotalHelper.updateHeadPath(friendId, file.toString(), headImg, modified);
                        else
                        {
                            CusDataLinkFriend linkFriend = new CusDataLinkFriend();
                            linkFriend.setHeadImg(headImg);
                            linkFriend.setFriendId(friendId);
                            linkFriend.setTime(modified);
                            linkFriend.setImgPath(file.toString());
                            if (isFriend)
                                linkFriend.setWhoType("1");
                            else
                                linkFriend.setWhoType("2");
                            realmMsgInfoTotalHelper.addRealmLinkFriend(linkFriend);
                        }
                    }
                });
    }
    LinkGroupAdapter mGroupAdapter=null;
    private void initGroupAdapter() {
        if (mGroupAdapter==null)
            mGroupAdapter = new LinkGroupAdapter(getActivity(),mGroupList);
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
                }else
                    return false;
            }
        });
        mExListViewGroup.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                String group_name = mGroupList.get(groupPosition).getGroupList().get(childPosition).getNickName();
                DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean groupListBean = mGroupList.get(groupPosition).getGroupList().get(childPosition);
                // 群聊
//                CusJumpGroupChatData cusJumpChatData = new CusJumpGroupChatData();
//                cusJumpChatData.setGroupId(groupListBean.getGroupOfId());
//                cusJumpChatData.setGroupName(groupListBean.getNickName());
//
//                final CusHomeRealmData cusHomeRealmData = new CusHomeRealmData();
//                cusHomeRealmData.setHeadImg(groupListBean.getHeadImg());
//                cusHomeRealmData.setFriendId(groupListBean.getGroupOfId());
//                cusHomeRealmData.setNickName(groupListBean.getNickName());
//                cusHomeRealmData.setNum(0);
//
//                CusHomeRealmData cusHomeRealmData1 = realmHelper.queryAllRealmChat(groupListBean.getGroupOfId());
//                if (cusHomeRealmData1!=null)
//                {
//                    realmHelper.updateNumZero(groupListBean.getGroupOfId());
//                }else
//                {
//                    realmHelper.addRealmMsgQun(cusHomeRealmData);
//                }
//                IntentUtils.JumpToHaveObj(ChatGroupActivity.class, Constants.KEY_FRIEND_HEADER, cusJumpChatData);

                IntentUtils.JumpToHaveTwo(GroupChatDetailsActivity.class, AppConfig.GROUP_ID, groupListBean.getGroupOfId(),AppConfig.IS_CHATGROUP_TYPE,"FALSE");
//                        ToastUtil.show("组别"+(groupPosition+1)+"点击了子"+group_name);
                return false;
            }
        });
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
        runnable = new  Runnable()
        {
            public void run()
            {
                tv_abc.setVisibility(View.INVISIBLE);
            }
        };
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
        runnable = new  Runnable()
        {
            public void run()
            {
                tv_abc_group.setVisibility(View.INVISIBLE);
            }
        };
        mManaBar.setonTouchLetterListener(new LetterBar.onTouchLetterListener() {
            @Override
            public void onTouchDown(String letter) {
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
            }
        });
    }
    private void initGroupUI(View view) {
        inflater = LayoutInflater.from(getActivity());
        titleView = (LinearLayout) inflater.inflate(R.layout.item_linkman_header, null);//得到头部的布局
        mManaBar = (LetterBar) view.findViewById(R.id.mana_let);
        mExListViewGroup = view.findViewById(R.id.frag_listview_mana);
        tv_abc_group = (TextView) view.findViewById(R.id.tv_abc);
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
