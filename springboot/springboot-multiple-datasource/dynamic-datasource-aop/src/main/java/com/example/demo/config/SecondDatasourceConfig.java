package com.example.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class SecondDatasourceConfig {
    @Bean(name = "secondDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public HikariDataSource dataSource() {
        return new HikariDataSource();
    }
    @Bean(name = "secondTransactionManager")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(this.dataSource());
    }

//    @Bean(name = "secondSqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("secondDataSource") DataSource dataSource) throws Exception {
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
//        return sessionFactory.getObject();
//    }
}