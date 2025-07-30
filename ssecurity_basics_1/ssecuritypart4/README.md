# Spring Security Demo Project

This project is a demonstration of Spring Boot 3 and Spring Security 6, showcasing basic authentication, endpoint protection, and in-memory user management. It is intended as a learning resource for understanding how to secure REST APIs using Spring Security.

## Features

- **Spring Boot 3.5.3** and **Java 21**
- **Spring Security 6** configuration
- In-memory user store with BCrypt password encoding
- Two users: `user` (password: `User_12345@@`) and `admin` (password: `Admin_12345@@`)
- Password strength checking using HaveIBeenPwned API
- Form login and HTTP Basic authentication
- Multiple REST endpoints with different access levels

## Endpoints

| Endpoint      | Method | Access         | Description                       |
|--------------|--------|----------------|-----------------------------------|
| /welcome     | GET    | Public         | Welcome message                   |
| /notices     | GET    | Public         | Notices information               |
| /contact     | GET    | Public         | Save contact inquiry              |
| /myAccount   | GET    | Authenticated  | Account details                   |
| /myBalance   | GET    | Authenticated  | Balance details                   |
| /myLoans     | GET    | Public/Auth    | Loans details (public & auth)     |
| /myCards     | GET    | Public/Auth    | Card details (public & auth)      |

- Endpoints `/myAccount`, `/myBalance`, `/myLoans`, `/myCards` require authentication.
- Endpoints `/notices`, `/contact`, `/myLoans`, `/myCards`, `/error` are permitted to all.

## Security Configuration

- **InMemoryUserDetailsManager** is used for user storage.
- Passwords are encoded with BCrypt.
- Passwords are checked against the HaveIBeenPwned API to prevent compromised passwords.
- Both form-based and HTTP Basic authentication are enabled.
- Access rules are defined in `ProjectSecurityConfig.java`.

## Getting Started

### Prerequisites
- Java 21+
- Maven 3.8+

### Running the Application

1. Clone the repository:
   ```bash
   git clone <repo-url>
   cd ssecuritypart4
   ```
2. Build and run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
   Or with Maven:
   ```bash
   mvn spring-boot:run
   ```

3. Access the endpoints using a tool like curl, Postman, or your browser.

### Example: Accessing a Protected Endpoint

```bash
curl -u user:User_12345@@ http://localhost:8080/myAccount
```

## Configuration

- Application properties can be set in `src/main/resources/application.properties`.
- Default application name: `securitypart1`
- Logging for Spring Security is set to TRACE by default.

## License

This project is for educational purposes.
