package com.study.privoder.mapper;

import com.study.privoder.entity.Emp;
import com.study.privoder.entity.pojo.ComAndEmp;
import com.study.privoder.mapper.provider.ComAndEmpProvider;
import com.study.privoder.mapper.provider.EmpProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * desc:
 * author caidingnu
 * create 2019/6/29
 * version 1.0.0
 */
public interface EmpMapper {
    //    或者查询
    @SelectProvider(type = EmpProvider.class, method = "or")
    List<Emp> or(Emp emp);


    //and查询
    @SelectProvider(type = EmpProvider.class, method = "and")
    List<Emp> and(Emp emp);

    //    增加
    @InsertProvider(type = EmpProvider.class, method = "add")
    int add(Emp emp);

    //    查询所有
    @SelectProvider(type = EmpProvider.class, method = "all")
    List<Emp> all(String keyword);

    //    条件查询  Provider
//    @SelectProvider(type = EmpProvider.class, method = "condition")
//    直接注解方式
    @Select("select * from emp where emp_name=#{name}")
    List<Emp> condition(String name);

    //    删除
    @DeleteProvider(type = EmpProvider.class, method = "delete")
    int delete(String id);

    //    修改
    @UpdateProvider(type = EmpProvider.class, method = "update")
    int update(Emp emp);

    //    批量增加
    @InsertProvider(type = EmpProvider.class, method = "batchAdd")
    int batchAdd(List<Emp> list);


    //    关联查查询
    @SelectProvider(type = ComAndEmpProvider.class, method = "inner")
    List<ComAndEmp> select();

}
