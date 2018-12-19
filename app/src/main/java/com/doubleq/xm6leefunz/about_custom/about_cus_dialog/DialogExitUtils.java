package com.doubleq.xm6leefunz.about_custom.about_cus_dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;


/**
 * Created by Administrator on 2017/7/26.
 */

public class DialogExitUtils {
    private static String checkId;

    public interface OnClickSureListener {
        void onClickSure(String checkedId);
    }

    public interface OnClickCancleListener {
        void onClickCancle();
    }


    static CusExitDialog CUS_EXIT_DIALOG =null;
    static  CusExitDialog.Builder  BUILDER=null;

    protected static Context mContext() {
        return AppManager.getAppManager().currentActivity();
    }

    protected static void haveShow(){
        try {
            if (CUS_EXIT_DIALOG!=null&&CUS_EXIT_DIALOG.isShowing()&&dialogs!=null)
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
            if (CUS_EXIT_DIALOG!=null) {
                CUS_EXIT_DIALOG.cancel();
                CUS_EXIT_DIALOG = null;
                dialogs.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void showDialog(String text,String text_second,OnClickSureListener onClickSureListener){
        haveShow();
        show(text,text_second,onClickSureListener);
    }
    static DialogInterface dialogs =null;
    protected static void show(final String text, final String text_second,final  OnClickSureListener onClickSureListener) {
        CusExitDialog.type ="1";
        if (BUILDER == null)
            BUILDER = new CusExitDialog.Builder(mContext());

        BUILDER.setMessage(text,text_second);
        BUILDER.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        BUILDER.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int var2) {
                onClickSureListener.onClickSure(CusExitDialog.type);
                dialogs = dialog;
                dialog.dismiss();
            }
        });
//            BUILDER.setPositiveButton("确定", new CusExitDialog.ClickSure() {
//                @Override
//                public void onClick(DialogInterface dialog, int which,String type) {
////                    mClickSure.clickSure(checkId);
//                    onClickSureListener.onClickSure(checkId);
//                    dialogs = dialog;
//                    dialog.dismiss();
//
//                }
//            });

        custom();
    }

    protected static void custom() {
        try {
            CUS_EXIT_DIALOG = BUILDER.create();
            CUS_EXIT_DIALOG.setCancelable(false);
            CUS_EXIT_DIALOG.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 网络连接错误
     */
//    public static void getError( String message) {
//        Activity activity = AppManager.getAppManager().currentActivity();
//        if (activity != null) {
//            TipPopWindow tipPopWindow = new TipPopWindow(activity);
//            tipPopWindow.getstyle("知道了", null);
//            if (message == null) {
//                tipPopWindow.setTitle_message("提示", a.worry);
//            } else {
//                if (message.equals("")) {
//                    tipPopWindow.setTitle_message("提示", CommonParameter.worry);
//                } else {
//                    tipPopWindow.setTitle_message("提示", message);
//                }
//            }
//        }
//    }
}
