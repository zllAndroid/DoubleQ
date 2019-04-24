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
public class TeamInteractor {

    interface OnTeamDealOverListener {

        void onError(String error);
        void onSuccess(String result);
        void onOpen(String result);
    }
    public   void  sendRequest(Context mContext, OnTeamDealOverListener onTeamDealOverListener, String request){


    }
    public   List<DataGroupMember.RecordBean.MemberListBean>   dealWithList( String request){

//        List<DataGroupMember.RecordBean.MemberListBean> allCusList


        return null;
    }


}
