package com.mding.chatfeng.about_utils.about_realm.realm_data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CusDataFriendRealm  extends RealmObject{
        /**
         * user_id : cb16ce45-854c-f553-9c9a-2e57a6addca3
         * mobile : 18150960007
         * wx_sno : mMyV95B4849406105b969d29e3ebf2.13962996
         * nick_name : 海大胖
         * head_img : http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132
         * group_id : 3
         * group_name : 同学
         * chart : H
         */
        @PrimaryKey
        private String userId;
        private String mobile;
        private String wxSno;
        private String nickName;
        private String headImg;
        private String groupId;
        private String groupName;
        private String chart;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getWxSno() {
            return wxSno;
        }

        public void setWxSno(String wxSno) {
            this.wxSno = wxSno;
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

        public String getChart() {
            return chart;
        }

        public void setChart(String chart) {
            this.chart = chart;
        }
}
