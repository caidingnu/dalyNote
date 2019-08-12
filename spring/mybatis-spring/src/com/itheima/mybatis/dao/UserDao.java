package com.itheima.mybatis.dao;

import com.itheima.mybatis.pojo.User;

/**
 * 原始开发使用
 */
public interface UserDao {

    public User selectById(Integer id);

}
