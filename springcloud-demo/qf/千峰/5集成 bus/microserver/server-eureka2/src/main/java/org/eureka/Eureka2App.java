package org.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 */
@SpringBootApplication
@EnableEurekaServer
public class Eureka2App {
    public static void main(String[] args) {
        SpringApplication.run(Eureka2App.class, args);
        System.out.println("注册中心启动");
    }
}
