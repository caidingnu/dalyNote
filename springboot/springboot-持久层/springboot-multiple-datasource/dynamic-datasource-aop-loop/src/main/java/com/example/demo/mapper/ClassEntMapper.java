package com.example.demo.mapper;

import com.example.demo.entity.ClassEnt;
import com.example.demo.entity.ClassEntExample;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface ClassEntMapper {
    int countByExample(ClassEntExample example);

    int deleteByExample(ClassEntExample example);

    int deleteByPrimaryKey(Integer classId);

    int insert(ClassEnt record);

    int insertSelective(ClassEnt record);

    List<ClassEnt> selectByExample(ClassEntExample example);

    ClassEnt selectByPrimaryKey(Integer classId);

    int updateByExampleSelective(@Param("record") ClassEnt record, @Param("example") ClassEntExample example);

    int updateByExample(@Param("record") ClassEnt record, @Param("example") ClassEntExample example);

    int updateByPrimaryKeySelective(ClassEnt record);

    int updateByPrimaryKey(ClassEnt record);

    @Insert("insert into class (class_name) values(#{className})")
    int  insertt(ClassEnt record);

    @Select("select * from class")
    List<ClassEnt> list();
}