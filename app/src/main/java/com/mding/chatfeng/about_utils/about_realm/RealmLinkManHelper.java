//package com.mding.chatfeng.about_utils.about_realm;
//
//import android.content.Context;
//import android.util.Log;
//
//import com.mding.chatfeng.about_base.web_base.SplitWeb;
//import com.mding.chatfeng.about_utils.about_realm.realm_data.CusDataFriendRealm;
//import com.mding.chatfeng.about_utils.about_realm.realm_data.CusDataRealmMsg;
//import com.mding.chatfeng.about_utils.about_realm.realm_data.CusRealmChatMsg;
//
//import java.util.List;
//
//import io.realm.Realm;
//import io.realm.RealmList;
//import io.realm.RealmResults;
//
//
//public class RealmLinkManHelper {
//    public static final String DB_NAME = "chat.realm";
//    public static final String USER_ID = "userId";
//
////  小强  15960525629
////   123456
//    private Realm mRealm;
//
//
//    public RealmLinkManHelper(Context context) {
//
//        mRealm = Realm.getDefaultInstance();
//    }
//    /**
//     * add （增）
//     */
//    public void addFriend(final CusDataFriendRealm cusDataFriendRealm) {
//        mRealm.beginTransaction();
//        mRealm.copyToRealm(cusDataFriendRealm);
//        mRealm.commitTransaction();
////        String userId = cusDataFriendRealm.getUserId();
////        if (isHaveExist(userId)) {
////            mRealm.beginTransaction();
////            mRealm.copyToRealm(cusDataFriendRealm);
////            mRealm.commitTransaction();
////        }else {
////            updateFriend(userId,cusDataFriendRealm);
////        }
//    }
//    /**
//     * update （改） 消息
//     */
//    public void updateFriend(String friendId,final CusDataFriendRealm cusDataFriendRealm) {
//        CusDataFriendRealm cusDataFriendRealm1 = mRealm.where(CusDataFriendRealm.class).equalTo(USER_ID, friendId).findFirst();
//        if (cusDataFriendRealm1!=null) {
//            mRealm.beginTransaction();
//            cusDataFriendRealm1.setHeadImg(cusDataFriendRealm.getHeadImg());
//            cusDataFriendRealm1.setChart(cusDataFriendRealm.getChart());
//            cusDataFriendRealm1.setGroupId(cusDataFriendRealm.getGroupId());
//            cusDataFriendRealm1.setHeadImg(cusDataFriendRealm.getHeadImg());
//            cusDataFriendRealm1.setHeadImg(cusDataFriendRealm.getHeadImg());
//            cusDataFriendRealm1.setHeadImg(cusDataFriendRealm.getHeadImg());
//            mRealm.commitTransaction();
//        }
//    }
//    public boolean isHaveExist(String id){
//        CusDataFriendRealm cusDataFriendRealm=mRealm.where(CusDataFriendRealm.class).equalTo(USER_ID,id).findFirst();
//        if (cusDataFriendRealm==null){
//            return false;
//        }else {
//            return  true;
//        }
//    }
//    public void addRealmChat(String friendId,String msg,String  messageType, int  userMessageType, String  created) {
////        String  sendId;//    发送者ID
////        String  receiveId;//    接收者ID
////        String  messageType;//    消息类型 1文字 2图 3表情 4文件
////        String  userMessageType; //1右 发送，2左 接收
////        String  message; //    消息内容
////        String  created; //    发送时间
//        mRealm.beginTransaction();
//        CusDataRealmMsg cusDataRealmMsg = mRealm.where(CusDataRealmMsg.class).equalTo(USER_ID, friendId).findFirst();
//        RealmList<CusRealmChatMsg> chatMsgs = cusDataRealmMsg.getChatMsgs();
//        CusRealmChatMsg cusRealmChatMsg = mRealm.createObject(CusRealmChatMsg.class);
////        CusRealmChatMsg cusRealmChatMsg = new CusRealmChatMsg();
//        cusRealmChatMsg.setMessage(msg);
//        cusRealmChatMsg.setSendId(SplitWeb.getSplitWeb().getUserId());
//        cusRealmChatMsg.setReceiveId(friendId);
//        cusRealmChatMsg.setMessageType(messageType);
//        cusRealmChatMsg.setUserMessageType(userMessageType);
//        cusRealmChatMsg.setCreated(created);
//        cusDataRealmMsg.getChatMsgs().add(cusRealmChatMsg);
////        chatMsgs.add(cusRealmChatMsg);
//        cusDataRealmMsg.setChatMsgs(chatMsgs);
////        addRealmMsg(cusDataRealmMsg);
//        mRealm.copyToRealm(cusDataRealmMsg);
//        mRealm.commitTransaction();
//    }
//
//    /**
//     * delete （删）
//     */
//    public void deleteRealmMsg(String friendId) {
//        CusDataRealmMsg dog = mRealm.where(CusDataRealmMsg.class).equalTo(USER_ID, friendId).findFirst();
//        if (dog!=null) {
//            mRealm.beginTransaction();
//            dog.deleteFromRealm();
//            mRealm.commitTransaction();
//        }
//    }
//    public  void deleteMsgBytaskId(String taskId) {
//        RealmResults<CusDataRealmMsg> dogs = mRealm.where(CusDataRealmMsg.class).equalTo(USER_ID, taskId).findAll();
////        CusDataRealmMsg dog = mRealm.where(CusDataRealmMsg.class).equalTo("taskId", taskId).findFirst();
//        if (dogs.size()>0)
//        {
//            mRealm.beginTransaction();
//            for (int i =0;i<dogs.size();i++)
//            {
////                dogs.deleteFromRealm(i);
//                CusDataRealmMsg dog = dogs.get(i);
//                dog.deleteFromRealm();
//            }
//            mRealm.commitTransaction();
//        }else
//        {
//            return;
//        }
//    }
//    //
//    public void deleteAll() {
//        final RealmResults<CusDataFriendRealm> dogs=  mRealm.where(CusDataFriendRealm.class).findAll();
//        if (dogs!=null) {
//            mRealm.beginTransaction();
//            dogs.deleteAllFromRealm();
//            mRealm.commitTransaction();
//        }
//    }
//
//    /**
//     * update （改） 消息和时间
//     */
//    public void updateMsg(String friendId, String msg, String time) {
//        CusDataFriendRealm realmMsg = mRealm.where(CusDataFriendRealm.class).equalTo(USER_ID, friendId).findFirst();
//        if (realmMsg!=null) {
//            mRealm.beginTransaction();
////            realmMsg.setMsg(msg);
////            realmMsg.setTime(time);
//            mRealm.commitTransaction();
//        }
//    }
//    /**
//     * update （改） 消息
//     */
//    public void updateMsg(String friendId, String msg) {
//        CusDataFriendRealm realmMsg = mRealm.where(CusDataFriendRealm.class).equalTo(USER_ID, friendId).findFirst();
//        if (realmMsg!=null) {
//            mRealm.beginTransaction();
////            realmMsg.setMsg(msg);
//            mRealm.commitTransaction();
//        }
//    }
//
//    /**
//     * query （查询所有）
//     */
//    public List<CusDataFriendRealm> queryAllRealmMsg() {
//        RealmResults<CusDataFriendRealm> realmMsgs = mRealm.where(CusDataFriendRealm.class).findAll();
//        /**
//         * 对查询结果，按Id进行排序，只能对查询结果进行排序
//         */
//        if (realmMsgs!=null) {
//            //增序排列
//            realmMsgs = realmMsgs.sort(USER_ID);
//        }
////        //降序排列
////        dogs=dogs.sort("id", Sort.DESCENDING);
//        return realmMsgs;
//    }
//
//    /**
//     * 根据id查询好友信息
//     * @return
//     */
//    public CusDataFriendRealm queryFriendRealmById(String friendId) {
//        CusDataFriendRealm friendRealm = mRealm.where(CusDataFriendRealm.class).equalTo(USER_ID,friendId).findFirst();
//            return friendRealm;
//    }
//
//    /**
//     * query （根据Id（主键）查）
//     */
//    public CusDataFriendRealm queryMsgById(String id) {
//        CusDataFriendRealm dog = mRealm.where(CusDataFriendRealm.class).equalTo("id", id).findFirst();
//        if (dog!=null)
//            return dog;
//        else
//            return null;
//    }
//    /**
//     * query （根据qrcode（主键）查）
//     */
//    public CusDataFriendRealm queryResultByQrcode(String qrcode) {
//
//        CusDataFriendRealm dog = mRealm.where(CusDataFriendRealm.class).equalTo("qrcode", qrcode).findFirst();
//        if (dog!=null)
//        {
//            return dog;
//        }
//        return null;
//    }
//    /**
//     * query （根据qrcode（主键）查）
//     */
//    public RealmResults<CusDataFriendRealm> queryResultBytaskId(String taskId) {
//        RealmResults<CusDataFriendRealm> dogs = mRealm.where(CusDataFriendRealm.class).equalTo("taskId", taskId).findAll();
////        CusDataFriendRealm dog = mRealm.where(CusDataFriendRealm.class).equalTo("taskId", taskId).findFirst();
//        if (dogs!=null)
//        {
//            return dogs;
//        }
//        return null;
//    }
//    public CusDataFriendRealm queryResultByUid(String uid) {
//
//        CusDataFriendRealm dog = mRealm.where(CusDataFriendRealm.class).equalTo("nfcUid", uid).findFirst();
//        if (dog!=null)
//        {
//            return dog;
//        }
//        return null;
//    }
//
//
//    /**
//     * query （根据age查）
//     */
//    public List<CusDataFriendRealm> queryDobByAge(int age) {
//        RealmResults<CusDataFriendRealm> dogs = mRealm.where(CusDataFriendRealm.class).equalTo("age", age).findAll();
//
//        return mRealm.copyFromRealm(dogs);
//    }
//
//    public boolean isDogExist(String id){
//        CusDataFriendRealm dog=mRealm.where(CusDataFriendRealm.class).equalTo("id",id).findFirst();
//        if (dog==null){
//            return false;
//        }else {
//            return  true;
//        }
//    }
//
//    public boolean isHaveExistBynfcUid(String nfcUid){
//        CusDataFriendRealm dog=mRealm.where(CusDataFriendRealm.class).equalTo("nfcUid",nfcUid).findFirst();
//        Log.e("CusDataFriendRealm","dog="+dog);
//        if (dog==null){
//            return false;
//        }else {
//            return  true;
//        }
//    }
//
//    public Realm getRealm(){
//
//        return mRealm;
//    }
//
//    public void close(){
//        if (mRealm!=null){
//            mRealm.close();
//        }
//    }
//}
