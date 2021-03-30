package com.cdn.springsecurity.controller;


import com.cdn.springsecurity.entity.TUser;
import com.cdn.springsecurity.entity.common.Result;
import com.cdn.springsecurity.service.impl.UserService;
import com.cdn.springsecurity.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cdn
 * @since 2020-05-17
 */
@RestController
@RequestMapping("/t-user")
public class TUserController {

    @Autowired
    UserService userService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    /**
     * desc:
     * param:
     * author: CDN
     * date: 2020/5/18
     */
    @GetMapping("curr")
    public Object getCurr() {
        return userService.getCurrUser();
    }


    /**
     * 登录以后返回token
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/login")
    public Result login(@RequestBody TUser user) {
        String token = userService.login(user.getUserName(), user.getPassword());
        if (token == null) {
            return Result.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        System.out.println("token:" + token);
        return Result.success(tokenMap);
    }

    /**
     * 登录
     *
     * @param token
     * @return
     */
    @PostMapping(value = "/refreshtoken")
    public Result refresh(@RequestHeader("Authorization") String token) {
        return Result.success(jwtTokenUtil.refreshToken(token));
    }


}
