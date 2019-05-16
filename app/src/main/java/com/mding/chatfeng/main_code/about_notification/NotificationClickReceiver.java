package com.mding.chatfeng.main_code.about_notification;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

import com.mding.chatfeng.about_application.BaseApplication;
import com.mding.chatfeng.main_code.mains.MainActivity;
import com.mding.chatfeng.main_code.ui.about_contacts.NoticeActivity;
import com.mding.chatfeng.about_base.AppConfig;

public class NotificationClickReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent2) {
        String string = intent2.getStringExtra(AppConfig.TYPE_KEY);
        switch (string)
        {
            case AppConfig.TYPE_CHAT:

//                intent2.setClass(context, MainActivity.class);
//                context.startActivity(intent2);
                if(IsAppProcessExist.isProcessExist(context,android.os.Process.myPid()))
                {

                    intent2.setClass(context, MainActivity.class);
                    intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent2);
                }else
                {
                    Intent launchIntent = context.getPackageManager().
                            getLaunchIntentForPackage("com.mding.chatfeng");
                    launchIntent.setFlags(
                            Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                    context.startActivity(launchIntent);
                }

                //     CusJumpChatData cusJumpChatData = new CusJumpChatData();
//                            cusJumpChatData.setFriendHeader(item.getHeadImg());
//                            cusJumpChatData.setFriendId(item.getFriendId());
//                            cusJumpChatData.setFriendName(item.getNickName());
//                            IntentUtils.JumpToHaveObj(ChatActivity.class, Constants.KEY_FRIEND_HEADER, cusJumpChatData);
//                CusJumpChatData cusJumpChatData = (CusJumpChatData)intent2.getSerializableExtra(AppConfig.TYPE_KEY_FRIEND);
////                if (SysRunUtils.isAppOnForeground(MyApplication.getAppContext())) {
//                    intent2.setClass(context, ChatActivity.class);
////                    intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable(Constants.KEY_FRIEND_HEADER, cusJumpChatData);
//                    intent2.putExtras(bundle);
//                    context.startActivity(intent2);
//                Intent intent3 = new Intent();
//                intent3.putExtra("id",cusJumpChatData.getFriendId());
//                intent3.setAction("zllrefreshMsg");
//                context.sendBroadcast(intent3);
//                wakeUpAndUnlock(context);

//                }
                break;
//                群消息
            case AppConfig.TYPE_CHAT_QUN:
                if(IsAppProcessExist.isProcessExist(context,android.os.Process.myPid()))
                {
                    intent2.setClass(context, MainActivity.class);
                    intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent2);
                }else
                {
                    Intent launchIntent = context.getPackageManager().
                            getLaunchIntentForPackage("com.mding.chatfeng");
                    launchIntent.setFlags(
                            Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                    context.startActivity(launchIntent);
                }
                //     CusJumpChatData cusJumpChatData = new CusJumpChatData();
//                            cusJumpChatData.setFriendHeader(item.getHeadImg());
//                            cusJumpChatData.setFriendId(item.getFriendId());
//                            cusJumpChatData.setFriendName(item.getNickName());
//                            IntentUtils.JumpToHaveObj(ChatActivity.class, Constants.KEY_FRIEND_HEADER, cusJumpChatData);
//                CusJumpChatData chatData = (CusJumpChatData)intent2.getSerializableExtra(AppConfig.TYPE_KEY_FRIEND);
//                CusJumpGroupChatData cusJumpGroupChatData = new CusJumpGroupChatData();
//                cusJumpGroupChatData.setGroupId(chatData.getFriendId());
//                cusJumpGroupChatData.setGroupName(chatData.getFriendName());
//
//                    intent2.setClass(context, ChatGroupActivity.class);
//                    Bundle bundle3 = new Bundle();
//                    bundle3.putSerializable(Constants.KEY_FRIEND_HEADER, cusJumpGroupChatData);
//                    intent2.putExtras(bundle3);
//                    context.startActivity(intent2);
                break;
            case AppConfig.TYPE_NOTICE:
                //判断app进程是否存活
                if(IsAppProcessExist.isProcessExist(context,android.os.Process.myPid()))
                {
//                    Intent mainIntent = new Intent(context, MainActivity.class);
//                    //将MainAtivity的launchMode设置成SingleTask, 或者在下面flag中加上Intent.FLAG_CLEAR_TOP,
//                    //如果Task栈中有MainActivity的实例，就会把它移到栈顶，把在它之上的Activity都清理出栈，
//                    //如果Task栈不存在MainActivity实例，则在栈顶创建
//                    mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                    Intent detailIntent = new Intent(context, DetailActivity.class);
//                    detailIntent.putExtra("name", "电饭锅");
//                    detailIntent.putExtra("price", "58元");
//                    detailIntent.putExtra("detail", "这是一个好锅, 这是app进程存在，直接启动Activity的");
//
//                    Intent[] intents = {mainIntent, detailIntent};
//
//                    context.startActivities(intents);


//                    intent2.setClass(context, MainActivity.class);
//                    intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent2);
                    intent2.setClass(context, NoticeActivity.class);
                    intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent2);
                }else
                {
                    Intent launchIntent = context.getPackageManager().
                            getLaunchIntentForPackage("com.mding.chatfeng");
                    launchIntent.setFlags(
                            Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                    context.startActivity(launchIntent);
                }
//                if (SysRunUtils.isAppOnForeground(MyApplication.getAppContext())) {
//                    Intent newIntent1 = new Intent(context, NoticeActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                    context.startActivity(newIntent1);
//                }else
//                {
//                    Intent newIntent2 = new Intent(context, NoticeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(newIntent2);
//                }
                break;

        }

    }

    public static void wakeUpAndUnlock(Context context){
        //屏锁管理器
        KeyguardManager km= (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");
        //解锁
        kl.disableKeyguard();
        //获取电源管理器对象
        PowerManager pm=(PowerManager) context.getSystemService(Context.POWER_SERVICE);
        //获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK,"bright");
        //点亮屏幕
        wl.acquire();
        //释放
        wl.release();
    }

}
