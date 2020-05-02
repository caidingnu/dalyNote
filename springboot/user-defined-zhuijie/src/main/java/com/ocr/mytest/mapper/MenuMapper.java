package com.ocr.mytest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

/**
 * desc:
 * author caidingnu
 * create 2019/10/21
 * version 1.0.0
 */
@Mapper
@MapperScan("")
public interface MenuMapper {

    @Select("select * from menu")
    List<Map<String, Object>> selectAll();
}
