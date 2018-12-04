package com.doubleq.model;

public class DataNoticeDetails {


    /**
     * code : 200
     * msg : 成功
     * method : messageDetail
     * record : {"userDetailInfo":{"qrcode":"","headImg":"http: //thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w132","nickName":"海大胖","personaSignature":"","remark":"我是你朋友"}}
     * api_key : 20180903
     * sign : E9DED6E84AD9071D78F68C538B9F35E5
     * timestamp : 1539076180
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
         * userDetailInfo : {"qrcode":"","headImg":"http: //thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w132","nickName":"海大胖","personaSignature":"","remark":"我是你朋友"}
         */

        private UserDetailInfoBean userDetailInfo;

        public UserDetailInfoBean getUserDetailInfo() {
            return userDetailInfo;
        }

        public void setUserDetailInfo(UserDetailInfoBean userDetailInfo) {
            this.userDetailInfo = userDetailInfo;
        }

        public static class UserDetailInfoBean {
            /**
             * qrcode :
             * headImg : http: //thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w132
             * nickName : 海大胖
             * personaSignature :
             * remark : 我是你朋友
             */

            private String qrcode;
            private String headImg;
            private String nickName;
            private String personaSignature;
            private String remark;

            public String getQrcode() {
                return qrcode;
            }

            public void setQrcode(String qrcode) {
                this.qrcode = qrcode;
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

            public String getPersonaSignature() {
                return personaSignature;
            }

            public void setPersonaSignature(String personaSignature) {
                this.personaSignature = personaSignature;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }
    }
}
