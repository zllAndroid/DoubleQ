package com.mding.model.push_data;

public class DataInvitationQrCodeGroupList {

    /**
     * code : 200
     * msg : 成功
     * method : invitationQrCodeGroupListSend
     * record : {"chart":"~","groupHeadImg":"http://192.168.4.131:40005/default/img/default_group.png","groupId":"1","groupManageId":"0","groupManageName":"","groupName":"666","listType":1,"userId":"1b42-b9c2-c6f"}
     * api_key : 20180903
     * sign : 3B8CB7398F341CD6C121A96D20919801
     * timestamp : 1547704227
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
         * chart : ~
         * groupHeadImg : http://192.168.4.131:40005/default/img/default_group.png
         * groupId : 1
         * groupManageId : 0
         * groupManageName :
         * groupName : 666
         * listType : 1
         * userId : 1b42-b9c2-c6f
         */

        private String chart;
        private String groupHeadImg;
        private String groupId;
        private String groupManageId;
        private String groupManageName;
        private String groupName;
        private int listType;
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

        public int getListType() {
            return listType;
        }

        public void setListType(int listType) {
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
