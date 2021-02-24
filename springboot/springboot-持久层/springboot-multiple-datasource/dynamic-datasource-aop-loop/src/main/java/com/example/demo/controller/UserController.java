package com.example.demo.controller;

import com.example.demo.annotation.DS;
import com.example.demo.entity.ClassEnt;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import com.example.demo.mapper.ClassEntMapper;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CDN
 * @desc
 * @date 2020/07/09 13:45
 */
@RestController
public class UserController {


    @Autowired
    ClassEntMapper classEntMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    StudentMapper studentMapper;


    /**
     * desc:
     * param:
     * author: CDN
     * date: 2020-7-10
     */
    @GetMapping("masterList")
    public Object master() {

        return classEntMapper.list();
    }

    @GetMapping("masterInsert")
    public Object masterInsert() {

        ClassEnt classEnt = new ClassEnt();
        classEnt.setClassName("--");

        return classEntMapper.insert(classEnt);
    }
//----------slave1

    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2020-7-10
     */
    @GetMapping("slave1List")
    @DS(value = "slave1")
    public Object slave1() {

        return userMapper.selectByExample(null);
    }


    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2020-7-10
     */
    @GetMapping("slave1Insert")
    @DS(value = "slave1")
    @Transactional
    public Object slave1Insert() {

        User user = new User();
        user.setName("用户");

        return userMapper.insert(user);
    }
//----------slave2

    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2020-7-10
     */
    @GetMapping("slave2List")
    @DS(value = "slave2")
    public Object slave2() {
        return studentMapper.selectByExample(null);
    }


    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2020-7-10
     */
    @GetMapping("slave2Insert")
    @DS(value = "slave2")
    @Transactional
    public Object slave2Insert() {
        User user = new User();
        user.setName("用户");
        Student student = new Student();
        student.setName("---");
        int insert = studentMapper.insert(student);
//        if (insert == 1) {
//            int a = 1 / 0;
//        }
        return insert;
    }

}
