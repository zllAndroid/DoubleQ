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

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.android.volley.VolleyError;
import com.mding.ILoginCallBack;
import com.mding.ILoginRequst;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.NetWorkUtlis;
import com.mding.chatfeng.main_code.about_login.LoginActivity;
import com.mding.model.DataLogin;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
import com.projects.zll.utilslibrarybyzll.aboutvolley.VolleyInterface;
import com.projects.zll.utilslibrarybyzll.aboutvolley.VolleyRequest;

import java.io.IOException;

import io.realm.Realm;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.mding.chatfeng.main_code.mains.PersonalFragment.IMAGE_BASE64;


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
        public void loginRequest(String loginUrl) throws RemoteException {
            initHttp(callback,loginUrl);
//            AppConfig.logs("========elseIF==str======"+loginUrl);
//            try{
//                AppConfig.logs("=======loginUrl========="+loginUrl);
//                //ok3请求
//                OkHttpClient mOkHttpClient = new OkHttpClient();
//                Request.Builder requestBuilder = new Request.Builder().url(loginUrl);
//                requestBuilder.method("GET",null);
//                Request request = requestBuilder.build();
//                Call mcall = mOkHttpClient.newCall(request);
//
//                //get
//                mOkHttpClient.newCall(request).enqueue(null);
//                //
//                mOkHttpClient.newCall(request).enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        try {
//
//                            callback.onFailure("请求失败");
//                        } catch (RemoteException e1) {
//                            e1.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        String body=response.body().string();
//                        if (null != response.cacheResponse()) {
//                            String str = response.cacheResponse().toString();
//                            try {
//                                callback.onFailure(str);
//                            } catch (RemoteException e) {
//                                e.printStackTrace();
//                            }
//                        } else if(!response.isSuccessful()) {
//                            String str = response.networkResponse().toString();
//                            try {
//                                callback.onFailure(str);
//                            } catch (RemoteException e) {
//                                e.printStackTrace();
//                            }
//                        }else{
//                            String str =response.networkResponse().toString();
//                            AppConfig.logs("========elseIF==str======"+str);
//                            try {
//                                MyLog.e("body","body="+body);
////                                initDataDeal(body);
//                                callback.onSuccess(body );
//                            } catch (RemoteException e) {
//                                e.printStackTrace();
//                            }
//                            //启动 WsChannelService 进入消息通道服务
////                            if(intent==null)
////                            {
////                                //启动WS通道连接服务
////                                intent=new Intent(ChannelDeamon.this, WsChannelService.class);
////                                intent.putExtra("data",body);
////                                startService(intent);
////                            }else {
////                                //尝试重连Ws，此次肯定至少是从第二次开始连接
////                                intent.putExtra("data",body);
////                                startService(intent);
////                            }
//                        }
//                    }
//                });
//            }catch (Exception e){
//                callback.onFailure("请求连接格式不规范");
//            }

        }

    };

    private void initHttp(final  ILoginCallBack callback,String loginUrl) {

//        Activity activity = AppManager.getAppManager().currentActivity();
        VolleyRequest.RequestGet(getApplicationContext(),loginUrl, new VolleyInterface(VolleyInterface.listener,VolleyInterface.errorListener) {
            @Override
            public void onSuccess(final String result) {
                MyLog.e("result","请求结果result----------=="+result);
                try {
                    callback.onSuccess(result);

                    //启动 WsChannelService 进入消息通道服务
                            if(intent==null)
                            {
                                //启动WS通道连接服务
                                intent=new Intent(ChannelDeamon.this, WsChannelService.class);
                                intent.putExtra("data",result);
                                startService(intent);
                            }else {
                                //尝试重连Ws，此次肯定至少是从第二次开始连接
                                intent.putExtra("data",result);
                                startService(intent);
                            }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onError(VolleyError result) {
//                ToastUtil.show(AppConfig.ERROR);
            }
        });
    }

    private void initDataDeal(String body) {

        DataLogin dataLogin = JSON.parseObject(body, DataLogin.class);
        DataLogin.RecordBean record = dataLogin.getRecord();
        if (record != null)
            SaveLoginResultData(record);
    }

    public ACache getaCache(){
        if (mCache==null)
        {
            mCache =  ACache.get(this);
        }
        return mCache;
    }
    ACache mCache;
    private void SaveLoginResultData(DataLogin.RecordBean userInfo) {
        String json = JSON.toJSON(userInfo).toString();
        mCache.clear();
        mCache.put(AppAllKey.TOKEN_KEY, json);
        mCache.put(IMAGE_BASE64, userInfo.getHeadImg());
        mCache.put(AppConfig.QR_CODE, userInfo.getQrcode());
        if (userInfo!=null) {
//            String is_first_login = userInfo.getIsFirstLogin();
//            if (is_first_login.equals("1"))
//                isFirst = true;
//            else
//                isFirst = false;

            initSetData(userInfo);
        }
    }
    private void initSetData(DataLogin.RecordBean dataLogin) {
        SPUtils.put(this, AppAllKey.USER_ID_KEY, dataLogin.getUserId());
        SPUtils.put(this, AppAllKey.USER_Token, dataLogin.getUserToken());

        SPUtils.put(this, AppConfig.TYPE_NAME, dataLogin.getNickName());
        SPUtils.put(this, AppConfig.TYPE_NO, dataLogin.getWxSno());
        SPUtils.put(this, AppConfig.TYPE_PHONE, dataLogin.getWxSno());
        SPUtils.put(this, AppConfig.User_HEAD_URL, dataLogin.getHeadImg());
        SPUtils.put(this, AppConfig.TYPE_SIGN, dataLogin.getPersonaSignature());


//        SPUtils.put(this,AppConfig.TYPE_WS_REQUEST,dataLogin.getServerIpWs());
        SplitWeb.getSplitWeb().USER_TOKEN = dataLogin.getUserToken();
        SplitWeb.getSplitWeb().MOBILE = dataLogin.getMobile();
        SplitWeb.getSplitWeb().QR_CODE = dataLogin.getQrcode();
        SplitWeb.getSplitWeb().NICK_NAME = dataLogin.getNickName();
        SplitWeb.getSplitWeb().PERSON_SIGN = dataLogin.getPersonaSignature();
        SplitWeb.getSplitWeb().QR_CODE = dataLogin.getQrcode();
        SplitWeb.getSplitWeb().WX_SNO = dataLogin.getWxSno();
        SplitWeb.getSplitWeb().USER_ID = dataLogin.getUserId();
        SplitWeb.getSplitWeb().USER_HEADER = dataLogin.getHeadImg();
        mCache.put(AppAllKey.USER_ID_KEY, dataLogin.getUserId());
        //TODO 集群
        try {
            SplitWeb.getSplitWeb().WS_REQUEST = dataLogin.getServerIpWs();
            SplitWeb.getSplitWeb().HTTP_REQUEST = dataLogin.getServerIpHttp();
            String serverIpWs = dataLogin.getServerIpWs();
            String serverIpHttp = dataLogin.getServerIpHttp();
            mCache.remove(AppConfig.TYPE_WS_REQUEST);
            mCache.put(AppConfig.TYPE_WS_REQUEST, serverIpWs);
            mCache.remove(AppConfig.TYPE_URL);
            mCache.put(AppConfig.TYPE_URL, serverIpHttp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
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

