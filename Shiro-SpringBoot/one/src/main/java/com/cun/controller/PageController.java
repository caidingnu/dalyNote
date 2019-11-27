package com.cun.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * desc：
 * author CDN
 * create 2019-09-23 18:20
 * version 1.0.0
 */
@Controller
public class PageController {


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


    //未登录，可以访问的页面
    @RequestMapping(value = "/logout" , method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        return  "redirect:login";
    }

}
