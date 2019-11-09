package com.cdn.springaoplog.Log;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cdn.springaoplog.entity.Menu;
import com.cdn.springaoplog.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc：
 * author CDN
 * create 2019-11-10 00:49
 * version 1.0.0
 */
@RestController
public class TestAopLogController {
    @Autowired
    IMenuService iMenuService;


    @RequestMapping("all")
    @Log("所有")
    public Object test() {
        EntityWrapper<Menu> entityWrapper = new EntityWrapper<>();
        return iMenuService.selectList(null);
    }
}
