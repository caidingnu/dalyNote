package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 * desc：指定读取多个配置配置文件
 * author CDN
 * create 2019-07-21 14:16
 * version 1.0.0
 */
@PropertySource(value= {"classpath:config/jdbc.properties", "classpath:config/cdn.properties"})
@Service
public class Te {

//    jdbc.properties
    @Value("${jdbc.url}")
    private String jdbcUrl;

//    cdn.properties
    @Value("${cdn.name}")
    private String cdnName;

//    application.yml
    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getCdnName() {
        return cdnName;
    }

    public String getDataSourceUrl() {
        return dataSourceUrl;
    }
}
