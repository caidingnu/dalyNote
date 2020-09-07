package com.cdn.one.Controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc：
 *
 * @author CDN
 * date 2020/09/07 22:56
 */
@RestController
public class HelloWordController {

    /**
     * desc: 缓存30分钟
     * param:
     * return:
     * author: CDN
     * date: 2020/9/7
     */
    @GetMapping("/")
    @Cacheable(cacheNames = Constanst.CACHE_30MINS,key = "'hello'",unless = "#result eq null ")
    public Object aa(){
        return "aaa";
    }

    /**
     * 缓存一天
     * @return
     */
    @GetMapping("/b")
    @Cacheable(cacheNames = Constanst.CACHE_1DAY,key = "'hello'",unless = "#result eq null ")
    public Object b(){
        return "bbb";
    }

    /**
     * 未指定过期时间
     * @return
     */
    @Cacheable(key = "'key'", cacheNames ="unknow")
    @GetMapping("key")
    public String demo2() {
        return "abc" ;
    }

}
