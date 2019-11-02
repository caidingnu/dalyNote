package com.demo.Traditional;

import org.junit.jupiter.api.Test;

/**
 * desc：传统方式
 * author CDN
 * create 2019-07-26 22:03
 * version 1.0.0
 */
public class MyTest {


    /**
     * desc: 传统方式
     * param:
     * author: CDN
     * date: 2019/7/26
     */

    @Test
    public void testOne() {
        UserDao userService = new UserDaoImpl();
        userService.save();

    }

}
