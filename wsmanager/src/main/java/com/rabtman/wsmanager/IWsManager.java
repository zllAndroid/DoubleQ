package com.rabtman.wsmanager;

import okhttp3.WebSocket;
import okio.ByteString;

import okhttp3.WebSocket;

/**
 * @author rabtman
 */

interface IWsManager {

  WebSocket getWebSocket();

  void startConnect();

  void stopConnect();

  boolean isWsConnected();

  int getCurrentStatus();

  void setCurrentStatus(int currentStatus);

  boolean sendMessage(String msg);

  boolean sendMessage(ByteString byteString);

  void fristStartConnByNetChange();
}
