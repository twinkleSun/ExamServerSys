package com.examsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ExamsysApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamsysApplication.class, args);
    }

}
