package com.hneb.fwk.query;

import com.hneb.ResultEnum;
import com.hneb.fwk.dao.BaseDao;
import com.hneb.fwk.exception.HnebException;
import net.sf.json.JSONObject;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by Administrator on 2017/10/11 0011.
 */
@Repository
public class PageQueryTools extends BaseDao{

    //params的key必须含三个参数，_pageNum(请求第多少页)\_pageSize（int 每页条数）\_count(可选，默认为true,boolean:true需要总条数、false不需要总条数)
    public PageResultDTO querySql(String sql, JSONObject params)throws Exception{
        int first = 0,max=0;
        boolean count = true;

        int _pageNum,_pageSize;
        try {
            _pageNum = Integer.parseInt(params.getString("_pageNum"));
            _pageSize = Integer.parseInt(params.getString("_pageSize"));

            if(params.containsKey("_count")){
                count = Boolean.parseBoolean(params.getString("_count"));
            }
        }catch (Exception e){
            throw new HnebException(ResultEnum.SYS_MISS_PAGE_QUERY_PARAMETER);
        }

        first = (_pageNum-1)*_pageSize;
        max = _pageSize;

        SqlFilterUtils sqlFilter = new SqlFilterUtils();
        String ql = sqlFilter.matching(sql, params);

        if (ql == null) {
            ql = sql;
            throw new Exception("SQLFilter 参数匹配只适合SELECT的语句!不适合DELETE、UPDATE操作!");
        }
        Query query =em.createNativeQuery(ql);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.setFirstResult(first);//设置起行
        query.setMaxResults(max);//设置结束行

        List resultList = query.getResultList();//获取分页后的数据
        PageResultDTO pageResultDTO = new PageResultDTO();
        pageResultDTO.setResultList(resultList);
        if(count) {
            String countSql = "select count(*) as countX from (" + ql + ")   tmp_count";
            Query countQuery = em.createNativeQuery(countSql);
            BigInteger total=(BigInteger) countQuery.getSingleResult();
            pageResultDTO.setTotal(total.intValue());
        }else{
            pageResultDTO.setTotal(Integer.parseInt(params.getString("_total")));
        }
        return pageResultDTO;
    }

}
