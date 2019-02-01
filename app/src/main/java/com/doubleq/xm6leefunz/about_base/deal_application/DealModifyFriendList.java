package com.doubleq.xm6leefunz.about_base.deal_application;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.doubleq.model.DataLinkManList;
import com.doubleq.model.DataModifyFriendList;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.util.List;


public class DealModifyFriendList {

    private  static ACache aCache;
    private  static Context mContext;
    private static List<DataLinkManList.RecordBean.FriendListBean> friendList;

    private static boolean isOld(String old){
        boolean olds = StrUtils.isEmpty(old);
        boolean oldsZero = old.equals("0");
        return olds||oldsZero;
    }
    public  static void modifyGroupOfFriend(Context context, String result){
        mContext = context;
        DataModifyFriendList dataModifyFriendList = JSON.parseObject(result, DataModifyFriendList.class);
        DataModifyFriendList.RecordBean record = dataModifyFriendList.getRecord();
        aCache =  ACache.get(mContext);
        if (aCache!=null)
        {
            String asString = aCache.getAsString(AppAllKey.FRIEND_DATA);
            if (!StrUtils.isEmpty(asString)&&record!=null)
            {
                //  1增   无到有
                if (isOld(record.getOldGroupId())&&!isOld(record.getNewGroupId()))
                {
                    Log.e("更改好友分组（增）","----------------------------------------------------------------");
                    initDataAdd(asString,record);
                }
                //  2改  有到有
                if (!isOld(record.getOldGroupId())&&!isOld(record.getNewGroupId()))
                {
                    Log.e("更改好友分组（改）","----------------------------------------------------------------");
                    String s = initDataSub(asString, record);
                    if (!StrUtils.isEmpty(s)){
                        String asString1 = aCache.getAsString(AppAllKey.FRIEND_DATA);
                        initDataAdd(asString1,record);
                    }
                }
                //  3删  有到无
                if (!isOld(record.getOldGroupId())&&isOld(record.getNewGroupId()))
                {
                    Log.e("更改好友分组（删）","----------------------------------------------------------------");
                    initDataSub(asString,record);
                }
            }
        }
    }
//    public  static void modifyGroupOfFriend(Context context, String result){
//        mContext = context;
//        DataModifyFriendList dataModifyFriendList = JSON.parseObject(result, DataModifyFriendList.class);
//        DataModifyFriendList.RecordBean record = dataModifyFriendList.getRecord();
//        aCache =  ACache.get(mContext);
//        if (aCache!=null)
//        {
//            String asString = aCache.getAsString(AppAllKey.FRIEND_DATA);
//            if (!StrUtils.isEmpty(asString)&&record!=null)
//            {
//                StrUtils.isEmpty(record.getOldGroupId()
//
//
//                //  1增   无到有
//                if ((StrUtils.isEmpty(record.getOldGroupId()) || record.getOldGroupId().equals("0"))
//                 && (!StrUtils.isEmpty(record.getNewGroupId()) || !record.getNewGroupId().equals("0"))){
//                    try {
//                        Log.e("更改好友分组（增）","----------------------------------------------------------------");
//                        initDataAdd(asString,record);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                //  2改  有到有
//                else if ((!StrUtils.isEmpty(record.getOldGroupId()) || !record.getOldGroupId().equals("0"))
//                        && (!StrUtils.isEmpty(record.getNewGroupId()) || !record.getNewGroupId().equals("0"))){
//                    try {
//                        Log.e("更改好友分组（改）","----------------------------------------------------------------");
//                        initDataModify(asString,record);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                //  3删  有到无
//                else if ((!StrUtils.isEmpty(record.getOldGroupId()) || !record.getOldGroupId().equals("0"))
//                        && (StrUtils.isEmpty(record.getNewGroupId()) || record.getNewGroupId().equals("0"))){
//                    try {
//                        Log.e("更改好友分组（删）","----------------------------------------------------------------");
//                        initDataSub(asString,record);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }

    private static void initDataAdd(String asString, DataModifyFriendList.RecordBean mRecord) {
        DataLinkManList.RecordBean record = JSON.parseObject(asString, DataLinkManList.RecordBean.class);
        if (record==null)
            return;
        friendList = record.getFriendList();
        String newGroupId = mRecord.getNewGroupId();
        String newGroupName = mRecord.getNewGroupName();
        //  若列表不为空
        if (friendList.size() > 0){
            String friendsId = mRecord.getFriendsId();
            for (int i = 0; i < friendList.size();i++) {
                Log.e("更改好友分组（增）", "--------------------------i=" + i);
                List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = friendList.get(i).getGroupList();
                if (friendList.get(i).getType().equals("1")) {
                    if (friendList.get(i).getGroupId() != null){
                        //  获取的新分组Id与当下的分组Id相同，则进行下一步判断
                        if (friendList.get(i).getGroupId().equals(newGroupId)) {
                            //  分组里的好友（小列表）不为空，则进行添判断是否已经在该组中添加该好友
                            if (groupList.size() > 0) {
                                //  判断该好友是否与该分组的成员相同，相同则不再添加
                                for (int j = 0; j < groupList.size(); j++)
                                    if (groupList.get(j).getUserId().equals(friendsId))
                                        return;
                            }
                            putCache(mRecord, i, newGroupName);
                            return;
                        }
                    }
                }
            }
        }else
            putCache(mRecord, 0, newGroupName);
    }
    private static void putCache(DataModifyFriendList.RecordBean mRecord, int i, String newGroupName) {

        List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = friendList.get(i).getGroupList();
        DataLinkManList.RecordBean.FriendListBean.GroupListBean groupListBean = new DataLinkManList.RecordBean.FriendListBean.GroupListBean();
        groupListBean.setGroupName(newGroupName);
//        groupListBean.setNickName(mRecord.getNewNickName());
        groupListBean.setGroupId(mRecord.getNewGroupId());
        groupListBean.setHeadImg(mRecord.getNewHeadImg());
        String name = StrUtils.isEmpty(mRecord.getRemarkName())? mRecord.getNewNickName() : mRecord.getRemarkName();
        groupListBean.setNickName(name);
        groupListBean.setModified(mRecord.getModified());
        groupListBean.setUserId(mRecord.getFriendsId());
        groupList.add(groupListBean);
        friendList.get(i).setGroupList(groupList);
        friendList.get(i).setType("1");
        DataLinkManList.RecordBean recordBean = new DataLinkManList.RecordBean();
        recordBean.setFriendList(friendList);
        String jsonString = JSON.toJSONString(recordBean);
        Log.e("jsonString","不展开（好友添加至分组）="+jsonString);
        aCache.remove(AppAllKey.FRIEND_DATA);
        aCache.put(AppAllKey.FRIEND_DATA, jsonString);

        Intent intent = new Intent();
        intent.setAction(AppConfig.LINK_FRIEND_ADD_ACTION);
        mContext.sendBroadcast(intent);
    }

    private static String initDataSub(String asString, DataModifyFriendList.RecordBean mRecord) {
        DataLinkManList.RecordBean record = JSON.parseObject(asString, DataLinkManList.RecordBean.class);
        if (record==null)
            return null;
        Log.e("更改好友分组（删）","--------------------------------initDataSub--------------------------------");
        friendList = record.getFriendList();
        String oldGroupId = mRecord.getOldGroupId();
        //  若列表不为空
        if (friendList.size() > 0){
            String friendsId = mRecord.getFriendsId();
            for (int i = 0; i < friendList.size();i++) {
                DataLinkManList.RecordBean.FriendListBean friendListBean = friendList.get(i);
                List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = friendListBean.getGroupList();
                if (friendListBean.getType().equals("1")) {
//                    列表分组名
//                    String groupName = friendListBean.getGroupName();
                    String groupId = friendListBean.getGroupId();
                    if (groupId != null){
                        if (groupId.equals(oldGroupId)) {
                            if (groupList.size()>0)
                            {
                                for (int j = 0; j < groupList.size(); j++) {
                                    String userId = groupList.get(j).getUserId();
                                    if (friendsId.equals(userId)) {
                                        friendList.get(i).getGroupList().remove(j);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
//        修改数据保存cache
        DataLinkManList.RecordBean recordBean = new DataLinkManList.RecordBean();
        recordBean.setFriendList(friendList);

        String jsonString = JSON.toJSONString(recordBean);
        Log.e("jsonString","更改好友分组（删）="+jsonString);
        aCache.remove(AppAllKey.FRIEND_DATA);
        aCache.put(AppAllKey.FRIEND_DATA, jsonString);

        Intent intent = new Intent();
        intent.setAction(AppConfig.LINK_FRIEND_DEL_ACTION);
        mContext.sendBroadcast(intent);
        return jsonString;
    }

}
