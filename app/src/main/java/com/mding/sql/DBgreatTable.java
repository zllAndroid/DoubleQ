package com.mding.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    // If you change the database schema, you must increment the database version.

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
//首页消息表
//    private static final String TIMESQL_CREATE_ENTRIES =
//            "CREATE TABLE " + MsgEntry.TABLE_NAME + " (" +
//                    MsgEntry._USER_ID + " INTEGER PRIMARY KEY," +
//                    MsgEntry.COLUMN_TITLE + TEXT_TYPE + COMMA_SEP +
//                    MsgEntry.COLUMN_TIME + TEXT_TYPE + COMMA_SEP +
//                    MsgEntry.COLUMN_BODY + TEXT_TYPE + COMMA_SEP +
//                    TimeEntry.COLUMN_CLOSE + TEXT_TYPE + " )";
    private static final String MSGSQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + TotalEntry.TABLE_NAME_Msg + " (" +
                    MsgEntry._USER_ID + " INTEGER PRIMARY KEY," +
                    MsgEntry.toMsgEntryString()+ " )";
//好友关系表
//    private static final String userRelationSQL_CREATE_ENTRIES =
//            "CREATE TABLE IF NOT EXISTS " + StudentsEntry.TABLE_NAME + " (" +
//                    StudentsEntry._ID + " INTEGER PRIMARY KEY," +
//                    StudentsEntry.COLUMN_KID + TEXT_TYPE + COMMA_SEP +
//                    StudentsEntry.COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
//                    StudentsEntry.COLUMN_CANJIA + TEXT_TYPE + COMMA_SEP +
//                    StudentsEntry.COLUMN_WENTI + TEXT_TYPE + COMMA_SEP +
//                    StudentsEntry.COLUMN_CLASS + TEXT_TYPE + " )";



//    private static final String TIMESQL_DELETE_ENTRIES =
//            "DROP TABLE IF EXISTS " + TimeEntry.TABLE_NAME;
//    private static final String STUSSQL_DELETE_ENTRIES =
//            "DROP TABLE IF EXISTS " + StudentsEntry.TABLE_NAME;
//    private static final String BODYSQL_DELETE_ENTRIES =
//            "DROP TABLE IF EXISTS " + BodyEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "xinf.db";
    /**
     * swooleServer : 39.108.3.131:8000
     * swooleServer_v1 : 39.108.3.131:9000
     */

    private String swooleServer;
    private String swooleServer_v1;

    public DBgreatTable(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MSGSQL_CREATE_ENTRIES);
//        db.execSQL(STUSSQL_CREATE_ENTRIES);
//        db.execSQL(BODYSQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
//        db.execSQL(TIMESQL_DELETE_ENTRIES);
//        db.execSQL(STUSSQL_DELETE_ENTRIES);
//        db.execSQL(BODYSQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
