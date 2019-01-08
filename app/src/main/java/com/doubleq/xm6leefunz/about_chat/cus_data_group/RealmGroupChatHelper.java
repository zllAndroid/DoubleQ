package com.doubleq.xm6leefunz.about_chat.cus_data_group;

import android.content.Context;

import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.about_utils.about_realm.new_home.CusChatData;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * 聊天记录相关的数据库查询类
 */

public class RealmGroupChatHelper {
    public static final String FILE_NAME = "groupUserId";

    private Realm mRealm;
    public RealmGroupChatHelper(Context context) {
        mRealm = Realm.getDefaultInstance();
    }
    /**
     * add （增）
     */
    public void addRealmChat(final CusGroupChatData realmChat) {
        mRealm.beginTransaction();
        mRealm.copyToRealm(realmChat);
        mRealm.commitTransaction();
    }

    /**
     * delete （删）
     */
    public void deleteRealmMsg(String friendId) {
        CusGroupChatData dog = mRealm.where(CusGroupChatData.class).equalTo(FILE_NAME, friendId+SplitWeb.getUserId()).findFirst();
        if (dog!=null) {
            mRealm.beginTransaction();
            dog.deleteFromRealm();
            mRealm.commitTransaction();
        }
    }
    public  void deleteMsgByFriendId(String friendId) {
        RealmResults<CusGroupChatData> dogs = mRealm.where(CusGroupChatData.class).equalTo(FILE_NAME, friendId+SplitWeb.getUserId()).findAll();
//        CusDataRealmMsg dog = mRealm.where(CusDataRealmMsg.class).equalTo("taskId", taskId).findFirst();
        if (dogs.size()>0)
        {
            mRealm.beginTransaction();
            for (int i =0;i<dogs.size();i++)
            {
                CusGroupChatData dog = dogs.get(i);
                dog.deleteFromRealm();
            }
            mRealm.commitTransaction();
        }else
        {
            return;
        }
    }
    public void deleteAll() {
        final RealmResults<CusGroupChatData> dogs=  mRealm.where(CusGroupChatData.class).findAll();
        if (dogs!=null) {
            mRealm.beginTransaction();
            dogs.deleteAllFromRealm();
            mRealm.commitTransaction();
        }
    }
    /**
     * query （查询所有）
     */
    public List<CusGroupChatData> queryAllGroupChat(String id) {
        RealmResults<CusGroupChatData> realmMsgs = mRealm.where(CusGroupChatData.class).equalTo(FILE_NAME, id+SplitWeb.getUserId()).findAll();
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
        return realmMsgs;
    }


    public boolean isHaveExist(String id){
        CusGroupChatData dog=mRealm.where(CusGroupChatData.class).equalTo(FILE_NAME,id+SplitWeb.getUserId()).findFirst();
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
