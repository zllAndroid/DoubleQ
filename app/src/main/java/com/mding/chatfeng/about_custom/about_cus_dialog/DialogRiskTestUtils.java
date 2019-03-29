package com.mding.chatfeng.about_custom.about_cus_dialog;

import android.content.Context;
import android.content.DialogInterface;

import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;


/**
 * Created by Administrator on 2017/7/26.
 */

public class DialogRiskTestUtils {
    public interface OnClickSureListener {
        void onClickSure();
    }

    public interface OnClickCancleListener {
        void onClickCancle();
    }

    static CusRiskTestDialog cusRiskTestDialog =null;
    static  CusRiskTestDialog.Builder  BUILDER=null;

    protected static Context mContext() {
        return AppManager.getAppManager().currentActivity();
    }

    protected static void haveShow(){
        try {
            if (cusRiskTestDialog!=null&&cusRiskTestDialog.isShowing()&&dialogs!=null)
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
            if (cusRiskTestDialog!=null) {
                cusRiskTestDialog.cancel();
                cusRiskTestDialog = null;
                dialogs.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void showDialog(String text,String text_second,String organization, String time,OnClickSureListener onClickSureListener){
        haveShow();
        show(text,text_second,organization,time,onClickSureListener);
    }
    static DialogInterface dialogs =null;
    protected static void show(final String text, final String text_second,String organization, String time,final  OnClickSureListener onClickSureListener) {
        if (BUILDER == null)
            BUILDER = new CusRiskTestDialog.Builder(mContext());

        BUILDER.setMessage(text,text_second,organization,time);
        BUILDER.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        BUILDER.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int var2) {
                onClickSureListener.onClickSure();
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
            cusRiskTestDialog = BUILDER.create();
            cusRiskTestDialog.setCancelable(false);
            cusRiskTestDialog.show();
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
