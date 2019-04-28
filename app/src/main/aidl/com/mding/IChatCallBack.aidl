// IChatCallBack.aidl
package com.mding;
import com.mding.models.ChatModel;
import com.mding.models.CusHomeRealmData;

interface IChatCallBack {

void recevieMsgStatus(in ChatModel mChatModel);
void recevieHeadStatus(in ChatModel mChatModel);
void recevieMsg(String type,String recevieMsg);
void recevieMainList(String list);
void recevieContactsList(String list);
void initSuccess(String suceess);
void onFail(String onFail);

void recevieHomeList(in List<CusHomeRealmData> mHomeList);
}
