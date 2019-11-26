package com.model.demo.config;

import com.model.demo.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Copyright (C), 2015-2019, 风尘博客
 * 公众号 : 风尘博客
 * FileName: GlobalExceptionHandler
 *
 * @author: Van
 * Date:     2019-09-25 22:09
 * Description: Web 配置
 * Version： V1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  /**
   * 添加jwt拦截器
   * @param registry
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(jwtInterceptor())
            .addPathPatterns("/jwt/**");
  }

  /**
   * jwt拦截器
   * @return
   */
  @Bean
  public JwtInterceptor jwtInterceptor() {
    return new JwtInterceptor();
  }
}