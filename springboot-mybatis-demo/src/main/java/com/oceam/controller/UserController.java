package com.oceam.controller;


import com.oceam.entity.UserInfo;
import com.oceam.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController相当于@Controller+@ResponseBody
@Controller
//@RequestMapping(value = {"/user"})
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 首页的配置方法(html)
     * @return
     */
    @RequestMapping(value = {"/"},produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
    public String getAllUsersindex(){
        return "index2";
    }


    /**
     * 重定向到另一个/find22请求
     * @return
     */
    @RequestMapping(value = {"/open"},produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
    public String getAllUsers2(){
        return "redict:/find22";
    }


    /**
     * 查询所有
     * @return
     */
    @RequestMapping(value = {"/findAll"},produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
    public List getAllUsers(){
        System.out.println("sssssss");
        List list =  userService.findAllUser();
        System.out.println(list);
        return list;
    }


    /**
     * 通过id查询用户,
     * 加了@ResponseBody，返回list数据给ajax============================>>>>>>>>>
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"/find3"},produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
    public UserInfo getAllUsers3(){
        System.out.println("sssssss");
       UserInfo list =  userService.selectByPrimaryKey(1);
        System.out.println(list);
        return list;
    }


}
