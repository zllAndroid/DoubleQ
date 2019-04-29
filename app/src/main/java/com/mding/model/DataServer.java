package com.mding.model;

public class DataServer {
    /**
     * code : 200
     * msg : 成功
     * method : getClusterIp
     * record : {"swooleServer":"47.107.108.224","httpProtocolIp":"http://47.107.108.224:30092","wsProtocolIp":"ws://47.107.108.224:30093"}
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
         * swooleServer : 47.107.108.224
         * httpProtocolIp : http://47.107.108.224:30092
         * wsProtocolIp : ws://47.107.108.224:30093
         */

        private String swooleServer;
        private String httpProtocolIp;
        private String wsProtocolIp;

        public String getSwooleServer() {
            return swooleServer;
        }

        public void setSwooleServer(String swooleServer) {
            this.swooleServer = swooleServer;
        }

        public String getHttpProtocolIp() {
            return httpProtocolIp;
        }

        public void setHttpProtocolIp(String httpProtocolIp) {
            this.httpProtocolIp = httpProtocolIp;
        }

        public String getWsProtocolIp() {
            return wsProtocolIp;
        }

        public void setWsProtocolIp(String wsProtocolIp) {
            this.wsProtocolIp = wsProtocolIp;
        }
    }


//    /**
//     * swooleServer : 192.168.4.66:8000
//     */
//
//    private String swooleServer;
//    private String swooleServer_v1;
//
//    public String getSwooleServer() {
//        return swooleServer_v1;
//    }
//
//    public void setSwooleServer(String swooleServer) {
//        this.swooleServer = swooleServer;
//    }
//
//    public String getSwooleServer_v1() {
//        return swooleServer_v1;
//    }
//
//    public void setSwooleServer_v1(String swooleServer_v1) {
//        this.swooleServer_v1 = swooleServer_v1;
//    }
////    /**
////     * sd_server : 192.168.4.66:8000
////     */
////
////    private String sd_server;
////
////    public String getSd_server() {
////        return sd_server;
////    }
////
////    public void setSd_server(String sd_server) {
////        this.sd_server = sd_server;
////    }
}
