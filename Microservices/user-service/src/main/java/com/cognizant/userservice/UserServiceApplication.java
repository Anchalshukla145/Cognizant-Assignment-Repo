package com.cognizant.userservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting UserServiceApplication...");
        SpringApplication.run(UserServiceApplication.class, args);
        LOGGER.info("UserServiceApplication started on port 8081");
    }
}
