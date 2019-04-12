package com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
//群成员
public class CusDataGroupMember extends RealmObject {

    @PrimaryKey
    String totalId;//共同id（由朋友id和用户id合并组成）

    String friendId;//好友id

    String userId;
    String groupId;
//
    String time;
//头像
    String headImg;
//好友状态     1陌生人显示界面，2好友，3自己
    String isRelation;
//账号
    String wxNo;
//    签名
    String sign;

    public String getTotalId() {
        return totalId;
    }

    public void setTotalId(String totalId) {
        this.totalId = totalId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getIsRelation() {
        return isRelation;
    }

    public void setIsRelation(String isRelation) {
        this.isRelation = isRelation;
    }

    public String getWxNo() {
        return wxNo;
    }

    public void setWxNo(String wxNo) {
        this.wxNo = wxNo;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
