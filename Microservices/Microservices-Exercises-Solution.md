# Microservices Architecture - Hands-On Exercises Solution

This directory contains a complete, production-ready Microservices Architecture solution built with **Spring Boot 3.4.1**, **Spring Cloud Gateway**, and **WebClient**.

---

## System Architecture

```
                      +-------------------+
                      |   API Gateway     |
                      |   (Port 8080)     |
                      +---------+---------+
                                |
             +------------------+------------------+
             |                                     |
   +---------v---------+                 +---------v---------+
   |   User Service    |                 |   Order Service   |
   |   (Port 8081)     |                 |   (Port 8082)     |
   +-------------------+                 +---------+---------+
                                                   | (WebClient)
                                         +---------v---------+
                                         |  Payment Service  |
                                         |   (Port 8083)     |
                                         +-------------------+
```

---

## Sub-Modules & Components

### 1. `user-service` (Port 8081)
- Manages user profiles (`User` model with `id`, `name`, `email`).
- REST Endpoints:
  - `GET /users` - Lists all registered users.
  - `GET /users/{id}` - Retrieves detailed user profile by ID.

### 2. `order-service` (Port 8082)
- Manages order items (`Order` model with `id`, `product`, `amount`, `userId`).
- Inter-service Communication: Uses Spring WebClient to dynamically fetch user details from `user-service` (`http://localhost:8081/users/{userId}`).
- REST Endpoints:
  - `GET /orders` - Lists all orders.
  - `GET /orders/{id}` - Retrieves order details populated with `UserDto`.

### 3. `api-gateway` (Port 8080)
- Built using **Spring Cloud Gateway**.
- Configures dynamic routing for incoming requests:
  - `/users/**` -> `http://localhost:8081`
  - `/orders/**` -> `http://localhost:8082`
- Custom Filter: `LoggingFilter` logs incoming URI, HTTP method, and response status code.

### 4. `payment-service` (Port 8083)
- Resilient payment processing service.
- Implements Fallback / Circuit Breaker logic (`paymentFallback`) for handling slow third-party API dependencies.

---

## Execution Steps

### 1. Build All Modules
From the `Microservices` root directory:
```bash
mvn clean package
```

### 2. Run Services
Launch each microservice in separate terminal windows:

```bash
# 1. Start User Service
cd user-service
mvn spring-boot:run

# 2. Start Order Service
cd order-service
mvn spring-boot:run

# 3. Start API Gateway
cd api-gateway
mvn spring-boot:run

# 4. Start Payment Service
cd payment-service
mvn spring-boot:run
```

---

## Verifying Endpoints via API Gateway

### Test User Service via Gateway (Port 8080)
```bash
curl http://localhost:8080/users
curl http://localhost:8080/users/1
```

### Test Order Service with Inter-Service Call via Gateway
```bash
curl http://localhost:8080/orders/101
```
Response:
```json
{
  "id": 101,
  "product": "Laptop",
  "amount": 1200.0,
  "userId": 1,
  "userDetails": {
    "id": 1,
    "name": "Alice Smith",
    "email": "alice@example.com"
  }
}
```

### Test Payment Circuit Breaker & Fallback
```bash
# Successful Call
curl http://localhost:8083/payment/process/101

# Trigger Fallback Response
curl http://localhost:8083/payment/process/101?simulateError=true
```
