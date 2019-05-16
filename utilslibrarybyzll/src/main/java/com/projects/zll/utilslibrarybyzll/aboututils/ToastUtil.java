package com.projects.zll.utilslibrarybyzll.aboututils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;


/**
 * Toast工具类
 */

public class ToastUtil {

    private static Toast TOAST;
    private static final String TAG = "ToastUtil";
    private static final boolean isDebug = false;  //若设为true，说明在调试模式；设为false，则将所有的toast取消

    //是否为调试模式的短时间吐司
    public static void isDebugShow( String text) {
        if (isDebug){
            show(text, Toast.LENGTH_SHORT);
        }
    }

    //短时间吐司
    public static void show(int resourceID) {
        show(resourceID, Toast.LENGTH_SHORT);
    }
    //    获取当前上下文
    public static Context mContext() {
        return  AppManager.getAppManager().currentActivity();
    }

    //短时间吐司
    public static void show( String text) {
        try {
            show(text, Toast.LENGTH_SHORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //自定义时长吐司
    public static void show( Integer resourceID, int duration) {
        String text = mContext().getResources().getString(resourceID);// 用于显示的文字
        show(text, duration);
    }

    //自定义时长吐司
    public static void show( @NonNull final String text, final int duration) {

        try {
            if (TOAST == null) {
                TOAST = Toast.makeText(mContext(), text, duration);
            } else {
                TOAST.setText(text);
                TOAST.setDuration(duration);
            }
            TOAST.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
