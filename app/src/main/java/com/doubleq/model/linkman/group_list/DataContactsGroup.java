package com.doubleq.model.linkman.group_list;

import java.util.List;

public class DataContactsGroup {
    String tv_parent_name_g;
    String tv_parent_now_g;
    String tv_parent_all_g;

    private boolean isExpand;

    public List<DataContactsGroupChild> getDataContactsGroupChildList() {
        return dataContactsGroupChildList;
    }

    public void setDataContactsGroupChildList(List<DataContactsGroupChild> dataContactsGroupChildList) {
        this.dataContactsGroupChildList = dataContactsGroupChildList;
    }

    private List<DataContactsGroupChild> dataContactsGroupChildList;


    public String getTv_parent_name_g() {
        return tv_parent_name_g;
    }

    public void setTv_parent_name_g(String tv_parent_name_g) {
        this.tv_parent_name_g = tv_parent_name_g;
    }

    public String getTv_parent_now_g() {
        return tv_parent_now_g;
    }

    public void setTv_parent_now_g(String tv_parent_now_g) {
        this.tv_parent_now_g = tv_parent_now_g;
    }

    public String getTv_parent_all_g() {
        return tv_parent_all_g;
    }

    public void setTv_parent_all_g(String tv_parent_all_g) {
        this.tv_parent_all_g = tv_parent_all_g;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public List<DataContactsGroupChild> dataContactsGroupChildList() {
        return dataContactsGroupChildList();
    }

    public DataContactsGroup(String tv_parent_name_g, String tv_parent_now_g, String tv_parent_all_g, List<DataContactsGroupChild> dataContactsGroupChildList) {
        this.tv_parent_name_g = tv_parent_name_g;
        this.tv_parent_now_g = tv_parent_now_g;
        this.tv_parent_all_g = tv_parent_all_g;
        this.dataContactsGroupChildList = dataContactsGroupChildList;
    }
}
