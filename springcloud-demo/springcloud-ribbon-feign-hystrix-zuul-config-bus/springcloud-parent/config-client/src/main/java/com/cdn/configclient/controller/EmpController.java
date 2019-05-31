package com.cdn.configclient.controller;

import com.cdn.configclient.entity.Emp;
import com.cdn.configclient.mapper.EmpMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能简述：
 *
 * @author CDN
 * @create 2019-05-30 21:27
 * @VERSION 1.0.0
 */
@RestController
public class EmpController {

    @Resource
    EmpMapper empMapper;
    /**
     * @Description: 
     * @Param: 
     * @return: 
     * @Author: CDN
     * @Date: 2019/5/30
     */
    @RequestMapping("list")
    public List<Emp> list(){
        return empMapper.selectList();
    }

    /**
     * @Description:
     * @Param:
     * @return:
     * @Author: CDN
     * @Date: 2019/5/30
     */
    @RequestMapping("one")
    public Emp one(Integer id){
        Emp one=empMapper.selectByPrimaryKey(id);
        return one;
    }
}
