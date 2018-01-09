package com.hneb.fwk.adapter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@ConfigurationProperties(prefix = "fwk")
@Configuration
public class DefaultView extends WebMvcConfigurerAdapter {


    private String welcome_file;

    public String getWelcome_file() {
        return welcome_file;
    }

    public void setWelcome_file(String welcome_file) {
        this.welcome_file = welcome_file;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry ) {
        registry.addViewController("/" ).setViewName( "forward:"+this.getWelcome_file() );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
    } 
}