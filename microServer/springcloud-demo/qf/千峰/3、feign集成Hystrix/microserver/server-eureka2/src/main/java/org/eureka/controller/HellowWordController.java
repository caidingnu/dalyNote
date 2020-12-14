package org.eureka.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc：
 * author CDN
 * create 2020-03-05 16:44
 * version 1.0.0
 */
@RestController
public class HellowWordController {

    @RequestMapping("server-eureka2")
    public String helloUser() {
        return "server-eureka2";
    }


}
