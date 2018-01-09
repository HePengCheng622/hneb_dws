package com.hneb.dws.dao.repository;

import com.hneb.dws.vo.RecipeVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author HPC
 * @create 2017-12-06 11:52
 * @desc 菜谱Repository
 **/
@Repository
public interface RecipeRepository  extends JpaRepository<RecipeVO,Long>{

    /**
     * 通过cpkId获取菜品
     * @param cPkId
     * @return
     */
    public RecipeVO getRecipeVOByCPkId(String cPkId);

    public void deleteByCPkId(String cPkId);
}
