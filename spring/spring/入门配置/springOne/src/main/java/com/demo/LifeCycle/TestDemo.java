package com.demo.LifeCycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * desc：
 * author CDN
 * create 2019-07-26 22:48
 * version 1.0.0
 */
public class TestDemo {


    /**
     * desc: 生命周期测试方法
     * param:
     * author: CDN
     * date: 2019/7/26
     */
    @Test
    public void ce() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/applicationContext.xml");
        LifeDao lifeDao = (LifeDao) applicationContext.getBean("lifeDao");
        lifeDao.my("测试生命周期");

//销毁
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }

}
