package com.itheima.mybatis.dao;

import com.itheima.mybatis.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * desc：原始方式spring和Mybatis整合
 * author CDN
 * create 2019-07-27 16:31
 * version 1.0.0
 */

public class YuanShiTest {

    /**
     * desc:
     * param:
     * author: CDN
     * date: 2019/7/27
     */
    @Test
    public void get() {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("resources/applicationContext.xml");
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        User user = userDao.selectById(32);
        System.out.println(user);
    }
}
