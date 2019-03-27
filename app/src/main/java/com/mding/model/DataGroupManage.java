package com.mding.model;

import java.io.Serializable;
import java.util.List;

public class DataGroupManage implements Serializable {

    /**
     * code : 200
     * msg : 成功
     * method : groupManageInfo
     {"code":200,"msg":"成功","method":"groupManageInfo","record":{"groupInfo":[{"id":"11","groupName":"吃饭","sortId":"1"},{"id":"1","groupName":"好友","sortId":"2"},{"id":"7","groupName":"我的女主","sortId":"3"},{"id":"2","groupName":"家人","sortId":"4"},{"id":"10","groupName":"问道222","sortId":"5"},{"id":"3","groupName":"同学","sortId":"6"},{"id":"6","groupName":"我游戏群","sortId":"7"}]},"api_key":"20180903","sign":"B4D28310B4B96B9DFE184D692D1A2C6F","timestamp":1539657953}
     * record : {"group_info":[{"id":"1","group_name":"好友","sort_id":"1"},{"id":"2","group_name":"家人","sort_id":"2"},{"id":"3","group_name":"同学","sort_id":"3"},{"id":"6","group_name":"我游戏群","sort_id":"4"},{"id":"7","group_name":"我的女友群","sort_id":"5"},{"id":"8","group_name":"LOL群","sort_id":"6"},{"id":"9","group_name":"问道1","sort_id":"7"},{"id":"10","group_name":"问道222","sort_id":"8"}]}
     * api_key : 20180903
     * sign : AA53DF7347FCC03D377A54330C231BD2
     * timestamp : 1538961855
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

    public static class RecordBean implements Serializable  {
        private List<GroupInfoBean> groupInfo;

        public List<GroupInfoBean> getGroupInfo() {
            return groupInfo;
        }

        public void setGroupInfo(List<GroupInfoBean> groupInfo) {
            this.groupInfo = groupInfo;
        }

        public static class GroupInfoBean  implements Serializable {
            /**
             * id : 1
             * group_name : 好友
             * sort_id : 1
             */

            private String id;
            private String groupName;
            private String sortId;
            private String disabledStatus;

            public String getDisabledStatus() {
                return disabledStatus;
            }

            public void setDisabledStatus(String disabledStatus) {
                this.disabledStatus = disabledStatus;
            }

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

            public String getSortId() {
                return sortId;
            }

            public void setSortId(String sortId) {
                this.sortId = sortId;
            }
        }
    }
}
