package com.hneb.dws.dao;

import com.hneb.dws.dao.repository.RecipeRepository;
import com.hneb.dws.vo.RecipeVO;
import com.hneb.fwk.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author HPC
 * @create 2017-12-06 11:48
 * @desc 菜谱dao层
 **/
@Repository
public class RecipeDao extends BaseDao{
    @Autowired
    private RecipeRepository recipeRep;

    public RecipeRepository getRecipeRep() {
        return recipeRep;
    }

    public void setRecipeRep(RecipeRepository recipeRep) {
        this.recipeRep = recipeRep;
    }

    /**
     * 查看指定菜品id是否被餐单使用，如果没有则返回0，如果有则返回大于0的个数
     * @param recipeId
     * @return
     * @throws Exception
     */
    public int countRecipe(String recipeId) throws Exception {
        String sql = "select count(1) as num from t_carte_list where c_recipe_id=?";
        List<Map> list = super.querySql(sql,recipeId);
        if(list==null || list.size()==0){
            return 0;
        }else{
            return Integer.parseInt(list.get(0).get("num").toString());
        }
    }


    public List getRecipe(String carteId,Long dayNum) throws Exception {
        String sql ="select  " +
                    "       a.c_name as nme, " +
                    "       a.c_food_desc as foodDesc, " +
                    "       a.c_pk_id as recipeId, " +
                    "       a.c_org_id as orgId, " +
                    "       c.c_taboo as taboo, " +
                    "       c.c_history as history, " +
                    "       c.c_spe_taboo as speTaboo, " +
                    "       b.c_meal_mrk as mealMark " +
                    "from  " +
                    "     t_recipe a ,t_carte_list b,t_carte c " +
                    "where  " +
                    "      b.c_carte_id=? " +
                    "      and b.n_day_num=? " +
                    "      and a.c_pk_id=b.c_recipe_id " +
                    "      and b.c_carte_id=c.c_pk_id";
        return super.querySql(sql,carteId,dayNum);
    }

    public List getRecipeAndAddr(String carteId,Long dayNum) throws Exception {
        String sql ="select   " +
                    "       a.c_name as recipeNme,  " +
                    "       a.c_food_desc as foodDesc,  " +
                    "       d.c_nme as nme, " +
                    "       d.c_phone_no as phoneNo, " +
                    "       d.c_province as province, " +
                    "       d.c_city as city, " +
                    "       d.c_zone as zone, " +
                    "       d.c_addr as addr, " +
                    "       d.c_pk_id as addrId,  " +
                    "       c.t_eat_tm as eatTm,  " +
                    "       c.c_meal_mrk as mealMrk " +
                    "from   " +
                    "     t_recipe a ,t_carte_list b,t_carte_order c,t_addr d   " +
                    "where   " +
                    "       b.c_carte_id=?  " +
                    "      and b.n_day_num=?  " +
                    "      and a.c_pk_id=b.c_recipe_id  " +
                    "      and c.c_carte_id = b.c_carte_id" +
                    "      and c.c_addr_id=d.c_pk_id";
        return super.querySql(sql,carteId,dayNum);
    }

    public List<Map> getRecipe(String carteId,String date) throws Exception {
        String sql= "select " +
                    "       a.N_WATER as water," +
                    "       a.N_ENERGY_1 as energy1," +
                    "       a.N_ENERGY_2 as energy2," +
                    "       a.N_PROTEIN as protein," +
                    "       a.N_FAT as fat," +
                    "       a.N_CHO as cho," +
                    "       a.N_DIETARY_FIBER as dietaryFiber," +
                    "       a.N_CHOLESTEROL as cholesterol," +
                    "       a.N_ASH as ash," +
                    "       a.N_VA as va," +
                    "       a.N_CAROTENE as carotene," +
                    "       a.N_RETINOL as retinol," +
                    "       a.N_THIAMIN as thiamin," +
                    "       a.N_RIBOFLAVIN as riboflavin," +
                    "       a.N_NIACIN as niacin," +
                    "       a.N_VC as vc," +
                    "       a.N_VE_TOTAL as veTotal," +
                    "       a.N_CA as ca," +
                    "       a.N_P as p," +
                    "       a.N_K as k," +
                    "       a.N_NA as na," +
                    "       a.N_MG as mg," +
                    "       a.N_FE as fe," +
                    "       a.N_ZN as zn," +
                    "       a.N_SE as se," +
                    "       a.N_CU as cu," +
                    "       a.N_MN as mn " +
                    "from " +
                    "     t_recipe a,t_carte_list b " +
                    "where " +
                    "      b.c_carte_id=? " +
                    "      and b.n_day_num=?" +
                    "      and b.c_recipe_id=a.c_pk_id";
        return super.querySql(sql,carteId,date);
    }

    /**
     * 根据菜品id更新最新标志
     * @param recipeId
     * @param latestMrk
     */
    public void updateRecipeLatestMrk(String recipeId,String latestMrk){
        String sql = "update t_recipe set c_latest_mrk=? where c_pk_id=?";
        super.updateSql(sql,latestMrk,recipeId);
    }

}
