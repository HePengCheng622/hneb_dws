package com.hneb.dws.service;

import com.hneb.dws.dao.SvcPriceDao;
import com.hneb.dws.dao.repository.SvcPriceRepository;
import com.hneb.dws.vo.SettingVO;
import com.hneb.dws.vo.SvcPriceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/10/13 0013.
 */
@Service
public class SvcPriceService {
    @Autowired
    private SvcPriceDao svcPriceDao;
}
