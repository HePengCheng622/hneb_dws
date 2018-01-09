package com.hneb.dws.dao.repository;

import com.hneb.dws.vo.FoodVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/29 0029.
 */
@Repository
public interface FoodRepository extends JpaRepository<FoodVO,Long> {
    /**
     * 根据cPkId来删除食物
     */
    public void deleteFoodVOByCPkId(String cPkId);

    /**
     * 通过cPkId获取指定的foodVO对象
     * @param cPkId
     * @return
     */
    public FoodVO getFoodVOByCPkId(String cPkId);

    /**
     * 通过名字like获取food
     * @param name
     * @return
     */
    public List<FoodVO> getFoodVOByCNameLike(String name);

    /**
     * 通过类别名字获取foods
     * @param name
     * @return
     */
    public List<FoodVO> getFoodVOByCSecKindNme(String name);

    /**
     * 获取所有的类别
     * @return
     */
    @Query("select distinct (cSecKindNme) from FoodVO ")
    public List getFoodVOAllType();
}

