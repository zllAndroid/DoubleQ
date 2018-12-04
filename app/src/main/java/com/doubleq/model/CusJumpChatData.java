package com.doubleq.model;

import java.io.Serializable;

//跳转到聊天界面所需要的数据
public class CusJumpChatData implements Serializable{
    String friendHeader;
    String friendName;
    String friendId;

    public String getFriendHeader() {
        return friendHeader;
    }

    public void setFriendHeader(String friendHeader) {
        this.friendHeader = friendHeader;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }
}
