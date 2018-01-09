package com.hneb.dws.dao.repository;

import com.hneb.dws.vo.IllDescVO;
import com.hneb.dws.vo.RecipeFoodVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author HPC
 * @create 2017-12-06 10:26
 * @desc 菜单食材Repository
 **/
@Repository
public interface RecipeFoodRepository extends JpaRepository<RecipeFoodVO,Long>{

    public void deleteByCRecipeId(String cRecipeId);
    /**
     * 通过食谱Id获取食谱对应所需的食材
     * @param cPkId
     * @return list
     */
    public RecipeFoodVO getRecipeFoodVOByCPkId(String cPkId);

    /**
     * 通过recipeId获取材料
     * @param recipeId
     * @return
     */
    public List<RecipeFoodVO> getRecipeFoodVOByCRecipeId(String recipeId);
    /**
     * 根据菜谱id和食物id来删除食材
     * @param recipeId
     * @param foodId
     */
    @Modifying
    public void deleteRecipeFoodVOByCPkIdAndCFoodId(String recipeId,String foodId);

}
