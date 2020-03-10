package org.order.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * desc：
 * author CDN
 * create 2020-03-05 16:57
 * version 1.0.0
 */
@RestController
public class HelloController {

    @Resource
    private RestTemplate restTempLate;

    //    cloud自带的实例，能通过服务名获取服务信息
    @Autowired
    EurekaClient eurekaClient;


    /**
     * desc:  测试使用RestTemplate进行服务调用时候的 hystrix
     * param:
     * return:
     * author: CDN
     * date: 2020/3/5
     */
    @RequestMapping("hystrixt")
    @HystrixCommand(fallbackMethod = "errResp")   //当调用该服务发生错误时候调用errResp方法
    public String helloOrder() {

//        通过eurekaClient  用名称调用服务获取，可以实现负载均衡
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("provider-user", false);
        String s2 = restTempLate.getForObject(instanceInfo.getHomePageUrl() + "/hellouser", String.class);

        return "Hystrix---" + ":" + s2;
    }


    /**
     * desc: 当调用该服务发生错误时候调用errResp方法
     * param:
     * return:
     * author: CDN
     * date: 2020/3/7
     */
    public String errResp(Throwable e) {

        return "服务出错，熔断器返回值"+e.getMessage();
    }

}
