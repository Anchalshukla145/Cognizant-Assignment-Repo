package com.cognizant.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentServiceApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting PaymentServiceApplication...");
        SpringApplication.run(PaymentServiceApplication.class, args);
        LOGGER.info("PaymentServiceApplication started on port 8083");
    }
}
