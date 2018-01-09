package com.hneb.dws.service;

import com.hneb.dws.dao.UserDao;
import com.hneb.dws.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/10/13 0013.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
}
