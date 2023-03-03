package com.speed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableCaching
@EnableJpaAuditing //Jpa的审计功能
@SpringBootApplication
public class MajorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MajorApplication.class, args);
    }
}
