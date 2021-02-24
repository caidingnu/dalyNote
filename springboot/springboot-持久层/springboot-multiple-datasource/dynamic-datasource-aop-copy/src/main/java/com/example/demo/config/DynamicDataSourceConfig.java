package com.example.demo.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

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
    private DataSource primaryDataSource;

    /**
     * 从数据源
     */
    @Autowired(required = false)
    @Qualifier("secondDataSource")
    private DataSource secondDataSource;

    @Autowired
    private Environment env;

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
            dataSourceMap.put("slave", secondDataSource);
        }
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        return dynamicDataSource;
    }

    @Primary
    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
//################################ 方法一 直接写死 ########################################
//        配置mybatis的xml的位置
//        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        //        指定数据库隐射实体
//        sqlSessionFactoryBean.setTypeAliasesPackage("com.example.demo.entity");

//         ######################### 方法二 获取配置文件的配置   #################################
        // 指定xml文件位置
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
//        指定数据库隐射实体
        sqlSessionFactoryBean.setTypeAliasesPackage(env.getProperty("type-aliases-package"));
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();
    }


    @Primary
    @Bean(name = "dynamicDataSourceTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("dynamicDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
