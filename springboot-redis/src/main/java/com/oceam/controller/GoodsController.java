package com.oceam.controller;

import com.oceam.entity.Goods;
import com.oceam.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * Description:
 *
 * @author
 * @since JDK1.8
 * @history 2018年8月30日 新建
 */
@Controller
public class GoodsController {

    @Autowired
    private RedisService redisService;


    //  通过key获取value
    @RequestMapping("/getgoodsfromredis.do")
    @ResponseBody
    public Object getRedis() {
        redisService.set("name", "测试啊");



        return redisService.get("name");
    }

    //  根据key获取缓存过期时间
    @RequestMapping("/getTime.do")
    @ResponseBody
    public long getExpire(@RequestParam String key) {
        return redisService.getExpire(key);
    }

    //  根据key删除缓存
    @RequestMapping("/delRediskey.do")
    @ResponseBody
    public void del(@RequestParam String ... key) {
        redisService.del(key);
    }
}