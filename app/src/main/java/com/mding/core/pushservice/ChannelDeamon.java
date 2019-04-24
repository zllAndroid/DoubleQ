/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ChannelDeamon
 * Author: xm6leefun
 * Date: 2019/4/2 下午11:12
 * Description: chafeng
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.mding.core.pushservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.mding.ILoginCallBack;
import com.mding.ILoginRequst;
import com.mding.core.AppConfig;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 *
 * @ProjectName: ProcessKeep-master
 * @Package: my.maya.android.core.pushservice
 * @ClassName: ChannelDeamon
 * @Description:  处理通道保活事务
 * @Author: wdh
 * @CreateDate: 2019/4/2 下午11:12
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/2 下午11:12
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ChannelDeamon extends Service {
    private final static String  TAG = AppConfig.TAG;
    private boolean needToRun=true;
    private Intent intent;


    @Override
    public void onCreate() {
        super.onCreate();
        AppConfig.logs("初始化创建，ChannelDeamon:onCreate"+AppConfig.ok);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AppConfig.logs("ChannelDeamon:onStartCommand");
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        AppConfig.logs("ChannelDeamon:onBind");

        return binder;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        needToRun=false;
        return super.onUnbind(intent);
    }

    //（1）与登入界面互动
    ILoginRequst.Stub binder=new ILoginRequst.Stub() {
        public ILoginCallBack callback;

        @Override
        public void register(ILoginCallBack callback) throws RemoteException {
            this.callback=callback;
        }


        @Override
        public void loginRequest( String loginUrl) throws RemoteException {
            try{
                //ok3请求
                OkHttpClient mOkHttpClient = new OkHttpClient();
                Request.Builder requestBuilder = new Request.Builder().url(loginUrl);
                requestBuilder.method("GET",null);
                Request request = requestBuilder.build();
                Call mcall = mOkHttpClient.newCall(request);

                mcall.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        try {
                            callback.onFailure("请求失败");
                        } catch (RemoteException e1) {
                            e1.printStackTrace();
                        }
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        if (null != response.cacheResponse()) {
                            String str = response.cacheResponse().toString();
                            try {
                                callback.onFailure(str);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                            AppConfig.logs("=======if========="+str);
                        } else if(!response.isSuccessful()) {
                            String str = response.networkResponse().toString();
                            try {
                                callback.onFailure(str);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                            AppConfig.logs("========elseIF========"+str);

                        }else{
                            String str =response.networkResponse().toString();
                            try {
                                callback.onSuccess(str);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                            //启动 WsChannelService 进入消息通道服务
                            if(intent==null)
                            {
                                //启动WS通道连接服务
                                intent=new Intent(ChannelDeamon.this, WsChannelService.class);
                                intent.putExtra("data","token:12312314123123123");
                                startService(intent);
                            }else {
                                //尝试重连Ws，此次肯定至少是从第二次开始连接
                                intent.putExtra("data","token:1231231412312312111111---3");
                                startService(intent);
                            }
                        }
                    }
                });
            }catch (Exception e){
                callback.onFailure("请求连接格式不规范");
            }





        }

    };


  /*  //（2）与通道服务互动，主要获取回执，如果在指定时间内没有回执，则重启通道服务
    ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            //获得Service中的WsChannelService
            mIGetData=(WsChannelService.WsCreateBinder)service;
            //新建接口，并且获得数据
            mIGetData.register(new IGetDataCallBacks() {
                @Override
                public void receiveData(String msg) {
                    //此处还可以拓展一个定时监听回执接口，如果在指定时间内，得不到回执，那么就重启通道（对象为空也要重启通道，不在此处做）
                    AppConfig.logs(msg+"-------------本条消息发送成功");

                }

            });
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //这里写入子线程需要做的工作
                    while(true){
                        try {
                            Thread.sleep(1000);
                            mIGetData.sendMsgToWs("你好！！！");

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            AppConfig.logs("-------------sendMsgToWs执行崩溃");
                        }
                    }
                }
            }).start(); //启动线程;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };*/


    @Override
    public void onDestroy() {
        super.onDestroy();
//        unbindService(conn);
        stopService(intent);
    }



}

