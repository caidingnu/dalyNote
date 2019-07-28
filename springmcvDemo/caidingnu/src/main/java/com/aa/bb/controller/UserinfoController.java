package com.aa.bb.controller;

import com.aa.bb.model.Userinfo;
import com.aa.bb.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by CodeX4J.
 */
@Controller
@RequestMapping("userinfo")
@ResponseBody
public class UserinfoController {
    @Autowired
    private UserinfoService userinfoService;
    
    @RequestMapping("add")
    public int add(Userinfo userinfo) {
        return userinfoService.add(userinfo);
    }
    
    @RequestMapping("find")
    public Userinfo find(int id) {
        return userinfoService.find(id);
    }
    
    @RequestMapping("update")
    public int update(Userinfo userinfo) {
        return userinfoService.update(userinfo);
    }
    
    @RequestMapping("delete")
    public int delete(int id) {
        return userinfoService.delete(id);
    }
}