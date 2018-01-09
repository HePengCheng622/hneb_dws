package com.hneb.dws.service;

import com.hneb.dws.dao.CarteDao;
import com.hneb.dws.dao.DoctorAuthDao;
import com.hneb.dws.vo.DoctorAuthVO;
import com.hneb.fwk.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/25.
 */
@Service
public class CarteService {
    @Autowired
    private CarteDao carteDao;
    @Autowired
    private DoctorAuthDao doctorAuthDao;

    public List<Map> getCarte(String eatTm, String mealTm) throws Exception {
        String userId = CurrentUser.getUser().getUserId();
        DoctorAuthVO auth = this.doctorAuthDao.getDoctorAuthRepository().getByCUserId(userId);

        String dptIds = "";
        if(auth!=null && auth.getcDptId()!=null&&!"".equals(auth.getcDptId())){
            dptIds = dptIds+"'"+auth.getcDptId()+"'";
        }
        if(auth!=null && auth.getcDptId1()!=null&&!"".equals(auth.getcDptId1())){
            dptIds = dptIds+("".equals(dptIds)?"'":",'"+auth.getcDptId1()+"'");
        }
        if(auth!=null && auth.getcDptId2()!=null&&!"".equals(auth.getcDptId2())){
            dptIds = dptIds+("".equals(dptIds)?"'":",'"+auth.getcDptId2()+"'");
        }
        return this.carteDao.getCarte(eatTm,mealTm,dptIds);
    }
}
