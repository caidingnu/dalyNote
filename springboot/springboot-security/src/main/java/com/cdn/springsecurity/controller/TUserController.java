package com.cdn.springsecurity.controller;


import com.cdn.springsecurity.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cdn
 * @since 2020-05-17
 */
@RestController
@RequestMapping("/t-user")
public class TUserController {

    @Autowired
    UserService userService;

    /**
     * desc:
     * param:
     * author: CDN
     * date: 2020/5/18
     */
    @GetMapping("curr")
    public Object getCurr(){

        return userService.getCurrUser();
    }


}
