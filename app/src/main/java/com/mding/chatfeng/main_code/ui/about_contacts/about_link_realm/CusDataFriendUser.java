package com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

//用戶表(所有用户都在本表中有且仅有一个，包含群成员，是否好友)
public class CusDataFriendUser extends RealmObject {

    @PrimaryKey
    String totalId;//共同id（由朋友id和用户id合并组成）

    String friendId;//好友id

    String userid;

    String imgPathUrl;

    String time;

    //昵称
    String name;

//    签名
    String sign;
//    账号
    String wxNo;
// 1是未添加 2是已添加 3是自己
//    String isRealtion;
    String headImgBase64;
////    1是好友，2是群
//    String whoType;

//    public String getWhoType() {
//        return whoType;
//    }
//
//    public void setWhoType(String whoType) {
//        this.whoType = whoType;
//    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getImgPathUrl() {
        return imgPathUrl;
    }

    public void setImgPathUrl(String imgPathUrl) {
        this.imgPathUrl = imgPathUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getWxNo() {
        return wxNo;
    }

    public void setWxNo(String wxNo) {
        this.wxNo = wxNo;
    }

//    public String getIsRealtion() {
//        return isRealtion;
//    }
//
//    public void setIsRealtion(String isRealtion) {
//        this.isRealtion = isRealtion;
//    }

    public String getHeadImgBase64() {
        return headImgBase64;
    }

    public void setHeadImgBase64(String headImgBase64) {
        this.headImgBase64 = headImgBase64;
    }
}
