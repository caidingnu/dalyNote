package com.database.backup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class BackupApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackupApplication.class, args);
    }

}
