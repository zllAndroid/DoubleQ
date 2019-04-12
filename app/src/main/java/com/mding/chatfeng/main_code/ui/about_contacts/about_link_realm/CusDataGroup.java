package com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

//好友表数据
public class CusDataGroup extends RealmObject {

    @PrimaryKey
    String totalId;//共同id（由朋友id和用户id合并组成）

    //    String friendId;//好友id
//
//    String userid;
////头像
//    String imgPath;
////时间
//    String time;
//
//    String persona_signature;//个性签名
////    国家地区
//    String countries_regions;
////    二维码地址
//    String qrcode;
////    用户邮箱
//    String email;
////    用户昵称
//    String nick_name;
////    微信唯一账号
//    String wx_sno;
    String groupId;
    String groupName;   //  群名称
    String groupSno;   //  群账号
    String groupType;   //  群类型 1：综合
    String groupHeadImg;   //  群头像地址
    String groupInstruction;   //  群介绍说明
    String groupQrcode;   //  群二维码地址
    String status;   //  群设置：1可直接加入 2需审核加入 3 禁止加入
    String maxNum;   //  群上限人数
    String nowNum;   //  群当前添加人数
    String createdId;   //  创建者id
    String created;   //  创建时间
    String modified;   //  修改时间

    public String getTotalId() {
        return totalId;
    }

    public void setTotalId(String totalId) {
        this.totalId = totalId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getGroupHeadImg() {
        return groupHeadImg;
    }

    public void setGroupHeadImg(String groupHeadImg) {
        this.groupHeadImg = groupHeadImg;
    }

    public String getGroupInstruction() {
        return groupInstruction;
    }

    public void setGroupInstruction(String groupInstruction) {
        this.groupInstruction = groupInstruction;
    }

    public String getGroupQrcode() {
        return groupQrcode;
    }

    public void setGroupQrcode(String groupQrcode) {
        this.groupQrcode = groupQrcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(String maxNum) {
        this.maxNum = maxNum;
    }

    public String getNowNum() {
        return nowNum;
    }

    public void setNowNum(String nowNum) {
        this.nowNum = nowNum;
    }

    public String getCreatedId() {
        return createdId;
    }

    public void setCreatedId(String createdId) {
        this.createdId = createdId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }
}
