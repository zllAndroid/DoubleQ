package com.doubleq.xm6leefunz.about_chat.cus_data_group;

import io.realm.RealmObject;

public class CusGroupChatData  extends RealmObject {

    String groupUserId;
    String groupId;
    String friendId;
    String message;
    String imgHead;
    String name;
    String messageType;//    消息类型 1文字 2图 3表情 4文件
    int  userMessageType; //1右 发送，2左 接收
    String  created; //    发送时间

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
