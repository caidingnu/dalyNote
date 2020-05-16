package com.cdn.quartz.controller;


import com.cdn.quartz.quartz.PickNewsJob;
import com.cdn.quartz.service.MyQuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时器控制器
 */
@RestController
@RequestMapping("/quartz")
public class QuartzPickNewsJobController {

    @Autowired
    private MyQuartzService myQuartzService;

//    @Autowired
//    private DynamicDemoTask dynamicDemoTask;


    /**
     * 增加定时任务
     */
    @PostMapping("/job")
    public boolean addJob() {
        Map map = new HashMap(2);
        map.put("id", 1L);
        // 先删除，再新增加
        myQuartzService.deleteJob("job", "test");
        return myQuartzService.addJob(PickNewsJob.class, "job", "test", "0/30 * * * * ?", map);

    }

    /**
     * 更新定时任务
     */
    @PutMapping("/job")
    public boolean updateJob() {
        return myQuartzService.updateJob("job", "test", "0/10 * * * * ?");
    }

    /**
     * 删除定时任务
     */
    @DeleteMapping("/job")
    public boolean deleteJob() {
        return myQuartzService.deleteJob("job", "test");
    }


    /**
     * 查询所有定时任务
     *
     * @return
     */
    @GetMapping("/job")
    public Object queryAllJob() {
        return myQuartzService.queryAllJob();
    }


    /**
     * desc:  暂停一个任务
     * param:
     * author: CDN
     * date: 2020/5/5
     */
    @PutMapping("pauseJob")
    public boolean pauseJob() {
        return myQuartzService.pauseJob("job", "test");
    }

    /**
     * desc:  恢复一个任务
     * param:
     * author: CDN
     * date: 2020/5/5
     */
    @PutMapping("resumeJob")
    public boolean resumeJob() {
        return myQuartzService.resumeJob("job", "test");
    }

    /**
     * desc:  立即执行一个job
     * param:
     * author: CDN
     * date: 2020/5/5
     */
    @PutMapping("runAJobNow")
    public boolean runAJobNow() {
        return myQuartzService.runAJobNow("job", "test");
    }

    /**
     * desc:  获取所有计划中的任务列表
     * param:
     * author: CDN
     * date: 2020/5/5
     */
    @PutMapping("queryRunJob")
    public List<Map<String, Object>> queryRunJob() {
        return myQuartzService.queryRunJob();
    }


}