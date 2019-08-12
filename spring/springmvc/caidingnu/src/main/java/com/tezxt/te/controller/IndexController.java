package com.tezxt.te.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by CodeX4J.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }
}