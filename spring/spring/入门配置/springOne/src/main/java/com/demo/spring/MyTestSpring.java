package com.demo.spring;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * desc：spring方式
 * author CDN
 * create 2019-07-26 22:13
 * version 1.0.0
 */
public class MyTestSpring {

    /**
     * desc:
     * param:
     * author: CDN
     * date: 2019/7/26
     */
    @Test
    public void demo() {
//    创建spring工厂
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/applicationContext.xml");

//        id的方式创建对象
        UserDao userDaoId = (UserDao) applicationContext.getBean("userDao");
        userDaoId.save("id的方式创建对象");
        //        name的方式创建对象=============================
        UserDao userDaoName = (UserDao) applicationContext.getBean("userDao2");
        userDaoName.save(" name的方式创建对象");
    }


}
