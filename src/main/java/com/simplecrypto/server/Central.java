package com.simplecrypto.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.simplecrypto.server"})
public class Central {
    public static void main(String[] args) {
        SpringApplication.run(Central.class, args);
    }
}