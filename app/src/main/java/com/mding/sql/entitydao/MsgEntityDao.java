package com.mding.sql.entitydao;

import com.mding.dao.Msg;
import com.mding.greendao.DatabaseManager;
import com.mding.greendao.MsgDao;

/**
 * Created by ji_cheng on 2016/12/6.
 */
public class MsgEntityDao extends BaseEntityDao<MsgDao, Msg, Long> {

    @Override
    protected MsgDao initEntityDao() {
        return DatabaseManager.newSession().getMsgDao();
    }


}
