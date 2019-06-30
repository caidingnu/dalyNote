package com.study.privoder.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.privoder.entity.Emp;
import com.study.privoder.entity.pojo.ComAndEmp;
import com.study.privoder.mapper.EmpMapper;
import com.study.privoder.service.EmpService;
import com.study.privoder.util.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * desc：
 * author CDN
 * create 2019-06-29 16:08
 * version 1.0.0
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Resource
    EmpMapper empMapper;

    @Override
    public List<Emp> or(Emp emp) {
        return empMapper.or(emp);
    }

    @Override
    public List<Emp> and(Emp emp) {
        return empMapper.and(emp);
    }

    @Override
    public List<Emp> condition(String name) {

        return empMapper.condition(name);
    }

    @Override
    public int delete(String id) {
        return empMapper.delete(id);
    }

    @Override
    public int update(Emp emp) {
        if (emp.getEmpId() <= 0) {
            System.out.println("impl----------------------------------------");
            return 000;
        }
        return empMapper.update(emp);
    }


    @Override
    public List<Emp> selectAll(String keyword) {
        List<Emp> list = empMapper.all(keyword);
        return list;
    }

    @Override
    public List<Emp> selectAllPage(String keyword) {
        int pageIndex = 0;
        int pageSize = 2;
        PageHelper.startPage(pageIndex, pageSize);
        List<Emp> list = empMapper.all(keyword);
        PageInfo<Emp> result = new PageInfo<Emp>(list);
        List<Emp> emps = result.getList();
        return emps;
    }

    @Override
    public int add(Emp emp) {
        return empMapper.add(emp);
    }

    @Override
    @Transactional
    public int batchAdd() {
        List<Emp> list1 = new ArrayList<>();
        list1.add(new Emp(8, "是是是", "33", "男"));
        list1.add(new Emp(9, "是是是1", "33", "男"));
        list1.add(new Emp(10, "是是是2", "33", "男"));
        list1.add(new Emp(11, "是是是3", "33", "男"));
        list1.add(new Emp(12, "是是是4", "33", "男"));
        int a = empMapper.batchAdd(list1);
        return a;
    }

    public List<ComAndEmp> select() {


        return empMapper.select();
    }
}
