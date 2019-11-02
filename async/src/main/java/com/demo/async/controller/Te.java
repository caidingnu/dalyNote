package com.demo.async.controller;

import com.demo.async.asyncClass.Task2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * desc：
 * author CDN
 * create 2019-07-07 22:05
 * version 1.0.0
 */
@Controller
public class Te {

    @Autowired
    private Task2 task2;

    //测试task1.
    @RequestMapping("ca")
    @ResponseBody
    public void task1() throws Exception {
        task2.doTaskOne();
        task2.doTaskTwo();
        task2.doTaskThree();

        System.out.println("-------1--------");
        System.out.println("-------2---------");
        System.out.println("------3--------");
        System.out.println("----4-------");
    }

}
