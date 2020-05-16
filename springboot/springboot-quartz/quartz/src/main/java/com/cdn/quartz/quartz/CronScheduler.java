package com.cdn.quartz.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * ----------------------Cron 表达式
 * CDN
 * 2020/05/05 17:42
 */
public class CronScheduler extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Cron 表达式的定时任务");
    }
}
