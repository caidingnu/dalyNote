package com.cdn.text.xml;

import com.cdn.text.zhujie.UserDao;
import com.cdn.text.zhujie.UserDaoImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * desc：spring的IOC注解开发测试类  XML方式
 * author CDN
 * create 2019-06-09 13:46
 * version 1.0.0
 */

public class TestXml {


    /**
     * desc: 传统方式
     * param:
     * author: CDN
     * date: 2019/6/9
     */
    @Test
    public void demo1() {
        UserDao u = new ServiceImpl();
        u.save();
    }


    /**
     * xml方式
     */
    @Test
    public void demo2() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("resources/xml/applicationContextXML.xml");
        ServiceImpl serviceImpl = (ServiceImpl) applicationContext.getBean("serviceImpl");
        serviceImpl.save();
    }


}
