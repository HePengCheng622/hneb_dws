package com.hneb.dws.dao.repository;

import com.hneb.dws.vo.CarteListVO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/12/14.
 */
public interface CarteListRepository extends JpaRepository<CarteListVO,Long> {

    /**
     * 通过pkId删除餐单list
     * @param pkId
     */
    public void deleteCarteListVOByCPkId(String pkId);

    public CarteListVO findByCPkId(String pkId);

    public CarteListVO findByCRecipeId(String recipeId);
    /**
     * 通过carteId删除餐单的所有数据
     * @param carteId
     */
    public void deleteCarteListVOByCCarteId(String carteId);

}
