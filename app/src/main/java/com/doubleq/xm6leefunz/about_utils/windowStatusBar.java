package com.doubleq.xm6leefunz.about_utils;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.doubleq.xm6leefunz.R;

public class windowStatusBar {
    private static final int STATUS_VIEW_ID = R.id.status_view;
    /**
     * 设置普通toolbar中状态栏颜色以及明暗度
     *
     * @param activity
     * @param color
     * @param statusBarAlpha
     */
    public static void setStatusColor(Activity activity, @ColorInt int color, int statusBarAlpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().setStatusBarColor(statusColorIntensity(color, statusBarAlpha));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            setStatusViewToAct(activity, color, statusBarAlpha);
            setRootView(activity);
        }
    }
    /**
     * 配置状态栏之下的View
     *
     * @param activity
     */
    public static void setRootView(Activity activity) {
        ViewGroup parent = (ViewGroup) activity.findViewById(android.R.id.content);
        for (int i = 0, count = parent.getChildCount(); i < count; i++) {
            View childView = parent.getChildAt(0);
            if (childView instanceof ViewGroup) {
                childView.setFitsSystemWindows(true);
                ((ViewGroup) childView).setClipToPadding(true);
            }
        }
    }
    /**
     * 计算状态栏颜色明暗度
     *
     * @param color color值
     * @param alpha alpha值
     * @return 最终的状态栏颜色
     */
    private static int statusColorIntensity(@ColorInt int color, int alpha) {
        if (alpha == 0) {
            return color;
        }
        float a = 1 - alpha / 255f;
        int red = color >> 16 & 0xff;
        int green = color >> 8 & 0xff;
        int blue = color & 0xff;
        red = (int) (red * a + 0.5);
        green = (int) (green * a + 0.5);
        blue = (int) (blue * a + 0.5);
        return 0xff << 24 | red << 16 | green << 8 | blue;
    }
    /**
     * 设置状态栏view的颜色并添加到界面中，如果找到状态栏view则直接设置，否则创建一个再设置
     *
     * @param activity
     * @param color
     * @param statusBarAlpha
     */
    private static void setStatusViewToAct(Activity activity, @ColorInt int color, int statusBarAlpha) {
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        View fakeStatusBarView = decorView.findViewById(STATUS_VIEW_ID);
        if (fakeStatusBarView != null) {
            if (fakeStatusBarView.getVisibility() == View.GONE) {
                fakeStatusBarView.setVisibility(View.VISIBLE);
            }
            fakeStatusBarView.setBackgroundColor(statusColorIntensity(color, statusBarAlpha));
        } else {
            decorView.addView(createStatusBarView(activity, color, statusBarAlpha));
        }
    }

    /**
     * 创建和状态栏一样高的矩形，用于改变状态栏颜色和明暗度
     *
     * @param activity
     * @param color
     * @param alpha
     * @return
     */
    private static View createStatusBarView(Activity activity, @ColorInt int color, int alpha) {
        // 绘制一个和状态栏一样高的矩形
        View statusBarView = new View(activity);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(statusColorIntensity(color, alpha));
        statusBarView.setId(STATUS_VIEW_ID);
        return statusBarView;
    }
    /**
     * 得到statusbar高度
     *
     * @param activity
     * @return
     */
    private static int getStatusBarHeight(Activity activity) {
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return activity.getResources().getDimensionPixelSize(resourceId);
    }
}
