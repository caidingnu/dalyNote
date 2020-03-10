package com.cdn.springclouduser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
//feign调用需要
@EnableFeignClients
public class SpringcloudUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudUserApplication.class, args);
        System.out.println("===用户服务===");
    }

    //    ribbon必须
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
