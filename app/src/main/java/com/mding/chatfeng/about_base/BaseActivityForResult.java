package com.mding.chatfeng.about_base;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.mding.chatfeng.R;
import com.mding.chatfeng.about_application.BaseApplication;
import com.mding.chatfeng.about_base.web_base.MessageEvent;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_custom.about_cus_dialog.DialogExitUtils;
import com.mding.chatfeng.about_custom.about_cus_dialog.DialogLoginUtils;
import com.mding.chatfeng.about_custom.loding.LoadingDialog;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.NetUtils;
import com.mding.chatfeng.about_utils.NotificationUtil;
import com.mding.chatfeng.about_utils.about_immersive.StateBarUtils;
import com.mding.chatfeng.about_utils.about_realm.new_home.RealmHomeHelper;
import com.mding.chatfeng.about_utils.windowStatusBar;
import com.mding.model.DataNoticAddFriendNews;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboutsystem.WindowBugDeal;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Stack;
import java.util.concurrent.ExecutionException;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseActivityForResult extends AppCompatActivity  {

    LoadingDialog.Speed speed = LoadingDialog.Speed.SPEED_TWO;
    private int repeatTime = 0;
    private long delayedTime = 200L;
    private long closeTime = 5000L;
    private int style = LoadingDialog.STYLE_LINE;
    String loadText = "加载中...";
    public static final int LOAD_SUCCESS = 1;
    public static final int LOAD_FAILED = 2;
    public static final int LOADING = 3;
    public static final int LOAD_WITHOUT_ANIM_SUCCESS = 4;
    public static final int LOAD_WITHOUT_ANIM_FAILED = 5;
    public static final int SAVE_YOU = 6;
    public static final int DOWN_DATA = 520;
    private LoadingDialog ld =null;
    //    是否拦截返回键  true 拦截
    private boolean intercept_back_event = true;
    //
    public Handler mHandler = null;
    public Handler mMsgHandler = null;
    Unbinder bind =null;
    View view;
    String simpleName;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /**
         * 切换为非全屏
         */
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        if (isChenjinshi())
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        getWindow().setBackgroundDrawableResource(android.R.color.transparent);// 将 Activity 的背景色取消


        AppManager.getAppManager().addActivity(this);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        simpleName = getClass().getSimpleName();
//        ScreenUtils.setWindowStatusBarColor(AppManager.getAppManager().currentActivity(),R.color.red);
        if (isChenjinshi())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

//            windowStatusBar.setStatusColor(this, getResources().getColor(R.color.app_theme), 50);
            getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        }
        initBeforeContentView();
        setContentView(getLayoutView());
//        hideBottomMenu();
        initStateBar();
        bind = ButterKnife.bind(this);

        if (mHandler==null)
            mHandler= new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    if (msg != null)
                    {
                        switch (msg.what) {
                            case LOAD_SUCCESS:
                                ld.loadSuccess();
//                                ld.close();
                                break;
                            case LOAD_FAILED:
                                ld.loadFailed();
//                                ld.close();
                                break;
                            case LOADING:
                                ld.show();
                                break;
                            case LOAD_WITHOUT_ANIM_FAILED:
                                ld.loadFailed();
                                break;
                            case LOAD_WITHOUT_ANIM_SUCCESS:
                                ld.loadSuccess();
                                break;
                            case SAVE_YOU:
                                ld.close();
                                break;
                            default:
                                break;
                        }
                    }
                    return false;
                }
            });
        realmHelper = new RealmHomeHelper(this);
        initBaseView();
        EventBus.getDefault().register(this);
    }
    //    public static void setNavigationBar(Activity activity,int visible){
//        View decorView = activity.getWindow().getDecorView();
//        //显示NavigationBar
//        if (View.GONE == visible){
//            int option =  View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
//            decorView.setSystemUiVisibility(option);
//        }
//    }
    protected void hideBottomMenu() {


        //隐藏虚拟按键
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            if(v!=null){
                v.setSystemUiVisibility(View.GONE);
            }
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            if (decorView != null) {
                decorView.setSystemUiVisibility(uiOptions);
            }

        }
    }
    //    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEventMainThread(NetEvent event) {
//        ToastUtil.show("沒有網絡了");
////        mLinNet.setVisibility(event.isNet ? View.GONE : View.VISIBLE);
//    }
    private void dealObs(String data) {

        //        判断返回成功的  字段
        String isSucess = HelpUtils.HttpIsSucess(data.toString());
        if (isSucess.equals(AppAllKey.CODE_OK)) {

//            判断返回的方法名
            String s = HelpUtils.backMethod(data.toString());
//            父类全局处理
            switch (s)
            {
//                接收消息
                case Methon.PrivateChat:
                    initReceive(data.toString());
                    break;
                case Methon.PrivateSend:
//                    DataJieShou dataJieShou = JSON.parseObject(data.toString(), DataJieShou.class);
//                    DataJieShou.RecordBean record = dataJieShou.getRecord();
//                    realmHelper.updateMsg(record.getFriendsId()+SplitWeb.getSplitWeb().getUserId(),record.getMessage(),record.getRequestTime());//更新首页聊天界面数据（消息和时间）
                    receiveResultMsg(data.toString());
                    break;
//                    添加好友通知
                case "addFriendSend":
                    dealFriendData(data.toString());
                    break;
//                    重连后的连接成功
                case "coroutineUid":
                    ToastUtil.show("连接成功");
                    break;
//                    其他情况返回给子类
                default:
                    receiveResultMsg(data.toString());
                    break;
            }
        }
        else if (isSucess.equals("10086"))
        {
//            返回的自定义判断 ，则重连（返回的字段不可预测）
            sendWeb(SplitWeb.getSplitWeb().coroutineUid());
        }else  if (isSucess.equals("9001"))
        {
            errorResult(data.toString());
        }
        else {
            ToastUtil.show(isSucess);
        }
//        如果打开弹窗加载显示，收到服务器的返回0.5秒后自动关闭（防止反应太快还没显示清楚就隐藏）
        if (isSendDialog&&isSucess.equals(AppAllKey.CODE_OK))
        {
            mHandler.sendEmptyMessageDelayed(SAVE_YOU, 100);
//            mHandler.sendEmptyMessageDelayed(LOAD_SUCCESS, 100);
        }else if (isSendDialog)
        {
            mHandler.sendEmptyMessageDelayed(SAVE_YOU, 100);
        }
        if (isSend&&isSucess.equals(AppAllKey.CODE_OK))
        {
            mHandler.sendEmptyMessageDelayed(SAVE_YOU, 100);
        }else if (isSend)
        {
            mHandler.sendEmptyMessageDelayed(SAVE_YOU, 100);
        }


//        String only = HelpUtils.backOnly(data.toString());
//        if (only.equals("1"))
//        {
////            sendWebHaveDialog(SplitWeb.getSplitWeb().bindUid(),"断线重连中...","重连成功");
//            if (!StrUtils.isEmpty(SplitWeb.getSplitWeb().getUserId()))
//                sendWeb(SplitWeb.getSplitWeb().bindUid());
//        }
    }
    public void errorResult(String s) {
    }

    public void initStateBar() {
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
//        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
//        //设置状态栏透明
////        StatusBarUtil.setStatusBarColor(this,0x1edec9);
//        windowStatusBar.setStatusColor(this, getResources().getColor(R.color.app_theme), 50);
//           getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
//        StatusBarUtil.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
//        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
//            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
//            //这样半透明+白=灰, 状态栏的文字能看得清
//            StatusBarUtil.setStatusBarColor(this,0x1edec9);
//        }
//        if (simpleName.equals("MainActivity")) {
//            WindowBugDeal.checkDeviceHasNavigationBar(AppManager.getAppManager().currentActivity());
//        } else
//            WindowBugDeal.SetTop(AppManager.getAppManager().currentActivity());
    }

    public RealmHomeHelper getRealm() {
        return realmHelper;
    }

    RealmHomeHelper realmHelper;
    boolean isSendDialog=false;
    boolean isSend=false;
    protected void sendWeb(String text) {
        isSendDialog=false;
        boolean isConnected = false;
        try {
            isConnected = NetUtils.isNetworkConnected(BaseApplication.getAppContext());
        } catch (Exception e) {
            e.printStackTrace();
            isConnected = true;
        }
        if (isConnected)
            BaseApplication.getApp().sendData(text);
        else
        {
            try {
                ToastUtil.show(AppManager.getAppManager().currentActivity().getResources().getString(R.string.no_net));
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
        }
//            ToastUtil.show("请检查网络设置...");
    }
    public static  void send(String text) {
        boolean isConnected = HelpUtils.isNetworkConnected(AppManager.getAppManager().currentActivity());
        if (isConnected)
            BaseApplication.getApp().sendData(text);
        else
        {
            try {
                ToastUtil.show(AppManager.getAppManager().currentActivity().getResources().getString(R.string.no_net));
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    protected void sendWebHaveDialog(String text,String loadText,String loadSuccessText) {
        boolean isConnected = NetUtils.isWifi(this);
        if (!isConnected) {
            try {
                ToastUtil.show(AppManager.getAppManager().currentActivity().getResources().getString(R.string.no_net));
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
            return;
        }
        isSendDialog=true;
        if ((ld != null))
            ld.close();
        ld = new LoadingDialog(this);
        ld.setLoadingText(loadText)
//                .setSuccessText(loadSuccessText)
                .setInterceptBack(intercept_back_event)
                .setLoadSpeed(speed)
                .setRepeatCount(repeatTime)
//                .setDrawColor(Color.WHITE)
                .setLoadStyle(style)
                .show();
        saveForThesePeopleWhoDoNotCallCloseAndUseInterceptBackMethod(intercept_back_event);
        BaseApplication.getApp().sendData(text);
    }
    protected void sendWebOnlyDialog(String text,String loadText) {
        boolean isConnected = HelpUtils.isNetworkConnected(this);
        if (!isConnected) {
            try {
                ToastUtil.show(AppManager.getAppManager().currentActivity().getResources().getString(R.string.no_net));
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }
            return;
        }
        isSend=true;
        if ((ld != null))
            ld.close();
        ld = new LoadingDialog(this);
        ld.setLoadingText(loadText)
//                .setSuccessText(loadSuccessText)
                .setInterceptBack(intercept_back_event)
                .setLoadSpeed(speed)
                .setRepeatCount(repeatTime)
//                .setDrawColor(Color.WHITE)
                .setLoadStyle(style)
                .show();
        saveForThesePeopleWhoDoNotCallCloseAndUseInterceptBackMethod(intercept_back_event);
        BaseApplication.getApp().sendData(text);
    }
    protected void sendWebHaveData(String loadText,String loadSuccessText) {
        if ((ld != null))
            ld.close();
        ld = new LoadingDialog(this);
        ld.setLoadingText(loadText)
                .setSuccessText(loadSuccessText)
                .setInterceptBack(intercept_back_event)
                .setLoadSpeed(speed)
                .setRepeatCount(repeatTime)
//                .setDrawColor(Color.WHITE)
                .setLoadStyle(style)
                .show();
        saveForThesePeopleWhoDoNotCallCloseAndUseInterceptBackMethod(intercept_back_event);
//        BaseApplication.getApp().sendData(text);
    }
    private void saveForThesePeopleWhoDoNotCallCloseAndUseInterceptBackMethod(boolean intercept_back_event) {
        if (intercept_back_event) {
            mHandler.sendEmptyMessageDelayed(LOAD_FAILED, closeTime);
        }
    }

    protected void initBeforeContentView() {
        if (isChenjinshi()) {
            if (isGonesStatus()) {
                //全屏不显示状态栏导航栏
                StateBarUtils.setFullscreen(this, false, false);
            } else {
                //全屏显示状态栏隐藏导航栏
                StateBarUtils.setFullscreen(this, true, false);
//            设置状态栏的颜色
                windowStatusBar.setStatusColor(this, getResources().getColor(R.color.app_theme), 50);
//            StateBarUtils.setFullscreen(this,true,true);
            }
        }
    }

    protected void initBaseView() {
    }
    protected int getLayoutView() {
        return 0;
    }

    protected boolean isGones() {
        return true;
    }

    //    是否隐藏状态栏，默认不隐藏
    protected boolean isGonesStatus() {
        return false;
    }
//    是否沉浸式状态栏  默认是
    protected boolean isChenjinshi() {
        return true;
    }

//    是否聊天页面，默认不是
    protected boolean isChat() {
        return false;
    }

//    是否登录页面 默认不是
    protected boolean isLogin() {
        return false;
    }

    @Override
    protected void onDestroy() {
//        realmChatHelper.close();
//        realmHelper.close();
        super.onDestroy();
        if (bind!=null) {
            bind.unbind();
            bind=null;
        }
        if ((ld != null))
            ld.close();
//        关闭eventbus
        EventBus.getDefault().unregister(this);
//        关闭弹窗
        DialogUtils.isShow();
        DialogExitUtils.isShow();
        DialogLoginUtils.isShow();

        SplitWeb.getSplitWeb().IS_SET_ACTIVITY="00";
    }
    //订阅方法，接收到服务器返回事件处理
    @Subscribe(threadMode = ThreadMode.MAIN)
        public void onEvent(MessageEvent messageEvent){
        Log.e("messageEvent","onEven_messageEvent="+messageEvent.getMessage());
        Stack<AppCompatActivity> stack = AppManager.getAppManager().getStack();
        if (stack!=null&&stack.size()!=0) {
            String stackLast = stack.get(stack.size() - 1).getClass().getSimpleName();
            String current = AppManager.getAppManager().currentActivity().getClass().getSimpleName();
            if (stackLast.equals(current))
            {
                dealObs(messageEvent.getMessage());
            }
        }
    }
    Bitmap bitmap;
    //    处理好友的信息
    private void dealFriendData(String message) {
        DataNoticAddFriendNews dataNoticAddFriendNews = JSON.parseObject(message, DataNoticAddFriendNews.class);
        final    DataNoticAddFriendNews.RecordBean record = dataNoticAddFriendNews.getRecord();
        if (record!=null)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bitmap = Glide.with(BaseApplication.getAppContext())
                                .load(record.getHeadImg())
                                .asBitmap() //必须
                                .centerCrop()
                                .into(500, 500)
                                .get();
                        NotificationUtil notificationUtils = new NotificationUtil(getApplicationContext());
                        String remark = (!StrUtils.isEmpty(record.getRemark()))?record.getRemark():"没有备注";
                        notificationUtils.sendNotification(record.getNickName()+"加您为好友", "备注："+remark,bitmap,AppConfig.TYPE_NOTICE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    //处理接收到消息的显示
    private void initReceive(String message) {
//        DataJieShou dataJieShou = JSON.parseObject(message, DataJieShou.class);
//        final DataJieShou.RecordBean record = dataJieShou.getRecord();
//        AppConfig.CHAT_FRIEND_ID = record.getFriendsId();
//        record.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
//        record.setType(Constants.CHAT_ITEM_TYPE_LEFT);
//                1代表现在是聊天界面
        if (!SplitWeb.getSplitWeb().IS_CHAT.equals("1"))
        {
//            不在聊天界面收到消息时候的处理
//            noChatUI(record);

        }else
        {
//            聊天界面就直接下传数据
            receiveResultMsg(message);
        }
    }

    private void initview(){
        if (isTopBack())
        {
            try {
//                findViewById(R.id.include_top_lin_newback).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        AppManager.getAppManager().finishActivity();
//                    }
//                });
                findViewById(R.id.include_top_lin_newback).setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        AppManager.getAppManager().finishActivity();

                        return false;
                    }
                });

                isGone();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (isGones())
        {
            isGone();
        }
        if (isChat())
        {
            isChatWindow();
        }
        if (isLogin())
        {
            isLoginWindow();
        }
    }

    private void isGone() {
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
//        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        View mtv=null;
        try {
            mtv = (View) AppManager.getAppManager().currentActivity().findViewById(R.id.include_top_margin10);
        } catch (Exception e) {
            e.printStackTrace();
            mtv=null;
        }
        if (mtv==null)
        {
            return;
        }
//        LinearLayout mLinBac = (LinearLayout) AppManager.getAppManager().currentActivity().findViewById(R.id.include_top_lin_background);
//        mLinBac.setBackgroundColor(getResources().getColor(R.color.app_theme));
        mtv.setBackgroundColor(getResources().getColor(R.color.app_theme));
        // 设置状态栏高度
        int statusBarHeight = WindowBugDeal.getStatusBarHeight(this);
        //这里我用RelativeLayout布局为列，其他布局设置方法一样，只需改变布局名就行
        LinearLayout.LayoutParams layout=(LinearLayout.LayoutParams)mtv.getLayoutParams();
        //获得button控件的位置属性，需要注意的是，可以将button换成想变化位置的其它控件
//        layout.setMargins(0,-statusBarHeight,0,0);
        layout.height=statusBarHeight;
        //设置button的新位置属性,left，top，right，bottom
        mtv.setLayoutParams(layout);
//        mtv.getBackground().setAlpha(0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mtv.setVisibility(View.VISIBLE);
        }else
        {
            mtv.setVisibility(View.GONE);
        }
    }
    private void isChatWindow() {
//聊天界面因为window，需要状态栏高度撑起
        View mtv;
        mtv = (View) AppManager.getAppManager().currentActivity().findViewById(R.id.include_top_margin10);
        LinearLayout mLinBac = (LinearLayout) AppManager.getAppManager().currentActivity().findViewById(R.id.include_top_lin_background);
        mLinBac.setBackgroundColor(getResources().getColor(R.color.app_theme));
        mtv.setBackgroundColor(getResources().getColor(R.color.app_theme));
        // 设置状态栏高度
        int statusBarHeight = WindowBugDeal.getStatusBarHeight(this);
        //这里我用RelativeLayout布局为列，其他布局设置方法一样，只需改变布局名就行
        LinearLayout.LayoutParams layout=(LinearLayout.LayoutParams)mtv.getLayoutParams();
        //获得button控件的位置属性，需要注意的是，可以将button换成想变化位置的其它控件
//        layout.setMargins(0,-statusBarHeight,0,0);
        layout.height=statusBarHeight;
        //设置button的新位置属性,left，top，right，bottom
        mtv.setLayoutParams(layout);
//        mtv.getBackground().setAlpha(0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mtv.setVisibility(View.VISIBLE);
        }else
        {
            mtv.setVisibility(View.GONE);
        }
    }
    private void isLoginWindow() {
//聊天界面因为window，需要状态栏高度撑起
        View mtv;
        mtv = (View) AppManager.getAppManager().currentActivity().findViewById(R.id.include_top_margin10);
        mtv.setBackgroundColor(getResources().getColor(R.color.app_theme));
        // 设置状态栏高度
        int statusBarHeight = WindowBugDeal.getStatusBarHeight(this);
        //这里我用RelativeLayout布局为列，其他布局设置方法一样，只需改变布局名就行
        LinearLayout.LayoutParams layout=(LinearLayout.LayoutParams)mtv.getLayoutParams();
        //获得button控件的位置属性，需要注意的是，可以将button换成想变化位置的其它控件
//        layout.setMargins(0,-statusBarHeight,0,0);
        layout.height=statusBarHeight;
        //设置button的新位置属性,left，top，right，bottom
        mtv.setLayoutParams(layout);
//        mtv.getBackground().setAlpha(0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mtv.setVisibility(View.VISIBLE);
        }else
        {
            mtv.setVisibility(View.GONE);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        initview();
    }

    public boolean isSupportSwipeBack() {
        return true;
    }
    protected boolean isTopBack() {
        return true;
    }

    //接收到消息，传递给子类
    public void receiveResultMsg(String responseText) {

    }

}