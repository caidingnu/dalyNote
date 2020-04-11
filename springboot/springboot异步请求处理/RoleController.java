package com.study.wxapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.UUID;

import static java.lang.String.format;

/**
 * desc：
 * author CDN
 * create 2020-01-04 21:29
 * version 1.0.0
 */
@RestController
public class RoleController {

    @Autowired
    @Qualifier("taskExecutor")
    private ThreadPoolTaskExecutor executor;


    @GetMapping("/threadPool")
    public WebAsyncTask<Object> asyncTaskThreadPool() {
        // 打印处理线程名
        System.out.println("请求处理线程");
        // 模拟开启一个异步任务, 超时时间为 10s
        WebAsyncTask<Object> asyncTask = new WebAsyncTask<>(2 * 1000L, executor,() -> {
            System.out.println("异步工作线程");
            // 任务处理时间 5s, 不超时
            Thread.sleep(5 * 1000L);
//            throw new Exception("异常");
            return "异步工作线程";
        });
        // 任务执行完成时调用该方法
        asyncTask.onCompletion(() -> System.out.println("任务执行完成"));

        asyncTask.onError(() -> {
            System.out.println("任务执行异常");
            return "任务执行异常";
        });

        asyncTask.onTimeout(() -> {
            System.out.println("任务执行超时");
            return "任务执行超时";
        });


        System.out.println("继续处理其他事情");
        return asyncTask;
    }

    public static class WebAsyncService {
        public static String generateUUID() {
            return UUID.randomUUID().toString();
        }
    }
}
