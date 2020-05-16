package com.cdn.quartzspringboot.controller;

import com.cdn.quartzspringboot.entity.SysJobLog;
import com.cdn.quartzspringboot.service.ISysJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 调度日志操作处理
 * 
 *
 */
@RestController
@RequestMapping("/monitor/jobLog")
public class SysJobLogController 
{
    @Autowired
    private ISysJobLogService jobLogService;

    /**
     * 查询定时任务调度日志列表
     */
    public Object list(SysJobLog sysJobLog)
    {
        List<SysJobLog> list = jobLogService.selectJobLogList(sysJobLog);
        return list;
    }


    /**
     * 根据调度编号获取详细信息
     */
    public Object getInfo(@PathVariable Long jobLogId)
    {
        return jobLogService.selectJobLogById(jobLogId);
    }


    /**
     * 删除定时任务调度日志
     */
    @DeleteMapping("/{jobLogIds}")
    public Object remove(@PathVariable Long[] jobLogIds)
    {
        return jobLogService.deleteJobLogByIds(jobLogIds);
    }

    @DeleteMapping("/clean")
    public Object clean()
    {
        jobLogService.cleanJobLog();
        return "ok";
    }
}
