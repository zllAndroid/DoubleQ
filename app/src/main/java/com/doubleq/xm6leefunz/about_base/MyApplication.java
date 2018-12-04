package com.doubleq.xm6leefunz.about_base;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.doubleq.model.DataJieShou;
import com.doubleq.model.DataTuiAddFriend;
import com.doubleq.xm6leefunz.about_base.web_base.AppResponseDispatcher;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.TimeUtil;
import com.doubleq.xm6leefunz.about_utils.about_realm.RealmHelper;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.CusChatData;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.CusHomeRealmData;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmChatHelper;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmHomeHelper;
import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.crash.PgyerCrashObservable;
import com.pgyersdk.crash.PgyerObserver;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.rance.chatui.util.Constants;
import com.zll.websocket.ErrorResponse;
import com.zll.websocket.IWebSocketPage;
import com.zll.websocket.Response;
import com.zll.websocket.WebSocketService;
import com.zll.websocket.WebSocketServiceConnectManager;
import com.zll.websocket.WebSocketSetting;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application  implements IWebSocketPage  {
    private static MyApplication mInstance;
    public static Context mContext;
    public static String  isChatWindow="00";

    /**
     * 屏幕宽度
     */
    public static int screenWidth;
    /**
     * 屏幕高度
     */
    public static int screenHeight;
    /**
     * 屏幕密度
     */
    public static float screenDensity;
    public static WebSocketServiceConnectManager mConnectManager;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mInstance = this;
        PgyCrashManager.register();
        PgyerCrashObservable.get().attach(new PgyerObserver() {
            @Override
            public void receivedCrash(Thread thread, Throwable throwable) {
            }
        });
        initScreenSize();
        initManagerService();
//        WebSocketService webSocketService = new WebSocketService();
//        webSocketService.onCreate();
//        mConnectManager = new WebSocketServiceConnectManager(this, AppManager.getAppManager().currentActivity());
//        mConnectManager.onCreate();
        initRealm();

    }
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }
    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        super.onTerminate();
        PgyCrashManager.unregister();
    }
    private void initManagerService() {
        //配置 WebSocket，必须在 WebSocket 服务启动前设置
        WebSocketSetting.setConnectUrl(SplitWeb.WebSocket_URL);//必选
//        WebSocketSetting.setConnectUrl("ws://192.168.4.133:9093");//必选
        WebSocketSetting.setResponseProcessDelivery(new AppResponseDispatcher());
        WebSocketSetting.setReconnectWithNetworkChanged(true);

        //启动 WebSocket 服务
        Intent intent = new Intent(this, WebSocketService.class);
        startService(intent);
        mConnectManager = new WebSocketServiceConnectManager(this, this);
        mConnectManager.onCreate();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration configuration=new RealmConfiguration.Builder()
                .name(RealmHelper.DB_NAME)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);

        realmHelper = new RealmHomeHelper(this);
        realmChatHelper = new RealmChatHelper(this);
    }
    RealmHomeHelper realmHelper;
    RealmChatHelper realmChatHelper;
    public static WebSocketServiceConnectManager getmConnectManager(){
        return mConnectManager;
    }
    public static MyApplication getApp(){
        return new MyApplication();
    }
    public static Context getAppContext(){
        return mContext;
    }
    public static Context getInstance() {
        return mInstance;
    }

    /**
     * 初始化当前设备屏幕宽高
     */
    private void initScreenSize() {
        DisplayMetrics curMetrics = getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = curMetrics.widthPixels;
        screenHeight = curMetrics.heightPixels;
        screenDensity = curMetrics.density;
    }

    @Override
    public void onServiceBindSuccess() {

    }

    @Override
    public void sendText(String text) {
        mConnectManager.sendText(text);
    }

    @Override
    public void reconnect() {
        mConnectManager.reconnect();
//        Log.e("WebSocketLib","----------------------调用-----------reconnect---------------------------------------");
//        sendText(SplitWeb.bindUid());
    }
    String  reBind ="0";
    boolean  isBind =true;
    @Override
    public void onConnected() {
        Log.e("WebSocketLib","---------------------------------onConnected---------------------------------------");
        reBind ="0";
//        添加重连机制，当连接成功后，重新绑定uid
        if (!StrUtils.isEmpty(SplitWeb.USER_ID)&&isBind)
            sendText(SplitWeb.bindUid());
        reBind ="1";

    }

    @Override
    public void onConnectError(Throwable cause) {
//        mConnectManager.onDestroy();
//        mConnectManager.onDestroyService();
//        initManagerService();
//        mConnectManager.reconnect();
//        mConnectManager.reBind(SplitWeb.bindUid());
        Log.e("WebSocketLib","---------------------------------onWantConnect---------------------------------------");
//        mConnectManager.onDestroy();
//        initManagerService();
//
//        sendText(SplitWeb.bindUid());

    }

    @Override
    public void onDisconnected() {
    }
    @Override
    public void onWantConnect(Throwable cause) {

    }
    public  static  Response message;
    @Override
    public void onMessageResponse(Response message) {
        Log.e("MyApplication", "MyApplication" +message.getResponseText());
//        onResult(message);
        this.message=message;
        if (reBind.equals("1"))
        {
            isBind =true;
            String method = HelpUtils.backMethod(message.getResponseText());
            if (method.equals("bindUid"))
            {
                isBind =false;
            }


        }
//        接收消息时处理消息并存库
        String isSucess = HelpUtils.HttpIsSucess(message.getResponseText());
//        Log.e("onEvent","activity"+messageEvent.getMessage());
        if (isSucess.equals(AppAllKey.CODE_OK)) {
//            判断返回的方法名
            String s = HelpUtils.backMethod(message.getResponseText());
            switch (s)
            {
                case "privateReceive":
                    dealReceiver(message.getResponseText());
                    break;
                case "addFriendSend":
//                    dealReceiver(message.getResponseText());
//                    dealFriend(message.getResponseText());
                    break;
                case "privateSend":
//                    dealReceiver(message.getResponseText());
                    dealSend(message.getResponseText());
                    break;
            }


        }


    }

    private void dealSend(String message) {
        DataJieShou dataJieShou = JSON.parseObject(message, DataJieShou.class);
        DataJieShou.RecordBean record = dataJieShou.getRecord();
        if (record != null) {
            record.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
            record.setType(Constants.CHAT_ITEM_TYPE_RIGHT);

            initMsgSend(record);//发布广播更新首页的信息

            CusChatData cusRealmChatMsg = new CusChatData();
            String time = (String) SPUtils.get(this, AppConfig.CHAT_SEND_TIME_REALM,"");
            if (StrUtils.isEmpty(time)) {
                SPUtils.put(this,AppConfig.CHAT_SEND_TIME_REALM,(String)record.getRequestTime());
            }else {
                try {
                    int i = TimeUtil.stringDaysBetween(record.getRequestTime(), time);
                    Log.e("stringDaysBetween", "++++++++++++++++++++++++++++++++++++++++++++++" + i);
                    SPUtils.put(this, AppConfig.CHAT_SEND_TIME_REALM, (String) record.getRequestTime());
                    if (Math.abs(i) < 5) {
                        record.setRequestTime("");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            cusRealmChatMsg.setCreated(record.getRequestTime());
//            cusRealmChatMsg.setCreated(TimeUtil.sf.format(new Date()));
            cusRealmChatMsg.setMessage(record.getMessage());
            cusRealmChatMsg.setMessageType(record.getMessageType());
            cusRealmChatMsg.setReceiveId(record.getFriendsId());
            cusRealmChatMsg.setSendId(record.getUserId());
            cusRealmChatMsg.setUserMessageType(record.getType());
            cusRealmChatMsg.setTotalId(record.getFriendsId() + SplitWeb.USER_ID);
            realmChatHelper.addRealmChat(cusRealmChatMsg);

        }
    }

    private void initMsgSend(DataJieShou.RecordBean record) {
        Intent intent = new Intent();
        intent.putExtra("message", record.getMessage());
        intent.putExtra("id", record.getFriendsId());
        intent.setAction("action.refreshMsgFragment");
        sendBroadcast(intent);
        realmHelper.updateMsg(record.getFriendsId(), record.getMessage(), record.getRequestTime());//更新首页聊天界面数据（消息和时间）
    }


    private void dealReceiver(String message) {
        DataJieShou dataJieShou = JSON.parseObject(message, DataJieShou.class);
        final DataJieShou.RecordBean record = dataJieShou.getRecord();
        AppConfig.CHAT_FRIEND_ID = record.getFriendsId();
        record.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
        record.setType(Constants.CHAT_ITEM_TYPE_LEFT);
        CusChatData cusRealmChatMsg = new CusChatData();
//            String format = TimeUtil.sf.format(new Date());
//            cusRealmChatMsg.setCreated(format);
        String Mytime=record.getRequestTime();
        String time = (String) SPUtils.get(this, AppConfig.CHAT_RECEIVE_TIME_REALM,"");
        if (StrUtils.isEmpty(time)) {
            SPUtils.put(this,AppConfig.CHAT_RECEIVE_TIME_REALM,(String)record.getRequestTime());
        }else {
            try {
                int i = TimeUtil.stringDaysBetween(record.getRequestTime(), time);
                Log.e("stringDaysBetween", "++++++++++++++++++++++++++++++++++++++++++++++" + i);
                SPUtils.put(this, AppConfig.CHAT_RECEIVE_TIME_REALM, (String) record.getRequestTime());
                if (Math.abs(i) < 5) {
//                    record.setRequestTime("");
                    Mytime="";
                }else {
                    Mytime=record.getRequestTime();
//                    SPUtils.put(this, AppConfig.CHAT_RECEIVE_TIME_REALM, "");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        cusRealmChatMsg.setCreated(Mytime);
        cusRealmChatMsg.setMessage(record.getMessage());
        cusRealmChatMsg.setMessageType(record.getMessageType());
        cusRealmChatMsg.setReceiveId(record.getFriendsId());
        cusRealmChatMsg.setSendId(record.getUserId());
        cusRealmChatMsg.setUserMessageType(record.getType());
        cusRealmChatMsg.setTotalId(record.getFriendsId()+SplitWeb.USER_ID);

        realmChatHelper.addRealmChat(cusRealmChatMsg);//更新聊天数据
        Log.e("realmChatHelper","msg="+record.getMessage());

        CusHomeRealmData homeRealmData = realmHelper.queryAllRealmChat(record.getFriendsId());

        if (homeRealmData!=null) {
            realmHelper.updateMsg(record.getFriendsId(), record.getMessage(), record.getRequestTime());//更新首页聊天界面数据（消息和时间）
            realmHelper.updateNum(record.getFriendsId());//更新首页聊天界面数据（未读消息数目）
        }
        else
        {
            final CusHomeRealmData cusJumpChatData = new CusHomeRealmData();
            cusJumpChatData.setHeadImg(record.getHeadImg());
            cusJumpChatData.setFriendId(record.getFriendsId());
            cusJumpChatData.setNickName(record.getFriendsName());
            cusJumpChatData.setMsg(record.getMessage());
            cusJumpChatData.setTime(record.getRequestTime());
            cusJumpChatData.setNum(0);
//            realmHelper.updateNum(record.getFriendsId());
            realmHelper.addRealmMsg(cusJumpChatData);
        }
    }
//    public static Response getResult(){
//        return message;
//    }
//    public void onResult(Response message) {
//        Log.e("message", "MyApplication" +message.getResponseText());
//    }

    @Override
    public void onSendMessageError(ErrorResponse error) {

    }
}
