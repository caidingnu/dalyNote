package com.cdn.springsecurity.controller;

import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * CDN
 * 2020/05/17 23:06
 */
@Controller
public class Page {


    /**
     * desc:
     * param:
     * author: CDN
     * date: 2020/5/17
     */
    @RequestMapping("index")
    public String index() {

        return "success";
    }

    @RequestMapping("/admin/index")
    public String adminIndex() {

        return "admin";
    }

    @GetMapping("/login-v")
    public String login() {

        return "login";
    }


    /**
     *    该方法可以配置为登陆失败的回调
     * @param request
     * @return
     */
    @GetMapping("/login/error")
    @ResponseBody
    public Object loginError(HttpServletRequest request) {
        AuthenticationException authenticationException = (AuthenticationException) request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        Map<String,Object> map=new HashMap<>();
        map.put("code",201);
        if (authenticationException instanceof UsernameNotFoundException || authenticationException instanceof BadCredentialsException) {
            map.put("msg","用户名或密码错误");
        } else if (authenticationException instanceof DisabledException) {
            map.put("msg","用户已被禁用");
        } else if (authenticationException instanceof LockedException) {
            map.put("msg","账户被锁定");
        } else if (authenticationException instanceof AccountExpiredException) {
            map.put("msg","账户过期");
        } else if (authenticationException instanceof CredentialsExpiredException) {
            map.put("msg","证书过期");
        } else {
            map.put("msg","登录失败");
        }
        return map;
    }

}
