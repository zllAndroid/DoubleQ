package com.mding.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 项目：DoubleQ
 * 文件描述：群聊消息表
 * 作者：zll
 * 创建时间：2019/4/4 0004
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
@Entity(
        indexes = {
                @Index(value = "userId DESC, groupId DESC", unique = true)
        }
)
public class GroupChatData {
    @Id(autoincrement = true)
    Long id;

    String userId;
    String groupId;
    String friendId;
    String message;
    String imgHead;
    String imgGroup;
    String nameGroup;
    String nameFriend;
    String messageType;//    消息类型 1文字 2图 3表情 4文件
    int  userMessageType; //1右 发送，2左 接收
    String  created; //    发送时间

    int sendState;

    private String bannedType;//是否被禁言
    private String disturbType;//消息免打扰
    private String operationType;//屏蔽该群
    private String topType;//置顶状态
    private String assistantType;//加入群助手
@Generated(hash = 1878306763)
public GroupChatData(Long id, String userId, String groupId, String friendId,
        String message, String imgHead, String imgGroup, String nameGroup,
        String nameFriend, String messageType, int userMessageType,
        String created, int sendState, String bannedType, String disturbType,
        String operationType, String topType, String assistantType) {
    this.id = id;
    this.userId = userId;
    this.groupId = groupId;
    this.friendId = friendId;
    this.message = message;
    this.imgHead = imgHead;
    this.imgGroup = imgGroup;
    this.nameGroup = nameGroup;
    this.nameFriend = nameFriend;
    this.messageType = messageType;
    this.userMessageType = userMessageType;
    this.created = created;
    this.sendState = sendState;
    this.bannedType = bannedType;
    this.disturbType = disturbType;
    this.operationType = operationType;
    this.topType = topType;
    this.assistantType = assistantType;
}
@Generated(hash = 64761229)
public GroupChatData() {
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
public String getMessage() {
    return this.message;
}
public void setMessage(String message) {
    this.message = message;
}
public String getImgHead() {
    return this.imgHead;
}
public void setImgHead(String imgHead) {
    this.imgHead = imgHead;
}
public String getImgGroup() {
    return this.imgGroup;
}
public void setImgGroup(String imgGroup) {
    this.imgGroup = imgGroup;
}
public String getNameGroup() {
    return this.nameGroup;
}
public void setNameGroup(String nameGroup) {
    this.nameGroup = nameGroup;
}
public String getNameFriend() {
    return this.nameFriend;
}
public void setNameFriend(String nameFriend) {
    this.nameFriend = nameFriend;
}
public String getMessageType() {
    return this.messageType;
}
public void setMessageType(String messageType) {
    this.messageType = messageType;
}
public int getUserMessageType() {
    return this.userMessageType;
}
public void setUserMessageType(int userMessageType) {
    this.userMessageType = userMessageType;
}
public String getCreated() {
    return this.created;
}
public void setCreated(String created) {
    this.created = created;
}
public int getSendState() {
    return this.sendState;
}
public void setSendState(int sendState) {
    this.sendState = sendState;
}
public String getBannedType() {
    return this.bannedType;
}
public void setBannedType(String bannedType) {
    this.bannedType = bannedType;
}
public String getDisturbType() {
    return this.disturbType;
}
public void setDisturbType(String disturbType) {
    this.disturbType = disturbType;
}
public String getOperationType() {
    return this.operationType;
}
public void setOperationType(String operationType) {
    this.operationType = operationType;
}
public String getTopType() {
    return this.topType;
}
public void setTopType(String topType) {
    this.topType = topType;
}
public String getAssistantType() {
    return this.assistantType;
}
public void setAssistantType(String assistantType) {
    this.assistantType = assistantType;
}


}
