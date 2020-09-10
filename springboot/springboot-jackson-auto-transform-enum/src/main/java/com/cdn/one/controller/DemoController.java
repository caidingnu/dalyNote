package com.cdn.one.controller;

import com.cdn.one.constant.Level;
import com.cdn.one.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * desc：
 *
 * @author CDN
 * date 2020/09/07 22:56
 */
@RestController
public class DemoController {

    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2020/9/10
     */
    @GetMapping("get")
    public Object get() {
        User user = new User();
        user.setCreate(new Date());
        user.setLevel(Level.ONE);
        return user;
    }

    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2020/9/10
     */
    @PostMapping("add")
    public Object add(@RequestBody User user){

        return user;
    }

}
