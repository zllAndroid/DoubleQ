package com.doubleq.model;

import java.util.List;

public class DataAddQunDetails {

    /**
     * code : 200
     * msg : 成功
     * method : searchDetailInfo
     * record : {"group_detail_info":{"group_info":[{"id":"2","group_name":"这是交友群","group_sno":"18150960007","group_head_img":"http://pic.sogou.com/d?query=é»\u0098è®¤å¤´å\u0083\u008f&ie=utf8&page=1&did=4&st=255&mode=255&phu=http://img.zcool.cn/community/016a1955ed02cc32f875a13291fb8b.png&p=40230500#did3","group_qrcode":"0"}],"group_user_info":[{"user_id":"1","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","nick_name":"小强"},{"user_id":"2","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","nick_name":"小强"},{"user_id":"3","head_img":"/storage/emulated/0/chat_image4e61c014-cb8a-4c4d-bdba-710a892589b4.jpg","nick_name":"android"},{"user_id":"4","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","nick_name":"android"}],"group_notice":{}}}
     * api_key : 20180903
     * sign : 50315382E92BAE84A02F71BE39400670
     * timestamp : 1538213268
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
         * group_detail_info : {"group_info":[{"id":"2","group_name":"这是交友群","group_sno":"18150960007","group_head_img":"http://pic.sogou.com/d?query=é»\u0098è®¤å¤´å\u0083\u008f&ie=utf8&page=1&did=4&st=255&mode=255&phu=http://img.zcool.cn/community/016a1955ed02cc32f875a13291fb8b.png&p=40230500#did3","group_qrcode":"0"}],"group_user_info":[{"user_id":"1","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","nick_name":"小强"},{"user_id":"2","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","nick_name":"小强"},{"user_id":"3","head_img":"/storage/emulated/0/chat_image4e61c014-cb8a-4c4d-bdba-710a892589b4.jpg","nick_name":"android"},{"user_id":"4","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","nick_name":"android"}],"group_notice":{}}
         */

        private GroupDetailInfoBean groupDetailInfo;

        public GroupDetailInfoBean getGroupDetailInfo() {
            return groupDetailInfo;
        }

        public void setGroupDetailInfo(GroupDetailInfoBean groupDetailInfo) {
            this.groupDetailInfo = groupDetailInfo;
        }
//        public GroupDetailInfoBean getGroupDetailInfo() {
//            return groupDetailInfo;
//        }
//
//        public void setGroup_detail_info(GroupDetailInfoBean groupDetailInfo) {
//            this.groupDetailInfo = groupDetailInfo;
//        }

        public static class GroupDetailInfoBean {
            /**
             * group_info : [{"id":"2","group_name":"这是交友群","group_sno":"18150960007","group_head_img":"http://pic.sogou.com/d?query=é»\u0098è®¤å¤´å\u0083\u008f&ie=utf8&page=1&did=4&st=255&mode=255&phu=http://img.zcool.cn/community/016a1955ed02cc32f875a13291fb8b.png&p=40230500#did3","group_qrcode":"0"}]
             * group_user_info : [{"user_id":"1","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","nick_name":"小强"},{"user_id":"2","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","nick_name":"小强"},{"user_id":"3","head_img":"/storage/emulated/0/chat_image4e61c014-cb8a-4c4d-bdba-710a892589b4.jpg","nick_name":"android"},{"user_id":"4","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","nick_name":"android"}]
             * group_notice : {}
             */

            private GroupNoticeBean groupNotice;
            private List<GroupInfoBean> groupInfo;
            private List<GroupUserInfoBean> groupUserInfo;

            public GroupNoticeBean getGroupNotice() {
                return groupNotice;
            }

            public void setGroupNotice(GroupNoticeBean groupNotice) {
                this.groupNotice = groupNotice;
            }

            public List<GroupInfoBean> getGroupInfo() {
                return groupInfo;
            }

            public void setGroupInfo(List<GroupInfoBean> groupInfo) {
                this.groupInfo = groupInfo;
            }

            public List<GroupUserInfoBean> getGroupUserInfo() {
                return groupUserInfo;
            }

            public void setGroupUserInfo(List<GroupUserInfoBean> groupUserInfo) {
                this.groupUserInfo = groupUserInfo;
            }
//            public GroupNoticeBean getGroup_notice() {
//                return groupNotice;
//            }
//
//            public void setGroup_notice(GroupNoticeBean groupNotice) {
//                this.groupNotice = groupNotice;
//            }
//
//            public List<GroupInfoBean> getGroup_info() {
//                return groupInfo;
//            }
//
//            public void setGroup_info(List<GroupInfoBean> groupInfo) {
//                this.groupInfo = groupInfo;
//            }
//
//            public List<GroupUserInfoBean> getGroup_user_info() {
//                return groupUserInfo;
//            }
//
//            public void setGroup_user_info(List<GroupUserInfoBean> groupUserInfo) {
//                this.groupUserInfo = groupUserInfo;
//            }

            public static class GroupNoticeBean {

                String id;
                String noticeContent;

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

            public static class GroupInfoBean {
                /**
                 * id : 2
                 * group_name : 这是交友群
                 * group_sno : 18150960007
                 * group_head_img : http://pic.sogou.com/d?query=é»è®¤å¤´å&ie=utf8&page=1&did=4&st=255&mode=255&phu=http://img.zcool.cn/community/016a1955ed02cc32f875a13291fb8b.png&p=40230500#did3
                 * group_qrcode : 0
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
//                public String getGroup_name() {
//                    return groupName;
//                }
//
//                public void setGroup_name(String groupName) {
//                    this.groupName = groupName;
//                }
//
//                public String getGroup_sno() {
//                    return groupSno;
//                }
//
//                public void setGroup_sno(String groupSno) {
//                    this.groupSno = groupSno;
//                }
//
//                public String getGroup_head_img() {
//                    return groupHeadImg;
//                }
//
//                public void setGroup_head_img(String groupHeadImg) {
//                    this.groupHeadImg = groupHeadImg;
//                }
//
//                public String getGroup_qrcode() {
//                    return groupQrcode;
//                }
//
//                public void setGroup_qrcode(String groupQrcode) {
//                    this.groupQrcode = groupQrcode;
//                }
            }

            public static class GroupUserInfoBean {
                /**
                 * user_id : 1
                 * head_img : http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132
                 * nick_name : 小强
                 */

                private String userId;
                private String headImg;
                private String nickName;

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
            }
        }
    }
}
