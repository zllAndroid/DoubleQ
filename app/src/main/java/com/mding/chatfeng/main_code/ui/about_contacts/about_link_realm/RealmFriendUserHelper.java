package com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm;

import android.content.Context;

import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

//用戶表(所有用户都在本表中有且仅有一个，包含群成员，是否好友)
public class RealmFriendUserHelper {
    public static final String FILE_NAME = "totalId";
    public static final String USERID = "userid";

    private Realm mRealm;

    public RealmFriendUserHelper(Context context) {

        mRealm = Realm.getDefaultInstance();
    }
    /**
     * add （增）
     */
//添加好友信息
    public void addRealmFriendUser(final CusDataFriendUser linkFriend) {
        linkFriend.setTotalId(linkFriend.getFriendId() + SplitWeb.getUserId());
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(linkFriend);//有主键的情况下使用，添加更新
        mRealm.commitTransaction();
    }
    /**
     * delete （删）
     */
    public void deleteRealmFriend(String friendId) {
        CusDataFriendUser dog = mRealm.where(CusDataFriendUser.class).equalTo(FILE_NAME, friendId+SplitWeb.getUserId()).findFirst();
        if (dog!=null) {
            mRealm.beginTransaction();
            dog.deleteFromRealm();
            mRealm.commitTransaction();
        }
    }

    public void deleteAll() {
        final RealmResults<CusDataFriendUser> dogs=  mRealm.where(CusDataFriendUser.class).findAll();
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
        CusDataFriendUser realmMsg = mRealm.where(CusDataFriendUser.class).equalTo(FILE_NAME, friendId+SplitWeb.getUserId()).findFirst();
        if (realmMsg!=null) {
            mRealm.beginTransaction();
            realmMsg.setHeadImgBase64(img);
            realmMsg.setImgPathUrl(imgPath);
            realmMsg.setTime(time);
            mRealm.insertOrUpdate(realmMsg);
            mRealm.commitTransaction();
        }
    }
    /**
     * 更新全部
     * @param friendId
     * @param cusDataFriendUser
     */
    public void updateAll(String friendId, CusDataFriendUser cusDataFriendUser) {
        CusDataFriendUser realmMsg = mRealm.where(CusDataFriendUser.class).equalTo(FILE_NAME, friendId+SplitWeb.getUserId()).findFirst();
        if (realmMsg!=null) {
            mRealm.beginTransaction();
            realmMsg=cusDataFriendUser;
            realmMsg.setTotalId(friendId + SplitWeb.getUserId());
            mRealm.insertOrUpdate(realmMsg);
            mRealm.commitTransaction();
        }
    }
//    public void updateHeadPath(String friendId, String imgPath,String img, String time) {
//        CusDataFriendUser realmMsg = mRealm.where(CusDataFriendUser.class).equalTo(FILE_NAME, friendId+SplitWeb.getUserId()).findFirst();
//        if (realmMsg!=null) {
//            mRealm.beginTransaction();
//            realmMsg.setHeadImgBase64(img);
//            realmMsg.setImgPathUrl(imgPath);
//            realmMsg.setTime(time);
//            mRealm.insertOrUpdate(realmMsg);
//            mRealm.commitTransaction();
//        }
//    }

    /**
     * query （查询所有人）
     */
    public List<CusDataFriendUser> queryAllUser() {
        RealmResults<CusDataFriendUser> realmMsgs = mRealm.where(CusDataFriendUser.class).findAll();
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
     * 根据id查询某一个人
     * @return
     */
    public CusDataFriendUser queryLinkFriend(String friendId) {
        CusDataFriendUser realmMsgs = mRealm.where(CusDataFriendUser.class)
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
    public String queryLinkFriendReturnname(String friendId) {
        CusDataFriendUser realmMsgs = mRealm.where(CusDataFriendUser.class)
                .equalTo(FILE_NAME,friendId+SplitWeb.getUserId())
                .findFirst();
        /**
         * 对查询结果，按Id进行排序，只能对查询结果进行排序
         */
//        //降序排列
        if (realmMsgs!=null) {
            if (StrUtils.isEmpty(realmMsgs.getRemarkName()))
                return realmMsgs.getName();
            else
            {
                return realmMsgs.getRemarkName();
            }
        }
        else
            return  null;
    }
    public String queryLinkFriendReturnImgPath(String friendId) {
        CusDataFriendUser realmMsgs = mRealm.where(CusDataFriendUser.class)
                .equalTo(FILE_NAME,friendId+SplitWeb.getUserId())
                .findFirst();
        /**
         * 对查询结果，按Id进行排序，只能对查询结果进行排序
         */
//        //降序排列
        if (realmMsgs!=null&&realmMsgs.getHeadImgBase64()!=null)
            return realmMsgs.getHeadImgBase64();
        else
            return  null;
    }
    //    查询是否存在
    public boolean queryIsLinkFriend(String friendId) {
        CusDataFriendUser realmMsgs = mRealm.where(CusDataFriendUser.class)
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
