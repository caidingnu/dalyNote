package com.oceam.mapper;

import com.oceam.entity.UserInfo;

import java.util.List;

public interface UserInfoMapper {

    int deleteByPrimaryKey(Integer userid);

    int insert(UserInfo record);


    int insertSelective(UserInfo record);


    UserInfo selectByPrimaryKey(Integer userid);


    int updateByPrimaryKeySelective(UserInfo record);


    int updateByPrimaryKey(UserInfo record);

    List findAllUser();
}