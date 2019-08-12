package com.itheima.mybatis.dao;

import com.itheima.mybatis.pojo.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;

/**
 * 原始Dao开发
 *
 * @author lx
 */
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {



    @Override
    public User selectById(Integer id) {
//        com.itheima.mybatis.mapper.UserMapper xml中的namespace
        return this.getSqlSession().selectOne("com.itheima.mybatis.mapper.UserMapper.findUserById",id);
    }
}
