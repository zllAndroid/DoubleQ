package com.doubleq.xm6leefunz.about_base.deal_application;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.doubleq.model.DataDeleteGroupingList;
import com.doubleq.model.DataLinkGroupList;
import com.doubleq.model.DataLinkManList;
import com.doubleq.model.DataAgreeGroupList;
import com.doubleq.model.DataModifyGroupingList;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.util.ArrayList;
import java.util.List;

public class DealModifyGroupOfList {

    private  static ACache aCache;
    @SuppressLint("StaticFieldLeak")
    private  static Context mContext;
    private static List<DataLinkManList.RecordBean.FriendListBean> friendList;
    private static List<DataLinkGroupList.RecordBean.GroupInfoListBean> groupList;
    //  增加分组
    public  static void addGroupOfList(Context context, String result){
        mContext = context;
        DataAgreeGroupList dataAgreeGroupList = JSON.parseObject(result, DataAgreeGroupList.class);
        DataAgreeGroupList.RecordBean record = dataAgreeGroupList.getRecord();
        aCache =  ACache.get(mContext);
        if (aCache!=null)
        {
            if (record.getType().equals("1")){
                String asString = aCache.getAsString(AppAllKey.FRIEND_DATA);
                if (!StrUtils.isEmpty(asString)&&record!=null)
                {
                    initDataFriend(asString,record);
                }
            }else{
                String asString = aCache.getAsString(AppAllKey.GROUD_DATA);
                if (!StrUtils.isEmpty(asString)&&record!=null)
                {
                    initDataGroup(asString,record);
                }
            }
        }
    }
    static String friendGroupId;
    private static void initDataFriend(String asString, DataAgreeGroupList.RecordBean mRecord) {
//        DataLinkManList.RecordBean recordBean = JSON.parseObject(asString, DataLinkManList.RecordBean.class);
        DataLinkManList.RecordBean record = JSON.parseObject(asString, DataLinkManList.RecordBean.class);
        if (record==null)
            return;
        friendList = record.getFriendList();
//        final List<DataLinkManList.RecordBean.FriendListBean> friend_info_list = recordBean.getFriendList();
//        有1有2  无1无2  有1无2  无1有2
        if (friendList.size() > 0) {
            for (int i = 0; i < friendList.size(); i++) {
                String type = friendList.get(i).getType();
                if (type.equals("2")) {  //有1有2 & 无1有2
                    String groupName = mRecord.getGroupName();
                    friendGroupId = mRecord.getGroupId();
                    if (groupName != null){
                        putCache(friendList, i, groupName);
                        return;
                    }
                }else if (friendList.size() == (i + 1)){  //有1无2
                    String groupName = mRecord.getGroupName();
                    friendGroupId = mRecord.getGroupId();
                    if (groupName != null){
                        putCache(friendList, friendList.size(), groupName);
                        return;
                    }
                }
            }
        }else {  //如果联系人列表为空         无1无2
            DataLinkManList.RecordBean.FriendListBean friendListBean = new DataLinkManList.RecordBean.FriendListBean();
            friendListBean.setGroupName(mRecord.getGroupName());
            friendListBean.setType("1");
            Log.e("groupInfoListBean","------------------------------------"+mRecord.getGroupId());
            friendListBean.setGroupId(mRecord.getGroupId());
            List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupListBean = new ArrayList<>();
            groupListBean.clear();
            friendListBean.setGroupList(groupListBean);
            friendList.add(friendListBean);
            record.setFriendList(friendList);

            String jsonString = JSON.toJSONString(record);
            Log.e("jsonString","不展开（增加）="+jsonString);

            aCache.remove(AppAllKey.FRIEND_DATA);
            aCache.put(AppAllKey.FRIEND_DATA, jsonString);

            Intent intent = new Intent();
            intent.setAction(AppConfig.LINK_FRIEND_ADD_ACTION);
            mContext.sendBroadcast(intent);

        }
    }
    static String groupId;
    private static void initDataGroup(String asString, DataAgreeGroupList.RecordBean mRecord) {
//        DataLinkManList.RecordBean recordBean = JSON.parseObject(asString, DataLinkManList.RecordBean.class);
        DataLinkGroupList.RecordBean record = JSON.parseObject(asString, DataLinkGroupList.RecordBean.class);
        if (record==null)
            return;
        groupList = record.getGroupInfoList();
//        final List<DataLinkManList.RecordBean.FriendListBean> friend_info_list = recordBean.getFriendList();
//        有1有2  无1无2  有1无2  无1有2
        if (groupList.size() > 0) {
            for (int i = 0; i < groupList.size(); i++) {
                String type = groupList.get(i).getType();
                if (type.equals("2")) {  //有1有2 & 无1有2
                    String groupName = mRecord.getGroupName();
                    groupId = mRecord.getGroupId();
                    if (groupName != null){
                        putCacheGroup(groupList, i, groupName);
                        return;
                    }
                }else if (groupList.size() == (i + 1)){  //有1无2
                    String groupName = mRecord.getGroupName();
                    groupId = mRecord.getGroupId();
                    if (groupName != null){
                        putCacheGroup(groupList, groupList.size(), groupName);
                        return;
                    }
                }
            }
        }else {  //如果群列表为空         无1无2
            DataLinkGroupList.RecordBean.GroupInfoListBean groupInfoListBean = new DataLinkGroupList.RecordBean.GroupInfoListBean();
            groupInfoListBean.setGroupName(mRecord.getGroupName());
            groupInfoListBean.setType("1");
            Log.e("groupInfoListBean","------------------------------------"+mRecord.getGroupId());
            groupInfoListBean.setGroupId(mRecord.getGroupId());
            List<DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean> groupListBean = new ArrayList<>();
            groupListBean.clear();
            groupInfoListBean.setGroupList(groupListBean);
            groupList.add(groupInfoListBean);
            record.setGroupInfoList(groupList);

            String jsonString = JSON.toJSONString(record);
            Log.e("jsonString","不展开（增加）="+jsonString);

            aCache.remove(AppAllKey.GROUD_DATA);
            aCache.put(AppAllKey.GROUD_DATA, jsonString);

            Intent intent = new Intent();
            intent.setAction(AppConfig.LINK_GROUP_ADD_ACTION);
            mContext.sendBroadcast(intent);

        }
    }
    private static void putCache(List<DataLinkManList.RecordBean.FriendListBean> friend_info_list, int i, String groupName) {
        DataLinkManList.RecordBean.FriendListBean friendListBean = new DataLinkManList.RecordBean.FriendListBean();
        List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = new ArrayList<>();
        groupList.clear();
        friendListBean.setGroupList(groupList);
        friendListBean.setGroupName(groupName);
        friendListBean.setType("1");
        friendListBean.setGroupId(friendGroupId);
        friend_info_list.add(i, friendListBean);

        DataLinkManList.RecordBean recordBean = new DataLinkManList.RecordBean();
        recordBean.setFriendList(friend_info_list);

        String jsonString = JSON.toJSONString(recordBean);
        Log.e("jsonString","不展开（添加）="+jsonString);

        aCache.remove(AppAllKey.FRIEND_DATA);
        aCache.put(AppAllKey.FRIEND_DATA, jsonString);

        Intent intent = new Intent();
        intent.setAction(AppConfig.LINK_FRIEND_ADD_ACTION);
        mContext.sendBroadcast(intent);

    }
    private static void putCacheGroup(List<DataLinkGroupList.RecordBean.GroupInfoListBean> group_info_list, int i, String groupName) {
        DataLinkGroupList.RecordBean.GroupInfoListBean groupInfoListBean = new DataLinkGroupList.RecordBean.GroupInfoListBean();
        List<DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean> groupListBean = new ArrayList<>();
        groupListBean.clear();
        groupInfoListBean.setGroupList(groupListBean);
        groupInfoListBean.setGroupName(groupName);
        groupInfoListBean.setType("1");
        groupInfoListBean.setGroupId(groupId);
        group_info_list.add(i, groupInfoListBean);

        DataLinkGroupList.RecordBean recordBean = new DataLinkGroupList.RecordBean();
        recordBean.setGroupInfoList(group_info_list);

        String jsonString = JSON.toJSONString(recordBean);
        Log.e("jsonString","不展开（添加）="+jsonString);

        aCache.remove(AppAllKey.GROUD_DATA);
        aCache.put(AppAllKey.GROUD_DATA, jsonString);

        Intent intent = new Intent();
        intent.setAction(AppConfig.LINK_GROUP_ADD_ACTION);
        mContext.sendBroadcast(intent);

    }

    //  删除分组
    public  static void deleteGroupOfList(Context context, String result){
        mContext = context;
        DataDeleteGroupingList dataDeleteGroupingList = JSON.parseObject(result, DataDeleteGroupingList.class);
        DataDeleteGroupingList.RecordBean record = dataDeleteGroupingList.getRecord();
        aCache =  ACache.get(mContext);
        if (aCache!=null)
        {
            if (record.getType().equals("1")){
                String asString = aCache.getAsString(AppAllKey.FRIEND_DATA);
                if (!StrUtils.isEmpty(asString)&&record!=null)
                {
                    initDataFriendDelete(asString,record);
                }
            }else{
                String asString = aCache.getAsString(AppAllKey.GROUD_DATA);
                if (!StrUtils.isEmpty(asString)&&record!=null)
                {
                    initDataGroupDelete(asString,record);
                }
            }
        }
    }
    private static void initDataFriendDelete(String asString, DataDeleteGroupingList.RecordBean mRecord) {
        DataLinkManList.RecordBean record = JSON.parseObject(asString, DataLinkManList.RecordBean.class);
        if (record==null)
            return;
        friendList = record.getFriendList();
        if (friendList.size() > 0) {
            for (int i = 0; i < friendList.size(); i++) {
                String type = friendList.get(i).getType();
                if (type.equals("1")) {
                    String groupName = mRecord.getGroupName();
                    if (groupName != null){
                        if (groupName.equals(friendList.get(i).getGroupName())){
                            putCacheFriendDelete(i);
                        }
                    }
                }
            }
        }
    }
    private static void initDataGroupDelete(String asString, DataDeleteGroupingList.RecordBean mRecord) {
        DataLinkGroupList.RecordBean record = JSON.parseObject(asString, DataLinkGroupList.RecordBean.class);
        if (record==null)
            return;
        groupList = record.getGroupInfoList();
        if (groupList.size() > 0) {
            for (int i = 0; i < groupList.size(); i++) {
                String type = groupList.get(i).getType();
                if (type.equals("1")) {
                    String groupName = mRecord.getGroupName();
                    if (groupName != null){
                        if (groupName.equals(groupList.get(i).getGroupName())){
                            putCacheGroupDelete(i);
                        }
                    }
                }
            }
        }
    }
    private static void putCacheFriendDelete(int i) {
        friendList.remove(i);

        DataLinkManList.RecordBean recordBean = new DataLinkManList.RecordBean();
        recordBean.setFriendList(friendList);

        String jsonString = JSON.toJSONString(recordBean);
        Log.e("jsonString","不展开（删除）="+jsonString);

        aCache.remove(AppAllKey.FRIEND_DATA);
        aCache.put(AppAllKey.FRIEND_DATA, jsonString);

        Intent intent = new Intent();
        intent.setAction(AppConfig.LINK_FRIEND_DEL_ACTION);
        mContext.sendBroadcast(intent);
    }
    private static void putCacheGroupDelete(int i) {
        groupList.remove(i);

        DataLinkGroupList.RecordBean recordBean = new DataLinkGroupList.RecordBean();
        recordBean.setGroupInfoList(groupList);

        String jsonString = JSON.toJSONString(recordBean);
        Log.e("jsonString","不展开（删除）="+jsonString);
        aCache.remove(AppAllKey.GROUD_DATA);
        aCache.put(AppAllKey.GROUD_DATA, jsonString);

        Intent intent = new Intent();
        intent.setAction(AppConfig.LINK_GROUP_ADD_ACTION);
        mContext.sendBroadcast(intent);
    }

    //  修改分组
    public  static void modifyGroupOfList(Context context, String result){
        mContext = context;
        DataModifyGroupingList dataModifyGroupingList = JSON.parseObject(result, DataModifyGroupingList.class);
        DataModifyGroupingList.RecordBean record = dataModifyGroupingList.getRecord();
        aCache =  ACache.get(mContext);
        if (aCache!=null)
        {
            if (record.getType().equals("1")){
                String asString = aCache.getAsString(AppAllKey.FRIEND_DATA);
                if (!StrUtils.isEmpty(asString)&&record!=null)
                {
                    initDataFriendModify(asString,record);
                }
            }else{
                String asString = aCache.getAsString(AppAllKey.GROUD_DATA);
                if (!StrUtils.isEmpty(asString)&&record!=null)
                {
                    initDataGroupModify(asString,record);
                }
            }
        }
    }
    private static void initDataFriendModify(String asString, DataModifyGroupingList.RecordBean mRecord) {
        DataLinkManList.RecordBean record = JSON.parseObject(asString, DataLinkManList.RecordBean.class);
        if (record==null)
            return;
        friendList = record.getFriendList();
        if (friendList.size() > 0) {
            for (int i = 0; i < friendList.size(); i++) {
                String type = friendList.get(i).getType();
                if (type.equals("1")) {
                    String OldGroupName = mRecord.getOldGroupName();
                    String NewGroupName = mRecord.getNewGroupName();
                    if (OldGroupName != null){
                        if (OldGroupName.equals(friendList.get(i).getGroupName())){
                            putCacheFriendModify(friendList,i,NewGroupName);
                        }
                    }
                }
            }
        }
    }
    private static void initDataGroupModify(String asString, DataModifyGroupingList.RecordBean mRecord) {
        DataLinkGroupList.RecordBean record = JSON.parseObject(asString, DataLinkGroupList.RecordBean.class);
        if (record==null)
            return;
        groupList = record.getGroupInfoList();
        if (groupList.size() > 0) {
            for (int i = 0; i < groupList.size(); i++) {
                String type = groupList.get(i).getType();
                if (type.equals("1")) {
                    String OldGroupName = mRecord.getOldGroupName();
                    String NewGroupName = mRecord.getNewGroupName();
                    if (OldGroupName != null){
                        if (OldGroupName.equals(groupList.get(i).getGroupName())){
                            putCacheGroupModify(groupList,i,NewGroupName);
                        }
                    }
                }
            }
        }
    }
    private static void putCacheFriendModify(List<DataLinkManList.RecordBean.FriendListBean> friend_info_list, int i, String NewGroupName) {
        friend_info_list.get(i).setGroupName(NewGroupName);

        DataLinkManList.RecordBean recordBean = new DataLinkManList.RecordBean();
        recordBean.setFriendList(friend_info_list);

        String jsonString = JSON.toJSONString(recordBean);
        Log.e("jsonString","不展开（修改）="+jsonString);

        aCache.remove(AppAllKey.FRIEND_DATA);
        aCache.put(AppAllKey.FRIEND_DATA, jsonString);

        Intent intent = new Intent();
        intent.setAction(AppConfig.LINK_FRIEND_ADD_ACTION);
        mContext.sendBroadcast(intent);
    }
    private static void putCacheGroupModify(List<DataLinkGroupList.RecordBean.GroupInfoListBean> group_info_list, int i, String NewGroupName) {
        group_info_list.get(i).setGroupName(NewGroupName);

        DataLinkGroupList.RecordBean recordBean = new DataLinkGroupList.RecordBean();
        recordBean.setGroupInfoList(group_info_list);

        String jsonString = JSON.toJSONString(recordBean);
        Log.e("jsonString","不展开（修改）="+jsonString);

        aCache.remove(AppAllKey.GROUD_DATA);
        aCache.put(AppAllKey.GROUD_DATA, jsonString);

        Intent intent = new Intent();
        intent.setAction(AppConfig.LINK_GROUP_ADD_ACTION);
        mContext.sendBroadcast(intent);
    }

}
