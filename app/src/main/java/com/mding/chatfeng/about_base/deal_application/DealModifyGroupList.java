package com.mding.chatfeng.about_base.deal_application;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_broadcastreceiver.LinkChangeEvent;
import com.mding.chatfeng.about_broadcastreceiver.MsgHomeEvent;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.RealmGroupHelper;
import com.mding.model.DataLinkGroupList;
import com.mding.model.DataModifyGroupOfList;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class DealModifyGroupList {

    private  static ACache aCache;
    private  static Context mContext;
    private static List<DataLinkGroupList.RecordBean.GroupInfoListBean> GroupList;

    private static boolean isOld(String old){
        boolean olds = StrUtils.isEmpty(old);
        boolean oldsZero = old.equals("0");
        return olds||oldsZero;
    }
    public  static void modifyGroupOfGroup(Context context, String result){
        mContext = context;
        DataModifyGroupOfList dataModifyGroupOfList = JSON.parseObject(result, DataModifyGroupOfList.class);
        DataModifyGroupOfList.RecordBean record = dataModifyGroupOfList.getRecord();
        aCache =  ACache.get(mContext);
        if (aCache!=null)
        {
            String asString = aCache.getAsString(AppAllKey.GROUD_DATA);
            if (!StrUtils.isEmpty(asString)&&record!=null)
            {
                //  1增   无到有
                if (isOld(record.getOldGroupManageId())&&!isOld(record.getNewGroupManageId()))
                {
                    Log.e("更改群分组（增）","----------------------------------------------------------------");
                    initDataAdd(asString,record);
                }
                //  2改  有到有
                if (!isOld(record.getOldGroupManageId())&&!isOld(record.getNewGroupManageId()))
                {
                    Log.e("更改群分组（改）","----------------------------------------------------------------");
                    String s = initDataSub(asString, record);
                    if (!StrUtils.isEmpty(s)){
                        String asString1 = aCache.getAsString(AppAllKey.GROUD_DATA);
                        initDataAdd(asString1,record);
                    }
                }
                //  3删  有到无
                if (!isOld(record.getOldGroupManageId())&&isOld(record.getNewGroupManageId()))
                {
                    Log.e("更改群分组（删）","----------------------------------------------------------------");
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

    private static void initDataAdd(String asString, DataModifyGroupOfList.RecordBean mRecord) {
        DataLinkGroupList.RecordBean record = JSON.parseObject(asString, DataLinkGroupList.RecordBean.class);
        if (record==null)
            return;
        GroupList = record.getGroupInfoList();
        String newGroupManageId = mRecord.getNewGroupManageId();
        String newGroupManageName = mRecord.getNewGroupManageName();
        //  若列表不为空
        if (GroupList.size() > 0){
            String groupId = mRecord.getGroupId();
            for (int i = 0; i < GroupList.size();i++) {
                Log.e("更改群分组（增）", "--------------------------i=" + i);
                List<DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean> groupListBeans = GroupList.get(i).getGroupList();
                if (GroupList.get(i).getType().equals("1")) {
                    if (GroupList.get(i).getGroupId() != null){
                        //  获取的新分组Id与当下的分组Id相同，则进行下一步判断
                        if (GroupList.get(i).getGroupId().equals(newGroupManageId)) {
                            //  分组里的好友（小列表）不为空，则进行添判断是否已经在该组中添加该好友
                            if (groupListBeans.size() > 0) {
                                //  判断该好友是否与该分组的成员相同，相同则不再添加
                                for (int j = 0; j < groupListBeans.size(); j++)
                                    if (groupListBeans.get(i).getGroupOfId().equals(groupId))
                                        return;
                            }
                            putCache(mRecord, i, newGroupManageName);
                            return;
                        }
                    }
                }
                //列表不为空则需要先查询是否存在该群,只操作type为2的群
//                if (GroupList.get(i).getType().equals("2"))
//                    initRealm(mRecord, i,true);
            }
        }else{
            putCache(mRecord, 0, newGroupManageName);
            //列表为空时，无需查询直接执行添加操作
//            initRealm(mRecord, 0, false);
        }
    }
//    //数据库操作
//    static RealmGroupHelper groupHelper;
//    private static void initRealm(DataModifyGroupOfList.RecordBean mRecord, int position, boolean isNeedQuery) {
//        groupHelper = new RealmGroupHelper(mContext);
////        List<DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean> group_info_list = GroupList;
//        String groupId = mRecord.getGroupId();
//        if (isNeedQuery){
//
//        }else {
//
//        }
//    }

    private static void putCache(DataModifyGroupOfList.RecordBean mRecord, int i, String newGroupManageName) {

        List<DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean> groupList = GroupList.get(i).getGroupList();
        DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean groupListBean = new DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean();
        groupListBean.setGroupName(newGroupManageName);
        groupListBean.setGroupFenzuId(mRecord.getNewGroupManageId());
        groupListBean.setHeadImg(mRecord.getNewGroupHeadImg());
        groupListBean.setNickName(mRecord.getNewGroupName());
        groupListBean.setModified(mRecord.getModified());
        groupListBean.setGroupOfId(mRecord.getGroupId());
        groupList.add(groupListBean);
        GroupList.get(i).setGroupList(groupList);
        GroupList.get(i).setType("1");
        GroupList.get(i).setGroupId(mRecord.getNewGroupManageId());
        GroupList.get(i).setGroupName(mRecord.getNewGroupManageName());
        DataLinkGroupList.RecordBean recordBean = new DataLinkGroupList.RecordBean();
        recordBean.setGroupInfoList(GroupList);
        String jsonString = JSON.toJSONString(recordBean);
        Log.e("jsonString","不展开（群添加至分组）="+jsonString);
        aCache.remove(AppAllKey.GROUD_DATA);
        aCache.put(AppAllKey.GROUD_DATA, jsonString);
        EventBus.getDefault().post(new LinkChangeEvent(AppConfig.LINK_GROUP_ADD_ACTION));
//        Intent intent = new Intent();
//        intent.setAction(AppConfig.LINK_GROUP_ADD_ACTION);
//        mContext.sendBroadcast(intent);
    }

    private static String initDataSub(String asString, DataModifyGroupOfList.RecordBean mRecord) {
        DataLinkGroupList.RecordBean record = JSON.parseObject(asString, DataLinkGroupList.RecordBean.class);
        if (record==null)
            return null;
        Log.e("更改群分组（删）","--------------------------------initDataSub--------------------------------");
        GroupList = record.getGroupInfoList();
        String oldGroupId = mRecord.getOldGroupManageId();
        //  若列表不为空
        if (GroupList.size() > 0){
            String groupOfId = mRecord.getGroupId();
            for (int i = 0; i < GroupList.size();i++) {
                DataLinkGroupList.RecordBean.GroupInfoListBean groupInfoListBean = GroupList.get(i);
                List<DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean> groupList = groupInfoListBean.getGroupList();
                if (groupInfoListBean.getType().equals("1")) {
//                    列表分组名
//                    String groupName = friendListBean.getGroupName();
//                    分组Id
                    String groupId = groupInfoListBean.getGroupId();
                    if (groupId != null){
                        if (groupId.equals(oldGroupId)) {
                            if (groupList.size()>0)
                            {
                                for (int j = 0; j < groupList.size(); j++) {
                                    String GroupId = groupList.get(j).getGroupOfId();
                                    if (groupOfId.equals(GroupId)) {
                                        GroupList.get(i).getGroupList().remove(j);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
//        修改数据保存cache
        DataLinkGroupList.RecordBean recordBean = new DataLinkGroupList.RecordBean();
        recordBean.setGroupInfoList(GroupList);

        String jsonString = JSON.toJSONString(recordBean);
        Log.e("jsonString","更改群分组（删）="+jsonString);
        aCache.remove(AppAllKey.GROUD_DATA);
        aCache.put(AppAllKey.GROUD_DATA, jsonString);
        EventBus.getDefault().post(new LinkChangeEvent(AppConfig.LINK_GROUP_DEL_ACTION));
        EventBus.getDefault().post(new MsgHomeEvent(AppConfig.LINK_GROUP_DEL_ACTION));
//        Intent intent = new Intent();
//        intent.setAction(AppConfig.LINK_GROUP_DEL_ACTION);
//        mContext.sendBroadcast(intent);
        return jsonString;
    }

}
