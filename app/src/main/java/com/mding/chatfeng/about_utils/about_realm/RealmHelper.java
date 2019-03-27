package com.mding.chatfeng.about_utils.about_realm;

import android.content.Context;
import android.util.Log;

import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_utils.about_realm.realm_data.CusDataRealmMsg;
import com.mding.chatfeng.about_utils.about_realm.realm_data.CusRealmChatMsg;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;


public class RealmHelper {
    public static final String DB_NAME = "chat.realm";
    public static final String FILE_NAME = "friendId";

//  小强  15960525629
//   123456
    private Realm mRealm;


    public RealmHelper(Context context) {

        mRealm = Realm.getDefaultInstance();
    }
    /**
     * add （增）
     */
    public void addRealmMsg(final CusDataRealmMsg realmMsg) {
        mRealm.beginTransaction();
        mRealm.copyToRealm(realmMsg);
//        mRealm.copyToRealmOrUpdate(realmMsg);
        mRealm.commitTransaction();
    }
//    public void addRealmChat(String friendId,String msg,String  messageType, int  userMessageType, String  created) {
////        String  sendId;//    发送者ID
////        String  receiveId;//    接收者ID
////        String  messageType;//    消息类型 1文字 2图 3表情 4文件
////        String  userMessageType; //1右 发送，2左 接收
////        String  message; //    消息内容
////        String  created; //    发送时间
//        mRealm.beginTransaction();
//        CusDataRealmMsg   cusDataRealmMsg = mRealm.where(CusDataRealmMsg.class).equalTo(FILE_NAME, friendId).findFirst();
////          cusDataRealmMsg= mRealm.createObject(CusDataRealmMsg.class);
////        RealmList<CusRealmChatMsg> chatMsgs = cusDataRealmMsg.getChatMsgs();
//        CusRealmChatMsg cusRealmChatMsg = mRealm.createObject(CusRealmChatMsg.class);
//
////        CusRealmChatMsg cusRealmChatMsg = new CusRealmChatMsg();
//        cusRealmChatMsg.setMessage(msg);
//        cusRealmChatMsg.setSendId(SplitWeb.USER_ID);
//        cusRealmChatMsg.setReceiveId(friendId);
//        cusRealmChatMsg.setMessageType(messageType);
//        cusRealmChatMsg.setUserMessageType(userMessageType);
//        cusRealmChatMsg.setCreated(created);
//
//        cusDataRealmMsg.getChatMsgs().add(cusRealmChatMsg);
//
////        chatMsgs.add(cusRealmChatMsg);
////        cusDataRealmMsg.setChatMsgs(chatMsgs);
////        addRealmMsg(cusDataRealmMsg);
////        mRealm.copyToRealm(cusDataRealmMsg);
//        mRealm.commitTransaction();
////
//
//    }
    public void addRealmChat(String friendId,String msg,String  messageType, int  userMessageType, String  created) {
//        String  sendId;//    发送者ID
//        String  receiveId;//    接收者ID
//        String  messageType;//    消息类型 1文字 2图 3表情 4文件
//        String  userMessageType; //1右 发送，2左 接收
//        String  message; //    消息内容
//        String  created; //    发送时间
        mRealm.beginTransaction();
        CusDataRealmMsg cusDataRealmMsg = mRealm.where(CusDataRealmMsg.class).equalTo(FILE_NAME, friendId).findFirst();
        if (cusDataRealmMsg==null)
        {
               cusDataRealmMsg= mRealm.createObject(CusDataRealmMsg.class);
            cusDataRealmMsg.setFriendId(friendId);
        }
        RealmList<CusRealmChatMsg> chatMsgs = cusDataRealmMsg.getChatMsgs();
        CusRealmChatMsg cusRealmChatMsg = mRealm.createObject(CusRealmChatMsg.class);
//        CusRealmChatMsg cusRealmChatMsg = new CusRealmChatMsg();
        cusRealmChatMsg.setMessage(msg);
        cusRealmChatMsg.setSendId(SplitWeb.getUserId());
        cusRealmChatMsg.setReceiveId(friendId);
        cusRealmChatMsg.setMessageType(messageType);
        cusRealmChatMsg.setUserMessageType(userMessageType);
        cusRealmChatMsg.setCreated(created);
//        cusDataRealmMsg.getChatMsgs().add(cusRealmChatMsg);
        chatMsgs.add(cusRealmChatMsg);
        cusDataRealmMsg.setChatMsgs(chatMsgs);
//        addRealmMsg(cusDataRealmMsg);
//        mRealm.copyToRealm(cusDataRealmMsg);
        mRealm.commitTransaction();

    }

    /**
     * delete （删）
     */
    public void deleteRealmMsg(String friendId) {
        CusDataRealmMsg dog = mRealm.where(CusDataRealmMsg.class).equalTo(FILE_NAME, friendId).findFirst();
        if (dog!=null) {
            mRealm.beginTransaction();
            dog.deleteFromRealm();
            mRealm.commitTransaction();
        }
    }
    public  void deleteMsgBytaskId(String taskId) {
        RealmResults<CusDataRealmMsg> dogs = mRealm.where(CusDataRealmMsg.class).equalTo(FILE_NAME, taskId).findAll();
//        CusDataRealmMsg dog = mRealm.where(CusDataRealmMsg.class).equalTo("taskId", taskId).findFirst();
        if (dogs.size()>0)
        {
            mRealm.beginTransaction();
            for (int i =0;i<dogs.size();i++)
            {
//                dogs.deleteFromRealm(i);
                CusDataRealmMsg dog = dogs.get(i);
                dog.deleteFromRealm();
            }
            mRealm.commitTransaction();
        }else
        {
            return;
        }
    }
    //
    public void deleteAll() {
        final RealmResults<CusDataRealmMsg> dogs=  mRealm.where(CusDataRealmMsg.class).findAll();
        if (dogs!=null) {
            mRealm.beginTransaction();
            dogs.deleteAllFromRealm();
            mRealm.commitTransaction();
        }
    }

    /**
     * update （改） 消息和时间
     */
    public void updateMsg(String friendId, String msg, String time) {
        CusDataRealmMsg realmMsg = mRealm.where(CusDataRealmMsg.class).equalTo(FILE_NAME, friendId).findFirst();
        if (realmMsg!=null) {
            mRealm.beginTransaction();
            realmMsg.setMsg(msg);
            realmMsg.setTime(time);
            mRealm.commitTransaction();
        }
    }
    /**
     * update （改） 消息
     */
    public void updateMsg(String friendId, String msg) {
        CusDataRealmMsg realmMsg = mRealm.where(CusDataRealmMsg.class).equalTo(FILE_NAME, friendId).findFirst();
        if (realmMsg!=null) {
            mRealm.beginTransaction();
            realmMsg.setMsg(msg);
            mRealm.commitTransaction();
        }
    }

    /**
     * query （查询所有）
     */
    public List<CusDataRealmMsg> queryAllRealmMsg() {
        RealmResults<CusDataRealmMsg> realmMsgs = mRealm.where(CusDataRealmMsg.class).findAll();
        /**
         * 对查询结果，按Id进行排序，只能对查询结果进行排序
         */
        if (realmMsgs!=null) {
            //增序排列
//            realmMsgs = realmMsgs.sort(FILE_NAME);
            realmMsgs = realmMsgs.sort(FILE_NAME, Sort.DESCENDING);
        }
//        //降序排列
//        dogs=dogs.sort("id", Sort.DESCENDING);
        if (realmMsgs!=null)
        return mRealm.copyFromRealm(realmMsgs);
        else
            return  null;
    }

    /**
     * 根据id查询所有聊天消息
     * @return
     */
    public List<CusRealmChatMsg> queryAllRealmChat(String friendId) {
        CusDataRealmMsg realmMsgs = mRealm.where(CusDataRealmMsg.class).equalTo(FILE_NAME,friendId).findFirst();
        /**
         * 对查询结果，按Id进行排序，只能对查询结果进行排序
         */
        RealmResults<CusRealmChatMsg> created = null;
        if (realmMsgs!=null) {
            RealmList<CusRealmChatMsg> chatMsgs = realmMsgs.getChatMsgs();
            if (chatMsgs!=null)
            {
                created = chatMsgs.sort("created");
//                created = chatMsgs.sort("created", Sort.DESCENDING);
            }
            //增序排列
//                chatMsgs = chatMsgs.sort("created");
        }
//        //降序排列
//        dogs=dogs.sort("id", Sort.DESCENDING);
        if (created!=null)
            return mRealm.copyFromRealm(created);
        else
            return  null;
    }

    /**
     * query （根据Id（主键）查）
     */
    public CusDataRealmMsg queryMsgById(String id) {
        CusDataRealmMsg dog = mRealm.where(CusDataRealmMsg.class).equalTo("id", id).findFirst();
        if (dog!=null)
            return dog;
        else
            return null;
    }
    /**
     * query （根据qrcode（主键）查）
     */
    public CusDataRealmMsg queryResultByQrcode(String qrcode) {

        CusDataRealmMsg dog = mRealm.where(CusDataRealmMsg.class).equalTo("qrcode", qrcode).findFirst();
        if (dog!=null)
        {
            return dog;
        }
        return null;
    }
    /**
     * query （根据qrcode（主键）查）
     */
    public RealmResults<CusDataRealmMsg> queryResultBytaskId(String taskId) {
        RealmResults<CusDataRealmMsg> dogs = mRealm.where(CusDataRealmMsg.class).equalTo("taskId", taskId).findAll();
//        CusDataRealmMsg dog = mRealm.where(CusDataRealmMsg.class).equalTo("taskId", taskId).findFirst();
        if (dogs!=null)
        {
            return dogs;
        }
        return null;
    }
    public CusDataRealmMsg queryResultByUid(String uid) {

        CusDataRealmMsg dog = mRealm.where(CusDataRealmMsg.class).equalTo("nfcUid", uid).findFirst();
        if (dog!=null)
        {
            return dog;
        }
        return null;
    }


    /**
     * query （根据age查）
     */
    public List<CusDataRealmMsg> queryDobByAge(int age) {
        RealmResults<CusDataRealmMsg> dogs = mRealm.where(CusDataRealmMsg.class).equalTo("age", age).findAll();

        return mRealm.copyFromRealm(dogs);
    }

    public boolean isDogExist(String id){
        CusDataRealmMsg dog=mRealm.where(CusDataRealmMsg.class).equalTo("id",id).findFirst();
        if (dog==null){
            return false;
        }else {
            return  true;
        }
    }
    public boolean isHaveExist(String id){
        CusDataRealmMsg dog=mRealm.where(CusDataRealmMsg.class).equalTo(FILE_NAME,id).findFirst();
        Log.e("CusDataRealmMsg","id="+dog);
        if (dog==null){
            return false;
        }else {
            return  true;
        }
    }
    public boolean isHaveExistBynfcUid(String nfcUid){
        CusDataRealmMsg dog=mRealm.where(CusDataRealmMsg.class).equalTo("nfcUid",nfcUid).findFirst();
        Log.e("CusDataRealmMsg","dog="+dog);
        if (dog==null){
            return false;
        }else {
            return  true;
        }
    }

    public Realm getRealm(){

        return mRealm;
    }

    public void close(){
        if (mRealm!=null){
            mRealm.close();
        }
    }
}
