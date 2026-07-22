package com.cognizant.orderservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class OrderServiceApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting OrderServiceApplication...");
        SpringApplication.run(OrderServiceApplication.class, args);
        LOGGER.info("OrderServiceApplication started on port 8082");
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}
