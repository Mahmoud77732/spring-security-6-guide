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
├── ssecurity_part2/                    # Part 2: Advanced Authentication
│   ├── ssecuritypart5/                 # JDBC Authentication
│   └── ssecuritypart6/                 # Custom Database Authentication
├── ssecurity_part3/                    # Part 3: Custom DB & Advanced Profiles
│   ├── ssecuritypart7/                 # Custom DB Authentication Example
│   ├── ssecuritypart8/                 # Custom Auth Provider Example
│   └── ssecuritypart9/                 # Advanced Profiles & Auth
└── ssecurity_part4/                    # Part 4: (Future/Advanced Topics)
|    └── ssecuritypart10/               # Coming Soon
|    └── ssecuritypart11/
|    └── ssecuritypart12/
|    └── ssecuritypart13/
└── ssecurity_part5/
|    └── ssecuritypart14/
|    └── ssecuritypart15/
└── ssecurity_part6/
|    └── ssecuritypart16/
└── ssecurity_part7/
|    └── ssecuritypart17/
|    └── ssecuritypart18/
└── ssecurity_part8/
|    └── ssecuritypart19/
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

### Part 3: Custom DB & Advanced Profiles

#### 7. ssecuritypart7: Custom DB Authentication Example
- Custom user authentication using a MySQL database
- Secure password storage with bcrypt
- RESTful API endpoints for account, balance, loans, cards, notices, contact, and user registration
- Role-based access control
- CSRF protection disabled for API testing
- [See detailed README](./ssecurity_part3/ssecuritypart7/README.md)

#### 8. ssecuritypart8: Custom Auth Provider Example
- Custom authentication provider using database-backed user details
- Secure password storage with BCrypt
- RESTful API endpoints for banking operations
- Role-based access control for endpoints
- User registration with password hashing
- [See detailed README](./ssecurity_part3/ssecuritypart8/README.md)

#### 9. ssecuritypart9: Advanced Profiles & Auth
- Custom authentication providers for different profiles (default and prod)
- User registration with password encoding
- Profile-based security configuration
- REST endpoints for account, balance, cards, loans, notices, and contact
- [See detailed README](./ssecurity_part3/ssecuritypart9/README.md)

### Part 4: (Future/Advanced Topics)

#### 10. ssecuritypart10: Coming Soon
- Details and documentation for this part will be added in a future update.

---

## Prerequisites
- Java 21+ (for parts 1-4), Java 17+ (for parts 5-10)
- Maven 3.8+
- MySQL 8.0+ (for database-backed parts)
- Docker (optional, for MySQL container in some parts)

---

## License
This repository and all demo projects are for educational and demonstration purposes only.
