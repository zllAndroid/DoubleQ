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

import com.alibaba.fastjson.JSON;
import com.android.volley.VolleyError;
import com.mding.IChatCallBack;
import com.mding.IChatRequst;
import com.mding.chatfeng.about_application.BaseApplication;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.model.DataLogin;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
import com.projects.zll.utilslibrarybyzll.aboutvolley.VolleyInterface;
import com.projects.zll.utilslibrarybyzll.aboutvolley.VolleyRequest;
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
            isBind=true;
            Log.e("mACache","---------------------------->>>"+ws);
            if (!StrUtils.isEmpty(ws))
                if (wsManager == null) {
                    wsManager = new WsManager.Builder(getBaseContext())
                            .client(
                                    new OkHttpClient().newBuilder()
                                            .pingInterval(250, TimeUnit.SECONDS)
                                            .retryOnConnectionFailure(true)
                                            .build())
                            .needReconnect(true)
//                            TODO 固定ws
//                                                .wsUrl("ws://192.168.4.133:5053")
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
//            String  data = intent.getStringExtra("data");
//            if (!StrUtils.isEmpty(data))
//            {
////                WsBindUid
//                DataLogin dataLogin = JSON.parseObject(data, DataLogin.class);
//                DataLogin.RecordBean record = dataLogin.getRecord();
//                String userToken = record.getUserToken();
//                String userId = record.getUserId();
//                wsManager.sendMessage(SplitWeb.getSplitWeb().WsBindUid(userId,userToken));
//            }
//            /*  connectionWs("第一次连"+data);*/
//            MyLog.e("isBind",isBind+"---------------------isBind--------------------------------"+data);
            //启动OK3，在OK3中判断UI状态，ChatService状态，然后执行消息通知栏
        }catch (Exception e){
            AppConfig.logs("WsChannelService->:报错了");
            isBind=true;
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
    public ACache getTACache(){
            aCache =  ACache.get(getBaseContext());
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

    public static boolean isBind=true;

    private final WsStatusListener wsStatusListener = new WsStatusListener() {
        @Override
        public void onOpen(Response response) {
            super.onOpen(response);
            AppConfig.logs("连接成功");
            MyLog.e("onOpen","----------是否启动绑定-------------->>>>"+isBind);
            String userId = getTACache().getAsString(AppAllKey.USER_ID_KEY);
            String userToken= getTACache().getAsString(AppAllKey.USER_Token);
            if(!StrUtils.isEmpty(userId))
            wsManager.sendMessage(SplitWeb.getSplitWeb().WsBindUid(userId,userToken));
            MyLog.e("onOpen","----------启动绑定-------------->>>>"+SplitWeb.getSplitWeb().WsBindUid(userId,userToken));
            initOff();
//            wsManager.sendMessage(SplitWeb.getSplitWeb().bindUid());
            if (isBind)
            {
//                MyLog.e("onOpen","----------启动绑定-------------->>>>"+SplitWeb.getSplitWeb().bindUid());
            }
//            callbacks.recevieContactsList();
        }
        private void initOff() {
            String http = (String) SPUtils.get(getApplication(),AppConfig.TYPE_URL, "123");
            Log.e("result","拉去请求结果url=="+SplitWeb.getSplitWeb().pullMergeChat(http));
            try {
                VolleyRequest.RequestGet(getApplication(),SplitWeb.getSplitWeb().pullMergeChat(http), new VolleyInterface(VolleyInterface.listener,VolleyInterface.errorListener) {
                    @Override
                    public void onSuccess(final String result) {
                        Log.e("result","拉去请求结果=="+result);
                    }
                    @Override
                    public void onError(VolleyError result) {
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("result","拉去请求结果url=="+"错误");
            }
        }
        @Override
        public void onMessage(String text) {
            super.onMessage(text);
            String backMethod = HelpUtils.backMethod(text);
            String only = HelpUtils.backOnly(text);
            MyLog.e("onMessage","only="+only+"---text="+text);

            if (only.equals("1"))
//            if (only.equals("1")&&backMethod.equals("bindUid"))
            {
//            sendWebHaveDialog(SplitWeb.getSplitWeb().bindUid(),"断线重连中...","重连成功");
                if (!StrUtils.isEmpty(SplitWeb.getSplitWeb().getUserId()))
                {
//                    wsManager.sendMessage(SplitWeb.getSplitWeb().bindUid());
                    String userId = getTACache().getAsString(AppAllKey.USER_ID_KEY);
                    String userToken= getTACache().getAsString(AppAllKey.USER_Token);
                    wsManager.sendMessage(SplitWeb.getSplitWeb().WsBindUid(userId,userToken));
                    MyLog.e("onOpen","----------接收断线重新绑定-------------->>>>"+SplitWeb.getSplitWeb().WsBindUid(userId,userToken));
                }
            }
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