package com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm;

import android.content.Context;

import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

//联系人部分   群表
public class RealmGroupHelper {
    public static final String FILE_NAME = "totalId";
    public static final String USERID = "userid";

    private Realm mRealm;

    public RealmGroupHelper(Context context) {
        mRealm = Realm.getDefaultInstance();
    }
    /**
     * add （增）
     */
//  添加好友信息
    public void addRealmGroup(final CusDataGroup linkFriend) {
        linkFriend.setTotalId(linkFriend.getGroupId() + SplitWeb.getSplitWeb().getUserId());
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(linkFriend);//有主键的情况下使用，添加更新
        mRealm.commitTransaction();
    }
    /**
     * delete （删）
     */
    public void deleteRealmFriend(String friendId) {
        CusDataGroup dog = mRealm.where(CusDataGroup.class).equalTo(FILE_NAME, friendId+SplitWeb.getSplitWeb().getUserId()).findFirst();
        if (dog!=null) {
            mRealm.beginTransaction();
            dog.deleteFromRealm();
            mRealm.commitTransaction();
        }
    }

    public void deleteAll() {
        final RealmResults<CusDataGroup> dogs=  mRealm.where(CusDataGroup.class).findAll();
        if (dogs!=null) {
            mRealm.beginTransaction();
            dogs.deleteAllFromRealm();
            mRealm.commitTransaction();
        }
    }

    /**
     * update （改） 头像和头像地址，时间
     */
    public void updateHeadPath(String friendId, String img, String time) {
        CusDataGroup realmMsg = mRealm.where(CusDataGroup.class).equalTo(FILE_NAME, friendId+SplitWeb.getSplitWeb().getUserId()).findFirst();
        if (realmMsg!=null) {
            mRealm.beginTransaction();
            realmMsg.setGroupHeadImg(img);
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
    public void updateAll(String friendId, CusDataGroup cusDataFriendRelation) {
        CusDataGroup realmMsg = mRealm.where(CusDataGroup.class).equalTo(FILE_NAME, friendId+SplitWeb.getSplitWeb().getUserId()).findFirst();
        if (realmMsg!=null) {
            mRealm.beginTransaction();
            realmMsg=cusDataFriendRelation;
            realmMsg.setTotalId(cusDataFriendRelation.getGroupId() + SplitWeb.getSplitWeb().getUserId());
            mRealm.insertOrUpdate(realmMsg);
            mRealm.commitTransaction();
        }
    }

    /**
     * query （查询所有）
     */
    public List<CusDataGroup> queryAllRealmMsg() {
        RealmResults<CusDataGroup> realmMsgs = mRealm.where(CusDataGroup.class).findAll();
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
    public CusDataGroup queryLinkFriend(String friendId) {
        CusDataGroup realmMsgs = mRealm.where(CusDataGroup.class)
                .equalTo(FILE_NAME,friendId+SplitWeb.getSplitWeb().getUserId())
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
    public String queryLinkFriendReturnName(String friendId) {
        CusDataGroup realmMsgs = mRealm.where(CusDataGroup.class)
                .equalTo(FILE_NAME,friendId+SplitWeb.getSplitWeb().getUserId())
                .findFirst();
        /**
         * 对查询结果，按Id进行排序，只能对查询结果进行排序
         */
//        //降序排列
        if (realmMsgs!=null) {
                return realmMsgs.getGroupName();
        }
        else
            return  null;
    }
    public String queryLinkFriendReturnImgPath(String friendId) {
        CusDataGroup realmMsgs = mRealm.where(CusDataGroup.class)
                .equalTo(FILE_NAME,friendId+SplitWeb.getSplitWeb().getUserId())
                .findFirst();
        /**
         * 对查询结果，按Id进行排序，只能对查询结果进行排序
         */
//        //降序排列
        if (realmMsgs!=null&&realmMsgs.getGroupHeadImg()!=null)
            return realmMsgs.getGroupHeadImg();
        else
            return  null;
    }
//    查询是否存在
    public boolean queryIsLinkFriend(String friendId) {
        CusDataGroup realmMsgs = mRealm.where(CusDataGroup.class)
                .equalTo(FILE_NAME,friendId+SplitWeb.getSplitWeb().getUserId())
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
