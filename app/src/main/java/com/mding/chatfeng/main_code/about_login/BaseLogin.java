package com.mding.chatfeng.main_code.about_login;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;

import com.mding.ILoginCallBack;
import com.mding.ILoginRequst;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.BaseActivity;
import com.mding.core.deamonservice.NotifyDeamon;
import com.mding.models.LoginModel;


public abstract class BaseLogin extends BaseActivity implements ServiceConnection {

    public ILoginRequst iLoginRequst;
    public Intent intent;
    abstract void onLoginSuccees(String mLoginModel);
    abstract void onLoginFail(String mLoginModel);
    Context mContext;


    public void init(Context mContext){
        this.mContext=mContext;

        if(intent!=null){
            try {
                unbindService(this);
                stopService(intent);
            }catch (Exception e){}
        }
        try {
            intent=new Intent(mContext, NotifyDeamon.class);
            bindService(intent,this, Context.BIND_IMPORTANT);
            startService(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

        iLoginRequst= ILoginRequst.Stub.asInterface(service);
        if (iLoginRequst!=null)
        {
            try {
                //告诉守护进程,无需做自动登入操作
                iLoginRequst.register(new ILoginCallBack.Stub(){
                    @Override
                    public void readData(LoginModel mLoginModel) throws RemoteException {
              /*          AppConfig.logs("远程“ChannelDeamon”已经登陆成功,现在数据回调回来");
                        //成功获取数据,将最终数据发给UI线程
                        Message message=new Message();
                        message.obj=mLoginModel;
                        handler.sendMessage(message);*/
                    }

                    @Override
                    public void onFailure(String res) throws RemoteException {
                        onLoginFail(res);
                    }

                    @Override
                    public void onSuccess(String res) throws RemoteException {
                        onLoginSuccees(res);
                    }
                });

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
        AppConfig.logs("登入ACT已经断开了");
    }

    @Override
    public void onBindingDied(ComponentName name) {

    }

    @Override
    public void onNullBinding(ComponentName name) {

    }


/*    @Override
    protected void onPause() {
        super.onPause();
        //如果是消息模块，此处可通知远程,我的界面已经暂定，消息请发通知栏。
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unbindService(this);
//        stopService(intent);
    }


}
