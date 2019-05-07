package com.mding.chatfeng.about_application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.PowerManager;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.zll.websocket.WebSocketServiceConnectManager;

import java.io.File;

public class BaseApplication extends Application implements AppView{
    private static BaseApplication mInstance;
    public static Context mContext;
    /**
     * 屏幕宽度
     */
    public static int screenWidth;
    /**
     * 屏幕高度
     */
    public static int screenHeight;
    /**
     * 屏幕密度
     */
    public static float screenDensity;
        public static WebSocketServiceConnectManager mConnectManager =null;
    public static ACache aCache;
    public static  boolean isMain;//MainActivity是否更新
    PresenterApp presenterApp;
    private static final int MEMORY_SIZE = 5 * 1024 * 1024;
    private static final int DISK_SIZE = 20 * 1024 * 1024;
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化 Image-Loader
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCache(new LruMemoryCache(MEMORY_SIZE))
                .diskCache(new UnlimitedDiscCache(new File(getCacheDir(),"caches")))
                .diskCacheSize(DISK_SIZE)
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(configuration);


        isMain=true;
        mContext = this;
        mInstance = this;
        if (presenterApp ==null) {
            presenterApp = new PresenterApp(this,this);
        }
        presenterApp.initApp(getApplicationContext());
        initScreenSize();
    }

    public  void startBase(Context mc)
    {
        presenterApp.start(mc);
    }
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        super.onTerminate();
        presenterApp.OnDestry();
    }
    public  WebSocketServiceConnectManager getmConnectManager() {
        if (presenterApp !=null)
            return presenterApp.mConnectManager;
        else
            return null;
    }
    public  void send(String msg) {
        if (presenterApp !=null)
        presenterApp.sendText(msg);
    }

    public static BaseApplication getApp() {
        return mInstance;
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static Context getInstance() {
        return mInstance;
    }
    /**
     * 初始化当前设备屏幕宽高
     */
    private void initScreenSize() {
        DisplayMetrics curMetrics = getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = curMetrics.widthPixels;
        screenHeight = curMetrics.heightPixels;
        screenDensity = curMetrics.density;
    }
    public  static  final String  TAG="myapplication";
//获取缓存对象
    public static  ACache getaCache(){
        if (aCache==null)
        {
            aCache =  ACache.get(getAppContext());
        }
        return aCache;
    }
    public static AppCompatActivity getACt(){
        return  AppManager.getAppManager().currentActivity();
    }
//    发送websocket请求
    @Override
    public void sendData(String request) {
        if (presenterApp !=null)
            presenterApp.sendText(request);
    }
//    接收websocket请求
    @Override
    public void backData(String request) {
    }
//    获取socket连接管理器对象
    @Override
    public void getmConnectManager(WebSocketServiceConnectManager mmConnectManager) {
        mConnectManager=mmConnectManager;
    }
}
