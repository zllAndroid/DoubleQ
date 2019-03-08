package com.doubleq.model;

public class DataModifyGroupOfList {

    /**
     * code : 200
     * msg : 成功
     * method : modifyGroupOfListSend
     * record : {"groupId":"1","listType":2,"newChart":"#","newGroupHeadImg":"http://192.168.4.131:40005/default/img/default_group.png","newGroupManageId":"0","newGroupManageName":"","newGroupName":"666","oldChart":"#","oldGroupHeadImg":"343242","oldGroupManageId":"0","oldGroupManageName":"","oldGroupName":"234vd","userId":"9a36-9ec1-412"}
     * api_key : 20180903
     * sign : 87BBAF81CDED0375A100D741CE32DC36
     * timestamp : 1548317371
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
         * groupId : 1
         * listType : 2
         * newChart : #
         * newGroupHeadImg : http://192.168.4.131:40005/default/img/default_group.png
         * newGroupManageId : 0
         * newGroupManageName :
         * newGroupName : 666
         * oldChart : #
         * oldGroupHeadImg : 343242
         * oldGroupManageId : 0
         * oldGroupManageName :
         * oldGroupName : 234vd
         * userId : 9a36-9ec1-412
         */

        private String groupId;
        private int listType;
        private String newChart;
        private String modified;
        private String newGroupHeadImg;
        private String newGroupManageId;
        private String newGroupManageName;
        private String newGroupName;
        private String oldChart;
        private String oldGroupHeadImg;
        private String oldGroupManageId;
        private String oldGroupManageName;
        private String oldGroupName;
        private String userId;

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
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

        public String getNewChart() {
            return newChart;
        }

        public void setNewChart(String newChart) {
            this.newChart = newChart;
        }

        public String getNewGroupHeadImg() {
            return newGroupHeadImg;
        }

        public void setNewGroupHeadImg(String newGroupHeadImg) {
            this.newGroupHeadImg = newGroupHeadImg;
        }

        public String getNewGroupManageId() {
            return newGroupManageId;
        }

        public void setNewGroupManageId(String newGroupManageId) {
            this.newGroupManageId = newGroupManageId;
        }

        public String getNewGroupManageName() {
            return newGroupManageName;
        }

        public void setNewGroupManageName(String newGroupManageName) {
            this.newGroupManageName = newGroupManageName;
        }

        public String getNewGroupName() {
            return newGroupName;
        }

        public void setNewGroupName(String newGroupName) {
            this.newGroupName = newGroupName;
        }

        public String getOldChart() {
            return oldChart;
        }

        public void setOldChart(String oldChart) {
            this.oldChart = oldChart;
        }

        public String getOldGroupHeadImg() {
            return oldGroupHeadImg;
        }

        public void setOldGroupHeadImg(String oldGroupHeadImg) {
            this.oldGroupHeadImg = oldGroupHeadImg;
        }

        public String getOldGroupManageId() {
            return oldGroupManageId;
        }

        public void setOldGroupManageId(String oldGroupManageId) {
            this.oldGroupManageId = oldGroupManageId;
        }

        public String getOldGroupManageName() {
            return oldGroupManageName;
        }

        public void setOldGroupManageName(String oldGroupManageName) {
            this.oldGroupManageName = oldGroupManageName;
        }

        public String getOldGroupName() {
            return oldGroupName;
        }

        public void setOldGroupName(String oldGroupName) {
            this.oldGroupName = oldGroupName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
