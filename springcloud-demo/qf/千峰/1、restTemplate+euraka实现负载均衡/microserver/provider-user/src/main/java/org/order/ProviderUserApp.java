package org.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableEurekaClient
public class ProviderUserApp {
    public static void main(String[] args) {
        SpringApplication.run(ProviderUserApp.class);
        System.out.println("Hello User!");
    }
}
