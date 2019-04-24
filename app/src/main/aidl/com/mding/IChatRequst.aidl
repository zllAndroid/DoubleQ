// IChatRequst.aidl
package com.mding;
import com.mding.IChatCallBack;
import com.mding.models.ChatModel;


interface IChatRequst {

void register(IChatCallBack callback);
boolean sendMsg(String mSendMsg);
void getHeadImg(String mGetHeadImg);
void onStartGetData();
void getDataBySqlite(String type);
void initData();
String getDataByUrl(String url);
}
