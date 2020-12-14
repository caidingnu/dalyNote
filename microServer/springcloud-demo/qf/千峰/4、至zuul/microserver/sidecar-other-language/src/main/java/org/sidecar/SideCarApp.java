package org.sidecar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 该服务是用来集成其他语言的服务到Eureka,
 * 改服务知识其他服务的一个代理
 *
 * 测试在   consumer-order-ribbon.HelloController.aa
 */
@SpringBootApplication
@EnableSidecar
public class SideCarApp {
    public static void main(String[] args) {
        SpringApplication.run(SideCarApp.class, args);
        System.out.println("Hello SideCarApp!");
    }


}
