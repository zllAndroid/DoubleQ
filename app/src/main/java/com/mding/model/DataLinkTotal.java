package com.mding.model;

import java.util.List;

public class DataLinkTotal {

    public String GroupName;
    private boolean isExpand;
    private List<DataLinkTotalChild> dataLinkTotalChildList;

    public DataLinkTotal(String GroupName,boolean isExpand,List<DataLinkTotalChild> dataLinkTotalChildList ) {
        this.GroupName=GroupName;
        this.isExpand=isExpand;
        this.dataLinkTotalChildList=dataLinkTotalChildList;

    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public List<DataLinkTotalChild> getDataLinkChildList() {
        return dataLinkTotalChildList;
    }

    public void setDataLinkChildList(List<DataLinkTotalChild> dataLinkTotalChildList) {
        this.dataLinkTotalChildList = dataLinkTotalChildList;
    }
    public  static class DataLinkTotalChild{
        String userId;
        String mobile;
        String wxSno;
        String nickName;
        String groupId;
//        String GroupName;
        String chart;

        public DataLinkTotalChild(String userId, String mobile, String wxSno, String nickName, String groupId, String chart) {
            this.userId = userId;
            this.mobile = mobile;
            this.wxSno = wxSno;
            this.nickName = nickName;
            this.groupId = groupId;
//            GroupName = groupName;
            this.chart = chart;
        }

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

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

//        public String getGroupName() {
//            return GroupName;
//        }
//
//        public void setGroupName(String groupName) {
//            GroupName = groupName;
//        }

        public String getChart() {
            return chart;
        }

        public void setChart(String chart) {
            this.chart = chart;
        }
    }

}
