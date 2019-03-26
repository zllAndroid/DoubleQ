package com.mding.model;

import java.util.List;

public class DataChatHisList {
    /**
     * code : 200
     * msg : 成功
     * method : messageObtain
     * record : {"messageList":[{"messageStoId":"13","sendId":"cb16ce45-854c-f553-9c9a-2e57a6addca3","receiveId":"3","message":"123","messageType":"1","userMessageType":"1","created":"1540263783"},{"messageStoId":"12","sendId":"cb16ce45-854c-f553-9c9a-2e57a6addca3","receiveId":"3","message":"123","messageType":"1","userMessageType":"1","created":"1540189210"},{"messageStoId":"11","sendId":"cb16ce45-854c-f553-9c9a-2e57a6addca3","receiveId":"3","message":"123","messageType":"1","userMessageType":"1","created":"1540189173"},{"messageStoId":"10","sendId":"cb16ce45-854c-f553-9c9a-2e57a6addca3","receiveId":"3","message":"123","messageType":"1","userMessageType":"1","created":"1540189115"},{"messageStoId":"9","sendId":"cb16ce45-854c-f553-9c9a-2e57a6addca3","receiveId":"3","message":"123","messageType":"1","userMessageType":"1","created":"1540189010"},{"messageStoId":"8","sendId":"cb16ce45-854c-f553-9c9a-2e57a6addca3","receiveId":"3","message":"123","messageType":"1","userMessageType":"1","created":"1540188950"},{"messageStoId":"7","sendId":"cb16ce45-854c-f553-9c9a-2e57a6addca3","receiveId":"3","message":"123","messageType":"1","userMessageType":"1","created":"1540188849"},{"messageStoId":"6","sendId":"cb16ce45-854c-f553-9c9a-2e57a6addca3","receiveId":"3","message":"123","messageType":"1","userMessageType":"1","created":"1540188795"},{"messageStoId":"5","sendId":"cb16ce45-854c-f553-9c9a-2e57a6addca3","receiveId":"3","message":"123","messageType":"1","userMessageType":"1","created":"1540187578"},{"messageStoId":"4","sendId":"cb16ce45-854c-f553-9c9a-2e57a6addca3","receiveId":"3","message":"123","messageType":"1","userMessageType":"1","created":"1540174023"},{"messageStoId":"2","sendId":"cb16ce45-854c-f553-9c9a-2e57a6addca3","receiveId":"3","message":"123","messageType":"1","userMessageType":"1","created":"1539914834"},{"messageStoId":"3","sendId":"3","receiveId":"cb16ce45-854c-f553-9c9a-2e57a6addca3","message":"哈哈哈","messageType":"1","userMessageType":"2","created":"1539914834"},{"messageStoId":"1","sendId":"cb16ce45-854c-f553-9c9a-2e57a6addca3","receiveId":"3","message":"123","messageType":"1","userMessageType":"1","created":"1539914706"}]}
     * api_key : 20180903
     * sign : 121EABB5E86390B257668CE867CFCEBD
     * timestamp : 1540365126
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
             * messageStoId : 13
             * sendId : cb16ce45-854c-f553-9c9a-2e57a6addca3
             * receiveId : 3
             * message : 123
             * messageType : 1
             * userMessageType : 1
             * created : 1540263783
             */

            private String messageStoId;
            private String sendId;
            private String receiveId;
            private String message;
            private String messageType;
            private int userMessageType;
            private String created;

            public String getMessageStoId() {
                return messageStoId;
            }

            public void setMessageStoId(String messageStoId) {
                this.messageStoId = messageStoId;
            }

            public String getSendId() {
                return sendId;
            }

            public void setSendId(String sendId) {
                this.sendId = sendId;
            }

            public String getReceiveId() {
                return receiveId;
            }

            public void setReceiveId(String receiveId) {
                this.receiveId = receiveId;
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

            public int getUserMessageType() {
                return userMessageType;
            }

            public void setUserMessageType(int userMessageType) {
                this.userMessageType = userMessageType;
            }

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }
        }
    }
}
