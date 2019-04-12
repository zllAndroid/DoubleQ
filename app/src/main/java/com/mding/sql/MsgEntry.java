package com.mding.sql;

import io.realm.annotations.PrimaryKey;

/**
 * 项目：DoubleQ
 * 文件描述：消息表信息
 * 作者：zll
 * 创建时间：2019/4/2 0002
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class MsgEntry {
  //  private static final String TEXT_TYPE = "TEXT";
//  private static final String COMMA_SEP = ",";
////首页消息表名
//  public static String TABLE_NAME ="home_msg";
  public static String _USER_ID ="userId";
  String friendId;//好友id
  //    消息类型  1 私聊   ，2群聊
  String type;

  //  String userid;
  //    昵称
  String nickName;
  //    头像
  String headImg;
  //    消息时间
  String time;
  //    消息数量
  int num;
  //    消息
  String msg;
  //    是否置顶   1否2是
  String isShield;

  private String bannedType;//是否被禁言

  private String assistantType;//加入群助手

  private String operationType;//屏蔽该群
  private String topType;//置顶状态


  private String groupNumMsg;//有几个群有新消息

  public static String getUserId() {
    return _USER_ID;
  }

  public static void setUserId(String UserId) {
    _USER_ID = UserId;
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

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getIsShield() {
    return isShield;
  }

  public void setIsShield(String isShield) {
    this.isShield = isShield;
  }

  public String getBannedType() {
    return bannedType;
  }

  public void setBannedType(String bannedType) {
    this.bannedType = bannedType;
  }

  public String getAssistantType() {
    return assistantType;
  }

  public void setAssistantType(String assistantType) {
    this.assistantType = assistantType;
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

  public String getGroupNumMsg() {
    return groupNumMsg;
  }

  public void setGroupNumMsg(String groupNumMsg) {
    this.groupNumMsg = groupNumMsg;
  }

  public static String toMsgEntryString(){
    return
                    "friendId" +  TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                    "type" +  TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                    "nickName" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
                    "headImg" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
                    "time" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
                    "num" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
                    "msg" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
                    "isShield" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
                    "bannedType" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
                    "assistantType" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP   +
                    "operationType" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                    "topType" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
                    "groupNumMsg" + TotalEntry.TEXT_TYPE   ;
  }
//  @Override
//  public String toString() {
//    return
//            "friendId" +  TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
//            "type" +  TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
//            "nickName" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
//            "headImg" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
//            "time" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
//            "num" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
//            "msg" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
//            "isShield" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
//            "bannedType" +  TotalEntry.TEXT_TYPE +  TotalEntry.COMMA_SEP  +
//            "assistantType" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP   +
//            "operationType" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
//            "topType" + TotalEntry.TEXT_TYPE + TotalEntry.COMMA_SEP  +
//            "groupNumMsg" + TotalEntry.TEXT_TYPE   ;
//  }
}
