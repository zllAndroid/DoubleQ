package com.doubleq.xm6leefunz.about_utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.doubleq.model.CusJumpChatData;
import com.doubleq.xm6leefunz.R;
import com.doubleq.xm6leefunz.about_base.AppConfig;
import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.main_code.about_login.AppStartActivity;
import com.doubleq.xm6leefunz.main_code.about_notification.NotificationClickReceiver;
import com.doubleq.xm6leefunz.main_code.mains.MainActivity;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

/**
 * 显示通知栏工具类
 */

public class NotificationUtil extends ContextWrapper {
    private NotificationManager manager;
    public static final String id = "channel_1";
    public static final String name = "channel_name_1";
    int i =0;
    public NotificationUtil(Context context){
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNotificationChannel(){
        NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
        getManager().createNotificationChannel(channel);
    }
    private NotificationManager getManager(){
        if (manager == null){
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        return manager;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getChannelNotification(String title, String content){
        return new Notification.Builder(getApplicationContext(), id)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.dou_logo)
                .setAutoCancel(true);
    }
    public NotificationCompat.Builder getNotification_25(String title, String content,Bitmap bitmap,String type){
        Intent intent =new Intent (this,NotificationClickReceiver.class);
        intent.putExtra(AppConfig.TYPE_KEY, type);
//        PendingIntent pendingIntent =PendingIntent.getBroadcast(this, 0, intent, 0);
        PendingIntent pendingIntent =PendingIntent.getBroadcast(this, i++, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.dou_logo)
                .setLargeIcon(bitmap)
//                .setFullScreenIntent(true);
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
    }
    public void sendNotification(String title, String content,Bitmap bitmap,String type){
        if (Build.VERSION.SDK_INT>=26){
            createNotificationChannel();
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.dou_logo);
            Intent intent =new Intent (this,NotificationClickReceiver.class);
            intent.putExtra(AppConfig.TYPE_KEY, type);
            PendingIntent pendingIntent =PendingIntent.getBroadcast(this, i++, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            Notification notification = getChannelNotification
                    (title, content)
                    .setLargeIcon(bitmap)
                    .setContentIntent(pendingIntent)
                    .setDefaults(R.raw.ring)
//                    .setDefaults(Notification.DEFAULT_SOUND)
                    .build();
            getManager().notify(1,notification);
        }else{
            Notification notification = getNotification_25(title, content,bitmap,type).build();
            getManager().notify(1,notification);
        }
    }

    public void sendNotification(CusJumpChatData cusJumpChatData, String title, String content, Bitmap bitmap, String type){
        if (Build.VERSION.SDK_INT>=26){
            createNotificationChannel();
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.dou_logo );
            Intent intent =new Intent (this,NotificationClickReceiver.class);
            intent.putExtra(AppConfig.TYPE_KEY, type);
            intent.putExtra(AppConfig.TYPE_KEY_FRIEND, cusJumpChatData);
            PendingIntent pendingIntent =PendingIntent.getBroadcast(this, i++, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            Notification.Builder channelNotification = getChannelNotification(title, content);
//            channelNotification.setLargeIcon(bitmap)
//                    .setContentIntent(pendingIntent)
//                    .setDefaults(Notification.DEFAULT_SOUND);

            Notification notification = getChannelNotification(title, content)
                    .setLargeIcon(bitmap)
                    .setContentIntent(pendingIntent)
                    .setDefaults(R.raw.ring)
                    .build();
            notification.ledOnMS = 5000; //闪光时间，毫秒


            getManager().notify(2,notification);
        }else{
            Notification notification = getNotification_25(cusJumpChatData,title, content,bitmap,type).build();
            notification.ledOnMS = 5000; //闪光时间，毫秒
            getManager().notify(2,notification);
        }
    }
    public NotificationCompat.Builder getNotification_25(CusJumpChatData cusJumpChatData,String title, String content,Bitmap bitmap,String type){
        Intent intent =new Intent (this,NotificationClickReceiver.class);
        intent.putExtra(AppConfig.TYPE_KEY, type);
        intent.putExtra(AppConfig.TYPE_KEY_FRIEND, cusJumpChatData);
        PendingIntent pendingIntent =PendingIntent.getBroadcast(this, i++, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.dou_logo)
                .setLargeIcon(bitmap)
//                .setFullScreenIntent(true);
                .setDefaults(R.raw.ring)
//                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
    }

//    class MyReceiver extends BroadcastReceiver {
//        public void onReceive(Context context, Intent intent) {
//            //处理相应逻辑
//        }
//    }
//
//    public void buildAction(Context ctx,Intent intent) {
//
//        int id = (int)System.currentTimeMillis() / 10000;
//        //这个getbroadcast方法和创建notifycation时都会用到一个id标示，一般都是统一的，但是我用不同值试过也是可以的
//
//        PendingIntent clickIntent = PendingIntent.getBroadcast(ctx, id,intent, PendingIntent.FLAG_UPDATE_CURRENT);
////        sendNotification(clickIntent,id);
//
//    }



//    /**
//     * 显示一个普通的通知
//     *
//     * @param context 上下文
//     */
//    public static void showNotification(Context context) {
//        Notification notification = new NotificationCompat.Builder(context)
//                /**设置通知左边的大图标**/
//                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
//                /**设置通知右边的小图标**/
//                .setSmallIcon(R.mipmap.ic_launcher)
//                /**通知首次出现在通知栏，带上升动画效果的**/
//                .setTicker("通知来了")
//                /**设置通知的标题**/
//                .setContentTitle("这是一个通知的标题")
//                /**设置通知的内容**/
//                .setContentText("这是一个通知的内容这是一个通知的内容")
//                /**通知产生的时间，会在通知信息里显示**/
//                .setWhen(System.currentTimeMillis())
//                /**设置该通知优先级**/
//                .setPriority(Notification.PRIORITY_DEFAULT)
//                /**设置这个标志当用户单击面板就可以让通知将自动取消**/
//                .setAutoCancel(true)
//                /**设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)**/
//                .setOngoing(false)
//                /**向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：**/
//                .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND)
//                .setContentIntent(PendingIntent.getActivity(context, 1, new Intent(context, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT))
//                .build();
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
//        /**发起通知**/
//        notificationManager.notify(0, notification);
//    }

    /**
     * 显示一个下载带进度条的通知
     *
     * @param context 上下文
     */
    public static void showNotificationProgress(Context context) {
        //进度条通知
        final NotificationCompat.Builder builderProgress = new NotificationCompat.Builder(context);
        builderProgress.setContentTitle("下载中");
        builderProgress.setSmallIcon(R.mipmap.ic_launcher);
        builderProgress.setTicker("进度条通知");
        builderProgress.setProgress(100, 0, false);
        final Notification notification = builderProgress.build();
        final NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        //发送一个通知
        notificationManager.notify(2, notification);
        /**创建一个计时器,模拟下载进度**/
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int progress = 0;

            @Override
            public void run() {
                Log.i("progress", progress + "");
                while (progress <= 100) {
                    progress++;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //更新进度条
                    builderProgress.setProgress(100, progress, false);
                    //再次通知
                    notificationManager.notify(2, builderProgress.build());
                }
                //计时器退出
                this.cancel();
                //进度条退出
                notificationManager.cancel(2);
                return;//结束方法
            }
        }, 0);
    }

    /**
     * 悬挂式，支持6.0以上系统
     *
     * @param context
     */
    public static void showFullScreen(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mIntent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
        builder.setAutoCancel(true);
        builder.setContentTitle("悬挂式通知");
        //设置点击跳转
        Intent hangIntent = new Intent();
        hangIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        hangIntent.setClass(context, MainActivity.class);
        //如果描述的PendingIntent已经存在，则在产生新的Intent之前会先取消掉当前的
        PendingIntent hangPendingIntent = PendingIntent.getActivity(context, 0, hangIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setFullScreenIntent(hangPendingIntent, true);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        notificationManager.notify(3, builder.build());
    }
    /**
     * 折叠式
     *
     * @param context
     */
    public static void shwoNotify(Context context) {
        //先设定RemoteViews
        RemoteViews view_custom = new RemoteViews(context.getPackageName(), R.layout.view_custom);
        //设置对应IMAGEVIEW的ID的资源图片
//        view_custom.setImageViewResource(R.id.custom_icon, R.mipmap.icon);
        view_custom.setTextViewText(R.id.tv_custom_title, "今日头条");
        view_custom.setTextColor(R.id.tv_custom_title, Color.BLACK);
        view_custom.setTextViewText(R.id.tv_custom_content, "金州勇士官方宣布球队已经解雇了主帅马克-杰克逊，随后宣布了最后的结果。");
        view_custom.setTextColor(R.id.tv_custom_content, Color.BLACK);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setContent(view_custom)
                .setContentIntent(PendingIntent.getActivity(context, 4, new Intent(context, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT))
                .setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示
                .setTicker("有新资讯")
                .setPriority(Notification.PRIORITY_HIGH)// 设置该通知优先级
                .setOngoing(false)//不是正在进行的   true为正在进行  效果和.flag一样
                .setSmallIcon(R.drawable.dou_logo);
        Notification notify = mBuilder.build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        notificationManager.notify(4, notify);
    }
}
