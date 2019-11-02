package com.tese.boot.mapper;

import com.tese.boot.entity.User;
import com.tese.boot.entity.pojo.LoginReturn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    List<LoginReturn> selectByid(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<LoginReturn> selectlogin(User user);

    String selectId(User user);


    List<User> selectAcc();
}