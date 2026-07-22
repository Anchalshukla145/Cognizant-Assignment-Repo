package com.cognizant.payment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

    @GetMapping("/process/{orderId}")
    public ResponseEntity<Map<String, Object>> processPayment(
            @PathVariable("orderId") Long orderId,
            @RequestParam(value = "simulateError", defaultValue = "false") boolean simulateError) {

        LOGGER.info("Initiating payment processing for orderId: {}", orderId);

        if (simulateError) {
            LOGGER.warn("Simulating external payment gateway timeout/failure for orderId: {}", orderId);
            return paymentFallback(orderId, new RuntimeException("External Payment Gateway Timeout"));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("orderId", orderId);
        response.put("status", "SUCCESS");
        response.put("transactionId", "TXN" + System.currentTimeMillis());
        response.put("message", "Payment processed successfully via Third-Party Gateway");

        LOGGER.info("Payment processed successfully for orderId: {}", orderId);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Map<String, Object>> paymentFallback(Long orderId, Throwable throwable) {
        LOGGER.error("Circuit Breaker Fallback executed for orderId: {}. Reason: {}", orderId, throwable.getMessage());

        Map<String, Object> fallbackResponse = new HashMap<>();
        fallbackResponse.put("orderId", orderId);
        fallbackResponse.put("status", "FALLBACK_QUEUED");
        fallbackResponse.put("message", "Payment service is currently experiencing high latency/downtime. Your transaction has been queued.");
        fallbackResponse.put("error", throwable.getMessage());

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(fallbackResponse);
    }
}
