package com.mding.chatfeng.about_application;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.mding.IChatCallBack;
import com.mding.IChatRequst;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.chatfeng.about_base.web_base.MessageEvent;
import com.mding.models.ChatModel;
import com.mding.workservice.ChatService;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseApp extends Application implements ServiceConnection {
    public static IChatRequst mIChatRequst;
    public Intent intent;
    abstract void onMainSuccees(String onLoginSuccees);
    abstract void onMainFail(String onLoginFail);
//    abstract void sendData(String onLoginFail);

    abstract void onRecevieMsg(String onSendMsg);
    Context mContext;
/*    abstract void onLoginSuccees(LoginModel mLoginModel);
    abstract void onLoginFail(LoginModel mLoginModel);*/


    public void init(Context mContext){

        this.mContext=mContext;

        if(intent!=null){
            try {
                mContext.unbindService(this);
                mContext.stopService(intent);
            }catch (Exception e){}
        }
        intent=new Intent(mContext, ChatService.class);
        mContext.bindService(intent,this, Context.BIND_IMPORTANT);
        mContext.startService(intent);
    }



    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mIChatRequst= IChatRequst.Stub.asInterface(service);
        if (mIChatRequst!=null)
        {
            try {
                mIChatRequst.register(new IChatCallBack.Stub() {

                    @Override
                    public void recevieMsgStatus(ChatModel mChatModel) throws RemoteException {

                    }

                    @Override
                    public void recevieHeadStatus(ChatModel mChatModel) throws RemoteException {

                    }

                    @Override
                    public void recevieMsg(String mChatModel) throws RemoteException {
                        onRecevieMsg(mChatModel);

                        AppConfig.logs("baseapp==>>"+mChatModel);
                    }

                    @Override
                    public void recevieMainList(String list) throws RemoteException {

                    }

                    @Override
                    public void recevieContactsList(String list) throws RemoteException {
                        onMainSuccees(list);
                    }

                    @Override
                    public void initSuccess(String suceess) throws RemoteException {

                    }

                    @Override
                    public void onFail(String onFail) throws RemoteException {
                        AppConfig.logs("baseapp==>>"+onFail);
//                        onMainFail(onFail);
                    }
                });

            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                //启动接收消息
                mIChatRequst.onStartGetData();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else{
            //服务没有启动.可尝试启动service
            AppConfig.logs("服务没有启动.可尝试启动service");
        }




    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        onMainFail("onLoginFailonLoginFailonLoginFail");
        intent=new Intent(mContext, ChatService.class);
        mContext.bindService(intent,this, Context.BIND_IMPORTANT);
        mContext.startService(intent);
    }

    @Override
    public void onBindingDied(ComponentName name) {

    }

    @Override
    public void onNullBinding(ComponentName name) {

    }

    protected void onDestroy() {
        mContext.unbindService(this);
        mContext.stopService(intent);
    }
}
