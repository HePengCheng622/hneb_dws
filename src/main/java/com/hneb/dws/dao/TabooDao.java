package com.hneb.dws.dao;

import com.hneb.dws.dao.repository.TabooRepository;
import com.hneb.fwk.dao.BaseDao;
import net.sf.json.JSONArray;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/5.
 */
@Repository
public class TabooDao extends BaseDao {

    @Autowired
    private TabooRepository tabooRepository;

    public TabooRepository getTabooRepository() {
        return tabooRepository;
    }

    public void setTabooRepository(TabooRepository tabooRepository) {
        this.tabooRepository = tabooRepository;
    }

    public String getFoodNames(String foodIds) throws Exception {
        String tmp = foodIds;
        tmp = tmp.replace("[","").replace("]","");
        tmp = tmp.replaceAll("\"","'");
        String sql ="select c_name as name from t_food a where c_pk_id in ("+tmp+")";
        List list = super.querySql(sql);
        String result = "";
        for(int i=0;i<list.size();i++){
            result = result + ((Map)list.get(i)).get("name")+",";
        }
        return result;
    }

    public List select() throws Exception {
        String sql="select " +
                "   c_pk_id as pkId, " +
                "   c_taboo as taboo, " +
                "   c_foods as foods " +
                "from " +
                "   t_taboo b " +
                "where " +
                "   b.c_eff_mrk='1' ";
        return super.querySql(sql);
    }

    //禁忌食物下拉框初始化（用于制定餐单 carte.html）
    public List initTaboo() throws Exception {
        String sql="select " +
                "   c_pk_id as value, " +
                "   c_taboo as label " +
                "from " +
                "   t_taboo b " +
                "where " +
                "   b.c_eff_mrk='1' ";
        return super.querySql(sql);
    }

    /**
     * 根据pkid获取所有禁忌食物名称
     * @return
     */
    public List<Map> getTaboos(String key) throws Exception {
        String sql="select " +
                "   c_pk_id as value, " +
                "   c_taboo as label " +
                "from " +
                "   t_taboo b " +
                "where " +
                "   b.c_pk_id in ("+key+")";
        return querySql(sql);
    }


    /**
     * 根据禁忌id，查询禁忌食物id
     * @return
     * @throws Exception
     */
    public List getTabooFoods(Object arr) throws Exception {
        String pkIds="";
        for (Object str:(JSONArray)arr) {
            pkIds+="'"+str.toString()+"',";
        }
        String sql= "select " +
                    "   c_foods as foods " +
                    "from " +
                    "   t_taboo b " +
                    "where " +
                    "   b.c_pk_id in ("+pkIds.substring(0,pkIds.length()-1)+") ";
        return em.createNativeQuery(sql).getResultList();
    }
}
