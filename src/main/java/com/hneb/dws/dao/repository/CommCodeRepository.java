package com.hneb.dws.dao.repository;

import com.hneb.dws.vo.CommCodeVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/11/30 0030.
 */
@Repository
public interface CommCodeRepository extends JpaRepository<CommCodeVO,Long>{
    // 通过code来获取CommCodeVo
    public List<CommCodeVO> findCommCodeVOByCCodeAndCEffMrk(String code,String mrk);

    // 通过parentCode来获取CommCodeVo
    public List<CommCodeVO> findCommCodeVOByCParentCodeAndCEffMrk(String parentCode,String mrk);

    // 通过code删除commcode(其实就是更改了code)
    @Modifying
    @Query("update CommCodeVO set cEffMrk='N' where cParentCode=?1")
    public void deleteSonCommCodeVO(String code);

    // 通过code删除commcode(其实就是更改了code)
    @Modifying
    @Query("update CommCodeVO set cEffMrk='N' where cCode=?1")
    public void deleteCommCodeVO(String code);

    /*获取子节点中最大的code值*/
    @Query("select max(cCode) from CommCodeVO where cParentCode=?1")
    public String getMaxCodeOfSon(String parentCode);
}
