package com.doubleq.model;

public class DataLogin {
    /**
     * code : 200
     * msg : 成功
     * method : loginIn
     * record : {"nickName":"匿名用户","userId":"b5c9f52b-a0fc-9709-9a02-5db1ad75c94a","mobile":"15960525627","wxSno":"c803ee98abb5155131df2ca9b884630a","userToken":"BC1CCC61-A0E9-FB0C-A86B-480197485060","isFirstLogin":2,"qrcode":"","personaSignature":""}
     */

    private int code;
    private String msg;
    private String method;
    private RecordBean record;

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

    public static class RecordBean {
        /**
         * nickName : 匿名用户
         * userId : b5c9f52b-a0fc-9709-9a02-5db1ad75c94a
         * mobile : 15960525627
         * wxSno : c803ee98abb5155131df2ca9b884630a
         * userToken : BC1CCC61-A0E9-FB0C-A86B-480197485060
         * isFirstLogin : 2
         * qrcode :
         * personaSignature :
         */

        private String nickName;
        private String userId;
        private String mobile;
        private String wxSno;
        private String userToken;
        private String isFirstLogin;
        private String qrcode;
        private String personaSignature;
        private String headImg;

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

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getWxSno() {
            return wxSno;
        }

        public void setWxSno(String wxSno) {
            this.wxSno = wxSno;
        }

        public String getUserToken() {
            return userToken;
        }

        public void setUserToken(String userToken) {
            this.userToken = userToken;
        }

        public String getIsFirstLogin() {
            return isFirstLogin;
        }

        public void setIsFirstLogin(String isFirstLogin) {
            this.isFirstLogin = isFirstLogin;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public String getPersonaSignature() {
            return personaSignature;
        }

        public void setPersonaSignature(String personaSignature) {
            this.personaSignature = personaSignature;
        }
    }


    /**
     * code : 200
     * msg : 登录成功
     * record : {"nick_name":"2222","user_id":"1","mobile":"15960525627","wx_sno":"sfs44444","user_token":"fdfsdf55555","is_first_login":1,"qrcode":"http://","persona_signature":"这世界真好啊"}
     */

//    private int code;
//    private String msg;
//    private RecordBean record;
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
//         * user_token : fdfsdf55555
//         * is_first_login : 1
//         * qrcode : http://
//         * persona_signature : 这世界真好啊
//         */
//
//        private String nick_name;
//        private String user_id;
//        private String mobile;
//        private String wx_sno;
//        private String user_token;
//        private String is_first_login;
//        private String qrcode;
//        private String persona_signature;
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
//        public String getUser_token() {
//            return user_token;
//        }
//
//        public void setUser_token(String user_token) {
//            this.user_token = user_token;
//        }
//
//        public String getIs_first_login() {
//            return is_first_login;
//        }
//
//        public void setIs_first_login(String is_first_login) {
//            this.is_first_login = is_first_login;
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


//    /**
//     * code : 200
//     * msg : 登录成功
//     * record : {"nick_name":"2222","user_id":"1","mobile":"15960525627","is_first_login":1}
//     */
//
//    private int code;
//    private String msg;
//    private RecordBean record;
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
//         * is_first_login : 1
//         */
//
//        private String nick_name;
//        private String user_id;
//        private String mobile;
//        private int is_first_login;
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
//        public int getIs_first_login() {
//            return is_first_login;
//        }
//
//        public void setIs_first_login(int is_first_login) {
//            this.is_first_login = is_first_login;
//        }
//    }
}
