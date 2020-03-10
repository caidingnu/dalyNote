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
import javax.servlet.http.HttpServletRequest;

/**
 * desc：
 * author CDN
 * create 2020-03-05 16:57
 * version 1.0.0
 */
@RestController
public class HelloController {

    @Resource
    private RestTemplate restTemplate;

    //    cloud自带的实例，能通过服务名获取服务信息
    @Autowired
    EurekaClient eurekaClient;

    @Value("${user.url}")
    String url;

    @Autowired
    HttpServletRequest request;

    /**
     * desc:  测试使用RestTemplate进行服务调用
     * param:
     * return:
     * author: CDN
     * date: 2020/3/5
     */
    @RequestMapping("helloorder")
    public String helloOrder() {

//写死url，用ip调用
//        String s = restTemplate.getForObject(url, String.class);

//        通过eurekaClient  用名称调用服务获取
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("provider-user", false);
        String s2 = restTemplate.getForObject(instanceInfo.getHomePageUrl()+"/hellouser", String.class);

        return "helloOrder返回值---" + "s" + ":" + s2;
    }

    /**
     * desc:    使用ribbon  进行负载均衡（springcloud默认继承了Ribbon）
     *         启动类 @LoadBalanced
     * param:
     * return:
     * author: CDN
     * date: 2020/3/6
     */
    @RequestMapping(value = "ribbon")
    @HystrixCommand(fallbackMethod = "errResp")   //当调用该服务发生错误时候调用errResp方法
    public String ribbon() {
//        直接调用服务
//        String object = restTemplate.getForObject("http://provider-user/hellouser", String.class);

//        还是调用网关，去找目标服务
        String object = restTemplate.getForObject("http://gateway-zuul/user/hellouser?token=1", String.class);
        return object;
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

    /**
     * desc:  调用其他语言的服务
     * param:
     * return:
     * author: CDN
     * date: 2020/3/8
     */
    @RequestMapping("other")
    public Object aa() {
        return restTemplate.getForObject("http://side-car/layui/tt.json", String.class);
    }
}
