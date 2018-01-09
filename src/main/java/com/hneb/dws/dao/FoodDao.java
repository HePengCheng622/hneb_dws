package com.hneb.dws.dao;

import com.hneb.dws.dao.repository.FoodRepository;
import com.hneb.fwk.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/29 0029.
 */
@Repository
public class FoodDao extends BaseDao {
    @Autowired
    private FoodRepository foodRep;

    public FoodRepository getFoodRep() {
        return foodRep;
    }

    public void setFoodRep(FoodRepository foodRep) {
        this.foodRep = foodRep;
    }


    public List<Map> getFoods() throws Exception {
        String sql ="select " +
                "       c_pk_id as value," +
                "       c_name as label" +
                " from " +
                "      t_food a " +
                " order by " +
                "       c_pk_id asc ";
        return super.querySql(sql);
    }

    /**
     * 根据二级分类查找食材
     * 在foodsCascader中使用
     * @param cde2
     * @return
     * @throws Exception
     */
    public List<Map> getFoods(String cde2) throws Exception {
        String sql ="select " +
                "       c_pk_id as value," +
                "       c_name as label" +
                " from " +
                "      t_food a " +
                " where " +
                "       a.c_cde2=? " +
                " order by " +
                "       c_pk_id asc ";
        return super.querySql(sql,cde2);
    }

    /**
     * 根据前台输入的食材名称模糊搜索食材
     * @param key
     * @return
     * @throws Exception
     */
    public List<Map> search(String key) throws Exception {
        String sql ="select " +
                "       c_pk_id as value," +
                "       c_name as label" +
                " from " +
                "      t_food a " +
                " where" +
                "       a.c_name like '%"+key+"%' " +
                " order by " +
                "       c_pk_id asc ";
        return super.querySql(sql);
    }

    /**
     * 通过食材的pkId获取所有的食材名字
     * @param key
     * @return
     * @throws Exception
     */
    public List<Map> searchList(String key) throws Exception {
        String sql ="select " +
                "       c_pk_id as value," +
                "       c_name as label" +
                " from " +
                "      t_food a " +
                " where" +
                "       a.c_pk_id in ("+key+")" ;
        return querySql(sql);
    }

}
