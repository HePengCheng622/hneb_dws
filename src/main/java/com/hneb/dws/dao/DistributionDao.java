package com.hneb.dws.dao;

import com.hneb.fwk.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/25.
 */
@Repository
public class DistributionDao extends BaseDao {

    public List<Map> getFoodAndAddr(String carteId, String mealMrk, Long dayNum) throws Exception {

        String sql= "select " +
                "       a.c_food_nme as foodNme," +
                "       a.n_weight as weight " +
                "from " +
                "     t_recipe_food a,t_carte_list b, " +
                "where " +
                "      b.c_carte_id=?" +
                "      and b.n_day_num=? " +
                mealMrk!=null?" and b.c_meal_mrk=?":""+
                "      and a.c_recipe_id=b.c_recipe_id";
        if(mealMrk!=null){
            return super.querySql(sql,carteId,mealMrk,dayNum);
        }
        return super.querySql(sql,carteId,dayNum);
    }
}
