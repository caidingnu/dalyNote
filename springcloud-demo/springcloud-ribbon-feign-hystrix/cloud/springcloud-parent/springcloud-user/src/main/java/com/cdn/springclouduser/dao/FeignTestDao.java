package com.cdn.springclouduser.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 本接口功能简述:
 * 〈ribbon方式调用movie服务〉
 *
 * @author caidingnu
 * @create 2019/4/7
 * @since 1.0.0
 */
@FeignClient(value = "service-movie", fallback = SchedualServiceHiHystric.class)
//这里的value对应调用服务的spring.applicatoin.name
public interface FeignTestDao {

    //    如果在FeignClient中的方法有参数传递一般要加@RequestParam（“xxx”）注解
//    传递对象 @RequestBody User user
    @RequestMapping(value = "/feign", method = RequestMethod.GET)
    String hi(@RequestParam("message") String message);
}
