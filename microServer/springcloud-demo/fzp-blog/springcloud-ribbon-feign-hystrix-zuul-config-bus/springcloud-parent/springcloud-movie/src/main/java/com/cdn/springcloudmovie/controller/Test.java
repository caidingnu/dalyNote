package com.cdn.springcloudmovie.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能简述：
 * <!--调用user服务测试-->
 *
 * @author caidingnu
 * @create 2019-04-07 22:16
 * @since 1.0.0
 */
@RestController
public class Test {


    /**
     * @Description: 提供给user利用ribbon调用
     * @Param:
     * @return:
     * @Author: caidingnu
     * @Date: 2019/4/7
     */
    @RequestMapping("ribbon")
    public String returnRibbon(String message) {
        return "我是被user服务使用ribbon调用的方法：" + message;

    }


    /**
     * @Description: 提供给user利用feign调用
     * @Param:
     * @return:
     * @Author: caidingnu
     * @Date: 2019/4/7
     */
    @RequestMapping("feign")
    public String returnfeign(String message) {
        System.out.println(message);
        return "我是被user服务使用feign调用的方法Movie：" + message;

    }

}
