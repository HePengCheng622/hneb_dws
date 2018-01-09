package com.hneb.dws.dao.repository;

import com.hneb.dws.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2017/10/13 0013.
 */
public interface UserRepository extends JpaRepository<UserVO,Long>{
    /**
     * 通过userId获取对象
     * @param userId
     * @return
     */
    UserVO getUserVOByCUserId(String userId);
}
