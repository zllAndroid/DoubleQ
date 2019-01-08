package com.doubleq.model;

import java.util.List;

public class DataHomeMsgNew {
    /**
     * code : 200
     * msg : 成功
     * method : messageQueue
     * record : {"friendList":[{"type":1,"friendId":"081b8de1-c974-c5f4-f918-e8f9a20fca1c","nickName":"123123","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154141056790163.png","messageStoId":"163","message":"🤣😋☺😚😚😊","messageType":"1","userMessageType":"2","created":"1541472242","isMessage":1},{"type":1,"friendId":"2644b163-b898-ba84-a7be-69cb62f214c7","nickName":"小强子","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154113866221225.png","messageStoId":"2","message":"(⊙o⊙)哦","messageType":"1","userMessageType":"2","created":"1541404185","isMessage":1},{"type":1,"friendId":"37e80787-0e5e-75f3-955c-e759d6327349","nickName":"匿名用户","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154141021868364.png","messageStoId":"58","message":"ff","messageType":"1","userMessageType":"2","created":"1541406943","isMessage":1},{"type":1,"friendId":"6e7baf54-2d23-eb5b-977b-9b5ae0341bb6","nickName":"脏兮兮","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154114379289196.png","messageStoId":"","message":"","messageType":"","userMessageType":"","created":"","isMessage":2},{"type":1,"friendId":"dc81f581-657f-1f9f-bf48-d67dcc0cc0b6","nickName":"密密酱","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154114389417655.png","messageStoId":"64","message":"龙","messageType":"1","userMessageType":"1","created":"1541409357","isMessage":1},{"type":2,"friendId":"3","nickName":"你好","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154141174145147.png","messageStoId":"","message":"","messageType":"","userMessageType":"","created":"","isMessage":2},{"type":2,"friendId":"4","nickName":"2018","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154141182197080.png","messageStoId":"","message":"","messageType":"","userMessageType":"","created":"","isMessage":2},{"type":2,"friendId":"5","nickName":"2018","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154141195351430.png","messageStoId":"","message":"","messageType":"","userMessageType":"","created":"","isMessage":2},{"type":2,"friendId":"6","nickName":"这是一个新的聊天群","headImg":"http://pic.sogou.com/d?query=é»\u0098è®¤å¤´å\u0083\u008f&ie=utf8&page=1&did=4&st=255&mode=255&phu=http://img.zcool.cn/community/016a1955ed02cc32f875a13291fb8b.png&p=40230500#did3","messageStoId":"","message":"","messageType":"","userMessageType":"","created":"","isMessage":2},{"type":2,"friendId":"16","nickName":"这是一个新的聊天群","headImg":"http://pic.sogou.com/d?query=é»\u0098è®¤å¤´å\u0083\u008f&ie=utf8&page=1&did=4&st=255&mode=255&phu=http://img.zcool.cn/community/016a1955ed02cc32f875a13291fb8b.png&p=40230500#did3","messageStoId":"","message":"","messageType":"","userMessageType":"","created":"","isMessage":2}]}
     * api_key : 20180903
     * sign : F0D2C9E41D43F734C90BDEC62241F6CF
     * timestamp : 1541494109
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
             * type : 1
             * friendId : 081b8de1-c974-c5f4-f918-e8f9a20fca1c
             * nickName : 123123
             * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154141056790163.png
             * messageStoId : 163
             * message : 🤣😋☺😚😚😊
             * messageType : 1
             * userMessageType : 2
             * created : 1541472242
             * isMessage : 1
             */

            private String type;
            private String friendId;
            private String nickName;
            private String headImg;
            private String messageStoId;
            private String message;
            private String messageType;
            private String userMessageType;
            private String created;
            private String isMessage;

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

            public String getMessageStoId() {
                return messageStoId;
            }

            public void setMessageStoId(String messageStoId) {
                this.messageStoId = messageStoId;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getMessageType() {
                return messageType;
            }

            public void setMessageType(String messageType) {
                this.messageType = messageType;
            }

            public String getUserMessageType() {
                return userMessageType;
            }

            public void setUserMessageType(String userMessageType) {
                this.userMessageType = userMessageType;
            }

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public String getIsMessage() {
                return isMessage;
            }

            public void setIsMessage(String isMessage) {
                this.isMessage = isMessage;
            }
        }
    }
}
