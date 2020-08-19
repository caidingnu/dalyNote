package com.cdn.jwtshiro.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cdn.jwtshiro.constant.CommonConstant;
import com.cdn.jwtshiro.constant.DefContants;
import com.cdn.jwtshiro.entity.SysUser;
import com.cdn.jwtshiro.service.ISysUserService;
import com.cdn.jwtshiro.utils.JwtUtil;
import com.cdn.jwtshiro.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@Slf4j
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
        SysUser sysUser1 = iSysUserService.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, sysUser.getUsername()).eq(SysUser::getPassword, sysUser.getPassword()));
        if (sysUser1 == null) {
            throw new ShiroException("用户名或密码错误");
        }
        return userInfo(sysUser1);
    }


    /**
     * 用户信息
     *
     * @param sysUser
     * @param
     * @return
     */
    private Map userInfo(SysUser sysUser) {
        // 生成token
        String token = JwtUtil.sign(sysUser.getId());
        // 设置token缓存有效时间
        redisUtil.set(DefContants.PREFIX_USER_TOKEN + token, token);
        redisUtil.expire(DefContants.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME * 2 / 1000);

        // 获取用户部门信息
        Map obj = new HashMap();
        obj.put("token", token);
        obj.put("userInfo", sysUser);
        return obj;
    }

    /**
     * 退出登录
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/logout")
    public Object logout(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> resp = new HashMap<>();
        //用户退出逻辑
        String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
        if (StringUtils.isEmpty(token)) {
            resp.put("result", "退出登录失败");
            return resp;
        }
        String userId = JwtUtil.getUserId(token);
        SysUser sysUser = iSysUserService.getById(userId);
        if (sysUser != null) {
            log.info(" 用户名:  " + sysUser.getUsername() + ",退出成功！ ");
            //清空用户Token缓存
            redisUtil.del(DefContants.X_ACCESS_TOKEN + token);
            //清空用户权限缓存：权限Perms和角色集合
            redisUtil.del(DefContants.LOGIN_USER_CACHERULES_ROLE + userId);
            redisUtil.del(DefContants.LOGIN_USER_CACHERULES_PERMISSION + userId);
            resp.put("result", "退出登录成功");
            return resp;
        } else {
            resp.put("result", "无效的token");
            return resp;
        }
    }

    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2020-7-30
     */
    @RequiresRoles("aa")
    @GetMapping("list")
    public Object list() {

        return iSysUserService.list();
    }

    @RequiresPermissions("user:select:list")
    @GetMapping("list2")
    public Object list2() {

        return iSysUserService.list();
    }

    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2020/8/19
     */
    @GetMapping("s")
    public String s() {

        return "s";
    }
}
