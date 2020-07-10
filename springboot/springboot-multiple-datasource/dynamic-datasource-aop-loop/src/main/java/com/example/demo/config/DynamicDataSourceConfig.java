package com.example.demo.config;

import com.example.demo.change.DynamicDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 动态数据源配置类
 */
@Configuration
public class DynamicDataSourceConfig {
    /**
     * 注入主数据源
     */
    @Autowired
    @Qualifier("primaryDataSource")
    private HikariDataSource primaryDataSource;

    /**
     * 从数据源
     */
    @Autowired(required = false)
    @Qualifier("otherDataSource")
    private List<HikariDataSource> secondDataSource;


    @Autowired
    Environment env;


    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        //动态数据源对象
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(primaryDataSource);
        //配置多数据源
        Map<Object, Object> dataSourceMap = new HashMap<>();
        //填入主数据源
        dataSourceMap.put("master", primaryDataSource);
        //如果从数据源存在，填入从数据源
        if (secondDataSource != null) {
            for (HikariDataSource hikariDataSource : secondDataSource) {
                dataSourceMap.put(hikariDataSource.getDataSourceProperties().get("datasourcesName"), hikariDataSource);
            }
        }
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        return dynamicDataSource;
    }

    @Primary
    @Bean(name = "dynamicDataSourceSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        String mapperLocation = env.getProperty("mybatis.mapper-locations");
        String typeAliasesPackage = env.getProperty("mybatis.type-aliases-package");
        String underscoreToCamelCase = env.getProperty("mybatis.configuration.map-underscore-to-camel-case");
        if (mapperLocation != null) {
            sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocation));
        } else {
            sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        }
        if (typeAliasesPackage != null) {
            sessionFactory.setTypeAliasesPackage(typeAliasesPackage);
        }
        Objects.requireNonNull(sessionFactory.getObject()).getConfiguration().setMapUnderscoreToCamelCase(underscoreToCamelCase != null);
        return sessionFactory.getObject();
    }

    @Primary
    @Bean(name = "dynamicDataSourceTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("dynamicDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}
