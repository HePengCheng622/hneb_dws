package com.hneb.fwk.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */
public class MyUser extends User implements MyUserDetails{
    private String userId;
    private String username;
    private String phoneNo;
    private String[] roles;
    private String password;
    private String dptId;
    private String officeId;
    private Collection<? extends GrantedAuthority> authorities;

    public MyUser(String userId, String userNme, String password, String phoneNo,String roles[],String dptId,String officeId,boolean enabled,
                           boolean accountNonExpired, boolean credentialsNonExpired,
                           boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities)
            throws IllegalArgumentException {
        super(userNme, password, enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);
        this.setUserId(userId);
        this.setUsername(userNme);
        this.setPassword(password);
        this.setPhoneNo(phoneNo);
        this.setRoles(roles);
        this.setDptId(dptId);
        this.setOfficeId(officeId);
        setAuthorities(authorities);
    }


    @Override
    public String getUserId() {
        return this.userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public void setUsername(String username) {
        this.username=username;
    }

    @Override
    public String[] getRoles() {
        return this.roles;
    }

    @Override
    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    @Override
    public String getPhoneNo() {
        return this.phoneNo;
    }

    @Override
    public void setPhoneNo(String phoneNo) {
        this.phoneNo=phoneNo;
    }

    @Override
    public void setPassword(String password) {
        this.password=password;
    }

    @Override
    public String getDptId() {
        return this.dptId;
    }

    @Override
    public void setDptId(String dptId) {
        this.dptId = dptId;
    }

    @Override
    public String getOfficeId() {
        return this.officeId;
    }

    @Override
    public void setOfficeId(String officeId) {
        this.officeId=officeId;
    }

    @Override
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
