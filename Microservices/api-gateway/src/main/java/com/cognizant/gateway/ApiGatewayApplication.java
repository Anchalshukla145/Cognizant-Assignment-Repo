package com.cognizant.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiGatewayApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting API Gateway Service...");
        SpringApplication.run(ApiGatewayApplication.class, args);
        LOGGER.info("API Gateway Service started on port 8080");
    }
}
