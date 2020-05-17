package com.cdn.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
