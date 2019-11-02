package com.cdn.text.JdbcDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * desc：类上获取配置文件
 * author CDN
 * create 2019-07-27 00:14
 * version 1.0.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:resources/zhujie/applicationContext.xml")
public class Jdbc3 {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Map<String, Object>> select() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select menun_name ,id from menu");
        System.out.println(list);
        for (Map<String, Object> stringObjectMap : list) {
            System.out.println(stringObjectMap.get("menun_name"));
        }

        return list;
    }

    @Test
    public void update() {
        String sql = "update menu set menun_name=? where uuid=? ";
        int result = jdbcTemplate.update(sql, "蔡定努", "wwww");
        System.out.println(result);
    }


    @Test
    public void insert() {
        String sql = "INSERT into menu (uuid,menun_name,id) VALUES(?,?,?)";
        int result = jdbcTemplate.update(sql, "wwww2", "蔡定努", 33);
        System.out.println(result);
    }

    @Test
    public void delete() {
        String sql = "delete from menu where uuid=?";
        int result = jdbcTemplate.update(sql, "wwww2");
        System.out.println(result);
    }
}
