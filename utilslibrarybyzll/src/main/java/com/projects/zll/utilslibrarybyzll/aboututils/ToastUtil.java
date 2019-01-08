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
        show(text, Toast.LENGTH_SHORT);
    }

    //自定义时长吐司
    public static void show( Integer resourceID, int duration) {
        String text = mContext().getResources().getString(resourceID);// 用于显示的文字
        show(text, duration);
    }

    //自定义时长吐司
    public static void show( @NonNull final String text, final int duration) {

        if (TOAST == null) {
            TOAST = Toast.makeText(mContext(), text, duration);
        } else {
            TOAST.setText(text);
            TOAST.setDuration(duration);
        }
        TOAST.show();
    }
}
