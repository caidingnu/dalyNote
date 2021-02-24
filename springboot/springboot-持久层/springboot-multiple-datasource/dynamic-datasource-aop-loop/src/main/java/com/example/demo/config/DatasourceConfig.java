package com.example.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@Configuration
public class DatasourceConfig {
    @Autowired
    Environment env;

    /**
     * 主数据源
     * @return
     */
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public HikariDataSource primaryDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
        return hikariDataSource;
    }

    /**
     * 其他数据源
     * @return
     */
    @Bean(name = "otherDataSource")
    public List<HikariDataSource> dataSource() {
        List<HikariDataSource> hikariDataSourceList = new ArrayList<>();
        String property = env.getProperty("datasourceName");
        if (property != null) {
            String[] datasourcesNames = property.split(",");
            for (String datasourcesName : datasourcesNames) {
                String driverClassName = env.getProperty("spring.datasource." + datasourcesName + ".driver-class-name");
                String jdbcUrl = env.getProperty("spring.datasource." + datasourcesName + ".jdbc-url");
                String username = env.getProperty("spring.datasource." + datasourcesName + ".username");
                String password = env.getProperty("spring.datasource." + datasourcesName + ".password");
                HikariDataSource hikariDataSource = new HikariDataSource();
                hikariDataSource.setJdbcUrl(jdbcUrl);
                hikariDataSource.setUsername(username);
                hikariDataSource.setPassword(password);
                hikariDataSource.setDriverClassName(driverClassName);
                Properties properties = new Properties();
                properties.setProperty("datasourcesName", datasourcesName);
                hikariDataSource.setDataSourceProperties(properties);
                hikariDataSourceList.add(hikariDataSource);
            }
        }
        return hikariDataSourceList;
    }
}