package com.cdn.springclouduser.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能简述：
 *
 * @author CDN
 * @create 2019-05-30 13:22
 * @VERSION 1.0.0
 */
@RestController
public class TestController {


    /**
     * @Description:
     * @Param:
     * @return:
     * @Author: CDN
     * @Date: 2019/5/30
     */
    @RequestMapping("/")
    public String admin() {

        return "我是user服务";
    }
}
