package com.hneb.dws.dao;

import com.hneb.dws.dao.repository.CarteRepository;
import com.hneb.dws.vo.UserVO;
import com.hneb.fwk.dao.BaseDao;
import com.hneb.fwk.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/13.
 */
@Repository
public class CarteDao extends BaseDao {
    @Autowired
    private CarteRepository carteRepository;

    public CarteRepository getCarteRepository() {
        return carteRepository;
    }

    public void setCarteRepository(CarteRepository carteRepository) {
        this.carteRepository = carteRepository;
    }

    /**
     * 根据指定机构、就餐时间和就餐餐别查询已付款的餐单
     * @param eatTm
     * @param mealMrk
     * @param dptIds
     * @return
     */
    public List<Map> getCarte(String eatTm, String mealMrk,String dptIds) throws Exception {
        String sql ="select " +
                    "       c.c_pk_id as carteId," +
                    "       date_format(c.t_bgn_tm,'%Y-%m-%d') as bgnTm " +
                    "from " +
                    "     t_carte_order a," +
                    "     t_svc_order b," +
                    "     t_carte c " +
                    "where " +
                    "      a.t_eat_tm=?" +
                    ((mealMrk!=null && !"".equals(mealMrk)) ?"      and a.c_meal_mrk=?":"") +
                    "      and a.c_dpt_id in ("+dptIds+")" +
                    "      and a.c_catering_mrk='Y'" +
                    "      and b.c_order_sts='1'" +
                    "      and b.c_service_sts is null" +
                    "      and a.c_order_id=b.c_order_id" +
                    "      and a.c_carte_id = c.c_pk_id";
        if(mealMrk!=null && !"".equals(mealMrk)){
            return super.querySql(sql,eatTm,mealMrk);
        }
        return super.querySql(sql,eatTm);

    }


}
