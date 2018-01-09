package com.hneb.dws.dao;

import com.hneb.dws.dao.repository.NutrientsRepository;
import com.hneb.dws.vo.NutrientVO;
import com.hneb.fwk.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/12/12.
 */
@Repository
public class NutrientDao extends BaseDao {

    @Autowired
    private NutrientsRepository nutrientsRepository;

    public Float get(String item,String sex,String type,float age) throws Exception {
        NutrientVO vo = this.nutrientsRepository.findTopByCItemAndCSexAndCTypeAndNAgeLessThanEqualOrderByNAgeDesc(item,sex,type,age);
        if(vo==null){
            throw new Exception("未找到符合条件的记录：item=>"+item+",sex=>"+sex+",type=>"+type+",age=>"+age);
        }else{
            if(vo.getnVal()==null){
                return new Float(-1.0);
            }else{
                return vo.getnVal();
            }
        }
    }
}
