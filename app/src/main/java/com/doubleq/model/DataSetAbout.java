package com.doubleq.model;

/**
 *查询状态接口(分享设置相关等)
 */
public class DataSetAbout {


    /**
     * code : 200
     * msg : 获取成功！
     * record : {"is_share":"1","is_msg_remind":"1","is_voice_remind":"1","is_video_remind":"1","is_sno_show":"1","is_qrcode_show":"1"}
     */

    private int code;
    private String msg;
    private String method;
    private RecordBean record;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

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

    public RecordBean getRecord() {
        return record;
    }

    public void setRecord(RecordBean record) {
        this.record = record;
    }

    public static class RecordBean {
        /**
         * is_share : 1
         * is_msg_remind : 1
         * is_voice_remind : 1
         * is_video_remind : 1
         * is_sno_show : 1
         * is_qrcode_show : 1
         */

        private String isShare;
        private String isMsgRemind;
        private String isVideoRemind;
        private String isVoiceRemind;
        private String isQrcodeShow;
        private String isSnoShow;

        public String getIsShare() {
            return isShare;
        }

        public void setIsShare(String isShare) {
            this.isShare = isShare;
        }

        public String getIsMsgRemind() {
            return isMsgRemind;
        }

        public void setIsMsgRemind(String isMsgRemind) {
            this.isMsgRemind = isMsgRemind;
        }

        public String getIsVideoRemind() {
            return isVideoRemind;
        }

        public void setIsVideoRemind(String isVideoRemind) {
            this.isVideoRemind = isVideoRemind;
        }

        public String getIsVoiceRemind() {
            return isVoiceRemind;
        }

        public void setIsVoiceRemind(String isVoiceRemind) {
            this.isVoiceRemind = isVoiceRemind;
        }

        public String getIsQrcodeShow() {
            return isQrcodeShow;
        }

        public void setIsQrcodeShow(String isQrcodeShow) {
            this.isQrcodeShow = isQrcodeShow;
        }

        public String getIsSnoShow() {
            return isSnoShow;
        }

        public void setIsSnoShow(String isSnoShow) {
            this.isSnoShow = isSnoShow;
        }
//        public String getIs_share() {
//            return is_share;
//        }
//
//        public void setIs_share(String is_share) {
//            this.is_share = is_share;
//        }
//
//        public String getIs_msg_remind() {
//            return is_msg_remind;
//        }
//
//        public void setIs_msg_remind(String is_msg_remind) {
//            this.is_msg_remind = is_msg_remind;
//        }
//
//        public String getIs_voice_remind() {
//            return is_voice_remind;
//        }
//
//        public void setIs_voice_remind(String is_voice_remind) {
//            this.is_voice_remind = is_voice_remind;
//        }
//
//        public String getIs_video_remind() {
//            return is_video_remind;
//        }
//
//        public void setIs_video_remind(String is_video_remind) {
//            this.is_video_remind = is_video_remind;
//        }
//
//        public String getIs_sno_show() {
//            return is_sno_show;
//        }
//
//        public void setIs_sno_show(String is_sno_show) {
//            this.is_sno_show = is_sno_show;
//        }
//
//        public String getIs_qrcode_show() {
//            return is_qrcode_show;
//        }
//
//        public void setIs_qrcode_show(String is_qrcode_show) {
//            this.is_qrcode_show = is_qrcode_show;
//        }
    }
}
