package com.doubleq.model;

import java.util.List;

public class DataLinkGroupList {

    /**
     * code : 200
     * msg : 成功
     * method : getGroupManage
     * record : {"groupInfoList":[{"type":1,"groupName":"我创建的群","groupList":[{"groupOfId":"1","groupFenzuId":"9","nickName":"1008","headImg":"http://pic.sogou.com/d?query=é»\u0098è®¤å¤´å\u0083\u008f&ie=utf8&page=1&did=4&st=255&mode=255&phu=http://img.zcool.cn/community/016a1955ed02cc32f875a13291fb8b.png&p=40230500#did3","groupQrcode":"0","groupSno":"1542177824","groupName":"我创建的群"},{"groupOfId":"2","groupFenzuId":"9","nickName":"哥哥哥","headImg":"http://pic.sogou.com/d?query=é»\u0098è®¤å¤´å\u0083\u008f&ie=utf8&page=1&did=4&st=255&mode=255&phu=http://img.zcool.cn/community/016a1955ed02cc32f875a13291fb8b.png&p=40230500#did3","groupQrcode":"0","groupSno":"1542177943","groupName":"我创建的群"}]},{"type":1,"groupName":"我加入的群","groupList":[{"groupOfId":""}]},{"type":2,"groupName":"#","groupList":[{"groupOfId":"1","groupFenzuId":"9","nickName":"1008","headImg":"http://pic.sogou.com/d?query=é»\u0098è®¤å¤´å\u0083\u008f&ie=utf8&page=1&did=4&st=255&mode=255&phu=http://img.zcool.cn/community/016a1955ed02cc32f875a13291fb8b.png&p=40230500#did3","groupQrcode":"0","groupSno":"1542177824","groupName":"我创建的群","chart":"#"}]},{"type":2,"groupName":"G","groupList":[{"groupOfId":"2","groupFenzuId":"9","nickName":"哥哥哥","headImg":"http://pic.sogou.com/d?query=é»\u0098è®¤å¤´å\u0083\u008f&ie=utf8&page=1&did=4&st=255&mode=255&phu=http://img.zcool.cn/community/016a1955ed02cc32f875a13291fb8b.png&p=40230500#did3","groupQrcode":"0","groupSno":"1542177943","groupName":"我创建的群","chart":"G"}]}]}
     * api_key : 20180903
     * sign : 1A896250DEE45FB4D4CB9C55AE91948F
     * timestamp : 1542179861
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
        private List<GroupInfoListBean> groupInfoList;

        public List<GroupInfoListBean> getGroupInfoList() {
            return groupInfoList;
        }

        public void setGroupInfoList(List<GroupInfoListBean> groupInfoList) {
            this.groupInfoList = groupInfoList;
        }

        public static class GroupInfoListBean {
            /**
             * type : 1
             * groupName : 我创建的群
             * groupList : [{"groupOfId":"1","groupFenzuId":"9","nickName":"1008","headImg":"http://pic.sogou.com/d?query=é»\u0098è®¤å¤´å\u0083\u008f&ie=utf8&page=1&did=4&st=255&mode=255&phu=http://img.zcool.cn/community/016a1955ed02cc32f875a13291fb8b.png&p=40230500#did3","groupQrcode":"0","groupSno":"1542177824","groupName":"我创建的群"},{"groupOfId":"2","groupFenzuId":"9","nickName":"哥哥哥","headImg":"http://pic.sogou.com/d?query=é»\u0098è®¤å¤´å\u0083\u008f&ie=utf8&page=1&did=4&st=255&mode=255&phu=http://img.zcool.cn/community/016a1955ed02cc32f875a13291fb8b.png&p=40230500#did3","groupQrcode":"0","groupSno":"1542177943","groupName":"我创建的群"}]
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
                 * groupOfId : 1
                 * groupFenzuId : 9
                 * nickName : 1008
                 * headImg : http://pic.sogou.com/d?query=é»è®¤å¤´å&ie=utf8&page=1&did=4&st=255&mode=255&phu=http://img.zcool.cn/community/016a1955ed02cc32f875a13291fb8b.png&p=40230500#did3
                 * groupQrcode : 0
                 * groupSno : 1542177824
                 * groupName : 我创建的群
                 */

                private String groupOfId;
                private String groupFenzuId;
                private String nickName;
                private String headImg;
                private String groupQrcode;
                private String groupSno;
                private String groupName;
                private String modified;

                public String getModified() {
                    return modified;
                }

                public void setModified(String modified) {
                    this.modified = modified;
                }

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

                public String getGroupQrcode() {
                    return groupQrcode;
                }

                public void setGroupQrcode(String groupQrcode) {
                    this.groupQrcode = groupQrcode;
                }

                public String getGroupSno() {
                    return groupSno;
                }

                public void setGroupSno(String groupSno) {
                    this.groupSno = groupSno;
                }

                public String getGroupName() {
                    return groupName;
                }

                public void setGroupName(String groupName) {
                    this.groupName = groupName;
                }
            }
        }
    }
}
