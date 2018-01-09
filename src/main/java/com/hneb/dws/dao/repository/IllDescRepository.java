package com.hneb.dws.dao.repository;

import com.hneb.dws.vo.IllDescVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/10/20 0020.
 */
@Repository
public interface IllDescRepository extends JpaRepository<IllDescVO,Long>{
    /**
     * 通过orderId获取病情对象
     * @param orderId
     * @return IllDescVO
     */
    IllDescVO getIllDescVOByCOrderId(String orderId);
}
