package com.doubleq.model;

public class DataDeleteGroupingList {

    /**
     * code : 200
     * msg : 成功
     * method : deleteGroupingListSend
     * record : {"disabledStatus":"0","groupId":"3","groupName":"同事","listType":8,"modified":"0","sortId":"0","type":"1","userId":"1b42-b9c2-c6f"}
     * api_key : 20180903
     * sign : C735A044D357C2B394BBA0AF7EDF11A4
     * timestamp : 1548405527
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
         * disabledStatus : 0
         * groupId : 3
         * groupName : 同事
         * listType : 8
         * modified : 0
         * sortId : 0
         * type : 1
         * userId : 1b42-b9c2-c6f
         */

        private String disabledStatus;
        private String groupId;
        private String groupName;
        private int listType;
        private String modified;
        private String sortId;
        private String type;
        private String userId;

        public String getDisabledStatus() {
            return disabledStatus;
        }

        public void setDisabledStatus(String disabledStatus) {
            this.disabledStatus = disabledStatus;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public int getListType() {
            return listType;
        }

        public void setListType(int listType) {
            this.listType = listType;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public String getSortId() {
            return sortId;
        }

        public void setSortId(String sortId) {
            this.sortId = sortId;
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
