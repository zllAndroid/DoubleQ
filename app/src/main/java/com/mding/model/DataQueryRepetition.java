package com.mding.model;

/**
 * 项目：DoubleQ
 * 文件描述：getQueryRepetition 接口数据  发送图片前检测
 * 作者：ljj
 * 创建时间：2019/4/29
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class DataQueryRepetition {

    /**
     * code : 200
     * msg : 成功
     * method : getQueryRepetition
     * record : {"fileMd5":"5cc592cc9dc6d6faef45a5d4","fileType":1,"type":"2","userId":"f817-2e60-e7c"}
     * api_key : 20180903
     * sign : BEEDCF07B6BB46A43B7D930325B2CB17
     * timestamp : 1556558410
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
         * fileMd5 : 5cc592cc9dc6d6faef45a5d4
         * fileType : 1
         * type : 2
         * userId : f817-2e60-e7c
         */

        private String fileMd5;
        private int fileType;
        private String type;
        private String userId;

        public String getFileMd5() {
            return fileMd5;
        }

        public void setFileMd5(String fileMd5) {
            this.fileMd5 = fileMd5;
        }

        public int getFileType() {
            return fileType;
        }

        public void setFileType(int fileType) {
            this.fileType = fileType;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
