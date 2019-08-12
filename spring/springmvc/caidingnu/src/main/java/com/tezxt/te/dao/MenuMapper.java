package com.tezxt.te.dao;

import com.tezxt.te.model.Menu;

public interface MenuMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
}