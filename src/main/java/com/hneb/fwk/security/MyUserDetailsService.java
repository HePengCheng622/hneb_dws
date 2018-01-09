package com.hneb.fwk.security;

import com.hneb.ResultEnum;
import com.hneb.fwk.dao.BaseDao;
import com.hneb.fwk.exception.HnebException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class MyUserDetailsService  extends BaseDao implements UserDetailsService {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LOGGER.info("loadUserByUsername --> [{}]", username);

        List<Map> userList = null;
        List<Map> userRoleList = new ArrayList<Map>();
        try {

            userList = querySql(securityProperties.getUsersByUsernameQuery(),username);
            userRoleList = querySql(securityProperties.getAuthoritiesByUsernameQuery(),username);
//            userList = querySql("select c_user_id as userId,c_user_nme as userNme,c_password as password,c_phone_no as phoneNo,(select c_dpt_id from t_doctor_auth b where a.c_user_id=b.c_user_id) as dptId,(select c_office_id from t_doctor_auth b where a.c_user_id=b.c_user_id) as officeId from t_user a where c_phone_no=? and c_role in ('999','100','101','*')",username);
//            userRole = querySql(securityProperties.getUserRoleByUsernameQuery(),username).get(0);

        } catch (Exception e) {
            e.printStackTrace();
            throw new HnebException(ResultEnum.SYS_SPRING_SECURITY);
        }

        if (userList == null || userList.size() == 0) {
            throw new BadCredentialsException("username not found.");
        }

        Map userMap = userList.get(0);
        String userId = userMap.get("userId").toString();
        String userNme = userMap.get("userNme").toString();
        String password = userMap.get("password").toString();
        String phoneNo = userMap.get("phoneNo").toString();
        String dptId = userMap.get("dptId").toString();
        String officeId = userMap.get("officeId").toString();
        String roles[]= new String[userRoleList.size()];

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(int i=0;i<userRoleList.size();i++){
            String role = userRoleList.get(0).get("role").toString();
            roles[i]=role;
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+role);//必须ROLE_为前缀
            grantedAuthorities.add(grantedAuthority);
        }

        Boolean enabled=true,accountNonExpired=true,credentialsNonExpired=true,accountNonLocked=true;
        MyUser myUser = new MyUser(userId, userNme, password, phoneNo,roles,dptId,officeId,enabled,accountNonExpired, credentialsNonExpired,accountNonLocked, grantedAuthorities);
        return myUser;
    }

    public UserDetails loadUserDetails(CasAssertionAuthenticationToken casAssertionAuthenticationToken) throws UsernameNotFoundException {
        return loadUserByUsername(casAssertionAuthenticationToken.getName());
    }
}