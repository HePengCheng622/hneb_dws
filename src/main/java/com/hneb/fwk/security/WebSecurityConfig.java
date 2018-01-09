package com.hneb.fwk.security;

import com.hneb.fwk.cas.CasProperties;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
//@PropertySource("classpath:application.properties")
@EnableConfigurationProperties(CasProperties.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private MyAuthenticationProvider authenticationProvider;//自定义验证

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private CasProperties casProperties;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //启用cas
        if(casProperties.getEnabled()){
            auth.authenticationProvider(casAuthenticationProvider());
        }else{
            auth.authenticationProvider(authenticationProvider);
            auth.userDetailsService(userDetailsService);
        };

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //启用cas
        if(casProperties.getEnabled()){
            http
                .headers()
                    .frameOptions().sameOrigin().disable()//disable X-Frame-Options
                .authorizeRequests()//配置安全策略
                    .antMatchers("/login").permitAll()//定义/请求不需要验证
                    .antMatchers("/login/**").permitAll()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/images/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/plugin/**").permitAll()
                    .anyRequest().authenticated()//其余的所有请求都需要验证
                .and()
                    .logout().permitAll()//定义logout不需要验证
                .and()
                    .formLogin()//使用form表单登录
                .and()
                    .exceptionHandling().authenticationEntryPoint(casAuthenticationEntryPoint())
                .and()
                    .addFilter(casAuthenticationFilter())
                    .addFilterBefore(casLogoutFilter(), LogoutFilter.class)
                    .addFilterBefore(singleSignOutFilter(), CasAuthenticationFilter.class);
        }else{
            http
                .headers()
                    .frameOptions().sameOrigin().disable()//disable X-Frame-Options
                .authorizeRequests()
                    .antMatchers("/pages/login.html").permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/login/**").permitAll()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/images/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/plugin/**").permitAll()
                    .anyRequest().fullyAuthenticated()//其他url需要鉴权
                .and()
                    .formLogin()
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginPage("/pages/login.html")
                        .loginProcessingUrl("/login")
                        .failureUrl("/pages/login.html?error=true")
                        .defaultSuccessUrl("/pages/index.html")
                        .permitAll()
                .and()
                    .logout()
                        .deleteCookies("JSESSIONID")
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/pages/login.html");
        }


        http.csrf().disable(); //禁用CSRF
    }


    /**认证的入口*/
    @Bean
    public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
        CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
        casAuthenticationEntryPoint.setLoginUrl(casProperties.getCasServerLoginUrl());
        casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
        return casAuthenticationEntryPoint;
    }

    /**指定service相关信息*/
    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService(casProperties.getAppServerUrl() + casProperties.getAppLoginUrl());
        serviceProperties.setAuthenticateAllArtifacts(true);
        return serviceProperties;
    }

    /**CAS认证过滤器*/
    @Bean
    public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
        CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
        casAuthenticationFilter.setAuthenticationManager(authenticationManager());
        casAuthenticationFilter.setFilterProcessesUrl(casProperties.getAppLoginUrl());
        return casAuthenticationFilter;
    }

    /**cas 认证 Provider*/
    @Bean
    public CasAuthenticationProvider casAuthenticationProvider() {
        CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
//        casAuthenticationProvider.setAuthenticationUserDetailsService(customAuthUserDetailsService());
        casAuthenticationProvider.setUserDetailsService(customUserDetailsService()); //这里只是接口类型，实现的接口不一样，都可以的。
        casAuthenticationProvider.setServiceProperties(serviceProperties());
        casAuthenticationProvider.setTicketValidator(cas20ServiceTicketValidator());
        casAuthenticationProvider.setKey("casAuthenticationProviderKey");
        return casAuthenticationProvider;
    }

    @Bean
    public UserDetailsService customUserDetailsService(){
        return new MyUserDetailsService();
    }

    @Bean
    public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
        return new Cas20ServiceTicketValidator(casProperties.getCasServerUrl());
    }

    /**单点登出过滤器*/
    @Bean
    public SingleSignOutFilter singleSignOutFilter() {
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setCasServerUrlPrefix(casProperties.getCasServerUrl());
        singleSignOutFilter.setIgnoreInitConfiguration(true);
        return singleSignOutFilter;
    }

    /**请求单点退出过滤器*/
    @Bean
    public LogoutFilter casLogoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter(casProperties.getCasServerLogoutUrl(), new SecurityContextLogoutHandler());
        logoutFilter.setFilterProcessesUrl(casProperties.getAppLogoutUrl());
        return logoutFilter;
    }
}