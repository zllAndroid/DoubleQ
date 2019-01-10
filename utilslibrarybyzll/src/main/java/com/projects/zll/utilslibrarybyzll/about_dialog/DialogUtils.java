package com.projects.zll.utilslibrarybyzll.about_dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;

import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;


/**
 * Created by Catherine on 2018/12/17.
 */

public class DialogUtils {
    public interface OnClickSureListener {
        void onClickSure();
    }
    public interface OnClickCancleListener {
        void onClickCancle();
    }
    static CustomDialog CUS_DIALOG =null;
    static  CustomDialog.Builder  BUILDER=null;
    protected static Context mContext() {
        return AppManager.getAppManager().currentActivity();
    }
    public static void showDialog(String text){
        haveShow();
        show(text);
    }
    public static void showDialogKnow(String text,String clickText){
        haveShow();
        show(text,clickText);
    }
    public static void isShow(){
        try {
            if (BUILDER != null)
            {
                BUILDER=null;
            }
            if (CUS_DIALOG!=null) {
                CUS_DIALOG.cancel();
                CUS_DIALOG.dismiss();
                CUS_DIALOG = null;
                dialogs.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected static void haveShow(){
        try {
            if (CUS_DIALOG!=null&&CUS_DIALOG.isShowing()&&dialogs!=null)
            {
                CUS_DIALOG.dismiss();
                dialogs.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void showDialogOne(String text,OnClickSureListener listener){
        haveShow();
        show(text,listener,false);
    }
    public static void showDialog(String text,OnClickSureListener listener){
        haveShow();
        show(text,listener,true);
    }
    public static void showDialog(String text,OnClickSureListener listener,OnClickCancleListener onClickCancleListener){
        haveShow();
        show(text,listener,onClickCancleListener);
    }
   static DialogInterface dialogs =null;
    protected static void show(@NonNull final String text, final  OnClickSureListener listener,boolean isHaveTwo) {

        if (BUILDER == null)
            BUILDER = new CustomDialog.Builder(mContext());

            BUILDER.setMessage(text);
            BUILDER.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialogs=dialog;
                    listener.onClickSure();
                    dialog.dismiss();
                }
            });
            if (isHaveTwo) {
                BUILDER.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
            }else
            {
                BUILDER.setPositiveButton(null,null);
            }
        custom();
    }
    protected static void show(@NonNull final String text, final  OnClickSureListener listener,final OnClickCancleListener onClickCancleListener) {

        if (BUILDER == null)
            BUILDER = new CustomDialog.Builder(mContext());

            BUILDER.setMessage(text);
            BUILDER.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listener.onClickSure();
                    dialogs=dialog;
                    dialog.dismiss();
                }
            });
            BUILDER.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    onClickCancleListener.onClickCancle();
                    dialogInterface.dismiss();
                }
            });

        custom();
    }

    protected static void custom() {
        try {
                CUS_DIALOG = BUILDER.create();
//            if (!CUS_DIALOG.isShowing()) {
                CUS_DIALOG.setCancelable(false);
                CUS_DIALOG.show();
//            }else
//            {
//                isShow();
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void show(@NonNull final String text) {

        if (BUILDER == null)
            BUILDER = new CustomDialog.Builder(mContext());
            BUILDER.setMessage(text);
            BUILDER.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    listener.onClickSure();
                    dialog.dismiss();
                    dialogs=dialog;
                }
            });
            BUILDER.setPositiveButton(null,null);

        custom();
    }
    protected static void show(@NonNull final String text,String clickText) {

        if (BUILDER == null)
            BUILDER = new CustomDialog.Builder(mContext());
            BUILDER.setMessage(text);
            BUILDER.setNegativeButton(clickText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    listener.onClickSure();
                    dialog.dismiss();
                    dialogs=dialog;
                }
            });
            BUILDER.setPositiveButton(null,null);

        custom();
    }
//    public static void getDialog(Context mc,String message,final  OnClickSureListener listener) {
////        CustomDialog mDialog;
////        CustomDialog.Builder  mBuilder;
//        if (mBuilder==null)
//        mBuilder = new CustomDialog.Builder(mc);
//
//        mBuilder.setMessage(message);
//        mBuilder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                listener.onClickSure();
//                dialog.dismiss();
//            }
//        });
//        mBuilder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        });
//        if (mDialog==null)
//            mDialog = mBuilder.create();
//        if (!mDialog.isShowing()) {
//            mDialog.setCancelable(false);
//            mDialog.show();
//        }
////        mDialog = mBuilder.create();
////        mDialog.setCancelable(false);
////        mDialog.show();
//    }
//    public static void getDialogOne(Context mc,String message,final  OnClickSureListener listener) {
////        CustomDialog mDialog;
////        CustomDialog.Builder  mBuilder;
//        if (mBuilder==null)
//        mBuilder = new CustomDialog.Builder(mc);
//        mBuilder.setMessage(message);
//        mBuilder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                listener.onClickSure();
//                dialog.dismiss();
//            }
//        });
//        if (mDialog==null)
//            mDialog = mBuilder.create();
//        if (!mDialog.isShowing()) {
//            mDialog.setCancelable(false);
//            mDialog.show();
//        }
////        mDialog = mBuilder.create();
////        mDialog.setCancelable(false);
////        mDialog.show();
//    }
//    public static void getDialog(Context mc,String message,final  OnClickSureListener listener,final OnClickCancleListener onClickCancleListener) {
////        CustomDialog mDialog;
////        CustomDialog.Builder  mBuilder;
//        if (mBuilder==null)
//        mBuilder = new CustomDialog.Builder(mc);
//        mBuilder.setMessage(message);
//        mBuilder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                listener.onClickSure();
//                dialog.dismiss();
//            }
//        });
//        mBuilder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                onClickCancleListener.onClickCancle();
//                dialogInterface.dismiss();
//            }
//        });
//        if (mDialog==null)
//            mDialog = mBuilder.create();
//        if (!mDialog.isShowing()) {
//            mDialog.setCancelable(false);
//            mDialog.show();
//        }
////        mDialog = mBuilder.create();
////        mDialog.setCancelable(false);
////        mDialog.show();
//    }
//    public static  DialogInterface dialogs =null;
//    public static CustomDialog mDialog = null;
//    public static void getDialogOneClick(Context mc,String message,final  OnClickSureListener listeners) {
//        if (mDialog!=null&&mDialog.isShowing()&&dialogs!=null)
//        {
//            dialogs.dismiss();
//        }
////        CustomDialog.Builder  mBuilder;
//        if (mBuilder==null)
//        mBuilder = new CustomDialog.Builder(mc);
//        mBuilder.setMessage(message);
//        mBuilder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialogs=dialog;
//                listeners.onClickSure();
//                dialog.dismiss();
//            }
//        });
//        if (mDialog==null)
//            mDialog = mBuilder.create();
//        if (!mDialog.isShowing()) {
//            mDialog.setCancelable(false);
//            mDialog.show();
//        }
////        if (mDialog==null) {
////            mDialog = mBuilder.create();
////            mDialog.setCancelable(false);
////            mDialog.show();
////        }else {
////            try {
////                mDialog.show();
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////        }
//    }
//    public static void getDialog( Context mc,String message,final boolean Isfinish) {
////        CustomDialog mDialog;
//        if (mBuilder==null)
//        mBuilder = new CustomDialog.Builder(mc);
//        mBuilder.setMessage(message);
//        mBuilder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (Isfinish)
//                {
//                    AppManager.getAppManager().finishActivity();
//                }
//                dialog.dismiss();
//
//            }
//        });
//        if (mDialog==null)
//            mDialog = mBuilder.create();
//        if (!mDialog.isShowing()) {
//            mDialog.setCancelable(false);
//            mDialog.show();
//        }
////        mDialog = mBuilder.create();
////        mDialog.setCancelable(false);
////        mDialog.show();
//    }
//
//    public static void getDialog(Context mc, String message) {
////        CustomDialog mDialog;
////        CustomDialog.Builder  mBuilder;
//        if (mBuilder==null) {
//            mBuilder = new CustomDialog.Builder(mc);
//        }
//            mBuilder.setMessage(message);
//            mBuilder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            });
//            if (mDialog==null)
//                mDialog = mBuilder.create();
//            if (!mDialog.isShowing()) {
//                mDialog.setCancelable(false);
//                mDialog.show();
//            }
//
//    }
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
