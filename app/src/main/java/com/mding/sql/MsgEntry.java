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
  public static String _USER_ID ="userid";
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
