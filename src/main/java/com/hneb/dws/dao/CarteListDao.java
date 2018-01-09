package com.hneb.dws.dao;

import com.hneb.dws.dao.repository.CarteListRepository;
import com.hneb.fwk.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/12/14.
 */
@Repository
public class CarteListDao extends BaseDao {
    @Autowired
    private CarteListRepository carteListRepository;

    public CarteListRepository getCarteListRepository() {
        return carteListRepository;
    }

    public void setCarteListRepository(CarteListRepository carteListRepository) {
        this.carteListRepository = carteListRepository;
    }

}
