package com.doubleq.model;

import android.content.Context;
import android.widget.LinearLayout;

import java.io.Serializable;

public class CusChatPop implements Serializable {
    Context context;
    String friendId;
    String groupId;
    String groupingName;
    String remarkName;
    String cardName;
    LinearLayout chatLinMainWhole;
    String isChecked;
    String type;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupingName() {
        return groupingName;
    }

    public void setGroupingName(String groupingName) {
        this.groupingName = groupingName;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public LinearLayout getChatLinMainWhole() {
        return chatLinMainWhole;
    }

    public void setChatLinMainWhole(LinearLayout chatLinMainWhole) {
        this.chatLinMainWhole = chatLinMainWhole;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
