package com.doubleq.xm6leefunz.about_chat.chat_group.sub_group.about_intent_data;

import java.io.Serializable;

public class IntentDataInvitation implements Serializable {

    String groupId;
    String groupType;
    String groupTital;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getGroupTital() {
        return groupTital;
    }

    public void setGroupTital(String groupTital) {
        this.groupTital = groupTital;
    }
}
