package com.study.privoder.controller;

import com.study.privoder.entity.Emp;
import com.study.privoder.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc：
 * author CDN
 * create 2019-06-29 16:11
 * version 1.0.0
 */
@RestController
public class EmpController {

    @Autowired
    EmpService empService;

    /**
     * desc:查询所有
     * author: CDN
     * date: 2019/6/29
     */
    @RequestMapping("all")
    public Object all(String keyword) {
        return empService.selectAll(keyword);
    }

    /**
     * 分页
     *
     * @param keyword
     * @return
     */
    @RequestMapping("allPage")
    public Object allPage(String keyword) {
        return empService.selectAllPage(keyword);
    }

    @RequestMapping("or")
    public Object or(Emp emp) {
        return empService.or(emp);
    }

    @RequestMapping("and")
    public Object and(Emp emp) {
        return empService.and(emp);
    }

    @RequestMapping("add")
    public int add(Emp emp) {
        return empService.add(emp);
    }


    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2019/6/29
     */
    @RequestMapping("condition")
    public Object condition(String name) {

        return empService.condition(name);
    }


    /**
     * des:
     * param:
     * return:
     * author: CDN
     * date: 2019/6/29
     */
    @RequestMapping("delete")
    public int delete(String id) {
        return empService.delete(id);
    }


    /**
     * des:
     * param:
     * return:
     * author: CDN
     * date: 2019/6/29
     */
    @RequestMapping("update")
    public int update(Emp emp) {

        return empService.update(emp);
    }


    /**
     * des: 批量插入
     * param:
     * return:
     * author: CDN
     * date: 2019/6/29
     */
    @RequestMapping("bath")
    public int bathAdd(){

        return empService.batchAdd();
    }


    /**
     * desc:关联查询
     * param:
     * return:
     * author: CDN
     * date: 2019/6/30
     */
    @RequestMapping("inner")
    public Object selectInner(){

        return empService.select();
    }
}
