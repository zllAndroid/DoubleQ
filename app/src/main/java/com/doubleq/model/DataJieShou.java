package com.doubleq.model;

public class DataJieShou {

    /**
     * code : 200
     * msg : 成功
     * method : privateSend
     * record : {"friendsId":"3","message":"123","messageStoId":13,"messageType":"1","userId":"cb16ce45-854c-f553-9c9a-2e57a6addca3"}
     * api_key : 20180903
     * sign : 768A21C4A4C3C915F11EF00BE035212A
     * timestamp : 1540263783
     */

    private int code;
    private String msg;
    private String method;
    private RecordBean record;
    private String api_key;
    private String sign;
    private int timestamp;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public RecordBean getRecord() {
        return record;
    }

    public void setRecord(RecordBean record) {
        this.record = record;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public static class RecordBean {
        /**
         * friendsId : 3
         * message : 123
         * messageStoId : 13
         * messageType : 1
         * userId : cb16ce45-854c-f553-9c9a-2e57a6addca3
         */
        private int type;
        private int sendState;
        private String friendsId;
        private String message;
        private int messageStoId;
        private String messageType;
        private String userId;
        private String requestTime;
        private String friendsName;


        private String headImg;

        private String shieldType;//屏蔽

        private String topType;//置顶
        private String disturbType;//消息免打扰


        public String getTopType() {
            return topType;
        }

        public void setTopType(String topType) {
            this.topType = topType;
        }

        public String getDisturbType() {
            return disturbType;
        }

        public void setDisturbType(String disturbType) {
            this.disturbType = disturbType;
        }

        public String getShieldType() {
            return shieldType;
        }

        public void setShieldType(String shieldType) {
            this.shieldType = shieldType;
        }

        public String getFriendsName() {
            return friendsName;
        }

        public void setFriendsName(String friendsName) {
            this.friendsName = friendsName;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getRequestTime() {
            return requestTime;
        }

        public void setRequestTime(String requestTime) {
            this.requestTime = requestTime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getSendState() {
            return sendState;
        }

        public void setSendState(int sendState) {
            this.sendState = sendState;
        }

        public String getFriendsId() {
            return friendsId;
        }

        public void setFriendsId(String friendsId) {
            this.friendsId = friendsId;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getMessageStoId() {
            return messageStoId;
        }

        public void setMessageStoId(int messageStoId) {
            this.messageStoId = messageStoId;
        }

        public String getMessageType() {
            return messageType;
        }

        public void setMessageType(String messageType) {
            this.messageType = messageType;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
