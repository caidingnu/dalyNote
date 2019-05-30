package com.cdn.configclient.mapper;

import com.cdn.configclient.entity.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpMapper {
    int deleteByPrimaryKey(Integer empId);

    int insert(Emp record);

    int insertSelective(Emp record);

    Emp selectByPrimaryKey(Integer empId);

    List<Emp> selectList();

    int updateByPrimaryKeySelective(Emp record);

    int updateByPrimaryKey(Emp record);
}