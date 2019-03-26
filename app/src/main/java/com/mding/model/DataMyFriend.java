package com.mding.model;

public class DataMyFriend {

    /**
     * code : 200
     * msg : 成功
     * method : getFriendInfo
     * record : {"email":"","friendId":"9a36-9ec1-412","groupName":"暂无","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png","isMobileShow":"1","isQrcodeShow":"1","isSnoShow":"1","mobile":"18065283783","nickName":"888","personaSignature":"","qrcode":"http://www.xm6leefun.cn:40005/default/img/099a65388eb7e84987228d795bee52b5.png","remarkName":"888","shieldType":"1","status":"1","wxSno":"44146cff4c640ac148bce839c929fac4"}
     * api_key : 20180903
     * sign : 0C61EAB038723E4C1AC83BC62322BEA5
     * timestamp : 1545199462
     * only : 1
     * verificationMD5Type : 1
     */

    private int code;
    private String msg;
    private String method;
    private RecordBean record;
    private String api_key;
    private String sign;
    private int timestamp;
    private int only;
    private String verificationMD5Type;

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

    public String getVerificationMD5Type() {
        return verificationMD5Type;
    }

    public void setVerificationMD5Type(String verificationMD5Type) {
        this.verificationMD5Type = verificationMD5Type;
    }

    public static class RecordBean {
        /**
         * email :
         * friendId : 9a36-9ec1-412
         * groupName : 暂无
         * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png
         * isMobileShow : 1
         * isQrcodeShow : 1
         * isSnoShow : 1
         * mobile : 18065283783
         * nickName : 888
         * personaSignature :
         * qrcode : http://www.xm6leefun.cn:40005/default/img/099a65388eb7e84987228d795bee52b5.png
         * remarkName : 888
         * shieldType : 1
         * status : 1
         * wxSno : 44146cff4c640ac148bce839c929fac4
         */

        private String email;
        private String friendId;
        private String groupName;
        private String headImg;
        private String isMobileShow;
        private String isQrcodeShow;
        private String isSnoShow;
        private String mobile;
        private String nickName;
        private String personaSignature;
        private String qrcode;
        private String remarkName;
        private String shieldType;
        private String status;
        private String wxSno;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFriendId() {
            return friendId;
        }

        public void setFriendId(String friendId) {
            this.friendId = friendId;
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

        public String getIsMobileShow() {
            return isMobileShow;
        }

        public void setIsMobileShow(String isMobileShow) {
            this.isMobileShow = isMobileShow;
        }

        public String getIsQrcodeShow() {
            return isQrcodeShow;
        }

        public void setIsQrcodeShow(String isQrcodeShow) {
            this.isQrcodeShow = isQrcodeShow;
        }

        public String getIsSnoShow() {
            return isSnoShow;
        }

        public void setIsSnoShow(String isSnoShow) {
            this.isSnoShow = isSnoShow;
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

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public String getRemarkName() {
            return remarkName;
        }

        public void setRemarkName(String remarkName) {
            this.remarkName = remarkName;
        }

        public String getShieldType() {
            return shieldType;
        }

        public void setShieldType(String shieldType) {
            this.shieldType = shieldType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getWxSno() {
            return wxSno;
        }

        public void setWxSno(String wxSno) {
            this.wxSno = wxSno;
        }
    }
}
