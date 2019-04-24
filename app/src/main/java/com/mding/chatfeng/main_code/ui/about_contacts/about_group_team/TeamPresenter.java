package com.mding.chatfeng.main_code.ui.about_contacts.about_group_team;

import android.content.Context;

import com.mding.model.DataGroupMember;

import java.util.List;

/**
 * 项目：DoubleQ
 * 文件描述：
 * 作者：zll
 * 创建时间：2019/4/24 0024
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class TeamPresenter implements TeamInteractor.OnTeamDealOverListener {
    TeamView mTeam;
    TeamInteractor mTeamInter;
    Context mContext;

    public TeamPresenter(TeamView mTeam, TeamInteractor mTeamInter, Context mContext) {
        this.mTeam = mTeam;
        this.mTeamInter = mTeamInter;
        this.mContext = mContext;
    }
    public void load(String msg)
    {
        mTeamInter.sendRequest(mContext,this,msg);
    }
    @Override
    public void onError(String error) {
        mTeam.hideProgress();
        mTeam.loaderror();
    }

    @Override
    public void onSuccess(String result) {
        mTeam.hideProgress();
        List<DataGroupMember.RecordBean.MemberListBean> memberListBeans = mTeamInter.dealWithList(result);
        mTeam.loadSuccess(memberListBeans);
    }

    @Override
    public void onOpen(String result) {
        mTeam.showProgress();
    }
}
