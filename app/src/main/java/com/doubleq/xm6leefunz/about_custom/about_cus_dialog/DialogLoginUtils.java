package com.doubleq.xm6leefunz.about_custom.about_cus_dialog;

import android.content.Context;
import android.content.DialogInterface;

import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;

public class DialogLoginUtils {

    public interface OnClickForgetPwdListener {
        void onClickForgetPwd();
    }

    static CusLoginDialog CUS_LOGIN_DIALOG =null;
    static  CusLoginDialog.Builder  BUILDER=null;

    protected static Context mContext() {
        return AppManager.getAppManager().currentActivity();
    }

    protected static void haveShow(){
        try {
            if (CUS_LOGIN_DIALOG!=null&&CUS_LOGIN_DIALOG.isShowing()&&dialogs!=null)
            {
                dialogs.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void isShow(){
        try {
            if (BUILDER != null)
            {
                BUILDER=null;
            }
            if (CUS_LOGIN_DIALOG!=null) {
                CUS_LOGIN_DIALOG.cancel();
                CUS_LOGIN_DIALOG = null;
                dialogs.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void showDialog(String text,OnClickForgetPwdListener onClickForgetPwdListener){
        haveShow();
        show(text,onClickForgetPwdListener);
    }
    static DialogInterface dialogs =null;
    protected static void show(final String text, final  OnClickForgetPwdListener onClickForgetPwdListener) {
        if (BUILDER == null)
            BUILDER = new CusLoginDialog.Builder(mContext());

        BUILDER.setMessage(text);
        BUILDER.setForgetPwdButton("忘记密码", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onClickForgetPwdListener.onClickForgetPwd();
                dialogs = dialogInterface;
                dialogInterface.dismiss();
            }
        });
        BUILDER.setReInputButton("重新输入", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int var2) {
                dialog.dismiss();
            }
        });

        BUILDER.setClose(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });

        custom();
    }

    protected static void custom() {
        try {
            CUS_LOGIN_DIALOG = BUILDER.create();
            CUS_LOGIN_DIALOG.setCancelable(false);
            CUS_LOGIN_DIALOG.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
