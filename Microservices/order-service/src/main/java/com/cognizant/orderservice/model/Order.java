package com.cognizant.orderservice.model;

import com.cognizant.orderservice.dto.UserDto;

public class Order {

    private Long id;
    private String product;
    private Double amount;
    private Long userId;
    private UserDto userDetails;

    public Order() {}

    public Order(Long id, String product, Double amount, Long userId) {
        this.id = id;
        this.product = product;
        this.amount = amount;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserDto getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDto userDetails) {
        this.userDetails = userDetails;
    }
}
