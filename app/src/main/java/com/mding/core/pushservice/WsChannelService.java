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
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.mding.IChatCallBack;
import com.mding.IChatRequst;
import com.mding.chatfeng.about_application.BaseApplication;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
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
    public IChatCallBack callbacks=null;
    private WsManager wsManager;

    @Override
    public IBinder onBind(Intent intent) {
        AppConfig.logs("WsChannelService-》onBind");
        MultiDex.install(getBaseContext());
        return binders;
    }

    ACache mACache=null;

    public ACache getACache() {
        if (mACache==null)
        {
            mACache=ACache.get(BaseApplication.getAppContext());
        }
        return mACache;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            String ws = (String) SPUtils.get(BaseApplication.getAppContext(), AppConfig.TYPE_WS_REQUEST, "123");
            Log.e("mACache","---------------------------->>>"+ws);
            if (!StrUtils.isEmpty(ws))
                if (wsManager == null) {
                    wsManager = new WsManager.Builder(getBaseContext())
                            .client(
                                    new OkHttpClient().newBuilder()
                                            .pingInterval(40, TimeUnit.SECONDS)
                                            .retryOnConnectionFailure(true)
                                            .build())
                            .needReconnect(true)
                            //                    .wsUrl("ws://120.78.92.225:9093")
                            .wsUrl(ws)
                            .build();
                    wsManager.setWsStatusListener(wsStatusListener);
                    wsManager.startConnect();

                    AppConfig.logs("-------------------->>"+ wsManager.isWsConnected()+"URL地址："+ ws);

                }else
                {
                    ToastUtil.show("加载失败，请退出重试");
                }
        } catch (Exception e) {
            e.printStackTrace();

        }

        try{
            String  data = intent.getStringExtra("data");
            /*  connectionWs("第一次连"+data);*/
            AppConfig.logs("WsChannelService->:报错了"+data);
            //启动OK3，在OK3中判断UI状态，ChatService状态，然后执行消息通知栏
        }catch (Exception e){
            AppConfig.logs("WsChannelService->:报错了");
        }

        return START_STICKY;
    }

    public ACache getaCache(){
        if (aCache==null)
        {
            aCache =  ACache.get(getBaseContext());
        }
        return aCache;
    }
    ACache aCache;
    //（1）与登入界面互动
    IChatRequst.Stub binders=new IChatRequst.Stub() {
        @Override
        public void register(IChatCallBack callback) throws RemoteException {
            AppConfig.logs("---IChatCallBack---register");
            callbacks=callback;


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
            wsManager.fristStartConnByNetChange();
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
            wsManager.sendMessage(SplitWeb.getSplitWeb().bindUid());
//            callbacks.recevieContactsList();
        }

        @Override
        public void onMessage(String text) {
            super.onMessage(text);
            AppConfig.logs("onMessage==>>"+text);
            String backMethod = HelpUtils.backMethod(text);
            if (backMethod.equals("")) {
            }


            try {
                if(callbacks!=null)
                    callbacks.recevieMsg("1",text);

            } catch (Exception e) {
                e.printStackTrace();
            }
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
            AppConfig.logs("连接失败"+t.toString());
            try {
                callbacks.onFail("WS->onFail");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        wsManager.stopConnect();
        wsManager = null;
    }

}