package com.hneb.fwk.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by Administrator on 2017/10/16.
 */
public interface MyUserDetails  extends UserDetails {
    public String getUserId();
    public void setUserId(String userId);

    public String getUsername();
    public void setUsername(String username);

    public String[] getRoles();
    public void setRoles(String roles[]);

    public String getPhoneNo();
    public void setPhoneNo(String phoneNo);

    public String getPassword();
    public void setPassword(String password);

    public String getDptId();
    public void setDptId(String dptId);

    public String getOfficeId();
    public void setOfficeId(String officeId);


    public Collection<? extends GrantedAuthority> getAuthorities();
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities);
}
