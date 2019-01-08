package com.doubleq.model;

public class DataScanFirendRequest {


    /**
     * code : 200
     * msg : 成功
     * method : addFriendQrCode
     * record : {"friendId":"1434-b183-449","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154330938250066.png","mobile":"18150960007","nickName":"3333","personaSignature":"","wxSno":"2566a72ed38b0275d57a85f35ba4b297"}
     * api_key : 20180903
     * sign : 4E90E2C1B884DCDB6B361C677BF4AF65
     * timestamp : 1543549009
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
         * friendId : 1434-b183-449
         * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154330938250066.png
         * mobile : 18150960007
         * nickName : 3333
         * personaSignature :
         * wxSno : 2566a72ed38b0275d57a85f35ba4b297
         */

        private String friendId;
        private String headImg;
        private String mobile;
        private String nickName;
        private String personaSignature;
        private String wxSno;

        public String getFriendId() {
            return friendId;
        }

        public void setFriendId(String friendId) {
            this.friendId = friendId;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPersonaSignature() {
            return personaSignature;
        }

        public void setPersonaSignature(String personaSignature) {
            this.personaSignature = personaSignature;
        }

        public String getWxSno() {
            return wxSno;
        }

        public void setWxSno(String wxSno) {
            this.wxSno = wxSno;
        }
    }
}
