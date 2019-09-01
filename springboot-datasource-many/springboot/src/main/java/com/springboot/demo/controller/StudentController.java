package com.springboot.demo.controller;

import java.util.Date;

import com.springboot.demo.dao.test1.StudentMapper;
import com.springboot.demo.entity.test1.Student;
import com.springboot.demo.entity.test1.StudentExample;
import com.springboot.demo.entity.test2.Teacher;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * desc：
 * author CDN
 * create 2019-09-01 02:02
 * version 1.0.0
 */
@RestController
public class StudentController {


    @Resource
    StudentMapper studentMapper;

    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2019/9/1
     */
    @RequestMapping("all")
    public Object all() {
        StudentExample studentExample = new StudentExample();
        studentExample.setOrderByClause("id desc , name asc");
        studentExample.createCriteria().andNameEqualTo("刘备22").andIdEqualTo(10);
        List<Student> students = studentMapper.selectByExample(null);

        List<String> list = new ArrayList<>();
        for (Student student : students) {
            list.add(student.getName());
        }
        return list;
    }


    @RequestMapping("add1")
    @Transactional
    public int add() {
        Student student = new Student();
        student.setName("测试");
        student.setAge(89);
        student.setScores("333");
        student.setCreateTime(new Date());
        student.setUpdateTime(new Date());
        int result = studentMapper.insertSelective(student);
        return result;
    }

}
