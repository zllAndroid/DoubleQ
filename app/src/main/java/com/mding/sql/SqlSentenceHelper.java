package com.mding.sql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.alibaba.fastjson.JSONArray;

/**
 * 项目：DoubleQ
 * 文件描述：数据库语句帮助类
 * 作者：zll
 * 创建时间：2019/4/3 0003
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class SqlSentenceHelper {

    //    查询是否存在此条数据
    public  static  boolean isQueryHave(SQLiteDatabase writableDatabase,String userId,String tableName)
    {
        Cursor cursor = writableDatabase.rawQuery("SELECT userId FROM " + tableName + " where userId=" + "'" + userId + "'" + " limit 1", null);
        JSONArray jsonArray = SqlUtils.cur2Json(cursor);
        if (jsonArray.size()==0)
            return false;
        return  true;
    }
//    查询此userId全部
    public  static  JSONArray S_QueryByUserId(SQLiteDatabase writableDatabase,String userId,String tableName)
    {
        Cursor cursor = writableDatabase.rawQuery("SELECT * FROM " + tableName + " where userId=" + "'" + userId + "'" , null);
        JSONArray jsonArray = SqlUtils.cur2Json(cursor);
        if (jsonArray.size()==0)
            return null;
        return  jsonArray;
    }
//    查询此全部
    public  static  JSONArray S_QueryAll(SQLiteDatabase writableDatabase,String userId,String tableName)
    {
        Cursor cursor = writableDatabase.rawQuery("SELECT * FROM " + tableName, null);
        JSONArray jsonArray = SqlUtils.cur2Json(cursor);
        if (jsonArray.size()==0)
            return null;
        return  jsonArray;
    }
//    public  static  JSONArray S_InsertMsg(SQLiteDatabase writableDatabase,String userId,String tableName)
//    {
//        Cursor cursor = writableDatabase.rawQuery("SELECT * FROM " + tableName, null);
//        JSONArray jsonArray = SqlUtils.cur2Json(cursor);
//        if (jsonArray.size()==0)
//            return null;
//        return  jsonArray;
//    }


}
