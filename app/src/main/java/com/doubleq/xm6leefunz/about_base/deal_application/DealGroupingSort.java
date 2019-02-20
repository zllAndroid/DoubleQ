package com.doubleq.xm6leefunz.about_base.deal_application;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.doubleq.model.DataLinkGroupList;
import com.doubleq.model.DataLinkManList;
import com.doubleq.model.DataModifyGroupingSort;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.util.List;

public class DealGroupingSort {

    private  static ACache aCache;
    private  static Context mContext;
    private static List<DataLinkManList.RecordBean.FriendListBean> friendList;
    private static List<DataLinkGroupList.RecordBean.GroupInfoListBean> groupList;
    public  static void groupingSort(Context context, String result){
        mContext = context;
        DataModifyGroupingSort dataModifyGroupingSort = JSON.parseObject(result,DataModifyGroupingSort.class);
        DataModifyGroupingSort.RecordBean recordBean = dataModifyGroupingSort.getRecord();
        List<DataModifyGroupingSort.RecordBean.OldGroupBean> oldGroupBeanList = recordBean.getOldGroup();
        List<DataModifyGroupingSort.RecordBean.NewGroupBean> newGroupBeanList = recordBean.getNewGroup();
//        String oldGroupId = oldGroupBeanList.get(0).getGroupId();
        aCache =  ACache.get(mContext);
        if (aCache!=null) {
            if (recordBean.getOldGroup().get(0).getType().equals("1")){
                String asString = aCache.getAsString(AppAllKey.FRIEND_DATA);
//                if (!StrUtils.isEmpty(asString) && recordBean!=null)
                if (!StrUtils.isEmpty(asString))
                {
                    initDataFriend(asString, oldGroupBeanList, newGroupBeanList);
                }
            }else{
                String asString = aCache.getAsString(AppAllKey.GROUD_DATA);
//                if (!StrUtils.isEmpty(asString) && recordBean!=null)
                if (!StrUtils.isEmpty(asString))
                {
                    initDataGroup(asString, oldGroupBeanList, newGroupBeanList);
                }
            }

        }
    }
    private static void initDataFriend(String asString, List<DataModifyGroupingSort.RecordBean.OldGroupBean> old_group_list,
                                       List<DataModifyGroupingSort.RecordBean.NewGroupBean> new_group_list) {
        Log.e("asString","--------------------------------------"+asString);
        DataLinkManList.RecordBean recordBean= JSON.parseObject(asString,DataLinkManList.RecordBean.class);
        List<DataLinkManList.RecordBean.FriendListBean> friendListBeanList= recordBean.getFriendList();
//        DataLinkManList.RecordBean.FriendListBean friendListBean;
        if (friendListBeanList==null)
            return;
        friendList = recordBean.getFriendList();
        if (friendListBeanList.size() > 0) {
            for (int i = 0; i < old_group_list.size(); i++) {
                String oldGroupId = old_group_list.get(i).getGroupId();
                for (int j = 0; j < new_group_list.size(); j++){
                    String newGroupId = new_group_list.get(j).getGroupId();
                    if (oldGroupId.equals(newGroupId)) {
//                        friendListBean = friendListBeanList.get(i);
                        friendModify(i, j);
                        putFriendCache(friendList);
//                        Log.e("jsonString","--------------------------------------"+friendList);
                    }
                }
            }

        }
    }
    private static void friendModify( int i, int j) {
//        将新位置原本的数据(旧数据)存起来
        DataLinkManList.RecordBean.FriendListBean listBeanI = friendList.get(i);
        DataLinkManList.RecordBean.FriendListBean listBeanJ = friendList.get(j);
        Log.e("jsonString","------------原始数据----------------"+listBeanI);
        Log.e("jsonString","------------111----------------"+listBeanJ);
//        删除新位置的数据（旧数据）
//        friendList.remove(j);
        friendList.set(j,listBeanI);
//        在新位置插入新数据
//        friendList.add(j,friendListBean);
        Log.e("jsonString","-------------222---------------"+friendList.get(j));
//        删除旧位置的数据（新数据）
//        friendList.remove(i);
//        在旧位置插入旧数据
        if (j == 0){
            friendList.add(j,listBeanI);
            Log.e("jsonString","------------333----------------"+friendList.get(j));
        }
        else{
            friendList.add(j-1,listBeanI);
            Log.e("jsonString","------------333----------------"+friendList.get(j-1));
        }

    }

    private static void putFriendCache(List<DataLinkManList.RecordBean.FriendListBean> friendList) {
        DataLinkManList.RecordBean recordBean = new DataLinkManList.RecordBean();
        recordBean.setFriendList(friendList);
        String jsonString = JSON.toJSONString(recordBean);
        aCache.remove(AppAllKey.FRIEND_DATA);
        aCache.put(AppAllKey.FRIEND_DATA, jsonString);
        Log.e("jsonString","分组排序（修改）="+jsonString);

        Intent intent = new Intent();
        intent.setAction(AppConfig.LINK_FRIEND_ADD_ACTION);
        mContext.sendBroadcast(intent);
    }

    private static void putCacheGroupModify(List<DataLinkManList.RecordBean.FriendListBean> friend_info_list) {

    }

    private static void initDataGroup(String asString,  List<DataModifyGroupingSort.RecordBean.OldGroupBean> old_group_list,
                                      List<DataModifyGroupingSort.RecordBean.NewGroupBean> new_group_list) {

    }
}
