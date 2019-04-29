package com.mding.chatfeng.about_base.deal_application;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_broadcastreceiver.LinkChangeEvent;
import com.mding.chatfeng.about_broadcastreceiver.MsgHomeEvent;
import com.mding.chatfeng.about_utils.JsonUtils;
import com.mding.chatfeng.about_utils.about_realm.new_home.RealmHomeHelper;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.CusDataGroup;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.RealmGroupHelper;
import com.mding.model.DataLinkGroupList;
import com.mding.model.push_data.DataAboutGroup;
import com.mding.model.push_data.DataAboutGroupModify;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class DealGroupAdd {

    private  String jsonString;
    public  ACache aCache;
    public  Context mContext;
    private  RealmGroupHelper groupHelper;

    private static DealGroupAdd dealFriendAdd;
    // 构造函数必须是私有的 这样在外部便无法使用 new 来创建该类的实例
    private DealGroupAdd() {}
    /**
     * 单一实例
     */
    public synchronized static DealGroupAdd getDealGroupAdd() {
        if (dealFriendAdd == null) {
            dealFriendAdd = new DealGroupAdd();
        }
        return dealFriendAdd;
    }
    
    // 创建群聊；加入群聊
    public  void updateGroupDataByAdd(Context context,String result)
    {
        mContext=context;
        if (groupHelper==null)
            groupHelper = new RealmGroupHelper(mContext);
        DataAboutGroup dataAboutGroup = JSON.parseObject(result, DataAboutGroup.class);
        DataAboutGroup.RecordBean record = dataAboutGroup.getRecord();
        if (aCache==null)
            aCache =  ACache.get(mContext);
        String asString = aCache.getAsString(AppAllKey.GROUD_DATA);
        if (!StrUtils.isEmpty(asString)&&record!=null)
        {
            initDataGroup(asString,record);
            doRealmGroup(dataAboutGroup,"1"); //  1 add   2 modify  3 delete
        }
    }

    private  void doRealmGroup(DataAboutGroup dataAboutGroup, String type) {
        DataAboutGroup.RecordBean record = dataAboutGroup.getRecord();
        CusDataGroup cusDataGroup = new CusDataGroup();
        cusDataGroup.setGroupHeadImg(record.getGroupHeadImg());
        cusDataGroup.setGroupId(record.getGroupId());
        cusDataGroup.setGroupName(record.getGroupName());
        cusDataGroup.setGroupManageId(record.getGroupManageId());
        cusDataGroup.setGroupManageName(record.getGroupManageName());
        CusDataGroup dataGroup = groupHelper.queryLinkFriend(record.getGroupId());
        if (type.equals("1") || type.equals("2")) {
            if (dataGroup != null) {
                //不为空说明有该群，则进行修改操作
                groupHelper.updateAll(record.getGroupId(), cusDataGroup);
            } else {
                //为空说明有该群，则进行添加操作
                groupHelper.addRealmGroup(cusDataGroup);
            }
        }
        else if (type.equals("3")){
            //不为空说明有该群，则进行删除操作
            groupHelper.deleteRealmFriend(record.getGroupId());
        }
    }

    //修改的数据库操作
    private  void doRealmGroupModify(DataAboutGroupModify dataAboutGroupModify) {
        DataAboutGroupModify.RecordBean record = dataAboutGroupModify.getRecord();
        CusDataGroup cusDataGroup = new CusDataGroup();
        cusDataGroup.setGroupHeadImg(record.getNewGroupHeadImg());
        cusDataGroup.setGroupId(record.getGroupId());
        cusDataGroup.setGroupName(record.getNewGroupName());
        cusDataGroup.setGroupManageId(record.getNewGroupManageId());
        cusDataGroup.setGroupManageName(record.getNewGroupManageName());
        CusDataGroup dataGroup = groupHelper.queryLinkFriend(record.getGroupId());
        if (dataGroup != null) {
            //不为空说明有该群，则进行修改操作
            groupHelper.updateAll(record.getGroupId(), cusDataGroup);
        } else {
            //为空说明有该群，则进行添加操作
            groupHelper.addRealmGroup(cusDataGroup);
        }
    }

    // 退出群聊；解散群聊
    public   void updateGroupDataBySub(Context context, String result, RealmHomeHelper realmHomeHelper) {
        mContext=context;
        if (groupHelper==null)
            groupHelper = new RealmGroupHelper(mContext);
        DataAboutGroup dataAboutGroup = JSON.parseObject(result, DataAboutGroup.class);
        DataAboutGroup.RecordBean record = dataAboutGroup.getRecord();
        if (aCache == null) {
            aCache =  ACache.get(mContext);
        }
        if (aCache!=null)
        {
            String asString = aCache.getAsString(AppAllKey.GROUD_DATA);
            if (!StrUtils.isEmpty(asString)&&record!=null)
            {
                initDataGroupSub(asString,record);
                realmHomeHelper.deleteRealmMsg(record.getGroupId());
                //type为 3  删除
                doRealmGroup(dataAboutGroup, "3");
            }
        }
    }
    //修改群聊信息的删除操作
    public   String updateGroupDataByModifySub(Context context,String result ) {
        mContext=context;
        groupHelper = new RealmGroupHelper(mContext);
        DataAboutGroupModify dataAboutGroupModify = JSON.parseObject(result, DataAboutGroupModify.class);
        DataAboutGroupModify.RecordBean record = dataAboutGroupModify.getRecord();
        if (aCache == null) {
            aCache =  ACache.get(mContext);
        }
        if (aCache!=null)
        {
            String asString = aCache.getAsString(AppAllKey.GROUD_DATA);
            if (!StrUtils.isEmpty(asString)&&record!=null)
            {
                s = initDataGroupModifySub(asString, record);
                doRealmGroupModify(dataAboutGroupModify);
            }
        }
        return  s;
    }
    //修改群聊信息的增加操作
    public   void updateGroupDataByModifyAdd(Context context,String result) {
        mContext=context;
        DataAboutGroupModify dataAboutGroupModify = JSON.parseObject(result, DataAboutGroupModify.class);
        DataAboutGroupModify.RecordBean record = dataAboutGroupModify.getRecord();
        if (aCache == null)
            aCache =  ACache.get(mContext);
        if (aCache!=null)
        {
            String asString = aCache.getAsString(AppAllKey.GROUD_DATA);
            if (!StrUtils.isEmpty(asString)&&record!=null)
            {
                initDataGroupModifyAdd(asString,record);
            }
        }
    }
    private  String s;
    private  String initDataGroupModifySub(String asString, DataAboutGroupModify.RecordBean mRecord) {
        DataLinkGroupList.RecordBean record = JSON.parseObject(asString, DataLinkGroupList.RecordBean.class);
        final List<DataLinkGroupList.RecordBean.GroupInfoListBean> group_info_list = record.getGroupInfoList();
        if (group_info_list.size() > 0) {
            String chart = mRecord.getOldChart();
            String groupManageId = mRecord.getOldGroupManageId();//分组id
            String groupId = mRecord.getGroupId();//群id
            for (int i = 0; i < group_info_list.size(); i++) {
                String type = group_info_list.get(i).getType();
                String groupName = group_info_list.get(i).getGroupName();
                List<DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean> groupList = group_info_list.get(i).getGroupList();
                if (type.equals("2")) {
                    if (chart != null && chart.equals(groupName)) {
                        if (groupList.size() == 0) {
//                            return;
                        } else if (groupList.size() == 1) {
                            group_info_list.remove(i);
                        } else if (groupList.size() > 1) {
                            for (int j = 0; j < groupList.size(); j++) {
                                String groupOfId = groupList.get(j).getGroupOfId();
                                if (groupId.equals(groupOfId)) {
                                    group_info_list.get(i).getGroupList().remove(j);
                                }
                            }
                        }
                    }
                }
                else if (type.equals("1")&&groupManageId != null && !groupManageId.equals("0"))
                {
                    String groupManageName = mRecord.getOldGroupManageName();
                    if (groupList.size()>0)
                    {
                        if (groupManageName!=null && groupManageName.equals(groupName)) {
                            for (int h = 0; h < groupList.size(); h++) {
                                String groupOfId = groupList.get(h).getGroupOfId();
                                if (mRecord.getGroupId().equals(groupOfId)) {
                                    group_info_list.get(i).getGroupList().remove(h);
                                }
                            }
                        }
                    }
                }
            }

//            修改数据保存cache
            DataLinkGroupList.RecordBean recordBean = new DataLinkGroupList.RecordBean();
            recordBean.setGroupInfoList(group_info_list);

            jsonString = JSON.toJSONString(recordBean);
            Log.e("jsonString","修改="+jsonString);
            aCache.remove(AppAllKey.GROUD_DATA);
            aCache.put(AppAllKey.GROUD_DATA, jsonString);
            EventBus.getDefault().post(new LinkChangeEvent(AppConfig.LINK_GROUP_DEL_ACTION));
            EventBus.getDefault().post(new MsgHomeEvent(AppConfig.LINK_GROUP_DEL_ACTION));
//            Intent intent = new Intent();
//            intent.setAction(AppConfig.LINK_GROUP_DEL_ACTION);
//            mContext.sendBroadcast(intent);
        }
        return jsonString;
    }
    private  void initDataGroupModifyAdd(String asString,DataAboutGroupModify.RecordBean mRecord) {
        DataLinkGroupList.RecordBean record = JSON.parseObject(asString, DataLinkGroupList.RecordBean.class);
        final List<DataLinkGroupList.RecordBean.GroupInfoListBean> group_info_list = record.getGroupInfoList();
        if (group_info_list.size()>0) {
            for (int i = 0; i < group_info_list.size(); i++) {
                String type = group_info_list.get(i).getType();
                if (type.equals("2")) {
                    String groupName = group_info_list.get(i).getGroupName();
                    String chart = mRecord.getNewChart();
                    String groupManageName = mRecord.getNewGroupManageName();
                    String groupManageId = mRecord.getNewGroupManageId();
                    if (chart != null && chart.equals(groupName)) {
                        putCacheModify(mRecord, group_info_list, i, chart);
                        return;
                    }
                }
                else {
                    String groupName = group_info_list.get(i).getGroupName();
                    String newGroupManageName = mRecord.getNewGroupManageName();
                    if (newGroupManageName != null && newGroupManageName.equals(groupName))
                        putCacheModifyTypeOne(mRecord, group_info_list, i);
                }
            }
//            如果列表中没有当前的字母，则判断外围新增
            for (int i = 0; i < group_info_list.size(); i++)
            {
                String type = group_info_list.get(i).getType();
                if (type.equals("2")) {
                    String groupName = group_info_list.get(i).getGroupName();
                    String chart = mRecord.getNewChart();
                    int i1 = stringToAscii(getFirstABC(groupName));
                    int i2 = stringToAscii(getFirstABC(chart));
                    if (group_info_list.size() > (i + 1)) {
                        String groupNameNext = group_info_list.get(i + 1).getGroupName();
                        int i3 = stringToAscii(getFirstABC(groupNameNext));
                        if (i1 < i2 && i2 < i3) {
                            dealNoChartModify(mRecord, group_info_list, (i+1), chart);
                            return;
                        }
                        else if (i1 > i2)
                        {
                            dealNoChartModify(mRecord, group_info_list, i, chart);
                            return;
                        }
                    } else if (i1 < i2) {
                        dealNoChartModify(mRecord, group_info_list, (i+1), chart);
                        return;
                    } else if (i1 > i2) {
                        dealNoChartModify(mRecord, group_info_list, i, chart);
                        return;
                    }
                }
            }

//
//            Intent intent = new Intent();
//            intent.setAction(AppConfig.LINK_GROUP_ADD_ACTION);
//            mContext.sendBroadcast(intent);
            EventBus.getDefault().post(new LinkChangeEvent(AppConfig.LINK_GROUP_ADD_ACTION));
        }
        else
        {
            String chart = mRecord.getNewChart();
            dealNoChartModify(mRecord, group_info_list, 0, chart);
            return;
        }

    }

    private  void putCacheModifyTypeOne(DataAboutGroupModify.RecordBean mRecord, List<DataLinkGroupList.RecordBean.GroupInfoListBean> group_info_list, int i) {
        List<DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean> groupList = group_info_list.get(i).getGroupList();
        DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean groupListBean = new DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean();
        groupListBean.setGroupName(mRecord.getNewGroupManageName());
        groupListBean.setNickName(mRecord.getNewGroupName());
        groupListBean.setGroupOfId(mRecord.getGroupId());
        groupListBean.setHeadImg(mRecord.getNewGroupHeadImg());
        groupListBean.setGroupFenzuId(mRecord.getNewGroupManageId());
        groupList.add(groupListBean);
        group_info_list.get(i).setGroupList(groupList);
        group_info_list.get(i).setGroupName(mRecord.getNewGroupManageName());
        group_info_list.get(i).setGroupId(mRecord.getNewGroupManageId());
        group_info_list.get(i).setType("1");

        DataLinkGroupList.RecordBean recordBean = new DataLinkGroupList.RecordBean();
        recordBean.setGroupInfoList(group_info_list);

//        String jsonString = JSON.toJSONString(recordBean);
        String jsonString = JsonUtils.toChangeJson(recordBean);//将java对象转换为json对象

        Log.e("jsonString","不展开（修改）="+jsonString);
        aCache.remove(AppAllKey.GROUD_DATA);
        aCache.put(AppAllKey.GROUD_DATA, jsonString);


    }

    private  void initDataGroupSub(String asString,DataAboutGroup.RecordBean mRecord) {
        DataLinkGroupList.RecordBean record = JSON.parseObject(asString, DataLinkGroupList.RecordBean.class);
        final List<DataLinkGroupList.RecordBean.GroupInfoListBean> group_info_list = record.getGroupInfoList();
        if (group_info_list.size() > 0) {
            String chart = mRecord.getChart();
            String groupManageId = mRecord.getGroupManageId();//分组id
            String groupId = mRecord.getGroupId();//群id
            for (int i = 0; i < group_info_list.size(); i++) {
                String type = group_info_list.get(i).getType();
                String groupName = group_info_list.get(i).getGroupName();
                List<DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean> groupList = group_info_list.get(i).getGroupList();
                if (type.equals("2")) {
                    if (chart != null && chart.equals(groupName)) {
                        if (groupList.size() == 0) {
//                            return;
                        } else if (groupList.size() == 1) {
                            group_info_list.remove(i);
                        } else {
                            groupList.size();
                            for (int j = 0; j < groupList.size(); j++) {
                                String groupOfId = groupList.get(j).getGroupOfId();
                                if (groupId.equals(groupOfId)) {
                                    group_info_list.get(i).getGroupList().remove(j);
                                }
                            }
                        }
                    }
                }
                else if (type.equals("1")&&groupManageId != null && !groupManageId.equals("0"))
                {
                    String groupManageName = mRecord.getGroupManageName();
                    if (groupList.size()>0)
                    {
                        if (groupManageName!=null&&groupManageName.equals(groupName)) {
                            for (int h = 0; h < groupList.size(); h++) {
                                String groupOfId = groupList.get(h).getGroupFenzuId();
                                if (groupManageId.equals(groupOfId)) {
                                    group_info_list.get(i).getGroupList().remove(h);
                                }
                            }
                        }
                    }
                }
            }

//            修改数据保存cache
            DataLinkGroupList.RecordBean recordBean = new DataLinkGroupList.RecordBean();
            recordBean.setGroupInfoList(group_info_list);

//            String jsonString = JSON.toJSONString(recordBean);
            String jsonString = JsonUtils.toChangeJson(recordBean);//将java对象转换为json对象
            Log.e("jsonString","减少="+jsonString);
            aCache.remove(AppAllKey.GROUD_DATA);
            aCache.put(AppAllKey.GROUD_DATA, jsonString);
            EventBus.getDefault().post(new LinkChangeEvent(AppConfig.LINK_GROUP_DEL_ACTION));
            EventBus.getDefault().post(new MsgHomeEvent(AppConfig.LINK_GROUP_DEL_ACTION));
//            Intent intent = new Intent();
//            intent.setAction(AppConfig.LINK_GROUP_DEL_ACTION);
//            mContext.sendBroadcast(intent);

        }
    }

    private  void initDataGroup(String asString,DataAboutGroup.RecordBean mRecord) {
        DataLinkGroupList.RecordBean record = JSON.parseObject(asString, DataLinkGroupList.RecordBean.class);
        final List<DataLinkGroupList.RecordBean.GroupInfoListBean> group_info_list = record.getGroupInfoList();
        if (record == null){
            return;
        }
        if (group_info_list.size()>0) {
            for (int i = 0; i < group_info_list.size(); i++) {
                String type = group_info_list.get(i).getType();
                if (type.equals("2")) {
                    String groupName = group_info_list.get(i).getGroupName();
                    String chart = mRecord.getChart();
                    String groupManageName = mRecord.getGroupManageName();
                    String groupManageId = mRecord.getGroupManageId();
                    if (chart != null && chart.equals(groupName)) {
                        putCache(mRecord, group_info_list, i, chart);
                        return;
                    }
                }
            }
//            如果列表中没有当前的字母，则判断外围新增
            for (int i = 0; i < group_info_list.size(); i++)
            {
                String type = group_info_list.get(i).getType();
                if (type.equals("2")) {
                    String groupName = group_info_list.get(i).getGroupName();
                    String chart = mRecord.getChart();
                    int i1 = stringToAscii(getFirstABC(groupName));
                    int i2 = stringToAscii(getFirstABC(chart));
                    if (group_info_list.size() > (i + 1)) {
                        String groupNameNext = group_info_list.get(i + 1).getGroupName();
                        int i3 = stringToAscii(getFirstABC(groupNameNext));
                        if (i1 < i2 && i2 < i3) {
                            dealNoChart(mRecord, group_info_list, (i+1), chart);
                            return;
                        }
                        else if (i1 > i2)
                        {
                            dealNoChart(mRecord, group_info_list, i, chart);
                            return;
                        }
                    } else if (i1 < i2) {
                        dealNoChart(mRecord, group_info_list, (i+1), chart);
                        return;
                    } else if (i1 > i2) {
                        dealNoChart(mRecord, group_info_list, i, chart);
                        return;
                    }
                }
            }
        }
        else
        {
            String chart = mRecord.getChart();
            dealNoChart(mRecord, group_info_list, 0, chart);
        }
    }

    public  String getFirstABC(String pinyin) {
        if(pinyin.length()==0)
        {
            return pinyin;
        }
        String upperCase = pinyin.substring(0,1).toUpperCase();
        return upperCase;
    }
    private  void dealNoChart(DataAboutGroup.RecordBean mRecord, List<DataLinkGroupList.RecordBean.GroupInfoListBean> group_info_list,
                                    int i, String chart) {
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
        groupInfoListBean.setGroupId(mRecord.getGroupId());
        groupInfoListBean.setGroupList(groupList);

        group_info_list.add(i,groupInfoListBean);

        DataLinkGroupList.RecordBean recordBean = new DataLinkGroupList.RecordBean();
        recordBean.setGroupInfoList(group_info_list);
//        String jsonString = JSON.toJSONString(recordBean);
        String jsonString = JsonUtils.toChangeJson(recordBean);//将java对象转换为json对象
        Log.e("jsonString","原本没有本chart展开="+jsonString);
        aCache.remove(AppAllKey.GROUD_DATA);
        aCache.put(AppAllKey.GROUD_DATA, jsonString);

//        Intent intent = new Intent();
//        intent.setAction(AppConfig.LINK_GROUP_ADD_ACTION);
//        mContext.sendBroadcast(intent);
        EventBus.getDefault().post(new LinkChangeEvent(AppConfig.LINK_GROUP_ADD_ACTION));
    }
    private  void dealNoChartModify(DataAboutGroupModify.RecordBean mRecord, List<DataLinkGroupList.RecordBean.GroupInfoListBean> group_info_list,
                                          int i, String chart) {
        List<DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean> groupList = new ArrayList<>();

        DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean groupListBean = new DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean();
        groupListBean.setGroupName(chart);
        groupListBean.setNickName(mRecord.getNewGroupName());
        groupListBean.setGroupOfId(mRecord.getGroupId());
        groupListBean.setHeadImg(mRecord.getNewGroupHeadImg());
        groupListBean.setGroupFenzuId(mRecord.getNewGroupManageId());
        groupList.add(groupListBean);

        DataLinkGroupList.RecordBean.GroupInfoListBean groupInfoListBean = new DataLinkGroupList.RecordBean.GroupInfoListBean();
        groupInfoListBean.setType("2");
        groupInfoListBean.setGroupName(chart);
        groupInfoListBean.setGroupId(mRecord.getGroupId());
        groupInfoListBean.setGroupList(groupList);

        group_info_list.add(i,groupInfoListBean);

        DataLinkGroupList.RecordBean recordBean = new DataLinkGroupList.RecordBean();
        recordBean.setGroupInfoList(group_info_list);
//        String jsonString = JSON.toJSONString(recordBean);
        String jsonString = JsonUtils.toChangeJson(recordBean);//将java对象转换为json对象
        Log.e("jsonString","原本没有本chart展开（修改）="+jsonString);
        aCache.remove(AppAllKey.GROUD_DATA);
        aCache.put(AppAllKey.GROUD_DATA, jsonString);
        EventBus.getDefault().post(new LinkChangeEvent(AppConfig.LINK_GROUP_ADD_ACTION));
//        Intent intent = new Intent();
//        intent.setAction(AppConfig.LINK_GROUP_ADD_ACTION);
//        mContext.sendBroadcast(intent);
    }

    public  int stringToAscii(String value) {
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
        int i = 0;
        try {
            i = Integer.parseInt(sbu.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        return  i;
    }
    private  void putCache(DataAboutGroup.RecordBean mRecord, List<DataLinkGroupList.RecordBean.GroupInfoListBean> group_info_list, int i, String chart) {
        List<DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean> groupList = group_info_list.get(i).getGroupList();
        DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean groupListBean = new DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean();
        groupListBean.setGroupName(chart);
        groupListBean.setNickName(mRecord.getGroupName());
        groupListBean.setGroupOfId(mRecord.getGroupId());
        groupListBean.setHeadImg(mRecord.getGroupHeadImg());
        groupListBean.setGroupFenzuId(mRecord.getGroupManageId());
        groupList.add(groupListBean);
        group_info_list.get(i).setGroupList(groupList);
        group_info_list.get(i).setGroupName(chart);
        group_info_list.get(i).setGroupId(mRecord.getGroupManageId());
        group_info_list.get(i).setType("2");

        DataLinkGroupList.RecordBean recordBean = new DataLinkGroupList.RecordBean();
        recordBean.setGroupInfoList(group_info_list);

//        String jsonString = JSON.toJSONString(recordBean);
        String jsonString = JsonUtils.toChangeJson(recordBean);//将java对象转换为json对象

        Log.e("jsonString","展开="+jsonString);
        aCache.remove(AppAllKey.GROUD_DATA);
        aCache.put(AppAllKey.GROUD_DATA, jsonString);
        EventBus.getDefault().post(new LinkChangeEvent(AppConfig.LINK_GROUP_ADD_ACTION));
//        Intent intent = new Intent();
//        intent.setAction(AppConfig.LINK_GROUP_ADD_ACTION);
//        mContext.sendBroadcast(intent);
    }
    private  void putCacheModify(DataAboutGroupModify.RecordBean mRecord, List<DataLinkGroupList.RecordBean.GroupInfoListBean> group_info_list, int i, String chart) {
        List<DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean> groupList = group_info_list.get(i).getGroupList();
        DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean groupListBean = new DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean();
        groupListBean.setGroupName(chart);
        groupListBean.setNickName(mRecord.getNewGroupName());
        groupListBean.setGroupOfId(mRecord.getGroupId());
        groupListBean.setHeadImg(mRecord.getNewGroupHeadImg());
        groupListBean.setGroupFenzuId(mRecord.getNewGroupManageId());
        groupList.add(groupListBean);
        group_info_list.get(i).setGroupList(groupList);
        group_info_list.get(i).setGroupName(chart);
        group_info_list.get(i).setGroupId(mRecord.getNewGroupManageId());
        group_info_list.get(i).setType("2");

        DataLinkGroupList.RecordBean recordBean = new DataLinkGroupList.RecordBean();
        recordBean.setGroupInfoList(group_info_list);

//        String jsonString = JSON.toJSONString(recordBean);
        String jsonString = JsonUtils.toChangeJson(recordBean);//将java对象转换为json对象

        Log.e("jsonString","展开（修改）="+jsonString);
        aCache.remove(AppAllKey.GROUD_DATA);
        aCache.put(AppAllKey.GROUD_DATA, jsonString);
        EventBus.getDefault().post(new LinkChangeEvent(AppConfig.LINK_GROUP_ADD_ACTION));
//        Intent intent = new Intent();
//        intent.setAction(AppConfig.LINK_GROUP_ADD_ACTION);
//        mContext.sendBroadcast(intent);
    }
}
