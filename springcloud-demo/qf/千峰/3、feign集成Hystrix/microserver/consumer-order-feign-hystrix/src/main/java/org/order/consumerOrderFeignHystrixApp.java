package org.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 *
 * @author cdn
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix   //开启熔断功能，当请求大量错误之后，直接调用熔断器方法，不会再去调用逻辑方法
public class consumerOrderFeignHystrixApp {
    @Bean
//    @LoadBalanced     //ribbon 负载均衡
    public RestTemplate getTem() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(consumerOrderFeignHystrixApp.class);
        System.out.println("Hello Feign Hystrix!");
    }

}
