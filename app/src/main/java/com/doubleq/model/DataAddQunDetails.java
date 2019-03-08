package com.doubleq.model;

import java.util.List;

public class DataAddQunDetails {
    /**
     * code : 200
     * msg : 成功
     * method : searchDetailInfo
     * record : {"groupDetailInfo":{"groupInfo":{"id":"107","groupName":"大吉大利","groupSno":"acd38da175d64ab6bf940e122","groupHeadImg":"http://192.168.4.131:40005/default/img/default_group.png","groupQrcode":"2_xm6leefun_acd38da175d64ab6bf940e122"},"groupUserInfo":[{"userId":"9a36-9ec1-412","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png","nickName":"湖里","personaSignature":"唯度°","qrcode":"1_xm6leefun_9a36-9ec1-412","modified":"1547792019","carteName":"","isRelation":2},{"userId":"d81a-0865-70d","headImg":"http://www.xm6leefun.cn:40005/default/img/default_head.png","nickName":"匿名用户","personaSignature":"","qrcode":"1_xm6leefun_d81a-0865-70d","modified":"0","carteName":"","isRelation":2},{"userId":"f9fa-f5dd-ab2","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/head_img/154839658814530.png","nickName":"我是大美人er","personaSignature":"幸福快乐每一天，真好呀！","qrcode":"1_xm6leefun_f9fa-f5dd-ab2","modified":"1548396588","carteName":"","isRelation":3},{"userId":"1b42-b9c2-c6f","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502973436653.png","nickName":"赞赞赞赞赞赞赞赞赞赞","personaSignature":"我是小鸭子","qrcode":"1_xm6leefun_1b42-b9c2-c6f","modified":"1547567065","carteName":"","isRelation":2}],"groupNotice":{"id":"","noticeContent":""},"userInfo":{"userId":"f9fa-f5dd-ab2","carteName":"","identityType":"3","disturbType":"1","assistantType":"1","groupManageId":"41","groupManageName":"6leefun"},"isRelation":2},"verificationMD5":""}
     * api_key : 20180903
     * sign : 2C0AF94EADDC7E3717FF9F13D2213E49
     * timestamp : 1551859411
     * only : 2
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
         * groupDetailInfo : {"groupInfo":{"id":"107","groupName":"大吉大利","groupSno":"acd38da175d64ab6bf940e122","groupHeadImg":"http://192.168.4.131:40005/default/img/default_group.png","groupQrcode":"2_xm6leefun_acd38da175d64ab6bf940e122"},"groupUserInfo":[{"userId":"9a36-9ec1-412","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png","nickName":"湖里","personaSignature":"唯度°","qrcode":"1_xm6leefun_9a36-9ec1-412","modified":"1547792019","carteName":"","isRelation":2},{"userId":"d81a-0865-70d","headImg":"http://www.xm6leefun.cn:40005/default/img/default_head.png","nickName":"匿名用户","personaSignature":"","qrcode":"1_xm6leefun_d81a-0865-70d","modified":"0","carteName":"","isRelation":2},{"userId":"f9fa-f5dd-ab2","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/head_img/154839658814530.png","nickName":"我是大美人er","personaSignature":"幸福快乐每一天，真好呀！","qrcode":"1_xm6leefun_f9fa-f5dd-ab2","modified":"1548396588","carteName":"","isRelation":3},{"userId":"1b42-b9c2-c6f","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502973436653.png","nickName":"赞赞赞赞赞赞赞赞赞赞","personaSignature":"我是小鸭子","qrcode":"1_xm6leefun_1b42-b9c2-c6f","modified":"1547567065","carteName":"","isRelation":2}],"groupNotice":{"id":"","noticeContent":""},"userInfo":{"userId":"f9fa-f5dd-ab2","carteName":"","identityType":"3","disturbType":"1","assistantType":"1","groupManageId":"41","groupManageName":"6leefun"},"isRelation":2}
         * verificationMD5 :
         */

        private GroupDetailInfoBean groupDetailInfo;
        private String verificationMD5;

        public GroupDetailInfoBean getGroupDetailInfo() {
            return groupDetailInfo;
        }

        public void setGroupDetailInfo(GroupDetailInfoBean groupDetailInfo) {
            this.groupDetailInfo = groupDetailInfo;
        }

        public String getVerificationMD5() {
            return verificationMD5;
        }

        public void setVerificationMD5(String verificationMD5) {
            this.verificationMD5 = verificationMD5;
        }

        public static class GroupDetailInfoBean {
            /**
             * groupInfo : {"id":"107","groupName":"大吉大利","groupSno":"acd38da175d64ab6bf940e122","groupHeadImg":"http://192.168.4.131:40005/default/img/default_group.png","groupQrcode":"2_xm6leefun_acd38da175d64ab6bf940e122"}
             * groupUserInfo : [{"userId":"9a36-9ec1-412","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png","nickName":"湖里","personaSignature":"唯度°","qrcode":"1_xm6leefun_9a36-9ec1-412","modified":"1547792019","carteName":"","isRelation":2},{"userId":"d81a-0865-70d","headImg":"http://www.xm6leefun.cn:40005/default/img/default_head.png","nickName":"匿名用户","personaSignature":"","qrcode":"1_xm6leefun_d81a-0865-70d","modified":"0","carteName":"","isRelation":2},{"userId":"f9fa-f5dd-ab2","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/head_img/154839658814530.png","nickName":"我是大美人er","personaSignature":"幸福快乐每一天，真好呀！","qrcode":"1_xm6leefun_f9fa-f5dd-ab2","modified":"1548396588","carteName":"","isRelation":3},{"userId":"1b42-b9c2-c6f","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502973436653.png","nickName":"赞赞赞赞赞赞赞赞赞赞","personaSignature":"我是小鸭子","qrcode":"1_xm6leefun_1b42-b9c2-c6f","modified":"1547567065","carteName":"","isRelation":2}]
             * groupNotice : {"id":"","noticeContent":""}
             * userInfo : {"userId":"f9fa-f5dd-ab2","carteName":"","identityType":"3","disturbType":"1","assistantType":"1","groupManageId":"41","groupManageName":"6leefun"}
             * isRelation : 2
             */

            private GroupInfoBean groupInfo;
            private GroupNoticeBean groupNotice;
            private UserInfoBean userInfo;
            private int isRelation;
            private List<GroupUserInfoBean> groupUserInfo;

            public GroupInfoBean getGroupInfo() {
                return groupInfo;
            }

            public void setGroupInfo(GroupInfoBean groupInfo) {
                this.groupInfo = groupInfo;
            }

            public GroupNoticeBean getGroupNotice() {
                return groupNotice;
            }

            public void setGroupNotice(GroupNoticeBean groupNotice) {
                this.groupNotice = groupNotice;
            }

            public UserInfoBean getUserInfo() {
                return userInfo;
            }

            public void setUserInfo(UserInfoBean userInfo) {
                this.userInfo = userInfo;
            }

            public int getIsRelation() {
                return isRelation;
            }

            public void setIsRelation(int isRelation) {
                this.isRelation = isRelation;
            }

            public List<GroupUserInfoBean> getGroupUserInfo() {
                return groupUserInfo;
            }

            public void setGroupUserInfo(List<GroupUserInfoBean> groupUserInfo) {
                this.groupUserInfo = groupUserInfo;
            }

            public static class GroupInfoBean {
                /**
                 * id : 107
                 * groupName : 大吉大利
                 * groupSno : acd38da175d64ab6bf940e122
                 * groupHeadImg : http://192.168.4.131:40005/default/img/default_group.png
                 * groupQrcode : 2_xm6leefun_acd38da175d64ab6bf940e122
                 */

                private String id;
                private String groupName;
                private String groupSno;
                private String groupHeadImg;
                private String groupQrcode;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getGroupName() {
                    return groupName;
                }

                public void setGroupName(String groupName) {
                    this.groupName = groupName;
                }

                public String getGroupSno() {
                    return groupSno;
                }

                public void setGroupSno(String groupSno) {
                    this.groupSno = groupSno;
                }

                public String getGroupHeadImg() {
                    return groupHeadImg;
                }

                public void setGroupHeadImg(String groupHeadImg) {
                    this.groupHeadImg = groupHeadImg;
                }

                public String getGroupQrcode() {
                    return groupQrcode;
                }

                public void setGroupQrcode(String groupQrcode) {
                    this.groupQrcode = groupQrcode;
                }
            }

            public static class GroupNoticeBean {
                /**
                 * id :
                 * noticeContent :
                 */

                private String id;
                private String noticeContent;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getNoticeContent() {
                    return noticeContent;
                }

                public void setNoticeContent(String noticeContent) {
                    this.noticeContent = noticeContent;
                }
            }

            public static class UserInfoBean {
                /**
                 * userId : f9fa-f5dd-ab2
                 * carteName :
                 * identityType : 3
                 * disturbType : 1
                 * assistantType : 1
                 * groupManageId : 41
                 * groupManageName : 6leefun
                 */

                private String userId;
                private String carteName;
                private String identityType;
                private String disturbType;
                private String assistantType;
                private String groupManageId;
                private String groupManageName;

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getCarteName() {
                    return carteName;
                }

                public void setCarteName(String carteName) {
                    this.carteName = carteName;
                }

                public String getIdentityType() {
                    return identityType;
                }

                public void setIdentityType(String identityType) {
                    this.identityType = identityType;
                }

                public String getDisturbType() {
                    return disturbType;
                }

                public void setDisturbType(String disturbType) {
                    this.disturbType = disturbType;
                }

                public String getAssistantType() {
                    return assistantType;
                }

                public void setAssistantType(String assistantType) {
                    this.assistantType = assistantType;
                }

                public String getGroupManageId() {
                    return groupManageId;
                }

                public void setGroupManageId(String groupManageId) {
                    this.groupManageId = groupManageId;
                }

                public String getGroupManageName() {
                    return groupManageName;
                }

                public void setGroupManageName(String groupManageName) {
                    this.groupManageName = groupManageName;
                }
            }

            public static class GroupUserInfoBean {
                /**
                 * userId : 9a36-9ec1-412
                 * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png
                 * nickName : 湖里
                 * personaSignature : 唯度°
                 * qrcode : 1_xm6leefun_9a36-9ec1-412
                 * modified : 1547792019
                 * carteName :
                 * isRelation : 2
                 */

                private String userId;
                private String headImg;
                private String nickName;
                private String personaSignature;
                private String qrcode;
                private String modified;
                private String carteName;
                private String isRelation;

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
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

                public String getQrcode() {
                    return qrcode;
                }

                public void setQrcode(String qrcode) {
                    this.qrcode = qrcode;
                }

                public String getModified() {
                    return modified;
                }

                public void setModified(String modified) {
                    this.modified = modified;
                }

                public String getCarteName() {
                    return carteName;
                }

                public void setCarteName(String carteName) {
                    this.carteName = carteName;
                }

                public String getIsRelation() {
                    return isRelation;
                }

                public void setIsRelation(String isRelation) {
                    this.isRelation = isRelation;
                }
            }
        }
    }


//    /**
//     * code : 200
//     * msg : 成功
//     * method : searchDetailInfo
//     * record : {"groupDetailInfo":{"groupInfo":{"id":"1","groupName":"咪咪酱","groupSno":"21d14cfbc38dc46373f22fcf1e170dcf","groupHeadImg":"http://192.168.4.131:40005/default/img/default_group.png","groupQrcode":"http://www.xm6leefun.cn:40005/default/img/079465c75f9663b04507172d3bbb9678.png"},"groupUserInfo":[{"userId":"08ca-e24c-7ac","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154399442839104.png","nickName":"密密酱","personaSignature":"走自己的路","qrcode":"http://192.168.4.131:40005/default/img/7111b6b89794ac8353b5d757eb296ebe.png","carteName":"","isRelation":1},{"userId":"3729-6df1-68a","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154329746816480.png","nickName":"indy","personaSignature":"","qrcode":"http://192.168.4.131:40005/default/img/4a2f50e1e66abc3836aeaed3e24176e8.png","carteName":"","isRelation":1},{"userId":"8f6b-a9b2-8e2","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154417305724798.png","nickName":"骆驼","personaSignature":"我是从北墙开着骆驼到南墙的骆驼","qrcode":"http://192.168.4.131:40005/default/img/ab2fcc273d206f5e236a3da0f349e6bf.png","carteName":"","isRelation":2},{"userId":"b18e-74bf-8e8","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154328711748669.png","nickName":"昆&仑#","personaSignature":"wu@杰","qrcode":"http://192.168.4.131:40005/default/img/aa64361695febc17fb344944a0c4d4dc.png","carteName":"","isRelation":3}],"groupNotice":{"id":"1","noticeContent":"通知通知"},"userInfo":{"userId":"b18e-74bf-8e8","carteName":"","identityType":"3","disturbType":"1","assistantType":"1"},"isRelation":2},"verificationMD5":""}
//     * api_key : 20180903
//     * sign : 8C572F32FAACEBA1DA29F462DAA2104B
//     * timestamp : 1545901489
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
//         * groupDetailInfo : {"groupInfo":{"id":"1","groupName":"咪咪酱","groupSno":"21d14cfbc38dc46373f22fcf1e170dcf","groupHeadImg":"http://192.168.4.131:40005/default/img/default_group.png","groupQrcode":"http://www.xm6leefun.cn:40005/default/img/079465c75f9663b04507172d3bbb9678.png"},"groupUserInfo":[{"userId":"08ca-e24c-7ac","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154399442839104.png","nickName":"密密酱","personaSignature":"走自己的路","qrcode":"http://192.168.4.131:40005/default/img/7111b6b89794ac8353b5d757eb296ebe.png","carteName":"","isRelation":1},{"userId":"3729-6df1-68a","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154329746816480.png","nickName":"indy","personaSignature":"","qrcode":"http://192.168.4.131:40005/default/img/4a2f50e1e66abc3836aeaed3e24176e8.png","carteName":"","isRelation":1},{"userId":"8f6b-a9b2-8e2","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154417305724798.png","nickName":"骆驼","personaSignature":"我是从北墙开着骆驼到南墙的骆驼","qrcode":"http://192.168.4.131:40005/default/img/ab2fcc273d206f5e236a3da0f349e6bf.png","carteName":"","isRelation":2},{"userId":"b18e-74bf-8e8","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154328711748669.png","nickName":"昆&仑#","personaSignature":"wu@杰","qrcode":"http://192.168.4.131:40005/default/img/aa64361695febc17fb344944a0c4d4dc.png","carteName":"","isRelation":3}],"groupNotice":{"id":"1","noticeContent":"通知通知"},"userInfo":{"userId":"b18e-74bf-8e8","carteName":"","identityType":"3","disturbType":"1","assistantType":"1"},"isRelation":2}
//         * verificationMD5 :
//         */
//
//        private GroupDetailInfoBean groupDetailInfo;
//        private String verificationMD5;
//
//        public GroupDetailInfoBean getGroupDetailInfo() {
//            return groupDetailInfo;
//        }
//
//        public void setGroupDetailInfo(GroupDetailInfoBean groupDetailInfo) {
//            this.groupDetailInfo = groupDetailInfo;
//        }
//
//        public String getVerificationMD5() {
//            return verificationMD5;
//        }
//
//        public void setVerificationMD5(String verificationMD5) {
//            this.verificationMD5 = verificationMD5;
//        }
//
//        public static class GroupDetailInfoBean {
//            /**
//             * groupInfo : {"id":"1","groupName":"咪咪酱","groupSno":"21d14cfbc38dc46373f22fcf1e170dcf","groupHeadImg":"http://192.168.4.131:40005/default/img/default_group.png","groupQrcode":"http://www.xm6leefun.cn:40005/default/img/079465c75f9663b04507172d3bbb9678.png"}
//             * groupUserInfo : [{"userId":"08ca-e24c-7ac","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154399442839104.png","nickName":"密密酱","personaSignature":"走自己的路","qrcode":"http://192.168.4.131:40005/default/img/7111b6b89794ac8353b5d757eb296ebe.png","carteName":"","isRelation":1},{"userId":"3729-6df1-68a","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154329746816480.png","nickName":"indy","personaSignature":"","qrcode":"http://192.168.4.131:40005/default/img/4a2f50e1e66abc3836aeaed3e24176e8.png","carteName":"","isRelation":1},{"userId":"8f6b-a9b2-8e2","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154417305724798.png","nickName":"骆驼","personaSignature":"我是从北墙开着骆驼到南墙的骆驼","qrcode":"http://192.168.4.131:40005/default/img/ab2fcc273d206f5e236a3da0f349e6bf.png","carteName":"","isRelation":2},{"userId":"b18e-74bf-8e8","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154328711748669.png","nickName":"昆&仑#","personaSignature":"wu@杰","qrcode":"http://192.168.4.131:40005/default/img/aa64361695febc17fb344944a0c4d4dc.png","carteName":"","isRelation":3}]
//             * groupNotice : {"id":"1","noticeContent":"通知通知"}
//             * userInfo : {"userId":"b18e-74bf-8e8","carteName":"","identityType":"3","disturbType":"1","assistantType":"1"}
//             * isRelation : 2
//             */
//
//            private GroupInfoBean groupInfo;
//            private GroupNoticeBean groupNotice;
//            private UserInfoBean userInfo;
//            private int isRelation;
//            private List<GroupUserInfoBean> groupUserInfo;
//
//            public GroupInfoBean getGroupInfo() {
//                return groupInfo;
//            }
//
//            public void setGroupInfo(GroupInfoBean groupInfo) {
//                this.groupInfo = groupInfo;
//            }
//
//            public GroupNoticeBean getGroupNotice() {
//                return groupNotice;
//            }
//
//            public void setGroupNotice(GroupNoticeBean groupNotice) {
//                this.groupNotice = groupNotice;
//            }
//
//            public UserInfoBean getUserInfo() {
//                return userInfo;
//            }
//
//            public void setUserInfo(UserInfoBean userInfo) {
//                this.userInfo = userInfo;
//            }
//
//            public int getIsRelation() {
//                return isRelation;
//            }
//
//            public void setIsRelation(int isRelation) {
//                this.isRelation = isRelation;
//            }
//
//            public List<GroupUserInfoBean> getGroupUserInfo() {
//                return groupUserInfo;
//            }
//
//            public void setGroupUserInfo(List<GroupUserInfoBean> groupUserInfo) {
//                this.groupUserInfo = groupUserInfo;
//            }
//
//            public static class GroupInfoBean {
//                /**
//                 * id : 1
//                 * groupName : 咪咪酱
//                 * groupSno : 21d14cfbc38dc46373f22fcf1e170dcf
//                 * groupHeadImg : http://192.168.4.131:40005/default/img/default_group.png
//                 * groupQrcode : http://www.xm6leefun.cn:40005/default/img/079465c75f9663b04507172d3bbb9678.png
//                 */
//
//                private String id;
//                private String groupName;
//                private String groupSno;
//                private String groupHeadImg;
//                private String groupQrcode;
//
//                public String getId() {
//                    return id;
//                }
//
//                public void setId(String id) {
//                    this.id = id;
//                }
//
//                public String getGroupName() {
//                    return groupName;
//                }
//
//                public void setGroupName(String groupName) {
//                    this.groupName = groupName;
//                }
//
//                public String getGroupSno() {
//                    return groupSno;
//                }
//
//                public void setGroupSno(String groupSno) {
//                    this.groupSno = groupSno;
//                }
//
//                public String getGroupHeadImg() {
//                    return groupHeadImg;
//                }
//
//                public void setGroupHeadImg(String groupHeadImg) {
//                    this.groupHeadImg = groupHeadImg;
//                }
//
//                public String getGroupQrcode() {
//                    return groupQrcode;
//                }
//
//                public void setGroupQrcode(String groupQrcode) {
//                    this.groupQrcode = groupQrcode;
//                }
//            }
//
//            public static class GroupNoticeBean {
//                /**
//                 * id : 1
//                 * noticeContent : 通知通知
//                 */
//
//                private String id;
//                private String noticeContent;
//
//                public String getId() {
//                    return id;
//                }
//
//                public void setId(String id) {
//                    this.id = id;
//                }
//
//                public String getNoticeContent() {
//                    return noticeContent;
//                }
//
//                public void setNoticeContent(String noticeContent) {
//                    this.noticeContent = noticeContent;
//                }
//            }
//
//            public static class UserInfoBean {
//                /**
//                 * userId : b18e-74bf-8e8
//                 * carteName :
//                 * identityType : 3
//                 * disturbType : 1
//                 * assistantType : 1
//                 */
//
//                private String userId;
//                private String carteName;
//                private String identityType;
//                private String disturbType;
//                private String assistantType;
//
//                public String getUserId() {
//                    return userId;
//                }
//
//                public void setUserId(String userId) {
//                    this.userId = userId;
//                }
//
//                public String getCarteName() {
//                    return carteName;
//                }
//
//                public void setCarteName(String carteName) {
//                    this.carteName = carteName;
//                }
//
//                public String getIdentityType() {
//                    return identityType;
//                }
//
//                public void setIdentityType(String identityType) {
//                    this.identityType = identityType;
//                }
//
//                public String getDisturbType() {
//                    return disturbType;
//                }
//
//                public void setDisturbType(String disturbType) {
//                    this.disturbType = disturbType;
//                }
//
//                public String getAssistantType() {
//                    return assistantType;
//                }
//
//                public void setAssistantType(String assistantType) {
//                    this.assistantType = assistantType;
//                }
//            }
//
//            public static class GroupUserInfoBean {
//                /**
//                 * userId : 08ca-e24c-7ac
//                 * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154399442839104.png
//                 * nickName : 密密酱
//                 * personaSignature : 走自己的路
//                 * qrcode : http://192.168.4.131:40005/default/img/7111b6b89794ac8353b5d757eb296ebe.png
//                 * carteName :
//                 * isRelation : 1
//                 */
//
//                private String userId;
//                private String headImg;
//                private String nickName;
//                private String personaSignature;
//                private String qrcode;
//                private String carteName;
//                private String isRelation;
//                private String modified;
//
//                public String getModified() {
//                    return modified;
//                }
//
//                public void setModified(String modified) {
//                    this.modified = modified;
//                }
//
//                public String getUserId() {
//                    return userId;
//                }
//
//                public void setUserId(String userId) {
//                    this.userId = userId;
//                }
//
//                public String getHeadImg() {
//                    return headImg;
//                }
//
//                public void setHeadImg(String headImg) {
//                    this.headImg = headImg;
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
//                public String getPersonaSignature() {
//                    return personaSignature;
//                }
//
//                public void setPersonaSignature(String personaSignature) {
//                    this.personaSignature = personaSignature;
//                }
//
//                public String getQrcode() {
//                    return qrcode;
//                }
//
//                public void setQrcode(String qrcode) {
//                    this.qrcode = qrcode;
//                }
//
//                public String getCarteName() {
//                    return carteName;
//                }
//
//                public void setCarteName(String carteName) {
//                    this.carteName = carteName;
//                }
//
//                public String  getIsRelation() {
//                    return isRelation;
//                }
//
//                public void setIsRelation(String isRelation) {
//                    this.isRelation = isRelation;
//                }
//            }
//        }
//    }


//    /**
//     * code : 200
//     * msg : 成功
//     * method : searchDetailInfo
//     * record : {"groupDetailInfo":{"groupInfo":[{"id":"1","groupName":"咪咪酱","groupSno":"21d14cfbc38dc46373f22fcf1e170dcf","groupHeadImg":"http://192.168.4.131:40005/default/img/default_group.png","groupQrcode":"http://www.xm6leefun.cn:40005/default/img/079465c75f9663b04507172d3bbb9678.png"}],"groupUserInfo":[{"userId":"08ca-e24c-7ac","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154399442839104.png","nickName":"密密酱","personaSignature":"走自己的路","qrcode":"http://192.168.4.131:40005/default/img/7111b6b89794ac8353b5d757eb296ebe.png","carteName":"","isRelation":1},{"userId":"3729-6df1-68a","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154329746816480.png","nickName":"indy","personaSignature":"","qrcode":"http://192.168.4.131:40005/default/img/4a2f50e1e66abc3836aeaed3e24176e8.png","carteName":"","isRelation":1},{"userId":"8f6b-a9b2-8e2","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154417305724798.png","nickName":"骆驼","personaSignature":"我是从北墙开着骆驼到南墙的骆驼","qrcode":"http://192.168.4.131:40005/default/img/ab2fcc273d206f5e236a3da0f349e6bf.png","carteName":"","isRelation":2},{"userId":"b18e-74bf-8e8","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154328711748669.png","nickName":"昆&仑#","personaSignature":"wu@杰","qrcode":"http://192.168.4.131:40005/default/img/aa64361695febc17fb344944a0c4d4dc.png","carteName":"","isRelation":3}],"groupNotice":[{"id":"1","noticeContent":"通知通知"}],"userInfo":{"userId":"b18e-74bf-8e8","carteName":"","identityType":"3","disturbType":"1","assistantType":"1"},"isRelation":2},"verificationMD5":""}
//     * api_key : 20180903
//     * sign : 13AA95D3150A60AD21447B64BDE41BAF
//     * timestamp : 1545900092
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
//         * groupDetailInfo : {"groupInfo":[{"id":"1","groupName":"咪咪酱","groupSno":"21d14cfbc38dc46373f22fcf1e170dcf","groupHeadImg":"http://192.168.4.131:40005/default/img/default_group.png","groupQrcode":"http://www.xm6leefun.cn:40005/default/img/079465c75f9663b04507172d3bbb9678.png"}],"groupUserInfo":[{"userId":"08ca-e24c-7ac","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154399442839104.png","nickName":"密密酱","personaSignature":"走自己的路","qrcode":"http://192.168.4.131:40005/default/img/7111b6b89794ac8353b5d757eb296ebe.png","carteName":"","isRelation":1},{"userId":"3729-6df1-68a","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154329746816480.png","nickName":"indy","personaSignature":"","qrcode":"http://192.168.4.131:40005/default/img/4a2f50e1e66abc3836aeaed3e24176e8.png","carteName":"","isRelation":1},{"userId":"8f6b-a9b2-8e2","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154417305724798.png","nickName":"骆驼","personaSignature":"我是从北墙开着骆驼到南墙的骆驼","qrcode":"http://192.168.4.131:40005/default/img/ab2fcc273d206f5e236a3da0f349e6bf.png","carteName":"","isRelation":2},{"userId":"b18e-74bf-8e8","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154328711748669.png","nickName":"昆&仑#","personaSignature":"wu@杰","qrcode":"http://192.168.4.131:40005/default/img/aa64361695febc17fb344944a0c4d4dc.png","carteName":"","isRelation":3}],"groupNotice":[{"id":"1","noticeContent":"通知通知"}],"userInfo":{"userId":"b18e-74bf-8e8","carteName":"","identityType":"3","disturbType":"1","assistantType":"1"},"isRelation":2}
//         * verificationMD5 :
//         */
//
//        private GroupDetailInfoBean groupDetailInfo;
//        private String verificationMD5;
//
//        public GroupDetailInfoBean getGroupDetailInfo() {
//            return groupDetailInfo;
//        }
//
//        public void setGroupDetailInfo(GroupDetailInfoBean groupDetailInfo) {
//            this.groupDetailInfo = groupDetailInfo;
//        }
//
//        public String getVerificationMD5() {
//            return verificationMD5;
//        }
//
//        public void setVerificationMD5(String verificationMD5) {
//            this.verificationMD5 = verificationMD5;
//        }
//
//        public static class GroupDetailInfoBean {
//            /**
//             * groupInfo : [{"id":"1","groupName":"咪咪酱","groupSno":"21d14cfbc38dc46373f22fcf1e170dcf","groupHeadImg":"http://192.168.4.131:40005/default/img/default_group.png","groupQrcode":"http://www.xm6leefun.cn:40005/default/img/079465c75f9663b04507172d3bbb9678.png"}]
//             * groupUserInfo : [{"userId":"08ca-e24c-7ac","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154399442839104.png","nickName":"密密酱","personaSignature":"走自己的路","qrcode":"http://192.168.4.131:40005/default/img/7111b6b89794ac8353b5d757eb296ebe.png","carteName":"","isRelation":1},{"userId":"3729-6df1-68a","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154329746816480.png","nickName":"indy","personaSignature":"","qrcode":"http://192.168.4.131:40005/default/img/4a2f50e1e66abc3836aeaed3e24176e8.png","carteName":"","isRelation":1},{"userId":"8f6b-a9b2-8e2","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154417305724798.png","nickName":"骆驼","personaSignature":"我是从北墙开着骆驼到南墙的骆驼","qrcode":"http://192.168.4.131:40005/default/img/ab2fcc273d206f5e236a3da0f349e6bf.png","carteName":"","isRelation":2},{"userId":"b18e-74bf-8e8","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154328711748669.png","nickName":"昆&仑#","personaSignature":"wu@杰","qrcode":"http://192.168.4.131:40005/default/img/aa64361695febc17fb344944a0c4d4dc.png","carteName":"","isRelation":3}]
//             * groupNotice : [{"id":"1","noticeContent":"通知通知"}]
//             * userInfo : {"userId":"b18e-74bf-8e8","carteName":"","identityType":"3","disturbType":"1","assistantType":"1"}
//             * isRelation : 2
//             */
//
//            private UserInfoBean userInfo;
//            private int isRelation;
//            private List<GroupInfoBean> groupInfo;
//            private List<GroupUserInfoBean> groupUserInfo;
//            private List<GroupNoticeBean> groupNotice;
//
//            public UserInfoBean getUserInfo() {
//                return userInfo;
//            }
//
//            public void setUserInfo(UserInfoBean userInfo) {
//                this.userInfo = userInfo;
//            }
//
//            public int getIsRelation() {
//                return isRelation;
//            }
//
//            public void setIsRelation(int isRelation) {
//                this.isRelation = isRelation;
//            }
//
//            public List<GroupInfoBean> getGroupInfo() {
//                return groupInfo;
//            }
//
//            public void setGroupInfo(List<GroupInfoBean> groupInfo) {
//                this.groupInfo = groupInfo;
//            }
//
//            public List<GroupUserInfoBean> getGroupUserInfo() {
//                return groupUserInfo;
//            }
//
//            public void setGroupUserInfo(List<GroupUserInfoBean> groupUserInfo) {
//                this.groupUserInfo = groupUserInfo;
//            }
//
//            public List<GroupNoticeBean> getGroupNotice() {
//                return groupNotice;
//            }
//
//            public void setGroupNotice(List<GroupNoticeBean> groupNotice) {
//                this.groupNotice = groupNotice;
//            }
//
//            public static class UserInfoBean {
//                /**
//                 * userId : b18e-74bf-8e8
//                 * carteName :
//                 * identityType : 3
//                 * disturbType : 1
//                 * assistantType : 1
//                 */
//
//                private String userId;
//                private String carteName;
//                private String identityType;
//                private String disturbType;
//                private String assistantType;
//
//                public String getUserId() {
//                    return userId;
//                }
//
//                public void setUserId(String userId) {
//                    this.userId = userId;
//                }
//
//                public String getCarteName() {
//                    return carteName;
//                }
//
//                public void setCarteName(String carteName) {
//                    this.carteName = carteName;
//                }
//
//                public String getIdentityType() {
//                    return identityType;
//                }
//
//                public void setIdentityType(String identityType) {
//                    this.identityType = identityType;
//                }
//
//                public String getDisturbType() {
//                    return disturbType;
//                }
//
//                public void setDisturbType(String disturbType) {
//                    this.disturbType = disturbType;
//                }
//
//                public String getAssistantType() {
//                    return assistantType;
//                }
//
//                public void setAssistantType(String assistantType) {
//                    this.assistantType = assistantType;
//                }
//            }
//
//            public static class GroupInfoBean {
//                /**
//                 * id : 1
//                 * groupName : 咪咪酱
//                 * groupSno : 21d14cfbc38dc46373f22fcf1e170dcf
//                 * groupHeadImg : http://192.168.4.131:40005/default/img/default_group.png
//                 * groupQrcode : http://www.xm6leefun.cn:40005/default/img/079465c75f9663b04507172d3bbb9678.png
//                 */
//
//                private String id;
//                private String groupName;
//                private String groupSno;
//                private String groupHeadImg;
//                private String groupQrcode;
//
//                public String getId() {
//                    return id;
//                }
//
//                public void setId(String id) {
//                    this.id = id;
//                }
//
//                public String getGroupName() {
//                    return groupName;
//                }
//
//                public void setGroupName(String groupName) {
//                    this.groupName = groupName;
//                }
//
//                public String getGroupSno() {
//                    return groupSno;
//                }
//
//                public void setGroupSno(String groupSno) {
//                    this.groupSno = groupSno;
//                }
//
//                public String getGroupHeadImg() {
//                    return groupHeadImg;
//                }
//
//                public void setGroupHeadImg(String groupHeadImg) {
//                    this.groupHeadImg = groupHeadImg;
//                }
//
//                public String getGroupQrcode() {
//                    return groupQrcode;
//                }
//
//                public void setGroupQrcode(String groupQrcode) {
//                    this.groupQrcode = groupQrcode;
//                }
//            }
//
//            public static class GroupUserInfoBean {
//                /**
//                 * userId : 08ca-e24c-7ac
//                 * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154399442839104.png
//                 * nickName : 密密酱
//                 * personaSignature : 走自己的路
//                 * qrcode : http://192.168.4.131:40005/default/img/7111b6b89794ac8353b5d757eb296ebe.png
//                 * carteName :
//                 * isRelation : 1
//                 */
//
//                private String userId;
//                private String headImg;
//                private String nickName;
//                private String personaSignature;
//                private String qrcode;
//                private String carteName;
//                private String isRelation;
//
//                public String getUserId() {
//                    return userId;
//                }
//
//                public void setUserId(String userId) {
//                    this.userId = userId;
//                }
//
//                public String getHeadImg() {
//                    return headImg;
//                }
//
//                public void setHeadImg(String headImg) {
//                    this.headImg = headImg;
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
//                public String getPersonaSignature() {
//                    return personaSignature;
//                }
//
//                public void setPersonaSignature(String personaSignature) {
//                    this.personaSignature = personaSignature;
//                }
//
//                public String getQrcode() {
//                    return qrcode;
//                }
//
//                public void setQrcode(String qrcode) {
//                    this.qrcode = qrcode;
//                }
//
//                public String getCarteName() {
//                    return carteName;
//                }
//
//                public void setCarteName(String carteName) {
//                    this.carteName = carteName;
//                }
//
//                public String getIsRelation() {
//                    return isRelation;
//                }
//
//                public void setIsRelation(String isRelation) {
//                    this.isRelation = isRelation;
//                }
//            }
//
//            public static class GroupNoticeBean {
//                /**
//                 * id : 1
//                 * noticeContent : 通知通知
//                 */
//
//                private String id;
//                private String noticeContent;
//
//                public String getId() {
//                    return id;
//                }
//
//                public void setId(String id) {
//                    this.id = id;
//                }
//
//                public String getNoticeContent() {
//                    return noticeContent;
//                }
//
//                public void setNoticeContent(String noticeContent) {
//                    this.noticeContent = noticeContent;
//                }
//            }
//        }
//    }


//    /**
//     * code : 200
//     * msg : 成功
//     * method : searchDetailInfo
//     * record : {"groupDetailInfo":{"groupInfo":{"groupOfId":"1","nickName":"666","groupSno":"65ffd7e846091f6442c7fbbeb92facea","headImg":"http://www.xm6leefun.cn:40005/default/img/default_group.png","groupInstruction":""},"groupUserInfo":[{"userId":"1b42-b9c2-c6f","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502973436653.png","nickName":"嘎嘎嘎嘎嘎","personaSignature":"","qrcode":"http://www.xm6leefun.cn:40005/default/img/00b8aaa5b1ad0fe00869bcae3519f9f4.png","carteName":"","isRelation":1},{"userId":"9a36-9ec1-412","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png","nickName":"888","personaSignature":"","qrcode":"http://www.xm6leefun.cn:40005/default/img/099a65388eb7e84987228d795bee52b5.png","carteName":"","isRelation":2},{"userId":"d81a-0865-70d","headImg":"http://www.xm6leefun.cn:40005/default/img/default_head.png","nickName":"匿名用户","personaSignature":"","qrcode":"http://www.xm6leefun.cn:40005/default/img/50eb9c30f7bd324715d81a9407e1f81c.png","carteName":"","isRelation":1}],"groupNotice":{"id":""},"userInfo":{"userId":"1b42-b9c2-c6f","carteName":"","identityType":"3","disturbType":"1","assistantType":"1"},"isRelation":2}}
//     * api_key : 20180903
//     * sign : 1DA037B83B9A27DC2E889ABC71F50ADB
//     * timestamp : 1545747240
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
//         * groupDetailInfo : {"groupInfo":{"groupOfId":"1","nickName":"666","groupSno":"65ffd7e846091f6442c7fbbeb92facea","headImg":"http://www.xm6leefun.cn:40005/default/img/default_group.png","groupInstruction":""},"groupUserInfo":[{"userId":"1b42-b9c2-c6f","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502973436653.png","nickName":"嘎嘎嘎嘎嘎","personaSignature":"","qrcode":"http://www.xm6leefun.cn:40005/default/img/00b8aaa5b1ad0fe00869bcae3519f9f4.png","carteName":"","isRelation":1},{"userId":"9a36-9ec1-412","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png","nickName":"888","personaSignature":"","qrcode":"http://www.xm6leefun.cn:40005/default/img/099a65388eb7e84987228d795bee52b5.png","carteName":"","isRelation":2},{"userId":"d81a-0865-70d","headImg":"http://www.xm6leefun.cn:40005/default/img/default_head.png","nickName":"匿名用户","personaSignature":"","qrcode":"http://www.xm6leefun.cn:40005/default/img/50eb9c30f7bd324715d81a9407e1f81c.png","carteName":"","isRelation":1}],"groupNotice":{"id":""},"userInfo":{"userId":"1b42-b9c2-c6f","carteName":"","identityType":"3","disturbType":"1","assistantType":"1"},"isRelation":2}
//         */
//
//        private GroupDetailInfoBean groupDetailInfo;
//
//        public GroupDetailInfoBean getGroupDetailInfo() {
//            return groupDetailInfo;
//        }
//
//        public void setGroupDetailInfo(GroupDetailInfoBean groupDetailInfo) {
//            this.groupDetailInfo = groupDetailInfo;
//        }
//
//        public static class GroupDetailInfoBean {
//            /**
//             * groupInfo : {"groupOfId":"1","nickName":"666","groupSno":"65ffd7e846091f6442c7fbbeb92facea","headImg":"http://www.xm6leefun.cn:40005/default/img/default_group.png","groupInstruction":""}
//             * groupUserInfo : [{"userId":"1b42-b9c2-c6f","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502973436653.png","nickName":"嘎嘎嘎嘎嘎","personaSignature":"","qrcode":"http://www.xm6leefun.cn:40005/default/img/00b8aaa5b1ad0fe00869bcae3519f9f4.png","carteName":"","isRelation":1},{"userId":"9a36-9ec1-412","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png","nickName":"888","personaSignature":"","qrcode":"http://www.xm6leefun.cn:40005/default/img/099a65388eb7e84987228d795bee52b5.png","carteName":"","isRelation":2},{"userId":"d81a-0865-70d","headImg":"http://www.xm6leefun.cn:40005/default/img/default_head.png","nickName":"匿名用户","personaSignature":"","qrcode":"http://www.xm6leefun.cn:40005/default/img/50eb9c30f7bd324715d81a9407e1f81c.png","carteName":"","isRelation":1}]
//             * groupNotice : {"id":""}
//             * userInfo : {"userId":"1b42-b9c2-c6f","carteName":"","identityType":"3","disturbType":"1","assistantType":"1"}
//             * isRelation : 2
//             */
//
//            private GroupInfoBean groupInfo;
//            private GroupNoticeBean groupNotice;
//            private UserInfoBean userInfo;
//            private int isRelation;
//            private List<GroupUserInfoBean> groupUserInfo;
//
//            public GroupInfoBean getGroupInfo() {
//                return groupInfo;
//            }
//
//            public void setGroupInfo(GroupInfoBean groupInfo) {
//                this.groupInfo = groupInfo;
//            }
//
//            public GroupNoticeBean getGroupNotice() {
//                return groupNotice;
//            }
//
//            public void setGroupNotice(GroupNoticeBean groupNotice) {
//                this.groupNotice = groupNotice;
//            }
//
//            public UserInfoBean getUserInfo() {
//                return userInfo;
//            }
//
//            public void setUserInfo(UserInfoBean userInfo) {
//                this.userInfo = userInfo;
//            }
//
//            public int getIsRelation() {
//                return isRelation;
//            }
//
//            public void setIsRelation(int isRelation) {
//                this.isRelation = isRelation;
//            }
//
//            public List<GroupUserInfoBean> getGroupUserInfo() {
//                return groupUserInfo;
//            }
//
//            public void setGroupUserInfo(List<GroupUserInfoBean> groupUserInfo) {
//                this.groupUserInfo = groupUserInfo;
//            }
//
//            public static class GroupInfoBean {
//                /**
//                 * groupOfId : 1
//                 * nickName : 666
//                 * groupSno : 65ffd7e846091f6442c7fbbeb92facea
//                 * headImg : http://www.xm6leefun.cn:40005/default/img/default_group.png
//                 * groupInstruction :
//                 */
//                private String id;
//                private String groupName;
//                private String groupSno;
//                private String groupHeadImg;
//                private String groupQrcode;
////                private String groupOfId;
////                private String nickName;
////                private String groupSno;
////                private String headImg;
////                private String groupInstruction;
//
//
//                public String getId() {
//                    return id;
//                }
//
//                public void setId(String id) {
//                    this.id = id;
//                }
//
//                public String getGroupName() {
//                    return groupName;
//                }
//
//                public void setGroupName(String groupName) {
//                    this.groupName = groupName;
//                }
//
//                public String getGroupSno() {
//                    return groupSno;
//                }
//
//                public void setGroupSno(String groupSno) {
//                    this.groupSno = groupSno;
//                }
//
//                public String getGroupHeadImg() {
//                    return groupHeadImg;
//                }
//
//                public void setGroupHeadImg(String groupHeadImg) {
//                    this.groupHeadImg = groupHeadImg;
//                }
//
//                public String getGroupQrcode() {
//                    return groupQrcode;
//                }
//
//                public void setGroupQrcode(String groupQrcode) {
//                    this.groupQrcode = groupQrcode;
//                }
//            }
//
//            public static class GroupNoticeBean {
//                /**
//                 * id :
//                 */
//
//                private String id;
//                String noticeContent;
//
//                public String getNoticeContent() {
//                    return noticeContent;
//                }
//
//                public void setNoticeContent(String noticeContent) {
//                    this.noticeContent = noticeContent;
//                }
//
//                public String getId() {
//                    return id;
//                }
//
//                public void setId(String id) {
//                    this.id = id;
//                }
//            }
//
//            public static class UserInfoBean {
//                /**
//                 * userId : 1b42-b9c2-c6f
//                 * carteName :
//                 * identityType : 3
//                 * disturbType : 1
//                 * assistantType : 1
//                 */
//
//                private String userId;
//                private String carteName;
//                private String identityType;
//                private String disturbType;
//                private String assistantType;
//
//                public String getUserId() {
//                    return userId;
//                }
//
//                public void setUserId(String userId) {
//                    this.userId = userId;
//                }
//
//                public String getCarteName() {
//                    return carteName;
//                }
//
//                public void setCarteName(String carteName) {
//                    this.carteName = carteName;
//                }
//
//                public String getIdentityType() {
//                    return identityType;
//                }
//
//                public void setIdentityType(String identityType) {
//                    this.identityType = identityType;
//                }
//
//                public String getDisturbType() {
//                    return disturbType;
//                }
//
//                public void setDisturbType(String disturbType) {
//                    this.disturbType = disturbType;
//                }
//
//                public String getAssistantType() {
//                    return assistantType;
//                }
//
//                public void setAssistantType(String assistantType) {
//                    this.assistantType = assistantType;
//                }
//            }
//
//            public static class GroupUserInfoBean {
//                /**
//                 * userId : 1b42-b9c2-c6f
//                 * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502973436653.png
//                 * nickName : 嘎嘎嘎嘎嘎
//                 * personaSignature :
//                 * qrcode : http://www.xm6leefun.cn:40005/default/img/00b8aaa5b1ad0fe00869bcae3519f9f4.png
//                 * carteName :
//                 * isRelation : 1
//                 */
//
//                private String userId;
//                private String headImg;
//                private String nickName;
//                private String personaSignature;
//                private String qrcode;
//                private String carteName;
//                private String isRelation;
//
//                public String getUserId() {
//                    return userId;
//                }
//
//                public void setUserId(String userId) {
//                    this.userId = userId;
//                }
//
//                public String getHeadImg() {
//                    return headImg;
//                }
//
//                public void setHeadImg(String headImg) {
//                    this.headImg = headImg;
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
//                public String getPersonaSignature() {
//                    return personaSignature;
//                }
//
//                public void setPersonaSignature(String personaSignature) {
//                    this.personaSignature = personaSignature;
//                }
//
//                public String getQrcode() {
//                    return qrcode;
//                }
//
//                public void setQrcode(String qrcode) {
//                    this.qrcode = qrcode;
//                }
//
//                public String getCarteName() {
//                    return carteName;
//                }
//
//                public void setCarteName(String carteName) {
//                    this.carteName = carteName;
//                }
//
//                public String getIsRelation() {
//                    return isRelation;
//                }
//
//                public void setIsRelation(String isRelation) {
//                    this.isRelation = isRelation;
//                }
//            }
//        }
//    }


    /**
     * code : 200
     * msg : 成功
     * method : searchDetailInfo
     * record : {"group_detail_info":{"group_info":[{"id":"2","group_name":"这是交友群","group_sno":"18150960007","group_head_img":"http://pic.sogou.com/d?query=é»\u0098è®¤å¤´å\u0083\u008f&ie=utf8&page=1&did=4&st=255&mode=255&phu=http://img.zcool.cn/community/016a1955ed02cc32f875a13291fb8b.png&p=40230500#did3","group_qrcode":"0"}],"group_user_info":[{"user_id":"1","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","nick_name":"小强"},{"user_id":"2","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","nick_name":"小强"},{"user_id":"3","head_img":"/storage/emulated/0/chat_image4e61c014-cb8a-4c4d-bdba-710a892589b4.jpg","nick_name":"android"},{"user_id":"4","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","nick_name":"android"}],"group_notice":{}}}
     * api_key : 20180903
     * sign : 50315382E92BAE84A02F71BE39400670
     * timestamp : 1538213268
     */

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
//         * group_detail_info : {"group_info":[{"id":"2","group_name":"这是交友群","group_sno":"18150960007","group_head_img":"http://pic.sogou.com/d?query=é»\u0098è®¤å¤´å\u0083\u008f&ie=utf8&page=1&did=4&st=255&mode=255&phu=http://img.zcool.cn/community/016a1955ed02cc32f875a13291fb8b.png&p=40230500#did3","group_qrcode":"0"}],"group_user_info":[{"user_id":"1","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","nick_name":"小强"},{"user_id":"2","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","nick_name":"小强"},{"user_id":"3","head_img":"/storage/emulated/0/chat_image4e61c014-cb8a-4c4d-bdba-710a892589b4.jpg","nick_name":"android"},{"user_id":"4","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","nick_name":"android"}],"group_notice":{}}
//         */
//
//        private GroupDetailInfoBean groupDetailInfo;
//
//        public GroupDetailInfoBean getGroupDetailInfo() {
//            return groupDetailInfo;
//        }
//
//        public void setGroupDetailInfo(GroupDetailInfoBean groupDetailInfo) {
//            this.groupDetailInfo = groupDetailInfo;
//        }
////        public GroupDetailInfoBean getGroupDetailInfo() {
////            return groupDetailInfo;
////        }
////
////        public void setGroup_detail_info(GroupDetailInfoBean groupDetailInfo) {
////            this.groupDetailInfo = groupDetailInfo;
////        }
//
//        public static class GroupDetailInfoBean {
//            /**
//             * group_info : [{"id":"2","group_name":"这是交友群","group_sno":"18150960007","group_head_img":"http://pic.sogou.com/d?query=é»\u0098è®¤å¤´å\u0083\u008f&ie=utf8&page=1&did=4&st=255&mode=255&phu=http://img.zcool.cn/community/016a1955ed02cc32f875a13291fb8b.png&p=40230500#did3","group_qrcode":"0"}]
//             * group_user_info : [{"user_id":"1","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","nick_name":"小强"},{"user_id":"2","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","nick_name":"小强"},{"user_id":"3","head_img":"/storage/emulated/0/chat_image4e61c014-cb8a-4c4d-bdba-710a892589b4.jpg","nick_name":"android"},{"user_id":"4","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","nick_name":"android"}]
//             * group_notice : {}
//             */
//
//            private GroupNoticeBean groupNotice;
//            private List<GroupInfoBean> groupInfo;
//            private List<GroupUserInfoBean> groupUserInfo;
//
//            public GroupNoticeBean getGroupNotice() {
//                return groupNotice;
//            }
//
//            public void setGroupNotice(GroupNoticeBean groupNotice) {
//                this.groupNotice = groupNotice;
//            }
//
//            public List<GroupInfoBean> getGroupInfo() {
//                return groupInfo;
//            }
//
//            public void setGroupInfo(List<GroupInfoBean> groupInfo) {
//                this.groupInfo = groupInfo;
//            }
//
//            public List<GroupUserInfoBean> getGroupUserInfo() {
//                return groupUserInfo;
//            }
//
//            public void setGroupUserInfo(List<GroupUserInfoBean> groupUserInfo) {
//                this.groupUserInfo = groupUserInfo;
//            }
////            public GroupNoticeBean getGroup_notice() {
////                return groupNotice;
////            }
////
////            public void setGroup_notice(GroupNoticeBean groupNotice) {
////                this.groupNotice = groupNotice;
////            }
////
////            public List<GroupInfoBean> getGroup_info() {
////                return groupInfo;
////            }
////
////            public void setGroup_info(List<GroupInfoBean> groupInfo) {
////                this.groupInfo = groupInfo;
////            }
////
////            public List<GroupUserInfoBean> getGroup_user_info() {
////                return groupUserInfo;
////            }
////
////            public void setGroup_user_info(List<GroupUserInfoBean> groupUserInfo) {
////                this.groupUserInfo = groupUserInfo;
////            }
//
//            public static class GroupNoticeBean {
//
//                String id;
//                String noticeContent;
//
//                public String getId() {
//                    return id;
//                }
//
//                public void setId(String id) {
//                    this.id = id;
//                }
//
//                public String getNoticeContent() {
//                    return noticeContent;
//                }
//
//                public void setNoticeContent(String noticeContent) {
//                    this.noticeContent = noticeContent;
//                }
//            }
//
//            public static class GroupInfoBean {
//                /**
//                 * id : 2
//                 * group_name : 这是交友群
//                 * group_sno : 18150960007
//                 * group_head_img : http://pic.sogou.com/d?query=é»è®¤å¤´å&ie=utf8&page=1&did=4&st=255&mode=255&phu=http://img.zcool.cn/community/016a1955ed02cc32f875a13291fb8b.png&p=40230500#did3
//                 * group_qrcode : 0
//                 */
//
//                private String id;
//                private String groupName;
//                private String groupSno;
//                private String groupHeadImg;
//                private String groupQrcode;
//
//                public String getId() {
//                    return id;
//                }
//
//                public void setId(String id) {
//                    this.id = id;
//                }
//
//                public String getGroupName() {
//                    return groupName;
//                }
//
//                public void setGroupName(String groupName) {
//                    this.groupName = groupName;
//                }
//
//                public String getGroupSno() {
//                    return groupSno;
//                }
//
//                public void setGroupSno(String groupSno) {
//                    this.groupSno = groupSno;
//                }
//
//                public String getGroupHeadImg() {
//                    return groupHeadImg;
//                }
//
//                public void setGroupHeadImg(String groupHeadImg) {
//                    this.groupHeadImg = groupHeadImg;
//                }
//
//                public String getGroupQrcode() {
//                    return groupQrcode;
//                }
//
//                public void setGroupQrcode(String groupQrcode) {
//                    this.groupQrcode = groupQrcode;
//                }
////                public String getGroup_name() {
////                    return groupName;
////                }
////
////                public void setGroup_name(String groupName) {
////                    this.groupName = groupName;
////                }
////
////                public String getGroup_sno() {
////                    return groupSno;
////                }
////
////                public void setGroup_sno(String groupSno) {
////                    this.groupSno = groupSno;
////                }
////
////                public String getGroup_head_img() {
////                    return groupHeadImg;
////                }
////
////                public void setGroup_head_img(String groupHeadImg) {
////                    this.groupHeadImg = groupHeadImg;
////                }
////
////                public String getGroup_qrcode() {
////                    return groupQrcode;
////                }
////
////                public void setGroup_qrcode(String groupQrcode) {
////                    this.groupQrcode = groupQrcode;
////                }
//            }
//
//            public static class GroupUserInfoBean {
//                /**
//                 * user_id : 1
//                 * head_img : http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132
//                 * nick_name : 小强
//                 */
//
//                private String userId;
//                private String headImg;
//                private String nickName;
//
////                1不是还有，2是好友，3自己
//                private String isRelation;
//
//                public String getIsRelation() {
//                    return isRelation;
//                }
//
//                public void setIsRelation(String isRelation) {
//                    this.isRelation = isRelation;
//                }
//
//                public String getUserId() {
//                    return userId;
//                }
//
//                public void setUserId(String userId) {
//                    this.userId = userId;
//                }
//
//                public String getHeadImg() {
//                    return headImg;
//                }
//
//                public void setHeadImg(String headImg) {
//                    this.headImg = headImg;
//                }
//
//                public String getNickName() {
//                    return nickName;
//                }
//
//                public void setNickName(String nickName) {
//                    this.nickName = nickName;
//                }
//            }
//        }
//    }
}
