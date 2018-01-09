package com.hneb.dws.dao.repository;

import com.hneb.dws.vo.SettingVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/10/10 0010.
 */
@Repository
public interface SettingRepository extends JpaRepository<SettingVO,Long>{


    /**
     * 通过userId获取Setting对象
     * @return settingVo对象
     */
    SettingVO getByCUserId(String cUserId);

}
