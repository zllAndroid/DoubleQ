package com.mding.model;

public class DataJiqun {


    /**
     * code : 200
     * msg : 成功
     * method : addrPort
     * record : {"serverIpWs":"ws://127.0.0.1:5053","serverIpHttp":"127.0.0.1:5052"}
     */

    private int code;
    private String msg;
    private String method;
    private RecordBean record;

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

    public static class RecordBean {
        /**
         * serverIpWs : ws://127.0.0.1:5053
         * serverIpHttp : 127.0.0.1:5052
         */

        private String serverIpWs;
        private String serverIpHttp;

        public String getServerIpWs() {
            return serverIpWs;
        }

        public void setServerIpWs(String serverIpWs) {
            this.serverIpWs = serverIpWs;
        }

        public String getServerIpHttp() {
            return serverIpHttp;
        }

        public void setServerIpHttp(String serverIpHttp) {
            this.serverIpHttp = serverIpHttp;
        }
    }
}
