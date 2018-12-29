package com.doubleq.model;

import java.util.List;

public class DataTransferGroupMember {


    /**
     * code : 200
     * msg : 成功
     * method : getTransterGroupMemberInfo
     * record : {"memberList":[{"groupName":"M","groupList":[{"memberId":"dc81f581-657f-1f9f-bf48-d67dcc0cc0b6","nickName":"密密酱","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154114389417655.png","identityType":"3","chart":"M"}]},{"groupName":"X","groupList":[{"memberId":"2644b163-b898-ba84-a7be-69cb62f214c7","nickName":"小强子","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154113866221225.png","identityType":"3","chart":"X"}]},{"groupName":"Y","groupList":[{"memberId":"37e80787-0e5e-75f3-955c-e759d6327349","nickName":"杨澜","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154141021868364.png","identityType":"3","chart":"Y"}]}]}
     * api_key : 20180903
     * sign : 49DE2001589EF57A0DCD6D0D27146A7C
     * timestamp : 1544636320
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
        private List<MemberListBean> memberList;

        public List<MemberListBean> getMemberList() {
            return memberList;
        }

        public void setMemberList(List<MemberListBean> memberList) {
            this.memberList = memberList;
        }

        public static class MemberListBean {
            /**
             * groupName : M
             * groupList : [{"memberId":"dc81f581-657f-1f9f-bf48-d67dcc0cc0b6","nickName":"密密酱","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154114389417655.png","identityType":"3","chart":"M"}]
             */

            private String groupName;
            private List<GroupListBean> groupList;

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
                 * memberId : dc81f581-657f-1f9f-bf48-d67dcc0cc0b6
                 * nickName : 密密酱
                 * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154114389417655.png
                 * identityType : 3
                 * chart : M
                 */

                private String memberId;
                private String nickName;
                private String headImg;
                private String identityType;
                private String chart;

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

                public String getChart() {
                    return chart;
                }

                public void setChart(String chart) {
                    this.chart = chart;
                }
            }
        }
    }
}
