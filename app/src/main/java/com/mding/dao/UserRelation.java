package com.mding.dao;

import com.mding.sql.TotalEntry;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 项目：DoubleQ
 * 文件描述：好友关系表
 * 作者：zll
 * 创建时间：2019/4/3 0003
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
@Entity(
        indexes = {
                @Index(value = "userId DESC, friendId DESC", unique = true)
        }
)
public class UserRelation {

//    public static String _USER_ID ="userId";

    @Id(autoincrement = true)
    Long id;

    String userId;

    String friendId;//好友id

    String groupId;//对应好友分组id 关联表t_group_manage

    String status;    //拉黑状态  2为拉黑 1为好友关系状态

    String remarkName;    //    好友备注名字
    String shieldType;    //屏蔽状态 1否 2是
    String queueType;    //删除消息   1否/2是
    String disturbType;    // 消息免打扰 1否 2是
    String nickName;    //    昵称
     String topType;//置顶状态
    //    头像
    String headImg;
    //    消息时间
    String created;//创建时间
    String modified;//修改时间

    String recordTime;//清除聊天记录的时间

@Generated(hash = 932060616)
public UserRelation(Long id, String userId, String friendId, String groupId,
        String status, String remarkName, String shieldType, String queueType,
        String disturbType, String nickName, String topType, String headImg,
        String created, String modified, String recordTime) {
    this.id = id;
    this.userId = userId;
    this.friendId = friendId;
    this.groupId = groupId;
    this.status = status;
    this.remarkName = remarkName;
    this.shieldType = shieldType;
    this.queueType = queueType;
    this.disturbType = disturbType;
    this.nickName = nickName;
    this.topType = topType;
    this.headImg = headImg;
    this.created = created;
    this.modified = modified;
    this.recordTime = recordTime;
}

@Generated(hash = 1108770591)
public UserRelation() {
}

    public static String toUserRelationEntryString(){
        return
                        "friendId" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "groupId" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "status" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "remarkName" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "shieldType" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "queueType" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "disturbType" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "nickName" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "topType" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "headImg" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "created" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                        "modified" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
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

public String getFriendId() {
    return this.friendId;
}

public void setFriendId(String friendId) {
    this.friendId = friendId;
}

public String getGroupId() {
    return this.groupId;
}

public void setGroupId(String groupId) {
    this.groupId = groupId;
}

public String getStatus() {
    return this.status;
}

public void setStatus(String status) {
    this.status = status;
}

public String getRemarkName() {
    return this.remarkName;
}

public void setRemarkName(String remarkName) {
    this.remarkName = remarkName;
}

public String getShieldType() {
    return this.shieldType;
}

public void setShieldType(String shieldType) {
    this.shieldType = shieldType;
}

public String getQueueType() {
    return this.queueType;
}

public void setQueueType(String queueType) {
    this.queueType = queueType;
}

public String getDisturbType() {
    return this.disturbType;
}

public void setDisturbType(String disturbType) {
    this.disturbType = disturbType;
}

public String getNickName() {
    return this.nickName;
}

public void setNickName(String nickName) {
    this.nickName = nickName;
}

public String getTopType() {
    return this.topType;
}

public void setTopType(String topType) {
    this.topType = topType;
}

public String getHeadImg() {
    return this.headImg;
}

public void setHeadImg(String headImg) {
    this.headImg = headImg;
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
