package com.mding.model;

public class DataMsgEd {
    /**
     * code : 200
     * msg : 成功
     * method : privateReceive
     * record : {"friendsId":"cb16ce45-854c-f553-9c9a-2e57a6addca3","message":"123","userId":"3"}
     * api_key : 20180903
     * sign : E6C4A3D029254741E67BF9D06719A35B
     * timestamp : 1539325937
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
         * friendsId : cb16ce45-854c-f553-9c9a-2e57a6addca3
         * message : 123
         * userId : 3
         */

        private String friendsId;
        private String message;
        private String userId;

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

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
//    {"code":200,"msg":"成功","method":"privateReceive","record":{"friendsId":"cb16ce45-854c-f553-9c9a-2e57a6addca3","message":"123","userId":"3"},"api_key":"20180903","sign":"E6C4A3D029254741E67BF9D06719A35B","timestamp":1539325937}


}
