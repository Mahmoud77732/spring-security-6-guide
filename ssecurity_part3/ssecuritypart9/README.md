# ssecuritypart9

A Spring Boot project demonstrating advanced Spring Security configuration with custom database authentication, multiple profiles, and user registration.

## Overview
This project showcases how to use custom database tables for user authentication and authorization in Spring Security. It supports multiple profiles (`default` and `prod`), each with its own authentication provider, and provides RESTful endpoints for typical banking operations.

## Features
- Custom authentication providers for different profiles
- User registration with password encoding
- Profile-based security configuration (`default` and `prod`)
- REST endpoints for account, balance, cards, loans, notices, and contact
- Environment-based configuration
- Database initialization scripts for MySQL

## Tech Stack
- Java 17
- Spring Boot 3.x
  - Spring Security
  - Spring Web
  - Spring Data JPA
  - Spring Boot Actuator
- MySQL
- Maven

## Getting Started

### Prerequisites
- Java 17+
- Maven 3.6+
- MySQL 8+

### Clone the Repository
```bash
git clone <repo-url>
cd ssecuritypart9
```

### Database Setup
1. Create a MySQL database named `springsecurity_db`.
2. Run the SQL script at `src/main/resources/sql/scripts.sql` to create tables and insert initial data.

### Configuration
- Default configuration is in `src/main/resources/application.properties`.
- Production-specific overrides are in `src/main/resources/application_prod.properties`.
- Update database credentials as needed:
  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3307/springsecurity_db?useSSL=false&... 
  spring.datasource.username=root
  spring.datasource.password=root
  ```
- Active profile is set via `spring.profiles.active` (default: `prod`).
- **Tip:** You can set environment variables (such as `SPRING_PROFILES_ACTIVE`) using the `env` block in your IDE's launch configuration or in your shell environment. For example, in VSCode, add an `env` block to your `launch.json`:
  ```json
  "env": {
    "SPRING_PROFILES_ACTIVE": "default"
  }
  ```

### Running the Application
#### Using Maven
```bash
mvn spring-boot:run
```
#### Or with the Maven Wrapper
```bash
./mvnw spring-boot:run
```

## API Endpoints
| Endpoint      | Method | Description                | Auth Required |
|-------------- |--------|---------------------------|--------------|
| /register     | POST   | Register a new user        | No           |
| /myAccount    | GET    | Get account details        | Yes          |
| /myBalance    | GET    | Get balance details        | Yes          |
| /myLoans      | GET    | Get loans details          | No           |
| /myCards      | GET    | Get cards details          | No           |
| /notices      | GET    | Get notices                | No           |
| /contact      | GET    | Save contact inquiry       | No           |

- Endpoints `/myAccount`, `/myBalance` require authentication.
- Use HTTP Basic or form login.

## Profiles & Security
- **default**: Uses a custom authentication provider that does not check passwords (for demo/testing).
- **prod**: Uses a custom authentication provider with password validation and additional checks.
- Switch profiles by setting `spring.profiles.active` in properties or environment variables.

## Database Schema
- See `src/main/resources/sql/scripts.sql` for table definitions and sample data for `users`, `authorities`, and `customer` tables.

## Contribution
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
This project is for educational purposes.
