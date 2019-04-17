package com.mding.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.mding.dao.FriendInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "FRIEND_INFO".
*/
public class FriendInfoDao extends AbstractDao<FriendInfo, Long> {

    public static final String TABLENAME = "FRIEND_INFO";

    /**
     * Properties of entity FriendInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property UserId = new Property(1, String.class, "userId", false, "USER_ID");
        public final static Property FriendId = new Property(2, String.class, "friendId", false, "FRIEND_ID");
        public final static Property ImageHead = new Property(3, String.class, "imageHead", false, "IMAGE_HEAD");
        public final static Property Persona_signature = new Property(4, String.class, "persona_signature", false, "PERSONA_SIGNATURE");
        public final static Property Countries_regions = new Property(5, String.class, "countries_regions", false, "COUNTRIES_REGIONS");
        public final static Property Qrcode = new Property(6, String.class, "qrcode", false, "QRCODE");
        public final static Property Email = new Property(7, String.class, "email", false, "EMAIL");
        public final static Property Nick_name = new Property(8, String.class, "nick_name", false, "NICK_NAME");
        public final static Property Wx_sno = new Property(9, String.class, "wx_sno", false, "WX_SNO");
    }


    public FriendInfoDao(DaoConfig config) {
        super(config);
    }
    
    public FriendInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"FRIEND_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"USER_ID\" TEXT," + // 1: userId
                "\"FRIEND_ID\" TEXT," + // 2: friendId
                "\"IMAGE_HEAD\" TEXT," + // 3: imageHead
                "\"PERSONA_SIGNATURE\" TEXT," + // 4: persona_signature
                "\"COUNTRIES_REGIONS\" TEXT," + // 5: countries_regions
                "\"QRCODE\" TEXT," + // 6: qrcode
                "\"EMAIL\" TEXT," + // 7: email
                "\"NICK_NAME\" TEXT," + // 8: nick_name
                "\"WX_SNO\" TEXT);"); // 9: wx_sno
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_FRIEND_INFO_FRIEND_ID_DESC ON \"FRIEND_INFO\"" +
                " (\"FRIEND_ID\" DESC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"FRIEND_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, FriendInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(2, userId);
        }
 
        String friendId = entity.getFriendId();
        if (friendId != null) {
            stmt.bindString(3, friendId);
        }
 
        String imageHead = entity.getImageHead();
        if (imageHead != null) {
            stmt.bindString(4, imageHead);
        }
 
        String persona_signature = entity.getPersona_signature();
        if (persona_signature != null) {
            stmt.bindString(5, persona_signature);
        }
 
        String countries_regions = entity.getCountries_regions();
        if (countries_regions != null) {
            stmt.bindString(6, countries_regions);
        }
 
        String qrcode = entity.getQrcode();
        if (qrcode != null) {
            stmt.bindString(7, qrcode);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(8, email);
        }
 
        String nick_name = entity.getNick_name();
        if (nick_name != null) {
            stmt.bindString(9, nick_name);
        }
 
        String wx_sno = entity.getWx_sno();
        if (wx_sno != null) {
            stmt.bindString(10, wx_sno);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, FriendInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(2, userId);
        }
 
        String friendId = entity.getFriendId();
        if (friendId != null) {
            stmt.bindString(3, friendId);
        }
 
        String imageHead = entity.getImageHead();
        if (imageHead != null) {
            stmt.bindString(4, imageHead);
        }
 
        String persona_signature = entity.getPersona_signature();
        if (persona_signature != null) {
            stmt.bindString(5, persona_signature);
        }
 
        String countries_regions = entity.getCountries_regions();
        if (countries_regions != null) {
            stmt.bindString(6, countries_regions);
        }
 
        String qrcode = entity.getQrcode();
        if (qrcode != null) {
            stmt.bindString(7, qrcode);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(8, email);
        }
 
        String nick_name = entity.getNick_name();
        if (nick_name != null) {
            stmt.bindString(9, nick_name);
        }
 
        String wx_sno = entity.getWx_sno();
        if (wx_sno != null) {
            stmt.bindString(10, wx_sno);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public FriendInfo readEntity(Cursor cursor, int offset) {
        FriendInfo entity = new FriendInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // userId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // friendId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // imageHead
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // persona_signature
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // countries_regions
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // qrcode
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // email
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // nick_name
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // wx_sno
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, FriendInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFriendId(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setImageHead(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPersona_signature(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCountries_regions(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setQrcode(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setEmail(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setNick_name(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setWx_sno(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(FriendInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(FriendInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(FriendInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}