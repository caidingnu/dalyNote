package com.syudy.freemarker.controller;

import com.syudy.freemarker.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.lang.model.element.NestingKind;
import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * desc：
 * author CDN
 * create 2019-11-19 22:38
 * version 1.0.0
 */
@Controller
public class pageController {


    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2019/11/19
     */
    @RequestMapping("page")
    public String page(Model model) {

        List<Student> stuList = new ArrayList<>();
        stuList.add(new Student(1, "小米", 11, "北京昌平回龙观"));
        stuList.add(new Student(2, "小米2", 12, "北京昌平回龙观"));
        stuList.add(new Student(3, "小米3", 13, "北京昌平回龙观"));
        stuList.add(new Student(4, "小米4", 14, "北京昌平回龙观"));
        stuList.add(new Student(5, "小米5", 15, "北京昌平回龙观"));
        stuList.add(new Student(6, "小米6", 16, "北京昌平回龙观"));
        stuList.add(new Student(7, "小米7", 17, "北京昌平回龙观"));
        model.addAttribute("stuList",stuList);
        return "test";
    }


    @RequestMapping("page2")
    public ModelAndView page(HttpServletRequest request) {
        HttpSession session=request.getSession();
        session.setAttribute("token", "这是存储在session中的token");
        List<Student> stuList = new ArrayList<>();
        stuList.add(new Student(1, "小米", 11, "北京昌平回龙观"));
        stuList.add(new Student(2, "小米2", 12, "北京昌平回龙观"));
        stuList.add(new Student(3, "小米3", 13, "北京昌平回龙观"));
        stuList.add(new Student(4, "小米4", 14, "北京昌平回龙观"));
        stuList.add(new Student(5, "小米5", 15, "北京昌平回龙观"));
        stuList.add(new Student(6, "小米6", 16, "北京昌平回龙观"));
        stuList.add(new Student(7, "小米7", 17, "北京昌平回龙观"));

        ModelAndView modelAndView=new ModelAndView("test");
        modelAndView.addObject("stuList",stuList);
        return modelAndView;
    }

}
