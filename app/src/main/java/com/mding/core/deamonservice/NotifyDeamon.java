/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.mding.core.deamonservice;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.RemoteException;

import com.mding.ILoginCallBack;
import com.mding.ILoginRequst;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_application.BaseApplication;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.core.pushservice.ChannelDeamon;
import com.mding.models.LoginModel;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;


/**
 * 1、启动通道守护ChannelDeamon.java，并与它建立连接，同时获得方法调用的对象->>iLoginRequst
 * 2、使用对象iLoginRequst尝试登入方法，并且获得登入是否成功的回执
 * 3、将结果返回给登入的入口调用者
 */
public class NotifyDeamon extends Service {
    //默认为自动登入,一般指进程被结束后自动复活进行自动登入
    private boolean isAutoLogin=true;
    public NotifyDeamonConn conn;
    public ILoginRequst iLoginRequstByChannel;
    public ILoginCallBack callback;
    //音乐保活
    private MediaPlayer bgmediaPlayer;
    //cpu常开，息屏保活
    private PowerManager pm;
    private PowerManager.WakeLock wakeLock;
     //定时唤醒的时间间隔，5分钟,前台保活
    public final static int ALARM_INTERVAL = 5 * 60 * 1000;
    public final static int WAKE_REQUEST_CODE = 6666;
    @Override
    public void onCreate() {
        super.onCreate();
        AppConfig.logs("NotifyDeamon->onCreate");
        if(conn == null){
            conn = new NotifyDeamonConn();
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        //凡是主动连接到本守护进程的，都标记为手动自主登入
        isAutoLogin=false;
     return binder;
    }

    @SuppressLint("InvalidWakeLockTag")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //绑定远程服务
        startService(new Intent(this, ChannelDeamon.class));
        bindService(new Intent(this, ChannelDeamon.class),conn,Context.BIND_IMPORTANT);

        pm=(PowerManager)getSystemService(Context.POWER_SERVICE);
        wakeLock=pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"CPUKeepRunning");
        wakeLock.acquire();

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent();
        alarmIntent.setAction(NotifyService.GRAY_WAKE_ACTION);
        PendingIntent operation = PendingIntent.getBroadcast(this, WAKE_REQUEST_CODE, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), ALARM_INTERVAL, operation);

        if(bgmediaPlayer == null){
            bgmediaPlayer = MediaPlayer.create(this, R.raw.running);
            bgmediaPlayer.setLooping(true);
            bgmediaPlayer.start();
        }
        return START_STICKY;
    }

    /**
     * 1、此处为登入总入口
     */
    ILoginRequst.Stub binder=new ILoginRequst.Stub() {
        @Override
        public void register(ILoginCallBack callbacks) throws RemoteException {
            callback=callbacks;
        }

        @Override
        public void loginRequest(String loginUrl) throws RemoteException {
            AppConfig.logs("开始启动登入,调用ChannelDeamon远程的实现对象"+loginUrl);
            //开始请求登入，此处由登入者触发，一般在activity处开始触发
            startLogin(loginUrl);
        }

    };



    /**
     * 2、与通道守护进程联动，即ChannelDeamon.java，主要任务是将登入所需的账号密码信息传递给ChannelDeamon，并回执回来登入和连接结果
     */
    class NotifyDeamonConn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iLoginRequstByChannel= ILoginRequst.Stub.asInterface(service);

            if (iLoginRequstByChannel!=null)
            {
                AppConfig.logs("iLoginRequstByChannel创建成功");
                try {
                    iLoginRequstByChannel.register(new ILoginCallBack.Stub(){
                        @Override
                        public void readData(LoginModel mLoginModel) throws RemoteException {
                  /*          AppConfig.logs("远程“ChannelDeamon”已经登陆成功,现在数据回调回来");
                            //登入成功后的结果回调给手动登入者
                            if(isAutoLogin)
                            {
                                AppConfig.logs("自动登入成功,登入状态：-"+mLoginModel.isLoginSuccess());
                            }else{
                                AppConfig.logs("手动登入状态："+mLoginModel.isLoginSuccess());
                                callback.readData(mLoginModel);
                            }*/
                            }

                        @Override
                        public void onFailure(String res) throws RemoteException {
                            callback.onFailure(res);
                        }

                        @Override
                        public void onSuccess(String res) throws RemoteException {
                            //isAutoLogin
                            callback.onSuccess(res);
                        }
                    });
//                    startLogin(null);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }else{
                AppConfig.logs("iLoginRequstByChannel创建失败");
            }

            AppConfig.logs("与守护通道连接建立成功");
        }


        @Override
        public void onServiceDisconnected(ComponentName name) {
            //远程服务被干掉,连接断掉的时候走此回调
            AppConfig.logs("断开连接--------");
            // 登入者将进程杀死后，此时与ChannelDeamon的连接会被断开,所以这里需要复活自己，然后重新走一遍业务逻辑
            startService(new Intent(getApplicationContext(), NotifyDeamon.class));
        }
    }
    public   ACache getaCache(){
        if (aCache==null)
        {
            aCache =  ACache.get(this);
        }
        return aCache;
    }
    ACache aCache;
    /**
     * 登入的公共方法
     * @param loginUrl
     * @throws RemoteException
     */
        public void startLogin(String loginUrl) throws RemoteException {
            if(iLoginRequstByChannel!=null)
            {
                if(isAutoLogin)
                {
                    AppConfig.logs("我是自动登入--------");
                    AppConfig.logs("我是自动登入--------启动成功");
                    //设置自动登入的账号密码
/*                    LoginModel mLoginModelz = new LoginModel();
                    mLoginModelz.setUserid("账号12331232ddddd");
                    mLoginModelz.setPsw("密码123233213123");*/
                    loginUrl="http://www.baidu.com";
//                    ACache aCache = BaseApplication.getApp().getaCache();
//                    getaCache().getAsString()

                }
                try {
                    iLoginRequstByChannel.loginRequest(loginUrl);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }


}
