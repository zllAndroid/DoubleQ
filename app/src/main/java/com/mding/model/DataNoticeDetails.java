package com.mding.model;

import java.util.List;
public class DataNoticeDetails {
    /**
     * code : 200
     * msg : 成功
     * method : messageDetail
     * record : {"userDetailInfo":{"qrcode":"1_xm6leefun_3dfc-dcb0-da3","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/head_img/154762091351654.png","nickName":"大小姐miss","personaSignature":"快乐每一天","wxSno":"232131","remark":[{"userId":"1b42-b9c2-c6f","friendsId":"3dfc-dcb0-da3","nickName":"赞赞赞赞赞赞赞赞赞赞","message":"1233"}]}}
     * api_key : 20180903
     * sign : 5BD79463FEF0F0CCD0BEC23EA8C384E9
     * timestamp : 1548951598
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
         * userDetailInfo : {"qrcode":"1_xm6leefun_3dfc-dcb0-da3","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/head_img/154762091351654.png","nickName":"大小姐miss","personaSignature":"快乐每一天","wxSno":"232131","remark":[{"userId":"1b42-b9c2-c6f","friendsId":"3dfc-dcb0-da3","nickName":"赞赞赞赞赞赞赞赞赞赞","message":"1233"}]}
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
             * qrcode : 1_xm6leefun_3dfc-dcb0-da3
             * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/head_img/154762091351654.png
             * nickName : 大小姐miss
             * personaSignature : 快乐每一天
             * wxSno : 232131
             * remark : [{"userId":"1b42-b9c2-c6f","friendsId":"3dfc-dcb0-da3","nickName":"赞赞赞赞赞赞赞赞赞赞","message":"1233"}]
             */

            private String qrcode;
            private String headImg;
            private String nickName;
            private String personaSignature;
            private String wxSno;
            private List<RemarkBean> remark;

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

            public String getWxSno() {
                return wxSno;
            }

            public void setWxSno(String wxSno) {
                this.wxSno = wxSno;
            }

            public List<RemarkBean> getRemark() {
                return remark;
            }

            public void setRemark(List<RemarkBean> remark) {
                this.remark = remark;
            }

            public static class RemarkBean {
                /**
                 * userId : 1b42-b9c2-c6f
                 * friendsId : 3dfc-dcb0-da3
                 * nickName : 赞赞赞赞赞赞赞赞赞赞
                 * message : 1233
                 */

                private String userId;
                private String friendsId;
                private String nickName;
                private String message;

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getFriendsId() {
                    return friendsId;
                }

                public void setFriendsId(String friendsId) {
                    this.friendsId = friendsId;
                }

                public String getNickName() {
                    return nickName;
                }

                public void setNickName(String nickName) {
                    this.nickName = nickName;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }
            }
        }
    }


//    /**
//     * code : 200
//     * msg : 成功
//     * method : messageDetail
//     * record : {"userDetailInfo":{"qrcode":"1_xm6leefun_3dfc-dcb0-da3","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/head_img/154762091351654.png","nickName":"大小姐miss","personaSignature":"快乐每一天","remark":[{"userId":"1b42-b9c2-c6f","friendsId":"3dfc-dcb0-da3","nickName":"赞赞赞赞赞赞赞赞赞赞","message":"1233"}]}}
//     * api_key : 20180903
//     * sign : 5BD79463FEF0F0CCD0BEC23EA8C384E9
//     * timestamp : 1548951598
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
//         * userDetailInfo : {"qrcode":"1_xm6leefun_3dfc-dcb0-da3","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/head_img/154762091351654.png","nickName":"大小姐miss","personaSignature":"快乐每一天","remark":[{"userId":"1b42-b9c2-c6f","friendsId":"3dfc-dcb0-da3","nickName":"赞赞赞赞赞赞赞赞赞赞","message":"1233"}]}
//         */
//
//        private UserDetailInfoBean userDetailInfo;
//
//        public UserDetailInfoBean getUserDetailInfo() {
//            return userDetailInfo;
//        }
//
//        public void setUserDetailInfo(UserDetailInfoBean userDetailInfo) {
//            this.userDetailInfo = userDetailInfo;
//        }
//
//        public static class UserDetailInfoBean {
//            /**
//             * qrcode : 1_xm6leefun_3dfc-dcb0-da3
//             * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/head_img/154762091351654.png
//             * nickName : 大小姐miss
//             * personaSignature : 快乐每一天
//             * remark : [{"userId":"1b42-b9c2-c6f","friendsId":"3dfc-dcb0-da3","nickName":"赞赞赞赞赞赞赞赞赞赞","message":"1233"}]
//             */
//
//            private String qrcode;
//            private String headImg;
//            private String nickName;
//            private String personaSignature;
//            private List<RemarkBean> remark;
//
//            public String getQrcode() {
//                return qrcode;
//            }
//
//            public void setQrcode(String qrcode) {
//                this.qrcode = qrcode;
//            }
//
//            public String getHeadImg() {
//                return headImg;
//            }
//
//            public void setHeadImg(String headImg) {
//                this.headImg = headImg;
//            }
//
//            public String getNickName() {
//                return nickName;
//            }
//
//            public void setNickName(String nickName) {
//                this.nickName = nickName;
//            }
//
//            public String getPersonaSignature() {
//                return personaSignature;
//            }
//
//            public void setPersonaSignature(String personaSignature) {
//                this.personaSignature = personaSignature;
//            }
//
//            public List<RemarkBean> getRemark() {
//                return remark;
//            }
//
//            public void setRemark(List<RemarkBean> remark) {
//                this.remark = remark;
//            }
//
//            public static class RemarkBean {
//                /**
//                 * userId : 1b42-b9c2-c6f
//                 * friendsId : 3dfc-dcb0-da3
//                 * nickName : 赞赞赞赞赞赞赞赞赞赞
//                 * message : 1233
//                 */
//
//                private String userId;
//                private String friendsId;
//                private String nickName;
//                private String message;
//
//                public String getUserId() {
//                    return userId;
//                }
//
//                public void setUserId(String userId) {
//                    this.userId = userId;
//                }
//
//                public String getFriendsId() {
//                    return friendsId;
//                }
//
//                public void setFriendsId(String friendsId) {
//                    this.friendsId = friendsId;
//                }
//
//                public String getNickName() {
//                    return nickName;
//                }
//
//                public void setNickName(String nickName) {
//                    this.nickName = nickName;
//                }
//
//                public String getMessage() {
//                    return message;
//                }
//
//                public void setMessage(String message) {
//                    this.message = message;
//                }
//            }
//        }
//    }

//
//
//    /**
//     * code : 200
//     * msg : 成功
//     * method : messageDetail
//     * record : {"userDetailInfo":{"qrcode":"","headImg":"http: //thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w132","nickName":"海大胖","personaSignature":"","remark":"我是你朋友"}}
//     * api_key : 20180903
//     * sign : E9DED6E84AD9071D78F68C538B9F35E5
//     * timestamp : 1539076180
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
//         * userDetailInfo : {"qrcode":"","headImg":"http: //thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w132","nickName":"海大胖","personaSignature":"","remark":"我是你朋友"}
//         */
//
//        private UserDetailInfoBean userDetailInfo;
//
//        public UserDetailInfoBean getUserDetailInfo() {
//            return userDetailInfo;
//        }
//
//        public void setUserDetailInfo(UserDetailInfoBean userDetailInfo) {
//            this.userDetailInfo = userDetailInfo;
//        }
//
//        public static class UserDetailInfoBean {
//            /**
//             * qrcode :
//             * headImg : http: //thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w132
//             * nickName : 海大胖
//             * personaSignature :
//             * remark : 我是你朋友
//             */
//
//            private String qrcode;
//            private String headImg;
//            private String nickName;
//            private String personaSignature;
//            private String remark;
//
//            public String getQrcode() {
//                return qrcode;
//            }
//
//            public void setQrcode(String qrcode) {
//                this.qrcode = qrcode;
//            }
//
//            public String getHeadImg() {
//                return headImg;
//            }
//
//            public void setHeadImg(String headImg) {
//                this.headImg = headImg;
//            }
//
//            public String getNickName() {
//                return nickName;
//            }
//
//            public void setNickName(String nickName) {
//                this.nickName = nickName;
//            }
//
//            public String getPersonaSignature() {
//                return personaSignature;
//            }
//
//            public void setPersonaSignature(String personaSignature) {
//                this.personaSignature = personaSignature;
//            }
//
//            public String getRemark() {
//                return remark;
//            }
//
//            public void setRemark(String remark) {
//                this.remark = remark;
//            }
//        }
//    }
}
