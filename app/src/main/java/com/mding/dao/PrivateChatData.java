package com.mding.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 项目：DoubleQ
 * 文件描述：私聊
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
public class PrivateChatData {
    @Id(autoincrement = true)
    Long id;
    String userId;
    String friendId;
    String  totalId;
    String  messageStoId;
    String  sendId;//    发送者ID
    String  receiveId;//    接收者ID
    String  messageType;//    消息类型 1文字 2图 3表情 4文件
    int  userMessageType; //1右 发送，2左 接收
    String  message; //    消息内容
    String  imgUrl; //    消息内容

    String  created; //    发送时间

@Generated(hash = 1133667981)
public PrivateChatData(Long id, String userId, String friendId, String totalId,
        String messageStoId, String sendId, String receiveId,
        String messageType, int userMessageType, String message, String imgUrl,
        String created) {
    this.id = id;
    this.userId = userId;
    this.friendId = friendId;
    this.totalId = totalId;
    this.messageStoId = messageStoId;
    this.sendId = sendId;
    this.receiveId = receiveId;
    this.messageType = messageType;
    this.userMessageType = userMessageType;
    this.message = message;
    this.imgUrl = imgUrl;
    this.created = created;
}

@Generated(hash = 439737305)
public PrivateChatData() {
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

public String getTotalId() {
    return this.totalId;
}

public void setTotalId(String totalId) {
    this.totalId = totalId;
}

public String getMessageStoId() {
    return this.messageStoId;
}

public void setMessageStoId(String messageStoId) {
    this.messageStoId = messageStoId;
}

public String getSendId() {
    return this.sendId;
}

public void setSendId(String sendId) {
    this.sendId = sendId;
}

public String getReceiveId() {
    return this.receiveId;
}

public void setReceiveId(String receiveId) {
    this.receiveId = receiveId;
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

public String getMessage() {
    return this.message;
}

public void setMessage(String message) {
    this.message = message;
}

public String getImgUrl() {
    return this.imgUrl;
}

public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
}

public String getCreated() {
    return this.created;
}

public void setCreated(String created) {
    this.created = created;
}

}
