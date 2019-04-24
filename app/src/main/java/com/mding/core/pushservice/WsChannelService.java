/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: WsChannelService
 * Author: xm6leefun
 * Date: 2019/4/2 下午11:23
 * Description: chatfeng
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.mding.core.pushservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.mding.IChatCallBack;
import com.mding.IChatRequst;
import com.mding.core.AppConfig;
import com.rabtman.wsmanager.WsManager;
import com.rabtman.wsmanager.listener.WsStatusListener;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import okio.ByteString;


/**
 *
 * @ProjectName: ChatFengIM
 * @Package: my.maya.android.core.pushservice
 * @ClassName: WsChannelService
 * @Description: ws通道连接，数据收发通道
 * @Author: wdh
 * @CreateDate: 2019/4/2 下午11:23
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/2 下午11:23
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class WsChannelService extends Service {
    public IChatCallBack callbacks;
    private WsManager wsManager;
    @Override
    public IBinder onBind(Intent intent) {
        AppConfig.logs("WsChannelService-》onBind");
        return binders;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AppConfig.logs("WsChannelService->:onStartCommand");
       try{
            String  data = intent.getStringExtra("data");
            AppConfig.logs("WsChannelService->:"+data);
            /*  connectionWs("第一次连"+data);*/

            //启动OK3，在OK3中判断UI状态，ChatService状态，然后执行消息通知栏
        }catch (Exception e){
            AppConfig.logs("WsChannelService->:报错了");
        }

        return START_STICKY;
    }


    //（1）与登入界面互动
    IChatRequst.Stub binders=new IChatRequst.Stub() {
        @Override
        public void register(IChatCallBack callback) throws RemoteException {
            AppConfig.logs("---IChatCallBack---register");
            callbacks=callback;

            if (wsManager != null) {
                wsManager.stopConnect();
                wsManager = null;
            }
            wsManager = new WsManager.Builder(getBaseContext())
                    .client(
                            new OkHttpClient().newBuilder()
                                    .pingInterval(15, TimeUnit.SECONDS)
                                    .retryOnConnectionFailure(true)
                                    .build())
                    .needReconnect(true)
                    .wsUrl("ws:\\/\\/120.78.92.225:9093")
                    .build();
            wsManager.setWsStatusListener(wsStatusListener);
            wsManager.startConnect();
        }

        @Override
        public boolean sendMsg(String mSendMsg) throws RemoteException {
            if (wsManager != null && wsManager.isWsConnected()) {
                return wsManager.sendMessage(mSendMsg);
            }else {
                return false;
            }
        }

        @Override
        public void getHeadImg(String mGetHeadImg) throws RemoteException {

        }

        @Override
        public void onStartGetData() throws RemoteException {

        }

        @Override
        public void getDataBySqlite(String type) throws RemoteException {

        }

        @Override
        public void initData() throws RemoteException {

        }

        @Override
        public String getDataByUrl(String url) throws RemoteException {
           callbacks.initSuccess(url);
            return "JSONtop";
        }
    };



    private final WsStatusListener wsStatusListener = new WsStatusListener() {
        @Override
        public void onOpen(Response response) {
            super.onOpen(response);
            AppConfig.logs("连接成功");
        }

        @Override
        public void onMessage(String text) {
            super.onMessage(text);
            AppConfig.logs(text);
        }

        @Override
        public void onMessage(ByteString bytes) {
            super.onMessage(bytes);
        }

        @Override
        public void onReconnect() {
            super.onReconnect();
        }

        @Override
        public void onClosing(int code, String reason) {
            super.onClosing(code, reason);
        }

        @Override
        public void onClosed(int code, String reason) {
            super.onClosed(code, reason);
        }

        @Override
        public void onFailure(Throwable t, Response response) {
            super.onFailure(t, response);
            AppConfig.logs("连接失败");

        }
    };



}