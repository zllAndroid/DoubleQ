package com.doubleq.xm6leefunz.main_code.ui.about_contacts;

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
import com.doubleq.model.DataLinkGroupList;
import com.doubleq.model.DataLinkManList;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.BaseFragment;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_broadcastreceiver.NetEvent;
import com.doubleq.xm6leefunz.about_chat.chat_group.ChatGroupActivity;
import com.doubleq.xm6leefunz.about_chat.cus_data_group.CusJumpGroupChatData;
import com.doubleq.xm6leefunz.about_utils.GlideCacheUtil;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.about_file.FilePath;
import com.doubleq.xm6leefunz.about_utils.about_file.HeadFileUtils;
import com.doubleq.xm6leefunz.about_utils.about_realm.RealmLinkManHelper;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.CusHomeRealmData;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmHomeHelper;
import com.doubleq.xm6leefunz.about_utils.about_realm.RealmGroupHelper;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter.LinkFriendAdapter;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_contacts_adapter.LinkGroupAdapter;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_custom.LetterBar;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_link_realm.CusDataLinkFriend;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_link_realm.RealmLinkFriendHelper;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity.ChangeInfoActivity;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.rance.chatui.util.Constants;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;


/**
 * 联系人
 */
public class ContactChildFragment extends BaseFragment {

    private ExpandableListView mListView;
    private ExpandableListView mExListView;
    public ContactChildFragment() {
    }
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
        if (view==null) {
            if (typeWho == 0) {
//                初始化好友列表
                view = inflater.inflate(R.layout.fragment_friend, container, false);
                initHome(view);
            }
            else if (typeWho == 1)
            {
//                初始化群组列表
                view = inflater.inflate(R.layout.fragment_group,container,false);
                initManage(view);
                realmHelper = new RealmHomeHelper(getActivity());
            }
        }
        return view;
    }

    private void initBroc() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action.addFriend");
        getActivity().registerReceiver(mRefreshBroadcastReceiver, intentFilter);
    }
    private BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("action.addFriend"))
            {
                int num = intent.getIntExtra("num",0);
                if (num>0)
                {
                    mTvFriendNews.setVisibility(View.VISIBLE);
                    mTvFriendNews.setText(num+"");
                }else
                    mTvFriendNews.setVisibility(View.INVISIBLE);
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
    private LetterBar letterBar;
    private LetterBar mManaBar;
    private Runnable runnable;
    private LinearLayout titleView;
    private LayoutInflater inflater;
    //    RealmLinkManHelper realmHelper;
    RealmGroupHelper realmGroup;
    RealmLinkFriendHelper realmLinkFriendHelper;

    TextView mTvFriendNews;
    // 初始化好友列表
    private void initHome(View view) {
        realmLinkFriendHelper = new RealmLinkFriendHelper(getActivity());
//        List<CusDataFriendRealm> cusDataFriendRealms = realmHelper.queryAllRealmMsg();
        letterBar = (LetterBar) view.findViewById(R.id.frag_letter_friend);
        tv_abc = (TextView) view.findViewById(R.id.tv_abc);
        inflater = LayoutInflater.from(getActivity());
        mListView = view.findViewById(R.id.frag_exlist_friend);
        titleView = (LinearLayout) inflater.inflate(R.layout.item_friend_header, null);//得到头部的布局
        mListView.addHeaderView(titleView);//添加头部
        mTvFriendNews = titleView.findViewById(R.id.link_tv_friend_news);
        int num = (int)SPUtils.get(getActivity(), AppConfig.LINKMAN_FRIEND_NUM, 0);
        if (num>0)
        {
            mTvFriendNews.setVisibility(View.VISIBLE);
            mTvFriendNews.setText(num+"");
//            Intent intent = new Intent();
//            intent.putExtra("num", num );
//            intent.setAction("action.addFriend");
//            getActivity().sendBroadcast(intent);
        }else
            mTvFriendNews.setVisibility(View.INVISIBLE);
        titleView.findViewById(R.id.link_lin_news).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.put(getActivity(), AppConfig.LINKMAN_FRIEND_NUM,0);
//                mTvFriendNews.setVisibility(View.INVISIBLE);
//                int num = (int)SPUtils.get(getActivity(), AppConfig.LINKMAN_FRIEND_NUM, 0);
//                SPUtils.put(getActivity(),AppConfig.LINKMAN_FRIEND_NUM,num+1);
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
        initBroc();
    }
    //    这是好友数据源
    List<DataLinkManList.RecordBean.FriendListBean> mFriendList=new ArrayList<>();
    //    好友适配器
    LinkFriendAdapter mlinkFriend=null;
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEventMainThread(NetEvent event) {
        if (!event.isNet)
        {
            initNoNet();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        //判断是好友还是群组页面，0好友，1群组
        initResume();
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
                        initDataGroup(asString);
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
                initFriendAdapter();

//                if (mlinkFriend==null)
//                 mlinkFriend = new LinkFriendAdapter(getActivity(),mFriendList);
//                 mListView.setAdapter(mlinkFriend);
//                 mlinkFriend.notifyDataSetChanged();
//                initFriendAdapter(friend_list);
                sendWeb(SplitWeb.getFriendList());
                break;
            case 1:
//                if (aCache!=null)
//                {
//                    String asString = aCache.getAsString(AppAllKey.GROUD_DATA);
//                    if (!StrUtils.isEmpty(asString))
//                    {
//                        initDataGroup(asString);
//                        return;
//                    }
//                }
                initGroupAdapter();
                sendWeb(SplitWeb.getGroupManage());
                break;
        }
    }

    //群组数据
    List<DataLinkGroupList.RecordBean.GroupInfoListBean> mGroupList = new ArrayList<>();
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
                DataLinkManList dataLinkManList = JSON.parseObject(responseText, DataLinkManList.class);
                String verificationMD5Type = dataLinkManList.getVerificationMD5Type();
                //                判断是否缓存，是则取出使用，否则去请求
//                if (aCache!=null)
//                {
//                    String asString = aCache.getAsString(AppAllKey.FRIEND_DATA);
//
//                    if (!StrUtils.isEmpty(asString)&&verificationMD5Type.equals(""))
//                    {
//                        initDataFriend(asString,false);
//                        return;
//                    }
//                }
                initDataFriend(responseText,true);
                break;

//            获取群组列表
            case "getGroupManage":
                initDataGroup(responseText);
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
              friend_list = record.getFriendList();
        }else
        {
             friend_list = JSON.parseArray(responseText, DataLinkManList.RecordBean.FriendListBean.class);
//            JSON.parseArray()
        }
        if (friend_list.size()>0)
        {
            initFriendRecord( friend_list,isWs);
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
            if (mlinkFriend!=null)
                mlinkFriend.notifyDataSetChanged();
        }
    }

    private void dealFriendRealm(List<DataLinkManList.RecordBean.FriendListBean> friend_list, int i) {
        if (friend_list.get(i).getType().equals("2"))
        {
            List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = friend_list.get(i).getGroupList();

            for (int j=0;j<groupList.size();j++)
            {
                final String modified = groupList.get(j).getModified();
                final String friendId = groupList.get(j).getUserId();
                final String headImg = groupList.get(j).getHeadImg();
//                            GlideCacheUtil.getInstance().clearImageAllCache(getActivity());
//                            List<String> fileName = FilePath.getFilesAllName(FilePath.getLinkImgPath());
//                            if (fileName!=null&&fileName.size()>0)
//                            {
//                                String path=fileName.get(fileName.size()-1);
//
//                            }
//                            boolean b = realmLinkFriendHelper.queryIsLinkFriend(friendId);
                CusDataLinkFriend cusDataLinkFriend = realmLinkFriendHelper.queryLinkFriend(friendId);
                if (cusDataLinkFriend!=null)
                {
                    String time = cusDataLinkFriend.getTime();
                    if (!modified.equals(time))
                    {
                        if (!StrUtils.isEmpty(headImg))
                            Glide.with(this)
                                    .load(headImg)
                                    .downloadOnly(new SimpleTarget<File>() {
                                        @Override
                                        public void onResourceReady(final File resource, GlideAnimation<? super File> glideAnimation) {
//                                    这里拿到的resource就是下载好的文件，
                                            File file = HeadFileUtils.saveImgPath(resource, AppConfig.TYPE_FRIEND,friendId,modified);
                                            realmLinkFriendHelper.updateHeadPath(friendId,file.toString(),headImg,modified);
                                        }
                                    });
                    }
                }else {
                    if (!StrUtils.isEmpty(headImg))
                        Glide.with(this)
                                .load(headImg)
                                .downloadOnly(new SimpleTarget<File>() {
                                    @Override
                                    public void onResourceReady(final File resource, GlideAnimation<? super File> glideAnimation) {
//                                    这里拿到的resource就是下载好的文件，
                                        File file = HeadFileUtils.saveImgPath(resource,AppConfig.TYPE_FRIEND,friendId,modified);
                                        CusDataLinkFriend linkFriend = new CusDataLinkFriend();
                                        linkFriend.setHeadImg(headImg);
                                        linkFriend.setFriendId(friendId);
                                        linkFriend.setTime(modified);
                                        linkFriend.setImgPath(file.toString());
                                        linkFriend.setWhoType("1");
                                        realmLinkFriendHelper.addRealmLinkFriend(linkFriend);
                                    }
                                });
                }
            }
        }
    }

    //    好友分组适配器
    private void initFriendAdapter() {
        if (mlinkFriend==null)
            mlinkFriend = new LinkFriendAdapter(getActivity(),mFriendList);
        mListView.setAdapter(mlinkFriend);
        mlinkFriend.notifyDataSetChanged();
        mListView.setGroupIndicator(null);
        mListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
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

        mListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                DataLinkManList.RecordBean.FriendListBean.GroupListBean groupListBean = mFriendList.get(groupPosition).getGroupList().get(childPosition);

                String userId = mFriendList.get(groupPosition).getGroupList().get(childPosition).getUserId();
                IntentUtils.JumpToHaveOne(FriendDataActivity.class,"id",userId);
                return false;
            }
        });
    }
    private void dealFriendRequestRealm() {
//        realmHelper.deleteAll();
        if (mlinkFriend!=null)
            for(int i = 0; i < mlinkFriend.getGroupCount(); i++) {
                if (mFriendList.get(i).getType().equals("2")) {
                    if (mListView!=null)
                        mListView.expandGroup(i);
//                    List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = mFriendList.get(i).getGroupList();
//                    if (StrUtils.isEmpty(groupList.get(0).getUserId()))
//                    {
//                        return;
//                    }
//                    for (int j=0;j<groupList.size();j++)
//                    {
//                        CusDataFriendRealm cusDataFriendRealm = new CusDataFriendRealm();
//                        cusDataFriendRealm.setGroupId(groupList.get(j).getGroupId());
////                        cusDataFriendRealm.setChart(groupList.get(j).getChart());
//
//                        cusDataFriendRealm.setHeadImg(groupList.get(j).getHeadImg());
//
//                        cusDataFriendRealm.setGroupName(groupList.get(j).getGroupName());
//                        cusDataFriendRealm.setNickName(groupList.get(j).getNickName());
//
//                        cusDataFriendRealm.setMobile(groupList.get(j).getMobile());
//
//                        cusDataFriendRealm.setUserId(groupList.get(j).getUserId());
//
//                        cusDataFriendRealm.setWxSno(groupList.get(j).getWxSno());
////                        try {
////                            if (StrUtils.isEmpty(mFriendList.get(i).getGroupList().get(0).getUserId()))
////                                mFriendList.get(i).getGroupList().remove(i);
////                        } catch (Exception e) {
////                            e.printStackTrace();
////                        }
////                    realmHelper.addFriend(cusDataFriendRealm);
//                    }
                }
            }
    }

    /**
     * 群组列表数据处理
     * @param responseText
     */
    private void initDataGroup(String responseText) {
        DataLinkGroupList dataLinkGroupList = JSON.parseObject(responseText, DataLinkGroupList.class);
        DataLinkGroupList.RecordBean record1 = dataLinkGroupList.getRecord();
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
//                for(int i = 0; i <mGroupList.size(); i++) {
//                    if (mGroupList.get(i).getType().equals("2"))
//                    {
//                        if (mExListView!=null)
//                            mExListView.expandGroup(i);
//                    }
//
//                }
//                String json = JSON.toJSON(dataLinkGroupList).toString();
//                aCache.remove(AppAllKey.GROUD_DATA);
//                aCache.put(AppAllKey.GROUD_DATA,json);
                dealGroupRuquest();
                if (mGroupAdapter!=null)
                    mGroupAdapter.notifyDataSetChanged();
            }
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
                CusDataLinkFriend cusDataLinkFriend = realmLinkFriendHelper.queryLinkFriend(friendId);
                if (cusDataLinkFriend!=null)
                {
                    String time = cusDataLinkFriend.getTime();
                    if (!modified.equals(time))
                    {
                        if (!StrUtils.isEmpty(headImg))
                            Glide.with(this)
                                    .load(headImg)
                                    .downloadOnly(new SimpleTarget<File>() {
                                        @Override
                                        public void onResourceReady(final File resource, GlideAnimation<? super File> glideAnimation) {
//                                    这里拿到的resource就是下载好的文件，
                                            File file = HeadFileUtils.saveImgPath(resource,AppConfig.TYPE_FRIEND,friendId,modified);
                                            realmLinkFriendHelper.updateHeadPath(friendId,file.toString(),headImg,modified);
                                        }
                                    });
                    }
                }else {
                    if (!StrUtils.isEmpty(headImg))
                        Glide.with(this)
                                .load(headImg)
                                .downloadOnly(new SimpleTarget<File>() {
                                    @Override
                                    public void onResourceReady(final File resource, GlideAnimation<? super File> glideAnimation) {
//                                    这里拿到的resource就是下载好的文件，
                                        File file = HeadFileUtils.saveImgPath(resource,AppConfig.TYPE_FRIEND,friendId,modified);
                                        CusDataLinkFriend linkFriend = new CusDataLinkFriend();
                                        linkFriend.setHeadImg(headImg);
                                        linkFriend.setFriendId(friendId);
                                        linkFriend.setTime(modified);
                                        linkFriend.setImgPath(file.toString());
                                        linkFriend.setWhoType("2");
                                        realmLinkFriendHelper.addRealmLinkFriend(linkFriend);
                                    }
                                });
                }
            }
        }
    }

    LinkGroupAdapter mGroupAdapter=null;
    private void initGroupAdapter() {
        if (mGroupAdapter==null)
            mGroupAdapter = new LinkGroupAdapter(getActivity(),mGroupList);
        mExListView.setAdapter(mGroupAdapter);
        mExListView.setGroupIndicator(null);
        mExListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
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
        mExListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String group_name = mGroupList.get(groupPosition).getGroupList().get(childPosition).getNickName();
                DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean groupListBean = mGroupList.get(groupPosition).getGroupList().get(childPosition);
                // 群聊
                CusJumpGroupChatData cusJumpChatData = new CusJumpGroupChatData();
                cusJumpChatData.setGroupId(groupListBean.getGroupOfId());
                cusJumpChatData.setGroupName(groupListBean.getNickName());

                final CusHomeRealmData cusHomeRealmData = new CusHomeRealmData();
                cusHomeRealmData.setHeadImg(groupListBean.getHeadImg());
                cusHomeRealmData.setFriendId(groupListBean.getGroupOfId());
                cusHomeRealmData.setNickName(groupListBean.getNickName());
                cusHomeRealmData.setNum(0);
//            realmHelper.updateNum(record.getFriendsId());
                realmHelper.addRealmMsgQun(cusHomeRealmData);
                IntentUtils.JumpToHaveObj(ChatGroupActivity.class, Constants.KEY_FRIEND_HEADER, cusJumpChatData);
//                        ToastUtil.show("组别"+(groupPosition+1)+"点击了子"+group_name);
                return false;
            }
        });
        mGroupAdapter.notifyDataSetChanged();
    }

    private void dealGroupRuquest() {
//        realmGroup.deleteAll();
        if (mGroupAdapter!=null)
            for(int i = 0; i < mGroupList.size(); i++) {
                if (mGroupList.get(i).getType().equals("2"))
                {
                    if (mExListView!=null)
                        mExListView.expandGroup(i);
                }
//                List<DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean> groupList = mGroupList.get(i).getGroupList();
//                if (StrUtils.isEmpty(groupList.get(0).getGroupOfId()))
//                {
//                    return;
//                }
//                for (int j=0;j<groupList.size();j++)
//                {
//                    CusDataGroupRealm cusDataGroupRealm = new CusDataGroupRealm();
//                    cusDataGroupRealm.setGroupId(groupList.get(j).getGroupOfId());
//                    cusDataGroupRealm.setChart(groupList.get(j).getGroupName());
//
//                    cusDataGroupRealm.setHeadImg(groupList.get(j).getHeadImg());
//
//                    cusDataGroupRealm.setGroupName(groupList.get(j).getGroupName());
//
//                    cusDataGroupRealm.setNickName(groupList.get(j).getNickName());
//
////                cusDataGroupRealm.setMobile(groupList.get(j).getMobile());
////
////                cusDataGroupRealm.setUserId(groupList.get(j).getUserId());
////
////                cusDataGroupRealm.setWxSno(groupList.get(j).getWxSno());
////                    try {
////                        if (StrUtils.isEmpty(mGroupList.get(i).getGroupList().get(0).getGroupOfId()))
////                            mGroupList.get(i).getGroupList().remove(0);
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }
////                realmGroup.addFriend(cusDataGroupRealm);
//                }
            }
    }

    public String getFirstABC(String pinyin)
    {
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
            public void onTouuchDown(String letter) {
                tv_abc.removeCallbacks(runnable);
                tv_abc.setVisibility(View.VISIBLE);
                tv_abc.setText(letter);
                if (letter.equals("⇧"))
                {
                    mListView.setSelection(0);
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
                            mListView.setSelectedGroup(i);
                            break;
                        }
                    }
                }
            }
            @Override
            public void onTouchUp() {
                tv_abc.postDelayed(runnable, 1000);
            }
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
                tv_abc.setVisibility(View.INVISIBLE);
            }
        };
        mManaBar.setonTouchLetterListener(new LetterBar.onTouchLetterListener() {
            @Override
            public void onTouuchDown(String letter) {
                tv_abc.removeCallbacks(runnable);
                tv_abc.setVisibility(View.VISIBLE);
                tv_abc.setText(letter);
                if (letter.equals("⇧"))
                {
                    mExListView.setSelection(0);
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
                                mExListView.setSelectedGroup(i);
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
                tv_abc.postDelayed(runnable, 1000);
            }
        });
    }

    private void initManage(View view) {
        realmGroup= new RealmGroupHelper(getActivity());
        inflater = LayoutInflater.from(getActivity());
        titleView = (LinearLayout) inflater.inflate(R.layout.item_linkman_header, null);//得到头部的布局
        mManaBar = (LetterBar) view.findViewById(R.id.mana_let);
        mExListView = view.findViewById(R.id.frag_listview_mana);
        tv_abc = (TextView) view.findViewById(R.id.tv_abc);
//        mCities_list = (ListView) footView.findViewById(R.id.list_city);
        mExListView.addHeaderView(titleView);//添加头部

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
//        initAdapterGroup();
    }



}
