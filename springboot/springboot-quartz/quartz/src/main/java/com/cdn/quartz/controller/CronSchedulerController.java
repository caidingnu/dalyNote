package com.cdn.quartz.controller;


import com.cdn.quartz.quartz.CronScheduler;
import com.cdn.quartz.service.MyQuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 定时器控制器
 */
@RestController
@RequestMapping("/cron")
public class CronSchedulerController {


    @Autowired
    private MyQuartzService myQuartzService;

    /**
     * 增加定时任务
     */
    @PostMapping("/cron")
    public boolean addJob() {
        Map map = new HashMap(2);
        map.put("id", 1L);
        // 先删除，再新增加
        myQuartzService.deleteJob("cron", "cronTest");
        return myQuartzService.addJob(CronScheduler.class, "cron", "cronTest", "0/30 * * * * ?", map);

    }

}
