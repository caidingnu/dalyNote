package com.cdn.quartz.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * CDN
 *------------------ 定时任务业务逻辑操作类
 * 2020/05/05 16:26
 */
public class PickNewsJob extends QuartzJobBean  {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //具体的业务逻辑
        System.out.println("业务逻辑");
    }
}
