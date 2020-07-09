package com.study.wxapp.config.dataSourceConfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author gengqiang
 * @date 2018/5/3
 */
@Configuration
public class DruidConfig {

    private Logger logger = LoggerFactory.getLogger(DruidConfig.class);

    /**
     * 主据源
     *
     * @return
     */
    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();

    }

    /**
     * 从数据源1
     *
     * @return
     */
    @Bean(name = "readDataSource0")
    @ConfigurationProperties(prefix = "spring.db.read0")
    public DataSource readDataSource0() {
        return DataSourceBuilder.create().type(com.alibaba.druid.pool.DruidDataSource.class).build();

    }

    /**
     * 从数据源2
     *
     * @return
     */
    @Bean(name = "readDataSource1")
    @ConfigurationProperties(prefix = "spring.db.read1")
    public DataSource readDataSource1() {
        return DataSourceBuilder.create().type(com.alibaba.druid.pool.DruidDataSource.class).build();

    }

}