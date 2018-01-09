package com.hneb.fwk.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 自定义验证方式
 */
@Component

public class MyAuthenticationProvider implements AuthenticationProvider {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private MyUserDetailsService userService;

    @Override
    public Authentication authenticate(Authentication authentication)
                                                throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();


        UserDetails user = userService.loadUserByUsername(username);

        LOGGER.info("password={}, needPassword={}", password, user.getPassword());
        //密码匹配验证
        if (passwordEncoder().encodePassword(password,"").equals(user.getPassword())) {
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            return new UsernamePasswordAuthenticationToken(user, password, authorities);
        }
//        if (password.equals(user.getPassword())) {
//            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
//            return new UsernamePasswordAuthenticationToken(user, password, authorities);
//        }

        throw new BadCredentialsException("Wrong password.");
    }

   @Bean
   public Md5PasswordEncoder passwordEncoder(){

       return new Md5PasswordEncoder();
   }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}