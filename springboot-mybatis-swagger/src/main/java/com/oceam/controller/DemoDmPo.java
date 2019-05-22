package com.oceam.controller;


import com.oceam.entity.UserInfo;
import com.oceam.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//@RestController相当于@Controller+@ResponseBody
@Controller
@Api(description = "用户管理222222") //文档上对controller的描述
//@RequestMapping(value = {"/user"})
public class DemoDmPo {

    @Autowired
    private IUserService userService;

    /**
     * 首页的配置方法(html)
     *
     * @return
     */
    //     @ApiOperation中的value是该请求的描述，notes是该请求的摘要
    @ApiOperation(value = "首页跳转", notes = "打开首页")
    @RequestMapping(value = {"/d"}, produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    public String getAllUsersindex() {
        return "index2";
    }


    /**
     * 重定向到另一个/find22请求
     *
     * @return
     */
    //     @ApiOperation中的value是该请求的描述，notes是该请求的摘要
    @ApiOperation(value = "重定向", notes = "重定向到另一个请求")
    @RequestMapping(value = {"/open1"}, produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    public String getAllUsers2() {
        return "redict:/find22";
    }


    /**
     * 查询所有
     *
     * @return
     */

//     @ApiOperation中的value是该请求的描述，notes是该请求的摘要
    @ApiOperation(value = "获取用户列表", notes = "获取所有用户信息")
    @RequestMapping(value = {"/findAll1"}, produces = {"application/json;charset=UTF-8"}, method = RequestMethod.GET)
    @ResponseBody
    public List getAllUsers() {
        System.out.println("sssssss");
        List list = userService.findAllUser();
        System.out.println(list);
        return list;
    }


    /**
     * 通过id查询用户,
     * 加了@ResponseBody，返回list数据给ajax============================>>>>>>>>>
     *
     * @return
     */
    //     @ApiOperation中的value是该请求的描述，notes是该请求的摘要
    @ApiOperation(value = "查询单个用户", notes = "通过id查询用户信息")
    @ResponseBody
    @RequestMapping(value = {"/find32"}, method = RequestMethod.GET)
    public UserInfo getAllUsers3() {
        System.out.println("sssssss");
        UserInfo list = userService.selectByPrimaryKey(1);
        System.out.println(list);
        return list;
    }


}
