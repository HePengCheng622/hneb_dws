package com.hneb.dws.dao;

import com.hneb.dws.dao.repository.SvcPriceRepository;
import com.hneb.dws.vo.SvcPriceVO;
import com.hneb.fwk.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/10/13 0013.
 */
@Repository
public class SvcPriceDao extends BaseDao {
    @Autowired
    private SvcPriceRepository priceRep;

    public SvcPriceRepository getPriceRep() {
        return priceRep;
    }

    public void setPriceRep(SvcPriceRepository priceRep) {
        this.priceRep = priceRep;
    }
}
