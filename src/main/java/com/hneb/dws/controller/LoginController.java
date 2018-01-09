package com.hneb.dws.controller;

import com.hneb.fwk.domain.Result;
import com.hneb.fwk.utils.ResultUtils;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import java.util.Enumeration;

import static java.lang.System.out;

/**
 * Created by Administrator on 2017/10/18.
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/exception")
    @ResponseBody
    public Result getLoginFailureMsg(HttpSession session){
        Object attrValue = session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        Exception exptionObj = null;
        if(attrValue!=null){
            exptionObj = (Exception)attrValue;
            String msg = exptionObj.getMessage();
            System.out.println(msg);
            if("username not found.".equals(msg)){
                msg="用户名不存在";
            }else if("Bad credentials".equals(msg)){
                msg="密码错误";
            }else{
                msg="未知错误";
            }
            return ResultUtils.error(null,msg);
        }else{
            return ResultUtils.success();
        }
//        Enumeration<String> attributeNames = session.getAttributeNames();
//        while(attributeNames.hasMoreElements()){
//            String tmp = attributeNames.nextElement();
//            System.out.println(tmp+"==>"+session.getAttribute(tmp));
//        }
//        out.print(msg);

    }
}
