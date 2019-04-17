package com.mding.chatfeng.about_utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;

import java.io.Serializable;

/**
 * Created by  on 2017/11/9 0009.
 */

public class IntentUtils {
    /**
     * 打电话
     *
     * @param phoneNum
     */
    public static Activity activity =   AppManager.getAppManager().currentActivity();
    public static void call(String phoneNum) {
        Intent intent = new Intent();
        // 启动电话程序
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNum));
        activity.startActivity(intent);
    }
    //    public static void JumpFinishTo(Class<?> resultactivity) {
//        Intent intent = new Intent( activity, resultactivity);
//        activity.startActivity(intent);
////        Log.e("finishActivity", AppManager.getAppManager().currentActivity().getCallingPackage());
//        AppManager.getAppManager().finishActivity(activity);
//    }
    public static void JumpFinishTo(AppCompatActivity activity, Class<?> resultactivity) {
        Intent intent = new Intent( activity, resultactivity);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        activity.startActivity(intent);
//        Log.e("finishActivity", AppManager.getAppManager().currentActivity().getCallingPackage());
        AppManager.getAppManager().finishActivity(activity);
        activity.overridePendingTransition(0,0);
    }
    public static void JumpFinishToHaveOneBoolean(AppCompatActivity activity, Class<?> resultactivity,String key,boolean value) {
        Intent intent = new Intent( activity, resultactivity);
        Bundle bundle = new Bundle();
        bundle.putBoolean(key,value);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        AppManager.getAppManager().finishActivity(activity);
        activity.overridePendingTransition(0,0);
    }
    public static void JumpTo(Class<?> resultactivity) {
        Intent intent = new Intent( activity, resultactivity);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }
    public static void JumpToHaveOne(Class<?> resultactivity,String key,String value) {
        Intent intent = new Intent(activity, resultactivity);
        Bundle bundle = new Bundle();
        bundle.putString(key,value);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }
    public static void JumpToHaveTwo(Class<?> resultactivity,String key1,String value1,String key2,String value2) {
        Intent intent = new Intent(activity, resultactivity);
        Bundle bundle = new Bundle();
        bundle.putString(key1,value1);
        bundle.putString(key2,value2);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }
    public static void JumpToHaveObj(Class<?> resultactivity,String key,Serializable value) {
        Intent intent = new Intent(activity, resultactivity);
        Bundle bundle = new Bundle();
        bundle.putSerializable(key,value);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }
    public static void JumpToHaveObjAndStr(Class<?> resultactivity,String key,Serializable value,String key1,String value1) {
        Intent intent = new Intent(activity, resultactivity);
        Bundle bundle = new Bundle();
        bundle.putSerializable(key,value);
        bundle.putString(key1,value1);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }
//    public static  void  JumpGoH5(String tital,String net_url)
//    {
//        Intent intent = new Intent();
//        intent.setClass(activity, GoH5Activity.class);
//
//        Bundle mBundle = new Bundle();
//        mBundle.putString(AppAllKey.GOH5_TITAL_KEY, tital);
//        mBundle.putString(AppAllKey.GOH5_ARTICAL_KEY, net_url);
//        intent.putExtras(mBundle);
//        activity.startActivity(intent);
//    }

}
