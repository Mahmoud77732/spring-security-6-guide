# Spring Security Custom DB Configuration Example

## Overview
This project demonstrates advanced Spring Security 6 features with a focus on custom database authentication, HTTPS configuration, and event handling. It uses a MySQL database for user management and provides both default and production security profiles.

## Features
- Custom authentication providers for different profiles (default and prod)
- Secure endpoints with role-based access
- Custom user details service
- Authentication event logging (success and failure)
- Custom exception handling for authentication and access denied
- HTTPS enforcement in production
- Environment-based configuration

## Endpoints
| Endpoint         | Method | Description                        | Access         |
|-----------------|--------|------------------------------------|---------------|
| /register        | POST   | Register a new user                | Public        |
| /myAccount      | GET    | Get account details                | Authenticated |
| /myBalance      | GET    | Get balance details                | Authenticated |
| /myCards        | GET    | Get card details                   | Authenticated |
| /myLoans        | GET    | Get loan details                   | Authenticated |
| /contact        | GET    | Save contact inquiry details       | Public        |
| /notices        | GET    | Get notices                        | Public        |

## Security Configuration
- **Default Profile**: Allows HTTP, disables HTTPS redirection, uses a custom authentication provider that does not check passwords (for demonstration).
- **Production Profile**: Enforces HTTPS, uses a custom authentication provider that checks passwords, and provides enhanced exception handling.
- **Session Management**: Limits to one session per user, with custom invalid session handling.
- **CSRF**: Disabled for simplicity.
- **Password Encoding**: Uses bcrypt by default.
- **Compromised Password Check**: Integrates with HaveIBeenPwned API.

## Authentication Events
Authentication events are triggered during the authentication process to notify the application about the outcome (success or failure) of an authentication attempt.
- `AuthenticationSuccessEvent`: Fired when a user successfully authenticates.
- `AbstractAuthenticationFailureEvent`: Fired when authentication fails.

These are handled in `AuthenticationEvents.java` and logged accordingly.

## Exception Handling
- **CustomBasicAuthenticationEntryPoint**: Returns a JSON response with error details for unauthorized access.
- **CustomAccessDeniedHandler**: Returns a JSON response with error details for forbidden access.

## Database Schema
The application uses the following tables:

```sql
create table `customer`(
    id int not null AUTO_INCREMENT primary key,
    email varchar(45) not null,
    pwd varchar(1000) not null,
    role varchar(45) not null
);
```

Sample data is provided in `src/main/resources/sql/scripts.sql`.

## Setup & Running
1. Ensure you have MySQL running and accessible at `localhost:3307`.
2. Create the database and tables using the provided SQL script in `src/main/resources/sql/scripts.sql`.
3. Update database credentials in `src/main/resources/application.properties` if needed.
4. Build and run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
5. The application will start on the default port (8080).

## Profiles
- **default**: For development/testing (HTTP allowed, verbose logging, password check skipped)
- **prod**: For production (HTTPS enforced, error logging, password check enabled)

Switch profiles by setting the `spring.profiles.active` property.

## application.properties Notes
```
# ${varName : defaultValue}  -> environment variable
spring.application.name= ${SPRING_APP_NAME:securitypart1}

logging.level.org.springframework.security=${SPRING_SECURITY_LOG_LEVEL:TRACE}

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3307/springsecurity_db?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.format_sql = true
spring.jpa.hibernate.show_sql = true
spring.jpa.hibernate.ddl-auto = update

spring.config.import = application_prod.properties

# default | prod
spring.profiles.active = default

server.servlet.session.timeout = ${SESSION_TIMEOUT:20m}

###
# ? Authentication Events:
#   * They are events triggered during the authentication process to notify the app about the outcome 
#   *   (success or failure) of an authentication attempt
#   * Ex:
#   *   AuthenticationSuccessEvent: Fired when a user successfully authenticates
#   *   AbstractAuthenticationFailureEvent: Fired when authentication fails
# ! Steps:
#   (1) create "events" package
#   (2) create "AuthenticationEvents" class
###
```

## License
This project is for educational purposes.
