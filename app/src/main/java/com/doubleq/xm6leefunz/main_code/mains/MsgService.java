package com.doubleq.xm6leefunz.main_code.mains;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.RealmHomeHelper;

import javax.annotation.Nullable;

public class MsgService extends Service {
    interface  OnServiceBackMethod{
      void  onBackMethod(String method,Intent intent);
    }
    OnServiceBackMethod onServiceBackMethod;
    public  void  setOnServiceBackMethod(Context context,OnServiceBackMethod onServiceBackMethod)
    {
        this.onServiceBackMethod=onServiceBackMethod;
        Intent intent_service = new Intent(context, MsgService.class);
        context.startService(intent_service);

    }

    Context context;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
//    public  MsgService(Context context,BroadcastReceiver mRefreshBroadcastReceiver)
//    {
//        this.mRefreshBroadcastReceiver=mRefreshBroadcastReceiver;
//        this.context=context;
//    }
    public  MsgService()
    {
    }
    public  MsgService(OnServiceBackMethod onServiceBackMethod)
    {
        this.onServiceBackMethod=onServiceBackMethod;
    }
    IntentFilter intentFilter;
    private MyReceiver receiver;
    //服务创建的时候注册广播接收者
    @Override
    public void onCreate() {
        super.onCreate();
        if (intentFilter==null) {
            receiver = new MyReceiver();
            intentFilter = new IntentFilter();
            intentFilter.addAction("action.refreshMsgFragment");
            intentFilter.addAction("zll.refreshMsgFragment");
            intentFilter.addAction("zero.refreshMsgFragment");
            intentFilter.addAction("del.refreshMsgFragment");
            registerReceiver(receiver, intentFilter);
        }
    }

    //内部类实现广播接收者
    private class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
//            if (realmHelper == null) {
//                realmHelper = new RealmHomeHelper(getActivity());
//            }
            switch (action) {
                case "action.refreshMsgFragment":
                    onServiceBackMethod.onBackMethod("action.refreshMsgFragment", intent);
//                initRefresh(intent);
                    break;
                case "zero.refreshMsgFragment":
                    onServiceBackMethod.onBackMethod("zero.refreshMsgFragment", intent);
//                initZeroNum(intent);
//                initRefresh(intent);
                    break;
                case "zll.refreshMsgFragment":
                    onServiceBackMethod.onBackMethod("zll.refreshMsgFragment", intent);
//                refreshMsg(intent);
                    break;
                case "del.refreshMsgFragment":
                    onServiceBackMethod.onBackMethod("del.refreshMsgFragment", intent);
//                initDel(intent);
                    break;
            }
        }
    }
//    public  BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
////            if (realmHelper == null) {
////                realmHelper = new RealmHomeHelper(getActivity());
////            }
//            if (action.equals("action.refreshMsgFragment"))
//            {
//                onServiceBackMethod.onBackMethod("action.refreshMsgFragment",intent);
////                initRefresh(intent);
//            }
//            else if (action.equals("zero.refreshMsgFragment"))
//            {
//                onServiceBackMethod.onBackMethod("zero.refreshMsgFragment",intent);
////                initZeroNum(intent);
////                initRefresh(intent);
//            }
//            else  if (action.equals("zll.refreshMsgFragment"))
//            {
//                onServiceBackMethod.onBackMethod("zll.refreshMsgFragment",intent);
////                refreshMsg(intent);
//            }
//            else  if (action.equals("del.refreshMsgFragment"))
//            {
//                onServiceBackMethod.onBackMethod("del.refreshMsgFragment",intent);
////                initDel(intent);
//            }
////            if (mRecyclerView!=null)
////                mRecyclerView.smoothScrollToPosition(0);
////            sendBroadcast();
//        }
//    };
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (intentFilter!=null)
        unregisterReceiver(receiver );
    }
}
