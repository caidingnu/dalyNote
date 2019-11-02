package com.cdn.text.JdbcDemo;

import com.cdn.text.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * desc： 将连接池和创建JDBC模板都交给spring
 * author CDN
 * create 2019-06-09 15:24
 * version 1.0.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:resources/applicationContext.xml")
public class Jdbc2 {

    //    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * desc:
     * param:
     * author: CDN
     * date: 2019/6/9
     */
//    @Test
    public List<Map<String, Object>> select() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("resources/applicationContext.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
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
