package com.mding.model;

public class DataGroupSend {

    /**
     * code : 200
     * msg : 成功
     * method : groupSend
     * record : {"groupId":"1","message":"123","messageStoId":23,"messageType":"1","requestTime":121312131,"userId":"3"}
     * api_key : 20180903
     * sign : 3C0A1D199567CBA04E3A6C083E1E9E08
     * timestamp : 1540952008
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
         * groupId : 1
         * message : 123
         * messageStoId : 23
         * messageType : 1
         * requestTime : 121312131
         * userId : 3
         */

        private String groupId;
        private String message;
        private int messageStoId;
        private String messageType;
        private int requestTime;
        private String userId;

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
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

        public int getRequestTime() {
            return requestTime;
        }

        public void setRequestTime(int requestTime) {
            this.requestTime = requestTime;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
