package com.hneb.dws.service;

import com.hneb.dws.dao.SettingDao;
import com.hneb.dws.dao.repository.SettingRepository;
import com.hneb.dws.vo.SettingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/10/13 0013.
 */
@Service
public class SettingService {

    @Autowired
    private SettingDao settingDao;

}
