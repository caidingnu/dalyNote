package com.cdn.text;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * desc：
 * author CDN
 * create 2019-06-09 14:15
 * version 1.0.0
 */
@Service
public class ServiceImp {

    @Resource
    UserDao userDao;

    public void aa() {
        userDao.save();
    }
}
