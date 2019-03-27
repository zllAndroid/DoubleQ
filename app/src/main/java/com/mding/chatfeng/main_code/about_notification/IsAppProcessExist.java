package com.mding.chatfeng.main_code.about_notification;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class IsAppProcessExist {

    /**
     *  判断进程是否存活
     */
    public static boolean isProcessExist(Context context, int pid) {

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> lists ;
        if (am != null) {
            lists = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo appProcess : lists) {
                if (appProcess.pid == pid) {
                    Log.e("TAG","333333");
                    return true;
                }
            }
        }
        return false;
    }
}
