package com.doubleq.model.push_data;

public class DataAboutGroup {


    /**
     * code : 200
     * msg : 成功
     * method : agreeGroupListSend
     * record : {"chat":"H","groupHeadImg":"http://192.168.4.131:40005/default/img/default_group.png","groupId":42,"groupManageId":"0","groupManageName":"","groupName":"海洋","listType":1,"userId":"3dfc-dcb0-da3"}
     * api_key : 20180903
     * sign : 4F77E2774C1A62BEF476C4213BFB0BCC
     * timestamp : 1547707989
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
         * chart : H
         * groupHeadImg : http://192.168.4.131:40005/default/img/default_group.png
         * groupId : 42
         * groupManageId : 0
         * groupManageName :
         * groupName : 海洋
         * listType : 1
         * userId : 3dfc-dcb0-da3
         */

        private String chart;
        private String groupHeadImg;
        private String groupId;
        private String groupManageId;
        private String groupManageName;
        private String groupName;
        private String listType;
        private String userId;

        public String getChart() {
            return chart;
        }

        public void setChart(String chart) {
            this.chart = chart;
        }

        public String getGroupHeadImg() {
            return groupHeadImg;
        }

        public void setGroupHeadImg(String groupHeadImg) {
            this.groupHeadImg = groupHeadImg;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getGroupManageId() {
            return groupManageId;
        }

        public void setGroupManageId(String groupManageId) {
            this.groupManageId = groupManageId;
        }

        public String getGroupManageName() {
            return groupManageName;
        }

        public void setGroupManageName(String groupManageName) {
            this.groupManageName = groupManageName;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getListType() {
            return listType;
        }

        public void setListType(String listType) {
            this.listType = listType;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
