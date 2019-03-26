package com.mding.model;

public class DataModifyFriendList {

    /**
     * code : 200
     * msg : 成功
     * method : modifyFriendListSend
     * record : {"friendsId":"1b42-b9c2-c6f","listType":2,"modified":"1547792019","newChart":"H","newGroupId":"0","newGroupName":"","newHeadImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png","newNickName":"湖里","newRemarkName":"辉辉","oldChart":"H","oldGroupId":"0","oldGroupName":"","oldHeadImg":"dfadfdasfasfa","oldNickName":"3434f","oldRemarkName":"辉辉","remarkName":"辉辉","userId":"9a36-9ec1-412"}
     * api_key : 20180903
     * sign : 8C5D2BABFCE7EACCE0C64895F0AEF473
     * timestamp : 1548315777
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
         * friendsId : 1b42-b9c2-c6f
         * listType : 2
         * modified : 1547792019
         * newChart : H
         * newGroupId : 0
         * newGroupName :
         * newHeadImg : http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png
         * newNickName : 湖里
         * newRemarkName : 辉辉
         * oldChart : H
         * oldGroupId : 0
         * oldGroupName :
         * oldHeadImg : dfadfdasfasfa
         * oldNickName : 3434f
         * oldRemarkName : 辉辉
         * remarkName : 辉辉
         * userId : 9a36-9ec1-412
         */

        private String friendsId;
        private int listType;
        private String modified;
        private String newChart;
        private String newGroupId;
        private String newGroupName;
        private String newHeadImg;
        private String newNickName;
        private String newRemarkName;
        private String oldChart;
        private String oldGroupId;
        private String oldGroupName;
        private String oldHeadImg;
        private String oldNickName;
        private String oldRemarkName;
        private String remarkName;
        private String userId;

        public String getFriendsId() {
            return friendsId;
        }

        public void setFriendsId(String friendsId) {
            this.friendsId = friendsId;
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

        public String getNewGroupId() {
            return newGroupId;
        }

        public void setNewGroupId(String newGroupId) {
            this.newGroupId = newGroupId;
        }

        public String getNewGroupName() {
            return newGroupName;
        }

        public void setNewGroupName(String newGroupName) {
            this.newGroupName = newGroupName;
        }

        public String getNewHeadImg() {
            return newHeadImg;
        }

        public void setNewHeadImg(String newHeadImg) {
            this.newHeadImg = newHeadImg;
        }

        public String getNewNickName() {
            return newNickName;
        }

        public void setNewNickName(String newNickName) {
            this.newNickName = newNickName;
        }

        public String getNewRemarkName() {
            return newRemarkName;
        }

        public void setNewRemarkName(String newRemarkName) {
            this.newRemarkName = newRemarkName;
        }

        public String getOldChart() {
            return oldChart;
        }

        public void setOldChart(String oldChart) {
            this.oldChart = oldChart;
        }

        public String getOldGroupId() {
            return oldGroupId;
        }

        public void setOldGroupId(String oldGroupId) {
            this.oldGroupId = oldGroupId;
        }

        public String getOldGroupName() {
            return oldGroupName;
        }

        public void setOldGroupName(String oldGroupName) {
            this.oldGroupName = oldGroupName;
        }

        public String getOldHeadImg() {
            return oldHeadImg;
        }

        public void setOldHeadImg(String oldHeadImg) {
            this.oldHeadImg = oldHeadImg;
        }

        public String getOldNickName() {
            return oldNickName;
        }

        public void setOldNickName(String oldNickName) {
            this.oldNickName = oldNickName;
        }

        public String getOldRemarkName() {
            return oldRemarkName;
        }

        public void setOldRemarkName(String oldRemarkName) {
            this.oldRemarkName = oldRemarkName;
        }

        public String getRemarkName() {
            return remarkName;
        }

        public void setRemarkName(String remarkName) {
            this.remarkName = remarkName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
