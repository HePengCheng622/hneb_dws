package com.hneb.dws.dao;

import com.hneb.dws.dao.repository.CommCodeRepository;
import com.hneb.fwk.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/30 0030.
 */
@Repository
public class CommCodeDao extends BaseDao {
    @Autowired
    private CommCodeRepository commCodeRep;

    public CommCodeRepository getCommCodeRep() {
        return commCodeRep;
    }

    public void setCommCodeRep(CommCodeRepository commCodeRep) {
        this.commCodeRep = commCodeRep;
    }

    public List<Map> getCommCodeByParentCode(String parentCode) throws Exception {
        String sql ="select " +
                    "       c_code as value," +
                    "       c_text as label," +
                    "       c_parent_code as parent," +
                    "       c_eff_mrk as mrk," +
                    "       (select count(1) from t_comm_code b where b.c_parent_code=a.c_code) as children" +
                    " from " +
                    "      t_comm_code a " +
                    " where " +
                    "       c_parent_code=? " +
                    "   and c_eff_mrk='Y'";
        return super.querySql(sql,parentCode);
    }

    public List<Map> getCommCodeLikeCode(String parentCode) throws Exception {
        String sql ="select " +
                "       c_code as value," +
                "       c_text as label," +
                "       c_parent_code as parent," +
                "       c_eff_mrk as mrk" +
                " from " +
                "      t_comm_code a " +
                " where " +
                "       c_code like ? ";
        return super.querySql(sql,parentCode+"%");
    }
}
