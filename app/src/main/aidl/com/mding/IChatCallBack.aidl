// IChatCallBack.aidl
package com.mding;
import com.mding.models.ChatModel;

interface IChatCallBack {

void recevieMsgStatus(in ChatModel mChatModel);
void recevieHeadStatus(in ChatModel mChatModel);
void recevieMsg(in ChatModel mChatModel);
void recevieMainList(String list);
void recevieContactsList(String list);
void initSuccess(String suceess);
}
