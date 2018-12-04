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
    //    昵称
    String nickName;
    //    头像
    String headImg;
    //是否消息免打扰
    String MsgIsDisTurb;
    //    消息时间
    String time;
    //    消息数量
    int num;
//    消息
    String msg;

    public RealmList<CusChatData> chatMsgs;

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
