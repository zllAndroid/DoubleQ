package com.mding.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 * 更新升级数据库 操作类
 */
public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        TODO 所有数据类写在此
        MigrationHelper.migrate(db, FriendInfoDao.class, MsgDao.class, GroupChatDataDao.class, GroupInfoDao.class, PrivateChatDataDao.class,UserRelationDao.class,GroupUserRelationDao.class);
    }
}
