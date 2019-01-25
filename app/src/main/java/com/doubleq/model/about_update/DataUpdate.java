package com.doubleq.model.about_update;

public class DataUpdate {


    /**
     * code : 200
     * msg : 成功
     * method : appUpdate
     * record : {"device":"android","force":"1","newVs":"1.0.0","nowVs":"1.0.0","time":"2018-01-20","update":"1","url":""}
     * api_key : 20180903
     * sign : F28D2232F58CC018041470A021E22C92
     * timestamp : 1548310247
     * only : 1
     */

    private int code;
    private String msg;
    private String method;
    private RecordBean record;
    private String api_key;
    private String sign;
    private int timestamp;
    private int only;

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

    public int getOnly() {
        return only;
    }

    public void setOnly(int only) {
        this.only = only;
    }

    public static class RecordBean {
        /**
         * device : android
         * force : 1
         * newVs : 1.0.0
         * nowVs : 1.0.0
         * time : 2018-01-20
         * update : 1
         * url :
         *  vsName最新版本名
         *  content更新说明
         */

        private String device;
        private String force;
        private String newVs;
        private String nowVs;
        private String time;
        private String update;
        private String url;
        private String content;
        private String vsName;

        public String getVsName() {
            return vsName;
        }

        public void setVsName(String vsName) {
            this.vsName = vsName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public String getForce() {
            return force;
        }

        public void setForce(String force) {
            this.force = force;
        }

        public String getNewVs() {
            return newVs;
        }

        public void setNewVs(String newVs) {
            this.newVs = newVs;
        }

        public String getNowVs() {
            return nowVs;
        }

        public void setNowVs(String nowVs) {
            this.nowVs = nowVs;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUpdate() {
            return update;
        }

        public void setUpdate(String update) {
            this.update = update;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
