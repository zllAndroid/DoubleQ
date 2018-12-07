package com.projects.zll.utilslibrarybyzll.aboutsystem;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.util.Stack;

/**
 *  应用程序Activity管理类：用于Activity管理和应用程序退出
 * @author zll
 */
public class AppManager {

    private Stack<AppCompatActivity> activityStack;
    // 定义一个私有的静态全局变量来保存该类的唯一实例
    private static AppManager instance;

    // 构造函数必须是私有的 这样在外部便无法使用 new 来创建该类的实例
    private AppManager() {

    }

    /**
     * 单一实例
     */
    public synchronized static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(AppCompatActivity activity) {
        if (activityStack==null)
            activityStack = new Stack<>();
        activityStack.add(activity);
    }
    public Stack<AppCompatActivity> getStack() {
        if (activityStack!=null)
            return activityStack;
        else
            return null;
    }

    /**
     * 从堆栈移除指定的Activity
     */
    public void removeActivity(AppCompatActivity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity = null;
        }
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public AppCompatActivity currentActivity() {
        if (activityStack!=null)
            if (activityStack.size()>0) {
                AppCompatActivity appCompatActivity = activityStack.get(activityStack.size() - 1);
                return appCompatActivity;
            }else
            {
                return activityStack.lastElement();
            }
        return null;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        finishActivity(activityStack.lastElement());
    }
    public void finishActivityLastTwo() {
        finishActivity(activityStack.lastElement());
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (AppCompatActivity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }
    /**
     * 结束指定的Activity
     */
    public void finishActivity(AppCompatActivity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }
    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }
    /**
     * 结束除了指定activity以外所有activity
     */
    public void finishAllExceptCurrentActivity(Class<?> cls) {
        AppCompatActivity activity2 = null;
        for (AppCompatActivity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishAllExceptCurrentActivity(activity);
                activity2 =activity;
            }else
            {
                activity.finish();
                activityStack.remove(activity);
            }
        }
        if (activity2!=null)
        {
            activityStack.clear();
            activityStack.add(activity2);
        }


    }
    public void finishAllExceptCurrentActivity(AppCompatActivity activity) {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)&& activity != activityStack.get(i)) {
                activityStack.get(i).finish();
                activityStack.remove(activityStack.get(i));
            }
        }
        activityStack.clear();
        activityStack.add(activity);
    }

    /**
     * 退出应用程序
     */
    @SuppressWarnings("deprecation")
    public void onAppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }
}
