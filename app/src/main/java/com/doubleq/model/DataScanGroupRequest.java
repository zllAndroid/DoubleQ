package com.doubleq.model;

public class DataScanGroupRequest {
    /**
     * code : 200
     * msg : 成功
     * method : addGroupOfQrCode
     * record : {"userId":"d757-14dc-3c2","groupId":"1","groupName":"同事"}
     * api_key : 20180903
     * sign : 905792D88F6B19E8FF82225F75266169
     * timestamp : 1548310989
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
         * userId : d757-14dc-3c2
         * groupId : 1
         * groupName : 同事
         */

        private String userId;
        private String groupId;
        private String groupName;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
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
    }


    //    /**
//     * code : 200
//     * msg : 成功
//     * method : addGroupOfQrCode
//     * record : {"groupDetailInfo":{"groupInfo":{"id":"1","groupName":"666","groupSno":"65ffd7e846091f6442c7fbbeb92facea","groupHeadImg":"http://192.168.4.131:40005/default/img/default_group.png","groupQrcode":"2_xm6leefun_65ffd7e846091f6442c7fbbeb92facea"},"groupUserInfo":[{"userId":"9a36-9ec1-412","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png","nickName":"湖里","personaSignature":"唯度°","qrcode":"1_xm6leefun_9a36-9ec1-412","modified":"1547792019","carteName":"","isRelation":2},{"userId":"1b42-b9c2-c6f","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502973436653.png","nickName":"赞赞赞赞赞赞赞赞赞赞","personaSignature":"我是小鸭子","qrcode":"1_xm6leefun_1b42-b9c2-c6f","modified":"1547567065","carteName":"","isRelation":3},{"userId":"d81a-0865-70d","headImg":"http://www.xm6leefun.cn:40005/default/img/default_head.png","nickName":"匿名用户","personaSignature":"","qrcode":"1_xm6leefun_d81a-0865-70d","modified":"0","carteName":"","isRelation":2}],"groupNotice":{"id":"","noticeContent":"dddd"},"userInfo":{"userId":"1b42-b9c2-c6f","carteName":"","identityType":"3","disturbType":"1","assistantType":"1","groupManageId":"0","groupManageName":"暂无"},"isRelation":2}}
//     * api_key : 20180903
//     * sign : 905792D88F6B19E8FF82225F75266169
//     * timestamp : 1548310989
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
//         * groupDetailInfo : {"groupInfo":{"id":"1","groupName":"666","groupSno":"65ffd7e846091f6442c7fbbeb92facea","groupHeadImg":"http://192.168.4.131:40005/default/img/default_group.png","groupQrcode":"2_xm6leefun_65ffd7e846091f6442c7fbbeb92facea"},"groupUserInfo":[{"userId":"9a36-9ec1-412","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png","nickName":"湖里","personaSignature":"唯度°","qrcode":"1_xm6leefun_9a36-9ec1-412","modified":"1547792019","carteName":"","isRelation":2},{"userId":"1b42-b9c2-c6f","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502973436653.png","nickName":"赞赞赞赞赞赞赞赞赞赞","personaSignature":"我是小鸭子","qrcode":"1_xm6leefun_1b42-b9c2-c6f","modified":"1547567065","carteName":"","isRelation":3},{"userId":"d81a-0865-70d","headImg":"http://www.xm6leefun.cn:40005/default/img/default_head.png","nickName":"匿名用户","personaSignature":"","qrcode":"1_xm6leefun_d81a-0865-70d","modified":"0","carteName":"","isRelation":2}],"groupNotice":{"id":"","noticeContent":"dddd"},"userInfo":{"userId":"1b42-b9c2-c6f","carteName":"","identityType":"3","disturbType":"1","assistantType":"1","groupManageId":"0","groupManageName":"暂无"},"isRelation":2}
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
//             * groupInfo : {"id":"1","groupName":"666","groupSno":"65ffd7e846091f6442c7fbbeb92facea","groupHeadImg":"http://192.168.4.131:40005/default/img/default_group.png","groupQrcode":"2_xm6leefun_65ffd7e846091f6442c7fbbeb92facea"}
//             * groupUserInfo : [{"userId":"9a36-9ec1-412","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png","nickName":"湖里","personaSignature":"唯度°","qrcode":"1_xm6leefun_9a36-9ec1-412","modified":"1547792019","carteName":"","isRelation":2},{"userId":"1b42-b9c2-c6f","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502973436653.png","nickName":"赞赞赞赞赞赞赞赞赞赞","personaSignature":"我是小鸭子","qrcode":"1_xm6leefun_1b42-b9c2-c6f","modified":"1547567065","carteName":"","isRelation":3},{"userId":"d81a-0865-70d","headImg":"http://www.xm6leefun.cn:40005/default/img/default_head.png","nickName":"匿名用户","personaSignature":"","qrcode":"1_xm6leefun_d81a-0865-70d","modified":"0","carteName":"","isRelation":2}]
//             * groupNotice : {"id":"","noticeContent":"dddd"}
//             * userInfo : {"userId":"1b42-b9c2-c6f","carteName":"","identityType":"3","disturbType":"1","assistantType":"1","groupManageId":"0","groupManageName":"暂无"}
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
//                 * groupName : 666
//                 * groupSno : 65ffd7e846091f6442c7fbbeb92facea
//                 * groupHeadImg : http://192.168.4.131:40005/default/img/default_group.png
//                 * groupQrcode : 2_xm6leefun_65ffd7e846091f6442c7fbbeb92facea
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
//                 * id :
//                 * noticeContent : dddd
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
//                 * userId : 1b42-b9c2-c6f
//                 * carteName :
//                 * identityType : 3
//                 * disturbType : 1
//                 * assistantType : 1
//                 * groupManageId : 0
//                 * groupManageName : 暂无
//                 */
//
//                private String userId;
//                private String carteName;
//                private String identityType;
//                private String disturbType;
//                private String assistantType;
//                private String groupManageId;
//                private String groupManageName;
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
//
//                public String getGroupManageId() {
//                    return groupManageId;
//                }
//
//                public void setGroupManageId(String groupManageId) {
//                    this.groupManageId = groupManageId;
//                }
//
//                public String getGroupManageName() {
//                    return groupManageName;
//                }
//
//                public void setGroupManageName(String groupManageName) {
//                    this.groupManageName = groupManageName;
//                }
//            }
//
//            public static class GroupUserInfoBean {
//                /**
//                 * userId : 9a36-9ec1-412
//                 * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png
//                 * nickName : 湖里
//                 * personaSignature : 唯度°
//                 * qrcode : 1_xm6leefun_9a36-9ec1-412
//                 * modified : 1547792019
//                 * carteName :
//                 * isRelation : 2
//                 */
//
//                private String userId;
//                private String headImg;
//                private String nickName;
//                private String personaSignature;
//                private String qrcode;
//                private String modified;
//                private String carteName;
//                private int isRelation;
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
//                public String getModified() {
//                    return modified;
//                }
//
//                public void setModified(String modified) {
//                    this.modified = modified;
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
//                public int getIsRelation() {
//                    return isRelation;
//                }
//
//                public void setIsRelation(int isRelation) {
//                    this.isRelation = isRelation;
//                }
//            }
//        }
//    }

//
//    /**
//     * code : 200
//     * msg : 成功
//     * method : addGroupOfQrCode
//     * record : {"groupDetailInfo":{"groupInfo":{"id":"40","groupName":"6666666688","groupSno":"5279ebf51417bfb61c555f0766dd3281","groupHeadImg":"http://192.168.4.131:40005/default/img/default_group.png","groupQrcode":"2_xm6leefun_5279ebf51417bfb61c555f0766dd3281"},"groupUserInfo":[{"userId":"f9fa-f5dd-ab2","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154751555593666.png","nickName":"我是大美人er","personaSignature":"幸福快乐每一天，真好呀。","qrcode":"1_xm6leefun_f9fa-f5dd-ab2","modified":"1547603978","carteName":"","isRelation":2},{"userId":"d81a-0865-70d","headImg":"http://www.xm6leefun.cn:40005/default/img/default_head.png","nickName":"匿名用户","personaSignature":"","qrcode":"1_xm6leefun_d81a-0865-70d","modified":"0","carteName":"","isRelation":2},{"userId":"1b42-b9c2-c6f","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502973436653.png","nickName":"赞赞赞赞赞赞赞赞赞赞","personaSignature":"我是小鸭子","qrcode":"1_xm6leefun_1b42-b9c2-c6f","modified":"1547567065","carteName":"","isRelation":2}],"groupNotice":{"id":""},"userInfo":{"userId":"","carteName":"","identityType":"","disturbType":"","assistantType":""},"isRelation":1}}
//     * api_key : 20180903
//     * sign : D0D61AC39F5151A4D5826EFD63A4379C
//     * timestamp : 1547696381
//     * only : 2
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
//         * groupDetailInfo : {"groupInfo":{"id":"40","groupName":"6666666688","groupSno":"5279ebf51417bfb61c555f0766dd3281","groupHeadImg":"http://192.168.4.131:40005/default/img/default_group.png","groupQrcode":"2_xm6leefun_5279ebf51417bfb61c555f0766dd3281"},"groupUserInfo":[{"userId":"f9fa-f5dd-ab2","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154751555593666.png","nickName":"我是大美人er","personaSignature":"幸福快乐每一天，真好呀。","qrcode":"1_xm6leefun_f9fa-f5dd-ab2","modified":"1547603978","carteName":"","isRelation":2},{"userId":"d81a-0865-70d","headImg":"http://www.xm6leefun.cn:40005/default/img/default_head.png","nickName":"匿名用户","personaSignature":"","qrcode":"1_xm6leefun_d81a-0865-70d","modified":"0","carteName":"","isRelation":2},{"userId":"1b42-b9c2-c6f","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502973436653.png","nickName":"赞赞赞赞赞赞赞赞赞赞","personaSignature":"我是小鸭子","qrcode":"1_xm6leefun_1b42-b9c2-c6f","modified":"1547567065","carteName":"","isRelation":2}],"groupNotice":{"id":""},"userInfo":{"userId":"","carteName":"","identityType":"","disturbType":"","assistantType":""},"isRelation":1}
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
//             * groupInfo : {"id":"40","groupName":"6666666688","groupSno":"5279ebf51417bfb61c555f0766dd3281","groupHeadImg":"http://192.168.4.131:40005/default/img/default_group.png","groupQrcode":"2_xm6leefun_5279ebf51417bfb61c555f0766dd3281"}
//             * groupUserInfo : [{"userId":"f9fa-f5dd-ab2","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154751555593666.png","nickName":"我是大美人er","personaSignature":"幸福快乐每一天，真好呀。","qrcode":"1_xm6leefun_f9fa-f5dd-ab2","modified":"1547603978","carteName":"","isRelation":2},{"userId":"d81a-0865-70d","headImg":"http://www.xm6leefun.cn:40005/default/img/default_head.png","nickName":"匿名用户","personaSignature":"","qrcode":"1_xm6leefun_d81a-0865-70d","modified":"0","carteName":"","isRelation":2},{"userId":"1b42-b9c2-c6f","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502973436653.png","nickName":"赞赞赞赞赞赞赞赞赞赞","personaSignature":"我是小鸭子","qrcode":"1_xm6leefun_1b42-b9c2-c6f","modified":"1547567065","carteName":"","isRelation":2}]
//             * groupNotice : {"id":""}
//             * userInfo : {"userId":"","carteName":"","identityType":"","disturbType":"","assistantType":""}
//             * isRelation : 1
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
//                 * id : 40
//                 * groupName : 6666666688
//                 * groupSno : 5279ebf51417bfb61c555f0766dd3281
//                 * groupHeadImg : http://192.168.4.131:40005/default/img/default_group.png
//                 * groupQrcode : 2_xm6leefun_5279ebf51417bfb61c555f0766dd3281
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
//                 * id :
//                 */
//
//                private String id;
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
//                 * userId :
//                 * carteName :
//                 * identityType :
//                 * disturbType :
//                 * assistantType :
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
//                 * userId : f9fa-f5dd-ab2
//                 * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154751555593666.png
//                 * nickName : 我是大美人er
//                 * personaSignature : 幸福快乐每一天，真好呀。
//                 * qrcode : 1_xm6leefun_f9fa-f5dd-ab2
//                 * modified : 1547603978
//                 * carteName :
//                 * isRelation : 2
//                 */
//
//                private String userId;
//                private String headImg;
//                private String nickName;
//                private String personaSignature;
//                private String qrcode;
//                private String modified;
//                private String carteName;
//                private int isRelation;
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
//                public String getModified() {
//                    return modified;
//                }
//
//                public void setModified(String modified) {
//                    this.modified = modified;
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
//                public int getIsRelation() {
//                    return isRelation;
//                }
//
//                public void setIsRelation(int isRelation) {
//                    this.isRelation = isRelation;
//                }
//            }
//        }
//    }
}
