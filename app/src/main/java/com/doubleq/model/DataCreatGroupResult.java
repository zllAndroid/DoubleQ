package com.doubleq.model;

public class DataCreatGroupResult {

    /**
     * code : 200
     * msg : 成功
     * method : createdUserGroup
     * record : {"groupOfId":"1","groupFenzuId":"12","groupNickName":"群群","groupHeadImg":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","groupSno":"1v4d561g4d5a61","groupQrcode":"http://www.xm6leefun.cn:40005/default/img/ec33b5a954853e1305acbddb2532774e.png"}
     * api_key : 20180903
     * sign : 20F6401E628FA6ACFE765048286C7793
     * timestamp : 1542814975
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
         * groupOfId : 1
         * groupFenzuId : 12
         * groupNickName : 群群
         * groupHeadImg : http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132
         * groupSno : 1v4d561g4d5a61
         * groupQrcode : http://www.xm6leefun.cn:40005/default/img/ec33b5a954853e1305acbddb2532774e.png
         */

        private String groupOfId;
        private String groupFenzuId;
        private String groupNickName;
        private String groupHeadImg;
        private String groupSno;
        private String groupQrcode;

        public String getGroupOfId() {
            return groupOfId;
        }

        public void setGroupOfId(String groupOfId) {
            this.groupOfId = groupOfId;
        }

        public String getGroupFenzuId() {
            return groupFenzuId;
        }

        public void setGroupFenzuId(String groupFenzuId) {
            this.groupFenzuId = groupFenzuId;
        }

        public String getGroupNickName() {
            return groupNickName;
        }

        public void setGroupNickName(String groupNickName) {
            this.groupNickName = groupNickName;
        }

        public String getGroupHeadImg() {
            return groupHeadImg;
        }

        public void setGroupHeadImg(String groupHeadImg) {
            this.groupHeadImg = groupHeadImg;
        }

        public String getGroupSno() {
            return groupSno;
        }

        public void setGroupSno(String groupSno) {
            this.groupSno = groupSno;
        }

        public String getGroupQrcode() {
            return groupQrcode;
        }

        public void setGroupQrcode(String groupQrcode) {
            this.groupQrcode = groupQrcode;
        }
    }
}
