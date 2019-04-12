package com.mding.sql.entitydao;

import com.mding.dao.PrivateChatData;
import com.mding.greendao.DatabaseManager;
import com.mding.greendao.PrivateChatDataDao;

/**
 * Created by ji_cheng on 2016/12/6.
 */
public class PrivateChatEntityDao extends BaseEntityDao<PrivateChatDataDao, PrivateChatData, Long> {

    @Override
    protected PrivateChatDataDao initEntityDao() {
        return DatabaseManager.newSession().getPrivateChatDataDao();
    }


}
