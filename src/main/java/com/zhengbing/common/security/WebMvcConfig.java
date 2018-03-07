package com.zhengbing.common.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by zhengbing on 2017-11-01.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("admin/login");
        registry.addViewController("/recommand").setViewName("recommand");
        registry.addViewController("/report").setViewName("report");
        registry.addViewController("/vip").setViewName("vip");
        registry.addViewController("/normal").setViewName("normal");
    }
}