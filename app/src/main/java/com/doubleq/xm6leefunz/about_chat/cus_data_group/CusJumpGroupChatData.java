package com.doubleq.xm6leefunz.about_chat.cus_data_group;

import java.io.Serializable;

//跳转到聊天界面所需要的数据
public class CusJumpGroupChatData implements Serializable{
    String groupName;
    String groupId;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
