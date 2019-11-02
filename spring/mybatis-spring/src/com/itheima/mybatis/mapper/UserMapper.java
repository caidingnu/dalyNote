package com.itheima.mybatis.mapper;

import com.itheima.mybatis.pojo.User;

import java.util.List;

public interface UserMapper {


    public User findUserById(Integer id);

    List<User> queryUserByUsername(String username);
}
