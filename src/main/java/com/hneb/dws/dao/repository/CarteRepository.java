package com.hneb.dws.dao.repository;

import com.hneb.dws.vo.CarteVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/12/13.
 */
@Repository
public interface CarteRepository extends JpaRepository<CarteVO,Long> {

    /**
     * 通过pkId来删除餐单
     * @param C_PK_ID
     */
    public void deleteCarteVOByCPkId(String C_PK_ID);

    public CarteVO getCarteVOByCPkId(String cPkId);
}
