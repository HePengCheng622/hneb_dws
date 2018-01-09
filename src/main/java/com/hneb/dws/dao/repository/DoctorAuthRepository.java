package com.hneb.dws.dao.repository;

import com.hneb.dws.vo.DoctorAuthVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/12/25.
 */
@Repository
public interface DoctorAuthRepository extends JpaRepository<DoctorAuthVO,Long> {

    public DoctorAuthVO getByCUserId(String userId);
}
