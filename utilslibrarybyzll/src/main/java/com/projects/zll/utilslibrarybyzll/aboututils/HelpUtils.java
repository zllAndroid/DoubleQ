package com.projects.zll.utilslibrarybyzll.aboututils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;


import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboutsystem.WindowBugDeal;

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
//    判断网络请求
    public static boolean isNetWorkAvailable(final Context context) {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process pingProcess = runtime.exec("/system/bin/ping -c 1 www.baidu.com");
            int exitCode = pingProcess.waitFor();
            return (exitCode == 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static int getLocalVersion() {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = activity.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(activity.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
            Log.d("TAG", "本软件的版本号。。" + localVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

//    public static String getLocalVersionName() {
//        String localVersion = "";
//        try {
//            PackageInfo packageInfo =  MyApplication.getAppContext().getApplicationContext()
//                    .getPackageManager()
//                    .getPackageInfo( MyApplication.getAppContext().getPackageName(), 0);
//            localVersion = packageInfo.versionName;
//            Log.e("TAG", "本软件的版本号。。" + localVersion);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        return localVersion;
//    }
    /**
     * 获取当前应用程序的包名
     * @param context 上下文对象
     * @return 返回包名
     */
    public static String getAppProcessName(Context context) {
        //当前应用pid
        int pid = android.os.Process.myPid();
        //任务管理类
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //遍历所有应用
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.pid == pid)//得到当前应用
                return info.processName;//返回包名
        }
        return "";
    }
    public  static  void  IsNeiping()
    {
        String simpleName = AppManager.getAppManager().currentActivity().getClass().getSimpleName();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (!simpleName.equals("MainActivityMy")) {
//                if (!simpleName.equals("LoginActivity"))
//                {
//                     mtv = (TextView)AppManager.getAppManager().currentActivity().findViewById(R.id.include_top_margin10);
//                      mtv.setVisibility(View.VISIBLE);
//                }
                WindowBugDeal.checkDeviceHasNavigationBar(AppManager.getAppManager().currentActivity());
            }
        }
    }

//    public static  void  AppUpData(final Activity activity)
//    {
////        getAppManager().currentActivity().startActivity(intent);
//        VolleyRequest.RequestGet(activity, UrlGetBean.URL_GetMyTea(), new VolleyInterface(VolleyInterface.listener,VolleyInterface.errorListener) {
//            @Override
//            public void onSuccess(String result) {
//                String s = HelpUtils.HttpIsSucess(result);
//                if (s.equals("1"))
//                {
//                    UpdataData updataData = JSON.parseObject(result, UpdataData.class);
//                    UpdataData.EditionBean edition = updataData.getEdition();
//                }
//            }
//            @Override
//            public void onError(VolleyError result) {
//            }
//        });
//    }
//    public static String HttpIsSucess(String result){
//         CustomDialog.Builder mBuilder;
//        if (!result.equals("")&&result!=null) {
//
//            JSONObject object = null;
//            try {
//                object = new JSONObject(result);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            String code = object.optString("code").toString().trim();
//            if (code.equals(CommonParameter.CODE_SUCCESS)) {
//                return code;
//            }
//            else {
//                if (code.equals(CommonParameter.CODE_TIMEOUT)) {
//                    DialogUtils.showDialogOne("登录超时，请重新登录...", new DialogUtils.OnClickSureListener() {
//                        @Override
//                        public void onClickSure() {
//                            Intent intent_recharge = null;
//                            try {
//                                URL_GET.USER_TOKEN ="";
//                                intent_recharge = new Intent(activity, LoginActivity.class);
//                                ACache.get(activity).clear();
//                                AppManager.getAppManager().finishAllActivity();
//                                activity.startActivity(intent_recharge);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
////                    CustomDialog mDialog;
////                  final Activity activity = AppManager.getAppManager().currentActivity();
////                    mBuilder = new CustomDialog.Builder(activity);
////                    mBuilder.setMessage("登录超时，请重新登录...");
////                    mBuilder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
////                        @Override
////                        public void onClick(DialogInterface dialog, int which) {
////                            Intent intent_recharge = null;
////                            try {
////                                URL_GET.USER_TOKEN ="";
////                                intent_recharge = new Intent(activity, LoginActivity.class);
////                                ACache.get(activity).clear();
////                                AppManager.getAppManager().finishAllActivity();
////                                activity.startActivity(intent_recharge);
////                            } catch (Exception e) {
////                                e.printStackTrace();
////                            }
////                        }
////                    });
////                    mDialog = mBuilder.create();
////                    mDialog.setCancelable(false);
////                    mDialog.show();
////                  AppManager.getAppManager().finishAllExceptCurrentActivity(LoginActivity.class);
//                    return code;
//                } else {
//                    String msg = object.optString("msg").toString().trim();
//                    return msg;
//                }
//            }
//        }
//        return "参数错误";
//    }
    public static boolean IsSucessRecord(String result){
        if (!result.equals("")&&result!=null) {
            JSONObject object = null;
            try {
                object = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String code = object.optString("code").toString().trim();
            if (code.equals(AppAllKey.CODE_OK)) {
                String record = object.optString("record").toString().trim();
                Log.e("record","record="+record);
//                if (record!=null)
                if (!StrUtils.isEmpty(record))
                {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public static Drawable loadImageFromNetwork(String urladdr) {
// TODO Auto-generated method stub
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
