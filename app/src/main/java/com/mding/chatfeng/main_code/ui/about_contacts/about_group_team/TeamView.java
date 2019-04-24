package com.mding.chatfeng.main_code.ui.about_contacts.about_group_team;

import com.mding.model.DataGroupMember;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目：DoubleQ
 * 文件描述：
 * 作者：zll
 * 创建时间：2019/4/23 0023
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public interface TeamView {

    void showProgress();

    void hideProgress();

    void loadSuccess(List<DataGroupMember.RecordBean.MemberListBean> allCusList);

    void loaderror();
}
