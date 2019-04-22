package com.mding.chatfeng.about_broadcastreceiver;
//联系人列表的修改
public class LinkChangeEvent {
    public String type;
    public LinkChangeEvent(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
