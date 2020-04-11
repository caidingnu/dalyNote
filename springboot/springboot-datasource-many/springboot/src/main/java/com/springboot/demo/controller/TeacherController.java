package com.springboot.demo.controller;

import com.springboot.demo.dao.test2.TeacherMapper;
import com.springboot.demo.entity.test2.Teacher;
import com.springboot.demo.entity.test2.TeacherExample;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * desc：
 * author CDN
 * create 2019-09-01 14:21
 * version 1.0.0
 */
@RestController
public class TeacherController {

    @Resource
    TeacherMapper teacherMapper;

    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2019/9/1
     */
    @RequestMapping("all2")
    public Object all() {
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.createCriteria().andIdEqualTo(1);

        return teacherMapper.selectByExample(teacherExample);
    }


    /**
     * des:
     * param:
     * return:
     * author: CDN
     * date: 2019/9/1
     */
    @RequestMapping("add2")
    @Transactional("test2TransactionManager")  //test2TransactionManager 对应配置DataSource2Config中的名称
    public int add() {
        Teacher teacher = new Teacher();
        teacher.setName("cdn");
        teacher.setAge(666);
        int result = teacherMapper.insertSelective(teacher);
        return result;
    }


}
