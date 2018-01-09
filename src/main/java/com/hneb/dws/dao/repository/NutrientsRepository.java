package com.hneb.dws.dao.repository;

import com.hneb.dws.vo.NutrientVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/12/12.
 */
public interface NutrientsRepository extends JpaRepository<NutrientVO,Long> {

    public NutrientVO findTopByCItemAndCSexAndCTypeAndNAgeLessThanEqualOrderByNAgeDesc(String item, String sex, String type, float age);
}
