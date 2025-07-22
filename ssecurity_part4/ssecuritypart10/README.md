# Spring Security Custom DB Example (with HTTPS Focus)

This project demonstrates a Spring Boot application with advanced Spring Security configuration, focusing on **HTTPS enforcement** and secure authentication using a custom database. It supports multiple profiles (default and production), custom authentication providers, and user registration.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [REST API Endpoints](#rest-api-endpoints)
- [Security Details](#security-details)
- [HTTPS Configuration](#https-configuration)
- [Database Schema](#database-schema)
- [Profiles](#profiles)
- [Dependencies](#dependencies)

---

## Overview
This project is a Spring Boot 3.5.x application that demonstrates how to:
- Use a custom database schema for user authentication and authorization
- Register new users with encoded passwords
- Secure endpoints with different access rules
- Use custom authentication providers and user details service
- **Enforce HTTPS in production for secure communication**
- Support multiple profiles (default and production) with different security behaviors

## Features
- **HTTPS Enforcement**: In production profile, all HTTP requests are redirected to HTTPS for secure communication.
- **Custom User Model**: Users are stored in a `customer` table with email, password, and role.
- **User Registration**: New users can register via a REST endpoint. Passwords are securely encoded.
- **Custom Authentication Providers**: Separate providers for default and production profiles.
- **Profile-based Security**: Different security settings for development and production.
- **RESTful Endpoints**: For account, balance, loans, cards, notices, and contact.
- **MySQL Database**: Used for user and authority storage.

## Getting Started

### Prerequisites
- Java 17+
- Maven
- MySQL (running on `localhost:3307` with database `springsecurity_db`)

### Setup
1. **Clone the repository**
2. **Configure the database**
   - Ensure MySQL is running and create the database:
     ```sql
     CREATE DATABASE springsecurity_db;
     ```
   - Run the SQL script in `src/main/resources/sql/scripts.sql` to create tables and insert sample data.
3. **Configure application properties**
   - Update `src/main/resources/application.properties` with your DB credentials if needed.
4. **Build and run the application**
   ```bash
   mvn spring-boot:run
   ```

## Configuration

### application.properties
- Database connection, JPA, and logging settings.
- Profile activation: `default` (dev) or `prod` (production).
- Imports `application_prod.properties` for production overrides.

### application_prod.properties
- Overrides for production (e.g., stricter logging, **HTTPS enforcement**).

## REST API Endpoints

| Endpoint         | Method | Description                        | Auth Required |
|------------------|--------|------------------------------------|---------------|
| `/register`      | POST   | Register a new user                | No            |
| `/myAccount`     | GET    | Get account details                | Yes           |
| `/myBalance`     | GET    | Get balance details                | Yes           |
| `/myLoans`       | GET    | Get loans details                  | Yes           |
| `/myCards`       | GET    | Get cards details                  | Yes           |
| `/notices`       | GET    | Get notices                        | No            |
| `/contact`       | GET    | Save contact inquiry               | No            |

## Security Details

- **Custom UserDetailsService**: Loads users from the `customer` table by email.
- **Custom Authentication Providers**:
  - `MyUsernamePwdAuthenticationProvider` (default): Authenticates users without password check (for dev/testing).
  - `MyProdUsernamePwdAuthenticationProvider` (prod): Authenticates users with password check using a password encoder.
- **Password Encoding**: Uses Spring's `DelegatingPasswordEncoder` (bcrypt by default).
- **CSRF**: Disabled for simplicity.
- **HTTPS**: Enforced in production profile only.
- **Compromised Password Checker**: Uses HaveIBeenPwned API.

## HTTPS Configuration

This project demonstrates how to enforce HTTPS in a Spring Boot application using Spring Security:

- In the **default profile** (development), HTTP is allowed for easier local testing.
- In the **production profile**, all HTTP requests are automatically redirected to HTTPS, ensuring secure communication.
- The enforcement is handled in the security configuration classes:
  - `ProjectSecurityConfig` (default): `http.redirectToHttps().disable();`
  - `ProjectSecurityProdConfig` (prod): `http.redirectToHttps().requestMatchers(AnyRequestMatcher.INSTANCE);`
- To enable HTTPS, you must provide a valid SSL certificate and configure it in your `application_prod.properties` or via command line.

**Example SSL configuration:**
```properties
server.port=8443
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-password=yourpassword
server.ssl.key-store-type=PKCS12
server.ssl.key-alias=tomcat
```

## Database Schema

- **users**: For Spring Security (username, password, enabled)
- **authorities**: For Spring Security (username, authority)
- **customer**: Custom user table (id, email, pwd, role)

See [`src/main/resources/sql/scripts.sql`](src/main/resources/sql/scripts.sql) for full schema and sample data.

## Profiles

- **default**: Allows HTTP, disables password check for easier testing.
- **prod**: Enforces HTTPS, checks passwords, stricter security.

Switch profiles by setting:
```properties
spring.profiles.active=prod
```

## Dependencies
- Spring Boot 3.5.x
- Spring Security
- Spring Data JPA
- Spring Web
- MySQL Connector/J
- Spring Boot Actuator

---

## License
This project is for educational/demo purposes.
