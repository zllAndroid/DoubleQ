package com.doubleq.xm6leefunz.about_utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.MyApplication;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.main_code.about_login.LoginActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_personal.about_activity.MineSetActivity;
import com.projects.zll.utilslibrarybyzll.about_dialog.CustomDialog;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboutsystem.WindowBugDeal;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


/**
 * Created by Administrator on 2017/9/14 0014.
 */

public class HelpUtils {
    public static Activity activity = AppManager.getAppManager().currentActivity();
    public static Activity getACt() {

        return AppManager.getAppManager().currentActivity();
    }
    // 判断网络连接状态
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static int getLocalVersion(Context mcon) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = mcon.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(mcon.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
//            Log.d("TAG", "本软件的版本号。。" + localVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }
    public static String getLocalVersionName() {
        String localVersion = "";
        try {
            PackageInfo packageInfo = MyApplication.getAppContext().getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(MyApplication.getAppContext().getPackageName(), 0);
            localVersion = packageInfo.versionName;
//            Log.e("TAG", "本软件的版本号。。" + localVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }
    public static String HttpIsSucess(String result){
        CustomDialog.Builder mBuilder;
        if (!result.equals("")&&result!=null) {

            JSONObject object = null;
            try {
                object = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String code = object.optString("code").toString().trim();
            switch (code) {
                case AppConfig.CODE_OK:
                    return code;
                case AppConfig.CODE_TIMEOUT:
                    return "1007";
//                    break;
                case AppConfig.CODE_TOKEN_OUT:
                    SplitWeb.USER_ID="";
                    AppManager.getAppManager().finishAllActivity();
                    IntentUtils.JumpTo(LoginActivity.class);
                    getACt().overridePendingTransition(0,0);
                    ACache.get(getACt()).clear();
                    SPUtils.clear(getACt());
                    return code;
                default:
                    String msg = object.optString("msg").toString().trim();
                    return msg;
            }

        }
        return "10086";
    }
    public static String HttpIsSucessLogin(String result){
        CustomDialog.Builder mBuilder;
        if (!result.equals("")&&result!=null) {

            JSONObject object = null;
            try {
                object = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String code = object.optString("code").toString().trim();
            switch (code) {
                case AppConfig.CODE_OK:
                    return code;
                case AppConfig.CODE_TIMEOUT:
//                    final Activity activity = AppManager.getAppManager().currentActivity();
//                    Tip.getDialogOne(activity, "登录超时，请重新登录...", new Tip.OnClickSureListener() {
//                        @Override
//                        public void onClickSure() {
//                            Intent intent_recharge = new Intent(activity, LoginActivity.class);
//                            ACache.get(MyApplication.getAppContext()).clear();
//                            AppManager.getAppManager().finishAllActivity();
//                            activity.startActivity(intent_recharge);
//                        }
//                    });
//                    final Activity activity = AppManager.getAppManager().currentActivity();
//                    Tip.getDialogOneClick(activity, "登录超时，请重新登录...", new Tip.OnClickSureListener() {
//                        @Override
//                        public void onClickSure() {
//                            Intent intent_recharge = new Intent(activity, LoginActivity.class);
//                            ACache.get(MyApplication.getAppContext()).clear();
//                            AppManager.getAppManager().finishAllActivity();
//                            activity.startActivity(intent_recharge);
//                        }
//                    });
                    return "1007";
//                    break;
                case AppConfig.CODE_TOKEN_OUT:
                    return code;
                case AppConfig.CODE_EPC:
                    return code;
                default:
                    String msg = object.optString("msg").toString().trim();
                    return msg;
            }

        }
        return "10086";
    }
    public static String backMethod(String result){
        if (!result.equals("")&&result!=null) {
            JSONObject object = null;
            try {
                object = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String code = object.optString("method").toString().trim();
            return code;
        }
        return "参数错误";
    }
    public static String backMD5(String result){
        if (!result.equals("")&&result!=null) {
            JSONObject object = null;
            try {
                object = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String code = object.optString("verificationMD5Type").toString().trim();
            return code;
        }
        return "";
    }
    public static String backOnly(String result){
        if (!result.equals("")&&result!=null) {
            JSONObject object = null;
            try {
                object = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String code = object.optString("only").toString().trim();
            return code;
        }
        return "参数错误";
    }

    public static Drawable loadImageFromNetwork(String urladdr) {
        Drawable drawable = null;
        try{
            //judge if has picture locate or not according to filename
            drawable = Drawable.createFromStream(new URL(urladdr).openStream(), "image.jpg");
        }catch(IOException e){
            Log.d("test",e.getMessage());
        }
        if(drawable == null){
            Log.d("test","null drawable");
        }else{
            Log.d("test","not null drawable");
        }
        return drawable;
    }
    //网络url图像转换成bitmap
    public static Bitmap getBitmap(String url) {
        URL fileUrl = null;
        Bitmap bitmap = null;
        try {
            fileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) fileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


}
