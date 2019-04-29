package com.mding.chatfeng.about_base.deal_application;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_broadcastreceiver.LinkChangeEvent;
import com.mding.chatfeng.about_utils.JsonUtils;
import com.mding.model.DataLinkGroupList;
import com.mding.model.DataLinkManList;
import com.mding.model.DataModifyGroupingSort;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class DealGroupingSort {

    private  static ACache aCache;
    private  static Context mContext;
    private static List<DataLinkManList.RecordBean.FriendListBean> friendSortList=new ArrayList<>();
    private static List<DataLinkManList.RecordBean.FriendListBean> fListType2=new ArrayList<>();
    private static List<DataLinkGroupList.RecordBean.GroupInfoListBean> groupSortList=new ArrayList<>();
    private static List<DataLinkGroupList.RecordBean.GroupInfoListBean> gListType2=new ArrayList<>();

    public  static void groupingSort(Context context, String result){
        mContext = context;
        DataModifyGroupingSort dataModifyGroupingSort = JSON.parseObject(result,DataModifyGroupingSort.class);
        DataModifyGroupingSort.RecordBean recordBean = dataModifyGroupingSort.getRecord();
        List<DataModifyGroupingSort.RecordBean.OldGroupBean> oldGroupBeanList = recordBean.getOldGroup();
        List<DataModifyGroupingSort.RecordBean.NewGroupBean> newGroupBeanList = recordBean.getNewGroup();
//        String oldGroupId = oldGroupBeanList.get(0).getGroupId();
        aCache =  ACache.get(mContext);
        if (aCache!=null) {
//            判断是好友还是群分组排序  1为好友  2为群
            if (recordBean.getOldGroup().get(0).getType().equals("1")){
                String asString = aCache.getAsString(AppAllKey.FRIEND_DATA);
                if (!StrUtils.isEmpty(asString))
                {
                    initDataFriend(asString, oldGroupBeanList, newGroupBeanList);
                }
            }else{
                String asString = aCache.getAsString(AppAllKey.GROUD_DATA);
                if (!StrUtils.isEmpty(asString))
                {
                    initDataGroup(asString, oldGroupBeanList, newGroupBeanList);
                }
            }

        }
    }
    private static void initDataFriend(String asString, List<DataModifyGroupingSort.RecordBean.OldGroupBean> old_group_list,
                                       List<DataModifyGroupingSort.RecordBean.NewGroupBean> new_group_list) {
        DataLinkManList.RecordBean recordBean= JSON.parseObject(asString,DataLinkManList.RecordBean.class);
//        旧的联系人列表：friendListBeanList
        List<DataLinkManList.RecordBean.FriendListBean> friendListBeanList= recordBean.getFriendList();
        if (friendListBeanList==null)
            return;
//        设置friendSortList新的空list来存放总的新列表
        friendSortList.clear();
//        设置fListType2来存放type=2的成员，即分组以下的好友
        fListType2.clear();
        if (friendListBeanList.size() > 0) {
            int size = old_group_list.size();
//            把type=2的成员存入列表
            for (int w=size;w<friendListBeanList.size();w++)
            {
                fListType2.add(friendListBeanList.get(w));
            }
//            将新序list每一行的群id与旧序list每一行的群id进行对比
            for (int j = 0; j < new_group_list.size(); j++){
                String newGroupId = new_group_list.get(j).getGroupId();
                for (int i = 0; i < old_group_list.size(); i++) {
                    String oldGroupId = old_group_list.get(i).getGroupId();
//                    若相等，则将该行数据存入当前位置j的列表中（即将旧序list中该分组中的好友放入新序分组中）
                    if (oldGroupId.equals(newGroupId)) {
                        DataLinkManList.RecordBean.FriendListBean listBeanI = friendListBeanList.get(i);
                        friendSortList.add(listBeanI);
                        Log.e("jsonString","--------------------------------------------- i=" + i + ", j=" + j);
                    }
                }
            }
//            存储数据并且发送广播
            putFriendCache();
        }
    }

    private static void putFriendCache() {
//        将
        friendSortList.addAll(fListType2);

        DataLinkManList.RecordBean recordBean = new DataLinkManList.RecordBean();
        recordBean.setFriendList(friendSortList);
//        String jsonString = JSON.toJSONString(recordBean);
        String jsonString = JsonUtils.toChangeJson(recordBean);//将java对象转换为json对象
        aCache.remove(AppAllKey.FRIEND_DATA);
        aCache.put(AppAllKey.FRIEND_DATA, jsonString);
        Log.e("jsonString","分组排序（修改）="+jsonString);
        EventBus.getDefault().post(new LinkChangeEvent(AppConfig.LINK_FRIEND_ADD_ACTION));
//        Intent intent = new Intent();
//        intent.setAction(AppConfig.LINK_FRIEND_ADD_ACTION);
//        mContext.sendBroadcast(intent);
    }

    private static void initDataGroup(String asString,  List<DataModifyGroupingSort.RecordBean.OldGroupBean> old_group_list,
                                      List<DataModifyGroupingSort.RecordBean.NewGroupBean> new_group_list) {
        DataLinkGroupList.RecordBean recordBean= JSON.parseObject(asString,DataLinkGroupList.RecordBean.class);
        List<DataLinkGroupList.RecordBean.GroupInfoListBean> groupInfoListBeans= recordBean.getGroupInfoList();
        if (groupInfoListBeans==null)
            return;
        groupSortList.clear();
        gListType2.clear();
        if (groupInfoListBeans.size() > 0) {
            int size = old_group_list.size();
            for (int w=size;w<groupInfoListBeans.size();w++)
            {
                gListType2.add(groupInfoListBeans.get(w));
            }
            for (int j = 0; j < new_group_list.size(); j++){
                String newGroupId = new_group_list.get(j).getGroupId();
                for (int i = 0; i < old_group_list.size(); i++) {
                    String oldGroupId = old_group_list.get(i).getGroupId();
                    if (oldGroupId.equals(newGroupId)) {
                        DataLinkGroupList.RecordBean.GroupInfoListBean listBeanI = groupInfoListBeans.get(i);
                        groupSortList.add(listBeanI);
                    }
                }
            }
            putGroupCache();
        }
    }
    private static void putGroupCache() {
        groupSortList.addAll(gListType2);

        DataLinkGroupList.RecordBean recordBean = new DataLinkGroupList.RecordBean();
        recordBean.setGroupInfoList(groupSortList);
//        String jsonString = JSON.toJSONString(recordBean);
        String jsonString = JsonUtils.toChangeJson(recordBean);//将java对象转换为json对象
        aCache.remove(AppAllKey.GROUD_DATA);
        aCache.put(AppAllKey.GROUD_DATA, jsonString);
        Log.e("jsonString","分组排序（修改）="+jsonString);
        EventBus.getDefault().post(new LinkChangeEvent(AppConfig.LINK_GROUP_ADD_ACTION));
//        Intent intent = new Intent();
//        intent.setAction(AppConfig.LINK_GROUP_ADD_ACTION);
//        mContext.sendBroadcast(intent);
    }

}
