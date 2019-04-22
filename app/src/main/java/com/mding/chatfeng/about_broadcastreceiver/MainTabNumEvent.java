package com.mding.chatfeng.about_broadcastreceiver;

public class MainTabNumEvent {
    public int num;
    public String type;
    public MainTabNumEvent() {}
    public MainTabNumEvent(int num,String type) {
        this.num = num;
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
