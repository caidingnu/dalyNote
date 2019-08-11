package com.admin.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * desc：
 * author CDN
 * create 2019-08-10 21:46
 * version 1.0.0
 */
@Controller
public class SslController {


    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2019/8/10
     */
    @RequestMapping("/")
    public String index() {

        return "index";
    }
}
