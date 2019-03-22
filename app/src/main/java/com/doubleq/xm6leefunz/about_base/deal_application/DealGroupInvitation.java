package com.doubleq.xm6leefunz.about_base.deal_application;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.doubleq.model.DataLinkGroupList;
import com.doubleq.model.push_data.DataInvitationGroupList;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.util.ArrayList;
import java.util.List;

import static com.doubleq.xm6leefunz.about_base.deal_application.DealGroupAdd.getFirstABC;
import static com.doubleq.xm6leefunz.about_base.deal_application.DealGroupAdd.stringToAscii;

public class DealGroupInvitation {
    private static String jsonString;
    public  static ACache aCache;
    public  static Context mContext;
    public  static void updateGroupDataByInvitation(Context context,String result)
    {
        mContext=context;
        DataInvitationGroupList dataInvitationGroupList = JSON.parseObject(result, DataInvitationGroupList.class);
        DataInvitationGroupList.RecordBean record = dataInvitationGroupList.getRecord();
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

    private static boolean isHaveTypeTwo = true;
    private static void initDataGroup(String asString, DataInvitationGroupList.RecordBean mRecord) {
        DataLinkGroupList.RecordBean record = JSON.parseObject(asString, DataLinkGroupList.RecordBean.class);
        final List<DataLinkGroupList.RecordBean.GroupInfoListBean> group_info_list = record.getGroupInfoList();
        if (group_info_list.size()>0) {
            for (int i = 0; i < group_info_list.size(); i++) {
                String type = group_info_list.get(i).getType();  //已存在的列表中的type
                if (type.equals("2")) {
                    isHaveTypeTwo = false;
                    break;
                }
            }
            String chart = mRecord.getChart();
            if (isHaveTypeTwo) {
                dealNoChart(mRecord, group_info_list, group_info_list.size(), chart);
            } else {
                for (int i = 0; i < group_info_list.size(); i++) {
                    String type = group_info_list.get(i).getType();
                    if (type.equals("2")) {
                        String groupName = group_info_list.get(i).getGroupName();
                        String groupManageName = mRecord.getGroupManageName();
                        String groupManageId = mRecord.getGroupManageId();
                        if (chart != null && chart.equals(groupName)) {
                            putCache(mRecord, group_info_list, i, groupManageName);
                            return;
                        }
                    }
                }
//            如果列表中没有当前的字母，则判断外围新增
                for (int i = 0; i < group_info_list.size(); i++) {
                    String type = group_info_list.get(i).getType();
                    if (type.equals("2")) {
                        String groupName = group_info_list.get(i).getGroupName();
                        int i1 = stringToAscii(getFirstABC(groupName));
                        int i2 = stringToAscii(getFirstABC(chart));
                        if (group_info_list.size() > (i + 1)) {
                            String groupNameNext = group_info_list.get(i + 1).getGroupName();
                            int i3 = stringToAscii(getFirstABC(groupNameNext));
                            if (i1 < i2 && i2 < i3) {
                                dealNoChart(mRecord, group_info_list, (i + 1), chart);
                                return;
                            } else if (i1 > i2) {
                                dealNoChart(mRecord, group_info_list, i, chart);
                                return;
                            }
                        } else if (i1 < i2) {
                            dealNoChart(mRecord, group_info_list, (i + 1), chart);
                            return;
                        } else if (i1 > i2) {
                            dealNoChart(mRecord, group_info_list, i, chart);
                            return;
                        }
                    }
                }
            }
        }
        else
        {
            String chart = mRecord.getChart();
            dealNoChart(mRecord, group_info_list, 0, chart);
            return;
        }
    }

    private static void dealNoChart(DataInvitationGroupList.RecordBean mRecord, List<DataLinkGroupList.RecordBean.GroupInfoListBean> group_info_list, int i, String chart) {
        List<DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean> groupList = new ArrayList<>();

        DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean groupListBean = new DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean();
        groupListBean.setGroupName(chart);
        groupListBean.setNickName(mRecord.getGroupName());
        groupListBean.setGroupOfId(mRecord.getGroupId());
        groupListBean.setHeadImg(mRecord.getGroupHeadImg());
        groupListBean.setGroupFenzuId(mRecord.getGroupManageId());
        groupList.add(groupListBean);

        DataLinkGroupList.RecordBean.GroupInfoListBean groupInfoListBean = new DataLinkGroupList.RecordBean.GroupInfoListBean();
        groupInfoListBean.setType("2");
        groupInfoListBean.setGroupName(chart);
        groupInfoListBean.setGroupId(mRecord.getGroupManageId());
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

    private static void putCache(DataInvitationGroupList.RecordBean mRecord, List<DataLinkGroupList.RecordBean.GroupInfoListBean> group_info_list, int i, String groupManageName) {
        List<DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean> groupList = group_info_list.get(i).getGroupList();
        DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean groupListBean = new DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean();
        groupListBean.setGroupName(groupManageName);
        groupListBean.setNickName(mRecord.getGroupName());
        groupListBean.setGroupOfId(mRecord.getGroupId());
        groupListBean.setHeadImg(mRecord.getGroupHeadImg());
        groupListBean.setGroupFenzuId(mRecord.getGroupManageId());
        groupList.add(groupListBean);
        group_info_list.get(i).setGroupList(groupList);
        group_info_list.get(i).setGroupName(mRecord.getChart());
        group_info_list.get(i).setGroupId(mRecord.getGroupManageId());
        group_info_list.get(i).setType("2");

        DataLinkGroupList.RecordBean recordBean = new DataLinkGroupList.RecordBean();
        recordBean.setGroupInfoList(group_info_list);

        String jsonString = JSON.toJSONString(recordBean);

        Log.e("jsonString","展开="+jsonString);
        aCache.remove(AppAllKey.GROUD_DATA);
        aCache.put(AppAllKey.GROUD_DATA, jsonString);

        Intent intent = new Intent();
        intent.setAction(AppConfig.LINK_GROUP_ADD_ACTION);
        mContext.sendBroadcast(intent);
    }

}
