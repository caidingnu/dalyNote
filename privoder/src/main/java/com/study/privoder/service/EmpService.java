package com.study.privoder.service;

import com.study.privoder.entity.Emp;
import com.study.privoder.entity.pojo.ComAndEmp;
import com.study.privoder.mapper.provider.EmpProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * desc:
 * author caidingnu
 * create 2019/6/29
 * version 1.0.0
 */
public interface EmpService {

    List<Emp> selectAllPage(String keyword);

    List<Emp> selectAll(String keyword);

    //    或者查询
    List<Emp> or(Emp emp);

    //    添加
    int add(Emp emp);

    //and查询
    List<Emp> and(Emp emp);

    //    条件查询
    List<Emp> condition(String name);


    int delete(String id);

    int update(Emp emp);

    int batchAdd();


    //    关联查查询
    List<ComAndEmp> select();
}
