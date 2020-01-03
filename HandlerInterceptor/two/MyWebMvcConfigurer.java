package com.rx.erp.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Bean //将自定义拦截器注册到spring bean中,解决在LoginInterceptor 中注入配置文件参数为null
    public  LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        List<String> excludePathPatterns=new ArrayList<String>(){{
            add("/admin/user/login");
            add("/admin/user/logout");
        }};
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns(excludePathPatterns);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
