package com.doubleq.model;

import java.util.List;

public class DataSearchResult {

    /**
     * code : 200
     * msg : 成功
     * method : searchInfo
     * record : {"seach_group_info":[{"id":"2","group_sno":"18150960007","group_name":"这是交友群","group_head_img":"http://pic.sogou.com/d?query=%E9%BB%98%E8%AE%A4%E5%A4%B4%E5%83%8F&ie=utf8&page=1&did=4&st=255&mode=255&phu=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F016a1955ed02cc32f875a13291fb8b.png&p=40230500#did3"}],"seach_user_info":[{"wx_sno":"mMyV95B4849406105b969d29e3ebf2.13962996","user_id":"cb16ce45-854c-f553-9c9a-2e57a6addca3","nick_name":"海大胖","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132"}]}
     * api_key : 20180903
     * sign : A41A2B414CA88CBF018F9E5AA01BE3AA
     * timestamp : 1538145749
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
        private List<SeachGroupInfoBean> seachGroupInfo;
        private List<SeachUserInfoBean> seachUserInfo;

        public List<SeachGroupInfoBean> getSeachGroupInfo() {
            return seachGroupInfo;
        }

        public void setSeachGroupInfo(List<SeachGroupInfoBean> seachGroupInfo) {
            this.seachGroupInfo = seachGroupInfo;
        }

        public List<SeachUserInfoBean> getSeachUserInfo() {
            return seachUserInfo;
        }

        public void setSeachUserInfo(List<SeachUserInfoBean> seachUserInfo) {
            this.seachUserInfo = seachUserInfo;
        }
//        public List<SeachGroupInfoBean> getSeach_group_info() {
//            return seachGroupInfo;
//        }
//
//        public void setSeach_group_info(List<SeachGroupInfoBean> seach_group_info) {
//            this.seachGroupInfo = seach_group_info;
//        }
//
//        public List<SeachUserInfoBean> getSeach_user_info() {
//            return seachUserInfo;
//        }
//
//        public void setSeach_user_info(List<SeachUserInfoBean> seachUserInfo) {
//            this.seachUserInfo = seachUserInfo;
//        }

        public static class SeachGroupInfoBean {
            /**
             * id : 2
             * group_sno : 18150960007
             * group_name : 这是交友群
             * group_head_img : http://pic.sogou.com/d?query=%E9%BB%98%E8%AE%A4%E5%A4%B4%E5%83%8F&ie=utf8&page=1&did=4&st=255&mode=255&phu=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F016a1955ed02cc32f875a13291fb8b.png&p=40230500#did3
             */

            private String id;
            private String groupSno;
            private String groupName;
            private String groupHeadImg;
            private String groupQrcode;
            private String isRelation;

            public String getIsRelation() {
                return isRelation;
            }

            public void setIsRelation(String isRelation) {
                this.isRelation = isRelation;
            }

            public String getGroupQrcode() {
                return groupQrcode;
            }

            public void setGroupQrcode(String groupQrcode) {
                this.groupQrcode = groupQrcode;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getGroupSno() {
                return groupSno;
            }

            public void setGroupSno(String groupSno) {
                this.groupSno = groupSno;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public String getGroupHeadImg() {
                return groupHeadImg;
            }

            public void setGroupHeadImg(String groupHeadImg) {
                this.groupHeadImg = groupHeadImg;
            }

            //            public String getGroup_sno() {
//                return groupSno;
//            }
//
//            public void setGroup_sno(String group_sno) {
//                this.groupSno = group_sno;
//            }
//
//            public String getGroup_name() {
//                return groupName;
//            }
//
//            public void setGroup_name(String group_name) {
//                this.groupName = group_name;
//            }
//
//            public String getGroup_head_img() {
//                return groupHeadImg;
//            }
//
//            public void setGroup_head_img(String group_head_img) {
//                this.groupHeadImg = group_head_img;
//            }
        }

        public static class SeachUserInfoBean {
            /**
             * wx_sno : mMyV95B4849406105b969d29e3ebf2.13962996
             * user_id : cb16ce45-854c-f553-9c9a-2e57a6addca3
             * nick_name : 海大胖
             * head_img : http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132
             */

            private String wxSno;
            private String userId;
            private String nickName;
            private String headImg;
            private String qrcode;
            private String isRelation;

            public String getIsRelation() {
                return isRelation;
            }

            public void setIsRelation(String isRelation) {
                this.isRelation = isRelation;
            }

            public String getQrcode() {
                return qrcode;
            }

            public void setQrcode(String qrcode) {
                this.qrcode = qrcode;
            }

            public String getWxSno() {
                return wxSno;
            }

            public void setWxSno(String wxSno) {
                this.wxSno = wxSno;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
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
//            public String getWx_sno() {
//                return wxSno;
//            }
//
//            public void setWx_sno(String wx_sno) {
//                this.wxSno = wx_sno;
//            }
//
//            public String getUser_id() {
//                return userId;
//            }
//
//            public void setUser_id(String user_id) {
//                this.userId = user_id;
//            }
//
//            public String getNick_name() {
//                return nickName;
//            }
//
//            public void setNick_name(String nick_name) {
//                this.nickName = nick_name;
//            }
//
//            public String getHead_img() {
//                return headImg;
//            }
//
//            public void setHead_img(String head_img) {
//                this.headImg = head_img;
//            }
        }
    }
}
