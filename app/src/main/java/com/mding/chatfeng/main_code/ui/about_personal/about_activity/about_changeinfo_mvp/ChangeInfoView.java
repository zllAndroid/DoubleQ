package com.mding.chatfeng.main_code.ui.about_personal.about_activity.about_changeinfo_mvp;

/**
 * 项目：DoubleQ
 * 文件描述：修改个人资料的View
 * 作者：ljj
 * 创建时间：2019/4/16
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public interface ChangeInfoView {
    void showTextVeiw();

    void noACacheShow();

    void errorHeadImg();

    void changeHeadImg();

    void changeNickName();

    void changeAccount();

    void changeSign();

    void toQrCodeAc();
}
