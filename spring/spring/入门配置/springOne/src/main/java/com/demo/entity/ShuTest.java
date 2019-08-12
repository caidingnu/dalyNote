package com.demo.entity;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * desc：复杂注入测试
 * author CDN
 * create 2019-07-26 23:36
 * version 1.0.0
 */
public class ShuTest {


    /**
     * desc:
     * param:
     * author: CDN
     * date: 2019/7/26
     */
    @Test
    public void  tt(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("config/applicationContext.xml");
        User user = (User) applicationContext.getBean("user");
      System.out.println(user.toString());
    }
}
