package com.admin.boot.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * desc：
 * author CDN
 * create 2019-08-12 00:23
 * version 1.0.0
 */
@RestController
@CrossOrigin
public class VueController {


    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2019/8/12
     */
    @RequestMapping("aa")
    public Object aa(HashMap map) {
        return "ssss";
    }
}
