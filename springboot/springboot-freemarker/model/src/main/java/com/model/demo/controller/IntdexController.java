package com.model.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * desc：
 * author CDN
 * create 2019-11-19 09:27
 * version 1.0.0
 */
@Controller
public class IntdexController {


    /**
     * @desc:
     * @param:
     * @return:
     * @author: CDN
     * @date:
     */
    @RequestMapping("showIndex")
    @ResponseBody
    public ModelAndView showIndex() {
        ModelAndView mv = new ModelAndView("index");
        mv.getModel().put("name", "同");
        return mv;
    }

    @RequestMapping("ftl/test")
    public String testFtl(ModelMap model){
        model.addAttribute("name","FreeMarker 模版引擎 ");
        return "one";
    }
}
