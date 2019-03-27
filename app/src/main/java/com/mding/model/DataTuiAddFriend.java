package com.mding.model;

public class DataTuiAddFriend {

    /**
     * code : 200
     * msg : 成功
     * method : addFriendSend
     * record : {"friendsId":"2644b163-b898-ba84-a7be-69cb62f214c7","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154113866221225.png","nickName":"小强子","remark":"dasd","userId":"49a4-9b2-68"}
     * api_key : 20180903
     * sign : AFA2E1FA63760040E711F548B3BA880B
     * timestamp : 1542334189
     */

    private int code;
    private String msg;
    private String method;
    private RecordBean record;
    private String api_key;
    private String sign;
    private int timestamp;

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

    public static class RecordBean {
        /**
         * friendsId : 2644b163-b898-ba84-a7be-69cb62f214c7
         * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154113866221225.png
         * nickName : 小强子
         * remark : dasd
         * userId : 49a4-9b2-68
         */

        private String friendsId;
        private String headImg;
        private String nickName;
        private String remark;
        private String userId;

        public String getFriendsId() {
            return friendsId;
        }

        public void setFriendsId(String friendsId) {
            this.friendsId = friendsId;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
