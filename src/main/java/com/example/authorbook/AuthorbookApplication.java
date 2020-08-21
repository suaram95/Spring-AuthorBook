package com.example.authorbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AuthorbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorbookApplication.class, args);
    }

}
