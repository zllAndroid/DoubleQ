package com.mding.chatfeng.about_application;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.PowerManager;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.mding.chatfeng.about_base.AppConfig;
import com.mding.chatfeng.about_base.Methon;
import com.mding.chatfeng.about_base.deal_application.DealFriendAdd;
import com.mding.chatfeng.about_base.deal_application.DealGroupAdd;
import com.mding.chatfeng.about_base.deal_application.DealGroupInvitation;
import com.mding.chatfeng.about_base.deal_application.DealGroupInvitationQrCode;
import com.mding.chatfeng.about_base.deal_application.DealGroupingSort;
import com.mding.chatfeng.about_base.deal_application.DealModifyFriendList;
import com.mding.chatfeng.about_base.deal_application.DealModifyGroupList;
import com.mding.chatfeng.about_base.deal_application.DealModifyGroupOfList;
import com.mding.chatfeng.about_base.deal_application.DealUpdateFriend;
import com.mding.chatfeng.about_base.web_base.SplitWeb;
import com.mding.chatfeng.about_broadcastreceiver.MainTabNumEvent;
import com.mding.chatfeng.about_broadcastreceiver.MsgHomeEvent;
import com.mding.chatfeng.about_chat.cus_data_group.CusGroupChatData;
import com.mding.chatfeng.about_chat.cus_data_group.RealmGroupChatHelper;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.MathUtils;
import com.mding.chatfeng.about_utils.NotificationUtil;
import com.mding.chatfeng.about_utils.SysRunUtils;
import com.mding.chatfeng.about_utils.TimeUtil;
import com.mding.chatfeng.about_utils.about_realm.new_home.CusChatData;
import com.mding.chatfeng.about_utils.about_realm.new_home.CusHomeRealmData;
import com.mding.chatfeng.about_utils.about_realm.new_home.RealmChatHelper;
import com.mding.chatfeng.about_utils.about_realm.new_home.RealmHomeHelper;
import com.mding.chatfeng.main_code.mains.MsgFragment;
import com.mding.model.CusJumpChatData;
import com.mding.model.DataAddfriendSendRequest;
import com.mding.model.DataAgreeFriend;
import com.mding.model.DataCreatGroupResult;
import com.mding.model.DataFriendPush;
import com.mding.model.DataGroupChatResult;
import com.mding.model.DataGroupChatSend;
import com.mding.model.DataJieShou;
import com.mding.model.off_line_msg.DataOffLineChat;
import com.mding.model.off_line_msg.DataOffLineGroupChat;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.projects.zll.utilslibrarybyzll.aboututils.SPUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.projects.zll.utilslibrarybyzll.aboututils.ToastUtil;
import com.rance.chatui.util.Constants;
import com.zll.websocket.Response;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * 项目：DoubleQ
 * 文件描述：
 * 作者：zll
 * 创建时间：2019/4/12 0012
 * 修改人：ljj
 * 更改时间：
 * 修改备注：
 */
public class DealDataByApp {
    public static Context mContext;
    public static String message;
    public static RealmHomeHelper realmHelper;
    public static RealmChatHelper realmChatHelper;
    public static RealmGroupChatHelper realmGroupChatHelper;
    private  static RealmHomeHelper  getRealmHomeHelper()
    {
//        if (realmHelper==null)
        realmHelper = new RealmHomeHelper(mContext);
        return realmHelper;
    }
    private  static RealmChatHelper  getRealmChatHelper()
    {
//        if (realmChatHelper==null)
        realmChatHelper = new RealmChatHelper(mContext);
        return realmChatHelper;
    }
    private  static RealmGroupChatHelper  getRealmGroupChatHelper() {
//        if (realmGroupChatHelper==null)
        realmGroupChatHelper = new RealmGroupChatHelper(mContext);
        return realmGroupChatHelper;
    }
    public static void synData(Context mmContext ,String mmessage) {
        mContext=BaseApplication.getAppContext();
//        mContext=mmContext;
        message=mmessage;
//        realmHelper=mrealmHelper;
//        realmChatHelper=mrealmChatHelper;
//       realmGroupChatHelper=mrealmGroupChatHelper;
//        realmHelper = new RealmHomeHelper(mContext);
//        realmGroupChatHelper = new RealmGroupChatHelper(mContext);
//        realmChatHelper = new RealmChatHelper(mContext);
        initReceiver();
    }
    public static void initReceiver() {
        //        接收消息时处理消息并存库
        String isSucess = HelpUtils.HttpIsSucess(message);
        if (isSucess.equals(AppAllKey.CODE_OK)) {
//            判断返回的方法名
            String s = HelpUtils.backMethod(message);
            switch (s) {
//                   接收好友消息
                case Methon.PrivateChat:
//                case "privateReceive":
                    MsgFragment.isZero = true;
                    dealReceiver(message);
                    break;
//                    接收群组消息
                case Methon.ReceiveGroupChat:
//                case "receiveGroupChat":
//                case "groupReceive":
                    MsgFragment.isZero = true;
                    initGroupReceiveData(message);
                    break;
//                    群发送信息
                case Methon.GroupChatSend:
//                case "groupSend":
                    dealGroupSend(message);
                    break;
//                    接收好友推送通知（对方加我为好友时）
                case "addFriendSend":
                    dealFriend(message);
                    break;
//                    添加好友-离线推送通知接口(接收者)
                case "addFriendPush":
                    dealFriendPush(message);
                    break;
//                    对方同意我的好友请求,后推送接口
                case "agreeFriendSend":
                    dealAgreeFriend(message);
                    break;
                case "createdUserGroup":
                    CGS(message);
                    break;
//                    私聊发送消息
                case Methon.PrivateSend:
//                case "privateSend":
                    dealSend(message);
                    break;


//                    用户在线私聊 - 离线消息
                case "pullPrivateChat":
                    dealOffLineChat(message);
                    break;
//                    用户在线群聊 - 离线消息
                case "pullGroupChat":
                    dealOffLineGroupChat(message);
                    break;


//                    创建群
                case "agreeGroupListSend":
                    DealGroupAdd.getDealGroupAdd().updateGroupDataByAdd(mContext, message);
                    break;
//                    加入群聊  用户加入群 - 给成员发送 联系人变动信息接口
                case "joinGroupListSend":
                    DealGroupAdd.getDealGroupAdd().updateGroupDataByAdd(mContext, message);
                    break;
//                    退出群聊
                case "outGroupListSend":
                    DealGroupAdd.getDealGroupAdd().updateGroupDataBySub(mContext, message,getRealmHomeHelper());
                    break;
//                    被移出群，被移除的成員收到的推送
                case "removeGroupListSend":
                    DealGroupAdd.getDealGroupAdd().updateGroupDataBySub(mContext, message,getRealmHomeHelper());
                    break;
//                    修改群信息   给成员发送 联系人变动信息接口  （含：用户修改自己群的名称）
                case "modifyGroupListSend":
                    String s1 = DealGroupAdd.getDealGroupAdd().updateGroupDataByModifySub(mContext, message);
                    if (!StrUtils.isEmpty(s1))
                    {
                        DealGroupAdd.getDealGroupAdd().updateGroupDataByModifyAdd(mContext,  message );
                    }
                    break;
//                    解散群聊
                case "dissolutionGroupListSend":
                    DealGroupAdd.getDealGroupAdd().updateGroupDataBySub(mContext, message,getRealmHomeHelper());
                    break;

//                    添加好友
                case "agreeFriendListSend":
                    DealFriendAdd.getDealFriendAdd().updateFriendDataByAdd(mContext, message);
                    break;
//                    删除好友
                case "deleteFriendSend":
                    DealFriendAdd.getDealFriendAdd().updateFriendDataBySub(mContext,message);
                    break;
//                    添、删、改好友分组or群分组管理
                case "addFriendGroupManageSend":
//                    DealModifyGroupOfList.modifyGroupOfList(this,message);
                    break;
//                    增加好友/群分组推送
                case "agreeGroupingListSend":
                    DealModifyGroupOfList.getDealModifyGroupOfList().addGroupOfList(mContext,message);
                    break;
//                    删除好友/群分组推送
                case "deleteGroupingListSend":
                    DealModifyGroupOfList.getDealModifyGroupOfList().deleteGroupOfList(mContext,message);
                    break;
//                    修改好友/群分组推送
                case "modifyGroupingListSend":
                    DealModifyGroupOfList.getDealModifyGroupOfList().modifyGroupOfList(mContext,message);
                    break;
//                    修改好友分组/备注推送
                case "modifyFriendListSend":
                    DealModifyFriendList.getDealModifyFriendList().modifyGroupOfFriend(mContext,message);
                    break;
//                    好友/群分组排序推送
                case "modifyGroupingSortSend":
                    DealGroupingSort.groupingSort(mContext,message);
                    break;
//                    修改群所在的分组推送
                case "modifyGroupOfListSend":
                    DealModifyGroupList.modifyGroupOfGroup(mContext,message);
                    break;
//                    扫码入群推送
                case "invitationQrCodeGroupListSend":
                    DealGroupInvitationQrCode.updateGroupDataByInvitation(mContext, message);
                    break;
//                    好友邀请入群推送
                case "invitationGroupListSend":
                    DealGroupInvitation.getDealGroupInvitation().updateGroupDataByInvitation(mContext, message);
                    break;
//                    好友修改Ta的信息
                case "updateFriendSend":
                    DealUpdateFriend.getDealUpdateFriend().updateFriend(mContext,message);
                    break;
//                    成员列表 变动 推送
                case "invitationfGroupMemberSend":
//                    DealUpdateFriend.getDealUpdateFriend().updateFriend(mContext,message);
                    break;
            }
        }
    }
    private static void dealOffLineGroupChat(String responseText) {
        DataOffLineGroupChat dataOffLineGroupChat = JSON.parseObject(responseText, DataOffLineGroupChat.class);
        DataOffLineGroupChat.RecordBean record = dataOffLineGroupChat.getRecord();
        if (record != null) {
            List<DataOffLineGroupChat.RecordBean.MessageListBean> messageList = record.getMessageList();
            if (messageList.size() > 0)
                for (int i = 0; i < messageList.size(); i++) {
                    initOffLineGroupChat(messageList.get(i));
                }
        }
    }
    private static  void initOffLineGroupChat(DataOffLineGroupChat.RecordBean.MessageListBean record) {
        AppConfig.CHAT_GROUP_ID = record.getGroupId();
        String Mytime = record.getRequestTime();
        String time = AppConfig.mCHAT_RECEIVE_TIME_REALM_GROUP;
        AppConfig.mCHAT_RECEIVE_TIME_REALM_GROUP = record.getRequestTime();
//        String time = (String) SPUtils.get(this, AppConfig.CHAT_RECEIVE_TIME_REALM_GROUP,"");
//        SPUtils.put(this, AppConfig.CHAT_RECEIVE_TIME_REALM_GROUP, (String)record.getRequestTime());
        if (!StrUtils.isEmpty(time)) {
            try {
                int i = TimeUtil.stringDaysBetween(record.getRequestTime(), time);
//                发送时间之间的间隔小于五分钟，则不显示时间
                if (MathUtils.abs(i) < 5) {
                    Mytime = "";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!SplitWeb.getSplitWeb().IS_CHAT_GROUP.equals("2")) {
//            不在聊天界面收到消息时候的提示
            noGroupChatUIOffLine(record);
        }
        CusGroupChatData groupChatData = new CusGroupChatData();
        groupChatData.setCreated(Mytime);
        groupChatData.setFriendId(record.getMemberId());
        groupChatData.setGroupId(record.getGroupId());
        groupChatData.setGroupUserId(record.getGroupId() + SplitWeb.getSplitWeb().getUserId());
        groupChatData.setImgHead(record.getMemberHeadImg());
        groupChatData.setImgGroup(record.getGroupHeadImg());
        groupChatData.setMessage(record.getMessage());
        groupChatData.setNameGroup(record.getGroupName());
        groupChatData.setNameFriend(record.getMemberName());
        groupChatData.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
        groupChatData.setUserMessageType(Constants.CHAT_ITEM_TYPE_LEFT);
        groupChatData.setMessageType(record.getMessageType());
        groupChatData.setMessageStoId(record.getMessageStoId());


        if (getRealmGroupChatHelper().isMessage(record.getMessageStoId())) {
            CusHomeRealmData homeRealmData = getRealmHomeHelper().queryAllRealmChat(record.getGroupId());
            String msg = record.getMessage();
            if (homeRealmData != null) {
                getRealmHomeHelper().updateGroupMsg(record.getGroupId(), msg, record.getRequestTime(), record);//更新首页聊天界面数据（消息和时间）
                getRealmHomeHelper().updateNum(record.getGroupId());//更新首页聊天界面数据（未读消息数目）
            } else {
                final CusHomeRealmData cusJumpChatData = new CusHomeRealmData();
                cusJumpChatData.setHeadImg(record.getGroupHeadImg());
                cusJumpChatData.setFriendId(record.getGroupId());
                cusJumpChatData.setNickName(record.getGroupName());
                cusJumpChatData.setMsg(msg);
                cusJumpChatData.setTime(record.getRequestTime());

                cusJumpChatData.setNum(0);
                cusJumpChatData.setMessageType(record.getMessageType());
                getRealmHomeHelper().addRealmMsgQun(cusJumpChatData);
            }
            //        发送广播更新首页
            EventBus.getDefault().post(new MsgHomeEvent(record.getMessage(), record.getGroupId(), AppConfig.MSG_ACTION_REFRESH));
        }
        getRealmGroupChatHelper().addRealmChat(groupChatData);//更新群聊聊天数据
    }
    private static  void noGroupChatUIOffLine(final DataOffLineGroupChat.RecordBean.MessageListBean record) {
        final CusJumpChatData cusJumpChatData = new CusJumpChatData();
        cusJumpChatData.setFriendHeader(record.getGroupHeadImg());
        cusJumpChatData.setFriendId(record.getGroupId());
        cusJumpChatData.setFriendName(record.getGroupName());
//在前台的时候处理接收到消息的事件
        if (SysRunUtils.isAppOnForeground(BaseApplication.getAppContext())) {
            try {
                ToastUtil.show("收到来自" + record.getGroupName() + "的一条新消息");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //APP在后台的时候处理接收到消息的事件
            new Thread(() -> {
                String msg = record.getMessage();
                if (!record.getMessageType().equals(Constants.CHAT_NOTICE)) {
                    msg = record.getMemberName() + "：" + record.getMessage();
                }
                try {
                    Bitmap  bitmap = Glide.with(mContext)
                            .load(record.getGroupHeadImg())
                            .asBitmap() //必须
                            .centerCrop()
                            .into(500, 500)
                            .get();
                    NotificationUtil notificationUtils = new NotificationUtil(mContext);
                    notificationUtils.sendNotification(cusJumpChatData, record.getGroupName(), msg, bitmap, AppConfig.TYPE_CHAT_QUN);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
    private static void dealOffLineChat(String responseText) {
        DataOffLineChat dataOffLineChat = JSON.parseObject(responseText, DataOffLineChat.class);

        DataOffLineChat.RecordBean record = dataOffLineChat.getRecord();
        if (record == null) {
            return;
        }
        List<DataOffLineChat.RecordBean.MessageListBean> messageList = record.getMessageList();
        if (messageList.size() > 0)
            for (int i = 0; i < messageList.size(); i++) {
                initListItem(messageList.get(i));
            }
    }
    private static void initListItem(final DataOffLineChat.RecordBean.MessageListBean record) {
        AppConfig.CHAT_FRIEND_ID = record.getFriendsId();

        record.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
        record.setType(Constants.CHAT_ITEM_TYPE_LEFT);
        CusChatData cusRealmChatMsg = new CusChatData();
//            String format = TimeUtil.sf.format(new Date());
//            cusRealmChatMsg.setCreated(format);
        String Mytime = record.getRequestTime();
        String time = AppConfig.mCHAT_RECEIVE_TIME_REALM;
        AppConfig.mCHAT_RECEIVE_TIME_REALM = record.getRequestTime();
//        String time = (String) SPUtils.get(this, AppConfig.CHAT_RECEIVE_TIME_REALM,"");
        if (!StrUtils.isEmpty(time)) {
            try {
                int i = TimeUtil.stringDaysBetween(record.getRequestTime(), time);
                if (MathUtils.abs(i) < 5) {
                    Mytime = "";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
//        SPUtils.put(this, AppConfig.CHAT_RECEIVE_TIME_REALM, (String) record.getRequestTime());
        if (!SplitWeb.getSplitWeb().IS_CHAT.equals("1"))
            dealList(record);
        cusRealmChatMsg.setCreated(Mytime);
        cusRealmChatMsg.setMessage(record.getMessage());
        cusRealmChatMsg.setMessageType(record.getMessageType());
        cusRealmChatMsg.setReceiveId(record.getFriendsId());
        cusRealmChatMsg.setSendId(record.getUserId());
        cusRealmChatMsg.setImgUrl(record.getHeadImg());
        cusRealmChatMsg.setUserMessageType(record.getType());
        cusRealmChatMsg.setMessageStoId(record.getMessageStoId());
        cusRealmChatMsg.setTotalId(record.getFriendsId() + SplitWeb.getSplitWeb().getUserId());


        if (getRealmChatHelper().isMessage(record.getMessageStoId())) {
            CusHomeRealmData homeRealmData = getRealmHomeHelper().queryAllRealmChat(record.getFriendsId());
            if (homeRealmData != null) {
//            getRealmHomeHelper().updateMsg(record.getFriendsId(), record.getMessage(), record.getRequestTime());//更新首页聊天界面数据（消息和时间）
                getRealmHomeHelper().updateMsg(record.getFriendsId(), record.getMessage(), record.getRequestTime(), record.getShieldType(), record.getDisturbType(), record.getTopType(), record.getMessageType());//更新首页聊天界面数据（消息和时间）
                getRealmHomeHelper().updateNum(record.getFriendsId());//更新首页聊天界面数据（未读消息数目）
            } else {
                final CusHomeRealmData cusJumpChatData = new CusHomeRealmData();
                cusJumpChatData.setHeadImg(record.getHeadImg());
                cusJumpChatData.setFriendId(record.getFriendsId());
                cusJumpChatData.setNickName(record.getNickName());
                cusJumpChatData.setMsg(record.getMessage());
                cusJumpChatData.setTime(record.getRequestTime());
                cusJumpChatData.setNum(0);
//            realmHelper.updateNum(record.getFriendsId());
                getRealmHomeHelper().addRealmMsg(cusJumpChatData);
                EventBus.getDefault().post(new MsgHomeEvent(record.getMessage(), record.getFriendsId(), AppConfig.MSG_ACTION_REFRESH));
            }
        }

        getRealmChatHelper().addRealmChat(cusRealmChatMsg);//更新聊天数据


    }
    private static void dealList(final DataOffLineChat.RecordBean.MessageListBean record) {
        final CusJumpChatData cusJumpChatData = new CusJumpChatData();
        cusJumpChatData.setFriendHeader(record.getHeadImg());
        cusJumpChatData.setFriendId(record.getFriendsId());
        cusJumpChatData.setFriendName(record.getNickName());

//        发送广播更新首页
        EventBus.getDefault().post(new MsgHomeEvent(record.getMessage(),record.getFriendsId(),AppConfig.MSG_ACTION_REFRESH));
//在前台的时候处理接收到消息的事件
        if (SysRunUtils.isAppOnForeground(BaseApplication.getAppContext())) {
            try {
                ToastUtil.show("收到来自" + record.getNickName() + "的一条新消息");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //APP在后台的时候处理接收到消息的事件
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Bitmap bitmap = Glide.with(mContext)
                                .load(record.getHeadImg())
                                .asBitmap() //必须
                                .centerCrop()
                                .into(500, 500)
                                .get();
                        NotificationUtil notificationUtils = new NotificationUtil(mContext);
                        notificationUtils.sendNotification(cusJumpChatData, record.getNickName(), record.getMessage(), bitmap, AppConfig.TYPE_CHAT);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private static void dealAgreeFriend(String responseText) {
        DataAgreeFriend dataAgreeFriend = JSON.parseObject(responseText, DataAgreeFriend.class);
        DataAgreeFriend.RecordBean record = dataAgreeFriend.getRecord();
        if (record != null) {
//            TODO   发送 我们已经是好友了，快来聊一聊吧
//            sendText(SplitWeb.getSplitWeb().privateSend(record.getFriendsId(), "我们已经是好友了，快来聊一聊吧", ChatActivity.messageType, TimeUtil.getTime()));

            final CusHomeRealmData cusJumpChatData = new CusHomeRealmData();
            cusJumpChatData.setHeadImg(record.getHeadImg());
            cusJumpChatData.setFriendId(record.getFriendsId());
            cusJumpChatData.setNickName(record.getNickName());
            cusJumpChatData.setMsg("我们已经是好友了，快来聊一聊吧");
            cusJumpChatData.setTime(TimeUtil.getTime());
            cusJumpChatData.setNum(0);
            getRealmHomeHelper().addRealmMsg(cusJumpChatData);
            EventBus.getDefault().post(new MsgHomeEvent("我们已经是好友了，快来聊一聊吧",record.getFriendsId(),AppConfig.MSG_ADD_REFRESH));
        }
    }
    private static  void dealFriendPush(String responseText) {
        DataFriendPush dataFriendPush = JSON.parseObject(responseText, DataFriendPush.class);
        final DataFriendPush.RecordBean record = dataFriendPush.getRecord();
        if (record == null) {
            return;
        }
        final List<DataFriendPush.RecordBean.MessageListBean> messageList = record.getMessageList();
        for (int i = 0; i < messageList.size(); i++) {
            int num = (int) SPUtils.get(mContext, AppConfig.LINKMAN_FRIEND_NUM, 0);
            SPUtils.put(mContext, AppConfig.LINKMAN_FRIEND_NUM, num + 1);
            EventBus.getDefault().post(new MainTabNumEvent((num+1),AppConfig.MAIN_TAB_TWO));
            final DataFriendPush.RecordBean.MessageListBean messageListBean = messageList.get(i);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Bitmap bitmap = Glide.with(mContext)
                                .load(messageListBean.getHeadImg())
                                .asBitmap() //必须
                                .centerCrop()
                                .into(500, 500)
                                .get();
                        NotificationUtil notificationUtils = new NotificationUtil(mContext);
                        String remark = (!StrUtils.isEmpty(messageListBean.getRemark())) ? messageListBean.getRemark() : "没有备注";
                        notificationUtils.sendNotification(messageListBean.getNickName() + "加您为好友", "备注：" + remark, bitmap, AppConfig.TYPE_NOTICE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
    private static  void CGS(String responseText) {
        DataCreatGroupResult dataCreatGroupResult = JSON.parseObject(responseText, DataCreatGroupResult.class);
        DataCreatGroupResult.RecordBean record = dataCreatGroupResult.getRecord();
        if (record != null) {
            final CusHomeRealmData cusJumpChatData = new CusHomeRealmData();
            cusJumpChatData.setHeadImg(record.getGroupHeadImg());
            cusJumpChatData.setFriendId(record.getGroupOfId());
            cusJumpChatData.setNickName(record.getGroupNickName());
            cusJumpChatData.setMsg("我新建了一个群");
            cusJumpChatData.setTime(TimeUtil.getTime());
            cusJumpChatData.setNum(0);
//            cusJumpChatData.setType(RealmHomeHelper.TypeQun);
            getRealmHomeHelper().addRealmMsgQun(cusJumpChatData);
            EventBus.getDefault().post(new MsgHomeEvent("我新建了一个群",record.getGroupOfId(),AppConfig.MSG_ACTION_REFRESH));
        }
    }
    private static void dealFriend(String responseText) {
        DataAddfriendSendRequest dataAddfriendSendRequest = JSON.parseObject(responseText, DataAddfriendSendRequest.class);
        DataAddfriendSendRequest.RecordBean record = dataAddfriendSendRequest.getRecord();
        int num = (int) SPUtils.get(mContext, AppConfig.LINKMAN_FRIEND_NUM, 0);
        SPUtils.put(mContext, AppConfig.LINKMAN_FRIEND_NUM, num + 1);
        EventBus.getDefault().post(new MainTabNumEvent((num+1),AppConfig.MAIN_TAB_TWO));

    }
    private static void initMsgSend(DataJieShou.RecordBean record) {
        getRealmHomeHelper().updateMsg(record.getFriendsId(), record.getMessage(), record.getRequestTime(),record.getShieldType(),record.getDisturbType(),record.getTopType(),record.getMessageType());//更新首页聊天界面数据（消息和时间）
        EventBus.getDefault().post(new MsgHomeEvent(record.getMessage(),record.getFriendsId(),AppConfig.MSG_ACTION_REFRESH));
//        getRealmHomeHelper().updateMsg(record.getFriendsId(), record.getMessage(), record.getRequestTime());//更新首页聊天界面数据（消息和时间）
    }
    private static void dealSend(String message) {
        DataJieShou dataJieShou = JSON.parseObject(message, DataJieShou.class);
        DataJieShou.RecordBean record = dataJieShou.getRecord();
        if (record != null) {
            record.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
            record.setType(Constants.CHAT_ITEM_TYPE_RIGHT);

            initMsgSend(record);//发布广播更新首页的信息

            CusChatData cusRealmChatMsg = new CusChatData();
            String myTime = record.getRequestTime();
            String time = AppConfig.mCHAT_SEND_TIME_REALM;
            AppConfig.mCHAT_SEND_TIME_REALM = record.getRequestTime();
//            String time = (String) SPUtils.get(this, AppConfig.CHAT_SEND_TIME_REALM,"");
//            SPUtils.put(this, AppConfig.CHAT_SEND_TIME_REALM, (String)record.getRequestTime());
            if (!StrUtils.isEmpty(time)) {
                try {
                    int i = TimeUtil.stringDaysBetween(myTime, time);
                    if (MathUtils.abs(i) < 5) {
                        myTime = "";
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            cusRealmChatMsg.setCreated(myTime);
//            cusRealmChatMsg.setCreated(TimeUtil.sf.format(new Date()));
            cusRealmChatMsg.setMessage(record.getMessage());
            cusRealmChatMsg.setMessageType(record.getMessageType());
            cusRealmChatMsg.setReceiveId(record.getFriendsId());
            cusRealmChatMsg.setSendId(record.getUserId());
            cusRealmChatMsg.setUserMessageType(record.getType());
            cusRealmChatMsg.setMessageStoId(record.getMessageStoId());
            cusRealmChatMsg.setTotalId(record.getFriendsId() + SplitWeb.getSplitWeb().getUserId());
            getRealmChatHelper().addRealmChat(cusRealmChatMsg);//更新聊天数据

        }
    }
    private static void initMsgGroupSend(DataGroupChatSend.RecordBean record) {
        getRealmHomeHelper().updateGroupMsg(record.getGroupId(), record.getMessage(), record.getRequestTime(),record);//更新首页聊天界面数据（消息和时间）
        EventBus.getDefault().post(new MsgHomeEvent(record.getMessage(),record.getGroupId(),AppConfig.MSG_ACTION_REFRESH));
//        getRealmHomeHelper().updateMsg(record.getGroupId(), record.getMessage(), record.getRequestTime(),record.getShieldType(),record.getDisturbType(),record.getTopType());//更新首页聊天界面数据（消息和时间）
    }
    private static void dealGroupSend(String message) {
        DataGroupChatSend dataGroupSend = JSON.parseObject(message, DataGroupChatSend.class);
        DataGroupChatSend.RecordBean record = dataGroupSend.getRecord();
        if (record != null) {
            initMsgGroupSend(record);//发布广播更新首页的信息
            CusGroupChatData cusRealmChatMsg = new CusGroupChatData();
            String MyTime = record.getRequestTime();

            String time = AppConfig.mCHAT_SEND_TIME_REALM_GROUP;
            AppConfig.mCHAT_SEND_TIME_REALM_GROUP = record.getRequestTime();
//            String time = (String) SPUtils.get(this, AppConfig.CHAT_SEND_TIME_REALM_GROUP,"");
            if (!StrUtils.isEmpty(time)) {
                try {
                    int i = TimeUtil.stringDaysBetween(record.getRequestTime(), time);
                    if (MathUtils.abs(i) < 5) {
                        MyTime = "";
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
//            SPUtils.put(this, AppConfig.CHAT_SEND_TIME_REALM_GROUP,record.getRequestTime());
            cusRealmChatMsg.setCreated(MyTime);
//            cusRealmChatMsg.setCreated(TimeUtil.sf.format(new Date()));
            cusRealmChatMsg.setMessage(record.getMessage());
            cusRealmChatMsg.setMessageType(record.getMessageType());
            cusRealmChatMsg.setGroupId(record.getGroupId());
            cusRealmChatMsg.setGroupUserId(record.getGroupId() + SplitWeb.getSplitWeb().getUserId());
//            cusRealmChatMsg.setSendId(record.getMemberId());
            cusRealmChatMsg.setNameFriend("我");
            cusRealmChatMsg.setUserMessageType(Constants.CHAT_ITEM_TYPE_RIGHT);
            cusRealmChatMsg.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
            cusRealmChatMsg.setMessageStoId(record.getMessageStoId());
            getRealmGroupChatHelper().addRealmChat(cusRealmChatMsg);//更新群聊聊天数据

//            CusHomeRealmData homeRealmData = getRealmHomeHelper().queryAllRealmChat(record.getGroupId());
//            String msg = record.getMessage();
//        if (!record.getMessageType().equals(Constants.CHAT_NOTICE)) {
//            msg = record.getMemberName() + "：" + record.getMessage();
//        }
//            if (homeRealmData != null) {
//                getRealmHomeHelper().updateGroupMsg(record.getGroupId(), msg, record.getRequestTime(),record);//更新首页聊天界面数据（消息和时间）
//                getRealmHomeHelper().updateNum(record.getGroupId());//更新首页聊天界面数据（未读消息数目）
//            } else {
//                final CusHomeRealmData cusJumpChatData = new CusHomeRealmData();
//                cusJumpChatData.setFriendId(record.getGroupId());
//                cusJumpChatData.setMsg(msg);
//                cusJumpChatData.setTime(record.getRequestTime());
//                cusJumpChatData.setMemberId(record.getMemberId());
//                cusJumpChatData.setMsgIsDisTurb(record.getDisturbType());
//                cusJumpChatData.setTopType(record.getTopType());
//                cusJumpChatData.setOperationType(record.getOperationType());
//                cusJumpChatData.setBannedType(record.getBannedType());
//                cusJumpChatData.setAssistantType(record.getAssistantType());
//                cusJumpChatData.setNum(0);
////            getRealmHomeHelper().updateNum(record.getFriendsId());
//                getRealmHomeHelper().addRealmMsgQun(cusJumpChatData);
//            }


            noChatUI(record.getMessage(), record.getGroupId());
//            getRealmHomeHelper()getRealmHomeHelper().updateMsg(record.getGroupId(),record.getMessage(),record.getRequestTime());
//            realmHelper.updateNum(record.getGroupId());//更新首页聊天界面数据（未读消息数目）
        }
    }

    private static  void dealReceiver(String message) {
        CusChatData cusRealmChatMsg = null;
        CusHomeRealmData cusJumpChatData = null;
        DataJieShou dataJieShou = JSON.parseObject(message, DataJieShou.class);
        final DataJieShou.RecordBean record = dataJieShou.getRecord();
        if (record == null) {
            return;
        }
        if ( record.getShieldType().equals("2"))
            return;
        AppConfig.CHAT_FRIEND_ID = record.getFriendsId();
        record.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
        record.setType(Constants.CHAT_ITEM_TYPE_LEFT);
//        initMsgSend(record);
        if (cusRealmChatMsg == null)
            cusRealmChatMsg = new CusChatData();
//            String format = TimeUtil.sf.format(new Date());
//            cusRealmChatMsg.setCreated(format);
        String Mytime = record.getRequestTime();
        String time = AppConfig.mCHAT_RECEIVE_TIME_REALM;
        AppConfig.mCHAT_RECEIVE_TIME_REALM = record.getRequestTime();
//        String time = (String) SPUtils.get(this, AppConfig.CHAT_RECEIVE_TIME_REALM,"");
        if (!StrUtils.isEmpty(time)) {
            try {
                int i = TimeUtil.stringDaysBetween(record.getRequestTime(), time);
                if (MathUtils.abs(i) < 5) {
//                    record.setRequestTime("");
                    Mytime = "";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
//        SPUtils.put(this,AppConfig.CHAT_RECEIVE_TIME_REALM,record.getRequestTime());
//        if (!SplitWeb.getSplitWeb().IS_CHAT.equals("1")) {
////            不在聊天界面收到消息时候的处理
//            noChatUI(record.getMessage(),record.getFriendsId());
//        }
        if (!record.getDisturbType().equals("2")) {
            if (record.getDisturbType().equals("1"))
                xipinhuanxing(record);
        }
        cusRealmChatMsg.setCreated(Mytime);
        cusRealmChatMsg.setMessage(record.getMessage());
        cusRealmChatMsg.setMessageType(record.getMessageType());
        cusRealmChatMsg.setReceiveId(record.getFriendsId());
        cusRealmChatMsg.setSendId(record.getUserId());
        cusRealmChatMsg.setUserMessageType(record.getType());
        cusRealmChatMsg.setImgUrl(record.getHeadImg());
        cusRealmChatMsg.setMessageStoId(record.getMessageStoId());
        cusRealmChatMsg.setTotalId(record.getFriendsId() + SplitWeb.getSplitWeb().getUserId());
        getRealmChatHelper().addRealmChat(cusRealmChatMsg);//更新聊天数据

        CusHomeRealmData homeRealmData = getRealmHomeHelper().queryAllRealmChat(record.getFriendsId());

        if (homeRealmData != null) {
            getRealmHomeHelper().updateMsg(record.getFriendsId(), record.getMessage(), record.getRequestTime(),record.getShieldType(),record.getDisturbType(),record.getTopType(),record.getMessageType());//更新首页聊天界面数据（消息和时间）
            if (SplitWeb.getSplitWeb().IS_CHAT_Zero)
                getRealmHomeHelper().updateNum(record.getFriendsId(),record.getFriendsName());//更新首页聊天界面数据（未读消息数目）
            else {
                getRealmHomeHelper().updateNumZero(record.getFriendsId(),record.getFriendsName());//更新首页聊天界面数据（未读消息数目）
            }
        } else {
            if (cusJumpChatData == null)
                cusJumpChatData = new CusHomeRealmData();
            cusJumpChatData.setHeadImg(record.getHeadImg());
            cusJumpChatData.setFriendId(record.getFriendsId());
            cusJumpChatData.setNickName(record.getFriendsName());
            cusJumpChatData.setMsg(record.getMessage());
            cusJumpChatData.setIsShield(record.getShieldType());
            cusJumpChatData.setIsTopMsg(record.getTopType());
            cusJumpChatData.setMsgIsDisTurb(record.getDisturbType());
            cusJumpChatData.setTime(record.getRequestTime());
            cusJumpChatData.setNum(0);
            cusJumpChatData.setMessageType(record.getMessageType());
            getRealmHomeHelper().addRealmMsg(cusJumpChatData);
        }
        noChatUI(record.getMessage(),record.getFriendsId());
    }
    //息屏唤醒
    private static void xipinhuanxing(final DataJieShou.RecordBean record) {
        final CusJumpChatData cusJumpChatData = new CusJumpChatData();
        cusJumpChatData.setFriendHeader(record.getHeadImg());
        cusJumpChatData.setFriendId(record.getFriendsId());
        cusJumpChatData.setFriendName(record.getFriendsName());
        PowerManager pm = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
        boolean screenOn = pm.isScreenOn();
        if (!screenOn) {
            // 获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
            @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "bright");
            wl.acquire(10000); // 点亮屏幕
            wl.release(); // 释放
        }
//        if (!SplitWeb.getSplitWeb().IS_CHAT.equals("1"))
        if (!SysRunUtils.isAppOnForeground(BaseApplication.getAppContext())||!SplitWeb.getSplitWeb().IS_CHAT.equals("1"))
            //APP在后台的时候处理接收到消息的事件
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Bitmap bitmap = Glide.with(BaseApplication.getAppContext())
                                .load(record.getHeadImg())
                                .asBitmap() //必须
                                .centerCrop()
                                .into(500, 500)
                                .get();
                        NotificationUtil notificationUtils = new NotificationUtil(mContext);
                        notificationUtils.sendNotification(cusJumpChatData, record.getFriendsName(), record.getMessage(), bitmap, AppConfig.TYPE_CHAT_QUN);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
    }
    private static void noChatUI(String msg,String id) {
//        发送广播更新首页
        EventBus.getDefault().post(new MsgHomeEvent(msg,id,AppConfig.MSG_ACTION_REFRESH));
    }
    private static void initGroupReceiveData(String responseText) {
        DataGroupChatResult dataGroupChat = JSON.parseObject(responseText, DataGroupChatResult.class);
        final DataGroupChatResult.RecordBean record = dataGroupChat.getRecord();
        if (record == null)
            return;
        if ( record.getOperationType().equals("2"))
            return;
        AppConfig.CHAT_GROUP_ID = record.getGroupId();
        String Mytime = record.getRequestTime();

        String time = AppConfig.mCHAT_RECEIVE_TIME_REALM_GROUP;
        AppConfig.mCHAT_RECEIVE_TIME_REALM_GROUP = record.getRequestTime();
//        String time = (String) SPUtils.get(this, AppConfig.CHAT_RECEIVE_TIME_REALM_GROUP,"");
        if (!StrUtils.isEmpty(time)) {
            try {
                int i = TimeUtil.stringDaysBetween(record.getRequestTime(), time);
//                发送时间之间的间隔小于五分钟，则不显示时间
                if (MathUtils.abs(i) < 5) {
//                    record.setRequestTime("");
                    Mytime = "";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //通知类型
        if (record.getMessageType().equals(Constants.CHAT_NOTICE)) {
            Mytime = "";
            AppConfig.mCHAT_RECEIVE_TIME_REALM_GROUP = "";
        }

        CusGroupChatData groupChatData = new CusGroupChatData();
        groupChatData.setCreated(Mytime);
        groupChatData.setFriendId(record.getMemberId());
        groupChatData.setGroupId(record.getGroupId());
        groupChatData.setGroupUserId(record.getGroupId() + SplitWeb.getSplitWeb().getUserId());
        groupChatData.setImgHead(record.getMemberHeadImg());
        groupChatData.setImgGroup(record.getGroupHeadImg());
        groupChatData.setMessage(record.getMessage());
        groupChatData.setNameGroup(record.getGroupName());
        groupChatData.setNameFriend(record.getMemberName());
        groupChatData.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
        groupChatData.setUserMessageType(Constants.CHAT_ITEM_TYPE_LEFT);
        groupChatData.setMessageType(record.getMessageType());

        groupChatData.setDisturbType(record.getDisturbType());
        groupChatData.setTopType(record.getTopType());
        groupChatData.setOperationType(record.getOperationType());
        groupChatData.setBannedType(record.getBannedType());
        groupChatData.setAssistantType("1");
        groupChatData.setMessageStoId(record.getMessageStoId());
        getRealmGroupChatHelper().addRealmChat(groupChatData);//更新群聊聊天数据
        MyLog.e("realmGroupChatHelper", "msg=" + record.getMessage());
        CusHomeRealmData homeRealmData = getRealmHomeHelper().queryAllRealmChat(record.getGroupId());
        String msg = record.getMessage();
//        if (!record.getMessageType().equals(Constants.CHAT_NOTICE)) {
//            msg = record.getMemberName() + "：" + record.getMessage();
//        }
        if (homeRealmData != null) {
            getRealmHomeHelper().updateGroupMsg(record.getGroupId(), msg, record.getRequestTime(),record);//更新首页聊天界面数据（消息和时间）
            getRealmHomeHelper().updateNum(record.getGroupId());//更新首页聊天界面数据（未读消息数目）
        } else {
            final CusHomeRealmData cusJumpChatData = new CusHomeRealmData();
            cusJumpChatData.setHeadImg(record.getGroupHeadImg());
            cusJumpChatData.setFriendId(record.getGroupId());
            cusJumpChatData.setNickName(record.getGroupName());
            cusJumpChatData.setMsg(msg);
            cusJumpChatData.setTime(record.getRequestTime());
            cusJumpChatData.setMemberId(record.getMemberId());
            cusJumpChatData.setMsgIsDisTurb(record.getDisturbType());
            cusJumpChatData.setTopType(record.getTopType());
            cusJumpChatData.setOperationType(record.getOperationType());
            cusJumpChatData.setBannedType(record.getBannedType());
            cusJumpChatData.setAssistantType(record.getAssistantType());
            cusJumpChatData.setNum(0);
//            getRealmHomeHelper().updateNum(record.getFriendsId());
            getRealmHomeHelper().addRealmMsgQun(cusJumpChatData);
        }
        String assistantType1 = record.getAssistantType();
        if (assistantType1!=null&&assistantType1.equals("2"))
        {
//            TODO 群助手
//            initAss();
        }else {
            if (!SplitWeb.getSplitWeb().IS_CHAT_GROUP.equals("2")) {
//            不在聊天界面收到消息时候的处理
                if (record.getDisturbType().equals("1"))
                    setGroupNotify(record);
            }
            noChatUI(record.getMessage(), record.getGroupId());
        }
    }
    private static void setGroupNotify(final DataGroupChatResult.RecordBean record) {
        final CusJumpChatData cusJumpChatData = new CusJumpChatData();
        cusJumpChatData.setFriendHeader(record.getGroupHeadImg());
        cusJumpChatData.setFriendId(record.getGroupId());
        cusJumpChatData.setFriendName(record.getGroupName());
        PowerManager pm = (PowerManager)mContext.getSystemService(Context.POWER_SERVICE);
        boolean screenOn = pm.isScreenOn();
        if (!screenOn) {
            // 获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
            @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "bright");
            wl.acquire(10000); // 点亮屏幕
            wl.release(); // 释放
        }
//        if (!SysRunUtils.isAppOnForeground(MyApplication.getAppContext()))
        //APP在后台的时候处理接收到消息的事件
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String msg = record.getMessage();
                    if (!record.getMessageType().equals(Constants.CHAT_NOTICE)) {
                        msg = record.getMemberName() + "：" + record.getMessage();
                    }
                    Bitmap  bitmap = Glide.with(mContext)
                            .load(record.getGroupHeadImg())
                            .asBitmap() //必须
                            .centerCrop()
                            .into(500, 500)
                            .get();
                    NotificationUtil notificationUtils = new NotificationUtil(mContext);
                    notificationUtils.sendNotification(cusJumpChatData, record.getGroupName(), msg, bitmap, AppConfig.TYPE_CHAT_QUN);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
