package com.hneb.dws.dao.repository;

import com.hneb.dws.vo.ChatMsgVO;
import com.hneb.dws.vo.SvcOrderVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/10/10 0010.
 */
@Repository
public interface SvcOrderRepository extends JpaRepository<SvcOrderVO,Long>{


    /**
     * 获取单个SvcOrderVO对象
     * @param orderId
     * @return SvcOrderVO
     */
    SvcOrderVO getSvcOrderVOByCOrderId(String orderId);

    @Transactional
    @Modifying
    @Query("update SvcOrderVO set cOrderSts=?1 where cOrderId=?2")
    int updateSvcOrderVOByCOrderId(String ordserSts,String orderId);
}
