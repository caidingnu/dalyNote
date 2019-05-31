package com.cdn.springcloudzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//EnableZuulProxy 是组合注解 已经包含 euraka客户端。
@EnableZuulProxy
public class SpringcloudZuulApplication {
//    @Bean
//    public MyFilter accessFilter() {
//        return new MyFilter();
//    }


    public static void main(String[] args) {
        SpringApplication.run(SpringcloudZuulApplication.class, args);
        System.out.println("#############网关 8769############");
    }

}
