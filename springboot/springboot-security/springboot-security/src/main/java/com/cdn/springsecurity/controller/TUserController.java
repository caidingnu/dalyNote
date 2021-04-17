package com.cdn.springsecurity.controller;


import com.cdn.springsecurity.common.Result;
import com.cdn.springsecurity.entity.TUser;
import com.cdn.springsecurity.service.ITUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cdn
 * @since 2020-05-17
 */
@RestController
@RequestMapping("/t-user")
public class TUserController {

    @Autowired
    ITUserService userService;

    /**
     * desc:
     * param:
     * author: CDN
     * date: 2020/5/18
     */
    @GetMapping("curr")
    public Object getCurr(){

        return userService.getCurrUser();
    }

    /**
     * 登录以后返回token
     *
     * @param user
     * @return
     */
    @GetMapping(value = "/login")
    public Result login(TUser user) {
        String token = userService.login(user.getUserName(), user.getPassword());
        if (token == null) {
            return Result.validateFailed("用户名或密码错误");
        }

        return Result.success(token);
    }

}
