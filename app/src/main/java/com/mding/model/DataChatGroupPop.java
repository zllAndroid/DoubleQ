package com.mding.model;

public class DataChatGroupPop {
    /**
     * code : 200
     * msg : 成功
     * method : groupSendInterface
     * record : {"groupDetailInfo":{"groupInfo":{"id":"097f5f2af48611e4e88acb8db375b0fd","groupName":"信风反馈总群","maxNum":50000,"nowNum":78},"userInfo":{"userId":"4dda-565b-f00","carteName":"","identityType":3,"disturbType":"1"},"isRelation":2}}
     * api_key : 20180903
     * sign : 059382EE437F8F8B7EF007FF4644A9FB
     * timestamp : 1558075488
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
         * groupDetailInfo : {"groupInfo":{"id":"097f5f2af48611e4e88acb8db375b0fd","groupName":"信风反馈总群","maxNum":50000,"nowNum":78},"userInfo":{"userId":"4dda-565b-f00","carteName":"","identityType":3,"disturbType":"1"},"isRelation":2}
         */

        private GroupDetailInfoBean groupDetailInfo;

        public GroupDetailInfoBean getGroupDetailInfo() {
            return groupDetailInfo;
        }

        public void setGroupDetailInfo(GroupDetailInfoBean groupDetailInfo) {
            this.groupDetailInfo = groupDetailInfo;
        }

        public static class GroupDetailInfoBean {
            /**
             * groupInfo : {"id":"097f5f2af48611e4e88acb8db375b0fd","groupName":"信风反馈总群","maxNum":50000,"nowNum":78}
             * userInfo : {"userId":"4dda-565b-f00","carteName":"","identityType":3,"disturbType":"1"}
             * isRelation : 2
             */

            private GroupInfoBean groupInfo;
            private UserInfoBean userInfo;
            private int isRelation;

            public GroupInfoBean getGroupInfo() {
                return groupInfo;
            }

            public void setGroupInfo(GroupInfoBean groupInfo) {
                this.groupInfo = groupInfo;
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

            public static class GroupInfoBean {
                /**
                 * id : 097f5f2af48611e4e88acb8db375b0fd
                 * groupName : 信风反馈总群
                 * maxNum : 50000
                 * nowNum : 78
                 */

                private String id;
                private String groupName;
                private int maxNum;
                private int nowNum;

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

                public int getMaxNum() {
                    return maxNum;
                }

                public void setMaxNum(int maxNum) {
                    this.maxNum = maxNum;
                }

                public int getNowNum() {
                    return nowNum;
                }

                public void setNowNum(int nowNum) {
                    this.nowNum = nowNum;
                }
            }

            public static class UserInfoBean {
                /**
                 * userId : 4dda-565b-f00
                 * carteName :
                 * identityType : 3
                 * disturbType : 1
                 */

                private String userId;
                private String carteName;
                private int identityType;
                private String disturbType;

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

                public int getIdentityType() {
                    return identityType;
                }

                public void setIdentityType(int identityType) {
                    this.identityType = identityType;
                }

                public String getDisturbType() {
                    return disturbType;
                }

                public void setDisturbType(String disturbType) {
                    this.disturbType = disturbType;
                }
            }
        }
    }


//
//    /**
//     * code : 200
//     * msg : 成功
//     * method : groupSendInterface
//     * record : {"groupDetailInfo":{"groupInfo":{"id":"1","groupName":"666"},"userInfo":{"userId":"d81a-0865-70d","carteName":"","identityType":"3","disturbType":"1"},"isRelation":2}}
//     * api_key : 20180903
//     * sign : EB709CF08C5241761C20DE8578CF71D4
//     * timestamp : 1551180771
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
//         * groupDetailInfo : {"groupInfo":{"id":"1","groupName":"666"},"userInfo":{"userId":"d81a-0865-70d","carteName":"","identityType":"3","disturbType":"1"},"isRelation":2}
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
//             * groupInfo : {"id":"1","groupName":"666"}
//             * userInfo : {"userId":"d81a-0865-70d","carteName":"","identityType":"3","disturbType":"1"}
//             * isRelation : 2
//             */
//
//            private GroupInfoBean groupInfo;
//            private UserInfoBean userInfo;
//            private int isRelation;
//
//            public GroupInfoBean getGroupInfo() {
//                return groupInfo;
//            }
//
//            public void setGroupInfo(GroupInfoBean groupInfo) {
//                this.groupInfo = groupInfo;
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
//            public static class GroupInfoBean {
//                /**
//                 * id : 1
//                 * groupName : 666
//                 */
//
//                private String id;
//                private String groupName;
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
//            }
//
//            public static class UserInfoBean {
//                /**
//                 * userId : d81a-0865-70d
//                 * carteName :
//                 * identityType : 3
//                 * disturbType : 1
//                 */
//
//                private String userId;
//                private String carteName;
//                private String identityType;
//                private String disturbType;
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
//            }
//        }
//    }
}
