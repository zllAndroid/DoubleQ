// IChatCallBack.aidl
package com.mding;
import com.mding.models.ChatModel;

interface IChatCallBack {

void recevieMsgStatus(in ChatModel mChatModel);
void recevieHeadStatus(in ChatModel mChatModel);
void recevieMsg(String recevieMsg);
void recevieMainList(String list);
void recevieContactsList(String list);
void initSuccess(String suceess);
void onFail(String onFail);
}
