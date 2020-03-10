package com.aa.bb.dao;

import com.aa.bb.model.Menu;

public interface MenuMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
}