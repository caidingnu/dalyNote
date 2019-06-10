package com.cdn.text.xml;

import com.cdn.text.zhujie.UserDao;

/**
 * desc：
 * author CDN
 * create 2019-06-10 11:39
 * version 1.0.0
 */
public class ServiceImpl implements UserDao {
    private String name;

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public void save() {
        System.out.println("ServiceImpl方法执行"+"---"+name);
    }
}
