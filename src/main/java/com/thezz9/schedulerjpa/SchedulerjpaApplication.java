package com.thezz9.schedulerjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SchedulerjpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedulerjpaApplication.class, args);
    }

}
