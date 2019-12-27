package com.study.shiro.dao;


import com.study.shiro.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    /**
     * 登录的时候，根据用户名获取用户实体
     *
     * @param userName
     * @return
     */
    @Select( "select * from t_user where user_name=#{userName}")
    User getUserByUserName(String userName);

    @Insert("insert into t_user(user_name,password,remarks) values (#{userName},#{password},#{remarks})")
    int register(User user);
}
