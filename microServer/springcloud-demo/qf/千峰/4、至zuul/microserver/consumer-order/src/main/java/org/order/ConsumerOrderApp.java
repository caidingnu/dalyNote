package org.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 * @author cdn
 */
@SpringBootApplication
@EnableEurekaClient
public class ConsumerOrderApp {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderApp.class,args);
        System.out.println("Hello Order!");
    }

    @Bean
    public RestTemplate getTem() {
        return new RestTemplate();
    }
}
