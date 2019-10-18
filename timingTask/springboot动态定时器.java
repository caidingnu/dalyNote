package com.ocr.mytest;

import freemarker.template.utility.DateUtil;
import org.apache.ibatis.javassist.bytecode.analysis.Executor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by pudding on 2017-11-15.(动态定时器  用于打卡前10分钟推送消息)（!闹钟）
 */
@Component
public class DynamicScheduledTask implements SchedulingConfigurer {

//    @Autowired
//    private CheckSystemMapper checkSystemMapper;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(() -> {
            // 定时任务的业务逻辑
            System.out.println("提醒打卡");

        }, triggerContext -> {//设置下次定时器


//                String cron = "0 " + minute + " " + hour + " * * " + day + "";
            String cron ="0/"+ScheduleJob.a+" * *  * * ? ";   //1秒1次

            System.out.println(cron);

            CronTrigger trigger = new CronTrigger(cron); // 定时任务触发，可修改定时任务的执行周期

            Date nextExecDate = trigger.nextExecutionTime(triggerContext);
            return nextExecDate;
        });
    }

}


