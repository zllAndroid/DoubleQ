package com.mding.chatfeng.about_chat.cus_data_group;

import java.io.Serializable;

//跳转到聊天界面所需要的数据
public class CusJumpGroupChatData implements Serializable{
    String groupName;
    String groupId;
    String disturbType;
    String identifyType;
    String cardName;

    public String getIdentifyType() {
        return identifyType;
    }

    public void setIdentifyType(String identifyType) {
        this.identifyType = identifyType;
    }
    public String getDisturbType() {
        return disturbType;
    }

    public void setDisturbType(String disturbType) {
        this.disturbType = disturbType;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

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
