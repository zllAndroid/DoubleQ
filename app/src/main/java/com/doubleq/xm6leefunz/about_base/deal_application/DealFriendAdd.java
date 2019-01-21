package com.doubleq.xm6leefunz.about_base.deal_application;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.doubleq.model.DataLinkGroupList;
import com.doubleq.model.DataLinkManList;
import com.doubleq.model.push_data.DataAboutFriend;
import com.doubleq.model.push_data.DataAboutGroup;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.util.ArrayList;
import java.util.List;

public class DealFriendAdd {
    private  static ACache aCache;
    private  static Context mContext;
    public  static void updateFriendDataByAdd(Context montext,String result)
    {
        mContext=montext;
        DataAboutFriend dataAboutFriend = JSON.parseObject(result, DataAboutFriend.class);
        DataAboutFriend.RecordBean record = dataAboutFriend.getRecord();
        aCache =  ACache.get(mContext);
        if (aCache!=null)
        {
            String asString = aCache.getAsString(AppAllKey.FRIEND_DATA);
            if (!StrUtils.isEmpty(asString)&&record!=null)
            {
                try {
                    initDataFriend(asString,record);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public  static void updateFriendDataBySub(Context montext,String result)
    {
        mContext=montext;
        DataAboutFriend dataAboutFriend = JSON.parseObject(result, DataAboutFriend.class);
        DataAboutFriend.RecordBean record = dataAboutFriend.getRecord();
        aCache =  ACache.get(mContext);
        if (aCache!=null)
        {
            String asString = aCache.getAsString(AppAllKey.FRIEND_DATA);
            if (!StrUtils.isEmpty(asString)&&record!=null)
            {
                try {
                    initDataFriend(asString,record);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void initDataGroupSub(String asString, DataAboutFriend.RecordBean mRecord) {
        DataLinkManList.RecordBean record = JSON.parseObject(asString, DataLinkManList.RecordBean.class);
        if (record==null)
            return;
        friendList = record.getFriendList();
        String chat = mRecord.getChat();
        String mRecordGroupName = mRecord.getGroupName();
        String groupId = mRecord.getGroupId();
        if (friendList.size() > 0) {
            String friendsId = mRecord.getFriendsId();
            for (int i = 0; i < friendList.size(); i++) {
                String type = friendList.get(i).getType();
                String groupName = friendList.get(i).getGroupName();
                List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = friendList.get(i).getGroupList();
                if (type.equals("2")) {
                    if (chat != null && chat.equals(groupName)) {
                        if (groupList.size() == 0) {
//                            return;
                        } else if (groupList.size() == 1) {
                            friendList.remove(i);
                        } else if (groupList.size() > 1) {
                            for (int j = 0; j < groupList.size(); j++) {
                                String groupOfId = groupList.get(j).getUserId();
                                if (friendsId.equals(groupOfId)) {
                                    friendList.get(i).getGroupList().remove(j);
                                }
                            }
                        }
                    }
                }else if (type.equals("1")&&groupId != null && !groupId.equals("0"))
                {
                    if (groupList.size()>0)
                    {
                        if (mRecordGroupName!=null&&mRecordGroupName.equals(groupName)) {
                            for (int h = 0; h < groupList.size(); h++) {
                                String userId = groupList.get(h).getUserId();
                                if (friendsId.equals(userId)) {
                                    friendList.get(i).getGroupList().remove(h);
                                }
                            }
                        }
                    }
                }
            }

//            修改数据保存cache
            DataLinkManList.RecordBean recordBean = new DataLinkManList.RecordBean();
            recordBean.setFriendList(friendList);

            String jsonString = JSON.toJSONString(recordBean);
            Log.e("jsonString","减少="+jsonString);
            aCache.remove(AppAllKey.FRIEND_DATA);
            aCache.put(AppAllKey.FRIEND_DATA, jsonString);

            Intent intent = new Intent();
            intent.setAction(AppConfig.LINK_FRIEND_DEL_ACTION);
            mContext.sendBroadcast(intent);

        }
    }


    private static List<DataLinkManList.RecordBean.FriendListBean> friendList;
    private static void initDataFriend(String asString, DataAboutFriend.RecordBean mRecord) {
        boolean isTopOne=true;
        boolean isTopTwo=true;
        DataLinkManList.RecordBean record = JSON.parseObject(asString, DataLinkManList.RecordBean.class);
        if (record==null)
            return;
        friendList = record.getFriendList();
        String chat = mRecord.getChat();
        String groupManageName = mRecord.getGroupName();
        String groupId = mRecord.getGroupId();
        if (friendList.size()>0)
        {
            for (int i=0;i<friendList.size();i++)
            {
                String groupName = friendList.get(i).getGroupName();
                String type = friendList.get(i).getType();
                //type 1
                if (groupId != null && !groupId.equals("0")&&type.equals("1")) {
                    if (groupManageName != null && groupManageName.equals(groupName)) {
                        dealTopOneHaveGroup(mRecord,  i, groupManageName);
                        isTopOne=false;
                    }
                }
                // type2
                if (chat != null && chat.equals(groupName)) {
                    dealTopOneHaveGroup(mRecord,  i, groupName);
                    isTopTwo=false;
                }
            }
            if (isTopTwo||isTopOne)
                for (int i=0;i<friendList.size();i++)
                {
                    String type = friendList.get(i).getType();
                    if (type.equals("1")&&isTopOne)
                    {
                        addTopTypeOne(mRecord,0);
                    }
                    else if (type.equals("2")&&isTopTwo) {
                        String groupName = friendList.get(i).getGroupName();
                        int i1 = DealGroupAdd.stringToAscii(DealGroupAdd.getFirstABC(groupName));
                        int i2 = DealGroupAdd.stringToAscii(DealGroupAdd.getFirstABC(chat));
                        if (friendList.size() > (i + 1)) {
                            String groupNameNext = friendList.get(i + 1).getGroupName();
                            int i3 = DealGroupAdd.stringToAscii(DealGroupAdd.getFirstABC(groupNameNext));
                            if (i1 < i2 && i2 < i3) {
                                dealNoChartFriend(mRecord, friendList, (i+1), chat);
                            }
                        } else if (i1 < i2) {
                            dealNoChartFriend(mRecord, friendList, (i+1), chat);
                        } else if (i1 > i2) {
                            dealNoChartFriend(mRecord, friendList, 0, chat);
                        }
                    }
                }
        }else {
            if (!StrUtils.isEmpty(groupId)&& !groupId.equals("0"))
            {
                addTopTypeOne(mRecord,0);
            }
            dealNoChartFriend(mRecord, friendList, 0, chat);
        }

        saveData();
    }

    private static void dealTypeTwoHaveChart(DataAboutFriend.RecordBean mRecord, int i, String groupName) {
    }

    private static void dealTopOneHaveGroup(DataAboutFriend.RecordBean mRecord, int i, String groupName) {

        List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = friendList.get(i).getGroupList();
        DataLinkManList.RecordBean.FriendListBean.GroupListBean groupListBean = new DataLinkManList.RecordBean.FriendListBean.GroupListBean();
        groupListBean.setGroupName(groupName);
        groupListBean.setNickName(mRecord.getGroupName());
        groupListBean.setGroupId(mRecord.getGroupId());
        groupListBean.setHeadImg(mRecord.getHeadImg());
        groupListBean.setRemarkName(mRecord.getRemarkName());
        groupListBean.setModified(mRecord.getModified());
        groupListBean.setUserId(mRecord.getFriendsId());
        groupList.add(groupListBean);
        friendList.get(i).setGroupList(groupList);
        DataLinkManList.RecordBean recordBean = new DataLinkManList.RecordBean();
        recordBean.setFriendList(friendList);
        String jsonString = JSON.toJSONString(recordBean);
        aCache.remove(AppAllKey.FRIEND_DATA);
        aCache.put(AppAllKey.FRIEND_DATA, jsonString);
    }

    //最后保存数据，并发送广播
    private static void saveData() {
//        DataLinkManList.RecordBean recordBean = new DataLinkManList.RecordBean();
//        recordBean.setFriendList(friendList);
//        String jsonString = JSON.toJSONString(recordBean);
//        aCache.remove(AppAllKey.FRIEND_DATA);
//        aCache.put(AppAllKey.FRIEND_DATA, jsonString);
        Intent intent = new Intent();
        intent.setAction(AppConfig.LINK_FRIEND_ADD_ACTION);
        mContext.sendBroadcast(intent);
    }

    //添加顶部无分组时，数据
    private static void addTopTypeOne(DataAboutFriend.RecordBean mRecord,int where) {
        List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = new ArrayList<>();

        DataLinkManList.RecordBean.FriendListBean.GroupListBean groupListBean = new DataLinkManList.RecordBean.FriendListBean.GroupListBean();
        groupListBean.setGroupName(mRecord.getGroupName());
        groupListBean.setNickName(mRecord.getNickName());
        groupListBean.setGroupId(mRecord.getGroupId());
        groupListBean.setHeadImg(mRecord.getHeadImg());
        groupListBean.setRemarkName(mRecord.getRemarkName());
        groupListBean.setModified(mRecord.getModified());
        groupListBean.setUserId(mRecord.getFriendsId());
        groupList.add(groupListBean);

        DataLinkManList.RecordBean.FriendListBean friendListBean = new DataLinkManList.RecordBean.FriendListBean();
        friendListBean.setType("1");
        friendListBean.setGroupName(mRecord.getGroupName());
        friendListBean.setGroupList(groupList);
        friendList.add(where,friendListBean);
        DataLinkManList.RecordBean recordBean = new DataLinkManList.RecordBean();
        recordBean.setFriendList(friendList);
        String jsonString = JSON.toJSONString(recordBean);
        aCache.remove(AppAllKey.FRIEND_DATA);
        aCache.put(AppAllKey.FRIEND_DATA, jsonString);
    }

    //存放type2时候，列表中没有当前组名；
    private static void dealNoChartFriend(DataAboutFriend.RecordBean mRecord, List<DataLinkManList.RecordBean.FriendListBean> friendList, int i, String chat) {
        List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = new ArrayList<>();

        DataLinkManList.RecordBean.FriendListBean.GroupListBean groupListBean = new DataLinkManList.RecordBean.FriendListBean.GroupListBean();
        groupListBean.setGroupName(chat);
        groupListBean.setNickName(mRecord.getGroupName());
        groupListBean.setGroupId(mRecord.getGroupId());
        groupListBean.setHeadImg(mRecord.getHeadImg());
        groupListBean.setRemarkName(mRecord.getRemarkName());
        groupListBean.setModified(mRecord.getModified());
        groupListBean.setUserId(mRecord.getFriendsId());
        groupList.add(groupListBean);

        DataLinkManList.RecordBean.FriendListBean friendListBean = new DataLinkManList.RecordBean.FriendListBean();
        friendListBean.setType("2");
        friendListBean.setGroupName(chat);
        friendListBean.setGroupList(groupList);

        friendList.add(i,friendListBean);

        DataLinkManList.RecordBean recordBean = new DataLinkManList.RecordBean();
        recordBean.setFriendList(friendList);
        String jsonString = JSON.toJSONString(recordBean);
        aCache.remove(AppAllKey.FRIEND_DATA);
        aCache.put(AppAllKey.FRIEND_DATA, jsonString);

//        Intent intent = new Intent();
//        intent.setAction(AppConfig.LINK_FRIEND_ADD_ACTION);
//        mContext.sendBroadcast(intent);




    }
}
