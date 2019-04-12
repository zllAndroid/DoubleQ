package com.mding.dao;

import com.mding.sql.TotalEntry;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 项目：DoubleQ
 * 文件描述：群用户关系表
 * 作者：ljj
 * 创建时间：2019/4/3
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
@Entity(
        indexes = {
                @Index(value = "userId DESC, groupId DESC", unique = true)
        }
)
public class GroupUserRelation {
//    public static String _ID ="id";   //  用户对群关系表的主键
    @Id(autoincrement = true)
    Long id;
    String userId;//用户编号
    String groupId;   //  创建群主表id 关联表chatGroup
    String friendId;   //  群员编号
    String carteName;   //  群名片
    String identitType;   //  群员身份1：群主；2：管理员；3：普通群员
    String operationType;   //  群员是否屏蔽该群 1：否；2：是
    String bannedtype;   //  是否被禁言 1：否；2：是
    String groupManageId;   //  群对应分组id   关联表t_group_manage主键
    String disturbType;   //  消息免打扰 1否 2是
    String assistantType;   //  加入群助手 1否 2是
    String topType;   //  置顶状态 1否 2是

    //  消息时间
    String created;   //  创建时间
    String modified;   //  修改时间
    String recordTime;   //  清除聊天记录的时间

@Generated(hash = 631059403)
public GroupUserRelation(Long id, String userId, String groupId, String friendId,
        String carteName, String identitType, String operationType, String bannedtype,
        String groupManageId, String disturbType, String assistantType, String topType,
        String created, String modified, String recordTime) {
    this.id = id;
    this.userId = userId;
    this.groupId = groupId;
    this.friendId = friendId;
    this.carteName = carteName;
    this.identitType = identitType;
    this.operationType = operationType;
    this.bannedtype = bannedtype;
    this.groupManageId = groupManageId;
    this.disturbType = disturbType;
    this.assistantType = assistantType;
    this.topType = topType;
    this.created = created;
    this.modified = modified;
    this.recordTime = recordTime;
}

@Generated(hash = 1734738817)
public GroupUserRelation() {
}

    public static String toGroupUserRelationString(){
        return
//                "_ID" +  TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
//                        "groupId" +  TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "userId" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
                        "carteName" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
                        "identitType" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
                        "operationType" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
                        "bannedtype" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
                        "groupManageId" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
                        "disturbType" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
                        "assistantType" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP   +
                        "topType" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP +
                        "created" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP +
                        "modified" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP +
                        "recordTime" + TotalEntry.TEXT_TYPE ;
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

public String getFriendId() {
    return this.friendId;
}

public void setFriendId(String friendId) {
    this.friendId = friendId;
}

public String getCarteName() {
    return this.carteName;
}

public void setCarteName(String carteName) {
    this.carteName = carteName;
}

public String getIdentitType() {
    return this.identitType;
}

public void setIdentitType(String identitType) {
    this.identitType = identitType;
}

public String getOperationType() {
    return this.operationType;
}

public void setOperationType(String operationType) {
    this.operationType = operationType;
}

public String getBannedtype() {
    return this.bannedtype;
}

public void setBannedtype(String bannedtype) {
    this.bannedtype = bannedtype;
}

public String getGroupManageId() {
    return this.groupManageId;
}

public void setGroupManageId(String groupManageId) {
    this.groupManageId = groupManageId;
}

public String getDisturbType() {
    return this.disturbType;
}

public void setDisturbType(String disturbType) {
    this.disturbType = disturbType;
}

public String getAssistantType() {
    return this.assistantType;
}

public void setAssistantType(String assistantType) {
    this.assistantType = assistantType;
}

public String getTopType() {
    return this.topType;
}

public void setTopType(String topType) {
    this.topType = topType;
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

public String getRecordTime() {
    return this.recordTime;
}

public void setRecordTime(String recordTime) {
    this.recordTime = recordTime;
}


}
