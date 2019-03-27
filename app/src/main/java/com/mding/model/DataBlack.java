package com.mding.model;

import java.util.List;

public class DataBlack {
    /**
     * code : 200
     * msg : 成功
     * method : blackList
     * record : [{"userId":"2f9c14e8-ba94-8a7e-9cc9-4260e14f8ffe","headImg":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","nickName":"海大胖"},{"userId":"cb16ce45-854c-f553-9c9a-2e57a6addca3","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/153820048090418.jpg","nickName":"kkk"}]
     * api_key : 20180903
     * sign : E4BE8682A5DB63D49FC08498499BB135
     * timestamp : 1538987950
     */

    private int code;
    private String msg;
    private String method;
    private String api_key;
    private String sign;
    private int timestamp;
    private List<RecordBean> record;

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

    public List<RecordBean> getRecord() {
        return record;
    }

    public void setRecord(List<RecordBean> record) {
        this.record = record;
    }

    public static class RecordBean {
        /**
         * userId : 2f9c14e8-ba94-8a7e-9cc9-4260e14f8ffe
         * headImg : http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132
         * nickName : 海大胖
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


    /**
     * code : 200
     * msg : 成功
     * method : blackList
     * record : [{"user_id":"1","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","nick_name":"小强"},{"user_id":"2","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","nick_name":"小强"},{"user_id":"cb16ce45-854c-f553-9c9a-2e57a6addca3","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","nick_name":""},{"user_id":"e0816099-d1d3-b20d-e114-fd56a192cc20","head_img":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","nick_name":""}]
     * api_key : 20180903
     * sign : D0E1AFB156F16B12F468BF73510CC3CE
     * timestamp : 1537195602
     */

//    private int code;
//    private String msg;
//    private String method;
//    private String api_key;
//    private String sign;
//    private int timestamp;
//    private List<RecordBean> record;
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
//    public List<RecordBean> getRecord() {
//        return record;
//    }
//
//    public void setRecord(List<RecordBean> record) {
//        this.record = record;
//    }
//
//    public static class RecordBean {
//        /**
//         * user_id : 1
//         * head_img : http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132
//         * nick_name : 小强
//         */
//
//        private String user_id;
//        private String head_img;
//        private String nick_name;
//
//        public String getUser_id() {
//            return user_id;
//        }
//
//        public void setUser_id(String user_id) {
//            this.user_id = user_id;
//        }
//
//        public String getHead_img() {
//            return head_img;
//        }
//
//        public void setHead_img(String head_img) {
//            this.head_img = head_img;
//        }
//
//        public String getNick_name() {
//            return nick_name;
//        }
//
//        public void setNick_name(String nick_name) {
//            this.nick_name = nick_name;
//        }
//    }
}
