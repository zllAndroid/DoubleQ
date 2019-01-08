package com.doubleq.model;

import java.io.Serializable;
import java.util.List;

public class DataHomeMsg  implements Serializable {


    /**
     * code : 200
     * msg : 成功
     * method : getUserRelation
     * record : {"friendList":[{"type":1,"friendId":"1","nickName":"小强","headImg":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132"},{"type":1,"friendId":"2","nickName":"小强","headImg":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132"},{"type":1,"friendId":"2f9c14e8-ba94-8a7e-9cc9-4260e14f8ffe","nickName":"海大胖","headImg":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132"},{"type":1,"friendId":"cb16ce45-854c-f553-9c9a-2e57a6addca3","nickName":"kkk","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/153969463549809.png"},{"type":1,"friendId":"e0816099-d1d3-b20d-e114-fd56a192cc20","nickName":"海大胖","headImg":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132"},{"type":2,"friendId":"1","nickName":"这是一个新的聊天群","headImg":"http://pic.sogou.com/d?query=é»\u0098è®¤å¤´å\u0083\u008f&ie=utf8&page=1&did=4&st=255&mode=255&phu=http://img.zcool.cn/community/016a1955ed02cc32f875a13291fb8b.png&p=40230500#did3"},{"type":2,"friendId":"2","nickName":"这是交友群","headImg":"http://pic.sogou.com/d?query=é»\u0098è®¤å¤´å\u0083\u008f&ie=utf8&page=1&did=4&st=255&mode=255&phu=http://img.zcool.cn/community/016a1955ed02cc32f875a13291fb8b.png&p=40230500#did3"},{"type":2,"friendId":"3","nickName":"群啊","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/153976378866601.png"},{"type":2,"friendId":"13","nickName":"这是一个新的聊天群","headImg":"http://pic.sogou.com/d?query=é»\u0098è®¤å¤´å\u0083\u008f&ie=utf8&page=1&did=4&st=255&mode=255&phu=http://img.zcool.cn/community/016a1955ed02cc32f875a13291fb8b.png&p=40230500#did3"},{"type":2,"friendId":"14","nickName":"ggios","headImg":"http://pic.sogou.com/d?query=é»\u0098è®¤å¤´å\u0083\u008f&ie=utf8&page=1&did=4&st=255&mode=255&phu=http://img.zcool.cn/community/016a1955ed02cc32f875a13291fb8b.png&p=40230500#did3"},{"type":2,"friendId":"15","nickName":"讨论帅哥","headImg":"http://pic.sogou.com/d?query=é»\u0098è®¤å¤´å\u0083\u008f&ie=utf8&page=1&did=4&st=255&mode=255&phu=http://img.zcool.cn/community/016a1955ed02cc32f875a13291fb8b.png&p=40230500#did3"}]}
     * api_key : 20180903
     * sign : 30CFF2C7B5C4F6F38077E31CBDEC77DA
     * timestamp : 1540274083
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

    public static class RecordBean   implements Serializable{
        private List<FriendListBean> friendList;

        public List<FriendListBean> getFriendList() {
            return friendList;
        }

        public void setFriendList(List<FriendListBean> friendList) {
            this.friendList = friendList;
        }

        public static class FriendListBean implements Serializable {
            /**
             * type : 1
             * friendId : 1
             * nickName : 小强
             * headImg : http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132
             */

            private String type;
            private String friendId;
            private String nickName;
            private String headImg;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getFriendId() {
                return friendId;
            }

            public void setFriendId(String friendId) {
                this.friendId = friendId;
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
