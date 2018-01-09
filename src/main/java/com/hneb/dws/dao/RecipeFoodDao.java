package com.hneb.dws.dao;

import com.hneb.dws.dao.repository.RecipeFoodRepository;
import com.hneb.fwk.dao.BaseDao;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * @author HPC
 * @create 2017-12-06 10:23
 * @desc 菜单食材Dao层
 **/
@Repository
public class RecipeFoodDao extends BaseDao {
    @Autowired
    private RecipeFoodRepository recipeFoodRep;

    public RecipeFoodRepository getRecipeFoodRep() {
        return recipeFoodRep;
    }

    public void setRecipeFoodRep(RecipeFoodRepository recipeFoodRep) {
        this.recipeFoodRep = recipeFoodRep;
    }

    public List<Map> getFood(String carteId, String mealMrk,Long dayNum) throws Exception {

        String sql= "select " +
                    "       a.c_food_nme as foodNme," +
                    "       a.n_weight as weight " +
                    "from " +
                    "     t_recipe_food a,t_carte_list b " +
                    "where " +
                    "      b.c_carte_id=? " +
                    "      and b.n_day_num=? " +
                    ((mealMrk!=null&&!"".equals(mealMrk))?" and b.c_meal_mrk=? ":"")+
                    "      and a.c_recipe_id=b.c_recipe_id ";
        if(mealMrk!=null&&!"".equals(mealMrk)){
            return super.querySql(sql,carteId,dayNum.toString(),mealMrk);
        }
        return super.querySql(sql,carteId,dayNum);
    }
}
