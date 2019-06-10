package com.tezxt.te.dao;

import com.tezxt.te.model.Userinfo;

public interface UserinfoMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);
}