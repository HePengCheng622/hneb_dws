package com.hneb.dws.dao;

import com.hneb.dws.dao.repository.IllDescRepository;
import com.hneb.fwk.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/10/20 0020.
 */
@Repository
public class IllDescDao extends BaseDao {

    @Autowired
    private IllDescRepository illDescRep;

    public IllDescRepository getIllDescRep() {
        return illDescRep;
    }

    public void setIllDescRep(IllDescRepository illDescRep) {
        this.illDescRep = illDescRep;
    }
}
