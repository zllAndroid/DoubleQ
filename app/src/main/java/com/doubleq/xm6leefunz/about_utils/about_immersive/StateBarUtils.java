package com.doubleq.xm6leefunz.about_utils.about_immersive;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_utils.windowStatusBar;

public class StateBarUtils {
    public static void setFullscreen(Activity mActivity, boolean isShowStatusBar, boolean isShowNavigationBar) {
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        if (!isShowStatusBar) {
            uiOptions |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }
        if (!isShowNavigationBar) {
            uiOptions |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }
        mActivity.getWindow().getDecorView().setSystemUiVisibility(uiOptions);

//        //隐藏标题栏

//        getSupportActionBar().hide();
        //专门设置一下状态栏导航栏背景颜色为透明，凸显效果。
//        setNavigationStatusColor(Color.WHITE);
        if (Build.VERSION.SDK_INT >= 21) {
            mActivity.getWindow().setNavigationBarColor(Color.WHITE);
        }
    }
    public static void setAndroidNativeLightStatusBar(Activity activity, boolean dark) {
        View decor = activity.getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
    public void setNavigationStatusColor(Activity mActivity,int color) {
        //VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
        if (Build.VERSION.SDK_INT >= 21) {
            mActivity. getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            mActivity.getWindow().setNavigationBarColor(color);
            mActivity.getWindow().setStatusBarColor(color);
        }
    }
}
