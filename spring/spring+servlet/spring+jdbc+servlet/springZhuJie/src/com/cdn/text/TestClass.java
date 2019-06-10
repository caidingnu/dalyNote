package com.cdn.text;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * desc：spring的IOC注解开发测试类
 * author CDN
 * create 2019-06-09 13:46
 * version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:resources/applicationContext.xml")
public class TestClass {
    @Resource
    ServiceImp ServiceImp;

    /**
     * desc: 传统方式
     * param:
     * author: CDN
     * date: 2019/6/9
     */
    @Test
    public void demo1() {
        UserDao u = new UserDaoImpl();
        u.save();
    }

    /**
     * desc:  注解方式
     * param:
     * author: CDN
     * date: 2019/6/9
     */
    @Test
    public void demo2() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("resources/applicationContext.xml");
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        userDao.save();
    }


    @Test
    public void demo3() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("resources/applicationContext.xml");
        ServiceImp ServiceImp = (ServiceImp) applicationContext.getBean("serviceImp");
        ServiceImp.aa();
    }


    /**
     * 在类上加载配置文件
     */
    @Test
    public void demo4() {
        ServiceImp.aa();
    }

}
