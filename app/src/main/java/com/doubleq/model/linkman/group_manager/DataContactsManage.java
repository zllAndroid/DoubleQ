package com.doubleq.model.linkman.group_manager;

import java.util.List;

public class DataContactsManage {

    String tv_parent_name_m;
    String tv_parent_now_m;
    String tv_parent_all_m;
    private boolean isExpand;
    private List<DataContactsManageChild> dataContactsManageChildList;

    public String getTv_parent_name_m() {
        return tv_parent_name_m;
    }

    public void setTv_parent_name_m(String tv_parent_name_m) {
        this.tv_parent_name_m = tv_parent_name_m;
    }

    public String getTv_parent_now_m() {
        return tv_parent_now_m;
    }

    public void setTv_parent_now_m(String tv_parent_now_m) {
        this.tv_parent_now_m = tv_parent_now_m;
    }

    public String getTv_parent_all_m() {
        return tv_parent_all_m;
    }

    public void setTv_parent_all_m(String tv_parent_all_m) {
        this.tv_parent_all_m = tv_parent_all_m;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public List<DataContactsManageChild> getDataContactsManageChildList() {
        return dataContactsManageChildList;
    }

    public void setDataContactsManageChildList(List<DataContactsManageChild> dataContactsManageChildList) {
        this.dataContactsManageChildList = dataContactsManageChildList;
    }

    public DataContactsManage(String tv_parent_name_m, String tv_parent_now_m, String tv_parent_all_m, List<DataContactsManageChild> dataContactsManageChildList) {
        this.tv_parent_name_m = tv_parent_name_m;
        this.tv_parent_now_m = tv_parent_now_m;
        this.tv_parent_all_m = tv_parent_all_m;
        this.dataContactsManageChildList = dataContactsManageChildList;
    }
}
