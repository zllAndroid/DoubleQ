package com.mding.chatfeng.main_code.ui.about_personal.about_activity.about_changeinfo_mvp;

import com.mding.chatfeng.about_application.BaseApplication;
import com.mding.chatfeng.about_base.web_base.SplitWeb;

/**
 * 项目：DoubleQ
 * 文件描述：修改个人资料的Interactor
 * 作者：ljj
 * 创建时间：2019/4/16
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class ChangeInfoInteractor {

    interface OnChangeFinishedListener{
        //        void onHeadImgSuccess();
//
//        void onNicnNameSuccess();
//
//        void onAccountSuccess();
//
//        void onSignSuccess();
//
//        void onChangeFailed();

        void onError(String error);

        void onSuccess(String result);
    }

    public void changeInfoRequest(final OnChangeFinishedListener onChangeFinishedListener){
//        BaseApplication.getApp().sendData(SplitWeb.upHeadImg());
    }
}
