package com.james.sample.file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能简述：
 *
 * @author caidingnu
 * @create 2019-04-18 21:22
 * @since 1.0.0
 */
@Controller
public class OpenIndex {

    /**
     * @Description:
     * @Param:
     * @return:
     * @Author: caidingnu
     * @Date: 2019/4/18
     */
    @RequestMapping("/many")
    public String index(){

        return "editManyPicUp";
    }

    @RequestMapping("/one")
    public String one(){

        return "editOnePicUp";
    }

    /**
     * @Description:
     * @Param:
     * @return:
     * @Author: caidingnu
     * @Date: 2019/4/18
     */
    @RequestMapping("/index")
    public String index2(){

        return "index";
    }
}
