package com.mding.chatfeng.about_utils.about_realm.new_home;

import io.realm.RealmObject;

/**
 * 聊天内容相关的数据
 */

public  class CusChatData extends RealmObject {
    //    消息存储ID messageStoId varchar 13

    String  totalId;
    String  messageStoId;//消息专属id
    String  sendId;//    发送者ID
    String  receiveId;//    接收者ID
    String  messageType;//    消息类型 1文字 2图 3表情 4文件
    int  userMessageType; //1右 发送，2左 接收
    String  message; //    消息内容
    String  imgUrl; //    消息内容
    String  created; //    发送时间
    String  timeSort; //    发送时间
    int msgState;//消息状态（发送中，发送成功，发送失败）

    public int getMsgState() {
        return msgState;
    }

    public void setMsgState(int msgState) {
        this.msgState = msgState;
    }

    public String getTimeSort() {
        return timeSort;
    }

    public void setTimeSort(String timeSort) {
        this.timeSort = timeSort;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTotalId() {
        return totalId;
    }

    public void setTotalId(String totalId) {
        this.totalId = totalId;
    }

    public String getMessageStoId() {
        return messageStoId;
    }

    public void setMessageStoId(String messageStoId) {
        this.messageStoId = messageStoId;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public int getUserMessageType() {
        return userMessageType;
    }

    public void setUserMessageType(int userMessageType) {
        this.userMessageType = userMessageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
