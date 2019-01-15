package com.doubleq.xm6leefunz.main_code.about_notification;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;

import com.doubleq.model.CusJumpChatData;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.MyApplication;
import com.doubleq.xm6leefunz.about_chat.ChatActivity;
import com.doubleq.xm6leefunz.about_chat.chat_group.ChatGroupActivity;
import com.doubleq.xm6leefunz.about_chat.cus_data_group.CusJumpGroupChatData;
import com.doubleq.xm6leefunz.about_utils.IntentUtils;
import com.doubleq.xm6leefunz.about_utils.SysRunUtils;
import com.doubleq.xm6leefunz.main_code.mains.MainActivity;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.NoticeActivity;
import com.rance.chatui.util.Constants;

import java.io.Serializable;

public class NotificationClickReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent2) {
        String string = intent2.getStringExtra(AppConfig.TYPE_KEY);
        switch (string)
        {
            case AppConfig.TYPE_CHAT:
                intent2.setClass(context, MainActivity.class);
                context.startActivity(intent2);


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
                intent2.setClass(context, MainActivity.class);
                context.startActivity(intent2);
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
                intent2.setClass(context, NoticeActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent2);
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
