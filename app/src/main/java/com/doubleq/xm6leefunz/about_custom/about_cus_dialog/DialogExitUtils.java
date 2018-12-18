package com.doubleq.xm6leefunz.about_custom.about_cus_dialog;

import android.content.Context;
import android.content.DialogInterface;

import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;


/**
 * Created by Administrator on 2017/7/26.
 */

public class DialogExitUtils {
    public interface OnClickSureListener {
        void onClickSure();
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
//    protected static void show(@NonNull final String text, final  OnClickSureListener listener,boolean isHaveTwo) {
//
//        if (BUILDER == null)
//            BUILDER = new CusExitDialog.Builder(mContext());
//
//            BUILDER.setMessage(text);
//            BUILDER.setNegativeButton("确定", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialogs=dialog;
//                    listener.onClickSure();
//                    dialog.dismiss();
//                }
//            });
//            if (isHaveTwo) {
//                BUILDER.setPositiveButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                    }
//                });
//            }else
//            {
//                BUILDER.setPositiveButton(null,null);
//            }
//        custom();
//    }

    protected static void show(final String text, final String text_second, final  OnClickSureListener onClickSureListener) {

        if (BUILDER == null)
            BUILDER = new CusExitDialog.Builder(mContext());

            BUILDER.setMessage(text,text_second);
            BUILDER.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
//                    onClickCancleListener.onClickCancle();
                    dialogInterface.dismiss();
                }
            });
            BUILDER.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onClickSureListener.onClickSure();
                    dialogs=dialog;
                    dialog.dismiss();

                }
            });

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

//    protected static void show(@NonNull final String text,final String text_second) {
//
//        if (BUILDER == null)
//            BUILDER = new CusExitDialog.Builder(mContext());
//            BUILDER.setMessage(text,text_second);
//            BUILDER.setNegativeButton("确定", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
////                    listener.onClickSure();
//                    dialog.dismiss();
//                    dialogs=dialog;
//                }
//            });
//            BUILDER.setPositiveButton(null,null);
//
//        custom();
//    }

//    protected static void show(@NonNull final String text,String clickText) {
//
//        if (BUILDER == null)
//            BUILDER = new CusExitDialog.Builder(mContext());
//            BUILDER.setMessage(text);
//            BUILDER.setNegativeButton(clickText, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
////                    listener.onClickSure();
//                    dialog.dismiss();
//                    dialogs=dialog;
//                }
//            });
//            BUILDER.setPositiveButton(null,null);
//
//        custom();
//    }

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
