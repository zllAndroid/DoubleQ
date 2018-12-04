package com.doubleq.model;

public class DataMyFriend {
    /**
     * code : 200
     * msg : 成功
     * method : getFriendInfo
     * record : {"email":"","friendId":"e0816099-d1d3-b20d-e114-fd56a192cc20","groupName":"家人","headImg":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","mobile":"18065283780","nickName":"海大胖","personaSignature":"","remarkName":"海大胖","wxSno":"b6cb0d083b77b4d0aaccdee78883a06e"}
     * api_key : 20180903
     * sign : 968B064B32258C7FF87580C1C470FD50
     * timestamp : 1539765170
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
         * email :
         * friendId : e0816099-d1d3-b20d-e114-fd56a192cc20
         * groupName : 家人
         * headImg : http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132
         * mobile : 18065283780
         * nickName : 海大胖
         * personaSignature :
         * remarkName : 海大胖
         * wxSno : b6cb0d083b77b4d0aaccdee78883a06e
         */

        private String email;
        private String friendId;
        private String groupName;
        private String headImg;
        private String mobile;
        private String nickName;
        private String personaSignature;
        private String remarkName;
        private String wxSno;

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        private String qrcode
                ;

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

        public String getRemarkName() {
            return remarkName;
        }

        public void setRemarkName(String remarkName) {
            this.remarkName = remarkName;
        }

        public String getWxSno() {
            return wxSno;
        }

        public void setWxSno(String wxSno) {
            this.wxSno = wxSno;
        }
    }
}
