package com.tezxt.te.controller;

import com.tezxt.te.model.Menu;
import com.tezxt.te.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by CodeX4J.
 */
@Controller
@RequestMapping("menu")
@ResponseBody
public class MenuController {
    @Autowired
    private MenuService menuService;
    
    @RequestMapping("add")
    public int add(Menu menu) {
        return menuService.add(menu);
    }
    
    @RequestMapping("find")
    public Menu find(String id) {
        return menuService.find(id);
    }
    
    @RequestMapping("update")
    public int update(Menu menu) {
        return menuService.update(menu);
    }
    
    @RequestMapping("delete")
    public int delete(String id) {
        return menuService.delete(id);
    }
}