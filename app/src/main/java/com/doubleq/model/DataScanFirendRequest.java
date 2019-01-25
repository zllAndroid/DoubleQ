package com.doubleq.model;

public class DataScanFirendRequest {
    /**
     * code : 200
     * msg : 成功
     * method : addFriendQrCode
     * record : {"email":"","friendId":"9a36-9ec1-412","groupId":"3","groupName":"同事","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png","isMobileShow":"1","isQrcodeShow":"1","isRelation":2,"isSnoShow":"1","mobile":"18065283783","nickName":"湖里","personaSignature":"唯度°","qrcode":"1_xm6leefun_9a36-9ec1-412","remarkName":"888","shieldType":"1","status":"1","wxSno":"44146cff4c640ac148bce839c929fac4"}
     * api_key : 20180903
     * sign : F11142752D19DB08DE6CCB2D9B69D733
     * timestamp : 1548310658
     * only : 1
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
         * email :
         * friendId : 9a36-9ec1-412
         * groupId : 3
         * groupName : 同事
         * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png
         * isMobileShow : 1
         * isQrcodeShow : 1
         * isRelation : 2
         * isSnoShow : 1
         * mobile : 18065283783
         * nickName : 湖里
         * personaSignature : 唯度°
         * qrcode : 1_xm6leefun_9a36-9ec1-412
         * remarkName : 888
         * shieldType : 1
         * status : 1
         * wxSno : 44146cff4c640ac148bce839c929fac4
         */

        private String email;
        private String friendId;
        private String groupId;
        private String groupName;
        private String headImg;
        private String isMobileShow;
        private String isQrcodeShow;
        private int isRelation;
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

        public int getIsRelation() {
            return isRelation;
        }

        public void setIsRelation(int isRelation) {
            this.isRelation = isRelation;
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


//    /**
//     * code : 200
//     * msg : 成功
//     * method : addFriendQrCode
//     * record : {"email":"","friendId":"9a36-9ec1-412","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png","isMobileShow":"1","isQrcodeShow":"1","isRelation":2,"isSnoShow":"1","mobile":"18065283783","nickName":"888","personaSignature":"","qrcode":"http://www.xm6leefun.cn:40005/default/img/099a65388eb7e84987228d795bee52b5.png","remarkName":"888","shieldType":"1","status":"1","groupId":"12","wxSno":"44146cff4c640ac148bce839c929fac4"}
//     * api_key : 20180903
//     * sign : BD2D0B06897B3EA8288E36D84116E68C
//     * timestamp : 1545736347
//     * only : 1
//     */
//
//    private int code;
//    private String msg;
//    private String method;
//    private RecordBean record;
//    private String api_key;
//    private String sign;
//    private int timestamp;
//    private int only;
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
//    public String getMethod() {
//        return method;
//    }
//
//    public void setMethod(String method) {
//        this.method = method;
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
//    public String getApi_key() {
//        return api_key;
//    }
//
//    public void setApi_key(String api_key) {
//        this.api_key = api_key;
//    }
//
//    public String getSign() {
//        return sign;
//    }
//
//    public void setSign(String sign) {
//        this.sign = sign;
//    }
//
//    public int getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(int timestamp) {
//        this.timestamp = timestamp;
//    }
//
//    public int getOnly() {
//        return only;
//    }
//
//    public void setOnly(int only) {
//        this.only = only;
//    }
//
//    public static class RecordBean {
//        /**
//         * email :
//         * friendId : 9a36-9ec1-412
//         * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png
//         * isMobileShow : 1
//         * isQrcodeShow : 1
//         * isRelation : 2
//         * isSnoShow : 1
//         * mobile : 18065283783
//         * nickName : 888
//         * personaSignature :
//         * qrcode : http://www.xm6leefun.cn:40005/default/img/099a65388eb7e84987228d795bee52b5.png
//         * remarkName : 888
//         * shieldType : 1
//         * status : 1
//         * groupId : 12
//         * wxSno : 44146cff4c640ac148bce839c929fac4
//         */
//
//        private String email;
//        private String friendId;
//        private String headImg;
//        private String isMobileShow;
//        private String isQrcodeShow;
//        private int isRelation;
//        private String isSnoShow;
//        private String mobile;
//        private String nickName;
//        private String personaSignature;
//        private String qrcode;
//        private String remarkName;
//        private String shieldType;
//        private String status;
//        private String groupId;
//        private String wxSno;
//
//        public String getEmail() {
//            return email;
//        }
//
//        public void setEmail(String email) {
//            this.email = email;
//        }
//
//        public String getFriendId() {
//            return friendId;
//        }
//
//        public void setFriendId(String friendId) {
//            this.friendId = friendId;
//        }
//
//        public String getHeadImg() {
//            return headImg;
//        }
//
//        public void setHeadImg(String headImg) {
//            this.headImg = headImg;
//        }
//
//        public String getIsMobileShow() {
//            return isMobileShow;
//        }
//
//        public void setIsMobileShow(String isMobileShow) {
//            this.isMobileShow = isMobileShow;
//        }
//
//        public String getIsQrcodeShow() {
//            return isQrcodeShow;
//        }
//
//        public void setIsQrcodeShow(String isQrcodeShow) {
//            this.isQrcodeShow = isQrcodeShow;
//        }
//
//        public int getIsRelation() {
//            return isRelation;
//        }
//
//        public void setIsRelation(int isRelation) {
//            this.isRelation = isRelation;
//        }
//
//        public String getIsSnoShow() {
//            return isSnoShow;
//        }
//
//        public void setIsSnoShow(String isSnoShow) {
//            this.isSnoShow = isSnoShow;
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
//        public String getNickName() {
//            return nickName;
//        }
//
//        public void setNickName(String nickName) {
//            this.nickName = nickName;
//        }
//
//        public String getPersonaSignature() {
//            return personaSignature;
//        }
//
//        public void setPersonaSignature(String personaSignature) {
//            this.personaSignature = personaSignature;
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
//        public String getRemarkName() {
//            return remarkName;
//        }
//
//        public void setRemarkName(String remarkName) {
//            this.remarkName = remarkName;
//        }
//
//        public String getShieldType() {
//            return shieldType;
//        }
//
//        public void setShieldType(String shieldType) {
//            this.shieldType = shieldType;
//        }
//
//        public String getStatus() {
//            return status;
//        }
//
//        public void setStatus(String status) {
//            this.status = status;
//        }
//
//        public String getGroupId() {
//            return groupId;
//        }
//
//        public void setGroupId(String groupId) {
//            this.groupId = groupId;
//        }
//
//        public String getWxSno() {
//            return wxSno;
//        }
//
//        public void setWxSno(String wxSno) {
//            this.wxSno = wxSno;
//        }
//}


//
//    /**
//     * code : 200
//     * msg : 成功
//     * method : addFriendQrCode
//     * record : {"friendId":"1434-b183-449","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154330938250066.png","mobile":"18150960007","nickName":"3333","personaSignature":"","wxSno":"2566a72ed38b0275d57a85f35ba4b297"}
//     * api_key : 20180903
//     * sign : 4E90E2C1B884DCDB6B361C677BF4AF65
//     * timestamp : 1543549009
//     */
//
//    private int code;
//    private String msg;
//    private String method;
//    private RecordBean record;
//    private String api_key;
//    private String sign;
//    private int timestamp;
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
//    public String getMethod() {
//        return method;
//    }
//
//    public void setMethod(String method) {
//        this.method = method;
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
//    public String getApi_key() {
//        return api_key;
//    }
//
//    public void setApi_key(String api_key) {
//        this.api_key = api_key;
//    }
//
//    public String getSign() {
//        return sign;
//    }
//
//    public void setSign(String sign) {
//        this.sign = sign;
//    }
//
//    public int getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(int timestamp) {
//        this.timestamp = timestamp;
//    }
//
//    public static class RecordBean {
//        /**
//         * friendId : 1434-b183-449
//         * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154330938250066.png
//         * mobile : 18150960007
//         * nickName : 3333
//         * personaSignature :
//         * wxSno : 2566a72ed38b0275d57a85f35ba4b297
//         */
//
//        private String friendId;
//        private String headImg;
//        private String mobile;
//        private String nickName;
//        private String personaSignature;
//        private String wxSno;
//
//        public String getFriendId() {
//            return friendId;
//        }
//
//        public void setFriendId(String friendId) {
//            this.friendId = friendId;
//        }
//
//        public String getHeadImg() {
//            return headImg;
//        }
//
//        public void setHeadImg(String headImg) {
//            this.headImg = headImg;
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
//        public String getNickName() {
//            return nickName;
//        }
//
//        public void setNickName(String nickName) {
//            this.nickName = nickName;
//        }
//
//        public String getPersonaSignature() {
//            return personaSignature;
//        }
//
//        public void setPersonaSignature(String personaSignature) {
//            this.personaSignature = personaSignature;
//        }
//
//        public String getWxSno() {
//            return wxSno;
//        }
//
//        public void setWxSno(String wxSno) {
//            this.wxSno = wxSno;
//        }
//    }
}
