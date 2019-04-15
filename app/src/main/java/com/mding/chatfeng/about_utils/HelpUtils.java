package com.mding.chatfeng.about_utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.mding.chatfeng.about_application.BaseApplication;
import com.mding.chatfeng.about_base.AppConfig;
import com.projects.zll.utilslibrarybyzll.about_dialog.CustomDialog;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Administrator on 2017/9/14 0014.
 */

public class HelpUtils {
    public static Activity activity = AppManager.getAppManager().currentActivity();
    public static Activity getACt() {

        return AppManager.getAppManager().currentActivity();
    }
//    // 判断网络连接状态
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

//    /**
//     * 判断当前网络是否可用(6.0以上版本)
//     * 实时
//     * @param context
//     * @return
//     */
//    public static boolean isNetworkConnected(Context context){
//        boolean isNetUsable = false;
//
//        ConnectivityManager manager = (ConnectivityManager)
//                context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
//            NetworkCapabilities networkCapabilities =manager.getNetworkCapabilities(manager.getActiveNetwork());
//            isNetUsable = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
//        }else
//        {
//            NetworkInfo mNetworkInfo = manager.getActiveNetworkInfo();
//            if (mNetworkInfo != null) {
//                return mNetworkInfo.isAvailable();
//            }
//        }
//
//
//        return isNetUsable;
//    }

    /* @author suncat
     * @category 判断是否有外网连接（普通方法不能判断外网的网络是否连接，比如连接上局域网）
     * @return
     */
//    public static final boolean ping() {
//        String result = null;
//        try {
//            String ip = "www.baidu.com";// ping 的地址，可以换成任何一种可靠的外网
//            Process p = Runtime.getRuntime().exec("ping -c 3 -w 100 " + ip);// ping网址3次
//            // 读取ping的内容，可以不加
//            InputStream input = p.getInputStream();
//            BufferedReader in = new BufferedReader(new InputStreamReader(input));
//            StringBuffer stringBuffer = new StringBuffer();
//            String content = "";
//            while ((content = in.readLine()) != null) {
//                stringBuffer.append(content);
//            }
//            Log.d("------ping-----", "result content : " + stringBuffer.toString());
//            // ping的状态
//            int status = p.waitFor();
//            if (status == 0) {
//                result = "success";
//                return true;
//            } else {
//                result = "failed";
//            }
//        } catch (IOException e) {
//            result = "IOException";
//        } catch (InterruptedException e) {
//            result = "InterruptedException";
//        } finally {
//            Log.d("----result---", "result = " + result);
//        }
//        return false;
//    }
//    public static final boolean NetworkPing(Context context) {
//
//        if (isNetworkConnected(context)) {
//            String result = null;
//            try {
//                String ip = "https://www.baidu.com/";// ping 的地址，可以换成任何一种可靠的外网
//                Process p = Runtime.getRuntime().exec("ping -c 3 -w 100 " + ip);// ping网址3次
//                // 读取ping的内容，可以不加
//                InputStream input = p.getInputStream();
//                BufferedReader in = new BufferedReader(new InputStreamReader(input));
//                StringBuffer stringBuffer = new StringBuffer();
//                String content = "";
//                while ((content = in.readLine()) != null) {
//                    stringBuffer.append(content);
//                }
//                Log.d("------ping-----", "result content : " + stringBuffer.toString());
//                // ping的状态
//                int status = p.waitFor();
//                if (status == 0) {
//                    result = "success";
//                    return true;
//                } else {
//                    result = "failed";
//                }
//            } catch (IOException e) {
//                result = "IOException";
//            } catch (InterruptedException e) {
//                result = "InterruptedException";
//            } finally {
//                Log.d("----result---", "result = " + result);
//            }
//            return false;
//        }else
//        {
//            return false;
//        }
//    }
    public static int getLocalVersion(Context mcon) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = mcon.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(mcon.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
            Log.d("TAG", "本软件的版本号。。" + localVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }
    public static String getLocalVersionName() {
        String localVersion = "";
        try {
            PackageInfo packageInfo = BaseApplication.getAppContext().getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(BaseApplication.getAppContext().getPackageName(), 0);
            localVersion = packageInfo.versionName;
//            Log.e("TAG", "本软件的版本号。。" + localVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }
    public static String HttpIsSucess(String result){
        CustomDialog.Builder mBuilder;
        try {
            if (!result.equals("")&&result!=null) {

                JSONObject object = null;
                try {
                    object = new JSONObject(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String code = object.optString("code").toString().trim();
                if(code==null)
                    return "";
                switch (code) {
                    case AppConfig.CODE_OK:
                        return code;
                    case AppConfig.CODE_TIMEOUT:
                        return "1007";
                    //                    break;
                    case AppConfig.CODE_TOKEN_OUT:
                        //                    SplitWeb.USER_ID="";
                        ////                    AppManager.getAppManager().finishAllActivity();
                        //                    IntentUtils.JumpTo(LoginActivity.class);
                        //                    getACt().overridePendingTransition(0,0);
                        //                    ACache.get(getACt()).clear();
                        //                    SPUtils.clear(getACt());
                        return code;
                    default:
                        String msg = object.optString("msg").toString().trim();
                        return msg;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
    public static String backLinkMan(String result,boolean isFriend){
        if (!result.equals("")&&result!=null) {
            JSONObject object = null;
            try {
                object = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String code = object.optString("record");
            if (!StrUtils.isEmpty(code)) {
                JSONObject object1 = null;
                try {
                    object1 = new JSONObject(code);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String friendInfo=null;
                if (isFriend) {
                    friendInfo = object1.optString("friendList");
                }else
                {
                    friendInfo = object1.optString("groupInfoList");
                }
                if(!StrUtils.isEmpty(friendInfo)){
                    com.alibaba.fastjson.JSONObject params = new com.alibaba.fastjson.JSONObject();
                    com.alibaba.fastjson.JSONArray objects = new com.alibaba.fastjson.JSONArray();
                    objects.add(friendInfo);
                    if (isFriend)
                        params.put("friendList", objects);
                    else
                        params.put("groupInfoList", objects);
                    String s = params.toJSONString();
                    String mStr=s.replace("\\","");
                    mStr=mStr.replace("\"[","");
                    mStr=mStr.replace("\"]","");
//                    MyLog.e("friendList","列表="+mStr);

//                    String json = JSON.toJSON(record).toString();
                    return mStr;
                }

            }

            return "";
        }
        return "";
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
