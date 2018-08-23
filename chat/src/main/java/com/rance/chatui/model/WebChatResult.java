package com.rance.chatui.model;

public class WebChatResult {
    /**
     * code : 200
     * msg : 成功
     * record : {"msg":"你到底想怎样"}
     * api_key : 20180313
     * sign : 22DFD9B8EAD58A74C7289D1385D89E58
     * timestamp : 1534781517
     */

    private int code;
    private String msg;
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
         * msg : 你到底想怎样
         */

        private String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

//    {"code":200,"msg":"成功","record":{"msg":"你到底想怎样"},"api_key":"20180313","sign":"22DFD9B8EAD58A74C7289D1385D89E58","timestamp":1534781517}

}
