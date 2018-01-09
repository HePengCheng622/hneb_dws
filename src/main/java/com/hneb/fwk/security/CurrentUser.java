package com.hneb.fwk.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by Administrator on 2017/10/17.
 */
public class CurrentUser {

    public static MyUser getUser(){
//        return (MyUser) SecurityContextHolder.getContext()
//                .getAuthentication()
//                .getPrincipal();
        //if not in unit test environment, get the current user using acegi
        if ((SecurityContextHolder.getContext() == null)
                || !(SecurityContextHolder.getContext() instanceof SecurityContext)
                || (((SecurityContext) SecurityContextHolder.getContext()).getAuthentication() == null)) {
            return null;
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() == null) {
            return null;
        }

        MyUserDetails user = null;
        if (auth.getPrincipal() instanceof MyUserDetails) {
            user = (MyUserDetails)auth.getPrincipal();
        }
        return (MyUser) user;
    }
}
