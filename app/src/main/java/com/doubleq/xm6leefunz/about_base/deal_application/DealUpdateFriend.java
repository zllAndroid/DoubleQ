package com.doubleq.xm6leefunz.about_base.deal_application;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.doubleq.model.DataLinkManList;
import com.doubleq.model.push_data.DataUpdateFriend;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.util.ArrayList;
import java.util.List;


public class DealUpdateFriend {
    private  static ACache aCache;
    private  static Context mContext;
    private static List<DataLinkManList.RecordBean.FriendListBean> friendList;

    private static boolean isEmpty(String empty){
        boolean emptys = StrUtils.isEmpty(empty);
        boolean Empty = empty.equals("0");
        return emptys || Empty;
    }
    public static void updateFriend(Context context, String result) {
        mContext = context;
        DataUpdateFriend dataUpdateFriend = JSON.parseObject(result, DataUpdateFriend.class);
        DataUpdateFriend.RecordBean record = dataUpdateFriend.getRecord();
        aCache =  ACache.get(mContext);
        if (aCache!=null) {
            String asString = aCache.getAsString(AppAllKey.FRIEND_DATA);
            if (!StrUtils.isEmpty(asString) && record != null) {
                /**
                 * 好友修改昵称
                 */
                //  若好友无备注则进行修改
                if (isEmpty(record.getNewRemarkName())){
                    initDataUpdate(asString,record);
                    return;
                }
                // TODO   好友修改头像

            }
        }
    }

    private static int groupListSize = 0;
    private static void initDataUpdate(String asString, DataUpdateFriend.RecordBean mRecord) {
        DataLinkManList.RecordBean record = JSON.parseObject(asString, DataLinkManList.RecordBean.class);
        if (record==null)
            return;
        friendList = record.getFriendList();
        if (friendList.size() > 0) {
            groupListSize = 0;
            for (int i = 0; i < friendList.size(); i++) {
                if (friendList.get(i).getType().equals("1")) {
                    groupListSize = groupListSize + 1;
//                    groupListSize++;
                }
            }
        }
        String friendsId = mRecord.getFriendsId();
        String newNickName = mRecord.getNewNickName();  //好友的新昵称
        String chart = mRecord.getNewChart();  //好友的新首字母
        for (int i = 0; i < friendList.size();i++) {
            DataLinkManList.RecordBean.FriendListBean friendListBean = friendList.get(i);
            List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = friendListBean.getGroupList();
            // type为1的好友分组
            if (friendListBean.getType().equals("1")) {
                for (int j = 0; j < groupList.size(); j++){
                    String userId = groupList.get(j).getUserId();
                    if (friendsId.equals(userId)){
                        friendList.get(i).getGroupList().remove(j);
                        putCacheUpdate(mRecord,i,newNickName);
                    }
                }
            }
            // type为2的好友列表
            else {
                for (int j = 0; j < groupList.size(); j++){
                    String userId = groupList.get(j).getUserId();
                    if (friendsId.equals(userId)){
                        //  若该字母下只有一个好友
                        if (friendList.get(i).getGroupList().size() == 1){
                            friendList.remove(i);
                            Log.e("friendLists","-------------------------------------------------"+friendList.size());
                            updateFriendAdd(mRecord, chart, newNickName);
                            return;
                        }
                        else {
                            friendList.get(i).getGroupList().remove(j);
                            updateFriendAdd(mRecord, chart, newNickName);
                            return;
                        }
                    }
                }
            }
        }
    }

    private static void updateFriendAdd(DataUpdateFriend.RecordBean mRecord, String chart, String newNickName) {
        for (int i = groupListSize; i < friendList.size(); i++) {
            String groupName = friendList.get(i).getGroupName();
            int i1 = DealGroupAdd.stringToAscii(DealGroupAdd.getFirstABC(groupName));  // 分组名
            int i2 = DealGroupAdd.stringToAscii(DealGroupAdd.getFirstABC(chart));
            if (friendList.size() > (i + 1)) {
                String groupNameNext = friendList.get(i + 1).getGroupName();
                int i3 = DealGroupAdd.stringToAscii(DealGroupAdd.getFirstABC(groupNameNext));
                if (i1 < i2 && i2 < i3) {
                    dealNoChartFriend(mRecord, friendList, (i + 1), chart, newNickName);
                    return;
                }
                else if (i1 > i2) {
                    dealNoChartFriend(mRecord, friendList, i, chart, newNickName);
                    return;
                }
                else if (i1 == i2){
                    dealTopOneHaveGroup(mRecord,i,chart,newNickName);
                    return;
                }
                else if (i1 == i3){
                    dealTopOneHaveGroup(mRecord,(i+1),chart,newNickName);
                    return;
                }
            }
            else if (i1 < i2) {
                dealNoChartFriend(mRecord, friendList, (i + 1), chart, newNickName);
                return;
            }
            else if (i1 > i2) {
                dealNoChartFriend(mRecord, friendList, i, chart, newNickName);
                return;
            }
            else if (i1 == i2){
                dealTopOneHaveGroup(mRecord,i,chart,newNickName);
                return;
            }
        }
    }

    private static void dealTopOneHaveGroup(DataUpdateFriend.RecordBean mRecord, int i, String groupName, String newNickName) {
        List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = friendList.get(i).getGroupList();
        DataLinkManList.RecordBean.FriendListBean.GroupListBean groupListBean = new DataLinkManList.RecordBean.FriendListBean.GroupListBean();
        groupListBean.setGroupName(groupName);
//        String name = StrUtils.isEmpty(newRemarkName)? mRecord.getNewNickName() :  newRemarkName;
        groupListBean.setNickName(newNickName);
        groupListBean.setGroupId(mRecord.getNewGroupId());
        groupListBean.setHeadImg(mRecord.getNewHeadImg());
        groupListBean.setRemarkName(mRecord.getNewRemarkName());
        groupListBean.setModified(mRecord.getModified());
        groupListBean.setUserId(mRecord.getFriendsId());
        groupList.add(groupListBean);

        friendList.get(i).setGroupList(groupList);
        friendList.get(i).setType("2");
        friendList.get(i).setGroupName(groupName);
        friendList.get(i).setGroupId(mRecord.getNewGroupId());

        DataLinkManList.RecordBean recordBean = new DataLinkManList.RecordBean();
        recordBean.setFriendList(friendList);
        String jsonString = JSON.toJSONString(recordBean);
        aCache.remove(AppAllKey.FRIEND_DATA);
        aCache.put(AppAllKey.FRIEND_DATA, jsonString);

        Intent intent = new Intent();
        intent.setAction(AppConfig.LINK_FRIEND_ADD_ACTION);
        mContext.sendBroadcast(intent);

    }

    private static void dealNoChartFriend(DataUpdateFriend.RecordBean mRecord, List<DataLinkManList.RecordBean.FriendListBean> friendList, int i, String chart, String newNickName) {
        List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = new ArrayList<>();

        DataLinkManList.RecordBean.FriendListBean.GroupListBean groupListBean = new DataLinkManList.RecordBean.FriendListBean.GroupListBean();
        groupListBean.setGroupName(chart);
//        String name = StrUtils.isEmpty(newRemarkName)? mRecord.getNewNickName() :  newRemarkName;
        groupListBean.setNickName(newNickName);
//        groupListBean.setNickName(mRecord.getNewNickName());
        groupListBean.setRemarkName(mRecord.getNewRemarkName());
        groupListBean.setGroupId(mRecord.getNewGroupId());
        groupListBean.setHeadImg(mRecord.getNewHeadImg());
        groupListBean.setModified(mRecord.getModified());
        groupListBean.setUserId(mRecord.getFriendsId());
        groupList.add(groupListBean);

        DataLinkManList.RecordBean.FriendListBean friendListBean = new DataLinkManList.RecordBean.FriendListBean();
        friendListBean.setType("2");
        friendListBean.setGroupName(chart);
        friendListBean.setGroupList(groupList);
        friendListBean.setGroupId(mRecord.getNewGroupId());

        friendList.add(i,friendListBean);

        DataLinkManList.RecordBean recordBean = new DataLinkManList.RecordBean();
        recordBean.setFriendList(friendList);
        String jsonString = JSON.toJSONString(recordBean);
        aCache.remove(AppAllKey.FRIEND_DATA);
        aCache.put(AppAllKey.FRIEND_DATA, jsonString);

        Intent intent = new Intent();
        intent.setAction(AppConfig.LINK_FRIEND_ADD_ACTION);
        mContext.sendBroadcast(intent);

    }

    private static void putCacheUpdate(DataUpdateFriend.RecordBean mRecord, int i, String newNickName) {
        List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = friendList.get(i).getGroupList();
        DataLinkManList.RecordBean.FriendListBean.GroupListBean groupListBean = new DataLinkManList.RecordBean.FriendListBean.GroupListBean();
        groupListBean.setRemarkName(mRecord.getNewRemarkName());
        groupListBean.setGroupId(mRecord.getNewGroupId());
        groupListBean.setGroupName(mRecord.getNewGroupName());
        groupListBean.setHeadImg(mRecord.getNewHeadImg());
//        String name = StrUtils.isEmpty(newRemarkName)? mRecord.getNewNickName() : newRemarkName;
        groupListBean.setNickName(newNickName);
        groupListBean.setModified(mRecord.getModified());
        groupListBean.setUserId(mRecord.getFriendsId());
        groupList.add(groupListBean);

        friendList.get(i).setGroupList(groupList);
        friendList.get(i).setType("1");
        friendList.get(i).setGroupId(mRecord.getNewGroupId());
        friendList.get(i).setGroupName(mRecord.getNewGroupName());

        DataLinkManList.RecordBean recordBean = new DataLinkManList.RecordBean();
        recordBean.setFriendList(friendList);
        String jsonString = JSON.toJSONString(recordBean);
        Log.e("jsonString","展开（好友添加至分组）="+jsonString);
        aCache.remove(AppAllKey.FRIEND_DATA);
        aCache.put(AppAllKey.FRIEND_DATA, jsonString);

        Intent intent = new Intent();
        intent.setAction(AppConfig.LINK_FRIEND_ADD_ACTION);
        mContext.sendBroadcast(intent);

    }
}
