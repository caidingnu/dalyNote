package com.study.config.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc：
 * author CDN
 * create 2019-11-03 17:07
 * version 1.0.0
 */
@RestController
public class Mytest {

    @Autowired
    Person person;

    @Autowired
    Student student;
    
    @Value("${server.port}")
    private String port;
    
    @Value("#{123}")
    private int arg;


    @RequestMapping("/")
    Object contextLoads() {
        System.out.println(person);
        return  this.person+"--"+this.student+port+arg;
    }
}
