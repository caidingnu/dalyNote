package com.neo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * desc：
 * author CDN
 * create 2019-10-13 21:49
 * version 1.0.0
 */
@Configuration
public class FileStaticConfig implements WebMvcConfigurer {
    @Value("${filePath}")
    String savePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {  //如果是Windows系统
            registry.addResourceHandler("/app/**") //虚拟路劲
                    // /apple/**表示在磁盘apple目录下的所有资源会被解析为以下的路径
                    .addResourceLocations("file:"+savePath); //媒体资源
        } else {  //linux 和mac
            registry.addResourceHandler("/app/**") //虚拟路劲
                    .addResourceLocations("file:/resources/upload/") ;  //媒体资源
        }
    }
}
