# Spring Security Custom DB Example (ssecuritypart11)

This project demonstrates advanced Spring Security 6 features with a custom database for authentication and authorization. It includes custom exception handling, environment-based configuration, and secure REST endpoints.

---

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
  - [application.properties Notes](#applicationproperties-notes)
  - [Production Profile](#production-profile)
- [Database Setup](#database-setup)
- [API Endpoints](#api-endpoints)
- [Exception Handling](#exception-handling)
- [Profiles & HTTPS](#profiles--https)
- [References](#references)

---

## Overview
This project is a Spring Boot application that secures REST endpoints using Spring Security 6. It uses a custom `customer` table for authentication and supports both default and production profiles, with different security behaviors (e.g., HTTPS enforcement in production).

## Features
- Custom user authentication using a `customer` table
- Custom `UserDetailsService` implementation
- Custom exception handling for authentication and authorization errors
- Environment-based configuration (default vs. prod)
- HTTPS enforcement in production
- RESTful API endpoints for account, balance, loans, cards, notices, contact, and user registration
- Password encoding with bcrypt
- Compromised password checking (HaveIBeenPwned API)

## Project Structure
- `src/main/java/com/hegazy/ssecuritypart11/config/` – Security and application configuration
- `src/main/java/com/hegazy/ssecuritypart11/controller/` – REST controllers for API endpoints
- `src/main/java/com/hegazy/ssecuritypart11/exceptionhandling/` – Custom exception handlers
- `src/main/java/com/hegazy/ssecuritypart11/model/` – JPA entity for `Customer`
- `src/main/java/com/hegazy/ssecuritypart11/repo/` – Spring Data repository for `Customer`
- `src/main/resources/sql/scripts.sql` – Database schema and initial data
- `src/main/resources/application.properties` – Main configuration (see notes below)
- `src/main/resources/application_prod.properties` – Production-specific configuration

## Getting Started
1. **Clone the repository**
2. **Set up MySQL** (see [Database Setup](#database-setup))
3. **Configure environment variables** (optional, see below)
4. **Run the application**:
   ```bash
   ./mvnw spring-boot:run
   ```
   or with a specific profile:
   ```bash
   ./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
   ```

## Configuration

### application.properties Notes
The `application.properties` file contains important notes and configuration tips:
- **Environment Variables**: You can override properties using environment variables, e.g., `${SPRING_APP_NAME:securitypart1}`.
- **Database Connection**: MySQL connection details are set here. Adjust as needed for your environment.
- **Profile Activation**: The default profile is `default`. Production settings are imported from `application_prod.properties`.
- **Exception Handling**:
  - **AuthenticationEntryPoint**: Custom response for failed authentication (see `CustomBasicAuthenticationEntryPoint`).
  - **AccessDeniedHandler**: Custom response for failed authorization (see `CustomAccessDeniedHandler`).
  - See the comments in `application.properties` for more details on how these are wired in the security config.

### Production Profile
- The `prod` profile enforces HTTPS and changes logging levels.
- Activate with `-Dspring-boot.run.profiles=prod` or by setting `SPRING_PROFILES_ACTIVE=prod`.

## Database Setup
- The schema and initial data are in [`src/main/resources/sql/scripts.sql`](src/main/resources/sql/scripts.sql).
- Tables: `users`, `authorities`, and `customer`.
- Example users and customers are pre-inserted for testing.
- Update MySQL connection details in `application.properties` as needed.

## API Endpoints
| Endpoint         | Method | Auth Required | Description                |
|------------------|--------|--------------|----------------------------|
| `/register`      | POST   | No           | Register a new customer    |
| `/myAccount`     | GET    | Yes          | Get account details        |
| `/myBalance`     | GET    | Yes          | Get balance details        |
| `/myLoans`       | GET    | Yes          | Get loans details          |
| `/myCards`       | GET    | Yes          | Get cards details          |
| `/notices`       | GET    | No           | Get public notices         |
| `/contact`       | GET    | No           | Contact endpoint           |

## Exception Handling
- **Authentication Failure**: Returns a custom JSON response with status 401 and a header `myapp-error-reason`.
- **Authorization Failure**: Returns a custom JSON response with status 403 and a header `myapp-access-denied-reason`.
- See `CustomBasicAuthenticationEntryPoint` and `CustomAccessDeniedHandler` for implementation details.

## Profiles & HTTPS
- **Default Profile**: HTTP allowed, HTTPS not enforced.
- **Production Profile**: HTTPS enforced for all endpoints.
- Switch profiles using the `spring.profiles.active` property or environment variable.

## References
- See comments in [`src/main/resources/application.properties`](src/main/resources/application.properties) for configuration tips and exception handling notes.
- [Spring Security Documentation](https://docs.spring.io/spring-security/reference/)
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
