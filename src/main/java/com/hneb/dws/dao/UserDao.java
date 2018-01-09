package com.hneb.dws.dao;

import com.hneb.dws.dao.repository.UserRepository;
import com.hneb.dws.vo.UserVO;
import com.hneb.fwk.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/10/13 0013.
 */
@Repository
public class UserDao extends BaseDao {
    @Autowired
    private UserRepository userRep;

    public UserRepository getUserRep() {
        return userRep;
    }

    public void setUserRep(UserRepository userRep) {
        this.userRep = userRep;
    }
}
