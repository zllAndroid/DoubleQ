package com.mding.chatfeng.about_application;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;

import com.alibaba.fastjson.JSON;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.deal_application.DealFriendAdd;
import com.mding.chatfeng.about_base.deal_application.DealGroupAdd;
import com.mding.chatfeng.about_base.deal_application.DealGroupInvitation;
import com.mding.chatfeng.about_base.deal_application.DealGroupInvitationQrCode;
import com.mding.chatfeng.about_base.deal_application.DealGroupingSort;
import com.mding.chatfeng.about_base.deal_application.DealModifyFriendList;
import com.mding.chatfeng.about_base.deal_application.DealModifyGroupList;
import com.mding.chatfeng.about_base.deal_application.DealModifyGroupOfList;
import com.mding.chatfeng.about_base.deal_application.DealUpdateFriend;
import com.mding.chatfeng.about_base.web_base.AppResponseDispatcher;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_chat.cus_data_group.RealmGroupChatHelper;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.NetWorkUtlis;
import com.mding.chatfeng.about_utils.about_realm.new_home.RealmChatHelper;
import com.mding.chatfeng.about_utils.about_realm.new_home.RealmHomeHelper;
import com.mding.chatfeng.main_code.mains.MsgFragment;
import com.mding.model.DataIsRealWeb;
import com.mding.model.DataJiqun;
import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.crash.PgyerCrashObservable;
import com.pgyersdk.crash.PgyerObserver;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
import com.zll.websocket.ErrorResponse;
import com.zll.websocket.IWebSocketPage;
import com.zll.websocket.Response;
import com.zll.websocket.WebSocketService;
import com.zll.websocket.WebSocketServiceConnectManager;
import com.zll.websocket.WebSocketSetting;

import java.util.List;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * 项目：DoubleQ
 * 文件描述：application控制器
 * 作者：zll
 * 创建时间：2019/4/12 0012
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class PresenterApp implements IWebSocketPage {

    Context mContext;
    AppView appView;
        public PresenterApp(Context mContext,AppView appView)
    {
        this.mContext=mContext;
        this.appView=appView;
    }
//    public PresenterApp(Context mContext)
//    {
//        this.mContext=mContext;
//    }
    public  void  initApp(BaseApplication mApplication)
    {

        /**
         * 必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回
         * 第一个参数：应用程序上下文
         * 第二个参数：如果发现滑动返回后立即触摸界面时应用崩溃，请把该界面里比较特殊的 View 的 class 添加到该集合中，目前在库中已经添加了 WebView 和 SurfaceView
         */
        BGASwipeBackHelper.init(mApplication, null);
        PgyCrashManager.register();
        PgyerCrashObservable.get().attach(new PgyerObserver() {
            @Override
            public void receivedCrash(Thread thread, Throwable throwable) {
            }
        });
//        初始化缓存，集群部分
        initCache();
        initRealm();

    }
    private void initRunnable() {
        AutoSaleTicket autoSaleTicket = new AutoSaleTicket();
        Thread t1 = new Thread(autoSaleTicket, "123");
        t1.start();
    }
    class AutoSaleTicket implements Runnable {
        private int ticket = 20;
        long checkDelay=10;
        long keepAliveDelay=60000;
        public void run() {

            while (true) {// 循环是指线程不停的去卖票
                // 当操作的是共享数据时,用同步代码块进行包围起来,这样在执行时,只能有一个线程执行同步代码块里面的内容
                synchronized (this) {
                    if (System.currentTimeMillis()-lastSendTime>keepAliveDelay)
                    {
                        acquireWakeLock();
                        lastSendTime=System.currentTimeMillis();
                    }else {
                        try {
                            Thread.sleep(checkDelay);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                // 所以把sleep放到同步代码块的外面,这样卖完一张票就休息一会,让其他线程再卖,这样所有的线程都可以卖票
                try {
                    Thread.sleep(200);
                } catch (Exception ex) {
                }
            }
        }
    }
    //获取电源锁，保持该服务在屏幕熄灭时仍然获取CPU时，保持运行
    private void acquireWakeLock()
    {
        if (null == wakeLock)
        {
            PowerManager pm = (PowerManager)mContext.getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK|PowerManager.ON_AFTER_RELEASE, "PostLocationService");
            if (null != wakeLock)
            {
                wakeLock.acquire();
            }
        }
    }
    long lastSendTime=0;
    private void initCache() {
        if (aCache==null)
            aCache =  ACache.get(mContext);
        String asString = aCache.getAsString(AppConfig.TYPE_WS_REQUEST);
        initServerBro();
        if (StrUtils.isEmpty(asString)) {
//            initServerBro();
        }else {
            initManagerService();
        }
    }
    IntentFilter intentFilter;
    private void initServerBro() {

        if (intentFilter == null) {
            intentFilter = new IntentFilter();
            intentFilter.addAction("server_application");
            mContext.registerReceiver(mRefreshBroadcastReceiver, intentFilter);
        }
    }
    public BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("server_application"))
            {
                initFirstService();
                //TODO 改端口
//                initOneService();
            }
        }
    };

    private void initFirstService() {
        String asString = aCache.getAsString(AppConfig.TYPE_WS_REQUEST);
        if (StrUtils.isEmpty(asString))
        {
            // TODO   Toast
//            ToastUtil.show("系统错误，请联系管理员...");
            return;
        }
        WebSocketSetting.setConnectUrl(asString);//必选
        MyLog.e("TYPE_WS_REQUEST=", asString + "---------------initFirstService------------");
//        WebSocketSetting.setConnectUrl("ws://120.78.92.225:9093");//必选
        AppResponseDispatcher appResponseDispatcher = new AppResponseDispatcher();
        WebSocketSetting.setResponseProcessDelivery(appResponseDispatcher);
        WebSocketSetting.setReconnectWithNetworkChanged(true);
        Intent intent = new Intent(mContext, WebSocketService.class);
        mContext.startService(intent);
//        MyLog.e("initFirstService",AppManager.getAppManager().currentActivity().getClass().getSimpleName()+"");
        if (mConnectManager!=null) {
//            mConnectManager.onDestroy();
            mConnectManager = null;
        }
        mConnectManager = new WebSocketServiceConnectManager(mContext, this);
        mConnectManager.onCreate();
        mConnectManager.reconnect();
        mConnectManager.reBind(SplitWeb.getSplitWeb().bindUid());
        Intent intent2 = new Intent();
        intent2.setAction("start_application");
        mContext.sendBroadcast(intent2);



    }
    public static WebSocketServiceConnectManager mConnectManager =null;
    private void initManagerService() {
        String asString = aCache.getAsString(AppConfig.TYPE_WS_REQUEST);
        if (StrUtils.isEmpty(asString))
        {
            // TODO  Toast
//            ToastUtil.show("系统错误，请联系管理员...");
            return;
        }
        WebSocketSetting.setConnectUrl(asString);//必选
        MyLog.e("TYPE_WS_REQUEST=", asString + "---------------initManagerService------------");
//        WebSocketSetting.setConnectUrl("ws://192.168.4.133:9093");//必选
        WebSocketSetting.setResponseProcessDelivery(new AppResponseDispatcher());
        WebSocketSetting.setReconnectWithNetworkChanged(true);

        //启动 WebSocket 服务
        Intent intent = new Intent(mContext, WebSocketService.class);
        boolean webSocketService = isServiceRunning("WebSocketService", BaseApplication.getAppContext());
        if (!webSocketService)
            mContext.startService(intent);
        if (mConnectManager!=null) {
            mConnectManager.onDestroy();
            mConnectManager = null;
        }
        mConnectManager = new WebSocketServiceConnectManager(mContext, this);
        mConnectManager.onCreate();
    }
    /**
     * 判断服务是否处于运行状态.
     * @param servicename
     * @param context
     * @return
     */
    public static boolean isServiceRunning(String servicename,Context context){
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> infos = am.getRunningServices(100);
        for(ActivityManager.RunningServiceInfo info: infos){
            if(servicename.equals(info.service.getClassName())){
                return true;
            }
        }
        return false;
    }
    /**
     * 配置初始化realm数据库
     */
    private void initRealm() {
        Realm.init(mContext);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(RealmHomeHelper.DB_NAME)
//                .deleteRealmIfMigrationNeeded()
//                .schemaVersion(3)
//                .migration(new Migration())
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
        realmHelper = new RealmHomeHelper(mContext);
        realmGroupChatHelper = new RealmGroupChatHelper(mContext);
        realmChatHelper = new RealmChatHelper(mContext);
    }

    RealmHomeHelper realmHelper;
    RealmChatHelper realmChatHelper;
    RealmGroupChatHelper realmGroupChatHelper;
    public  ACache aCache;
    @Override
    public void onServiceBindSuccess() {

    }

    @Override
    public void sendText(String text) {
        boolean isConnected = false;
        try {
            isConnected = HelpUtils.isNetworkConnected(AppManager.getAppManager().currentActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isConnected)
        {
//            aCache.remove(AppConfig.TYPE_METHON);
//            aCache.put(AppConfig.TYPE_METHON,text);
            mConnectManager.sendText(text);
        }else {
            ToastUtil.show("网络连接失败");
        }
    }

    @Override
    public void reconnect() {
        mConnectManager.reconnect();
    }
    @Override
    public void onConnected() {
//        添加重连机制，当连接成功后，重新绑定uid
        try {
//            if (!StrUtils.isEmpty(SplitWeb.getSplitWeb().getUserId())) {
//            String userId = SplitWeb.getSplitWeb().getUserId();
            if (!StrUtils.isEmpty(SplitWeb.getSplitWeb().getUserId())) {
                sendText(SplitWeb.getSplitWeb().bindUid());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectError(Throwable cause) {

    }

    @Override
    public void onWantConnect(Throwable cause) {

    }

    @Override
    public void onDisconnected() {
        senderror();
    }

    @Override
    public void onMessageResponse(Response message) {

        if (!message.getResponseText().contains("{")) {
            ToastUtil.show("系统参数异常，请重新请求");
            return;
        }
        if (appView!=null)
        appView.backData(message.getResponseText());
        String method = HelpUtils.backMethod(message.getResponseText());
        HelpUtils.HttpIsSucess(message.getResponseText());
        if (method.equals("bindUid")) {
            String only = HelpUtils.backOnly(message.getResponseText());
        }
        DealDataByApp.synData(mContext,message,realmHelper,realmChatHelper,realmGroupChatHelper);
//            DealDataByApp.initReceiver();
    }

    @Override
    public void onSendMessageError(ErrorResponse error) {
        MyLog.e("onSendMessageError",""+error.getResponseText());
        senderror();
    }

    public void OnDestry() {
        PgyCrashManager.unregister();
        try {
            if (mRefreshBroadcastReceiver!=null)
                mContext.unregisterReceiver(mRefreshBroadcastReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mConnectManager!=null) {
            mConnectManager.onDestroy();
            mConnectManager = null;
        }
        releaseWakeLock();
    }

    PowerManager.WakeLock wakeLock = null;
    //释放设备电源锁
    private void releaseWakeLock()
    {
        if (null != wakeLock)
        {
            wakeLock.release();
            wakeLock = null;
        }
    }
    private void senderror() {
        boolean isConnected = HelpUtils.isNetworkConnected(mContext);
        if (isConnected) {
            try {
                NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
                netWorkUtlis.setOnNetWork(AppConfig.NORMAL, SplitWeb.getSplitWeb().errorRequest(aCache.getAsString(AppConfig.TYPE_URL)), new NetWorkUtlis.OnNetWork() {
                    @Override
                    public void onNetSuccess(String result) {
                        MyLog.e("errorRequest", result + "---------------------result------");
                        DataIsRealWeb dataIsRealWeb = JSON.parseObject(result, DataIsRealWeb.class);
                        String status = dataIsRealWeb.getStatus();
                        if (status.equals("1")) {
                            dealStatusOne();
                        } else {
                            errorRequest();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            ToastUtil.show("请检查网络连接是否正常");
        }
    }
    private void dealStatusOne() {
        try {
            if (mConnectManager!=null) {
                mConnectManager.onDestroy();
                mConnectManager = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mConnectManager = new WebSocketServiceConnectManager(mContext, this);
        mConnectManager.onCreate();
    }
    private void errorRequest() {
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(AppConfig.NORMAL, SplitWeb.getSplitWeb().addrPort(), new NetWorkUtlis.OnNetWork() {
            @Override
            public void onNetSuccess(String msg) {
                MyLog.e("onNetSuccess","msg="+msg);
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
            }


        });
    }
    private void initjiqun(DataJiqun.RecordBean record) {
        SplitWeb.getSplitWeb().WS_REQUEST = record.getServerIpWs();
        String serverIpWs = record.getServerIpWs();
        String http = record.getServerIpHttp();
        aCache.remove(AppConfig.TYPE_WS_REQUEST);
        aCache.remove(AppConfig.TYPE_URL);
        aCache.put(AppConfig.TYPE_WS_REQUEST,serverIpWs);
        aCache.put(AppConfig.TYPE_URL,http);
        reStartApp();
    }
    private void reStartApp() {
//        ToastUtil.show("一秒后重启应用");
        Intent intent = mContext.getPackageManager()
                .getLaunchIntentForPackage(mContext.getPackageName());
        PendingIntent restartIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager mgr = (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, restartIntent); // 1秒钟后重启应用
        System.exit(0);
    }

}
