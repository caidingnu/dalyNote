package com.oceam.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MywebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 拦截器按照顺序执行
         *addPathPatterns 用于添加拦截
         *excludePathPatterns 用于排除拦截
         */
        registry.addInterceptor(new OneInterceptor()).addPathPatterns("/a")  //把 /a的全部拦截到OneInterceptor
                .excludePathPatterns("/login"); //login不拦截


        registry.addInterceptor(new TwoInterceptor()).addPathPatterns("/two/**") //把 /two下的全部拦截到OneInterceptor
                .addPathPatterns("/one/**")
                .addPathPatterns("/three/**");


        super.addInterceptors(registry);
    }

    // 这个方法是用来配置静态资源的，比如html，js，css，等等
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

}
