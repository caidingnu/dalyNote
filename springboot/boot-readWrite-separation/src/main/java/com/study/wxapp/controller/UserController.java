package com.study.wxapp.controller;

import com.study.wxapp.entity.User;
import com.study.wxapp.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 用户表(User)表控制层
 *
 * @author makejava
 * @since 2020-03-15 23:29:50
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public User selectOne(String id) {
        return this.userService.queryById(id);
    }


    @GetMapping("insertOne")
    public User insertOne() {
        User user = new User();
        user.setId(UUID.randomUUID().toString().substring(0, 10));
        user.setUsername("张三");
        user.setSex("男");



        return this.userService.insert(user);
    }


}