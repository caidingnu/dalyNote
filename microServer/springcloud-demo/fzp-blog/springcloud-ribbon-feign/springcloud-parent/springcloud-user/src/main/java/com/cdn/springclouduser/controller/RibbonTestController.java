package com.cdn.springclouduser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 功能简述：
 * ribbon调用其他服务
 *
 * @author caidingnu
 * @create 2019-04-08 00:00
 * @since 1.0.0
 */
@RestController
public class RibbonTestController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/getmovieRibbon")
    public String getmovieRibbon() {
        String message = "我是ribbon参数";
        System.out.println(restTemplate.getForObject("http://service-movie/ribbon?message=" + message, String.class));
        return restTemplate.getForObject("http://service-movie/ribbon?message=" + message, String.class);
    }

}

