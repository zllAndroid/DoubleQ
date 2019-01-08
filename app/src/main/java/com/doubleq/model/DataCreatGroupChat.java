package com.doubleq.model;

import java.util.List;

public class DataCreatGroupChat {
    /**
     * code : 200
     * msg : 成功
     * method : getGroupWebInfo
     * record : {"friend_list":[{"group_name":"H","group_list":[{"user_id":"2f9c14e8-ba94-8a7e-9cc9-4260e14f8ffe","mobile":"15960525637","wx_sno":"39f0bd9895eaffebd7a551a549d47411","nick_name":"海大胖","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","chart":"H"},{"user_id":"cb16ce45-854c-f553-9c9a-2e57a6addca3","mobile":"18150960007","wx_sno":"mMyV95B4849406105b969d29e3ebf2.13962996","nick_name":"海大胖","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","chart":"H"},{"user_id":"cb16ce45-854c-f553-9c9a-2e57a6addca3","mobile":"18150960007","wx_sno":"mMyV95B4849406105b969d29e3ebf2.13962996","nick_name":"海大胖","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","chart":"H"}]},{"group_name":"X","group_list":[{"user_id":"1","mobile":"15960525627","wx_sno":"b6cb0d083b77b4d0aaccdee788821425","nick_name":"小强","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","chart":"X"},{"user_id":"2","mobile":"15960525628","wx_sno":"b6cb0d083b77b4d0aac12435678678","nick_name":"小强","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","chart":"X"}]}]}
     * api_key : 20180903
     * sign : D33710DDA7D6E74BBB0F2A67FA14EADD
     * timestamp : 1538020858
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
        private List<FriendListBean> friendList;

        public List<FriendListBean> getFriendList() {
            return friendList;
        }

        public void setFriendList(List<FriendListBean> friendList) {
            this.friendList = friendList;
        }

        public static class FriendListBean {
            /**
             * group_name : H
             * group_list : [{"user_id":"2f9c14e8-ba94-8a7e-9cc9-4260e14f8ffe","mobile":"15960525637","wx_sno":"39f0bd9895eaffebd7a551a549d47411","nick_name":"海大胖","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","chart":"H"},{"user_id":"cb16ce45-854c-f553-9c9a-2e57a6addca3","mobile":"18150960007","wx_sno":"mMyV95B4849406105b969d29e3ebf2.13962996","nick_name":"海大胖","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","chart":"H"},{"user_id":"cb16ce45-854c-f553-9c9a-2e57a6addca3","mobile":"18150960007","wx_sno":"mMyV95B4849406105b969d29e3ebf2.13962996","nick_name":"海大胖","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","chart":"H"}]
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
                 * user_id : 2f9c14e8-ba94-8a7e-9cc9-4260e14f8ffe
                 * mobile : 15960525637
                 * wx_sno : 39f0bd9895eaffebd7a551a549d47411
                 * nick_name : 海大胖
                 * head_img : http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132
                 * chart : H
                 */

                private String userId;
                private String mobile;
                private String wxSno;
                private String nickName;
                private String headImg;
                private String chart;

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getWxSno() {
                    return wxSno;
                }

                public void setWxSno(String wxSno) {
                    this.wxSno = wxSno;
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
