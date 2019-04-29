package com.mding.model;

/**
 * 项目：DoubleQ
 * 文件描述：
 * 作者：zll
 * 创建时间：2019/4/29 0029
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class DataForceLogin {


    /**
     * code : 200
     * msg : 成功
     * method : sendForceLogin
     * record : {"machineCode":"","message":"您的账户于2019-04-29 22:58:43在另一台设备上登录。","modelName":"","userId":"5f98-83f5-9a3"}
     * api_key : 20180903
     * sign : E04EC3E40F377F872380529EF5395CA3
     * timestamp : 1556549923
     * only : 2
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
         * machineCode :
         * message : 您的账户于2019-04-29 22:58:43在另一台设备上登录。
         * modelName :
         * userId : 5f98-83f5-9a3
         */

        private String machineCode;
        private String message;
        private String modelName;
        private String userId;

        public String getMachineCode() {
            return machineCode;
        }

        public void setMachineCode(String machineCode) {
            this.machineCode = machineCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getModelName() {
            return modelName;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
