package com.mding.model;

public class DataNotice {

    /**
     * code : 200
     * msg : 成功
     * method : groupNoticeInfo
     * record : {"groupInfo":{"id":"1","noticeContent":"通知通知","created":"1546422162"},"memberInfo":{"memberId":"9a36-9ec1-412","nickName":"888","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png"}}
     * api_key : 20180903
     * sign : 9FDE4A7AE7593543E8E5464A2B157BA8
     * timestamp : 1546450886
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
         * groupInfo : {"id":"1","noticeContent":"通知通知","created":"1546422162"}
         * memberInfo : {"memberId":"9a36-9ec1-412","nickName":"888","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png"}
         */

        private GroupInfoBean groupInfo;
        private MemberInfoBean memberInfo;

        public GroupInfoBean getGroupInfo() {
            return groupInfo;
        }

        public void setGroupInfo(GroupInfoBean groupInfo) {
            this.groupInfo = groupInfo;
        }

        public MemberInfoBean getMemberInfo() {
            return memberInfo;
        }

        public void setMemberInfo(MemberInfoBean memberInfo) {
            this.memberInfo = memberInfo;
        }

        public static class GroupInfoBean {
            /**
             * id : 1
             * noticeContent : 通知通知
             * created : 1546422162
             */

            private String id;
            private String noticeContent;
            private String created;

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

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }
        }

        public static class MemberInfoBean {
            /**
             * memberId : 9a36-9ec1-412
             * nickName : 888
             * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png
             */

            private String memberId;
            private String nickName;
            private String headImg;

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
        }
    }
}
