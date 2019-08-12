package com.demo.spring;

import com.demo.spring.UserDao;

/**
 * desc：
 * author CDN
 * create 2019-07-26 22:02
 * version 1.0.0
 */
public class UserDaoImpl implements UserDao {

    private String username;
    private String job;

    public UserDaoImpl(String job) {
        this.job = job;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void save(String text) {
        System.out.println("UserServiceImpl的方法执行了,"+text+"----姓名："+username+",工作："+job);
    }


}
