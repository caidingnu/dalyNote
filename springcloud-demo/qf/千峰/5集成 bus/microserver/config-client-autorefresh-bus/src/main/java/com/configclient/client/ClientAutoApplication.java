package com.configclient.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ClientAutoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientAutoApplication.class, args);
        System.out.println("config AutoClient");
    }

}
