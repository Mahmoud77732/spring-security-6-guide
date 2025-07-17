# ssecuritypart3

A demo project for Spring Boot Security, showcasing basic authentication, authorization, and endpoint protection using in-memory users.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Endpoints](#endpoints)
- [Security](#security)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Configuration](#configuration)
- [Testing](#testing)
- [References](#references)

## Overview
This project demonstrates how to secure a Spring Boot application using Spring Security. It includes multiple REST endpoints, some of which are protected and require authentication, while others are publicly accessible.

## Features
- Spring Boot 3.5.3
- Spring Security with in-memory authentication
- RESTful API endpoints
- Form-based and HTTP Basic authentication
- Simple user roles (user, admin)

## Endpoints
| Endpoint      | Method | Authentication | Description                       |
|--------------|--------|----------------|-----------------------------------|
| `/welcome`   | GET    | None           | Public welcome message            |
| `/myAccount` | GET    | Required       | Returns account details           |
| `/myBalance` | GET    | Required       | Returns balance details           |
| `/myLoans`   | GET    | Optional*      | Returns loan details              |
| `/myCards`   | GET    | Optional*      | Returns card details              |
| `/notices`   | GET    | None           | Returns notices                   |
| `/contact`   | GET    | None           | Saves contact inquiry             |

*`/myLoans` and `/myCards` are listed as both authenticated and permitted for all in the config, so they are accessible without authentication.

## Security
- **In-Memory Users:**
  - `user` / `user123` (authority: `read`)
  - `admin` / `admin123` (authority: `admin`)
- **Authentication:**
  - HTTP Basic and Form Login enabled
- **Authorization:**
  - `/myAccount`, `/myBalance`, `/myLoans`, `/myCards` require authentication
  - `/notices`, `/contact`, `/error` are public

## Getting Started
### Prerequisites
- Java 21+
- Maven 3.8+

### Build & Run
```bash
mvn clean install
mvn spring-boot:run
```
The application will start on [http://localhost:8080](http://localhost:8080).

## Usage
- Access public endpoints directly.
- For protected endpoints, use the following credentials:
  - Username: `user` / Password: `user123`
  - Username: `admin` / Password: `admin123`
- You can use tools like [Postman](https://www.postman.com/) or `curl`:

```bash
curl -u user:user123 http://localhost:8080/myAccount
```

## Configuration
- Application properties can be set in `src/main/resources/application.properties`.
- Example environment variables:
  - `SPRING_APP_NAME` (default: securitypart1)
  - `SPRING_SECURITY_LOG_LEVEL` (default: TRACE)

## Testing
- Basic context load test is provided in `src/test/java/com/hegazy/ssecuritypart1/Ssecuritypart1ApplicationTests.java`.
- Run tests with:
```bash
mvn test
```

## References
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Security Reference](https://docs.spring.io/spring-security/reference/index.html)
- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
- [Spring Boot Maven Plugin](https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/html/)

---

*This project is for educational/demo purposes only.*
