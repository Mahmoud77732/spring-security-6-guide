## ssecuritypart17: Spring Security Custom DB Configuration

This project demonstrates advanced Spring Security 6 concepts with a strong focus on authority-based access control. It uses custom database tables—specifically `Authority` and `Customer`—and leverages the `hasAuthority()` method for fine-grained security. The app includes custom authentication providers, a tailored `UserDetailsService`, and environment-based configuration.

---

### Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [Notes from `application.properties`](#notes-from-applicationproperties)
- [Project Structure](#project-structure)
- [References](#references)

---

## Overview
This project is a Spring Boot application that secures web endpoints using Spring Security 6. The core of its security model is built around the `Authority` and `Customer` tables in a MySQL database. It demonstrates how to:
- Implement a custom `UserDetailsService` that loads users and their authorities from the database
- Use the `hasAuthority()` method in security configuration for precise access control
- Manage authorities and roles via a dedicated `Authority` entity linked to `Customer`
- Switch between default and production profiles for flexible configuration

## Features
- Authority-based authentication and authorization using `hasAuthority()`
- Custom `Authority` and `Customer` tables for user and permission management
- Environment-based configuration (default and prod)
- Thymeleaf templates for login and home pages
- MySQL database integration
- Session timeout configuration

## Getting Started
1. **Clone the repository**
2. **Configure MySQL**: Ensure a MySQL instance is running and accessible at `localhost:3307` with database `springsecurity_db` and user/password `root`/`root` (or update in `application.properties`).
3. **Run database scripts**: Execute the SQL in `src/main/resources/sql/scripts.sql` to create the required `customer` and `authority` tables.
4. **Build and run**:
   ```bash
   ./mvnw spring-boot:run
   ```
5. **Access the app**: Open [http://localhost:8080](http://localhost:8080)

## Configuration
Configuration is managed via `application.properties` and `application_prod.properties`.

- **Profile selection**: Set `spring.profiles.active` to `default` or `prod`.
- **Environment variables**: Many properties can be overridden using environment variables (see below).
- **Session timeout**: Configurable via `SESSION_TIMEOUT` env variable.

## Notes from `application.properties`

- `${varName : defaultValue}` syntax allows overriding properties via environment variables.
- `spring.application.name` can be set with `SPRING_APP_NAME`.
- `logging.level.org.springframework.security` can be set with `SPRING_SECURITY_LOG_LEVEL` (e.g., TRACE, ERROR).
- Database connection details are configurable and use MySQL 8 driver.
- JPA/Hibernate settings:
  - `hibernate.dialect` is set for MySQL8
  - SQL formatting and show SQL are enabled in default, disabled in prod
  - `ddl-auto` is set to `update`
- `spring.config.import` is used to import `application_prod.properties` for production profile.
- `server.servlet.session.timeout` is settable via `SESSION_TIMEOUT` (default 20m, 10m in prod)
- **Security notes:**
  - Custom `UserDetailsService` is implemented in the `config` package and loads users from the `customer` table and authorities from the `authority` table
  - Authorities are managed via a custom `Authority` class and a `Many-To-One` relationship with `Customer`
  - The `hasAuthority()` method is used in security configuration for endpoint protection
  - See `scripts.sql` for the `customer` and `authority` table creation
  - Refactorings and steps are documented in the property file

## Project Structure
```
src/
  main/
    java/com/hegazy/ssecuritypart16/
      config/         # Security configuration and custom providers
      controller/     # Web controllers
      model/          # Entity models (Customer, Authority, etc.)
      repo/           # Spring Data repositories
    resources/
      application.properties
      application_prod.properties
      sql/scripts.sql
      templates/      # Thymeleaf templates
      static/         # Static assets
  test/
    java/com/hegazy/ssecuritypart16/
      Ssecuritypart16ApplicationTests.java
```

## References
- [Spring Security Reference](https://docs.spring.io/spring-security/reference/)
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Thymeleaf Documentation](https://www.thymeleaf.org/documentation.html)

---

**Author:** Mahmoud Hegazy  
**License:** MIT