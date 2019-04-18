package com.james.sample.file;

import com.james.sample.file.property.FileProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileProperties.class})
public class SpringBootFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFileApplication.class, args);
        System.out.println("==================================");
    }

}
