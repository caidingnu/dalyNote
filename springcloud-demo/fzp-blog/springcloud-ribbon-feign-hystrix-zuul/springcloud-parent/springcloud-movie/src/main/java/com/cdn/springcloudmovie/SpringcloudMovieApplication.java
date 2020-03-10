package com.cdn.springcloudmovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringcloudMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudMovieApplication.class, args);
        System.out.println("=====电影服务  8763====");
    }

}
