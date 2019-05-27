package com.cdn.springcloudeurkar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringcloudEurkarApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudEurkarApplication.class, args);
        System.out.println("===注册中心====");
    }

}
