package com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
//好友表数据
public class CusDataFriend extends RealmObject {

    @PrimaryKey
    String totalId;//共同id（由朋友id和用户id合并组成）

    String friendId;//好友id

    String userid;
//头像
    String imgPath;
//时间
    String time;

    String persona_signature;//个性签名
//    国家地区
    String countries_regions;
//    二维码地址
    String qrcode;
//    用户邮箱
    String email;
//    用户昵称
    String nick_name;
//    微信唯一账号
    String wx_sno;

    public String getPersona_signature() {
        return persona_signature;
    }

    public void setPersona_signature(String persona_signature) {
        this.persona_signature = persona_signature;
    }

    public String getCountries_regions() {
        return countries_regions;
    }

    public void setCountries_regions(String countries_regions) {
        this.countries_regions = countries_regions;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getWx_sno() {
        return wx_sno;
    }

    public void setWx_sno(String wx_sno) {
        this.wx_sno = wx_sno;
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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
