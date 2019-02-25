package com.doubleq.xm6leefunz.about_utils.about_realm.new_home;

import com.doubleq.xm6leefunz.about_utils.about_realm.realm_data.CusRealmChatMsg;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Administrator on 2018/2/8 0008.
 */

public  class CusHomeRealmData extends RealmObject {


    /**
     * "type": 1,

     "friendId": "1",

     "nickName": "小强",

     "headImg": "http:\/\/thirdwx.qlogo.cn\/mmopen\/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w\/132"
     */

    @PrimaryKey
    String totalId;//共同id（由朋友id和用户id合并组成）

    String friendId;//好友id
    //    消息类型  1 私聊   ，2群聊
    String type;

    String userid;
    //    昵称
    String nickName;
    //    头像
    String headImg;
    //是否消息免打扰   1否2是
    String MsgIsDisTurb;
    //    消息时间
    String time;
    //    消息数量
    int num;
//    消息
    String msg;

//    是否置顶   1否2是
    String isTopMsg;
//    是否置顶   1否2是
    String isShield;

    private String bannedType;//是否被禁言

    private String assistantType;//加入群助手

    private String operationType;//屏蔽该群
    private String topType;//置顶状态


    private String groupNumMsg;//有几个群有新消息

    public String getGroupNumMsg() {
        return groupNumMsg;
    }

    public void setGroupNumMsg(String groupNumMsg) {
        this.groupNumMsg = groupNumMsg;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getTopType() {
        return topType;
    }

    public void setTopType(String topType) {
        this.topType = topType;
    }

    public String getAssistantType() {
        return assistantType;
    }

    public void setAssistantType(String assistantType) {
        this.assistantType = assistantType;
    }

    public String getBannedType() {
        return bannedType;
    }

    public void setBannedType(String bannedType) {
        this.bannedType = bannedType;
    }

    public String getIsShield() {
        return isShield;
    }

    public void setIsShield(String isShield) {
        this.isShield = isShield;
    }

    public String getIsTopMsg() {
        return isTopMsg;
    }

    public void setIsTopMsg(String isTopMsg) {
        this.isTopMsg = isTopMsg;
    }

    public RealmList<CusChatData> chatMsgs;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTotalId() {
        return totalId;
    }

    public void setTotalId(String totalId) {
        this.totalId = totalId;
    }

    public RealmList<CusChatData> getChatMsgs() {
        return chatMsgs;
    }

    public void setChatMsgs(RealmList<CusChatData> chatMsgs) {
        this.chatMsgs = chatMsgs;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getMsgIsDisTurb() {
        return MsgIsDisTurb;
    }

    public void setMsgIsDisTurb(String msgIsDisTurb) {
        MsgIsDisTurb = msgIsDisTurb;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
