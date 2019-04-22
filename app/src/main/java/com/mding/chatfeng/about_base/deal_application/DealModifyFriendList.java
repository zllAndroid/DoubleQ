package com.mding.chatfeng.about_base.deal_application;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_broadcastreceiver.LinkChangeEvent;
import com.mding.chatfeng.about_broadcastreceiver.MsgHomeEvent;
import com.mding.model.DataLinkManList;
import com.mding.model.DataModifyFriendList;
import com.mding.model.push_data.DataAboutFriend;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


public class DealModifyFriendList {

    private   ACache aCache;
    private   Context mContext;
    private  List<DataLinkManList.RecordBean.FriendListBean> friendList;
    private static DealModifyFriendList dealFriendAdd;
    // 构造函数必须是私有的 这样在外部便无法使用 new 来创建该类的实例
    private DealModifyFriendList() {}
    /**
     * 单一实例
     */
    public synchronized static DealModifyFriendList getDealModifyFriendList() {
        if (dealFriendAdd == null) {
            dealFriendAdd = new DealModifyFriendList();
        }
        return dealFriendAdd;
    }
    private  boolean isOld(String old){
        boolean olds = StrUtils.isEmpty(old);
        boolean oldsZero = old.equals("0");
        return olds||oldsZero;
    }
    public   void modifyGroupOfFriend(Context context, String result){
        mContext = context;
        DataModifyFriendList dataModifyFriendList = JSON.parseObject(result, DataModifyFriendList.class);
        DataModifyFriendList.RecordBean record = dataModifyFriendList.getRecord();
        aCache =  ACache.get(mContext);
        if (aCache!=null)
        {
            String asString = aCache.getAsString(AppAllKey.FRIEND_DATA);
            if (!StrUtils.isEmpty(asString)&&record!=null)
            {
                /**
                 * 修改备注
                 */
                //  1旧：""  新："NewRemarkName"
                if (isOld(record.getOldRemarkName()) && !isOld(record.getNewRemarkName())){
                    initDataUpdate(asString,record);
                    return;
                }
                //  2旧："OldRemarkName"  新："NewRemarkName"   OldRemarkName != NewRemarkName
                else if (!isOld(record.getOldRemarkName()) && !isOld(record.getNewRemarkName()) && !record.getOldRemarkName().equals(record.getNewRemarkName())){
                    initDataUpdate(asString,record);
                    return;
                }
                //  3旧："OldRemarkName"  新：""
                else if (!isOld(record.getOldRemarkName()) && isOld(record.getNewRemarkName())){
                    initDataUpdate(asString,record);
                    return;
                }
                /**
                 * 修改分组
                 */
                //  1增   无到有
                if (isOld(record.getOldGroupId())&&!isOld(record.getNewGroupId()))
                {
                    Log.e("更改好友分组（增）","----------------------------------------------------------------");
                    initDataAdd(asString,record);
                }
                //  2改  有到有
                else if (!isOld(record.getOldGroupId())&&!isOld(record.getNewGroupId()))
                {
                    Log.e("更改好友分组（改）","----------------------------------------------------------------");
                    String s = initDataSub(asString, record);
                    if (!StrUtils.isEmpty(s)){
                        String asString1 = aCache.getAsString(AppAllKey.FRIEND_DATA);
                        initDataAdd(asString1,record);
                    }
                }
                //  3删  有到无
                else if (!isOld(record.getOldGroupId())&&isOld(record.getNewGroupId()))
                {
                    Log.e("更改好友分组（删）","----------------------------------------------------------------");
                    initDataSub(asString,record);
                }
            }
            EventBus.getDefault().post(new LinkChangeEvent(AppConfig.LINK_FRIEND_ADD_ACTION));
//            EventBus.getDefault().post(new MsgHomeEvent(AppConfig.LINK_GROUP_DEL_ACTION));
//            Intent intent = new Intent();
//            intent.setAction(AppConfig.LINK_FRIEND_ADD_ACTION);
//            mContext.sendBroadcast(intent);

        }
    }
    private  int groupListSize = 0;
    private  void initDataUpdate(String asString,final DataModifyFriendList.RecordBean mRecord) {
        DataLinkManList.RecordBean record = JSON.parseObject(asString, DataLinkManList.RecordBean.class);
        if (record==null)
            return;
        friendList = record.getFriendList();
        if (friendList.size() > 0){
            groupListSize = 0;
            for (int i = 0;i < friendList.size(); i++){
                if (friendList.get(i).getType().equals("1")){
//                    groupListSize = groupListSize + 1;
                    groupListSize++;
                }
            }

            String friendsId = mRecord.getFriendsId();
//            String oldRemarkName = mRecord.getOldRemarkName();
            String newRemarkName = mRecord.getNewRemarkName();
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
                            putCacheUpdate(mRecord,i,newRemarkName);
                        }
                    }
                }
                // type为2的好友列表
                if (friendListBean.getType().equals("2"))  {
                    for (int j = 0; j < groupList.size(); j++){
                        String userId = groupList.get(j).getUserId();
                        if (friendsId.equals(userId)){
                            if((friendList.size()-groupListSize)==1 && friendList.get(i).getGroupList().size() == 1)
                            {
//                       TODO      整个数据只有一条数据的情况，删掉本条数据   直接添加type2
                                friendList.remove(i);
                                dealNoChartFriend(mRecord, friendList, friendList.size(), chart);
                                return;
                            }
                            //  若该字母下只有一个好友
                            if (friendList.get(i).getGroupList().size() == 1){
                                friendList.remove(i);
                                Log.e("friendLists","-------------------------------------------------"+friendList.size());
                                updateFriendAdd(mRecord, chart, newRemarkName);
                                return;
                            }
                            else {
                                friendList.get(i).getGroupList().remove(j);
                                updateFriendAdd(mRecord, chart, newRemarkName);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
    private  void dealNoChartFriend(DataModifyFriendList.RecordBean mRecord, List<DataLinkManList.RecordBean.FriendListBean> friendList, int i, String chart) {
        List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = new ArrayList<>();

        DataLinkManList.RecordBean.FriendListBean.GroupListBean groupListBean = new DataLinkManList.RecordBean.FriendListBean.GroupListBean();
        groupListBean.setGroupName(chart);
        groupListBean.setNickName(mRecord.getNewNickName());
        groupListBean.setGroupId(mRecord.getNewGroupId());
        groupListBean.setHeadImg(mRecord.getNewHeadImg());
        groupListBean.setRemarkName(mRecord.getNewRemarkName());
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
        EventBus.getDefault().post(new LinkChangeEvent(AppConfig.LINK_FRIEND_ADD_ACTION));
//        Intent intent = new Intent();
//        intent.setAction(AppConfig.LINK_FRIEND_ADD_ACTION);
//        mContext.sendBroadcast(intent);

    }
    private  void updateFriendAdd(DataModifyFriendList.RecordBean mRecord, String chart, String newRemarkName) {
        for (int i = groupListSize; i < friendList.size(); i++) {
            String groupName = friendList.get(i).getGroupName();
            int i1 = DealGroupAdd.getDealGroupAdd().stringToAscii(DealGroupAdd.getDealGroupAdd().getFirstABC(groupName));  // 分组名
            int i2 = DealGroupAdd.getDealGroupAdd().stringToAscii(DealGroupAdd.getDealGroupAdd().getFirstABC(chart));
            if (friendList.size() > (i + 1)) {
                String groupNameNext = friendList.get(i + 1).getGroupName();
                int i3 = DealGroupAdd.getDealGroupAdd().stringToAscii(DealGroupAdd.getDealGroupAdd().getFirstABC(groupNameNext));
                if (i1 < i2 && i2 < i3) {
                    dealNoChartFriend(mRecord, friendList, (i + 1), chart, newRemarkName);
                    return;
                }
                else if (i1 > i2) {
                    dealNoChartFriend(mRecord, friendList, i, chart, newRemarkName);
                    return;
                }
                else if (i1 == i2){
                    dealTopOneHaveGroup(mRecord,i,chart,newRemarkName);
                    return;
                }
                else if (i1 == i3){
                    dealTopOneHaveGroup(mRecord,(i+1),chart,newRemarkName);
                    return;
                }
            }
            else if (i1 < i2) {
                dealNoChartFriend(mRecord, friendList, (i + 1), chart, newRemarkName);
                return;
            }
            else if (i1 > i2) {
                dealNoChartFriend(mRecord, friendList, i, chart, newRemarkName);
                return;
            }
            else if (i1 == i2){
                dealTopOneHaveGroup(mRecord,i,chart,newRemarkName);
            }
        }
    }
    private  void dealTopOneHaveGroup(DataModifyFriendList.RecordBean mRecord, int i, String groupName, String newRemarkName) {

        List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = friendList.get(i).getGroupList();
        DataLinkManList.RecordBean.FriendListBean.GroupListBean groupListBean = new DataLinkManList.RecordBean.FriendListBean.GroupListBean();
        groupListBean.setGroupName(groupName);
        String name = StrUtils.isEmpty(newRemarkName)? mRecord.getNewNickName() :  newRemarkName;
        groupListBean.setNickName(name);
        groupListBean.setGroupId(mRecord.getNewGroupId());
        groupListBean.setHeadImg(mRecord.getNewHeadImg());
        groupListBean.setRemarkName(mRecord.getRemarkName());
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
        EventBus.getDefault().post(new LinkChangeEvent(AppConfig.LINK_FRIEND_ADD_ACTION));
//        Intent intent = new Intent();
//        intent.setAction(AppConfig.LINK_FRIEND_ADD_ACTION);
//        mContext.sendBroadcast(intent);
    }
    private  void dealNoChartFriend(DataModifyFriendList.RecordBean mRecord, List<DataLinkManList.RecordBean.FriendListBean> friendList, int i, String chart, String newRemarkName) {
        List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = new ArrayList<>();

        DataLinkManList.RecordBean.FriendListBean.GroupListBean groupListBean = new DataLinkManList.RecordBean.FriendListBean.GroupListBean();
        groupListBean.setGroupName(chart);
        String name = StrUtils.isEmpty(newRemarkName)? mRecord.getNewNickName() :  newRemarkName;
        groupListBean.setNickName(name);
//        groupListBean.setNickName(mRecord.getNewNickName());
        groupListBean.setRemarkName(newRemarkName);
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
        EventBus.getDefault().post(new LinkChangeEvent(AppConfig.LINK_FRIEND_ADD_ACTION));
//        Intent intent = new Intent();
//        intent.setAction(AppConfig.LINK_FRIEND_ADD_ACTION);
//        mContext.sendBroadcast(intent);
    }
    private  void putCacheUpdate(DataModifyFriendList.RecordBean mRecord, int i, String newRemarkName) {
        List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = friendList.get(i).getGroupList();
        DataLinkManList.RecordBean.FriendListBean.GroupListBean groupListBean = new DataLinkManList.RecordBean.FriendListBean.GroupListBean();
        groupListBean.setRemarkName(newRemarkName);
        groupListBean.setGroupId(mRecord.getNewGroupId());
        groupListBean.setGroupName(mRecord.getNewGroupName());
        groupListBean.setHeadImg(mRecord.getNewHeadImg());
        String name = StrUtils.isEmpty(newRemarkName)? mRecord.getNewNickName() : newRemarkName;
        groupListBean.setNickName(name);
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
        EventBus.getDefault().post(new LinkChangeEvent(AppConfig.LINK_FRIEND_ADD_ACTION));
//        Intent intent = new Intent();
//        intent.setAction(AppConfig.LINK_FRIEND_ADD_ACTION);
//        mContext.sendBroadcast(intent);
    }
//    public   void modifyGroupOfFriend(Context context, String result){
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

    private  void initDataAdd(String asString, DataModifyFriendList.RecordBean mRecord) {
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
    private  void putCache(DataModifyFriendList.RecordBean mRecord, int i, String newGroupName) {

        List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = friendList.get(i).getGroupList();
        DataLinkManList.RecordBean.FriendListBean.GroupListBean groupListBean = new DataLinkManList.RecordBean.FriendListBean.GroupListBean();
        groupListBean.setGroupName(newGroupName);
        groupListBean.setGroupId(mRecord.getNewGroupId());
        groupListBean.setHeadImg(mRecord.getNewHeadImg());
        String name = StrUtils.isEmpty(mRecord.getRemarkName())? mRecord.getNewNickName() : mRecord.getNewRemarkName();
        groupListBean.setNickName(name);
//        groupListBean.setNickName(mRecord.getNewNickName());
        groupListBean.setRemarkName(mRecord.getNewRemarkName());
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
        Log.e("jsonString","不展开（好友添加至分组）="+jsonString);
        aCache.remove(AppAllKey.FRIEND_DATA);
        aCache.put(AppAllKey.FRIEND_DATA, jsonString);
        EventBus.getDefault().post(new LinkChangeEvent(AppConfig.LINK_FRIEND_ADD_ACTION));
//        Intent intent = new Intent();
//        intent.setAction(AppConfig.LINK_FRIEND_ADD_ACTION);
//        mContext.sendBroadcast(intent);
    }
    private  String initDataSub(String asString, DataModifyFriendList.RecordBean mRecord) {
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
        EventBus.getDefault().post(new LinkChangeEvent(AppConfig.LINK_FRIEND_DEL_ACTION));
        EventBus.getDefault().post(new MsgHomeEvent(AppConfig.LINK_FRIEND_DEL_ACTION));
//        Intent intent = new Intent();
//        intent.setAction(AppConfig.LINK_FRIEND_DEL_ACTION);
//        mContext.sendBroadcast(intent);
        return jsonString;
    }

}
