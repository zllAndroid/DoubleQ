package com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm;

import android.content.Context;

import com.mding.chatfeng.about_base.web_base.SplitWeb;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;


public class RealmFriendRelationHelper {
    public static final String FILE_NAME = "totalId";
    public static final String USERID = "userid";

    private Realm mRealm;

    public RealmFriendRelationHelper(Context context) {
        mRealm = Realm.getDefaultInstance();
    }
    /**
     * add （增）
     */
//  添加好友信息
    public void addRealmLinkFriend(final CusDataFriendRelation linkFriend) {
        linkFriend.setTotalId(linkFriend.getFriendId() + SplitWeb.getUserId());
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(linkFriend);//有主键的情况下使用，添加更新
        mRealm.commitTransaction();
    }
    /**
     * delete （删）
     */
    public void deleteRealmFriend(String friendId) {
        CusDataFriendRelation dog = mRealm.where(CusDataFriendRelation.class).equalTo(FILE_NAME, friendId+SplitWeb.getUserId()).findFirst();
        if (dog!=null) {
            mRealm.beginTransaction();
            dog.deleteFromRealm();
            mRealm.commitTransaction();
        }
    }

    public void deleteAll() {
        final RealmResults<CusDataFriendRelation> dogs=  mRealm.where(CusDataFriendRelation.class).findAll();
        if (dogs!=null) {
            mRealm.beginTransaction();
            dogs.deleteAllFromRealm();
            mRealm.commitTransaction();
        }
    }

    /**
     * update （改） 头像和头像地址，时间
     */
    public void updateHeadPath(String friendId, String imgPath,String img, String time) {
        CusDataFriendRelation realmMsg = mRealm.where(CusDataFriendRelation.class).equalTo(FILE_NAME, friendId+SplitWeb.getUserId()).findFirst();
        if (realmMsg!=null) {
            mRealm.beginTransaction();
            realmMsg.setHeadImg(img);
            realmMsg.setHeadImg(imgPath);
            realmMsg.setCreated(time);
            mRealm.insertOrUpdate(realmMsg);
            mRealm.commitTransaction();
        }
    }

    /**
     * 更新全部
     * @param friendId
     * @param cusDataFriendRelation
     */
    public void updateAll(String friendId, CusDataFriendRelation cusDataFriendRelation) {
        CusDataFriendRelation realmMsg = mRealm.where(CusDataFriendRelation.class).equalTo(FILE_NAME, friendId+SplitWeb.getUserId()).findFirst();
        if (realmMsg!=null) {
            mRealm.beginTransaction();
            realmMsg=cusDataFriendRelation;
            realmMsg.setTotalId(friendId + SplitWeb.getUserId());
            mRealm.insertOrUpdate(realmMsg);
            mRealm.commitTransaction();
        }
    }

    /**
     * query （查询所有）
     */
    public List<CusDataFriendRelation> queryAllRealmMsg() {
        RealmResults<CusDataFriendRelation> realmMsgs = mRealm.where(CusDataFriendRelation.class).findAll();
        /**
         * 对查询结果，按Id进行排序，只能对查询结果进行排序
         */
        if (realmMsgs!=null) {
            //增序排列
//            realmMsgs = realmMsgs.sort(FILE_NAME);
            realmMsgs = realmMsgs.sort("time", Sort.DESCENDING);
        }
        if (realmMsgs!=null)
            return mRealm.copyFromRealm(realmMsgs);
        else
            return  null;
    }

    /**
     * 根据id查询
     * @return
     */
    public CusDataFriendRelation queryLinkFriend(String friendId) {
        CusDataFriendRelation realmMsgs = mRealm.where(CusDataFriendRelation.class)
                .equalTo(FILE_NAME,friendId+SplitWeb.getUserId())
                .findFirst();
        /**
         * 对查询结果，按Id进行排序，只能对查询结果进行排序
         */
//        //降序排列
        if (realmMsgs!=null)
            return mRealm.copyFromRealm(realmMsgs);
        else
            return  null;
    }
    public String queryLinkFriendReturnImgPath(String friendId) {
        CusDataFriendRelation realmMsgs = mRealm.where(CusDataFriendRelation.class)
                .equalTo(FILE_NAME,friendId+SplitWeb.getUserId())
                .findFirst();
        /**
         * 对查询结果，按Id进行排序，只能对查询结果进行排序
         */
//        //降序排列
        if (realmMsgs!=null&&realmMsgs.getHeadImg()!=null)
            return realmMsgs.getHeadImg();
        else
            return  null;
    }
//    查询是否存在
    public boolean queryIsLinkFriend(String friendId) {
        CusDataFriendRelation realmMsgs = mRealm.where(CusDataFriendRelation.class)
                .equalTo(FILE_NAME,friendId+SplitWeb.getUserId())
                .findFirst();
        /**
         * 对查询结果，按Id进行排序，只能对查询结果进行排序
         */
//        //降序排列
        if (realmMsgs!=null)
            return true;
        else
            return  false;
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
