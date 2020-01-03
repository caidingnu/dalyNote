package com.study.wxapp.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.wxapp.entity.JsonResult;
import com.study.wxapp.entity.User;
import com.study.wxapp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cdn
 * @since 2020-01-03
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService iUserService;

    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2020/1/3
     */
    @RequestMapping("insert")
    public JsonResult insert(User user) {
        return JsonResult.buildSuccess(iUserService.save(user));
    }

    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2020/1/3
     */
    @RequestMapping("delete")
    public JsonResult delete(User user) {
        return JsonResult.buildSuccess(user.deleteById());
    }

    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2020/1/3
     */
    @RequestMapping("upadte")
    public JsonResult upadte(User user) {
        return JsonResult.buildSuccess(user.updateById());
    }

    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2020/1/3
     */
    @RequestMapping("findOne")
    public JsonResult findOne(User user) {
        return JsonResult.buildSuccess(user.selectById());
    }


    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2020/1/3
     */
    @RequestMapping("listPage")
    public JsonResult listPage(@RequestParam(defaultValue = "0") Integer pageIndex,
                               @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<User> page = new Page<>(pageIndex, pageSize);
        return JsonResult.buildSuccess(iUserService.page(page));
    }

}
