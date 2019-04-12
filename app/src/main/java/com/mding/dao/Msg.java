package com.mding.dao;

import com.mding.sql.TotalEntry;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;

/**
 * 项目：DoubleQ
 * 文件描述：消息表信息
 * 作者：zll
 * 创建时间：2019/4/2 0002
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
@Entity(
        indexes = {
                @Index(value = "userId DESC, friendId DESC", unique = true)
        }
)
public class Msg {
  //  private static final String TEXT_TYPE = "TEXT";
//  private static final String COMMA_SEP = ",";
////首页消息表名
//  public static String TABLE_NAME ="home_msg";
  //        综合组件

    @Id(autoincrement = true)
    Long id;
    String userId;
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


@Generated(hash = 517207264)
public Msg(Long id, String userId, String friendId, String type, String nickName,
        String headImg, String time, int num, String msg, String isShield,
        String bannedType, String assistantType, String operationType, String topType,
        String groupNumMsg) {
    this.id = id;
    this.userId = userId;
    this.friendId = friendId;
    this.type = type;
    this.nickName = nickName;
    this.headImg = headImg;
    this.time = time;
    this.num = num;
    this.msg = msg;
    this.isShield = isShield;
    this.bannedType = bannedType;
    this.assistantType = assistantType;
    this.operationType = operationType;
    this.topType = topType;
    this.groupNumMsg = groupNumMsg;
}


@Generated(hash = 23037457)
public Msg() {
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


public Long getId() {
    return this.id;
}


public void setId(Long id) {
    this.id = id;
}


public String getUserId() {
    return this.userId;
}


public void setUserId(String userId) {
    this.userId = userId;
}


public String getFriendId() {
    return this.friendId;
}


public void setFriendId(String friendId) {
    this.friendId = friendId;
}


public String getType() {
    return this.type;
}


public void setType(String type) {
    this.type = type;
}


public String getNickName() {
    return this.nickName;
}


public void setNickName(String nickName) {
    this.nickName = nickName;
}


public String getHeadImg() {
    return this.headImg;
}


public void setHeadImg(String headImg) {
    this.headImg = headImg;
}


public String getTime() {
    return this.time;
}


public void setTime(String time) {
    this.time = time;
}


public int getNum() {
    return this.num;
}


public void setNum(int num) {
    this.num = num;
}


public String getMsg() {
    return this.msg;
}


public void setMsg(String msg) {
    this.msg = msg;
}


public String getIsShield() {
    return this.isShield;
}


public void setIsShield(String isShield) {
    this.isShield = isShield;
}


public String getBannedType() {
    return this.bannedType;
}


public void setBannedType(String bannedType) {
    this.bannedType = bannedType;
}


public String getAssistantType() {
    return this.assistantType;
}


public void setAssistantType(String assistantType) {
    this.assistantType = assistantType;
}


public String getOperationType() {
    return this.operationType;
}


public void setOperationType(String operationType) {
    this.operationType = operationType;
}


public String getTopType() {
    return this.topType;
}


public void setTopType(String topType) {
    this.topType = topType;
}


public String getGroupNumMsg() {
    return this.groupNumMsg;
}


public void setGroupNumMsg(String groupNumMsg) {
    this.groupNumMsg = groupNumMsg;
}
}
