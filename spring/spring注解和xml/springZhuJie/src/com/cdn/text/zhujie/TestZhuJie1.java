package com.cdn.text.zhujie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * desc：注解方式(在类上获取配置文件)
 * author CDN
 * create 2019-06-10 11:42
 * version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:resources/zhujie/applicationContext.xml")
public class TestZhuJie1 {

    @Resource(name = "userDaoImpl")
    UserDao userDaoImpl;

    @Test
    public void t() {
        userDaoImpl.save();
    }
}
