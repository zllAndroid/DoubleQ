package com.mding.model;

import java.io.Serializable;
import java.util.List;

public class DataNews implements Serializable {
    /**
     * code : 200
     * msg : 成功
     * method : messageList
     * record : {"listInfo":[{"id":"17","userId":"1b42-b9c2-c6f","nickName":"大小姐miss","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/head_img/154762091351654.png","isAgree":"0","sendUserId":"3dfc-dcb0-da3","pushType":"1","status":"1","remark":{"userId":"1b42-b9c2-c6f","friendsId":"3dfc-dcb0-da3","nickName":"赞赞赞赞赞赞赞赞赞赞","message":"1233"}}]}
     * api_key : 20180903
     * sign : A6214F82C85F7DDD4FCF04AE84084F56
     * timestamp : 1548951104
     * only : 1
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

    public static class RecordBean implements Serializable {
        private List<ListInfoBean> listInfo;

        public List<ListInfoBean> getListInfo() {
            return listInfo;
        }

        public void setListInfo(List<ListInfoBean> listInfo) {
            this.listInfo = listInfo;
        }

        public static class ListInfoBean implements Serializable{

            /**
             * id : 17
             * userId : 1b42-b9c2-c6f
             * nickName : 大小姐miss
             * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/head_img/154762091351654.png
             * isAgree : 0
             * sendUserId : 3dfc-dcb0-da3
             * pushType : 1
             * status : 1
             * remark : {"userId":"1b42-b9c2-c6f","friendsId":"3dfc-dcb0-da3","nickName":"赞赞赞赞赞赞赞赞赞赞","message":"1233"}
             */

            private String id;
            private String userId;
            private String nickName;
            private String headImg;
            private String isAgree;
            private String sendUserId;
            private String pushType;
            private String status;
            private RemarkBean remark;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
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

            public String getIsAgree() {
                return isAgree;
            }

            public void setIsAgree(String isAgree) {
                this.isAgree = isAgree;
            }

            public String getSendUserId() {
                return sendUserId;
            }

            public void setSendUserId(String sendUserId) {
                this.sendUserId = sendUserId;
            }

            public String getPushType() {
                return pushType;
            }

            public void setPushType(String pushType) {
                this.pushType = pushType;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public RemarkBean getRemark() {
                return remark;
            }

            public void setRemark(RemarkBean remark) {
                this.remark = remark;
            }

            public static class RemarkBean implements Serializable{
                /**
                 * userId : 1b42-b9c2-c6f
                 * friendsId : 3dfc-dcb0-da3
                 * nickName : 赞赞赞赞赞赞赞赞赞赞
                 * message : 1233
                 */

                private String userId;
                private String friendsId;
                private String nickName;
                private String message;

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getFriendsId() {
                    return friendsId;
                }

                public void setFriendsId(String friendsId) {
                    this.friendsId = friendsId;
                }

                public String getNickName() {
                    return nickName;
                }

                public void setNickName(String nickName) {
                    this.nickName = nickName;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }
            }
        }
    }

//
//    /**
//     * code : 200
//     * msg : 成功
//     * method : messageList
//     * record : {"listInfo":[{"nickName":"海大胖","headImg":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","isAgree":"已同意","userId":"3","sendUserId":"2f9c14e8-ba94-8a7e-9cc9-4260e14f8ffe","pushType":"1"},{"nickName":"海大胖","headImg":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","isAgree":0,"userId":"3","sendUserId":"5","pushType":"2"}]}
//     * api_key : 20180903
//     * sign : 1DF3A215B3E537B7D384FDD65C47666C
//     * timestamp : 1539076010
//     */
//
//    private int code;
//    private String msg;
//    private String method;
//    private RecordBean record;
//    private String api_key;
//    private String sign;
//    private int timestamp;
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
//    public RecordBean getRecord() {
//        return record;
//    }
//
//    public void setRecord(RecordBean record) {
//        this.record = record;
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
//    public static class RecordBean {
//        private List<ListInfoBean> listInfo;
//
//        public List<ListInfoBean> getListInfo() {
//            return listInfo;
//        }
//
//        public void setListInfo(List<ListInfoBean> listInfo) {
//            this.listInfo = listInfo;
//        }
//
//        public static class ListInfoBean   implements Serializable{
//            /**
//             * nickName : 海大胖
//             * headImg : http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132
//             * isAgree : 已同意
//             * userId : 3
//             * sendUserId : 2f9c14e8-ba94-8a7e-9cc9-4260e14f8ffe
//             * pushType : 1
//             */
//            private String id;
//            private String nickName;
//            private String headImg;
//
//            private String isAgree;
//            private String userId;
//            private String sendUserId;
//            private String pushType;
//
//            public String getId() {
//                return id;
//            }
//
//            public void setId(String id) {
//                this.id = id;
//            }
//
//            public String getNickName() {
//                return nickName;
//            }
//
//            public void setNickName(String nickName) {
//                this.nickName = nickName;
//            }
//
//            public String getHeadImg() {
//                return headImg;
//            }
//
//            public void setHeadImg(String headImg) {
//                this.headImg = headImg;
//            }
//
//            public String getIsAgree() {
//                return isAgree;
//            }
//
//            public void setIsAgree(String isAgree) {
//                this.isAgree = isAgree;
//            }
//
//            public String getUserId() {
//                return userId;
//            }
//
//            public void setUserId(String userId) {
//                this.userId = userId;
//            }
//
//            public String getSendUserId() {
//                return sendUserId;
//            }
//
//            public void setSendUserId(String sendUserId) {
//                this.sendUserId = sendUserId;
//            }
//
//            public String getPushType() {
//                return pushType;
//            }
//
//            public void setPushType(String pushType) {
//                this.pushType = pushType;
//            }
//        }
//    }
}
