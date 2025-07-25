# Spring Security Custom DB Example (ssecuritypart13)

## Overview
This project demonstrates advanced Spring Security configuration using a custom database for authentication and authorization. It covers both development and production security best practices, including session management, HTTPS enforcement, and custom authentication providers.

## Features
- Custom user authentication using a MySQL database
- Separate security configuration for development and production
- Session fixation and hijacking protection
- HTTPS enforcement in production
- Custom access denied and authentication entry points
- RESTful API endpoints for account, balance, cards, loans, notices, contact, and user registration
- Password encoding with bcrypt and compromised password checking

## Requirements
- Java 17+
- Maven
- MySQL 8+

## Setup
1. **Clone the repository**
2. **Configure the database:**
   - Create a database named `springsecurity_db` in MySQL.
   - Run the SQL script at `src/main/resources/sql/scripts.sql` to create tables and insert sample data.
3. **Configure application properties:**
   - Edit `src/main/resources/application.properties` and `application_prod.properties` as needed for your environment.
   - Key properties:
     - `spring.datasource.url`, `spring.datasource.username`, `spring.datasource.password`
     - `spring.profiles.active` (set to `prod` for production)
     - `server.servlet.session.timeout` (default: 20m, prod: 10m)
     - Logging and JPA settings
4. **Build and run the application:**
   - `./mvnw spring-boot:run`

## Configuration Notes (from application.properties)
- You can use environment variables in properties: `${VAR_NAME:defaultValue}`
- `spring.application.name` (default: securitypart1)
- `logging.level.org.springframework.security` (default: TRACE, prod: ERROR)
- `spring.datasource.*` for DB connection
- `spring.jpa.hibernate.ddl-auto=update` (auto schema update)
- `spring.config.import=application_prod.properties` (import prod config)
- `spring.profiles.active=default` (switch to `prod` for production)
- `server.servlet.session.timeout` (default: 20m, prod: 10m)

### Security Notes
- **Session Fixation:**
  - Spring Security uses the `changeSessionId` strategy by default to prevent session fixation attacks.
  - After login, the session ID is changed to prevent attackers from reusing a known session ID.
- **Session Hijacking:**
  - Use HTTPS (`server.ssl.enabled: true` in production)
  - Limit session timeout (`server.servlet.session.timeout`)
  - Limit concurrent sessions
  - Use Secure & HttpOnly cookies

## API Endpoints
| Method | Endpoint      | Description                        | Auth Required |
|--------|--------------|------------------------------------|--------------|
| GET    | /myAccount   | Get account details                 | Yes          |
| GET    | /myBalance   | Get balance details                 | Yes          |
| GET    | /myCards     | Get card details                    | Yes          |
| GET    | /myLoans     | Get loan details                    | Yes          |
| GET    | /notices     | Get notices                         | No           |
| GET    | /contact     | Save contact inquiry                | No           |
| POST   | /register    | Register a new user (see below)     | No           |

### User Registration
- **POST /register**
- Request body (JSON):
  ```json
  {
    "email": "user@example.com",
    "pwd": "yourPassword",
    "role": "read" // or "admin"
  }
  ```

## Database Schema
- See `src/main/resources/sql/scripts.sql` for full schema and sample data.
- Main tables:
  - `users` (username, password, enabled)
  - `authorities` (username, authority)
  - `customer` (id, email, pwd, role)

## Running in Production
- Activate the `prod` profile: `spring.profiles.active=prod`
- HTTPS is enforced in production configuration.
- Logging is set to ERROR for security logs.
- Session timeout is reduced (default: 10m).

## License
This project is for educational purposes.
