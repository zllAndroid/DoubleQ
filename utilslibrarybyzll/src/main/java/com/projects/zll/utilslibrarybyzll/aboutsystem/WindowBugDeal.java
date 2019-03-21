package com.projects.zll.utilslibrarybyzll.aboutsystem;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import com.projects.zll.utilslibrarybyzll.R;

/**
 * Created by Administrator on 2017/9/25 0025.
 */

public class WindowBugDeal {
    public static void checkDeviceHasNavigationBars(Activity context){
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar","bool","android");
        if (id > 0)
        {
//            Window window = context.getWindow();
//            context.getWindow().addFlags(View.SYSTEM_UI_FLAG_IMMERSIVE);
//            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
//            context.getWindow().addFlags(WindowManager.LayoutParams.IMMERSIVE);//A
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//B//
            context.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION );
////            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//////                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            context.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE );
////            context.getWindow().getDecorView().setSystemUiVisibility(View.VISIBLE );
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//B//
//            context.getWindow().addFlags(View.SYSTEM_UI_FLAG_VISIBLE);
        }
    }

    public static void SetTop(Activity context){
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar","bool","android");
        if (id > 0) {
//            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //添加变色标志
//                context.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//导航栏颜色
                context.getWindow().setNavigationBarColor(context.getResources().getColor(R.color.white));
//                context.getWindow().setStatusBarColor(context.getResources().getColor(R.color.app_theme));
            }
        }
    }


    /**
     * 获取状态栏高度
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    /**
     * 以下是旧的
     */
    public static void checkDeviceHasNavigationBar(Activity context){
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar","bool","android");
        if (id > 0)
        {
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//B//
//            context.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION );
//			context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
//            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//B//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
//            {
//                //添加变色标志
//                context.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
////导航栏颜色
//                context.getWindow().setNavigationBarColor(context.getResources().getColor(R.color.app_theme));
//            }
        }
    }
//    public static void SetTop(Activity context){
//        Resources rs = context.getResources();
//        int id = rs.getIdentifier("config_showNavigationBar","bool","android");
//        if (id > 0)
//        {
//			context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
//        }
//    }
}
