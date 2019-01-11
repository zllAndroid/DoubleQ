package com.doubleq.model;


import java.io.Serializable;
import java.util.List;

public class DataLinkManList  implements Serializable{
    /**
     * code : 200
     * msg : 成功
     * method : getFriendList
     * record : {"friendList":[{"type":1,"groupName":"同事","groupList":[{"userId":"9a36-9ec1-412","groupId":"3","nickName":"888","status":"1","shieldType":"1","wxSno":"*","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png","mobile":"*","groupName":"同事","isSnoShow":"0","isMobileShow":"0"}]},{"type":2,"groupName":"~","groupList":[{"userId":"9a36-9ec1-412","groupId":"3","nickName":"888","status":"1","shieldType":"1","wxSno":"*","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png","mobile":"*","groupName":"同事","isSnoShow":"0","isMobileShow":"0","chart":"~"}]}]}
     * api_key : 20180903
     * sign : BC9AF0278F88E2574E8DCBE9220727FF
     * timestamp : 1545200738
     * only : 2
     * verificationMD5Type : 1
     */

    private int code;
    private String msg;
    private String method;
    private RecordBean record;
    private String api_key;
    private String sign;
    private int timestamp;
    private int only;
    private String verificationMD5Type;

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

    public String getVerificationMD5Type() {
        return verificationMD5Type;
    }

    public void setVerificationMD5Type(String verificationMD5Type) {
        this.verificationMD5Type = verificationMD5Type;
    }

    public static class RecordBean {
        private List<FriendListBean> friendList;

        public List<FriendListBean> getFriendList() {
            return friendList;
        }

        public void setFriendList(List<FriendListBean> friendList) {
            this.friendList = friendList;
        }

        public static class FriendListBean {
            /**
             * type : 1
             * groupName : 同事
             * groupList : [{"userId":"9a36-9ec1-412","groupId":"3","nickName":"888","status":"1","shieldType":"1","wxSno":"*","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png","mobile":"*","groupName":"同事","isSnoShow":"0","isMobileShow":"0"}]
             */

            private String type;
            private String groupName;
            private List<GroupListBean> groupList;

            public String getType() {
                return type;
            }

            public void setType(String type) {
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
                 * userId : 9a36-9ec1-412
                 * groupId : 3
                 * nickName : 888
                 * status : 1
                 * shieldType : 1
                 * wxSno : *
                 * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154502986970112.png
                 * mobile : *
                 * groupName : 同事
                 * isSnoShow : 0
                 * isMobileShow : 0
                 */

                private String userId;
                private String groupId;
                private String nickName;
                private String remarkName;
                private String status;
                private String shieldType;
                private String wxSno;
                private String headImg;
                private String mobile;
                private String groupName;
                private String isSnoShow;
                private String isMobileShow;
                private String modified;

                public String getRemarkName() {
                    return remarkName;
                }

                public void setRemarkName(String remarkName) {
                    this.remarkName = remarkName;
                }

                public String getModified() {
                    return modified;
                }

                public void setModified(String modified) {
                    this.modified = modified;
                }

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

                public String getNickName() {
                    return nickName;
                }

                public void setNickName(String nickName) {
                    this.nickName = nickName;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getShieldType() {
                    return shieldType;
                }

                public void setShieldType(String shieldType) {
                    this.shieldType = shieldType;
                }

                public String getWxSno() {
                    return wxSno;
                }

                public void setWxSno(String wxSno) {
                    this.wxSno = wxSno;
                }

                public String getHeadImg() {
                    return headImg;
                }

                public void setHeadImg(String headImg) {
                    this.headImg = headImg;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getGroupName() {
                    return groupName;
                }

                public void setGroupName(String groupName) {
                    this.groupName = groupName;
                }

                public String getIsSnoShow() {
                    return isSnoShow;
                }

                public void setIsSnoShow(String isSnoShow) {
                    this.isSnoShow = isSnoShow;
                }

                public String getIsMobileShow() {
                    return isMobileShow;
                }

                public void setIsMobileShow(String isMobileShow) {
                    this.isMobileShow = isMobileShow;
                }
            }
        }
    }


//    /**
//     * code : 200
//     * msg : 成功
//     * method : getFriendList
//     * record : {"friend_list":[{"type":2,"group_name":"H","group_list":[{"user_id":"cb16ce45-854c-f553-9c9a-2e57a6addca3","mobile":"18150960007","wx_sno":"mMyV95B4849406105b969d29e3ebf2.13962996","nick_name":"海大胖","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","group_id":"3","group_name":"同学","chart":"H"},{"user_id":"cb16ce45-854c-f553-9c9a-2e57a6addca3","mobile":"18150960007","wx_sno":"mMyV95B4849406105b969d29e3ebf2.13962996","nick_name":"海大胖","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","group_id":"3","group_name":"同学","chart":"H"}]},{"type":2,"group_name":"X","group_list":[{"user_id":"1","mobile":"15960525627","wx_sno":"b6cb0d083b77b4d0aaccdee788821425","nick_name":"小强","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","group_id":"1","group_name":"好友","chart":"X"},{"user_id":"2","mobile":"15960525628","wx_sno":"b6cb0d083b77b4d0aac12435678678","nick_name":"小强","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","group_id":"1","group_name":"好友","chart":"X"}]},{"type":1,"group_name":"好友","group_list":[{"user_id":"1","mobile":"15960525627","wx_sno":"b6cb0d083b77b4d0aaccdee788821425","nick_name":"小强","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","group_id":"1","group_name":"好友"},{"user_id":"2","mobile":"15960525628","wx_sno":"b6cb0d083b77b4d0aac12435678678","nick_name":"小强","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","group_id":"1","group_name":"好友"}]},{"type":1,"group_name":"同学","group_list":[{"user_id":"cb16ce45-854c-f553-9c9a-2e57a6addca3","mobile":"18150960007","wx_sno":"mMyV95B4849406105b969d29e3ebf2.13962996","nick_name":"海大胖","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","group_id":"3","group_name":"同学"}]},{"type":1,"group_name":"家人","group_list":[{"user_id":"e0816099-d1d3-b20d-e114-fd56a192cc20","mobile":"18065283780","wx_sno":"b6cb0d083b77b4d0aaccdee78883a06e","nick_name":"海大胖","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","group_id":"2","group_name":"家人"}]}]}
//     * api_key : 20180903
//     * sign : 6A40B444A41F53A3F2F30CC4BFD83372
//     * timestamp : 1537894058
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
//    public static class RecordBean  implements Serializable{
//        private List<FriendListBean> friendList;
//
//        public List<FriendListBean> getFriendList() {
//            return friendList;
//        }
//
//        public void setFriendList(List<FriendListBean> friendList) {
//            this.friendList = friendList;
//        }
//
//        public static class FriendListBean implements Serializable  {
//            /**
//             * type : 2
//             * group_name : H
//             * group_list : [{"user_id":"cb16ce45-854c-f553-9c9a-2e57a6addca3","mobile":"18150960007","wx_sno":"mMyV95B4849406105b969d29e3ebf2.13962996","nick_name":"海大胖","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","group_id":"3","group_name":"同学","chart":"H"},{"user_id":"cb16ce45-854c-f553-9c9a-2e57a6addca3","mobile":"18150960007","wx_sno":"mMyV95B4849406105b969d29e3ebf2.13962996","nick_name":"海大胖","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","group_id":"3","group_name":"同学","chart":"H"}]
//             */
//
//            private String type;
//            private String groupName;
//            private List<GroupListBean> groupList;
//
//            public String getType() {
//                return type;
//            }
//
//            public void setType(String type) {
//                this.type = type;
//            }
//
//            public String getGroupName() {
//                return groupName;
//            }
//
//            public void setGroupName(String groupName) {
//                this.groupName = groupName;
//            }
//
//            public List<GroupListBean> getGroupList() {
//                return groupList;
//            }
//
//            public void setGroupList(List<GroupListBean> groupList) {
//                this.groupList = groupList;
//            }
//
//            public static class GroupListBean implements Serializable {
//                /**
//                 * user_id : cb16ce45-854c-f553-9c9a-2e57a6addca3
//                 * mobile : 18150960007
//                 * wx_sno : mMyV95B4849406105b969d29e3ebf2.13962996
//                 * nick_name : 海大胖
//                 * head_img : http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132
//                 * group_id : 3
//                 * group_name : 同学
//                 * chart : H
//                 */
//
//                private String userId;
//                private String mobile;
//                private String wxSno;
//                private String nickName;
//                private String headImg;
//                private String groupId;
//                private String groupName;
//                private String chart;
//
//                public String getUserId() {
//                    return userId;
//                }
//
//                public void setUserId(String userId) {
//                    this.userId = userId;
//                }
//
//                public String getMobile() {
//                    return mobile;
//                }
//
//                public void setMobile(String mobile) {
//                    this.mobile = mobile;
//                }
//
//                public String getWxSno() {
//                    return wxSno;
//                }
//
//                public void setWxSno(String wxSno) {
//                    this.wxSno = wxSno;
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
//                public String getHeadImg() {
//                    return headImg;
//                }
//
//                public void setHeadImg(String headImg) {
//                    this.headImg = headImg;
//                }
//
//                public String getGroupId() {
//                    return groupId;
//                }
//
//                public void setGroupId(String groupId) {
//                    this.groupId = groupId;
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
//                public String getChart() {
//                    return chart;
//                }
//
//                public void setChart(String chart) {
//                    this.chart = chart;
//                }
//            }
//        }
//    }
}
