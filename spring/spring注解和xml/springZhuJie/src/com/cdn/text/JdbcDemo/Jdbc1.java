package com.cdn.text.JdbcDemo;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.util.List;

/**
 * desc：jdbc模板
 * author CDN
 * create 2019-06-09 15:07
 * version 1.0.0
 */
public class Jdbc1 {

    /**
     * desc:
     * param:
     * author: CDN
     * date: 2019/6/9
     */
    @Test
    public List select() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/caidingnu?characterEncoding=utf8&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
//        创建Jdbc模板
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List list = jdbcTemplate.queryForList("select * from menu");
        return list;
    }
}
