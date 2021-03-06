package com.mding.chatfeng.about_utils.about_realm.new_home;

import android.content.Context;

import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_chat.cus_data_group.CusGroupChatData;
import com.rance.chatui.util.Constants;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * 聊天记录相关的数据库查询类
 */

public class RealmChatHelper {
    public static final String FILE_NAME = "totalId";
    public static final String MSG_ID = "messageStoId";

    //  小强  15960525629
//   123456
    private Realm mRealm;

    //realm.beginTransaction();
//final Dog managedDog = realm.copyToRealm(dog); // Persist unmanaged objects
//Person person = realm.createObject(Person.class); // Create managed objects directly
//person.getDogs().add(managedDog);
//realm.commitTransaction();
    public RealmChatHelper(Context context) {

        mRealm = Realm.getDefaultInstance();
    }
    /**
     * add （增）
     */
    public void addRealmChat(final CusChatData realmChat) {
        if (realmChat.getMessageStoId()!=null) {
            CusChatData cusChatData = mRealm.where(CusChatData.class).equalTo(MSG_ID, realmChat.getMessageStoId()).findFirst();
            if (cusChatData == null) {
                addChat(realmChat);
            }else {
//                upDataState(realmChat);
            }
        }else
        {
            addChat(realmChat);
        }
    }
    public boolean isMessage(String msgId) {
        if (msgId!=null) {
            CusChatData dog = mRealm.where(CusChatData.class).equalTo(MSG_ID, msgId).findFirst();
            if (dog!=null) {
                return false;
            }
        }
        return true;
    }
    /**
     * add （增）
     */
    private void addChat(final CusChatData realmChat) {
        mRealm.beginTransaction();
        mRealm.copyToRealm(realmChat);
        mRealm.commitTransaction();
    }
    private void upDataState(final CusChatData realmChat) {
        mRealm.beginTransaction();
        realmChat.setMsgState(Constants.CHAT_ITEM_SEND_SUCCESS);
//                mRealm.copyToRealm(realmChat);
        mRealm.insertOrUpdate(realmChat);
        mRealm.commitTransaction();
    }

    /**
     * delete （删）
     */
    public void deleteRealmMsg(String friendId) {
        CusChatData dog = mRealm.where(CusChatData.class).equalTo(FILE_NAME, friendId+SplitWeb.getSplitWeb().getUserId()).findFirst();
        if (dog!=null) {
            mRealm.beginTransaction();
            dog.deleteFromRealm();
            mRealm.commitTransaction();
        }
    }
    public  void deleteMsgByFriendId(String friendId) {
        RealmResults<CusChatData> dogs = mRealm.where(CusChatData.class).equalTo(FILE_NAME, friendId+SplitWeb.getSplitWeb().getUserId()).findAll();
//        CusDataRealmMsg dog = mRealm.where(CusDataRealmMsg.class).equalTo("taskId", taskId).findFirst();
        if (dogs.size()>0)
        {
            mRealm.beginTransaction();
            for (int i =0;i<dogs.size();i++)
            {
//                dogs.deleteFromRealm(i);
                CusChatData dog = dogs.get(i);
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
        final RealmResults<CusChatData> dogs=  mRealm.where(CusChatData.class).findAll();
        if (dogs!=null) {
            mRealm.beginTransaction();
            dogs.deleteAllFromRealm();
            mRealm.commitTransaction();
        }
    }


    /**
     * query （查询所有）
     */
    public List<CusChatData> queryAllRealmChat(String id) {
        RealmResults<CusChatData> realmMsgs = mRealm.where(CusChatData.class).equalTo(FILE_NAME, id+SplitWeb.getSplitWeb().getUserId()).findAll();
        /**
         * 对查询结果，按Id进行排序，只能对查询结果进行排序
         */
        if (realmMsgs!=null) {
            //增序排列
//            realmMsgs = realmMsgs.sort(FILE_NAME);
            realmMsgs = realmMsgs.sort("timeSort", Sort.ASCENDING);
        }
//        //降序排列
//        dogs=dogs.sort("id", Sort.DESCENDING);
        return realmMsgs;
    }
    public boolean deletePosition(String id,String msgId) {
        CusChatData realmMsgs = mRealm.where(CusChatData.class).equalTo(FILE_NAME, id + SplitWeb.getSplitWeb().getUserId()).equalTo("messageStoId", msgId).findFirst();
        if (realmMsgs!=null) {
            mRealm.beginTransaction();
            realmMsgs.deleteFromRealm();
            mRealm.commitTransaction();
            return true;
        }
//        //降序排列
//        dogs=dogs.sort("id", Sort.DESCENDING);
        return false;
    }
    public boolean deletePosition(String id,int position) {
        RealmResults<CusChatData> realmMsgs = mRealm.where(CusChatData.class).equalTo(FILE_NAME, id+SplitWeb.getSplitWeb().getUserId()).findAll();
        if (realmMsgs!=null) {
            mRealm.beginTransaction();
            realmMsgs.deleteFromRealm(position);
            mRealm.commitTransaction();
            return true;
        }
//        //降序排列
//        dogs=dogs.sort("id", Sort.DESCENDING);
        return false;
    }
    public void delChatMsgAll(String id) {
        RealmResults<CusChatData> realmMsgs = mRealm.where(CusChatData.class).equalTo(FILE_NAME, id+SplitWeb.getSplitWeb().getUserId()).findAll();
        /**
         * 对查询结果，按Id进行排序，只能对查询结果进行排序
         */
        if (realmMsgs!=null) {
            //增序排列
//            realmMsgs = realmMsgs.sort(FILE_NAME);
            mRealm.beginTransaction();
            realmMsgs.deleteAllFromRealm();
            mRealm.commitTransaction();
        }
//        //降序排列
//        dogs=dogs.sort("id", Sort.DESCENDING);
    }


    public boolean isHaveExist(String id){
        CusChatData dog=mRealm.where(CusChatData.class).equalTo(FILE_NAME,id+SplitWeb.getSplitWeb().getUserId()).findFirst();
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
