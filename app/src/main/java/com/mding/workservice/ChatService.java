package com.mding.workservice;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.mding.IChatCallBack;
import com.mding.IChatRequst;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.core.pushservice.WsChannelService;
import com.mding.models.ChatModel;


/**
 * 数据库的操作逻辑
 */
public class ChatService extends Service {
    private IChatCallBack callback;
    private Intent intents;
    private IChatRequst mIChatRequsts;
    private  ChatServiceConn conn;
    public ChatService() {}
    @Override
    public void onCreate() {
        super.onCreate();
        AppConfig.logs("ChatService->onCreate");
        if(conn == null){
            conn = new ChatService.ChatServiceConn();
        }
    }
    @Override
    public IBinder onBind(Intent intent) {


       return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intents==null)
        {
            //启动WS通道连接服务
/*
            intents=new Intent(ChatService.this, WsChannelService.class);
            bindService(intent,conn, Context.BIND_AUTO_CREATE);
            startService(intents);
*/
            startService(new Intent(this, WsChannelService.class));
            bindService(new Intent(this, WsChannelService.class),conn,Context.BIND_IMPORTANT);
        }

        return START_STICKY;
    }


    /**
     * 来自WsChannelService数据回调
     */
    class ChatServiceConn implements ServiceConnection{
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIChatRequsts= IChatRequst.Stub.asInterface(service);

            if (mIChatRequsts!=null)
            {
                try {
                    mIChatRequsts.register(new IChatCallBack.Stub() {
                        @Override
                        public void recevieMsgStatus(ChatModel mChatModel) throws RemoteException {

                        }

                        @Override
                        public void recevieHeadStatus(ChatModel mChatModel) throws RemoteException {

                        }

                        @Override
                        public void recevieMsg(String mChatModel)  {
                            AppConfig.logs("CS"+mChatModel);
                            try {
                                callback.recevieMsg(mChatModel);
                            } catch (Exception e) {
                                e.printStackTrace();
                                //表示UI进程已经不存在
                            }
                        }

                        @Override
                        public void recevieMainList(String list) throws RemoteException {

                        }

                        @Override
                        public void recevieContactsList(String list) throws RemoteException {
                            //

                        }

                        @Override
                        public void initSuccess(String suceess) throws RemoteException {
                            AppConfig.logs(suceess);
                        }

                        @Override
                        public void onFail(String onFail) throws RemoteException {
                            callback.onFail(onFail);
                        }
                    });

                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            startService(new Intent(ChatService.this, WsChannelService.class));
            bindService(new Intent(ChatService.this, WsChannelService.class),conn,Context.BIND_IMPORTANT);
        }
    }




    /**
     * 聊天功能的请求实现，与UI界面联动
     */
    IChatRequst.Stub binder= new IChatRequst.Stub(){
        @Override
        public void register(IChatCallBack callbacks) throws RemoteException {
            callback=callbacks;
        }
        //初始化数据
        @Override
        public void initData() throws RemoteException {
/*        //获取数据，存库操作
            getDataByUrl("WWW");
            //初始化成功后触发回调函数
            callback.initSuccess("1");*/
            mIChatRequsts.initData();
        }

        @Override
        public String getDataByUrl(String url) throws RemoteException {
            if(mIChatRequsts!=null)
                 mIChatRequsts.getDataByUrl("21312");
            return "JSON";
        }

        @Override
        public boolean sendMsg(String mSendMsg) throws RemoteException {
          /*  ChatModel mChatModel=new ChatModel();
            mChatModel.setMessege(mSendMsg);
            callback.recevieMsg(mChatModel);*/
            return mIChatRequsts.sendMsg(mSendMsg);
        }

        @Override
        public void getHeadImg(final String mGetHeadImg) throws RemoteException {

        }

        //从数据库获取数据
        @Override
        public void getDataBySqlite(String type) throws RemoteException {
            switch (type){
                case "1":
                    //返回主界面好友和群列表
                    callback.recevieMainList("");break;
                case "2":
                    //返回联系人列表
                    callback.recevieContactsList("");break;
                default:break;
            }
        }


        //开始接收消息
        @Override
        public void onStartGetData() throws RemoteException {
//     new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    //这里写入子线程需要做的工作
//                    while(true){
//                        try {
//                            Thread.sleep(1000);
//                            AppConfig.logs("-------------ChatService");
//                            try {
//                                callback.onFail("213123213");
//                            } catch (RemoteException e) {
//                                e.printStackTrace();
//                            }
//                            //收到新消息则返回数据
//
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                            AppConfig.logs("-------------sendMsgToWs执行崩溃");
//                        }
//                    }
//                }
//            }).start(); //启动线程;
        }

    };

}

/*

     new Thread(new Runnable() {
@Override
public void run() {
        //这里写入子线程需要做的工作
        while(true){
        try {
        Thread.sleep(1000);
        AppConfig.logs("-------------顶顶顶");
        } catch (InterruptedException e) {
        e.printStackTrace();
        AppConfig.logs("-------------sendMsgToWs执行崩溃");
        }
        }
        }
        }).start(); //启动线程;*/
