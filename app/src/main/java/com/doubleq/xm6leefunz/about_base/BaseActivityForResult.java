package com.doubleq.xm6leefunz.about_base;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.doubleq.model.DataJieShou;
import com.doubleq.model.DataNoticAddFriendNews;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.web_base.MessageEvent;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_custom.about_cus_dialog.DialogLoginUtils;
import com.doubleq.xm6leefunz.about_custom.loding.LoadingDialog;
import com.doubleq.xm6leefunz.about_utils.HelpUtils;
import com.doubleq.xm6leefunz.about_utils.NotificationUtil;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmHomeHelper;
import com.doubleq.xm6leefunz.about_custom.about_cus_dialog.DialogExitUtils;
import com.doubleq.xm6leefunz.about_utils.windowStatusBar;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboutsystem.ScreenUtils;
import com.projects.zll.utilslibrarybyzll.aboutsystem.WindowBugDeal;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
import com.rance.chatui.util.Constants;

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
        AppManager.getAppManager().addActivity(this);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        simpleName = getClass().getSimpleName();
//        ScreenUtils.setWindowStatusBarColor(AppManager.getAppManager().currentActivity(),R.color.red);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            //添加变色标志
//           getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
////导航栏颜色
//           getWindow().setNavigationBarColor(getResources().getColor(com.projects.zll.utilslibrarybyzll.R.color.white));
//          getWindow().setStatusBarColor(getResources().getColor(com.projects.zll.utilslibrarybyzll.R.color.app_theme));
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            Window window = getWindow();
//
////设置修改状态栏
////            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//
////设置状态栏的颜色，和你的app主题或者标题栏颜色设置一致就ok了
//            window.setStatusBarColor(getResources().getColor(R.color.app_theme));
            WindowBugDeal.SetTop(AppManager.getAppManager().currentActivity());
//            windowStatusBar.setStatusColor(this, getResources().getColor(R.color.app_theme), 0);
            //            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            显示 内屏返回键
//            if (!simpleName.equals("MainActivity")) {
//                WindowBugDeal.checkDeviceHasNavigationBar(AppManager.getAppManager().currentActivity());
//            } else
//                WindowBugDeal.SetTop(AppManager.getAppManager().currentActivity());
        }

        initBeforeContentView();

//        if (getLayoutView()!=0)
//        {
//            LayoutInflater  mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View mContentView = mInflater.inflate(getLayoutView(), null);
//            mContentView.setBackgroundColor(getResources().getColor(android.R.color.white));
//        }
        setContentView(getLayoutView());
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

    private void dealObs(String data) {

        //        判断返回成功的  字段
        String isSucess = HelpUtils.HttpIsSucess(data.toString());
        if (isSucess.equals(AppAllKey.CODE_OK)) {

            String only = HelpUtils.backOnly(data.toString());
            if (only.equals("1"))
            {
                sendWebHaveDialog(SplitWeb.bindUid(),"断线重连中...","重连成功");
                return;
            }
//            判断返回的方法名
            String s = HelpUtils.backMethod(data.toString());
//            父类全局处理
            switch (s)
            {
//                接收消息
                case "privateReceive":
                    initReceive(data.toString());
                    break;
                case "privateSend":
                    DataJieShou dataJieShou = JSON.parseObject(data.toString(), DataJieShou.class);
                    DataJieShou.RecordBean record = dataJieShou.getRecord();
                    realmHelper.updateMsg(record.getFriendsId()+SplitWeb.getUserId(),record.getMessage(),record.getRequestTime());//更新首页聊天界面数据（消息和时间）
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
            sendWeb(SplitWeb.coroutineUid());
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
            mHandler.sendEmptyMessageDelayed(LOAD_SUCCESS, 500);
        }else if (isSendDialog)
        {
            mHandler.sendEmptyMessageDelayed(LOAD_FAILED, 500);
        }
    }
    public void errorResult(String s) {
    }

    public void initStateBar() {
    }

    public RealmHomeHelper getRealm() {
        return realmHelper;
    }

    RealmHomeHelper realmHelper;
    boolean isSendDialog=false;
    protected void sendWeb(String text) {
        isSendDialog=false;
        MyApplication.getmConnectManager().sendText(text);
    }
    public static  void send(String text) {
        MyApplication.getmConnectManager().sendText(text);
    }
    protected void sendWebHaveDialog(String text,String loadText,String loadSuccessText) {
        isSendDialog=true;
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
        MyApplication.getmConnectManager().sendText(text);
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
//        MyApplication.getmConnectManager().sendText(text);
    }
    private void saveForThesePeopleWhoDoNotCallCloseAndUseInterceptBackMethod(boolean intercept_back_event) {
        if (intercept_back_event) {
            mHandler.sendEmptyMessageDelayed(LOAD_FAILED, closeTime);
        }
    }


    protected void initBeforeContentView() {
    }

    protected void initBaseView() {
    }
    protected int getLayoutView() {
        return 0;
    }

    protected boolean isGones() {
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
        AppManager.getAppManager().finishActivity(this);
    }
    //订阅方法，接收到服务器返回事件处理
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent){
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
                        bitmap = Glide.with(MyApplication.getAppContext())
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
        if (!SplitWeb.IS_CHAT.equals("1"))
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
    }

    private void isGone() {
        View mtv;
        mtv = (View) AppManager.getAppManager().currentActivity().findViewById(R.id.include_top_margin10);
        // 设置状态栏高度
        int statusBarHeight = WindowBugDeal.getStatusBarHeight(this);
        //这里我用RelativeLayout布局为列，其他布局设置方法一样，只需改变布局名就行
        LinearLayout.LayoutParams layout=(LinearLayout.LayoutParams)mtv.getLayoutParams();
        //获得button控件的位置属性，需要注意的是，可以将button换成想变化位置的其它控件
//        layout.setMargins(0,-statusBarHeight,0,0);
        layout.height=statusBarHeight;
        //设置button的新位置属性,left，top，right，bottom
        mtv.setLayoutParams(layout);

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

    protected boolean isSupportSwipeBack() {
        return true;
    }
    protected boolean isTopBack() {
        return true;
    }

    //接收到消息，传递给子类
    public void receiveResultMsg(String responseText) {

    }

}