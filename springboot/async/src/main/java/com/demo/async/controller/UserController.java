package com.demo.async.controller;


import com.demo.async.entity.User;
import com.demo.async.service.IUserService;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cdn
 * @since 2020-06-08
 */
@RestController
public class UserController {


    @Resource
    IUserService iUserService;

    /**
     * desc: 异步返回值
     * param:
     * return:
     * author: CDN
     * date: 2020/6/8
     */
    @GetMapping("/index")
    public Object list() {
        long start = System.currentTimeMillis();
        Future<List<User>> listFuture = iUserService.muList();
        List<User> list = null;
        try {
            list = listFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        for (User user : list) {
            user.setAge(66);
        }
        list.forEach(p -> p.setAge(55));
        long end = System.currentTimeMillis();

        Map map = new LinkedHashMap();
        map.put("time", end - start);
        map.put("data", list);
        return map;
    }

    @PostMapping("/add")
    public void add() {
        for (int i = 0; i < 100000; i++) {
            User user = new User();
            user.setUserId(i);
            user.setName("张三--" + i);
            user.insertOrUpdate();
        }
    }

    @GetMapping("/hi")
    public Map<String, Object> testAsyncReturn() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        Map<String, Object> map = new HashMap<>();
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Future<String> future = new AsyncResult<>(i + "");
            futures.add(future);
        }
        List<String> response = new ArrayList<>();
        for (Future future : futures) {
            String string = (String) future.get();
            response.add(string);
        }
        map.put("data", response);
        map.put("消耗时间", String.format("任务执行成功,耗时{%s}毫秒", System.currentTimeMillis() - start));
        return map;
    }

}
