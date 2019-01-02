package com.doubleq.xm6leefunz.about_chat.chat_group.group_realm;

import android.content.Context;

import com.doubleq.xm6leefunz.about_base.web_base.SplitWeb;
import com.doubleq.xm6leefunz.main_code.ui.about_contacts.about_link_realm.CusDataLinkFriend;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;


public class RealmGroupChatHeaderHelper {
    public static final String FILE_NAME = "totalId";
    public static final String USERID = "userid";

    private Realm mRealm;

    public RealmGroupChatHeaderHelper(Context context) {

        mRealm = Realm.getDefaultInstance();
    }
    /**
     * add （增）
     */
//添加好友信息
    public void addRealmGroupChat(final CusDataGroupChat groupChat) {
        groupChat.setTotalId(groupChat.getFriendId() + SplitWeb.getUserId());
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(groupChat);//有主键的情况下使用，添加更新
        mRealm.commitTransaction();
    }
    /**
     * delete （删）
     */
    public void deleteRealmFriend(String friendId) {
        CusDataGroupChat dog = mRealm.where(CusDataGroupChat.class).equalTo(FILE_NAME, friendId+SplitWeb.getUserId()).findFirst();
        if (dog!=null) {
            mRealm.beginTransaction();
            dog.deleteFromRealm();
            mRealm.commitTransaction();
        }
    }

    public void deleteAll() {
        final RealmResults<CusDataGroupChat> dogs=  mRealm.where(CusDataGroupChat.class).findAll();
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
        CusDataGroupChat realmMsg = mRealm.where(CusDataGroupChat.class).equalTo(FILE_NAME, friendId+SplitWeb.getUserId()).findFirst();
        if (realmMsg!=null) {
            mRealm.beginTransaction();
            realmMsg.setHeadImg(img);
            realmMsg.setImgPath(imgPath);
            realmMsg.setTime(time);
            mRealm.insertOrUpdate(realmMsg);
            mRealm.commitTransaction();
        }
    }

    /**
     * query （查询所有）
     */
    public List<CusDataGroupChat> queryAllRealmMsg() {
        RealmResults<CusDataGroupChat> realmMsgs = mRealm.where(CusDataGroupChat.class).findAll();
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
    public CusDataGroupChat queryGroupChat(String friendId) {
        CusDataGroupChat realmMsgs = mRealm.where(CusDataGroupChat.class)
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
    public String queryGroupChatReturnImgPath(String friendId) {
        CusDataGroupChat realmMsgs = mRealm.where(CusDataGroupChat.class)
                .equalTo(FILE_NAME,friendId+SplitWeb.getUserId())
                .findFirst();
        /**
         * 对查询结果，按Id进行排序，只能对查询结果进行排序
         */
//        //降序排列
        if (realmMsgs!=null&&realmMsgs.getImgPath()!=null)
            return realmMsgs.getImgPath();
        else
            return  null;
    }
//    查询是否存在
    public boolean queryIsGroupChat(String friendId) {
        CusDataGroupChat realmMsgs = mRealm.where(CusDataGroupChat.class)
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
