package com.howie.shirojwt.controller;

import com.howie.shirojwt.mapper.UserMapper;
import com.howie.shirojwt.model.ResultMap;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description admin角色权限controller
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private  UserMapper userMapper;
    @Autowired
    private  ResultMap resultMap;


    @GetMapping("/getUser")
    @RequiresRoles("admin")
    public ResultMap getUser() {
        List<String> list = userMapper.getUser();
        return resultMap.success().code(200).message(list);
    }

    /**
     * 封号操作
     */
    @PostMapping("/banUser")
    @RequiresRoles("admin")
    public ResultMap updatePassword(String username) {
        userMapper.banUser(username);
        return resultMap.success().code(200).message("成功封号！");
    }
}
