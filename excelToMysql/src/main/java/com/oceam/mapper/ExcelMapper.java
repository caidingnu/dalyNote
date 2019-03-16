package com.oceam.mapper;

import com.oceam.entity.Excel;

public interface ExcelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Excel record);

    int insertSelective(Excel record);

    Excel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Excel record);

    int updateByPrimaryKey(Excel record);
}