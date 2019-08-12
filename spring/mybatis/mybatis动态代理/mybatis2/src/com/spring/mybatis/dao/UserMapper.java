package com.spring.mybatis.dao;


import com.spring.mybatis.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * desc:
 * author caidingnu
 * create 2019/6/10
 * version 1.0.0
 */
@Component
public interface UserMapper {

     Menu selectById(String uuid);
}
