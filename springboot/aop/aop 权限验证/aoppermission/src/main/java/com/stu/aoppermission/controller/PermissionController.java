package com.stu.aoppermission.controller;

import com.stu.aoppermission.anno.Permission;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc：
 * author CDN
 * create 2020-02-27 17:18
 * version 1.0.0
 */
@RestController
public class PermissionController {


    @RequestMapping("testAop")
    @Permission
    public Object testAop(StringBuffer name) {
        if (name == null || name.length() == 0) {
            return "用户名不能为空";
        }
        return name.append("有管理员权限");
    }

    @RequestMapping("noTestAop")
    public Object noTestAop(StringBuffer name) {
        if (name == null || name.length() == 0) {
            return "用户名不能为空";
        }
        return ("没有Permission注解");
    }

}
