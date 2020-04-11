package com.study.shiro.controller;

import com.study.shiro.shiroconfig.MyRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * desc：
 * author CDN
 * create 2019-09-23 18:20
 * version 1.0.0
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @Resource(name = "re")
    MyRealm myRealm;


    //错误页面展示
    @GetMapping("/403")
    public String error() {
        return "403";
    }


    //跳转到登录表单页面
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {

        return "login";
    }

    //登录成功后，跳转的页面
    @RequestMapping("/main")
    public String index() {
        return "main";
    }

    //未登录，可以访问的页面
    @RequestMapping("/index")
    public String list() {
        return "index";
    }

    /**
     * 跳转到注册页面
     *
     * @return
     */
    @RequestMapping("/register")
    public String register() {
        return "register";
    }


    @RequestMapping("/layout")
    public String layout() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        myRealm.clearCachedAuthenticationInfo(principals);
        myRealm.clearCachedAuthorizationInfo(principals);

        return "login";
    }


}
