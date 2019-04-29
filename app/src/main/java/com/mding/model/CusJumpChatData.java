package com.mding.model;

import java.io.Serializable;

//跳转到聊天界面所需要的数据
public class CusJumpChatData implements Serializable{
    String friendHeader;
    String friendName;
    String friendRemarkName;
    String friendId;
    String friendGroupName;

    public String getFriendGroupName() {
        return friendGroupName;
    }

    public void setFriendGroupName(String friendGroupName) {
        this.friendGroupName = friendGroupName;
    }

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

    public String getFriendRemarkName() {
        return friendRemarkName;
    }

    public void setFriendRemarkName(String friendRemarkName) {
        this.friendRemarkName = friendRemarkName;
    }
}
