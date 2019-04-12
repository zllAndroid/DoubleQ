package com.mding.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 项目：DoubleQ
 * 文件描述：
 * 作者：zll
 * 创建时间：2019/4/4 0004
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
@Entity(
        indexes = {
                @Index(value = " friendId DESC", unique = true)
//                @Index(value = "userId DESC, friendId DESC", unique = true)
        }
)
public class FriendInfo {
    @Id(autoincrement = true)
    Long id;
    String userId;
    String friendId;
    String imageHead;
    String persona_signature;
    String countries_regions;
    String qrcode;
    String email;
    String nick_name;
    String wx_sno;
@Generated(hash = 786951933)
public FriendInfo(Long id, String userId, String friendId, String imageHead,
        String persona_signature, String countries_regions, String qrcode,
        String email, String nick_name, String wx_sno) {
    this.id = id;
    this.userId = userId;
    this.friendId = friendId;
    this.imageHead = imageHead;
    this.persona_signature = persona_signature;
    this.countries_regions = countries_regions;
    this.qrcode = qrcode;
    this.email = email;
    this.nick_name = nick_name;
    this.wx_sno = wx_sno;
}
@Generated(hash = 459681999)
public FriendInfo() {
}
public Long getId() {
    return this.id;
}
public void setId(Long id) {
    this.id = id;
}
public String getUserId() {
    return this.userId;
}
public void setUserId(String userId) {
    this.userId = userId;
}
public String getFriendId() {
    return this.friendId;
}
public void setFriendId(String friendId) {
    this.friendId = friendId;
}
public String getImageHead() {
    return this.imageHead;
}
public void setImageHead(String imageHead) {
    this.imageHead = imageHead;
}
public String getPersona_signature() {
    return this.persona_signature;
}
public void setPersona_signature(String persona_signature) {
    this.persona_signature = persona_signature;
}
public String getCountries_regions() {
    return this.countries_regions;
}
public void setCountries_regions(String countries_regions) {
    this.countries_regions = countries_regions;
}
public String getQrcode() {
    return this.qrcode;
}
public void setQrcode(String qrcode) {
    this.qrcode = qrcode;
}
public String getEmail() {
    return this.email;
}
public void setEmail(String email) {
    this.email = email;
}
public String getNick_name() {
    return this.nick_name;
}
public void setNick_name(String nick_name) {
    this.nick_name = nick_name;
}
public String getWx_sno() {
    return this.wx_sno;
}
public void setWx_sno(String wx_sno) {
    this.wx_sno = wx_sno;
}

}
