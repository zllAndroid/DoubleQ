package com.mding.chatfeng.main_code.ui.about_load;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.NetWorkUtlis;
import com.mding.chatfeng.about_utils.about_file.HeadFileUtils;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.CusDataFriendRelation;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.CusDataGroup;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.CusDataLinkFriend;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.RealmFriendRelationHelper;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.RealmFriendUserHelper;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.RealmGroupHelper;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.RealmMsgInfoTotalHelper;
import com.mding.model.DataLinkGroupList;
import com.mding.model.DataLinkManList;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboutvolley.VolleyInterface;
import com.projects.zll.utilslibrarybyzll.aboutvolley.VolleyRequest;

import java.io.File;
import java.util.List;

public class LoadInteractor {

    interface OnLoadFinishedListener {

        void onError(String error);

        void onSuccess(String result);
    }
    public void linkManRequest(final OnLoadFinishedListener onLoadFinishedListener) {
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWorkTwo(SplitWeb.contactsListHttp(), new NetWorkUtlis.OnNetWork() {
            @Override
            public void onNetSuccess(String result) {
                onLoadFinishedListener.onSuccess(result);
            }
        }, new NetWorkUtlis.OnNetWorkError() {
            @Override
            public void onNetError(String msg) {
                onLoadFinishedListener.onError(msg);
            }
        });
    }
    interface OnSqlListener {

        void onSqlError();

        void onSqlSuccess();
    }
    ACache aCache;
    RealmMsgInfoTotalHelper realmMsgInfoTotalHelper;
    RealmFriendRelationHelper friendHelper;
    RealmFriendUserHelper friendUserHelper;
    RealmGroupHelper groupHelper;
    Context mContext;
    public  void setDataToRealm(Context mContext,String responseText,OnSqlListener onSqlListener)
    {
        this.mContext=mContext;
        aCache =  ACache.get(mContext);
        realmMsgInfoTotalHelper = new RealmMsgInfoTotalHelper(mContext);
        friendHelper = new RealmFriendRelationHelper(mContext);
        friendUserHelper = new RealmFriendUserHelper(mContext);
        groupHelper = new RealmGroupHelper(mContext);

        String friend = HelpUtils.backLinkMan(responseText, true);
        String group = HelpUtils.backLinkMan(responseText, false);
        if (!StrUtils.isEmpty(friend))
        {
//                    Log.e("backLinkMan","friend="+friend);
            DataLinkManList.RecordBean record = JSON.parseObject(friend, DataLinkManList.RecordBean.class);
//                    DataLinkManList.RecordBean record = JSON.parseObject(friend, DataLinkManList.RecordBean.class);
            dealFriendData(record);
        }

        if (!StrUtils.isEmpty(group))
        {
//                    Log.e("backLinkMan","group="+group);
            DataLinkGroupList.RecordBean recordBean = JSON.parseObject(group, DataLinkGroupList.RecordBean.class);
            dealGroupData(recordBean);
        }
        onSqlListener.onSqlSuccess();

    }
    private void dealGroupData(DataLinkGroupList.RecordBean  record_group) {
        final List<DataLinkGroupList.RecordBean.GroupInfoListBean> group_info_list = record_group.getGroupInfoList();
        if (group_info_list.size()>0) {
            for (int i = 0; i < group_info_list.size(); i++) {
                String userId = group_info_list.get(i).getGroupList().get(0).getGroupOfId();
                String groupName = group_info_list.get(i).getGroupName();
                if (group_info_list.get(i).getType().equals("1")) {
                    if (StrUtils.isEmpty(userId)) {
                        group_info_list.get(i).getGroupList().remove(0);
                    }
                    if (StrUtils.isEmpty(groupName)) {
                        group_info_list.remove(i);
                    }
                }
                if (group_info_list.size()>0)
                    if (group_info_list.get(i).getType().equals("2"))
                        dealGroupRealm(group_info_list, i);
            }
            String json_group = JSON.toJSON(record_group).toString();
            aCache.remove(AppAllKey.GROUD_DATA);
            aCache.put(AppAllKey.GROUD_DATA, json_group);
        }
    }

    private void dealGroupRealm(List<DataLinkGroupList.RecordBean.GroupInfoListBean> group_info_list, int i) {
        List<DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean> groupList = group_info_list.get(i).getGroupList();
        if (groupList.size()>0) {
            for (int j = 0; j < groupList.size(); j++) {
                DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean groupListBean = groupList.get(j);
                final String modified = groupList.get(j).getModified();
                final String friendId = groupList.get(j).getGroupOfId();
                final String headImg = groupList.get(j).getHeadImg();
                CusDataLinkFriend cusDataLinkFriend = realmMsgInfoTotalHelper.queryLinkFriend(friendId);
                if (StrUtils.isEmpty(headImg)) {
                    return;
                }
                if (cusDataLinkFriend != null) {

                    String time = cusDataLinkFriend.getTime();
                    if (modified != null && !modified.equals(time)) {
                        setGlideData(true, false, modified, friendId, headImg);
                        setGroupData(true, groupListBean);
                    } else {
                        setGlideData(true, false, modified, friendId, headImg);
                        setGroupData(false, groupListBean);
                    }
//                boolean equals = modified.equals(time);
//                setGlideData(!equals,false,modified, friendId, headImg);
                } else {
                    setGroupData(false, groupListBean);
                    setGlideData(false, false, modified, friendId, headImg);
                }
            }
        }

    }

    private void setGroupData(boolean isUpdata, DataLinkGroupList.RecordBean.GroupInfoListBean.GroupListBean groupListBean) {
        CusDataGroup cusDataGroup = new CusDataGroup();
        cusDataGroup.setGroupHeadImg(groupListBean.getHeadImg());
        cusDataGroup.setGroupId(groupListBean.getGroupOfId());
        cusDataGroup.setGroupName(groupListBean.getGroupName());
        cusDataGroup.setGroupQrcode(groupListBean.getGroupQrcode());
        cusDataGroup.setCreated(groupListBean.getModified());
        if (isUpdata)
        {
            groupHelper.updateAll(groupListBean.getGroupOfId(),cusDataGroup);
        }
        else
        {
            groupHelper.addRealmGroup(cusDataGroup);
        }
    }

    //设置好友信息
    private void setFriendRealm(boolean isUpData,DataLinkManList.RecordBean.FriendListBean.GroupListBean groupListBean) {
        CusDataFriendRelation cusDataFriendRelation = new CusDataFriendRelation();
        cusDataFriendRelation.setHeadImg(groupListBean.getHeadImg());
        cusDataFriendRelation.setNickName(groupListBean.getNickName());
        cusDataFriendRelation.setFriendId(groupListBean.getUserId());
        cusDataFriendRelation.setGroupId(groupListBean.getGroupId());
        cusDataFriendRelation.setModified(groupListBean.getModified());
        cusDataFriendRelation.setRemarkName(groupListBean.getRemarkName());
        if (isUpData)
        {
//            更新该好友全部内容
            friendHelper.updateAll(groupListBean.getUserId(),cusDataFriendRelation);
        }else
        {
//            添加该好友信息
            friendHelper.addRealmLinkFriend(cusDataFriendRelation);
        }
    }

    private void setGlideData(final boolean isSame,final boolean isFriend,final String modified, final String friendId, final String headImg) {
        Glide.with(mContext)
                .load(headImg)
                .downloadOnly(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(final File resource, GlideAnimation<? super File> glideAnimation) {
//                                    这里拿到的resource就是下载好的文件，
                        File file = HeadFileUtils.saveImgPath(resource, AppConfig.TYPE_FRIEND, friendId, modified);
                        if (isSame)
                        {
//                            db.insert(TotalEntry.)
                            realmMsgInfoTotalHelper.updateHeadPath(friendId, file.toString(), headImg, modified);
                        }
                        else
                        {
                            CusDataLinkFriend linkFriend = new CusDataLinkFriend();
                            linkFriend.setHeadImg(headImg);
                            linkFriend.setFriendId(friendId);
                            linkFriend.setTime(modified);
                            linkFriend.setImgPath(file.toString());
                            if (isFriend)
                                linkFriend.setWhoType("1");
                            else
                                linkFriend.setWhoType("2");
                            realmMsgInfoTotalHelper.addRealmLinkFriend(linkFriend);
                        }
                    }
                });
    }

    private void dealFriendData(DataLinkManList.RecordBean record) {
        List<DataLinkManList.RecordBean.FriendListBean> friendList = record.getFriendList();
        if (friendList.size()>0)
            for (int i=0;i<friendList.size();i++)
            {
                String userId = friendList.get(i).getGroupList().get(0).getUserId();
                String groupName = friendList.get(i).getGroupName();
                if (friendList.get(i).getType().equals("1"))
                {
                    if (StrUtils.isEmpty(userId)) {
                        friendList.get(i).getGroupList().remove(0);
                    }
                    if (StrUtils.isEmpty(groupName)) {
                        friendList.remove(i);
                    }
                }
                if (friendList.size()>0)
                    dealFriendRealm(friendList, i);
            }
        String json = JSON.toJSON(record).toString();
        aCache.remove(AppAllKey.FRIEND_DATA);
        aCache.put(AppAllKey.FRIEND_DATA, json);
    }

    private void dealFriendRealm(List<DataLinkManList.RecordBean.FriendListBean> friendList, int i) {
        if (friendList.get(i).getType().equals("2"))
        {
            List<DataLinkManList.RecordBean.FriendListBean.GroupListBean> groupList = friendList.get(i).getGroupList();
            if (groupList.size()>0)
                for (int j=0;j<groupList.size();j++) {
                    DataLinkManList.RecordBean.FriendListBean.GroupListBean groupListBean = groupList.get(j);


                    final String modified = groupList.get(j).getModified();
                    final String friendId = groupList.get(j).getUserId();
                    final String headImg = groupList.get(j).getHeadImg();
                    CusDataLinkFriend cusDataLinkFriend = realmMsgInfoTotalHelper.queryLinkFriend(friendId);
                    CusDataFriendRelation cusDataFriendRelation = friendHelper.queryLinkFriend(friendId);
                    if (StrUtils.isEmpty(headImg))
                    {
                        return;
                    }
                    if (cusDataLinkFriend!=null&&cusDataFriendRelation!=null) {

                        String time = cusDataLinkFriend.getTime();
                        if ( !modified.equals(time))
                        {
                            setGlideData(true,true,modified, friendId, headImg);
                            setFriendRealm(true,groupListBean);
                        }else {
                            setGlideData(false,true,modified, friendId, headImg);
                            setFriendRealm(false,groupListBean);
                        }
//                boolean equals = modified.equals(time);
//                setGlideData(!equals,false,modified, friendId, headImg);
                    }else {
                        setGlideData(false,true,modified, friendId, headImg);
                        setFriendRealm(false,groupListBean);
                    }
//                if (cusDataLinkFriend != null) {
//                    if (StrUtils.isEmpty(headImg)) {
//                        return;
//                    }
//                    String time = cusDataLinkFriend.getTime();
//                    boolean equals = modified.equals(time);
//                    setGlideData(!equals,true,modified, friendId, headImg);
//                }
                }
        }
    }

}
