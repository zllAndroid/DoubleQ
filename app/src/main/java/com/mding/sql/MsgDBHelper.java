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
public class MsgDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="my_contact.db";
    public MsgDBHelper(Context context){
        super(context,DB_NAME,null,1);
    }
//    public MsgDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlStmt= "CREATE TABLE IF NOT EXISTS info(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "firstname TEXT, " +
                "lastname  TEXT," +
                "phone     TEXT, " +
                "email     TEXT)";
        sqLiteDatabase.execSQL(sqlStmt);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
