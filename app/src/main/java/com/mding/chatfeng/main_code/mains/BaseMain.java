//package com.mding.chatfeng.main_code.mains;
//
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.os.IBinder;
//import android.os.RemoteException;
//import android.support.v7.app.AppCompatActivity;
//
//import com.mding.IChatCallBack;
//import com.mding.IChatRequst;
//import com.mding.chatfeng.about_base.AppConfig;
//import com.mding.chatfeng.about_base.BaseActivity;
//import com.mding.models.ChatModel;
//import com.mding.workservice.ChatService;
//
//public abstract class BaseMain extends BaseActivity implements ServiceConnection {
//    public static IChatRequst mIChatRequst;
//    public Intent intent;
//    abstract void onMainSuccees(String onLoginSuccees);
//    abstract void onMainFail(String onLoginFail);
//    abstract void onRecevieMsg(String onSendMsg);
//    Context mContext;
///*    abstract void onLoginSuccees(LoginModel mLoginModel);
//    abstract void onLoginFail(LoginModel mLoginModel);*/
//
//
//    public void init(Context mContext){
//        this.mContext=mContext;
//
//        if(intent!=null){
//            try {
//                unbindService(this);
//                stopService(intent);
//            }catch (Exception e){}
//        }
//        intent=new Intent(mContext, ChatService.class);
//        bindService(intent,this, Context.BIND_AUTO_CREATE);
//        startService(intent);
//    }
//
//
//
//    @Override
//    public void onServiceConnected(ComponentName name, IBinder service) {
//        mIChatRequst= IChatRequst.Stub.asInterface(service);
//        if (mIChatRequst!=null)
//        {
//            try {
//                mIChatRequst.register(new IChatCallBack.Stub() {
//
//                    @Override
//                    public void recevieMsgStatus(ChatModel mChatModel) throws RemoteException {
//
//                    }
//
//                    @Override
//                    public void recevieHeadStatus(ChatModel mChatModel) throws RemoteException {
//
//                    }
//
//                    @Override
//                    public void recevieMsg(String mChatModel) throws RemoteException {
//                        onRecevieMsg(mChatModel);
//                    }
//
//                    @Override
//                    public void recevieMainList(String list) throws RemoteException {
//
//                    }
//
//                    @Override
//                    public void recevieContactsList(String list) throws RemoteException {
//                        onMainSuccees(list);
//                    }
//
//                    @Override
//                    public void initSuccess(String suceess) throws RemoteException {
//
//                    }
//
//                    @Override
//                    public void onFail(String onFail) throws RemoteException {
//
//                    }
//                });
//
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//            try {
//                //启动接收消息
//                mIChatRequst.onStartGetData();
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }else{
//            //服务没有启动.可尝试启动service
//            AppConfig.logs("服务没有启动.可尝试启动service");
//        }
//
//
//
//
//    }
//
//    @Override
//    public void onServiceDisconnected(ComponentName name) {
//        onMainFail("onLoginFailonLoginFailonLoginFail");
//    }
//
//    @Override
//    public void onBindingDied(ComponentName name) {
//
//    }
//
//    @Override
//    public void onNullBinding(ComponentName name) {
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unbindService(this);
//        stopService(intent);
//    }
//}
