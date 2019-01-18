package com.doubleq.xm6leefunz.about_base.deal_application;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.doubleq.model.DataLinkGroupList;
import com.doubleq.model.push_data.DataAboutGroup;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.util.ArrayList;
import java.util.List;

public class DealGroupAdd {

    public  static ACache aCache;
    public  static Context mContext;
    public  static void updateGroupDataByAdd(Context montext,String result)
    {
        mContext=montext;
        DataAboutGroup dataAboutGroup = JSON.parseObject(result, DataAboutGroup.class);
        DataAboutGroup.RecordBean record = dataAboutGroup.getRecord();
        aCache =  ACache.get(mContext);
        if (aCache!=null)
        {
            String asString = aCache.getAsString(AppAllKey.GROUD_DATA);
            if (!StrUtils.isEmpty(asString)&&record!=null)
            {
                initDataGroup(asString,record);
            }
        }
    }

    private static void initDataGroup(String asString,DataAboutGroup.RecordBean mRecord) {
        DataLinkGroupList.RecordBean record = JSON.parseObject(asString, DataLinkGroupList.RecordBean.class);
        final List<DataLinkGroupList.RecordBean.GroupInfoListBean> group_info_list = record.getGroupInfoList();
        if (group_info_list.size()>0) {
            for (int i = 0; i < group_info_list.size(); i++) {
                String type = group_info_list.get(i).getType();
                if (type.equals("2")) {
                    String groupName = group_info_list.get(i).getGroupName();
//                if (group_info_list.get(i).getType().equals("1")) {
//                    if (StrUtils.isEmpty(userId)) {
//                        group_info_list.get(i).getGroupList().remove(0);
//                    }
//                    if (StrUtils.isEmpty(groupName)) {
//                        group_info_list.remove(i);
//                    }
//                }
                    String chat = mRecord.getChat();
                    String groupManageName = mRecord.getGroupManageName();
                    String groupManageId = mRecord.getGroupManageId();
                    if (chat != null && chat.equals(groupName)) {
                        putCache(mRecord, group_info_list, i, groupManageName);
                        return;
                    }
                    if (groupManageId != null && !groupManageId.equals("0")) {
                        if (groupManageName.equals(groupName)) {
                            putCache(mRecord, group_info_list, i, groupManageName);
                            return;
//                        List<DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean> groupList = group_info_list.get(i).getGroupList();
//                        DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean groupListBean = new DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean();
//                        groupListBean.setGroupName(groupManageName);
//                        groupListBean.setNickName(mRecord.getGroupName());
//                        groupListBean.setGroupOfId(mRecord.getGroupId());
//                        groupListBean.setHeadImg(mRecord.getGroupHeadImg());
//                        groupListBean.setGroupFenzuId(mRecord.getGroupManageId());
//                        groupList.add(groupListBean);
//                        group_info_list.get(i).setGroupList(groupList);
//                        DataLinkGroupList.RecordBean recordBean = new DataLinkGroupList.RecordBean();
//                        recordBean.setGroupInfoList(group_info_list);
//                        String jsonString = JSON.toJSONString(recordBean);
//                        aCache.remove(AppAllKey.GROUD_DATA);
//                        aCache.put(AppAllKey.GROUD_DATA, jsonString);
//                        Log.e("jsonString","闭合="+jsonString);
                        }
                    }
                }
            }
//            如果列表中没有当前的字母，则判断外围新增
                for (int i = 0; i < group_info_list.size(); i++)
                {
                    String type = group_info_list.get(i).getType();
                    if (type.equals("2")) {
                        String groupName = group_info_list.get(i).getGroupName();
                        String chat = mRecord.getChat();
                        int i1 = stringToAscii(getFirstABC(groupName));
                        int i2 = stringToAscii(getFirstABC(chat));
                        if (group_info_list.size() > (i + 1)) {
                            String groupNameNext = group_info_list.get(i + 1).getGroupName();
                            int i3 = stringToAscii(getFirstABC(groupNameNext));
                            if (i1 < i2 && i2 < i3) {
                                dealNoChart(mRecord, group_info_list, (i+1), chat);
                                return;
                            }
                        } else if (i1 < i2) {
                            dealNoChart(mRecord, group_info_list, (i+1), chat);
                            return;
                        } else if (i1 > i2) {
                            dealNoChart(mRecord, group_info_list, 0, chat);
                            return;
                        }
                    }
            }
        }
        else
        {
            String chat = mRecord.getChat();
            dealNoChart(mRecord, group_info_list, 0, chat);
            return;
        }
    }
    public static String getFirstABC(String pinyin)
    {
        if(pinyin.length()==0)
        {
            return pinyin;
        }
        String upperCase = pinyin.substring(0,1).toUpperCase();
        return upperCase;
    }
    private static void dealNoChart(DataAboutGroup.RecordBean mRecord, List<DataLinkGroupList.RecordBean.GroupInfoListBean> group_info_list, int i, String chat) {
        List<DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean> groupList = new ArrayList<>();

        DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean groupListBean = new DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean();
        groupListBean.setGroupName(chat);
        groupListBean.setNickName(mRecord.getGroupName());
        groupListBean.setGroupOfId(mRecord.getGroupId());
        groupListBean.setHeadImg(mRecord.getGroupHeadImg());
        groupListBean.setGroupFenzuId(mRecord.getGroupManageId());
        groupList.add(groupListBean);

        DataLinkGroupList.RecordBean.GroupInfoListBean groupInfoListBean = new DataLinkGroupList.RecordBean.GroupInfoListBean();
        groupInfoListBean.setType("2");
        groupInfoListBean.setGroupName(chat);
        groupInfoListBean.setGroupList(groupList);

        group_info_list.add(i,groupInfoListBean);

        DataLinkGroupList.RecordBean recordBean = new DataLinkGroupList.RecordBean();
        recordBean.setGroupInfoList(group_info_list);
        String jsonString = JSON.toJSONString(recordBean);
        Log.e("jsonString","原本没有本chart展开="+jsonString);
        aCache.remove(AppAllKey.GROUD_DATA);
        aCache.put(AppAllKey.GROUD_DATA, jsonString);

        Intent intent = new Intent();
        intent.setAction(AppConfig.LINK_GROUP_ADD_ACTION);
        mContext.sendBroadcast(intent);
    }

    public static int stringToAscii(String value)
    {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(i != chars.length - 1)
            {
                sbu.append((int)chars[i]).append(",");
            }
            else {
                sbu.append((int)chars[i]);
            }
        }
        return Integer.parseInt(sbu.toString());
    }
    private static void putCache(DataAboutGroup.RecordBean mRecord, List<DataLinkGroupList.RecordBean.GroupInfoListBean> group_info_list, int i, String groupManageName) {
        List<DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean> groupList = group_info_list.get(i).getGroupList();
        DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean groupListBean = new DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean();
        groupListBean.setGroupName(groupManageName);
        groupListBean.setNickName(mRecord.getGroupName());
        groupListBean.setGroupOfId(mRecord.getGroupId());
        groupListBean.setHeadImg(mRecord.getGroupHeadImg());
        groupListBean.setGroupFenzuId(mRecord.getGroupManageId());
        groupList.add(groupListBean);
        group_info_list.get(i).setGroupList(groupList);
//                    DataLinkGroupList.RecordBean.GroupInfoListBean groupInfoListBean = new DataLinkGroupList.RecordBean.GroupInfoListBean();
//                    groupInfoListBean.setGroupList(group_info_list);
        DataLinkGroupList.RecordBean recordBean = new DataLinkGroupList.RecordBean();
        recordBean.setGroupInfoList(group_info_list);

        String jsonString = JSON.toJSONString(recordBean);
//                    JSONObject object=new JSONObject();
//                    object.put("record",jsonString);

        Log.e("jsonString","展开="+jsonString);
        aCache.remove(AppAllKey.GROUD_DATA);
        aCache.put(AppAllKey.GROUD_DATA, jsonString);

        Intent intent = new Intent();
        intent.setAction(AppConfig.LINK_GROUP_ADD_ACTION);
        mContext.sendBroadcast(intent);
    }
}
