package com.oceam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 本类功能简述：
 * 〈打开页面〉
 *
 * @author caidingnu
 * @create 2018/12/19
 * @since 1.0.0
 */
@Controller
@RequestMapping(value = "/one")
public class OpenIndex {


    @RequestMapping(value = "/l")
    public String name() {

        return "index";
    }

}
