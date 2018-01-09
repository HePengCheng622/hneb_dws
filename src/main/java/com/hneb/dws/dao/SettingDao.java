package com.hneb.dws.dao;

import com.hneb.dws.dao.repository.SettingRepository;
import com.hneb.dws.vo.SettingVO;
import com.hneb.fwk.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/10/13 0013.
 */
@Repository
public class SettingDao extends BaseDao {
    @Autowired
    private SettingRepository settingRep;

    public SettingRepository getSettingRep() {
        return settingRep;
    }

    public void setSettingRep(SettingRepository settingRep) {
        this.settingRep = settingRep;
    }
}
