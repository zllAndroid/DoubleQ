package com.doubleq.xm6leefunz.main_code.mains;

import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.BaseActivity;
import com.doubleq.xm6leefunz.about_base.MyApplication;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

import butterknife.BindView;

public class TestActivity extends AppCompatActivity {


    @BindView(R.id.test_tv)
    TextView testTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            添加变色标志
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//            状态栏颜色
            getWindow().setStatusBarColor(getResources().getColor(R.color.app_theme));
//            导航栏颜色
            getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        }
        setContentView( R.layout.activity_test);

        initBaseView();
    }



    protected void initBaseView() {
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
//                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
//                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
//                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        PowerManager pm = (PowerManager) TestActivity.this.getSystemService(Context.POWER_SERVICE);
        boolean screenOn = pm.isScreenOn();
        if (!screenOn) {
            // 获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
            @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "bright");
            wl.acquire(10000); // 点亮屏幕
            wl.release(); // 释放
            String msg = getIntent().getStringExtra("message");
            testTv.setText("收到的消息：" + msg);
        }


//        testBtn = findViewById(R.id.test_btn);
//        testTv = findViewById(R.id.test_tv);
//        testBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new Thread() {
//                    @Override
//                    public void run() {
//                        try {
//                            sleep(5000);
//                            handler.sendEmptyMessage(100);
//                            testBtn.setText("已发送");
//                            testTv.setText("收到消息:" + msg);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }.start();
//            }
//        });
//        initReceiver();
    }

//    public  BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            dealMsgBroReceiver(intent);
//        }
//
//        private void dealMsgBroReceiver(Intent intent) {
//
//        }
//    };

//    @SuppressLint("HandlerLeak")
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.what == 100) {
//                int x = 0;
//                x = x + 1;
//                sendData(" msg + " + x);
//            }
//        }
//    };
//
//    private void sendData(String data) {
//        Intent intent = new Intent("action_send");
//        intent.putExtra("data", data);
//        sendBroadcast(intent);
//    }
//
//
//    IntentFilter intentFilter;
//
//    private void initReceiver() {
//        if (intentFilter == null) {
//            intentFilter = new IntentFilter();
//            intentFilter.addAction("action.test");
//            registerReceiver(broadcastReceiver, intentFilter);
//        }
//    }
//
//    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            dealBro(intent);
//        }
//
//        private void dealBro(Intent intent) {
//            String action = intent.getAction();
//            if (action.equals("action.test")) {
//                ToastUtil.show("ok");
//            }
//        }
//
//    };
//
////    /**
////     * 唤醒手机屏幕
////     */
////    public void wakeUp() {
////        // 获取电源管理器对象
////        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
////        boolean screenOn = pm.isScreenOn();
////        if (!screenOn) {
////            // 获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
////            @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "bright");
////            wl.acquire(10000); // 点亮屏幕
////            wl.release(); // 释放
////        }
////        // 屏幕解锁
//////        KeyguardManager keyguardManager = (KeyguardManager)this.getSystemService(KEYGUARD_SERVICE);
//////        KeyguardManager.KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("unLock");
////        // 屏幕锁定
//////        keyguardLock.reenableKeyguard();
//////        keyguardLock.disableKeyguard(); // 解锁
////    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unregisterReceiver(broadcastReceiver);
//    }

}
