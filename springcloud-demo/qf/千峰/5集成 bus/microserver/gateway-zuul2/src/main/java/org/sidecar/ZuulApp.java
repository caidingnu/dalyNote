package org.sidecar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@SpringBootApplication
@EnableZuulProxy    //自带熔断功能
@EnableEurekaClient
public class ZuulApp {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApp.class,args);
        System.out.println("Hello ZuulApp2!");
    }
}
