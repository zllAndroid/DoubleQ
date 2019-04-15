package com.mding.chatfeng.about_application;

import com.zll.websocket.WebSocketServiceConnectManager;

/**
 * 项目：DoubleQ
 * 文件描述：
 * 作者：zll
 * 创建时间：2019/4/12 0012
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public interface AppView {
    void sendData(String request);
    void backData(String request);
    void getmConnectManager(WebSocketServiceConnectManager mConnectManager);
}
