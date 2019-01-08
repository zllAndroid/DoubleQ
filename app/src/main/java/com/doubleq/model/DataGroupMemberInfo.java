package com.doubleq.model;
//获取群成员资料信息
public class DataGroupMemberInfo {

    /**
     * code : 200
     * msg : 成功
     * method : getGroupMemberInfo
     * record : {"carteName":"","email":"","headImg":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","isFriend":2,"memberId":"4","mobile":"18065283788","nickName":"android","persona_signature":"","wxSno":"b6cb0d083b77b44565467586797890"}
     * verificationMD5Type : 1
     * api_key : 20180903
     * sign : F48386E395C92B47C5212BE255C74162
     * timestamp : 1539765389
     */

    private int code;
    private String msg;
    private String method;
    private RecordBean record;
    private String verificationMD5Type;
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

    public String getVerificationMD5Type() {
        return verificationMD5Type;
    }

    public void setVerificationMD5Type(String verificationMD5Type) {
        this.verificationMD5Type = verificationMD5Type;
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
         * carteName :
         * email :
         * headImg : http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132
         * isFriend : 2
         * memberId : 4
         * mobile : 18065283788
         * nickName : android
         * persona_signature :
         * wxSno : b6cb0d083b77b44565467586797890
         */

        private String carteName;
        private String email;
        private String headImg;
        private int isFriend;
        private String memberId;
        private String mobile;
        private String nickName;
        private String persona_signature;
        private String wxSno;

        public String getCarteName() {
            return carteName;
        }

        public void setCarteName(String carteName) {
            this.carteName = carteName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public int getIsFriend() {
            return isFriend;
        }

        public void setIsFriend(int isFriend) {
            this.isFriend = isFriend;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
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

        public String getPersona_signature() {
            return persona_signature;
        }

        public void setPersona_signature(String persona_signature) {
            this.persona_signature = persona_signature;
        }

        public String getWxSno() {
            return wxSno;
        }

        public void setWxSno(String wxSno) {
            this.wxSno = wxSno;
        }
    }
}
