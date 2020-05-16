package com.cdn.quartzspringboot.controller;

import com.cdn.quartzspringboot.entity.SysJob;
import com.cdn.quartzspringboot.exception.TaskException;
import com.cdn.quartzspringboot.service.ISysJobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 调度任务信息操作处理
 * 
 */
@RestController
@RequestMapping("/monitor/job")
public class SysJobController
{
    @Autowired
    private ISysJobService jobService;

    /**
     * 查询定时任务列表
     */
    @GetMapping("/list")
    public Object list(SysJob sysJob)
    {
        List<SysJob> list = jobService.selectJobList(sysJob);
        return list;
    }


    /**
     * 获取定时任务详细信息
     */
    @GetMapping(value = "/{jobId}")
    public Object getInfo(@PathVariable("jobId") Long jobId)
    {
        return jobService.selectJobById(jobId);
    }

    /**
     * 新增定时任务
     */
    @PostMapping
    public Object add(@RequestBody SysJob sysJob) throws SchedulerException, TaskException
    {
        return jobService.insertJob(sysJob);
    }

    /**
     * 修改定时任务
     */
    @PutMapping
    public Object edit(@RequestBody SysJob sysJob) throws SchedulerException, TaskException
    {
        return jobService.updateJob(sysJob);
    }

    /**
     * 定时任务状态修改
     */
    @PutMapping("/changeStatus")
    public Object changeStatus(@RequestBody SysJob job) throws SchedulerException
    {
        SysJob newJob = jobService.selectJobById(job.getJobId());
        newJob.setStatus(job.getStatus());
        return jobService.changeStatus(newJob);
    }

    /**
     * 定时任务立即执行一次
     */
    @PutMapping("/run")
    public Object run(@RequestBody SysJob job) throws SchedulerException
    {
        jobService.run(job);

        return "ok";
    }

    /**
     * 删除定时任务
     */
    @DeleteMapping("/{jobIds}")
    public Object remove(@PathVariable Long[] jobIds) throws SchedulerException, TaskException
    {
        jobService.deleteJobByIds(jobIds);
        return "ok";
    }
}
