package com.cdn.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能简述：
 *
 * @author caidingnu
 * @create 2019-05-03 01:09
 * @since 1.0.0
 */
@RestController
public class TestController {

    @Value("${server.port}")
    String port;


    @RequestMapping("/hi")
    public String hi(){
        return "1";
    }
    @RequestMapping("/")
    public String hello(){
        return port;
    }


}
