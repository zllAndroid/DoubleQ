package com.mding.model;

import java.util.List;

public class DataModifyGroupingSort {


    /**
     * code : 200
     * msg : 成功
     * method : modifyGroupingSortSend
     * record : {"listType":9,"newGroup":[{"userId":"f9fa-f5dd-ab2","groupId":"88","groupName":"aaa","sortId":"1","disabledStatus":"2","modified":"0","type":"1"},{"userId":"f9fa-f5dd-ab2","groupId":"89","groupName":"bbb","sortId":"2","disabledStatus":"2","modified":"0","type":"1"},{"userId":"f9fa-f5dd-ab2","groupId":"91","groupName":"mmm","sortId":"3","disabledStatus":"2","modified":"1548990621","type":"1"},{"userId":"f9fa-f5dd-ab2","groupId":"41","groupName":"6leefun","sortId":"4","disabledStatus":"2","modified":"0","type":"2"},{"userId":"f9fa-f5dd-ab2","groupId":"78","groupName":"qqq","sortId":"5","disabledStatus":"2","modified":"0","type":"2"},{"userId":"f9fa-f5dd-ab2","groupId":"87","groupName":"www","sortId":"6","disabledStatus":"2","modified":"1548833397","type":"2"}],"oldGroup":[{"userId":"f9fa-f5dd-ab2","groupId":"88","groupName":"aaa","sortId":"1","disabledStatus":"2","modified":"0","type":"1"},{"userId":"f9fa-f5dd-ab2","groupId":"89","groupName":"bbb","sortId":"2","disabledStatus":"2","modified":"0","type":"1"},{"userId":"f9fa-f5dd-ab2","groupId":"91","groupName":"mmm","sortId":"3","disabledStatus":"2","modified":"1548990621","type":"1"},{"userId":"f9fa-f5dd-ab2","groupId":"41","groupName":"6leefun","sortId":"4","disabledStatus":"2","modified":"0","type":"2"},{"userId":"f9fa-f5dd-ab2","groupId":"78","groupName":"qqq","sortId":"5","disabledStatus":"2","modified":"0","type":"2"},{"userId":"f9fa-f5dd-ab2","groupId":"87","groupName":"www","sortId":"6","disabledStatus":"2","modified":"1548833397","type":"2"}],"userId":"f9fa-f5dd-ab2"}
     * api_key : 20180903
     * sign : B3AECBA74E572E5A3E2AF44EFC91B301
     * timestamp : 1550068179
     * only : 2
     */

    private int code;
    private String msg;
    private String method;
    private RecordBean record;
    private String api_key;
    private String sign;
    private int timestamp;
    private int only;

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

    public static class RecordBean {
        /**
         * listType : 9
         * newGroup : [{"userId":"f9fa-f5dd-ab2","groupId":"88","groupName":"aaa","sortId":"1","disabledStatus":"2","modified":"0","type":"1"},{"userId":"f9fa-f5dd-ab2","groupId":"89","groupName":"bbb","sortId":"2","disabledStatus":"2","modified":"0","type":"1"},{"userId":"f9fa-f5dd-ab2","groupId":"91","groupName":"mmm","sortId":"3","disabledStatus":"2","modified":"1548990621","type":"1"},{"userId":"f9fa-f5dd-ab2","groupId":"41","groupName":"6leefun","sortId":"4","disabledStatus":"2","modified":"0","type":"2"},{"userId":"f9fa-f5dd-ab2","groupId":"78","groupName":"qqq","sortId":"5","disabledStatus":"2","modified":"0","type":"2"},{"userId":"f9fa-f5dd-ab2","groupId":"87","groupName":"www","sortId":"6","disabledStatus":"2","modified":"1548833397","type":"2"}]
         * oldGroup : [{"userId":"f9fa-f5dd-ab2","groupId":"88","groupName":"aaa","sortId":"1","disabledStatus":"2","modified":"0","type":"1"},{"userId":"f9fa-f5dd-ab2","groupId":"89","groupName":"bbb","sortId":"2","disabledStatus":"2","modified":"0","type":"1"},{"userId":"f9fa-f5dd-ab2","groupId":"91","groupName":"mmm","sortId":"3","disabledStatus":"2","modified":"1548990621","type":"1"},{"userId":"f9fa-f5dd-ab2","groupId":"41","groupName":"6leefun","sortId":"4","disabledStatus":"2","modified":"0","type":"2"},{"userId":"f9fa-f5dd-ab2","groupId":"78","groupName":"qqq","sortId":"5","disabledStatus":"2","modified":"0","type":"2"},{"userId":"f9fa-f5dd-ab2","groupId":"87","groupName":"www","sortId":"6","disabledStatus":"2","modified":"1548833397","type":"2"}]
         * userId : f9fa-f5dd-ab2
         */

        private int listType;
        private String userId;
        private List<NewGroupBean> newGroup;
        private List<OldGroupBean> oldGroup;

        public int getListType() {
            return listType;
        }

        public void setListType(int listType) {
            this.listType = listType;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public List<NewGroupBean> getNewGroup() {
            return newGroup;
        }

        public void setNewGroup(List<NewGroupBean> newGroup) {
            this.newGroup = newGroup;
        }

        public List<OldGroupBean> getOldGroup() {
            return oldGroup;
        }

        public void setOldGroup(List<OldGroupBean> oldGroup) {
            this.oldGroup = oldGroup;
        }

        public static class NewGroupBean {
            /**
             * userId : f9fa-f5dd-ab2
             * groupId : 88
             * groupName : aaa
             * sortId : 1
             * disabledStatus : 2
             * modified : 0
             * type : 1
             */

            private String userId;
            private String groupId;
            private String groupName;
            private String sortId;
            private String disabledStatus;
            private String modified;
            private String type;

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

            public String getDisabledStatus() {
                return disabledStatus;
            }

            public void setDisabledStatus(String disabledStatus) {
                this.disabledStatus = disabledStatus;
            }

            public String getModified() {
                return modified;
            }

            public void setModified(String modified) {
                this.modified = modified;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class OldGroupBean {
            /**
             * userId : f9fa-f5dd-ab2
             * groupId : 88
             * groupName : aaa
             * sortId : 1
             * disabledStatus : 2
             * modified : 0
             * type : 1
             */

            private String userId;
            private String groupId;
            private String groupName;
            private String sortId;
            private String disabledStatus;
            private String modified;
            private String type;

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

            public String getDisabledStatus() {
                return disabledStatus;
            }

            public void setDisabledStatus(String disabledStatus) {
                this.disabledStatus = disabledStatus;
            }

            public String getModified() {
                return modified;
            }

            public void setModified(String modified) {
                this.modified = modified;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
