package com.oceam.mapper;

import com.oceam.entity.Excel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExcelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Excel record);

    int insertSelective(Excel record);

    Excel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Excel record);

    int updateByPrimaryKey(Excel record);
}