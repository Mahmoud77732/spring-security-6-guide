# Spring Security 6 Guide

A comprehensive guide and personal documentation for mastering Spring Security 6, including hands-on notes, key concepts, and implementations of JWT and OAuth2. This repository is organized into multiple demo projects, each focusing on different aspects of Spring Security.

---

## Table of Contents
- [Overview](#overview)
- [Repository Structure](#repository-structure)
- [Getting Started](#getting-started)
- [Project Summaries](#project-summaries)
- [Prerequisites](#prerequisites)
- [License](#license)

---

## Overview
This repository is a learning resource for Spring Security 6, featuring step-by-step demo projects. Each part demonstrates a specific security concept or configuration in Spring Boot 3, with practical code and detailed documentation.

---

## Repository Structure

```
spring-security-6-guide/
├── ssecurity_part1/                    # Part 1: Basic Security Concepts
│   ├── ssecuritypart1/                 # Basic Security Setup
│   ├── ssecuritypart2/                 # Endpoint Protection
│   ├── ssecuritypart3/                 # User Management
│   └── ssecuritypart4/                 # Advanced Configuration
└── ssecurity_part2/                    # Part 2: Advanced Authentication
    ├── ssecuritypart5/                 # JDBC Authentication
    └── ssecuritypart6/                 # Custom Database Authentication
```

Each subdirectory contains a standalone Spring Boot project with its own README and code.

---

## Getting Started

1. **Clone the repository:**
   ```bash
   git clone <repo-url>
   cd spring-security-6-guide/ssecurity_part1/<project-directory>
   ```
2. **Build and run any part:**
   ```bash
   ./mvnw spring-boot:run
   ```
   or
   ```bash
   mvn spring-boot:run
   ```
3. **Explore endpoints and security features as described in each part's README.**

---

## Project Summaries

### Part 1: Basic Security Concepts

#### 1. ssecuritypart1: Basic Security Setup
- Basic Spring Security integration in a Spring Boot app.
- Single secured endpoint (`/welcome`).
- Customizable credentials via environment variables or `application.properties`.
- [See detailed README](./ssecurity_part1/ssecuritypart1/README.md)

#### 2. ssecuritypart2: Endpoint Protection
- Demonstrates endpoint protection, form login, and HTTP Basic authentication.
- Custom access rules for public and secured endpoints.
- Environment-based configuration for credentials.
- [See detailed README](./ssecurity_part1/ssecuritypart2/README.md)

#### 3. ssecuritypart3: User Management
- In-memory user management with roles (user, admin).
- Multiple REST endpoints with varying access levels.
- Demonstrates both authentication and authorization.
- [See detailed README](./ssecurity_part1/ssecuritypart3/README.md)

#### 4. ssecuritypart4: Advanced Configuration
- Advanced security configuration with BCrypt password encoding.
- Password strength checking using HaveIBeenPwned API.
- In-memory users, form login, and HTTP Basic authentication.
- [See detailed README](./ssecurity_part1/ssecuritypart4/README.md)

### Part 2: Advanced Authentication

#### 5. ssecuritypart5: JDBC Authentication
- **JDBC-based User Authentication**: User credentials stored in MySQL database
- **Password Security**: BCrypt password encoding with compromised password detection
- **Role-based Access Control**: Different endpoints for different user roles
- **Security Headers**: Built-in security headers and CSRF protection
- **Actuator Integration**: Health checks and application monitoring
- [See detailed README](./ssecurity_part2/ssecuritypart5/README.md)

#### 6. ssecuritypart6: Custom Database Authentication
- **Custom Database Authentication**: Uses MySQL database with custom `customer` table
- **Spring Security 6**: Latest Spring Security implementation with modern configuration
- **JPA/Hibernate**: Database persistence with Spring Data JPA
- **Password Security**: BCrypt password encoding with compromised password checking
- **Role-based Access Control**: Different endpoints for different user roles
- **Form and HTTP Basic Authentication**: Multiple authentication methods
- [See detailed README](./ssecurity_part2/ssecuritypart6/README.md)

---

## Prerequisites
- Java 21+ (for parts 1-4), Java 17+ (for parts 5-6)
- Maven 3.8+
- MySQL 8.0+ (for parts 5-6)
- Docker (optional, for MySQL container in part 5)

---

## License
This repository and all demo projects are for educational and demonstration purposes only.
