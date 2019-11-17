package com.neo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 功能简述：
 *
 * @author caidingnu
 * @create 2019-04-27 00:33
 * @since 1.0.0
 */
@Controller
public class OpenController {

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @GetMapping("/one")
    public String one() {
        return "editOnePicUp";
    }

    @GetMapping("/many")
    public String many() {
        return "editManyPicUp";
    }

}
