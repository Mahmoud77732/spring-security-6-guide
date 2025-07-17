# ssecuritypart2

A demo project for Spring Boot Security, showcasing basic authentication, endpoint protection, and custom security configuration.

## Overview
This project demonstrates how to secure a Spring Boot application using Spring Security. It includes examples of protected and public endpoints, form-based login, HTTP Basic authentication, and environment-based configuration.

## Features
- Spring Boot 3.5.3
- Java 21
- Spring Security integration
- Form login and HTTP Basic authentication
- Custom endpoint access rules
- Environment variable-based configuration

## Endpoints
| Endpoint      | Method | Description                          | Authentication Required |
|--------------|--------|--------------------------------------|------------------------|
| `/myAccount` | GET    | Get account details                  | Yes                    |
| `/myBalance` | GET    | Get balance details                  | Yes                    |
| `/myLoans`   | GET    | Get loans details                    | Yes                    |
| `/myCards`   | GET    | Get cards details                    | Yes                    |
| `/notices`   | GET    | Get notices                          | No                     |
| `/contact`   | GET    | Save contact inquiry                 | No                     |
| `/welcome`   | GET    | Welcome message                      | No                     |

## Security Configuration
- Endpoints `/myAccount`, `/myBalance`, `/myLoans`, `/myCards` require authentication.
- Endpoints `/notices`, `/contact`, `/myLoans`, `/myCards`, `/error` are permitted to all (note: `/myLoans` and `/myCards` are listed in both, so they are accessible without authentication).
- Supports both form-based login and HTTP Basic authentication.

## Getting Started

### Prerequisites
- Java 21+
- Maven 3.8+

### Setup
1. Clone the repository:
   ```bash
   git clone <repo-url>
   cd ssecuritypart2
   ```
2. Build the project:
   ```bash
   ./mvnw clean install
   ```
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

### Default Credentials
The default username and password are set via environment variables or fallback values in `src/main/resources/application.properties`:

- Username: `admin`
- Password: `admin123`

You can override these by setting the following environment variables before starting the app:
- `SECURITY_USERNAME`
- `SECURITY_PASSWORD`

### Example (Linux/macOS):
```bash
export SECURITY_USERNAME=myuser
export SECURITY_PASSWORD=mypassword
./mvnw spring-boot:run
```

## Logging
Spring Security logging level can be controlled via the `SPRING_SECURITY_LOG_LEVEL` environment variable (default: `TRACE`).

## License
This project is for demonstration and educational purposes.
