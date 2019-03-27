package com.mding.model.linkman.good_friends;

import java.util.List;

public class DataContactsFriend {
    String tv_parent_name_f;
    String tv_parent_now_f;
    String tv_parent_all_f;
    private boolean isExpand;
    private List<DataContactsFriendChild> dataContactsFriendChildList;

    public String getTv_parent_name_f() {
        return tv_parent_name_f;
    }

    public void setTv_parent_name_f(String tv_parent_name_f) {
        this.tv_parent_name_f = tv_parent_name_f;
    }

    public String getTv_parent_now_f() {
        return tv_parent_now_f;
    }

    public void setTv_parent_now_f(String tv_parent_now_f) {
        this.tv_parent_now_f = tv_parent_now_f;
    }

    public String getTv_parent_all_f() {
        return tv_parent_all_f;
    }

    public void setTv_parent_all_f(String tv_parent_all_f) {
        this.tv_parent_all_f = tv_parent_all_f;
    }

    public List<DataContactsFriendChild> getDataContactsFriendChildList() {
        return dataContactsFriendChildList;
    }

    public void setDataContactsFriendChildList(List<DataContactsFriendChild> dataContactsFriendChildList) {
        this.dataContactsFriendChildList = dataContactsFriendChildList;
    }

    public DataContactsFriend(String tv_parent_name_f, String tv_parent_now_f, String tv_parent_all_f, List<DataContactsFriendChild> dataContactsFriendChildList) {
        this.tv_parent_name_f = tv_parent_name_f;
        this.tv_parent_now_f = tv_parent_now_f;
        this.tv_parent_all_f = tv_parent_all_f;
        this.dataContactsFriendChildList = dataContactsFriendChildList;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }
//    public DataContactsFriend() {
//    }


}
