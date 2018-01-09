package com.hneb.dws.dao;

import com.hneb.dws.dao.repository.DoctorAuthRepository;
import com.hneb.fwk.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/12/25.
 */
@Repository
public class DoctorAuthDao extends BaseDao {

    @Autowired
    private DoctorAuthRepository doctorAuthRepository;

    public DoctorAuthRepository getDoctorAuthRepository() {
        return doctorAuthRepository;
    }

    public void setDoctorAuthRepository(DoctorAuthRepository doctorAuthRepository) {
        this.doctorAuthRepository = doctorAuthRepository;
    }
}
