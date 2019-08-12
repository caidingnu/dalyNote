package com.cdn.text.zhujie;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

/**
 * desc：注解方式(直接获取配置文件)
 * author CDN
 * create 2019-06-10 11:42
 * version 1.0.0
 */
public class TestZhuJie2 {


    @Test
    public void t() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("resources/zhujie/applicationContext.xml");
        UserDaoImpl userDaoImpl = (UserDaoImpl) applicationContext.getBean("userDaoImpl");
        userDaoImpl.save();
    }
}
