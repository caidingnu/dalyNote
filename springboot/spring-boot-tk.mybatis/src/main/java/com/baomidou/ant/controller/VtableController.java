package com.baomidou.ant.controller;


import com.baomidou.ant.entity.Vtable;
import com.baomidou.ant.service.IVtableService;
import com.baomidou.ant.service.common.BaseExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

/**
 * <p>
 * VIEW 前端控制器
 * </p>
 *
 * @author author
 * @since 2020-08-25
 */
@RestController
@RequestMapping("/vtable")
public class VtableController {


    @Autowired
    IVtableService iVtableService;


    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2020/8/25
     */
    @GetMapping
    public Object list() {
        BaseExample example=new BaseExample(Vtable.class,0,1);
        example.setOrderByClause("id desc");
        return iVtableService.selectPageInfo(example);
    }
}
