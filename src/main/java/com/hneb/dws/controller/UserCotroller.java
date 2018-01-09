package com.hneb.dws.controller;

import com.hneb.dws.dao.UserDao;
import com.hneb.dws.vo.UserVO;
import com.hneb.fwk.domain.Result;
import com.hneb.fwk.security.CurrentUser;
import com.hneb.fwk.security.MyUser;
import com.hneb.fwk.security.SecurityProperties;
import com.hneb.fwk.utils.ResultUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/10/13 0013.
 */
@RestController
@RequestMapping("/user")
public class UserCotroller {

    @Autowired
    private UserDao userDao;
    @RequestMapping("/modifyPassword")
    public Result modifyPassword(@RequestBody JSONObject json){
        String userId = json.getString("cUserId");
        String newPassword = passwordEncoder().encodePassword(json.getString("newPassword").trim(),"");
        String oldPassword = passwordEncoder().encodePassword(json.getString("oldPassword").trim(),"");
        UserVO user = userDao.getUserRep().getUserVOByCUserId(userId);
        if(user!=null&&user.getcPassword().equals(oldPassword)){
            user.setcPassword(newPassword);
            userDao.getUserRep().saveAndFlush(user);
            return ResultUtils.success("修改成功");
        }else{
            return ResultUtils.error(-1,"密码错误,请重新输入");
        }
    }

    @RequestMapping("/userInfo")
    public Result userInfo(){
        String userId = CurrentUser.getUser().getUserId();
        UserVO userVO = userDao.getUserRep().getUserVOByCUserId(userId);
        return ResultUtils.success(userVO);
    }

    @Bean
    public Md5PasswordEncoder passwordEncoder(){

        return new Md5PasswordEncoder();
    }
}
