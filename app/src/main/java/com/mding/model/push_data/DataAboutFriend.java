package com.mding.model.push_data;

public class DataAboutFriend {

    /**
     * code : 200
     * msg : 成功
     * method : agreeFriendListSend
     * record : {"chat":"~","friendsId":"9a36-9ec1-412","groupId":"3","groupName":"同事","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png","listType":1,"modified":"1547604007","nickName":"木木木1","remarkName":"888","userId":"1b42-b9c2-c6f"}
     * api_key : 20180903
     * sign : 1DCFC46E55AFCECF968AE3552CFD21DA
     * timestamp : 1547697648
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
         * chat : ~
         * friendsId : 9a36-9ec1-412
         * groupId : 3
         * groupName : 同事
         * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png
         * listType : 1
         * modified : 1547604007
         * nickName : 木木木1
         * remarkName : 888
         * userId : 1b42-b9c2-c6f
         */

        private String chart;
        private String friendsId;
        private String groupId;
        private String groupName;
        private String headImg;
        private int listType;
        private String modified;
        private String nickName;
        private String remarkName;
        private String userId;

        public String getChart() {
            return chart;
        }

        public void setChart(String chart) {
            this.chart = chart;
        }

        public String getFriendsId() {
            return friendsId;
        }

        public void setFriendsId(String friendsId) {
            this.friendsId = friendsId;
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

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
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

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
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
