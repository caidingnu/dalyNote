package org.order.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * desc:  feign服务调用，已经集成负载均衡
 * <p>
 * <p>
 * 调用user服务
 * <p>
 * create 2020/3/6
 */
@FeignClient(name = "provider-user")
public interface FeignUserService {

    //    调用provider-user服务
    @RequestMapping("/hellouser")
    String getUserHellouser();


}
