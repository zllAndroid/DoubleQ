package com.mding.chatfeng.about_base.deal_application;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_broadcastreceiver.LinkChangeEvent;
import com.mding.chatfeng.about_broadcastreceiver.MsgHomeEvent;
import com.mding.chatfeng.about_utils.JsonUtils;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.CusDataFriendRelation;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.CusDataFriendUser;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.RealmFriendRelationHelper;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.RealmFriendUserHelper;
import com.mding.model.DataLinkManList;
import com.mding.model.push_data.DataAboutFriend;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class DealFriendAdd {
    private   ACache aCache;
    private   Context mContext;
    static RealmFriendRelationHelper friendHelper;
    static RealmFriendUserHelper friendUserHelper;
    private static DealFriendAdd dealFriendAdd;
    // 构造函数必须是私有的 这样在外部便无法使用 new 来创建该类的实例
    private DealFriendAdd() {}
    /**
     * 单一实例
     */
    public synchronized static DealFriendAdd getDealFriendAdd() {
        if (dealFriendAdd == null) {
            dealFriendAdd = new DealFriendAdd();
        }
        return dealFriendAdd;
    }
    public   void updateFriendDataByAdd(Context montext,String result) {
        if (friendHelper==null)
        friendHelper = new RealmFriendRelationHelper(mContext);
        if (friendUserHelper==null)
        friendUserHelper = new RealmFriendUserHelper(mContext);
        mContext=montext;
        DataAboutFriend dataAboutFriend = JSON.parseObject(result, DataAboutFriend.class);
        DataAboutFriend.RecordBean record = dataAboutFriend.getRecord();
        aCache =  ACache.get(mContext);
        if (aCache!=null)
        {
            String asString = aCache.getAsString(AppAllKey.FRIEND_DATA);
            if (!StrUtils.isEmpty(asString) && record!=null)
            {
                try {
                    initDataFriend(asString,record);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (record!=null)
            {
                CusDataFriendUser cusDataFriendUser = friendUserHelper.queryLinkFriend(record.getFriendsId());
                CusDataFriendRelation cusDataFriendRelation = friendHelper.queryLinkFriend(record.getFriendsId());
                String modified = record.getModified();
                if (StrUtils.isEmpty(record.getHeadImg()))
                {
                    return;
                }
                //用户信息存库（用户表）
                if(cusDataFriendUser!=null)
                {
                    String time = cusDataFriendUser.getTime();
                    if ( !modified.equals(time))
                    {
                        setUserData(true,record);
                    }
                }else
                {
                    setUserData(false,record);
                }

                //好友关系列表
                if (cusDataFriendRelation!=null) {
                    String time = cusDataFriendRelation.getCreated();
                    if ( !modified.equals(time))
                    {
                        setFriendRealm(true,record);
                    }
                }else {
                    setFriendRealm(false,record);
                }
            }
        }
    }
    //设置好友信息
    private void setFriendRealm(boolean isUpData,DataAboutFriend.RecordBean groupListBean) {
        CusDataFriendRelation cusDataFriendRelation = new CusDataFriendRelation();
        cusDataFriendRelation.setHeadImg(groupListBean.getHeadImg());
        cusDataFriendRelation.setNickName(groupListBean.getNickName());
        cusDataFriendRelation.setFriendId(groupListBean.getFriendsId());
        cusDataFriendRelation.setGroupId(groupListBean.getGroupId());
        cusDataFriendRelation.setModified(groupListBean.getModified());
        cusDataFriendRelation.setRemarkName(groupListBean.getRemarkName());
        if (isUpData)
        {
//            更新该好友全部内容
            friendHelper.updateAll(groupListBean.getFriendsId(),cusDataFriendRelation);
        }else
        {
//            添加该好友信息
            friendHelper.addRealmLinkFriend(cusDataFriendRelation);
        }
    }
    private void setUserData(boolean isUpData,DataAboutFriend.RecordBean groupListBean) {
        CusDataFriendUser cusDataFriendUser = new CusDataFriendUser();
        cusDataFriendUser.setFriendId(groupListBean.getFriendsId());
        cusDataFriendUser.setHeadImgBase64(groupListBean.getHeadImg());
        cusDataFriendUser.setName(groupListBean.getNickName());
        cusDataFriendUser.setRemarkName(groupListBean.getRemarkName());
        cusDataFriendUser.setTime(groupListBean.getModified());
//        TODO 添加二维码数据
//        cusDataFriendUser.setErWeiCode(groupListBean.get());
        if(isUpData)
        {
            friendUserHelper.updateAll(groupListBean.getFriendsId(),cusDataFriendUser);
        }else
        {
            friendUserHelper.addRealmFriendUser(cusDataFriendUser);
        }
    }
    public   void updateFriendDataBySub(Context montext,String result) {
        if (friendHelper==null)
            friendHelper = new RealmFriendRelationHelper(mContext);
        if (friendUserHelper==null)
            friendUserHelper = new RealmFriendUserHelper(mContext);
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
                    initDataFriendSub(asString,record);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (record!=null)
            {
                friendHelper.deleteRealmFriend(record.getFriendsId());
                friendUserHelper.deleteRealmFriend(record.getFriendsId());
            }

        }
    }

    private  void initDataFriendSub(String asString, DataAboutFriend.RecordBean mRecord) {
        DataLinkManList.RecordBean record = JSON.parseObject(asString, DataLinkManList.RecordBean.class);
        if (record==null)
            return;
        friendList = record.getFriendList();
        String chart = mRecord.getChart();
        String mRecordGroupName = mRecord.getGroupName();
        String groupId = mRecord.getGroupId();
        if (friendList.size() > 0) {
            String friendsId = mRecord.getFriendsId();
            for (int i = 0; i < friendList.size(); i++) {
                String type = friendList.get(i).getType();
                String groupName = friendList.get(i).getGroupName();
                List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = friendList.get(i).getGroupList();
                if (type.equals("2")) {
                    if (chart != null && chart.equals(groupName)) {
                        if (groupList.size() == 0) {
//                            return;
                        } else if (groupList.size() == 1) {
                            friendList.remove(i);
                        } else if (groupList.size() > 1) {
                            for (int j = 0; j < groupList.size(); j++) {
                                String userId = groupList.get(j).getUserId();
                                if (friendsId.equals(userId)) {
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

//            String jsonString = JSON.toJSONString(recordBean);
            String jsonString = JsonUtils.toChangeJson(recordBean);//将java对象转换为json对象
            Log.e("jsonString","减少="+jsonString);
            aCache.remove(AppAllKey.FRIEND_DATA);
            aCache.put(AppAllKey.FRIEND_DATA, jsonString);
            EventBus.getDefault().post(new LinkChangeEvent(AppConfig.LINK_FRIEND_DEL_ACTION));
            EventBus.getDefault().post(new MsgHomeEvent(AppConfig.LINK_FRIEND_DEL_ACTION));
//            Intent intent = new Intent();
//            intent.setAction(AppConfig.LINK_FRIEND_DEL_ACTION);
//            mContext.sendBroadcast(intent);
        }
    }
    private  boolean isHaveTypeTwo = true;
    private  List<DataLinkManList.RecordBean.FriendListBean> friendList;
    private  void initDataFriend(String asString, DataAboutFriend.RecordBean mRecord) {
        boolean isTopOne = true;
        boolean isTopTwo = true;
        DataLinkManList.RecordBean record = JSON.parseObject(asString, DataLinkManList.RecordBean.class);
        if (record == null)
            return;
        friendList = record.getFriendList();
        String chart = mRecord.getChart();  //新加好友的首字母
        String groupManageName = mRecord.getGroupName();  //新加好友的分组名
        String groupId = mRecord.getGroupId();  //新加好友的分组id
        if (friendList.size() > 0) {
            for (int i = 0; i < friendList.size(); i++) {
                String type = friendList.get(i).getType();  //已存在的列表中的type
                if (type.equals("2")) {
                    isHaveTypeTwo = false;
                    break;
                }
            }
            if (isHaveTypeTwo) {
                for (int i = 0; i < friendList.size(); i++) {
                    String groupName = friendList.get(i).getGroupName();  //已存在的列表中的分组名
                    String type = friendList.get(i).getType();  //已存在的列表中的type
                    if (groupId != null && !groupId.equals("0") && type.equals("1")) {
                        if (groupManageName != null && groupManageName.equals(groupName)) {
                            dealTopOneHaveGroup(mRecord, i, groupManageName, "1");
                        }
                    }
                }
                dealNoChartFriend(mRecord, friendList, friendList.size(), chart);
            }
            else {
                for (int i = 0; i < friendList.size(); i++) {
                    String groupName = friendList.get(i).getGroupName();  //已存在的列表中的分组名
                    String type = friendList.get(i).getType();  //已存在的列表中的type
                    // type 1   分组id不为空 && 不为0  && type为1
                    if (groupId != null && !groupId.equals("0") && type.equals("1")) {
                        if (groupManageName != null && groupManageName.equals(groupName)) {
                            dealTopOneHaveGroup(mRecord, i, groupManageName, "1");
                            isTopOne = false;
                        }
                    }
                    // type2   首字母不为空 && 首字母与已存在的列表中的分组名相同 && type为2
                    if (chart != null && chart.equals(groupName) && type.equals("2")) {
                        dealTopOneHaveGroup(mRecord, i, chart, "2");
                        saveData();
                        isTopTwo = false;
                    }
//                //  type2  有type1无type2时
//                else if (!StrUtils.isEmpty(chart)){
//                    dealNoChartFriend(mRecord, friendList, friendList.size(), chart);
//                    return;
//                }
                }
                if (isTopTwo || isTopOne)
                    for (int i = 0; i < friendList.size(); i++) {
                        String type = friendList.get(i).getType();
                        if (type.equals("1") && isTopOne && groupId != null && !groupId.equals("0")) {
                            addTopTypeOne(mRecord, 0);
                        } else if (type.equals("2") && isTopTwo) {
                            String groupName = friendList.get(i).getGroupName();
                            int i1 = DealGroupAdd.getDealGroupAdd().stringToAscii(DealGroupAdd.getDealGroupAdd().getFirstABC(groupName));
                            int i2 = DealGroupAdd.getDealGroupAdd().stringToAscii(DealGroupAdd.getDealGroupAdd().getFirstABC(chart));
                            if (friendList.size() > (i + 1)) {
                                String groupNameNext = friendList.get(i + 1).getGroupName();
                                int i3 = DealGroupAdd.getDealGroupAdd().stringToAscii(DealGroupAdd.getDealGroupAdd().getFirstABC(groupNameNext));
                                if (i1 < i2 && i2 < i3) {
                                    dealNoChartFriend(mRecord, friendList, (i + 1), chart);
                                    return;
                                } else if (i1 > i2) {
                                    dealNoChartFriend(mRecord, friendList, i, chart);
                                    return;
                                }
                            } else if (i1 < i2) {
                                dealNoChartFriend(mRecord, friendList, (i + 1), chart);
                                return;
                            } else if (i1 > i2) {
                                dealNoChartFriend(mRecord, friendList, i, chart);
                                return;
                            }
                        }
                    }
            }
        }
        else {
//            if (!StrUtils.isEmpty(groupId)&& !groupId.equals("0"))
//            {
//                addTopTypeOne(mRecord,0);
//            }
            dealNoChartFriend(mRecord, friendList, 0, chart);
            return;
        }
        saveData();
    }

    private  void dealTopOneHaveGroup(DataAboutFriend.RecordBean mRecord, int i, String groupName,String type) {

        List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = friendList.get(i).getGroupList();
        DataLinkManList.RecordBean.FriendListBean.GroupListBean groupListBean = new DataLinkManList.RecordBean.FriendListBean.GroupListBean();
        groupListBean.setGroupName(groupName);
        groupListBean.setNickName(mRecord.getNickName());
        groupListBean.setGroupId(mRecord.getGroupId());
        groupListBean.setHeadImg(mRecord.getHeadImg());
        groupListBean.setRemarkName(mRecord.getRemarkName());
        groupListBean.setModified(mRecord.getModified());
        groupListBean.setUserId(mRecord.getFriendsId());
        groupList.add(groupListBean);
        friendList.get(i).setGroupList(groupList);
        friendList.get(i).setType(type);
        friendList.get(i).setGroupName(groupName);
        friendList.get(i).setGroupId(mRecord.getGroupId());
        DataLinkManList.RecordBean recordBean = new DataLinkManList.RecordBean();
        recordBean.setFriendList(friendList);
//        String jsonString = JSON.toJSONString(recordBean);
        String jsonString = JsonUtils.toChangeJson(recordBean);//将java对象转换为json对象
        aCache.remove(AppAllKey.FRIEND_DATA);
        aCache.put(AppAllKey.FRIEND_DATA, jsonString);
    }

    //最后保存数据，并发送广播
    private  void saveData() {
//        DataLinkManList.RecordBean recordBean = new DataLinkManList.RecordBean();
//        recordBean.setFriendList(friendList);
//        String jsonString = JSON.toJSONString(recordBean);
//        aCache.remove(AppAllKey.FRIEND_DATA);
//        aCache.put(AppAllKey.FRIEND_DATA, jsonString);
//        Intent intent = new Intent();
//        intent.setAction(AppConfig.LINK_FRIEND_ADD_ACTION);
//        mContext.sendBroadcast(intent);
        EventBus.getDefault().post(new LinkChangeEvent(AppConfig.LINK_FRIEND_ADD_ACTION));
    }

    //添加顶部无分组时，数据
    private  void addTopTypeOne(DataAboutFriend.RecordBean mRecord,int where) {
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
        friendList.get(where).setGroupName(mRecord.getGroupName());
        friendList.get(where).setGroupId(mRecord.getGroupId());
        friendList.get(where).setType("1");
        DataLinkManList.RecordBean recordBean = new DataLinkManList.RecordBean();
        recordBean.setFriendList(friendList);
//        String jsonString = JSON.toJSONString(recordBean);
        String jsonString = JsonUtils.toChangeJson(recordBean);//将java对象转换为json对象
        aCache.remove(AppAllKey.FRIEND_DATA);
        aCache.put(AppAllKey.FRIEND_DATA, jsonString);
    }

    //存放type2时候，列表中没有当前组名；
    private  void dealNoChartFriend(DataAboutFriend.RecordBean mRecord, List<DataLinkManList.RecordBean.FriendListBean> friendList, int i, String chart) {
        List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = new ArrayList<>();

        DataLinkManList.RecordBean.FriendListBean.GroupListBean groupListBean = new DataLinkManList.RecordBean.FriendListBean.GroupListBean();
        groupListBean.setGroupName(chart);
        groupListBean.setNickName(mRecord.getNickName());
        groupListBean.setGroupId(mRecord.getGroupId());
        groupListBean.setHeadImg(mRecord.getHeadImg());
        groupListBean.setRemarkName(mRecord.getRemarkName());
        groupListBean.setModified(mRecord.getModified());
        groupListBean.setUserId(mRecord.getFriendsId());
        groupList.add(groupListBean);

        DataLinkManList.RecordBean.FriendListBean friendListBean = new DataLinkManList.RecordBean.FriendListBean();
        friendListBean.setType("2");
        friendListBean.setGroupName(chart);
        friendListBean.setGroupList(groupList);
        friendListBean.setGroupId(mRecord.getGroupId());

        friendList.add(i,friendListBean);

        DataLinkManList.RecordBean recordBean = new DataLinkManList.RecordBean();
        recordBean.setFriendList(friendList);
//        String jsonString = JSON.toJSONString(recordBean);
        String jsonString = JsonUtils.toChangeJson(recordBean);//将java对象转换为json对象
        aCache.remove(AppAllKey.FRIEND_DATA);
        aCache.put(AppAllKey.FRIEND_DATA, jsonString);
        EventBus.getDefault().post(new LinkChangeEvent(AppConfig.LINK_FRIEND_ADD_ACTION));
//        Intent intent = new Intent();
//        intent.setAction(AppConfig.LINK_FRIEND_ADD_ACTION);
//        mContext.sendBroadcast(intent);

    }
}