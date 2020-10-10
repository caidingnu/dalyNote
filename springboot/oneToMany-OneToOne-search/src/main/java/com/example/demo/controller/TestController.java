package com.example.demo.controller;

import com.example.demo.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CDN
 * @desc
 * @date 2020/09/25 22:23
 */
@RestController
public class TestController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/xmlUser")
    public Object xmlUser() {
        return userMapper.xmlOneToManylist();
    }

    @GetMapping("/xmlrole")
    public Object xmlrole() {
        return userMapper.xmlOneToOneRole();
    }

    @GetMapping("/zhujieOneToOneRole")
    public Object zhujieOneToOneRole() {
        return userMapper.zhujieOneToOneRole(1);
    }

    @GetMapping("/zhujieOntToManylist")
    public Object zhujieOntToManylist() {
        return userMapper.zhujieOneToManylist(1);
    }

}

