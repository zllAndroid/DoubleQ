package com.doubleq.xm6leefunz.about_base;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.PowerManager;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.doubleq.model.CusJumpChatData;
import com.doubleq.model.DataAddfriendSendRequest;
import com.doubleq.model.DataAgreeFriend;
import com.doubleq.model.DataCreatGroupResult;
import com.doubleq.model.DataFriendPush;
import com.doubleq.model.DataGroupChatResult;
import com.doubleq.model.DataGroupChatSend;
import com.doubleq.model.DataJieShou;
import com.doubleq.xm6leefunz.about_base.web_base.AppResponseDispatcher;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_chat.ChatActivity;
import com.doubleq.xm6leefunz.about_chat.cus_data_group.CusGroupChatData;
import com.doubleq.xm6leefunz.about_chat.cus_data_group.RealmGroupChatHelper;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.NotificationUtil;
import com.doubleq.xm6leefunz.about_utils.SysRunUtils;
import com.doubleq.xm6leefunz.about_utils.TimeUtil;
import com.doubleq.xm6leefunz.about_utils.about_realm.RealmHelper;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.CusChatData;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.CusHomeRealmData;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmChatHelper;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmHomeHelper;
import com.doubleq.xm6leefunz.main_code.about_notification.ScreenAndLockService;
import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.crash.PgyerCrashObservable;
import com.pgyersdk.crash.PgyerObserver;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
import com.rance.chatui.util.Constants;
import com.zll.websocket.ErrorResponse;
import com.zll.websocket.IWebSocketPage;
import com.zll.websocket.Response;
import com.zll.websocket.WebSocketService;
import com.zll.websocket.WebSocketServiceConnectManager;
import com.zll.websocket.WebSocketSetting;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
//        Intent intent = new Intent(mContext,ScreenAndLockService.class);
//        stopService(intent);
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
        realmGroupChatHelper = new RealmGroupChatHelper(this);
        realmChatHelper = new RealmChatHelper(this);
    }
    RealmHomeHelper realmHelper;
    RealmChatHelper realmChatHelper;
    RealmGroupChatHelper realmGroupChatHelper;
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
        try {
            if (!StrUtils.isEmpty(SplitWeb.getUserId())&&isBind)
                sendText(SplitWeb.bindUid());
        } catch (Exception e) {
            e.printStackTrace();
        }
        reBind ="1";

    }

    @Override
    public void onConnectError(Throwable cause) {
        Log.e("WebSocketLib","---------------------------------onWantConnect---------------------------------------");
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
                //接收好友消息
                case "privateReceive":
                    dealReceiver(message.getResponseText());
                    break;
//                    接收群组消息
                case "groupReceive":
                    initGroupReceiveData(message.getResponseText());
//                    dealGroupReceiver(message.getResponseText());
                    break;
//                    群发送信息
                case "groupSend":
                    dealGroupSend(message.getResponseText());
//                    dealGroupReceiver(message.getResponseText());
                    break;
//                    接收好友推送通知（对方加我为好友时）
                case "addFriendSend":
                    dealFriend(message.getResponseText());
                    break;
//                添加好友-离线推送通知接口(接收者)
                case "addFriendPush":
                    dealFriendPush(message.getResponseText());
                    break;
//                    对方同意我的好友请求,后推送接口
                case "agreeFriendSend":
                    dealAgreeFriend(message.getResponseText());
                    break;
//                case "createdGroupSend":
//                    CGS(message.getResponseText());
//                    break;
                case "createdUserGroup":
                    CGS(message.getResponseText());
                    break;
//                    私聊发送消息
                case "privateSend":
                    dealSend(message.getResponseText());
                    break;
            }
        }
    }

    private void dealFriendPush(String responseText) {
        DataFriendPush dataFriendPush = JSON.parseObject(responseText, DataFriendPush.class);
        final   DataFriendPush.RecordBean record = dataFriendPush.getRecord();
        final  List<DataFriendPush.RecordBean.MessageListBean> messageList = record.getMessageList();
        for (int i = 0; i<messageList.size(); i++)
        {
            int num = (int)SPUtils.get(this, AppConfig.LINKMAN_FRIEND_NUM, 0);
            SPUtils.put(this,AppConfig.LINKMAN_FRIEND_NUM,num+1);
            Intent intent = new Intent();
            intent.putExtra("num", num+1);
            intent.setAction("action.addFriend");
            sendBroadcast(intent);

//            Intent intent3 = new Intent(this,ScreenAndLockService.class);
//            this.startService(intent3);
//            PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
//            PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, this.getClass().getName());//持有唤醒锁
////            wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, this.getClass().getName());//持有唤醒锁
//            wakeLock.setReferenceCounted(false);
//            wakeLock.acquire(30*1000);//30s亮屏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//亮屏
            final DataFriendPush.RecordBean.MessageListBean messageListBean = messageList.get(i);
            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        bitmap = Glide.with(MyApplication.getAppContext())
                                .load(messageListBean.getHeadImg())
                                .asBitmap() //必须
                                .centerCrop()
                                .into(500, 500)
                                .get();
                        NotificationUtil notificationUtils = new NotificationUtil(getApplicationContext());
                        String remark = (!StrUtils.isEmpty(messageListBean.getRemark()))?messageListBean.getRemark():"没有备注";
                        notificationUtils.sendNotification(messageListBean.getNickName()+"加您为好友", "备注："+remark,bitmap,AppConfig.TYPE_NOTICE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }
    //终止服务
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Intent intent = new Intent(mContext,ScreenAndLockService.class);
//        stopService(intent);
//    }
    private void CGS(String responseText) {
        DataCreatGroupResult dataCreatGroupResult=JSON.parseObject(responseText,DataCreatGroupResult.class);
        DataCreatGroupResult.RecordBean record = dataCreatGroupResult.getRecord();
//        record1 = dataCreatGroupResult.getRecord();
        if (record!=null)
        {
            sendText(SplitWeb.groupSend(record.getGroupOfId(),"群创建成功，快来聊天吧",AppConfig.SEND_MESSAGE_TYPE_TEXT, TimeUtil.getTime()));
            final CusHomeRealmData cusJumpChatData = new CusHomeRealmData();
            cusJumpChatData.setHeadImg(record.getGroupHeadImg());
            cusJumpChatData.setFriendId(record.getGroupOfId());
            cusJumpChatData.setNickName(record.getGroupNickName());
            cusJumpChatData.setMsg("群创建成功，快来聊天吧");
            cusJumpChatData.setTime(TimeUtil.getTime());
            cusJumpChatData.setNum(0);
//            cusJumpChatData.setType(RealmHomeHelper.TypeQun);
            realmHelper.addRealmMsgQun(cusJumpChatData);
            Intent intent = new Intent();
            intent.putExtra("message","群创建成功，快来聊天吧");
            intent.putExtra("id",record.getGroupOfId());
            intent.setAction("action.refreshMsgFragment");
            sendBroadcast(intent);
        }

//        DataAgreeFriend dataAgreeFriend = JSON.parseObject(responseText, DataAgreeFriend.class);
//        DataAgreeFriend.RecordBean record = dataAgreeFriend.getRecord();
//        if (record!=null)
//        {
//            sendText(SplitWeb.groupSend(record.getFriendsId(),"我们已经是好友了，快来聊一聊吧", ChatActivity.messageType, TimeUtil.getTime()));
//
//            final CusHomeRealmData cusJumpChatData = new CusHomeRealmData();
//            cusJumpChatData.setHeadImg(record.getHeadImg());
//            cusJumpChatData.setFriendId(record.getFriendsId());
//            cusJumpChatData.setNickName(record.getNickName());
//            cusJumpChatData.setMsg("我们已经是好友了，快来聊一聊吧");
//            cusJumpChatData.setTime(TimeUtil.getTime());
//            cusJumpChatData.setNum(0);
//            realmHelper.addRealmMsg(cusJumpChatData);
//            Intent intent = new Intent();
//            intent.putExtra("message","我们已经是好友了，快来聊一聊吧");
//            intent.putExtra("id",record.getFriendsId());
//            intent.setAction("action.refreshMsgFragment");
//            sendBroadcast(intent);
//        }
    }

    private void initGroupReceiveData(String responseText) {
        DataGroupChatResult dataGroupChat = JSON.parseObject(responseText, DataGroupChatResult.class);
        final DataGroupChatResult.RecordBean record = dataGroupChat.getRecord();
        if (record==null)
            return;
        AppConfig.CHAT_GROUP_ID = record.getGroupId();
        String Mytime=record.getRequestTime();
        String time = (String) SPUtils.get(this, AppConfig.CHAT_RECEIVE_TIME_REALM_GROUP,"");
        if (StrUtils.isEmpty(time)) {
            SPUtils.put(this,AppConfig.CHAT_RECEIVE_TIME_REALM_GROUP,(String)record.getRequestTime());
        }else {
            try {
                int i = TimeUtil.stringDaysBetween(record.getRequestTime(), time);
                MyLog.e("stringDaysBetween", "++++++++++++++++++++++++++++++++++++++++++++++" + i);
                SPUtils.put(this, AppConfig.CHAT_RECEIVE_TIME_REALM_GROUP, (String) record.getRequestTime());
//                发送时间之间的间隔小于五分钟，则不显示时间
                if (Math.abs(i) < 5) {
//                    record.setRequestTime("");
                    Mytime="";
                }else {
                    Mytime=record.getRequestTime();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!SplitWeb.IS_CHAT_GROUP.equals("2"))
        {
//            不在聊天界面收到消息时候的处理
            noGroupChatUI(record);
        }
        CusGroupChatData groupChatData = new CusGroupChatData();
        groupChatData.setCreated(Mytime);
        groupChatData.setFriendId(record.getMemberId());
        groupChatData.setGroupId(record.getGroupId());
        groupChatData.setGroupUserId(record.getGroupId()+SplitWeb.getUserId());
        groupChatData.setImgHead(record.getMemberHeadImg());
        groupChatData.setImgGroup(record.getGroupHeadImg());
        groupChatData.setMessage(record.getMessage());
        groupChatData.setNameGroup(record.getGroupName());
        groupChatData.setNameFriend(record.getMemberName());
        groupChatData.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
        groupChatData.setUserMessageType(Constants.CHAT_ITEM_TYPE_LEFT);
        groupChatData.setMessageType(record.getMessageType());

        realmGroupChatHelper.addRealmChat(groupChatData);//更新群聊聊天数据
        MyLog.e("realmGroupChatHelper","msg="+record.getMessage());
        CusHomeRealmData homeRealmData = realmHelper.queryAllRealmChat(record.getGroupId());
        if (homeRealmData!=null) {
            realmHelper.updateMsg(record.getGroupId(), record.getMessage(), record.getRequestTime());//更新首页聊天界面数据（消息和时间）
            realmHelper.updateNum(record.getGroupId());//更新首页聊天界面数据（未读消息数目）
        }
        else
        {
            final CusHomeRealmData cusJumpChatData = new CusHomeRealmData();
            cusJumpChatData.setHeadImg(record.getGroupHeadImg());
            cusJumpChatData.setFriendId(record.getGroupId());
            cusJumpChatData.setNickName(record.getGroupName());
            cusJumpChatData.setMsg(record.getMessage());
            cusJumpChatData.setTime(record.getRequestTime());

            cusJumpChatData.setNum(0);
//            realmHelper.updateNum(record.getFriendsId());
            realmHelper.addRealmMsgQun(cusJumpChatData);
        }
        List<CusHomeRealmData> cusHomeRealmData = realmHelper.queryAllmMsg();
        MyLog.e("MyApplication","queryAllmMsg="+cusHomeRealmData.size());
    }

    private void dealGroupSend(String message) {
        DataGroupChatSend dataGroupSend = JSON.parseObject(message, DataGroupChatSend.class);
        DataGroupChatSend.RecordBean record = dataGroupSend.getRecord();
        if (record != null) {

            initMsgGroupSend(record);//发布广播更新首页的信息

            CusGroupChatData cusRealmChatMsg = new CusGroupChatData();
            String time = (String) SPUtils.get(this, AppConfig.CHAT_SEND_TIME_REALM_GROUP,"");
            if (StrUtils.isEmpty(time)) {
                SPUtils.put(this,AppConfig.CHAT_SEND_TIME_REALM_GROUP,(String)record.getRequestTime());
            }else {
                try {
                    int i = TimeUtil.stringDaysBetween(record.getRequestTime(), time);
                    Log.e("stringDaysBetween", "++++++++++++++++++++++++++++++++++++++++++++++" + i);
                    SPUtils.put(this, AppConfig.CHAT_SEND_TIME_REALM_GROUP, (String) record.getRequestTime());
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
            cusRealmChatMsg.setGroupId(record.getGroupId());
            cusRealmChatMsg.setGroupUserId(record.getGroupId()+SplitWeb.getUserId());
//            cusRealmChatMsg.setSendId(record.getMemberId());
            cusRealmChatMsg.setUserMessageType(Constants.CHAT_ITEM_TYPE_RIGHT);
            cusRealmChatMsg.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
            realmGroupChatHelper.addRealmChat(cusRealmChatMsg);

//            realmHelper.updateMsg(record.getGroupId(),record.getMessage(),record.getRequestTime());
//            realmHelper.updateNum(record.getGroupId());//更新首页聊天界面数据（未读消息数目）
        }
    }

    private void dealAgreeFriend(String responseText) {
        DataAgreeFriend dataAgreeFriend = JSON.parseObject(responseText, DataAgreeFriend.class);
        DataAgreeFriend.RecordBean record = dataAgreeFriend.getRecord();
        if (record!=null)
        {
            sendText(SplitWeb.privateSend(record.getFriendsId(),"我们已经是好友了，快来聊一聊吧", ChatActivity.messageType, TimeUtil.getTime()));

            final CusHomeRealmData cusJumpChatData = new CusHomeRealmData();
            cusJumpChatData.setHeadImg(record.getHeadImg());
            cusJumpChatData.setFriendId(record.getFriendsId());
            cusJumpChatData.setNickName(record.getNickName());
            cusJumpChatData.setMsg("我们已经是好友了，快来聊一聊吧");
            cusJumpChatData.setTime(TimeUtil.getTime());
            cusJumpChatData.setNum(0);
            realmHelper.addRealmMsg(cusJumpChatData);
            Intent intent = new Intent();
            intent.putExtra("message","我们已经是好友了，快来聊一聊吧");
            intent.putExtra("id",record.getFriendsId());
            intent.setAction("action.refreshMsgFragment");
            sendBroadcast(intent);
        }
    }

    private void dealFriend(String responseText) {
        DataAddfriendSendRequest dataAddfriendSendRequest = JSON.parseObject(responseText, DataAddfriendSendRequest.class);
        DataAddfriendSendRequest.RecordBean record = dataAddfriendSendRequest.getRecord();
        int num = (int)SPUtils.get(this, AppConfig.LINKMAN_FRIEND_NUM, 0);
        SPUtils.put(this,AppConfig.LINKMAN_FRIEND_NUM,num+1);
        Intent intent = new Intent();
        intent.putExtra("num", num+1);
        intent.setAction("action.addFriend");
        sendBroadcast(intent);
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
                    MyLog.e("stringDaysBetween", "++++++++++++++++++++++++++++++++++++++++++++++" + i);
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
            cusRealmChatMsg.setTotalId(record.getFriendsId() + SplitWeb.getUserId());
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
    private void initMsgGroupSend( DataGroupChatSend.RecordBean record) {
        Intent intent = new Intent();
        intent.putExtra("message", record.getMessage());
        intent.putExtra("id", record.getGroupId());
        intent.setAction("action.refreshMsgFragment");
        sendBroadcast(intent);
        realmHelper.updateMsg(record.getGroupId(), record.getMessage(), record.getRequestTime());//更新首页聊天界面数据（消息和时间）
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
        if (!SplitWeb.IS_CHAT.equals("1"))
        {
//            不在聊天界面收到消息时候的处理
            noChatUI(record);
        }
        cusRealmChatMsg.setCreated(Mytime);
        cusRealmChatMsg.setMessage(record.getMessage());
        cusRealmChatMsg.setMessageType(record.getMessageType());
        cusRealmChatMsg.setReceiveId(record.getFriendsId());
        cusRealmChatMsg.setSendId(record.getUserId());
        cusRealmChatMsg.setUserMessageType(record.getType());
        cusRealmChatMsg.setTotalId(record.getFriendsId()+SplitWeb.getUserId());

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
        List<CusHomeRealmData> cusHomeRealmData = realmHelper.queryAllRealmMsg();
        Log.e("MyApplication","cusHomeRealmData="+cusHomeRealmData.size());
    }

    Bitmap bitmap;
    private void noChatUI(final DataJieShou.RecordBean record) {
        final CusJumpChatData cusJumpChatData = new CusJumpChatData();
        cusJumpChatData.setFriendHeader(record.getHeadImg());
        cusJumpChatData.setFriendId(record.getFriendsId());
        cusJumpChatData.setFriendName(record.getFriendsName());

//        发送广播更新首页
        Intent intent = new Intent();
        intent.putExtra("message",record.getMessage());
        intent.putExtra("id",record.getFriendsId());
        intent.setAction("action.refreshMsgFragment");
        sendBroadcast(intent);
//在前台的时候处理接收到消息的事件
        if (SysRunUtils.isAppOnForeground(MyApplication.getAppContext()))
        {
//            TODO 弄成popwindow   弹框
            ToastUtil.show("收到来自"+record.getFriendsName()+"的一条新消息");

//            JNoticeAgent.setIsAutoDismiss(true);
//            JNoticeAgent.setAutoDismissTime(4000);
//            JNoticeAgent.setIsUseHomeKey(true);
//            JNoticeAgent.register(this);
//            JDefaultAdapter jDefaultAdapter = new JDefaultAdapter(this, R.layout.jnotice_adpter_item, null);
//            JNoticeAgent.getJNoticeAgent().setAdapter(jDefaultAdapter);
//            JNoticeAgent.addJNotice(new JNoticeBean(0,record.getFriendsName(),record.getMessage(),R.drawable.dou_logo));
//            jDefaultAdapter.setOnJNoticeListener(new JNotice.OnJNoticeListener() {
//                @Override
//                public void onItemClick(int position) {
//                    ToastUtil.show("点击了通知");
//                }
//
//                @Override
//                public void onDismissDingToast() {
//
//                }
//            });
        }else {
            //APP在后台的时候处理接收到消息的事件
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bitmap = Glide.with(MyApplication.getAppContext())
                                .load(record.getHeadImg())
                                .asBitmap() //必须
                                .centerCrop()
                                .into(500, 500)
                                .get();
                        NotificationUtil notificationUtils = new NotificationUtil(getApplicationContext());
                        notificationUtils.sendNotification(cusJumpChatData, record.getFriendsName(), record.getMessage(), bitmap, AppConfig.TYPE_CHAT);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
    private void noGroupChatUI(final DataGroupChatResult.RecordBean record) {
        final CusJumpChatData cusJumpChatData = new CusJumpChatData();
        cusJumpChatData.setFriendHeader(record.getGroupHeadImg());
        cusJumpChatData.setFriendId(record.getGroupId());
        cusJumpChatData.setFriendName(record.getGroupName());

//        发送广播更新首页
        Intent intent = new Intent();
        intent.putExtra("message",record.getMessage());
        intent.putExtra("id",record.getGroupId());
        intent.setAction("action.refreshMsgFragment");
        sendBroadcast(intent);
//在前台的时候处理接收到消息的事件
        if (SysRunUtils.isAppOnForeground(MyApplication.getAppContext()))
        {
//            TODO 弄成popwindow   弹框
            ToastUtil.show("收到来自"+record.getGroupName()+"的一条新消息");
//            JNoticeAgent.setIsAutoDismiss(true);
//            JNoticeAgent.setAutoDismissTime(5000);
//            JNoticeAgent.setIsUseHomeKey(true);
//            JNoticeAgent.register(this);
//            JNoticeAgent.getJNoticeAgent().setAdapter(new JDefaultAdapter(this, R.layout.jnotice_adpter_item, null));
//            JNoticeAgent.addJNotice(new JNoticeBean(1,record.getGroupName(),record.getMessage(),R.drawable.dou_logo));
        }else {
            //APP在后台的时候处理接收到消息的事件
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bitmap = Glide.with(MyApplication.getAppContext())
                                .load(record.getGroupHeadImg())
                                .asBitmap() //必须
                                .centerCrop()
                                .into(500, 500)
                                .get();
                        NotificationUtil notificationUtils = new NotificationUtil(getApplicationContext());
                        notificationUtils.sendNotification(cusJumpChatData, record.getGroupName(), record.getMessage(), bitmap, AppConfig.TYPE_CHAT_QUN);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    public void onSendMessageError(ErrorResponse error) {

    }
}
