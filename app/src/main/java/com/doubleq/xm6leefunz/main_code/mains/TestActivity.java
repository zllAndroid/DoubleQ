package com.doubleq.xm6leefunz.main_code.mains;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.doubleq.xm6leefunz.R;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;

public class TestActivity extends AppCompatActivity {

    String msg;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100){
                int x = 0;
                x = x+1;
                sendData(" msg + "+x);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        final Button button = findViewById(R.id.test_btn);
        textView = (TextView) findViewById(R.id.test_tv);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            sleep(5000);
                            handler.sendEmptyMessage(100);
                            button.setText("已发送");
                            textView.setText("收到消息:" + msg);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }.start();
            }
        });
        initReceiver();
    }

    private void sendData(String data) {
        Intent intent = new Intent("action_send");
        intent.putExtra("data", data);
        sendBroadcast(intent);
    }


    IntentFilter intentFilter;
    private void initReceiver() {
        if (intentFilter == null){
            intentFilter = new IntentFilter();
            intentFilter.addAction("action.test");
            registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            dealBro(intent);
        }

        private void dealBro(Intent intent) {
            String action = intent.getAction();
            if (action.equals("action.test"))
            {
                ToastUtil.show("ok");
            }
        }

    };

//    /**
//     * 唤醒手机屏幕
//     */
//    public void wakeUp() {
//        // 获取电源管理器对象
//        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
//        boolean screenOn = pm.isScreenOn();
//        if (!screenOn) {
//            // 获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
//            @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "bright");
//            wl.acquire(10000); // 点亮屏幕
//            wl.release(); // 释放
//        }
//        // 屏幕解锁
////        KeyguardManager keyguardManager = (KeyguardManager)this.getSystemService(KEYGUARD_SERVICE);
////        KeyguardManager.KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("unLock");
//        // 屏幕锁定
////        keyguardLock.reenableKeyguard();
////        keyguardLock.disableKeyguard(); // 解锁
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

//    Button button;
    TextView textView;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test);
//        final Window win = getWindow();
//        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED      //        锁屏状态下显示
//                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD         //                解锁
//                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON           //        保持屏幕常量
//                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);         //                打开屏幕
//
//        //下面就是根据自己的跟你需求来写，跟写一个Activity一样的
//        //拿到传过来的数据
//        String msg = getIntent().getStringExtra("msg");
//        button = findViewById(R.id.test_btn);
//        textView = (TextView) findViewById(R.id.test_tv);
//        textView.setText("收到消息:" + msg);
//    }
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        Log.d("test", "onReceive: 收到广播");
//        Log.d("test", intent.getAction());
//        //拿到传来过来数据
//        String msg = intent.getStringExtra("msg");
//        //拿到锁屏管理者
//        KeyguardManager km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
//        if (km.isKeyguardLocked()) {   //为true就是锁屏状态下
//            //启动Activity
//            Intent alarmIntent = new Intent(context, TestActivity.class);
//            //携带数据
//            alarmIntent.putExtra("msg", msg);
//            //activity需要新的任务栈
//            alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(alarmIntent);
//        }
//    }
//
//    public final static boolean isScreenOn(Context c) {
//        PowerManager powermanager;
//        powermanager = (PowerManager) c.getSystemService(Context.POWER_SERVICE);
//        return powermanager.isScreenOn();
//    }

}
