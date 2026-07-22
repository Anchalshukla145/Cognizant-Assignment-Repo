# Spring REST using Spring Boot - Hands-On Guide

This folder contains a complete Spring Boot RESTful Web Application implementing all requirements for **Hands-on Labs 1 to 5** and **JWT Security**.

---

## Hands-on 1: Spring Boot Application Initialization
- Project initialized using Spring Boot `3.4.1` and Java 17+.
- Main entry point: `SpringLearnApplication.java`.
- Configured logging levels and server port (`8090`) in `application.properties`.

## Hands-on 2: HTTP Request & Response, Country Controller
- Implemented `Country` bean (`code`, `name`).
- Implemented `CountryController` with endpoints:
  - `GET /country`: returns default country India (`IN`).
  - `GET /countries`: returns all countries loaded via `CountryDao` and `CountryService`.
  - `GET /countries/{code}`: returns specific country or throws `CountryNotFoundException` (`404 Not Found`).

## Hands-on 3: RESTful Web Service Integration (GET Employee)
- Implemented `Employee`, `Department`, and `Skill` beans.
- Implemented `EmployeeDao` and `EmployeeService` layers for managing employees.
- Implemented `GET /employees` endpoint.

## Hands-on 4: POST / PUT / DELETE Services & Validation
- Added bean validation constraints (`@NotNull`, `@Size`, `@Min`, `@NotBlank`, `@JsonFormat`).
- Implemented `POST /countries` with `@Valid` payload.
- Implemented `PUT /employees` with `@Valid` payload.
- Implemented `DELETE /employees/{id}` endpoint.
- Added `GlobalExceptionHandler` extending `ResponseEntityExceptionHandler` with `@ControllerAdvice`:
  - `handleMethodArgumentNotValid()`: Returns JSON with field validation errors (`400 Bad Request`).
  - `handleHttpMessageNotReadable()`: Catches data format mismatch (e.g. invalid date or non-numeric id).

## Hands-on 5: JWT Authentication & Spring Security
- Configured Spring Security in `SecurityConfig`.
- Implemented `/authenticate` endpoint in `AuthenticationController` returning JSON Web Token (JWT).
- Implemented `JwtAuthorizationFilter` extending `BasicAuthenticationFilter`:
  - Extracts `Authorization: Bearer <token>` header.
  - Validates JWT signature and sets authentication context.

---

## Testing Endpoints with curl / Postman

### 1. Authenticate & Get JWT Token
```bash
curl -u user:pwd http://localhost:8090/authenticate
```
Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### 2. Get Countries using Token
```bash
curl -H "Authorization: Bearer <TOKEN>" http://localhost:8090/countries
```

### 3. Add Country with Validation
```bash
curl -i -H "Content-Type: application/json" \
     -H "Authorization: Bearer <TOKEN>" \
     -X POST -d '{"code":"I","name":"India"}' \
     http://localhost:8090/countries
```
Response (`400 Bad Request`):
```json
{
  "timestamp": "2026-07-21T12:00:00.000+00:00",
  "status": 400,
  "errors": ["Country code should be 2 characters"]
}
```
