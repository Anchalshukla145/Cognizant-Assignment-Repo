package com.cognizant.orderservice.service;

import com.cognizant.orderservice.dto.UserDto;
import com.cognizant.orderservice.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    private final List<Order> orders = new ArrayList<>();

    @Autowired
    private WebClient webClient;

    public OrderService() {
        orders.add(new Order(101L, "Laptop", 1200.0, 1L));
        orders.add(new Order(102L, "Smartphone", 800.0, 2L));
        orders.add(new Order(103L, "Headphones", 150.0, 1L));
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public Order getOrderById(Long id) {
        LOGGER.info("Fetching order by id: {}", id);
        Order order = orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (order != null) {
            try {
                LOGGER.info("Calling user-service for userId: {}", order.getUserId());
                UserDto user = webClient.get()
                        .uri("http://localhost:8081/users/" + order.getUserId())
                        .retrieve()
                        .bodyToMono(UserDto.class)
                        .block();
                order.setUserDetails(user);
            } catch (Exception ex) {
                LOGGER.error("Failed to fetch user details from user-service: {}", ex.getMessage());
            }
        }
        return order;
    }
}
