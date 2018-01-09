package com.hneb.dws.dao.repository;

import com.hneb.dws.vo.SvcPriceVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/10/13 0013.
 */
@Repository
public interface SvcPriceRepository extends JpaRepository<SvcPriceVO,Long> {
    /**
     * 通过salerId和type获取对象
     * @param salerId
     * @param type
     * @return
     */
    SvcPriceVO getByCSalerIdAndCProdType(String salerId,String type);
}
