package com.mding.dao;

import com.mding.sql.TotalEntry;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 项目：DoubleQ
 * 文件描述：群信息表
 * 作者：ljj
 * 创建时间：2019/4/4
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
@Entity(
        indexes = {
                @Index(value = "userId DESC, groupId DESC", unique = true)
        }
)
public class GroupInfo {
    @Id(autoincrement = true)
    Long id;
    String userId;//用户id
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
    @Generated(hash = 321859703)
    public GroupInfo(Long id, String userId, String groupId, String groupName, String groupSno,
                     String groupType, String groupHeadImg, String groupInstruction, String groupQrcode,
                     String status, String maxNum, String nowNum, String createdId, String created,
                     String modified) {
        this.id = id;
        this.userId = userId;
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupSno = groupSno;
        this.groupType = groupType;
        this.groupHeadImg = groupHeadImg;
        this.groupInstruction = groupInstruction;
        this.groupQrcode = groupQrcode;
        this.status = status;
        this.maxNum = maxNum;
        this.nowNum = nowNum;
        this.createdId = createdId;
        this.created = created;
        this.modified = modified;
    }
    @Generated(hash = 1250265142)
    public GroupInfo() {
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
    public String getGroupId() {
        return this.groupId;
    }
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public String getGroupName() {
        return this.groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public String getGroupSno() {
        return this.groupSno;
    }
    public void setGroupSno(String groupSno) {
        this.groupSno = groupSno;
    }
    public String getGroupType() {
        return this.groupType;
    }
    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }
    public String getGroupHeadImg() {
        return this.groupHeadImg;
    }
    public void setGroupHeadImg(String groupHeadImg) {
        this.groupHeadImg = groupHeadImg;
    }
    public String getGroupInstruction() {
        return this.groupInstruction;
    }
    public void setGroupInstruction(String groupInstruction) {
        this.groupInstruction = groupInstruction;
    }
    public String getGroupQrcode() {
        return this.groupQrcode;
    }
    public void setGroupQrcode(String groupQrcode) {
        this.groupQrcode = groupQrcode;
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMaxNum() {
        return this.maxNum;
    }
    public void setMaxNum(String maxNum) {
        this.maxNum = maxNum;
    }
    public String getNowNum() {
        return this.nowNum;
    }
    public void setNowNum(String nowNum) {
        this.nowNum = nowNum;
    }
    public String getCreatedId() {
        return this.createdId;
    }
    public void setCreatedId(String createdId) {
        this.createdId = createdId;
    }
    public String getCreated() {
        return this.created;
    }
    public void setCreated(String created) {
        this.created = created;
    }
    public String getModified() {
        return this.modified;
    }
    public void setModified(String modified) {
        this.modified = modified;
    }


//    public static String toGroupInfoString(){
//        return
////                "_ID" +  TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
//                "group_name" +  TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
//                        "groupSno" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
//                        "groupType" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
//                        "groupHeadImg" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
//                        "groupInstruction" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
//                        "groupQrcode" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
//                        "status" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
//                        "maxNum" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
//                        "nowNum" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP   +
//                        "createdId" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP +
//                        "created" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP +
//                        "modified" + TotalEntry.TEXT_TYPE ;
//    }
}
