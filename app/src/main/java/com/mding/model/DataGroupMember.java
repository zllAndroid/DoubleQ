package com.mding.model;

import java.util.List;

public class DataGroupMember {


    /**
     * code : 200
     * msg : 成功
     * method : getGroupMemberList
     * record : {"memberList":[{"type":1,"groupName":"管理员","groupList":[{"memberId":"9a36-9ec1-412","nickName":"888","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png","identityType":"1","isRelation":2}]},{"type":2,"groupName":"G","groupList":[{"memberId":"1b42-b9c2-c6f","nickName":"嘎嘎嘎嘎嘎","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502973436653.png","identityType":"3","isRelation":3,"chart":"G"}]},{"type":2,"groupName":"N","groupList":[{"memberId":"d81a-0865-70d","nickName":"匿名用户","headImg":"http://www.xm6leefun.cn:40005/default/img/default_head.png","identityType":"3","isRelation":1,"chart":"N"}]}],"verificationMD5":""}
     * api_key : 20180903
     * sign : 8968792AC3736F6E896A7830E8C600B2
     * timestamp : 1545749218
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
         * memberList : [{"type":1,"groupName":"管理员","groupList":[{"memberId":"9a36-9ec1-412","nickName":"888","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png","identityType":"1","isRelation":2}]},{"type":2,"groupName":"G","groupList":[{"memberId":"1b42-b9c2-c6f","nickName":"嘎嘎嘎嘎嘎","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502973436653.png","identityType":"3","isRelation":3,"chart":"G"}]},{"type":2,"groupName":"N","groupList":[{"memberId":"d81a-0865-70d","nickName":"匿名用户","headImg":"http://www.xm6leefun.cn:40005/default/img/default_head.png","identityType":"3","isRelation":1,"chart":"N"}]}]
         * verificationMD5 :
         */

        private String verificationMD5;
        private List<MemberListBean> memberList;

        public String getVerificationMD5() {
            return verificationMD5;
        }

        public void setVerificationMD5(String verificationMD5) {
            this.verificationMD5 = verificationMD5;
        }

        public List<MemberListBean> getMemberList() {
            return memberList;
        }

        public void setMemberList(List<MemberListBean> memberList) {
            this.memberList = memberList;
        }

        public static class MemberListBean {
            /**
             * type : 1
             * groupName : 管理员
             * groupList : [{"memberId":"9a36-9ec1-412","nickName":"888","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png","identityType":"1","isRelation":2}]
             */

            private int type;
            private String groupName;
            private List<GroupListBean> groupList;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public List<GroupListBean> getGroupList() {
                return groupList;
            }

            public void setGroupList(List<GroupListBean> groupList) {
                this.groupList = groupList;
            }

            public static class GroupListBean {
                /**
                 * memberId : 9a36-9ec1-412
                 * nickName : 888
                 * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png
                 * identityType : 1
                 * isRelation : 2
                 */

                private String memberId;
                private String nickName;
                private String headImg;
                private String identityType;
                private int isRelation;

                public String getMemberId() {
                    return memberId;
                }

                public void setMemberId(String memberId) {
                    this.memberId = memberId;
                }

                public String getNickName() {
                    return nickName;
                }

                public void setNickName(String nickName) {
                    this.nickName = nickName;
                }

                public String getHeadImg() {
                    return headImg;
                }

                public void setHeadImg(String headImg) {
                    this.headImg = headImg;
                }

                public String getIdentityType() {
                    return identityType;
                }

                public void setIdentityType(String identityType) {
                    this.identityType = identityType;
                }

                public int getIsRelation() {
                    return isRelation;
                }

                public void setIsRelation(int isRelation) {
                    this.isRelation = isRelation;
                }
            }
        }
    }
}
