package com.zll.websocket;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;
import de.tavendo.autobahn.WebSocketOptions;

public class MyWebSocketService extends Service {

    private static final String TAG = MyWebSocketService.class.getSimpleName();

    public static final String WEBSOCKET_ACTION = "WEBSOCKET_ACTION";

    private BroadcastReceiver connectionReceiver;
    private static boolean isClosed = true;
    private static WebSocketConnection webSocketConnection;
    private static WebSocketOptions options = new WebSocketOptions();
    private static boolean isExitApp = false;
    private static String websocketHost = "ws://192.168.4.133:9093";
//    private static String websocketHost = "ws://121.40.165.18:8800";
//    private static String websocketHost = "ws://121.40.165.18:8088";
    //websocket服务端的url,,,ws是协议,和http一样, 我使用的是WebSocket 在线测试的URL，可以百度到。


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (connectionReceiver == null) {
            connectionReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                    if (networkInfo == null || !networkInfo.isAvailable()) {
                        Toast.makeText(getApplicationContext(), "网络已断开，请重新连接", Toast.LENGTH_SHORT).show();
                    } else {
                        if (webSocketConnection != null) {
                            webSocketConnection.disconnect();
                        }
                        if (isClosed) {
                            webSocketConnect();
                        }
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(connectionReceiver, intentFilter);
        }
        return super.onStartCommand(intent, flags, startId);
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void closeWebsocket(boolean exitApp) {
        isExitApp = exitApp;
        if (webSocketConnection != null && webSocketConnection.isConnected()) {
            webSocketConnection.disconnect();
            webSocketConnection = null;
        }
    }
    static   OnMsgListener   onMsgListener;
    public  interface  OnMsgListener{
        void onMsg(String msg);
        void onSuc(String msg);
//        void onSuc(String msg);
    }
    public void SetOnMsgListener(OnMsgListener   onMsgListener){
        webSocketConnect();
        this.onMsgListener=onMsgListener;
    }


    public static void webSocketConnect(){
        webSocketConnection = new WebSocketConnection();
        try {
            webSocketConnection.connect(websocketHost,new WebSocketHandler(){
                //websocket启动时候的回调
                @Override
                public void onOpen() {
                    Log.d(TAG,"open");
                    onMsgListener.onSuc("open");
                    isClosed = false;
                }
                //websocket接收到消息后的回调
                @Override
                public void onTextMessage(String payload) {
                    onMsgListener.onMsg(payload);
                    Log.d(TAG, "payload = " + payload);
                }
                //websocket关闭时候的回调
                @Override
                public void onClose(int code, String reason) {
                    isClosed = true;
                    Log.d(TAG, "code = " + code + " reason = " + reason);
                }
            } , options);
        } catch (WebSocketException e) {
            e.printStackTrace();
        }
    }

    public static void sendMsg(String s) {
        Log.d(TAG, "sendMsg = " + s);
        if (!TextUtils.isEmpty(s))
            if (webSocketConnection != null) {
                webSocketConnection.sendTextMessage(s);
            }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (connectionReceiver != null) {
            unregisterReceiver(connectionReceiver);
        }
    }
}
