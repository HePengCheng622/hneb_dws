package com.hneb.fwk.dao;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/13 0013.
 */
@Transactional
public class BaseDao {
    @Autowired
    public EntityManager em;

    public List<Map> querySql(String sql, Object ...params)throws Exception{

        Query query =em.createNativeQuery(sql);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        for (int i=0;i<params.length;i++){
            query.setParameter(i+1,params[i]);
        }

        List<Map> resultList = query.getResultList();

        return resultList;
    }

    public int updateSql(String sql,Object ...params){
        Query query = em.createNativeQuery(sql);
        for (int i=0;i<params.length;i++){
            query.setParameter(i+1,params[i]);
        }
        return query.executeUpdate();
    }
}
