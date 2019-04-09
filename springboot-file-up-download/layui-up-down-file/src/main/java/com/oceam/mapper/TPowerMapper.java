package com.oceam.mapper;

import com.oceam.entity.TPower;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TPowerMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(TPower record);

    int insertSelective(TPower record);

    TPower selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(TPower record);

    int updateByPrimaryKeyWithBLOBs(TPower record);

    int updateByPrimaryKey(TPower record);

    List<TPower> selectAll(@Param(value = "roleName") String roleName);
}