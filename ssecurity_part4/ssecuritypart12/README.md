# Spring Security Custom DB Example (ssecuritypart12)

## Overview
This project demonstrates advanced Spring Security configuration using a custom database for authentication and authorization. It covers session management, custom authentication providers, and environment-based configuration.

## Features
- Custom user authentication using a MySQL database
- Separate security configuration for development and production profiles
- Session timeout and invalid session handling
- Custom access denied and authentication entry point handlers
- RESTful endpoints for account, balance, cards, loans, notices, contact, and user registration

## Requirements
- Java 17+
- Maven
- MySQL 8+

## Setup Instructions

### 1. Database Setup
- Create a database named `springsecurity_db` in MySQL.
- Run the SQL script at `src/main/resources/sql/scripts.sql` to create tables and insert sample data.

### 2. Configuration
- Application properties are managed in `src/main/resources/application.properties` and `application_prod.properties`.
- You can override configuration using environment variables (see below).

#### Key Properties
```
spring.datasource.url=jdbc:mysql://localhost:3307/springsecurity_db?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
server.servlet.session.timeout = ${SESSION_TIMEOUT:20m} # (default profile)
```
- For production, see `application_prod.properties` (default session timeout: 10m).

#### Profiles
- Default profile: `spring.profiles.active = default`
- Production profile: `spring.profiles.active = prod`

### 3. Running the Application
- Build and run with Maven:
  ```bash
  mvn spring-boot:run
  ```
- The main class is `com.hegazy.ssecuritypart12.Ssecuritypart12Application`.

## Session Management Notes
The following notes are taken from `application.properties`:

```
# ? How to secure our sessions with Spring Security
# ! when user login: JSESSIONID value is saved at storage/cookies
# !     so he doesn't need to login each time, and
# !     JSESSIONID is related to session_timeout
# (1) add property in "default & prod.properties"
#   (1.1) "server.servlet.session.timeout = 20m"
# (2) edit security-config-classes: defaultSecurityFilterChain()
#   (2.1) add "http.sessionManagement()"
#   (2.2) add its endpoint "/invalidSession" in the allowed endpoints
```

- The session timeout is controlled by `server.servlet.session.timeout`.
- When a session expires, users are redirected to `/invalidSession` (ensure this endpoint is implemented).

## Endpoints
- `/myAccount`, `/myBalance`, `/myLoans`, `/myCards` (authenticated)
- `/notices`, `/contact`, `/register`, `/error`, `/invalidSession` (public)

## Customization
- Security configuration is in `src/main/java/com/hegazy/ssecuritypart12/config/`
- Exception handling in `src/main/java/com/hegazy/ssecuritypart12/exceptionhandling/`
- Controllers in `src/main/java/com/hegazy/ssecuritypart12/controller/`

## License
This project is for educational purposes.
