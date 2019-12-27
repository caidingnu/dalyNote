package com.example.mybatisplustest.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplustest.entity.UniData;
import com.example.mybatisplustest.entity.User;
import com.example.mybatisplustest.mapper.UniDataMapper;
import com.example.mybatisplustest.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import java.util.HashMap;
import java.util.Map;

/**
 * desc： plus 分页方式
 * author CDN
 * create 2019-12-27 21:37
 * version 1.0.0
 */
@RestController
public class PageDataController {
    @Autowired
    UniDataMapper uniDataMapper;

    @Autowired
    UserMapper userMapper;

    /**
     * desc:  注解方式（关联分页查询）
     * param:
     * return:
     * author: CDN
     * date: 2019/12/27
     */
    @RequestMapping("/annotationsPage")
    public Object annotationsPage() {
        IPage<UniData> page=new Page<>(1, 2);
        Map map=new HashMap();
        map.put("userId", 1);
        IPage<UniData> all = uniDataMapper.annotationsPage(page,map);
        return all;
    }


    /**
     * desc:  provider （关联分页查询）
     * param:
     * return:
     * author: CDN
     * date: 2019/12/27
     */
    @RequestMapping("/providerPage")
    public Object providerPage() {
        IPage<UniData> page=new Page<>(1, 2);
        Map map=new HashMap();
        map.put("userId", 1);
        IPage<UniData> all = uniDataMapper.providerPage(page,map);
        return all;
    }


    /**
     * desc:  xml方式（关联分页查询）
     * param:
     * return:
     * author: CDN
     * date: 2019/12/27
     */
    @RequestMapping("/xmlPage")
    public Object xmlPage() {
        IPage<UniData> page=new Page<>(1, 2);
        Map map=new HashMap();
        map.put("userId", 1);
        IPage<UniData> all = uniDataMapper.xmlPage(page,map);
        return all;
    }


    /**
     * desc:  常规方式(单表 user表)
     * param:
     * return:
     * author: CDN
     * date: 2019/12/27
     */
    @RequestMapping("/commonPage")
    public Object commonPage() {
        IPage<User> page=new Page<>(1, 2);
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id", 1);
        IPage<User> all = userMapper.selectPage(page,queryWrapper);
        return all;
    }


}
