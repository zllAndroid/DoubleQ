package com.doubleq.model;

public class DataMyZiliao {
    /**
     * code : 200
     * msg : 成功
     * method : personalCenter
     * record : {"headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/153829787075008.png","mobile":"18065283783","nickName":"android","personaSignature":"我是很个性的签名","qrcode":"http://picture.beybow.com/default/user_qrcod/usercode_384e84283becd1fc9adc3366d9e17cd0.png","upSnoNum":"1","wxSno":"b6cb0d083b77b4d0aac1249789080"}
     * api_key : 20180903
     * sign : A2E2F57800074C4B688D0EF3871AEC37
     * timestamp : 1538984929
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
         * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/logo/153829787075008.png
         * mobile : 18065283783
         * nickName : android
         * personaSignature : 我是很个性的签名
         * qrcode : http://picture.beybow.com/default/user_qrcod/usercode_384e84283becd1fc9adc3366d9e17cd0.png
         * upSnoNum : 1
         * wxSno : b6cb0d083b77b4d0aac1249789080
         */

        private String headImg;
        private String mobile;
        private String nickName;
        private String personaSignature;
        private String qrcode;
        private String upSnoNum;
        private String wxSno;

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

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public String getUpSnoNum() {
            return upSnoNum;
        }

        public void setUpSnoNum(String upSnoNum) {
            this.upSnoNum = upSnoNum;
        }

        public String getWxSno() {
            return wxSno;
        }

        public void setWxSno(String wxSno) {
            this.wxSno = wxSno;
        }
    }


    /**
     * method : personalCenter
     * code : 200
     * msg : 获取成功！
     * record : {"nick_name":"2222","user_id":"1","mobile":"15960525627","wx_sno":"sfs44444","head_img":"1","qrcode":"http://","persona_signature":"这世界真好啊"}
     */

//    private String method;
//    private int code;
//    private String msg;
//    private RecordBean record;
//
//    public String getMethod() {
//        return method;
//    }
//
//    public void setMethod(String method) {
//        this.method = method;
//    }
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public RecordBean getRecord() {
//        return record;
//    }
//
//    public void setRecord(RecordBean record) {
//        this.record = record;
//    }
//
//    public static class RecordBean {
//        /**
//         * nick_name : 2222
//         * user_id : 1
//         * mobile : 15960525627
//         * wx_sno : sfs44444
//         * head_img : 1
//         * qrcode : http://
//         * persona_signature : 这世界真好啊
//         */
//
//        private String nick_name;
//        private String user_id;
//        private String mobile;
//        private String wx_sno;
//        private String head_img;
//        private String qrcode;
//        private String persona_signature;
//        private String up_sno_num;
//
//        public String getUp_sno_num() {
//            return up_sno_num;
//        }
//
//        public void setUp_sno_num(String up_sno_num) {
//            this.up_sno_num = up_sno_num;
//        }
//
//        public String getNick_name() {
//            return nick_name;
//        }
//
//        public void setNick_name(String nick_name) {
//            this.nick_name = nick_name;
//        }
//
//        public String getUser_id() {
//            return user_id;
//        }
//
//        public void setUser_id(String user_id) {
//            this.user_id = user_id;
//        }
//
//        public String getMobile() {
//            return mobile;
//        }
//
//        public void setMobile(String mobile) {
//            this.mobile = mobile;
//        }
//
//        public String getWx_sno() {
//            return wx_sno;
//        }
//
//        public void setWx_sno(String wx_sno) {
//            this.wx_sno = wx_sno;
//        }
//
//        public String getHead_img() {
//            return head_img;
//        }
//
//        public void setHead_img(String head_img) {
//            this.head_img = head_img;
//        }
//
//        public String getQrcode() {
//            return qrcode;
//        }
//
//        public void setQrcode(String qrcode) {
//            this.qrcode = qrcode;
//        }
//
//        public String getPersona_signature() {
//            return persona_signature;
//        }
//
//        public void setPersona_signature(String persona_signature) {
//            this.persona_signature = persona_signature;
//        }
//    }
}
