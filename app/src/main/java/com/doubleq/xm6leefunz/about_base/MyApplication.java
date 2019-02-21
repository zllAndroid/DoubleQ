package com.doubleq.xm6leefunz.about_base;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.PowerManager;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;
import android.util.Log;

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
import com.doubleq.model.DataJiqun;
import com.doubleq.model.DataLogin;
import com.doubleq.model.off_line_msg.DataOffLineChat;
import com.doubleq.model.off_line_msg.DataOffLineGroupChat;
import com.doubleq.xm6leefunz.about_base.deal_application.DealFriendAdd;
import com.doubleq.xm6leefunz.about_base.deal_application.DealGroupAdd;
import com.doubleq.xm6leefunz.about_base.deal_application.DealModifyFriendList;
import com.doubleq.xm6leefunz.about_base.deal_application.DealModifyGroupOfList;
import com.doubleq.xm6leefunz.about_base.web_base.AppResponseDispatcher;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_chat.ChatActivity;
import com.doubleq.xm6leefunz.about_chat.chat_group.GroupChatDetailsActivity;
import com.doubleq.xm6leefunz.about_chat.cus_data_group.CusGroupChatData;
import com.doubleq.xm6leefunz.about_chat.cus_data_group.RealmGroupChatHelper;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.about_utils.MathUtils;
import com.doubleq.xm6leefunz.about_utils.NetWorkUtlis;
import com.doubleq.xm6leefunz.about_utils.NotificationUtil;
import com.doubleq.xm6leefunz.about_utils.SysRunUtils;
import com.doubleq.xm6leefunz.about_utils.TimeUtil;
import com.doubleq.xm6leefunz.about_utils.about_realm.Migration;
import com.doubleq.xm6leefunz.about_utils.about_realm.RealmHelper;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.CusChatData;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.CusHomeRealmData;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmChatHelper;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmHomeHelper;
import com.doubleq.xm6leefunz.main_code.about_login.LoginActivity;
import com.doubleq.xm6leefunz.main_code.mains.MsgFragment;
import com.doubleq.xm6leefunz.main_code.mains.TestActivity;
import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.crash.PgyerCrashObservable;
import com.pgyersdk.crash.PgyerObserver;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
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
import io.realm.rx.RealmObservableFactory;

public class MyApplication extends Application implements IWebSocketPage {
    private static MyApplication mInstance;
    public static Context mContext;
    public static String isChatWindow = "00";

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
    public static ACache aCache;
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
        if (aCache==null)
            aCache =  ACache.get(this);
        String asString = aCache.getAsString(AppConfig.TYPE_URL);
//TODO 集群
        initServerBro();
        if (StrUtils.isEmpty(asString)) {
//            initServerBro();
        }else {
            initManagerService();
        }
//        initOneService();
        initRealm();

    }
    IntentFilter intentFilter;
    private void initServerBro() {

        if (intentFilter == null) {
            intentFilter = new IntentFilter();
            intentFilter.addAction("server_application");
            registerReceiver(mRefreshBroadcastReceiver, intentFilter);
        }
    }
    public  BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("server_application"))
            {
                initFirstService();
            }
        }
    };
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
        try {
            mConnectManager.onDestroy();
            if (mRefreshBroadcastReceiver!=null)
                unregisterReceiver(mRefreshBroadcastReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Intent intent = new Intent(mContext,ScreenAndLockService.class);
//        stopService(intent);
    }

    private void initFirstService() {
        String asString = aCache.getAsString(AppConfig.TYPE_URL);
        WebSocketSetting.setConnectUrl(asString);//必选
//        Log.e("TYPE_URL=", asString + "---------------initManagerService------------");
//        WebSocketSetting.setConnectUrl("ws://120.78.92.225:9093");//必选
        AppResponseDispatcher appResponseDispatcher = new AppResponseDispatcher();
        WebSocketSetting.setResponseProcessDelivery(appResponseDispatcher);
        WebSocketSetting.setReconnectWithNetworkChanged(true);
        //启动 WebSocket 服务
        Intent intent = new Intent(this, WebSocketService.class);
        startService(intent);
//        Log.e("initFirstService",AppManager.getAppManager().currentActivity().getClass().getSimpleName()+"");
        mConnectManager = new WebSocketServiceConnectManager(this, this);
        mConnectManager.onCreate();

        //配置 WebSocket，必须在 WebSocket 服务启动前设置
        Intent intent2 = new Intent();
        intent2.setAction("start_application");
         sendBroadcast(intent2);
    }
    private void initManagerService() {
        String asString = aCache.getAsString(AppConfig.TYPE_URL);
        WebSocketSetting.setConnectUrl(asString);//必选
        Log.e("TYPE_URL=", asString + "---------------initManagerService------------");
//        WebSocketSetting.setConnectUrl("ws://192.168.4.133:9093");//必选
        WebSocketSetting.setResponseProcessDelivery(new AppResponseDispatcher());
        WebSocketSetting.setReconnectWithNetworkChanged(true);

        //启动 WebSocket 服务
        Intent intent = new Intent(this, WebSocketService.class);
        startService(intent);
        mConnectManager = new WebSocketServiceConnectManager(this, this);
        mConnectManager.onCreate();


        //配置 WebSocket，必须在 WebSocket 服务启动前设置
//        WebSocketSetting.setConnectUrl(aCache.getAsString(AppConfig.TYPE_URL));//必选
////        Log.e("TYPE_URL=", aCache.getAsString(AppConfig.TYPE_URL) + "---------------------------");
////        WebSocketSetting.setConnectUrl("ws://192.168.4.133:9093");//必选
//        WebSocketSetting.setResponseProcessDelivery(new AppResponseDispatcher());
//        WebSocketSetting.setReconnectWithNetworkChanged(true);
//
//        //启动 WebSocket 服务
//        Intent intent = new Intent(this, WebSocketService.class);
//        startService(intent);
//        mConnectManager = new WebSocketServiceConnectManager(this, this);
//        mConnectManager.onCreate();

    }
    private void initOneService() {
        //配置 WebSocket，必须在 WebSocket 服务启动前设置
        WebSocketSetting.setConnectUrl(SplitWeb.WebSocket_URL);//必选
//        Log.e("TYPE_URL=", aCache.getAsString(AppConfig.TYPE_URL) + "---------------------------");
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
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(RealmHelper.DB_NAME)
//                .deleteRealmIfMigrationNeeded()
//                .schemaVersion(3)
//                .migration(new Migration())
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

    public static WebSocketServiceConnectManager getmConnectManager() {
        return mConnectManager;
    }

    public static MyApplication getApp() {
        return new MyApplication();
    }

    public static Context getAppContext() {
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
    }

    String reBind = "0";
    boolean isBind = true;
    boolean isFirst = true;

    @Override
    public void onConnected() {
        reBind = "0";
//        添加重连机制，当连接成功后，重新绑定uid
        try {
//            if (!StrUtils.isEmpty(SplitWeb.getUserId())) {
//            String userId = SplitWeb.getUserId();
            if (!StrUtils.isEmpty(SplitWeb.getUserId())) {
                sendText(SplitWeb.bindUid());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        reBind = "1";
    }

    @Override
    public void onConnectError(Throwable cause) {
    }
    @Override
    public void onDisconnected() {
    }
    @Override
    public void onWantConnect(Throwable cause) {
    }
    public static Response message;
    @Override
    public void onMessageResponse(Response message) {
//        onResult(message);
        this.message = message;
        if (!message.getResponseText().contains("{")) {
            ToastUtil.show("系统参数异常，请重新请求");
            return;
        }
        if (reBind.equals("1")) {
            isBind = true;
            String method = HelpUtils.backMethod(message.getResponseText());
            HelpUtils.HttpIsSucess(message.getResponseText());
            if (method.equals("bindUid")) {
                isBind = false;
            }
        }
        try {
            initReceiver(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initReceiver(Response message) {
        //        接收消息时处理消息并存库
        String isSucess = HelpUtils.HttpIsSucess(message.getResponseText());
//        Log.e("onEvent","activity"+messageEvent.getMessage());
        if (isSucess.equals(AppAllKey.CODE_OK)) {
//            判断返回的方法名
            String s = HelpUtils.backMethod(message.getResponseText());
            switch (s) {
                //接收好友消息
                case "privateReceive":
                    MsgFragment.isZero = true;
                    dealReceiver(message.getResponseText());
//                    dealReceiverShow(message.getResponseText());
                    break;
//                    接收群组消息
                case "groupReceive":
                    MsgFragment.isZero = true;
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
//                    用户在线私聊 - 离线消息
                case "messagePush":
                    dealOffLineChat(message.getResponseText());
                    break;
//                    用户在线群聊 - 离线消息
                case "messageGroupPush":
                    dealOffLineGroupChat(message.getResponseText());
                    break;
//                    创建群
                case "agreeGroupListSend":
                    DealGroupAdd.updateGroupDataByAdd(this, message.getResponseText());
                    break;
//                 用户加入群 - 给成员发送 联系人变动信息接口
                case "joinGroupListSend":
                    DealGroupAdd.updateGroupDataByAdd(this, message.getResponseText());
                    break;
//                    退出群聊
                case "outGroupListSend":
                    DealGroupAdd.updateGroupDataBySub(this, message.getResponseText(),realmHelper);
                    break;
//                    刪除群成員
                case "removeGroupListSend":
                    DealGroupAdd.updateGroupDataBySub(this, message.getResponseText(),realmHelper);
                    break;
//                    给成员发送 联系人变动信息接口
                case "modifyGroupListSend":
                    String s1 = DealGroupAdd.updateGroupDataByModifySub(this, message.getResponseText());
                    if (!StrUtils.isEmpty(s1))
                    {
                        DealGroupAdd.updateGroupDataByModifyAdd(this,  message.getResponseText() );
                    }
                    break;
//                    解散群聊
                case "dissolutionGroupListSend":
                    DealGroupAdd.updateGroupDataBySub(this, message.getResponseText(),realmHelper);
                    break;
                //添加好友
                case "agreeFriendListSend":
                    DealFriendAdd.updateFriendDataByAdd(this, message.getResponseText());
                    break;
//                    删除好友
                case "deleteFriendSend":
                    DealFriendAdd.updateFriendDataBySub(this,message.getResponseText());
                    break;
//                添、删、改好友分组or群分组管理
                case "addFriendGroupManageSend":
//                    DealModifyGroupOfList.modifyGroupOfList(this,message.getResponseText());
                    break;
                case "agreeGroupingListSend":
                    DealModifyGroupOfList.addGroupOfList(this,message.getResponseText());
                    break;
                case "deleteGroupingListSend":
                    DealModifyGroupOfList.deleteGroupOfList(this,message.getResponseText());
                    break;
                case "modifyGroupingListSend":
                    DealModifyGroupOfList.modifyGroupOfList(this,message.getResponseText());
                    break;
                case "modifyFriendListSend":
                    DealModifyFriendList.modifyGroupOfFriend(this,message.getResponseText());
                    break;

            }
        }
    }


    private void dealOffLineGroupChat(String responseText) {
        DataOffLineGroupChat dataOffLineGroupChat = JSON.parseObject(responseText, DataOffLineGroupChat.class);
        DataOffLineGroupChat.RecordBean record = dataOffLineGroupChat.getRecord();
        if (record != null) {
            List<DataOffLineGroupChat.RecordBean.MessageListBean> messageList = record.getMessageList();
            if (messageList.size() > 0)
                for (int i = 0; i < messageList.size(); i++) {
                    initOffLineGroupChat(messageList.get(i));
                }
        }
    }

    private void initOffLineGroupChat(DataOffLineGroupChat.RecordBean.MessageListBean record) {
        AppConfig.CHAT_GROUP_ID = record.getGroupId();
        String Mytime = record.getRequestTime();
        String time = AppConfig.mCHAT_RECEIVE_TIME_REALM_GROUP;
        AppConfig.mCHAT_RECEIVE_TIME_REALM_GROUP = record.getRequestTime();
//        String time = (String) SPUtils.get(this, AppConfig.CHAT_RECEIVE_TIME_REALM_GROUP,"");
//        SPUtils.put(this, AppConfig.CHAT_RECEIVE_TIME_REALM_GROUP, (String)record.getRequestTime());
        if (!StrUtils.isEmpty(time)) {
            try {
                int i = TimeUtil.stringDaysBetween(record.getRequestTime(), time);
//                发送时间之间的间隔小于五分钟，则不显示时间
                if (MathUtils.abs(i) < 5) {
                    Mytime = "";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!SplitWeb.IS_CHAT_GROUP.equals("2")) {
//            不在聊天界面收到消息时候的处理
            noGroupChatUIOffLine(record);
        }
        CusGroupChatData groupChatData = new CusGroupChatData();
        groupChatData.setCreated(Mytime);
        groupChatData.setFriendId(record.getMemberId());
        groupChatData.setGroupId(record.getGroupId());
        groupChatData.setGroupUserId(record.getGroupId() + SplitWeb.getUserId());
        groupChatData.setImgHead(record.getMemberHeadImg());
        groupChatData.setImgGroup(record.getGroupHeadImg());
        groupChatData.setMessage(record.getMessage());
        groupChatData.setNameGroup(record.getGroupName());
        groupChatData.setNameFriend(record.getMemberName());
        groupChatData.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
        groupChatData.setUserMessageType(Constants.CHAT_ITEM_TYPE_LEFT);
        groupChatData.setMessageType(record.getMessageType());

        realmGroupChatHelper.addRealmChat(groupChatData);//更新群聊聊天数据
        MyLog.e("realmGroupChatHelper", "msg=" + record.getMessage());
        CusHomeRealmData homeRealmData = realmHelper.queryAllRealmChat(record.getGroupId());
        String msg = record.getMessage();
        if (!record.getMessageType().equals(Constants.CHAT_NOTICE)) {
            msg = record.getMemberName() + "：" + record.getMessage();
        }

        if (homeRealmData != null) {
            realmHelper.updateGroupMsg(record.getGroupId(), msg, record.getRequestTime(),record);//更新首页聊天界面数据（消息和时间）
            realmHelper.updateNum(record.getGroupId());//更新首页聊天界面数据（未读消息数目）
        } else {
            final CusHomeRealmData cusJumpChatData = new CusHomeRealmData();
            cusJumpChatData.setHeadImg(record.getGroupHeadImg());
            cusJumpChatData.setFriendId(record.getGroupId());
            cusJumpChatData.setNickName(record.getGroupName());
            cusJumpChatData.setMsg(msg);
            cusJumpChatData.setTime(record.getRequestTime());

            cusJumpChatData.setNum(0);
//            realmHelper.updateNum(record.getFriendsId());
            realmHelper.addRealmMsgQun(cusJumpChatData);
        }
    }

    private void dealOffLineChat(String responseText) {
        DataOffLineChat dataOffLineChat = JSON.parseObject(responseText, DataOffLineChat.class);

        DataOffLineChat.RecordBean record = dataOffLineChat.getRecord();
        if (record == null) {
            return;
        }
        List<DataOffLineChat.RecordBean.MessageListBean> messageList = record.getMessageList();
        if (messageList.size() > 0)
            for (int i = 0; i < messageList.size(); i++) {
                initListItem(messageList.get(i));
            }
    }

    private void initListItem(final DataOffLineChat.RecordBean.MessageListBean record) {
        AppConfig.CHAT_FRIEND_ID = record.getFriendsId();

        record.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
        record.setType(Constants.CHAT_ITEM_TYPE_LEFT);
        CusChatData cusRealmChatMsg = new CusChatData();
//            String format = TimeUtil.sf.format(new Date());
//            cusRealmChatMsg.setCreated(format);
        String Mytime = record.getRequestTime();
        String time = AppConfig.mCHAT_RECEIVE_TIME_REALM;
        AppConfig.mCHAT_RECEIVE_TIME_REALM = record.getRequestTime();
//        String time = (String) SPUtils.get(this, AppConfig.CHAT_RECEIVE_TIME_REALM,"");
        if (!StrUtils.isEmpty(time)) {
            try {
                int i = TimeUtil.stringDaysBetween(record.getRequestTime(), time);
                if (MathUtils.abs(i) < 5) {
                    Mytime = "";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
//        SPUtils.put(this, AppConfig.CHAT_RECEIVE_TIME_REALM, (String) record.getRequestTime());
        if (!SplitWeb.IS_CHAT.equals("1"))
            dealList(record);
        else {

        }
        cusRealmChatMsg.setCreated(Mytime);
        cusRealmChatMsg.setMessage(record.getMessage());
        cusRealmChatMsg.setMessageType(record.getMessageType());
        cusRealmChatMsg.setReceiveId(record.getFriendsId());
        cusRealmChatMsg.setSendId(record.getUserId());
        cusRealmChatMsg.setUserMessageType(record.getType());
        cusRealmChatMsg.setTotalId(record.getFriendsId() + SplitWeb.getUserId());

        realmChatHelper.addRealmChat(cusRealmChatMsg);//更新聊天数据

        CusHomeRealmData homeRealmData = realmHelper.queryAllRealmChat(record.getFriendsId());

        if (homeRealmData != null) {
//            realmHelper.updateMsg(record.getFriendsId(), record.getMessage(), record.getRequestTime());//更新首页聊天界面数据（消息和时间）
            realmHelper.updateMsg(record.getFriendsId(), record.getMessage(), record.getRequestTime(),record.getShieldType(),record.getDisturbType(),record.getTopType());//更新首页聊天界面数据（消息和时间）


            realmHelper.updateNum(record.getFriendsId());//更新首页聊天界面数据（未读消息数目）
        } else {
            final CusHomeRealmData cusJumpChatData = new CusHomeRealmData();
            cusJumpChatData.setHeadImg(record.getHeadImg());
            cusJumpChatData.setFriendId(record.getFriendsId());
            cusJumpChatData.setNickName(record.getNickName());
            cusJumpChatData.setMsg(record.getMessage());
            cusJumpChatData.setTime(record.getRequestTime());
            cusJumpChatData.setNum(0);
//            realmHelper.updateNum(record.getFriendsId());
            realmHelper.addRealmMsg(cusJumpChatData);
        }
    }

    private void dealList(final DataOffLineChat.RecordBean.MessageListBean record) {
        final CusJumpChatData cusJumpChatData = new CusJumpChatData();
        cusJumpChatData.setFriendHeader(record.getHeadImg());
        cusJumpChatData.setFriendId(record.getFriendsId());
        cusJumpChatData.setFriendName(record.getNickName());

//        发送广播更新首页
        Intent intent = new Intent();
        intent.putExtra("message", record.getMessage());
        intent.putExtra("id", record.getFriendsId());
        intent.setAction("action.refreshMsgFragment");
        sendBroadcast(intent);
//在前台的时候处理接收到消息的事件
        if (SysRunUtils.isAppOnForeground(MyApplication.getAppContext())) {
            ToastUtil.show("收到来自" + record.getNickName() + "的一条新消息");
        } else {
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
                        notificationUtils.sendNotification(cusJumpChatData, record.getNickName(), record.getMessage(), bitmap, AppConfig.TYPE_CHAT);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private void dealFriendPush(String responseText) {
        DataFriendPush dataFriendPush = JSON.parseObject(responseText, DataFriendPush.class);
        final DataFriendPush.RecordBean record = dataFriendPush.getRecord();
        if (record == null) {
            return;
        }
        final List<DataFriendPush.RecordBean.MessageListBean> messageList = record.getMessageList();
        for (int i = 0; i < messageList.size(); i++) {
            int num = (int) SPUtils.get(this, AppConfig.LINKMAN_FRIEND_NUM, 0);
            SPUtils.put(this, AppConfig.LINKMAN_FRIEND_NUM, num + 1);
            Intent intent = new Intent();
            intent.putExtra("num", num + 1);
            intent.setAction("action.addFriend");
            sendBroadcast(intent);

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
                        String remark = (!StrUtils.isEmpty(messageListBean.getRemark())) ? messageListBean.getRemark() : "没有备注";
                        notificationUtils.sendNotification(messageListBean.getNickName() + "加您为好友", "备注：" + remark, bitmap, AppConfig.TYPE_NOTICE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }
    private void CGS(String responseText) {
        DataCreatGroupResult dataCreatGroupResult = JSON.parseObject(responseText, DataCreatGroupResult.class);
        DataCreatGroupResult.RecordBean record = dataCreatGroupResult.getRecord();
//        record1 = dataCreatGroupResult.getRecord();
        if (record != null) {
//            sendText(SplitWeb.groupSend(record.getGroupOfId(),"群创建成功，快来聊天吧",AppConfig.SEND_MESSAGE_TYPE_TEXT, TimeUtil.getTime()));
            final CusHomeRealmData cusJumpChatData = new CusHomeRealmData();
            cusJumpChatData.setHeadImg(record.getGroupHeadImg());
            cusJumpChatData.setFriendId(record.getGroupOfId());
            cusJumpChatData.setNickName(record.getGroupNickName());
            cusJumpChatData.setMsg("我新建了一个群");
            cusJumpChatData.setTime(TimeUtil.getTime());
            cusJumpChatData.setNum(0);
//            cusJumpChatData.setType(RealmHomeHelper.TypeQun);
            realmHelper.addRealmMsgQun(cusJumpChatData);
            Intent intent = new Intent();
            intent.putExtra("message", "我新建了一个群");
            intent.putExtra("id", record.getGroupOfId());
            intent.setAction("action.refreshMsgFragment");
            sendBroadcast(intent);
        }

    }

    private void initGroupReceiveData(String responseText) {
        DataGroupChatResult dataGroupChat = JSON.parseObject(responseText, DataGroupChatResult.class);
        final DataGroupChatResult.RecordBean record = dataGroupChat.getRecord();
        if (record == null)
            return;
        if ( record.getOperationType().equals("2"))
            return;
        AppConfig.CHAT_GROUP_ID = record.getGroupId();
        String Mytime = record.getRequestTime();

        String time = AppConfig.mCHAT_RECEIVE_TIME_REALM_GROUP;
        AppConfig.mCHAT_RECEIVE_TIME_REALM_GROUP = record.getRequestTime();
//        String time = (String) SPUtils.get(this, AppConfig.CHAT_RECEIVE_TIME_REALM_GROUP,"");
        if (!StrUtils.isEmpty(time)) {
            try {
                int i = TimeUtil.stringDaysBetween(record.getRequestTime(), time);
//                发送时间之间的间隔小于五分钟，则不显示时间
                if (MathUtils.abs(i) < 5) {
//                    record.setRequestTime("");
                    Mytime = "";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (record.getMessageType().equals(Constants.CHAT_NOTICE)) {
            Mytime = "";
            AppConfig.mCHAT_RECEIVE_TIME_REALM_GROUP = "";
        }
//        SPUtils.put(this, AppConfig.CHAT_RECEIVE_TIME_REALM_GROUP, (String)record.getRequestTime());
        if (!SplitWeb.IS_CHAT_GROUP.equals("2")) {
//            不在聊天界面收到消息时候的处理
            noGroupChatUI(record);
        }
        setGroupNotify(record);
        CusGroupChatData groupChatData = new CusGroupChatData();
        groupChatData.setCreated(Mytime);
        groupChatData.setFriendId(record.getMemberId());
        groupChatData.setGroupId(record.getGroupId());
        groupChatData.setGroupUserId(record.getGroupId() + SplitWeb.getUserId());
        groupChatData.setImgHead(record.getMemberHeadImg());
        groupChatData.setImgGroup(record.getGroupHeadImg());
        groupChatData.setMessage(record.getMessage());
        groupChatData.setNameGroup(record.getGroupName());
        groupChatData.setNameFriend(record.getMemberName());
        groupChatData.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
        groupChatData.setUserMessageType(Constants.CHAT_ITEM_TYPE_LEFT);
        groupChatData.setMessageType(record.getMessageType());


        groupChatData.setDisturbType(record.getDisturbType());
        groupChatData.setTopType(record.getTopType());
        groupChatData.setOperationType(record.getOperationType());
        groupChatData.setBannedType(record.getBannedType());
        groupChatData.setAssistantType(record.getAssistantType());

        realmGroupChatHelper.addRealmChat(groupChatData);//更新群聊聊天数据
        MyLog.e("realmGroupChatHelper", "msg=" + record.getMessage());
        CusHomeRealmData homeRealmData = realmHelper.queryAllRealmChat(record.getGroupId());
        String msg = record.getMessage();
        if (!record.getMessageType().equals(Constants.CHAT_NOTICE)) {
            msg = record.getMemberName() + "：" + record.getMessage();
        }
        if (homeRealmData != null) {

            realmHelper.updateGroupMsg(record.getGroupId(), msg, record.getRequestTime(),record);//更新首页聊天界面数据（消息和时间）
            realmHelper.updateNum(record.getGroupId());//更新首页聊天界面数据（未读消息数目）
        } else {
            final CusHomeRealmData cusJumpChatData = new CusHomeRealmData();
            cusJumpChatData.setHeadImg(record.getGroupHeadImg());
            cusJumpChatData.setFriendId(record.getGroupId());
            cusJumpChatData.setNickName(record.getGroupName());
            cusJumpChatData.setMsg(msg);
            cusJumpChatData.setTime(record.getRequestTime());
            cusJumpChatData.setMsgIsDisTurb(record.getDisturbType());
            cusJumpChatData.setTopType(record.getTopType());
            cusJumpChatData.setOperationType(record.getOperationType());
            cusJumpChatData.setBannedType(record.getBannedType());
            cusJumpChatData.setAssistantType(record.getAssistantType());


            cusJumpChatData.setNum(0);
//            realmHelper.updateNum(record.getFriendsId());
            realmHelper.addRealmMsgQun(cusJumpChatData);
        }
    }

    private void dealGroupSend(String message) {
        DataGroupChatSend dataGroupSend = JSON.parseObject(message, DataGroupChatSend.class);
        DataGroupChatSend.RecordBean record = dataGroupSend.getRecord();
        if (record != null) {
            initMsgGroupSend(record);//发布广播更新首页的信息
            CusGroupChatData cusRealmChatMsg = new CusGroupChatData();
            String MyTime = record.getRequestTime();

            String time = AppConfig.mCHAT_SEND_TIME_REALM_GROUP;
            AppConfig.mCHAT_SEND_TIME_REALM_GROUP = record.getRequestTime();
//            String time = (String) SPUtils.get(this, AppConfig.CHAT_SEND_TIME_REALM_GROUP,"");
            if (!StrUtils.isEmpty(time)) {
                try {
                    int i = TimeUtil.stringDaysBetween(record.getRequestTime(), time);
                    if (MathUtils.abs(i) < 5) {
                        MyTime = "";
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
//            SPUtils.put(this, AppConfig.CHAT_SEND_TIME_REALM_GROUP,record.getRequestTime());
            cusRealmChatMsg.setCreated(MyTime);
//            cusRealmChatMsg.setCreated(TimeUtil.sf.format(new Date()));
            cusRealmChatMsg.setMessage(record.getMessage());
            cusRealmChatMsg.setMessageType(record.getMessageType());
            cusRealmChatMsg.setGroupId(record.getGroupId());
            cusRealmChatMsg.setGroupUserId(record.getGroupId() + SplitWeb.getUserId());
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
        if (record != null) {
            sendText(SplitWeb.privateSend(record.getFriendsId(), "我们已经是好友了，快来聊一聊吧", ChatActivity.messageType, TimeUtil.getTime()));

            final CusHomeRealmData cusJumpChatData = new CusHomeRealmData();
            cusJumpChatData.setHeadImg(record.getHeadImg());
            cusJumpChatData.setFriendId(record.getFriendsId());
            cusJumpChatData.setNickName(record.getNickName());
            cusJumpChatData.setMsg("我们已经是好友了，快来聊一聊吧");
            cusJumpChatData.setTime(TimeUtil.getTime());
            cusJumpChatData.setNum(0);
            realmHelper.addRealmMsg(cusJumpChatData);
            Intent intent = new Intent();
            intent.putExtra("message", "我们已经是好友了，快来聊一聊吧");
            intent.putExtra("id", record.getFriendsId());
            intent.setAction("action.refreshMsgFragment");
            sendBroadcast(intent);
        }
    }

    private void dealFriend(String responseText) {
        DataAddfriendSendRequest dataAddfriendSendRequest = JSON.parseObject(responseText, DataAddfriendSendRequest.class);
        DataAddfriendSendRequest.RecordBean record = dataAddfriendSendRequest.getRecord();
        int num = (int) SPUtils.get(this, AppConfig.LINKMAN_FRIEND_NUM, 0);
        SPUtils.put(this, AppConfig.LINKMAN_FRIEND_NUM, num + 1);
        Intent intent = new Intent();
        intent.putExtra("num", num + 1);
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
            String myTime = record.getRequestTime();
            String time = AppConfig.mCHAT_SEND_TIME_REALM;
            AppConfig.mCHAT_SEND_TIME_REALM = record.getRequestTime();

//            String time = (String) SPUtils.get(this, AppConfig.CHAT_SEND_TIME_REALM,"");
//            SPUtils.put(this, AppConfig.CHAT_SEND_TIME_REALM, (String)record.getRequestTime());
            if (!StrUtils.isEmpty(time)) {
                try {
                    int i = TimeUtil.stringDaysBetween(myTime, time);
                    if (MathUtils.abs(i) < 5) {
                        myTime = "";
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            cusRealmChatMsg.setCreated(myTime);
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
//        realmHelper.updateMsg(record.getFriendsId(), record.getMessage(), record.getRequestTime());//更新首页聊天界面数据（消息和时间）
        realmHelper.updateMsg(record.getFriendsId(), record.getMessage(), record.getRequestTime(),record.getShieldType(),record.getDisturbType(),record.getTopType());//更新首页聊天界面数据（消息和时间）

    }

    private void initMsgGroupSend(DataGroupChatSend.RecordBean record) {
        Intent intent = new Intent();
        intent.putExtra("message", record.getMessage());
        intent.putExtra("id", record.getGroupId());
        intent.setAction("action.refreshMsgFragment");
        sendBroadcast(intent);
//        realmHelper.updateMsg(record.getGroupId(), record.getMessage(), record.getRequestTime(),record.getShieldType(),record.getDisturbType(),record.getTopType());//更新首页聊天界面数据（消息和时间）
        realmHelper.updateGroupMsg(record.getGroupId(), record.getMessage(), record.getRequestTime(),record);//更新首页聊天界面数据（消息和时间）
    }


    CusChatData cusRealmChatMsg = null;
    CusHomeRealmData cusJumpChatData = null;

    private void dealReceiver(String message) {
        DataJieShou dataJieShou = JSON.parseObject(message, DataJieShou.class);
        final DataJieShou.RecordBean record = dataJieShou.getRecord();
        if (record == null) {
            return;
        }
        if ( record.getShieldType().equals("2"))
            return;
        AppConfig.CHAT_FRIEND_ID = record.getFriendsId();
        record.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
        record.setType(Constants.CHAT_ITEM_TYPE_LEFT);
//        initMsgSend(record);
        if (cusRealmChatMsg == null)
            cusRealmChatMsg = new CusChatData();
//            String format = TimeUtil.sf.format(new Date());
//            cusRealmChatMsg.setCreated(format);
        String Mytime = record.getRequestTime();
        String time = AppConfig.mCHAT_RECEIVE_TIME_REALM;
        AppConfig.mCHAT_RECEIVE_TIME_REALM = record.getRequestTime();
//        String time = (String) SPUtils.get(this, AppConfig.CHAT_RECEIVE_TIME_REALM,"");
        if (!StrUtils.isEmpty(time)) {
            try {
                int i = TimeUtil.stringDaysBetween(record.getRequestTime(), time);
                if (MathUtils.abs(i) < 5) {
//                    record.setRequestTime("");
                    Mytime = "";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
//        SPUtils.put(this,AppConfig.CHAT_RECEIVE_TIME_REALM,record.getRequestTime());
//        if (!SplitWeb.IS_CHAT.equals("1")) {
////            不在聊天界面收到消息时候的处理
//            noChatUI(record);
//        }
        if (!record.getDisturbType().equals("2"))
            xipinhuanxing(record);
        cusRealmChatMsg.setCreated(Mytime);
        cusRealmChatMsg.setMessage(record.getMessage());
        cusRealmChatMsg.setMessageType(record.getMessageType());
        cusRealmChatMsg.setReceiveId(record.getFriendsId());
        cusRealmChatMsg.setSendId(record.getUserId());
        cusRealmChatMsg.setUserMessageType(record.getType());
        cusRealmChatMsg.setImgUrl(record.getHeadImg());
        cusRealmChatMsg.setTotalId(record.getFriendsId() + SplitWeb.getUserId());

        realmChatHelper.addRealmChat(cusRealmChatMsg);//更新聊天数据

        CusHomeRealmData homeRealmData = realmHelper.queryAllRealmChat(record.getFriendsId());

        if (homeRealmData != null) {
//            cusJumpChatData.setIsShield(record.getShieldType());
//            cusJumpChatData.setIsTopMsg(record.getTopType());
//            cusJumpChatData.setMsgIsDisTurb(record.getDisturbType());


            realmHelper.updateMsg(record.getFriendsId(), record.getMessage(), record.getRequestTime(),record.getShieldType(),record.getDisturbType(),record.getTopType());//更新首页聊天界面数据（消息和时间）
//            boolean chatZero = MyApplication.getApp().isChatZero();
//            boolean chatZero = isChatZero();
            if (SplitWeb.IS_CHAT_Zero)
                realmHelper.updateNum(record.getFriendsId());//更新首页聊天界面数据（未读消息数目）
            else {
                realmHelper.updateNumZero(record.getFriendsId());//更新首页聊天界面数据（未读消息数目）
            }
        } else {
            if (cusJumpChatData == null)
                cusJumpChatData = new CusHomeRealmData();
            cusJumpChatData.setHeadImg(record.getHeadImg());
            cusJumpChatData.setFriendId(record.getFriendsId());
            cusJumpChatData.setNickName(record.getFriendsName());
            cusJumpChatData.setMsg(record.getMessage());
            cusJumpChatData.setIsShield(record.getShieldType());
            cusJumpChatData.setIsTopMsg(record.getTopType());
            cusJumpChatData.setMsgIsDisTurb(record.getDisturbType());
            cusJumpChatData.setTime(record.getRequestTime());
            cusJumpChatData.setNum(0);
//            realmHelper.updateNum(record.getFriendsId());
            realmHelper.addRealmMsg(cusJumpChatData);
        }
        Intent intent = new Intent();
        intent.putExtra("message", record.getMessage());
        intent.putExtra("id", record.getFriendsId());
        intent.setAction("action.refreshMsgFragment");
        sendBroadcast(intent);
//        initMsgSend(record);
    }
    //息屏唤醒
    private void xipinhuanxing(final DataJieShou.RecordBean record) {
        final CusJumpChatData cusJumpChatData = new CusJumpChatData();
        cusJumpChatData.setFriendHeader(record.getHeadImg());
        cusJumpChatData.setFriendId(record.getFriendsId());
        cusJumpChatData.setFriendName(record.getFriendsName());


        PowerManager pm = (PowerManager) MyApplication.this.getSystemService(Context.POWER_SERVICE);
        boolean screenOn = pm.isScreenOn();
        if (!screenOn) {
            // 获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
            @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "bright");
            wl.acquire(10000); // 点亮屏幕
            wl.release(); // 释放
        }
//        if (!SplitWeb.IS_CHAT.equals("1"))
        if (!SysRunUtils.isAppOnForeground(MyApplication.getAppContext())||!SplitWeb.IS_CHAT.equals("1"))
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
                        notificationUtils.sendNotification(cusJumpChatData, record.getFriendsName(), record.getMessage(), bitmap, AppConfig.TYPE_CHAT_QUN);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

    }

    Bitmap bitmap;

    private void noChatUI(final DataJieShou.RecordBean record) {
        final CusJumpChatData cusJumpChatData = new CusJumpChatData();
        cusJumpChatData.setFriendHeader(record.getHeadImg());
        cusJumpChatData.setFriendId(record.getFriendsId());
        cusJumpChatData.setFriendName(record.getFriendsName());

//        发送广播更新首页
        Intent intent = new Intent();
        intent.putExtra("message", record.getMessage());
        intent.putExtra("id", record.getFriendsId());
        intent.setAction("action.refreshMsgFragment");
        sendBroadcast(intent);


//在前台的时候处理接收到消息的事件
//        if (SysRunUtils.isAppOnForeground(MyApplication.getAppContext()))
//        {
//            ToastUtil.show("收到来自"+record.getFriendsName()+"的一条新消息");
//
//        }

//        else {
//
////            Intent intent1 = new Intent();
////            intent1.putExtra("test_msg",record.getMessage());
////            intent1.putExtra("test_id",record.getFriendsId());
////            intent1.setAction("action.test");
////            sendBroadcast(intent1);
//
//
//            //APP在后台的时候处理接收到消息的事件
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        bitmap = Glide.with(MyApplication.getAppContext())
//                                .load(record.getHeadImg())
//                                .asBitmap() //必须
//                                .centerCrop()
//                                .into(500, 500)
//                                .get();
//                        NotificationUtil notificationUtils = new NotificationUtil(getApplicationContext());
//                        notificationUtils.sendNotification(cusJumpChatData, record.getFriendsName(), record.getMessage(), bitmap, AppConfig.TYPE_CHAT);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }
    }


    private void noGroupChatUI(final DataGroupChatResult.RecordBean record) {
        final CusJumpChatData cusJumpChatData = new CusJumpChatData();
        cusJumpChatData.setFriendHeader(record.getGroupHeadImg());
        cusJumpChatData.setFriendId(record.getGroupId());
        cusJumpChatData.setFriendName(record.getGroupName());
//        发送广播更新首页
        Intent intent = new Intent();
        intent.putExtra("message", record.getMessage());
        intent.putExtra("id", record.getGroupId());
        intent.setAction("action.refreshMsgFragment");
        sendBroadcast(intent);


//在前台的时候处理接收到消息的事件
//        if (SysRunUtils.isAppOnForeground(MyApplication.getAppContext()))
//        {
//            ToastUtil.show("收到来自"+record.getGroupName()+"的一条新消息");
////            JNoticeAgent.setIsAutoDismiss(true);
////            JNoticeAgent.setAutoDismissTime(5000);
////            JNoticeAgent.setIsUseHomeKey(true);
////            JNoticeAgent.register(this);
////            JNoticeAgent.getJNoticeAgent().setAdapter(new JDefaultAdapter(this, R.layout.jnotice_adpter_item, null));
////            JNoticeAgent.addJNotice(new JNoticeBean(1,record.getGroupName(),record.getMessage(),R.drawable.dou_logo));
//        }else {
//
//        }
    }

    private void setGroupNotify(final DataGroupChatResult.RecordBean record) {
        final CusJumpChatData cusJumpChatData = new CusJumpChatData();
        cusJumpChatData.setFriendHeader(record.getGroupHeadImg());
        cusJumpChatData.setFriendId(record.getGroupId());
        cusJumpChatData.setFriendName(record.getGroupName());
        PowerManager pm = (PowerManager) MyApplication.this.getSystemService(Context.POWER_SERVICE);
        boolean screenOn = pm.isScreenOn();
        if (!screenOn) {
            // 获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
            @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "bright");
            wl.acquire(10000); // 点亮屏幕
            wl.release(); // 释放
        }
//        if (!SysRunUtils.isAppOnForeground(MyApplication.getAppContext()))
        //APP在后台的时候处理接收到消息的事件
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String msg = record.getMessage();
                    if (!record.getMessageType().equals(Constants.CHAT_NOTICE)) {
                        msg = record.getMemberName() + "：" + record.getMessage();
                    }


                    bitmap = Glide.with(MyApplication.getAppContext())
                            .load(record.getGroupHeadImg())
                            .asBitmap() //必须
                            .centerCrop()
                            .into(500, 500)
                            .get();
                    NotificationUtil notificationUtils = new NotificationUtil(getApplicationContext());
                    notificationUtils.sendNotification(cusJumpChatData, record.getGroupName(), msg, bitmap, AppConfig.TYPE_CHAT_QUN);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void noGroupChatUIOffLine(final DataOffLineGroupChat.RecordBean.MessageListBean record) {
        final CusJumpChatData cusJumpChatData = new CusJumpChatData();
        cusJumpChatData.setFriendHeader(record.getGroupHeadImg());
        cusJumpChatData.setFriendId(record.getGroupId());
        cusJumpChatData.setFriendName(record.getGroupName());

//        发送广播更新首页
        Intent intent = new Intent();
        intent.putExtra("message", record.getMessage());
        intent.putExtra("id", record.getGroupId());
        intent.setAction("action.refreshMsgFragment");
        sendBroadcast(intent);
//在前台的时候处理接收到消息的事件
        if (SysRunUtils.isAppOnForeground(MyApplication.getAppContext())) {
            ToastUtil.show("收到来自" + record.getGroupName() + "的一条新消息");
        } else {
            //APP在后台的时候处理接收到消息的事件
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String msg = record.getMessage();
                    if (!record.getMessageType().equals(Constants.CHAT_NOTICE)) {
                        msg = record.getMemberName() + "：" + record.getMessage();
                    }
                    try {
                        bitmap = Glide.with(MyApplication.getAppContext())
                                .load(record.getGroupHeadImg())
                                .asBitmap() //必须
                                .centerCrop()
                                .into(500, 500)
                                .get();
                        NotificationUtil notificationUtils = new NotificationUtil(getApplicationContext());
                        notificationUtils.sendNotification(cusJumpChatData, record.getGroupName(), msg, bitmap, AppConfig.TYPE_CHAT_QUN);
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
//        ToastUtil.show("集群出错"+error.getRequestText());
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(AppConfig.NORMAL,SplitWeb.addrPort(), new NetWorkUtlis.OnNetWork() {
            @Override
            public void onNetSuccess(String msg) {
                Log.e("onNetSuccess","msg="+msg);
                DataJiqun dataJiqun = JSON.parseObject(msg, DataJiqun.class);
                DataJiqun.RecordBean record = dataJiqun.getRecord();
                if (record != null)
                {
                    initjiqun(record);
                }
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                IntentUtils.JumpFinishTo(MainActivity.class);
            }


        });
    }
    private void initjiqun(DataJiqun.RecordBean record) {
        SplitWeb.WS_REQUEST = record.getServerIpWs();
        String serverIpWs = record.getServerIpWs();
        aCache.remove(AppConfig.TYPE_URL);
        aCache.put(AppConfig.TYPE_URL,serverIpWs);

        WebSocketSetting.setConnectUrl(aCache.getAsString(AppConfig.TYPE_URL));//必选
        Log.e("TYPE_URL=", aCache.getAsString(AppConfig.TYPE_URL) + "---------------------initjiqun------");
//        WebSocketSetting.setConnectUrl("ws://120.78.92.225:9093");//必选
//        WebSocketSetting.setResponseProcessDelivery(new AppResponseDispatcher());
//        WebSocketSetting.setReconnectWithNetworkChanged(true);
//
//        //启动 WebSocket 服务
//        Intent intent = new Intent(this, WebSocketService.class);
//        startService(intent);
//        mConnectManager = new WebSocketServiceConnectManager(this, this);
//        mConnectManager.onCreate();
//        sendText(SplitWeb.bindUid());
        Intent intent2 = new Intent();
        intent2.setAction("start_application");
        sendBroadcast(intent2);
//        ToastUtil.show("重新配置完成");
    }
    private void initSetData(DataLogin.RecordBean dataLogin) {
        SPUtils.put(this,AppAllKey.USER_ID_KEY,dataLogin.getUserId());
        SPUtils.put(this,AppAllKey.USER_Token,dataLogin.getUserToken());

        SPUtils.put(this, AppConfig.TYPE_NAME,dataLogin.getNickName());
        SPUtils.put(this,AppConfig.TYPE_NO,dataLogin.getWxSno());
        SPUtils.put(this,AppConfig.TYPE_PHONE,dataLogin.getWxSno());

        SPUtils.put(this,AppConfig.TYPE_SIGN,dataLogin.getPersonaSignature());
//        SPUtils.put(this,AppConfig.TYPE_WS_REQUEST,dataLogin.getServerIpWs());
        SplitWeb.USER_TOKEN = dataLogin.getUserToken();
        SplitWeb.MOBILE = dataLogin.getMobile();
        SplitWeb.QR_CODE = dataLogin.getQrcode();
        SplitWeb.NICK_NAME = dataLogin.getNickName();
        SplitWeb.PERSON_SIGN = dataLogin.getPersonaSignature();
        SplitWeb.QR_CODE = dataLogin.getQrcode();
        SplitWeb.WX_SNO = dataLogin.getWxSno();
        SplitWeb.USER_ID = dataLogin.getUserId();
        SplitWeb.USER_HEADER = dataLogin.getHeadImg();
//        SplitWeb.WS_REQUEST = dataLogin.getServerIpWs();
        String serverIpWs = dataLogin.getServerIpWs();
        aCache.remove(AppConfig.TYPE_URL);
        aCache.put(AppConfig.TYPE_URL,serverIpWs);
        String json = JSON.toJSON(dataLogin).toString();
        aCache.remove(AppConfig.TOKEN_KEY);
        aCache.put(AppAllKey.TOKEN_KEY, json);

        WebSocketSetting.setConnectUrl(aCache.getAsString(AppConfig.TYPE_URL));//必选
        Log.e("TYPE_URL=", aCache.getAsString(AppConfig.TYPE_URL) + "---------------------2122------");
//        WebSocketSetting.setConnectUrl("ws://192.168.4.133:9093");//必选
        WebSocketSetting.setResponseProcessDelivery(new AppResponseDispatcher());
        WebSocketSetting.setReconnectWithNetworkChanged(true);

        //启动 WebSocket 服务
        Intent intent = new Intent(this, WebSocketService.class);
        startService(intent);
        mConnectManager = new WebSocketServiceConnectManager(this, this);
        mConnectManager.onCreate();
//        ToastUtil.show("一秒后重启应用");
//        WebSocketSetting.setConnectUrl(serverIpWs);//必选
//        Intent intent8 = getBaseContext().getPackageManager()
//                .getLaunchIntentForPackage(getBaseContext().getPackageName());
//        PendingIntent restartIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent8, PendingIntent.FLAG_ONE_SHOT);
//        AlarmManager mgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, restartIntent); // 1秒钟后重启应用
//        System.exit(0);
//        Intent intent = new Intent();
//        intent.setAction("server_application");
//        sendBroadcast(intent);
    }
    /**
     * 判断服务是否处于运行状态.
     * @param servicename
     * @param context
     * @return
     */
    public static boolean isServiceRunning(String servicename,Context context){
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo>  infos = am.getRunningServices(100);
        for(ActivityManager.RunningServiceInfo info: infos){
            if(servicename.equals(info.service.getClassName())){
                return true;
            }
        }
        return false;
    }
}
