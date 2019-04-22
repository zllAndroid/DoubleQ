package com.mding.chatfeng.about_broadcastreceiver;

public class MsgHomeEvent {
    public String message;
    public String id;
    public String type;
    public MsgHomeEvent() {}
    public MsgHomeEvent(String message, String id, String type) {
        this.message = message;
        this.id = id;
        this.type = type;
    }
    public MsgHomeEvent(String type) {
        this.type = type;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
