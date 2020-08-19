package com.cdn.jwtshiro.controller;


import com.cdn.jwtshiro.constant.CommonConstant;
import com.cdn.jwtshiro.entity.SysUser;
import com.cdn.jwtshiro.service.ISysUserService;
import com.cdn.jwtshiro.utils.JwtUtil;
import com.cdn.jwtshiro.utils.RedisUtil;
import net.bytebuddy.asm.Advice;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author author
 * @since 2020-07-30
 */
@RestController
@RequestMapping("/sys/")
public class SysUserController {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ISysUserService iSysUserService;

    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2020-7-30
     */
    @PostMapping("/login")
    public Object login(SysUser sysUser) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(sysUser.getUsername(), sysUser.getPassword());
        try {
            SecurityUtils.getSubject().login(usernamePasswordToken);
        } catch (Exception e) {
            throw new AuthenticationException("用户不存在!");
        }
        return userInfo(sysUser);
    }


    /**
     * 用户信息
     *
     * @param sysUser
     * @param
     * @return
     */
    private Map userInfo(SysUser sysUser) {
        String syspassword = sysUser.getPassword();
        String username = sysUser.getUsername();
        // 生成token
        String token = JwtUtil.sign(username);
        // 设置token缓存有效时间
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME * 2 / 1000);

        // 获取用户部门信息
        Map obj = new HashMap();
        obj.put("token", token);
        obj.put("userInfo", sysUser);
        return obj;
    }

    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2020-7-30
     */
    @GetMapping("list")
    public Object list(){

        return iSysUserService.list();
    }

}
