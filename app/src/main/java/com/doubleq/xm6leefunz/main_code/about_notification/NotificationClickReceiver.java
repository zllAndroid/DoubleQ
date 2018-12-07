package com.doubleq.xm6leefunz.main_code.about_notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.doubleq.model.CusJumpChatData;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.MyApplication;
import com.doubleq.xm6leefunz.about_chat.ChatActivity;
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
                //     CusJumpChatData cusJumpChatData = new CusJumpChatData();
//                            cusJumpChatData.setFriendHeader(item.getHeadImg());
//                            cusJumpChatData.setFriendId(item.getFriendId());
//                            cusJumpChatData.setFriendName(item.getNickName());
//                            IntentUtils.JumpToHaveObj(ChatActivity.class, Constants.KEY_FRIEND_HEADER, cusJumpChatData);
                CusJumpChatData cusJumpChatData = (CusJumpChatData)intent2.getSerializableExtra(AppConfig.TYPE_KEY_FRIEND);
//                if (SysRunUtils.isAppOnForeground(MyApplication.getAppContext())) {
                    intent2.setClass(context, ChatActivity.class);
                    intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.KEY_FRIEND_HEADER, cusJumpChatData);
                    intent2.putExtras(bundle);
                    context.startActivity(intent2);
                Intent intent3 = new Intent();
                intent3.putExtra("id",cusJumpChatData.getFriendId());
                intent3.setAction("zllrefreshMsg");
                context.sendBroadcast(intent3);
//                }
                break;
//                群消息
            case AppConfig.TYPE_CHAT_QUN:
                //     CusJumpChatData cusJumpChatData = new CusJumpChatData();
//                            cusJumpChatData.setFriendHeader(item.getHeadImg());
//                            cusJumpChatData.setFriendId(item.getFriendId());
//                            cusJumpChatData.setFriendName(item.getNickName());
//                            IntentUtils.JumpToHaveObj(ChatActivity.class, Constants.KEY_FRIEND_HEADER, cusJumpChatData);
                CusJumpChatData chatData = (CusJumpChatData)intent2.getSerializableExtra(AppConfig.TYPE_KEY_FRIEND);
//                if (SysRunUtils.isAppOnForeground(MyApplication.getAppContext())) {
                    intent2.setClass(context, ChatActivity.class);
                    intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Bundle bundle3 = new Bundle();
                    bundle3.putSerializable(Constants.KEY_FRIEND_HEADER, chatData);
                    intent2.putExtras(bundle3);
                    context.startActivity(intent2);
//                Intent intent0 = new Intent();
//                intent0.putExtra("id",chatData.getFriendId());
//                intent0.setAction("zllrefreshMsg");
//                context.sendBroadcast(intent0);
//                }
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
}
