package com.mding.chatfeng.about_utils.about_realm.new_home;

import android.content.Context;
import android.util.Log;

import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.model.CusJumpChatData;
import com.mding.model.DataGroupChatResult;
import com.mding.model.DataGroupChatSend;
import com.mding.model.off_line_msg.DataOffLineGroupChat;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;


public class RealmHomeHelper {
    public static final String DB_NAME = "chat.realm";
    public static final String FILE_NAME = "totalId";
    public static final String USERID = "userid";
    public static final String TypeMsg = "1";
    public static final String TypeQun = "2";

    private Realm mRealm;

    public RealmHomeHelper(Context context) {

        mRealm = Realm.getDefaultInstance();
    }
    /**
     * add （增）
     */
    public void addRealmMsg(final CusJumpChatData realmMsg) {
        CusHomeRealmData homeRealmData =new CusHomeRealmData();
//        CusHomeRealmData homeRealmData = mRealm.createObject(CusHomeRealmData.class,realmMsg.getFriendId()+SplitWeb.getSplitWeb().getUserId());
        homeRealmData.setNickName(realmMsg.getFriendName());
        homeRealmData.setFriendId(realmMsg.getFriendId());
        homeRealmData.setHeadImg(realmMsg.getFriendHeader());
        homeRealmData.setType(TypeMsg);
        homeRealmData.setUserid(SplitWeb.getSplitWeb().getUserId());
        homeRealmData.setTotalId(realmMsg.getFriendId()+SplitWeb.getSplitWeb().getUserId());
        homeRealmData.setMessageType(homeRealmData.getMessageType());
        mRealm.beginTransaction();
//        mRealm.copyToRealm(homeRealmData);//进来几次就添加几个
        mRealm.copyToRealmOrUpdate(homeRealmData);//有主键的情况下使用，添加更新
//        queryAllRealmChat(realmMsg.getFriendId());
        mRealm.commitTransaction();
    }
    public void addRealmMsgList(final List<CusHomeRealmData> mList)
    {
        deleteAll();
        for (int i =0;i<=mList.size()-1;i++)
        {
            addRealmMsg( mList.get(i));
        }
    }
//添加群消息
    public void addRealmMsgQun(final CusHomeRealmData homeRealmData) {
        homeRealmData.setType(TypeQun);
        homeRealmData.setUserid(SplitWeb.getSplitWeb().getUserId());
        int num = 0;
        try {
            num = homeRealmData.getNum();
        } catch (Exception e) {
            e.printStackTrace();
        }
        homeRealmData.setNum(num + 1);
        homeRealmData.setTotalId(homeRealmData.getFriendId() + SplitWeb.getSplitWeb().getUserId());
        homeRealmData.setMessageType(homeRealmData.getMessageType());
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(homeRealmData);//有主键的情况下使用，添加更新
        mRealm.commitTransaction();
    }
//    添加好友消息
    public void addRealmMsg(final CusHomeRealmData homeRealmData) {
        homeRealmData.setType(TypeMsg);
        homeRealmData.setUserid(SplitWeb.getSplitWeb().getUserId());
        int num = 0;
        try {
            num = homeRealmData.getNum();
        } catch (Exception e) {
            e.printStackTrace();
        }
        homeRealmData.setNum(num + 1);

        homeRealmData.setTotalId(homeRealmData.getFriendId() + SplitWeb.getSplitWeb().getUserId());
        homeRealmData.setMessageType(homeRealmData.getMessageType());
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(homeRealmData);//有主键的情况下使用，添加更新
        mRealm.commitTransaction();
    }
    public void insertaddRealmMsg(final CusJumpChatData realmMsg) {
        mRealm.beginTransaction();
        CusHomeRealmData homeRealmData = new CusHomeRealmData();
//        CusHomeRealmData homeRealmData = mRealm.createObject(CusHomeRealmData.class,realmMsg.getFriendId()+SplitWeb.getSplitWeb().getUserId());
        homeRealmData.setNickName(realmMsg.getFriendName());
        homeRealmData.setFriendId(realmMsg.getFriendId());
        homeRealmData.setHeadImg(realmMsg.getFriendHeader());
        homeRealmData.setType(TypeMsg);
        homeRealmData.setTotalId(realmMsg.getFriendId()+SplitWeb.getSplitWeb().getUserId());
//        mRealm.copyToRealm(homeRealmData);//进来几次就添加几个
        mRealm.insertOrUpdate(homeRealmData);//插入更新
        mRealm.commitTransaction();
    }
    /**
     * delete （删）
     */
    public void deleteRealmMsg(String friendId) {
        CusHomeRealmData dog = mRealm.where(CusHomeRealmData.class).equalTo(FILE_NAME, friendId+SplitWeb.getSplitWeb().getUserId()).findFirst();
        if (dog!=null) {
            mRealm.beginTransaction();
            dog.deleteFromRealm();
            mRealm.commitTransaction();
        }
    }

    public void deleteAll() {
        final RealmResults<CusHomeRealmData> dogs=  mRealm.where(CusHomeRealmData.class).findAll();
        if (dogs!=null) {
            mRealm.beginTransaction();
            dogs.deleteAllFromRealm();
            mRealm.commitTransaction();
        }
    }

    /**
     * update （改） 消息和时间
     */
    public void updateMsg(String friendId, String msg, String time,String shield,String disturb,String top,String msgType) {
        CusHomeRealmData realmMsg = mRealm.where(CusHomeRealmData.class).equalTo(FILE_NAME, friendId+SplitWeb.getSplitWeb().getUserId()).findFirst();
        if (realmMsg!=null) {
            mRealm.beginTransaction();
            realmMsg.setMsg(msg);
            realmMsg.setTime(time);

            realmMsg.setMessageType(msgType);

            realmMsg.setMsgIsDisTurb(disturb);
            realmMsg.setIsTopMsg(top);
            realmMsg.setIsShield(shield);
            mRealm.insertOrUpdate(realmMsg);
            mRealm.commitTransaction();
        }
    }
    public void updateGroupMsg(String friendId, String msg, String time,DataGroupChatResult.RecordBean record) {
        CusHomeRealmData realmMsg = mRealm.where(CusHomeRealmData.class).equalTo(FILE_NAME, friendId+SplitWeb.getSplitWeb().getUserId()).findFirst();
        if (realmMsg!=null) {
            mRealm.beginTransaction();
            realmMsg.setMsg(msg);
            realmMsg.setTime(time);
            realmMsg.setNickName(record.getGroupName());

            realmMsg.setMemberId(record.getMemberId());
            realmMsg.setMessageType(record.getMessageType());
            realmMsg.setMsgIsDisTurb(record.getDisturbType());
            realmMsg.setIsTopMsg(record.getTopType());
            realmMsg.setIsShield(record.getOperationType());
            realmMsg.setBannedType(record.getBannedType());//是否被禁言
            realmMsg.setAssistantType(record.getAssistantType());//加入群助手
            mRealm.insertOrUpdate(realmMsg);
            mRealm.commitTransaction();
        }
    }
    public void updateGroupAssNum( String num) {
        CusHomeRealmData realmMsg = mRealm.where(CusHomeRealmData.class).equalTo(FILE_NAME, AppConfig.GroupAssistant).findFirst();
        if (realmMsg!=null) {
            mRealm.beginTransaction();
            realmMsg.setUserid(SplitWeb.getSplitWeb().getUserId());
            realmMsg.setTotalId(AppConfig.GroupAssistant);
            realmMsg.setGroupNumMsg(num);
            mRealm.insertOrUpdate(realmMsg);
            mRealm.commitTransaction();
        }else {
            CusHomeRealmData homeRealmData =new CusHomeRealmData();
            homeRealmData.setTotalId(AppConfig.GroupAssistant);
            homeRealmData.setUserid(SplitWeb.getSplitWeb().getUserId());
            homeRealmData.setGroupNumMsg(num);
            mRealm.beginTransaction();
            mRealm.copyToRealmOrUpdate(homeRealmData);//有主键的情况下使用，添加更新
            mRealm.commitTransaction();
        }
    }
    public void updateGroupMsg(String friendId, String msg, String time,DataGroupChatSend.RecordBean record) {
        CusHomeRealmData realmMsg = mRealm.where(CusHomeRealmData.class).equalTo(FILE_NAME, friendId+SplitWeb.getSplitWeb().getUserId()).findFirst();
        if (realmMsg!=null) {
            mRealm.beginTransaction();
            realmMsg.setMsg(msg);
            realmMsg.setTime(time);
            realmMsg.setMemberId(record.getMemberId());
            realmMsg.setMessageType(record.getMessageType());
            realmMsg.setMsgIsDisTurb(record.getDisturbType());
            realmMsg.setIsTopMsg(record.getTopType());
            realmMsg.setIsShield(record.getOperationType());
            realmMsg.setBannedType(record.getBannedType());//是否被禁言
            realmMsg.setAssistantType(record.getAssistantType());//是群助手
            mRealm.insertOrUpdate(realmMsg);
            mRealm.commitTransaction();
        }
    }
    public void updateGroupMsg(String friendId, String msg, String time,DataOffLineGroupChat.RecordBean.MessageListBean record) {
        CusHomeRealmData realmMsg = mRealm.where(CusHomeRealmData.class).equalTo(FILE_NAME, friendId+SplitWeb.getSplitWeb().getUserId()).findFirst();
        if (realmMsg!=null) {
            mRealm.beginTransaction();
            realmMsg.setMsg(msg);
            realmMsg.setTime(time);
            realmMsg.setMessageType(record.getMessageType());

            realmMsg.setMsgIsDisTurb(record.getDisturbType());
            realmMsg.setIsTopMsg(record.getTopType());
            realmMsg.setIsShield(record.getOperationType());
            realmMsg.setBannedType(record.getBannedType());//是否被禁言
            realmMsg.setAssistantType(record.getAssistantType());//是群助手
            mRealm.insertOrUpdate(realmMsg);
            mRealm.commitTransaction();
        }
    }
    public void updateNum(String friendId) {
        CusHomeRealmData realmMsg = mRealm.where(CusHomeRealmData.class).equalTo(FILE_NAME, friendId+SplitWeb.getSplitWeb().getUserId()).findFirst();
        if (realmMsg!=null) {
            mRealm.beginTransaction();
            int num = 0;
            try {
                num = realmMsg.getNum();
            } catch (Exception e) {
                e.printStackTrace();
            }
            realmMsg.setNum(num+1);
            mRealm.insertOrUpdate(realmMsg);
            mRealm.commitTransaction();
        }
    }
    public void updateNum(String friendId,String name) {
        CusHomeRealmData realmMsg = mRealm.where(CusHomeRealmData.class).equalTo(FILE_NAME, friendId+SplitWeb.getSplitWeb().getUserId()).findFirst();
        if (realmMsg!=null) {
            mRealm.beginTransaction();
            int num = 0;
            try {
                num = realmMsg.getNum();
            } catch (Exception e) {
                e.printStackTrace();
            }
            realmMsg.setNum(num+1);
            realmMsg.setNickName(name);
            mRealm.insertOrUpdate(realmMsg);
            mRealm.commitTransaction();
        }
    }
    public void updateNumZero(String friendId) {
//    public void updateNumZero(String friendId,String name) {
        CusHomeRealmData realmMsg = mRealm.where(CusHomeRealmData.class).equalTo(FILE_NAME, friendId+SplitWeb.getSplitWeb().getUserId()).findFirst();
        if (realmMsg!=null) {
            mRealm.beginTransaction();
            realmMsg.setNum(0);
            mRealm.insertOrUpdate(realmMsg);
            mRealm.commitTransaction();
        }
    }
    public void updateNumZero(String friendId,String name) {
//    public void updateNumZero(String friendId,String name) {
        CusHomeRealmData realmMsg = mRealm.where(CusHomeRealmData.class).equalTo(FILE_NAME, friendId+SplitWeb.getSplitWeb().getUserId()).findFirst();
        if (realmMsg!=null) {
            mRealm.beginTransaction();
            realmMsg.setNickName(name);
            realmMsg.setNum(0);
            mRealm.insertOrUpdate(realmMsg);
            mRealm.commitTransaction();
        }
    }
    public void updateGroup(String friendId,CusHomeRealmData cusHomeRealmData) {
        CusHomeRealmData realmMsg = mRealm.where(CusHomeRealmData.class).equalTo(FILE_NAME, friendId+SplitWeb.getSplitWeb().getUserId()).findFirst();
        if (realmMsg!=null) {
            mRealm.beginTransaction();
            realmMsg=cusHomeRealmData;
            realmMsg.setTotalId(friendId+SplitWeb.getSplitWeb().getUserId());
            realmMsg.setNum(0);
            realmMsg.setType(TypeQun);
            mRealm.insertOrUpdate(realmMsg);
            mRealm.commitTransaction();
        }
    }

    /**
     * update （改） 群名
     */
    public void updateGroupName(String groupId, String groupName) {
        CusHomeRealmData realmInfo = mRealm.where(CusHomeRealmData.class).equalTo(FILE_NAME, groupId+SplitWeb.getSplitWeb().getUserId()).findFirst();
        if (realmInfo!=null) {
            mRealm.beginTransaction();
            realmInfo.setNickName(groupName);
            mRealm.insertOrUpdate(realmInfo);
            mRealm.commitTransaction();
        }
    }

    public int queryNum(String friendId) {
        CusHomeRealmData realmMsg = mRealm.where(CusHomeRealmData.class)
                .equalTo(FILE_NAME, friendId+SplitWeb.getSplitWeb().getUserId())
                .findFirst();
        int num=0;
        num = realmMsg.getNum();
        if (num==0)
        {
            return 0;
        }
        return num;
    }

    /**
     * query （查询所有）
     */
    public List<CusHomeRealmData> queryAllRealmMsg() {
        RealmResults<CusHomeRealmData> realmMsgs = mRealm.where(CusHomeRealmData.class).equalTo(USERID, SplitWeb.getSplitWeb().getUserId()).findAll();
        /**
         * 对查询结果，按Id进行排序，只能对查询结果进行排序
         */
        if (realmMsgs!=null) {
            //增序排列
//            realmMsgs = realmMsgs.sort(FILE_NAME);
            realmMsgs = realmMsgs.sort("time", Sort.DESCENDING);
//            realmMsgs = realmMsgs.sort(FILE_NAME, Sort.ASCENDING);
//            realmMsgs = realmMsgs.sort(FILE_NAME, Sort.DESCENDING);
        }
//        //降序排列
//        dogs=dogs.sort("id", Sort.DESCENDING);
//        if (realmMsgs!=null)
//            return realmMsgs;
        if (realmMsgs!=null)
            return mRealm.copyFromRealm(realmMsgs);
        else
            return  null;
    }
    public List<CusHomeRealmData> queryAllmMsg() {
        RealmResults<CusHomeRealmData> realmMsgs = mRealm.where(CusHomeRealmData.class).equalTo(USERID, SplitWeb.getSplitWeb().getUserId()).findAll();
        /**
         * 对查询结果，按Id进行排序，只能对查询结果进行排序
         */
        if (realmMsgs!=null) {
            //增序排列
            realmMsgs = realmMsgs.sort("time", Sort.DESCENDING);
        }
        if (realmMsgs!=null)
            return mRealm.copyFromRealm(realmMsgs);
        else
            return  null;
    }

    /**
     * 根据id查询聊天消息
     * @return
     */
    public CusHomeRealmData queryAllRealmChat(String friendId) {
        CusHomeRealmData realmMsgs = mRealm.where(CusHomeRealmData.class)
                .equalTo(FILE_NAME,friendId+SplitWeb.getSplitWeb().getUserId())
                .findFirst();
//        CusHomeRealmData realmMsgs = mRealm.where(CusHomeRealmData.class).equalTo(FILE_NAME,friendId+SplitWeb.getSplitWeb().getUserId()).findFirst();
        /**
         * 对查询结果，按Id进行排序，只能对查询结果进行排序
         */
//        //降序排列
//        dogs=dogs.sort("id", Sort.DESCENDING);
        if (realmMsgs!=null)
            return mRealm.copyFromRealm(realmMsgs);
        else
            return  null;
    }
    public boolean isFriendMsg(String friendId) {
        CusHomeRealmData realmMsgs = mRealm.where(CusHomeRealmData.class)
                .equalTo(FILE_NAME,friendId+SplitWeb.getSplitWeb().getUserId())
                .findFirst();
//        CusHomeRealmData realmMsgs = mRealm.where(CusHomeRealmData.class).equalTo(FILE_NAME,friendId+SplitWeb.getSplitWeb().getUserId()).findFirst();
        /**
         * 对查询结果，按Id进行排序，只能对查询结果进行排序
         */
//        //降序排列
//        dogs=dogs.sort("id", Sort.DESCENDING);
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
