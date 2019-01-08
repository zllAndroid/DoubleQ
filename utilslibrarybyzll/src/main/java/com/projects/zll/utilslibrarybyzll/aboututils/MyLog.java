package com.projects.zll.utilslibrarybyzll.aboututils;
import android.util.Log;

/**
 * 日志输出工具类
 */
public class MyLog {
    public static int LOG_LEVEL =6;
    public static int ERROR = 1;
    public static int WARN = 2;
    public static int INFO = 3;
    public static int DEBUG = 4;
    public static int VERBOS = 5;
    public static void e(String tag,String msg){
        if(LOG_LEVEL>ERROR)
            Log.e(tag, msg);
    }

    public static void w(String tag,String msg){
        if(LOG_LEVEL>WARN)
            Log.w(tag, msg);
    }
    public static void i(String tag,String msg){
        if(LOG_LEVEL>INFO)
            Log.i(tag, msg);
    }
    public static void d(String tag,String msg){
        if(LOG_LEVEL>DEBUG)
            Log.d(tag, msg);
    }
    public static void v(String tag,String msg){
        if(LOG_LEVEL>VERBOS)
            Log.v(tag, msg);
    }
//    //读取BuildConfig.LOG_DEBUG 签名时为FALSE 不打印 debug时为true 打印
//    public static void i(String tag,String message){
//        if(BuildConfig.LOG_DEBUG)Helper.logi(tag,message);
//    }
//    public static void d(String tag,String message){
//        if(BuildConfig.LOG_DEBUG)Log.d(tag,message);
//    }
//    public static void e(String tag,String message){
//        if(BuildConfig.LOG_DEBUG)Log.e(tag,message);
//    }
}
