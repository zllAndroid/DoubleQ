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

        }

    };

    private void initHttp(final  ILoginCallBack callback,String loginUrl) {

//        Activity activity = AppManager.getAppManager().currentActivity();
        VolleyRequest.RequestGet(getApplicationContext(),loginUrl, new VolleyInterface(VolleyInterface.listener,VolleyInterface.errorListener) {
            @Override
            public void onSuccess(final String result) {
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

    @Override
    public void onDestroy() {
        super.onDestroy();
//        unbindService(this);
        stopService(intent);
    }



}

