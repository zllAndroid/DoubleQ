package com.mding.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mding.dao.UserRelation;

/**
 * 项目：DoubleQ
 * 文件描述：
 * 作者：zll
 * 创建时间：2019/4/2 0002
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class DBgreatTable extends SQLiteOpenHelper {
//    首页消息
    private static final String MSGSQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + TotalEntry.TABLE_NAME_Msg + " (" +
                    TotalEntry._USER_ID + " INTEGER PRIMARY KEY," +
                    MsgEntry.toMsgEntryString()+ " )";
//    私聊
    private static final String PrivateChatSQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + TotalEntry.TABLE_NAME_Private_chat + " (" +
                    TotalEntry._USER_ID + " INTEGER PRIMARY KEY," +
                    MsgEntry.toMsgEntryString()+ " )";
//    好友关系
//    private static final String userRelationSQL_CREATE_ENTRIES =
//            "CREATE TABLE IF NOT EXISTS " + TotalEntry.TABLE_NAME_user_relation + " (" +
//                    TotalEntry._USER_ID + " INTEGER PRIMARY KEY," +
//                    UserRelation.toUserRelationEntryString()+ " )";
//    群用户关系
    private static final String groupUserRelationSQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + TotalEntry.TABLE_NAME_group_user_relation + " (" +
                    TotalEntry._GROUP_ID + " INTEGER PRIMARY KEY," +
                    GroupUserRelation.toGroupUserRelationString()+ " )";
//    群信息关系
    private static final String groupInfoSQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + TotalEntry.TABLE_NAME_group_info + " (" +
                    TotalEntry._GROUP_ID + " INTEGER PRIMARY KEY," +
                    GroupInfo.toGroupInfoString()+ " )";

    private static final String TIMESQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TotalEntry.TABLE_NAME_Msg;
    private static final String STUSSQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TotalEntry.TABLE_NAME_Private_chat;
    private static final String BODYSQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TotalEntry.TABLE_NAME_user_relation;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "mding.db";

    public DBgreatTable(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MSGSQL_CREATE_ENTRIES);//消息表
        db.execSQL(PrivateChatSQL_CREATE_ENTRIES);//聊天数据表
//        db.execSQL(userRelationSQL_CREATE_ENTRIES);//好友关系表
        db.execSQL(groupUserRelationSQL_CREATE_ENTRIES);//群用户关系表
        db.execSQL(groupInfoSQL_CREATE_ENTRIES);//群信息表
//        db.execSQL(BODYSQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(TIMESQL_DELETE_ENTRIES);
        db.execSQL(STUSSQL_DELETE_ENTRIES);
        db.execSQL(BODYSQL_DELETE_ENTRIES);
        onCreate(db);
    }
//    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        onUpgrade(db, oldVersion, newVersion);
//    }
}
