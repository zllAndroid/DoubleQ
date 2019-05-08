package com.mding.chatfeng.about_chat.cus_data_group;

import io.realm.RealmObject;

public class CusGroupChatData  extends RealmObject {

    String groupUserId;
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
   String  messageStoId; //消息专属id
    private String bannedType;//是否被禁言
    private String disturbType;//消息免打扰
    private String operationType;//屏蔽该群
    private String topType;//置顶状态
    private String assistantType;//加入群助手

    public String getMessageStoId() {
        return messageStoId;
    }

    public void setMessageStoId(String messageStoId) {
        this.messageStoId = messageStoId;
    }

    public String getAssistantType() {
        return assistantType;
    }

    public void setAssistantType(String assistantType) {
        this.assistantType = assistantType;
    }
//   int type;

    public String getBannedType() {
        return bannedType;
    }

    public void setBannedType(String bannedType) {
        this.bannedType = bannedType;
    }

    public String getDisturbType() {
        return disturbType;
    }

    public void setDisturbType(String disturbType) {
        this.disturbType = disturbType;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getTopType() {
        return topType;
    }

    public void setTopType(String topType) {
        this.topType = topType;
    }

    public int getSendState() {
        return sendState;
    }

    public void setSendState(int sendState) {
        this.sendState = sendState;
    }

//    public int getType() {
//        return type;
//    }
//
//    public void setType(int type) {
//        this.type = type;
//    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public String getNameFriend() {
        return nameFriend;
    }

    public void setNameFriend(String nameFriend) {
        this.nameFriend = nameFriend;
    }

    public String getImgGroup() {
        return imgGroup;
    }

    public void setImgGroup(String imgGroup) {
        this.imgGroup = imgGroup;
    }

    public int getUserMessageType() {
        return userMessageType;
    }

    public void setUserMessageType(int userMessageType) {
        this.userMessageType = userMessageType;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getGroupUserId() {
        return groupUserId;
    }

    public void setGroupUserId(String groupUserId) {
        this.groupUserId = groupUserId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImgHead() {
        return imgHead;
    }

    public void setImgHead(String imgHead) {
        this.imgHead = imgHead;
    }



    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
