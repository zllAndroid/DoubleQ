package com.doubleq.model.off_line_msg;

import java.util.List;

public class DataOffLineChat {


    /**
     * code : 200
     * msg : 成功
     * method : messagePush
     * record : {"messageList":[{"friendsId":"1bf0c95c-8f7e-54ce-f022-dc1cce71fdb3","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154155508679840.png","message":"4232","messageStoId":1542768063,"messageType":"1","nickName":"123123","requestTime":121312131,"shieldType":"2","userId":"07667cff-f153-dcc9-664c-d7b320136bef"}]}
     * api_key : 20180903
     * sign : 456F3B742124A3741AA83CF9EA9CA58B
     * timestamp : 1542768063
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
        private List<MessageListBean> messageList;

        public List<MessageListBean> getMessageList() {
            return messageList;
        }

        public void setMessageList(List<MessageListBean> messageList) {
            this.messageList = messageList;
        }

        public static class MessageListBean {
            /**
             * friendsId : 1bf0c95c-8f7e-54ce-f022-dc1cce71fdb3
             * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154155508679840.png
             * message : 4232
             * messageStoId : 1542768063
             * messageType : 1
             * nickName : 123123
             * requestTime : 121312131
             * shieldType : 2
             * userId : 07667cff-f153-dcc9-664c-d7b320136bef
             */

            private String friendsId;
            private String headImg;
            private String message;
            private int messageStoId;
            private String messageType;
            private String nickName;
            private String requestTime;
            private String shieldType;
            private String userId;


            //消息类别，在左还是右
            private int type;
            //            发送成功还是失败
            private int sendState;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getSendState() {
                return sendState;
            }

            public void setSendState(int sendState) {
                this.sendState = sendState;
            }

            public String getFriendsId() {
                return friendsId;
            }

            public void setFriendsId(String friendsId) {
                this.friendsId = friendsId;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public int getMessageStoId() {
                return messageStoId;
            }

            public void setMessageStoId(int messageStoId) {
                this.messageStoId = messageStoId;
            }

            public String getMessageType() {
                return messageType;
            }

            public void setMessageType(String messageType) {
                this.messageType = messageType;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getRequestTime() {
                return requestTime;
            }

            public void setRequestTime(String requestTime) {
                this.requestTime = requestTime;
            }

            public String getShieldType() {
                return shieldType;
            }

            public void setShieldType(String shieldType) {
                this.shieldType = shieldType;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }
        }
    }
}
