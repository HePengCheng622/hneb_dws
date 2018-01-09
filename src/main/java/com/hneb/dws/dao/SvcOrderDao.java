package com.hneb.dws.dao;

import com.hneb.dws.dao.repository.ChatMsgRepository;
import com.hneb.dws.dao.repository.SvcOrderRepository;
import com.hneb.dws.dao.repository.SvcPriceRepository;
import com.hneb.fwk.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/10/20 0020.
 */
@Repository
public class SvcOrderDao extends BaseDao {
    @Autowired
    private SvcOrderRepository svcOrderRep;

    public SvcOrderRepository getSvcOrderRep() {
        return svcOrderRep;
    }

    public void setSvcOrderRep(SvcOrderRepository svcOrderRep) {
        this.svcOrderRep = svcOrderRep;
    }
}
