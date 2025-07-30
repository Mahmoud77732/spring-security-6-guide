# spring-security-jwt-20

A Spring Boot project demonstrating custom database configuration for Spring Security, including JWT authentication, CSRF protection, and role-based access control.

## Features

- **Spring Security 6** integration
- **JWT (JSON Web Token)** authentication for stateless APIs
- **Role-based access control** for endpoints
- **CSRF protection** using cookies
- **Custom filters** for JWT generation and validation
- **MySQL database** integration
- **Profile-based configuration** (`default`, `prod`)
- **RESTful endpoints** for account, balance, loans, cards, user, and notices

## JWT Overview

- **JWT Structure**: Header, Payload, Signature
- **Claims**: Registered (iss, exp, sub, aud), Public, Private
- **Advantages**:
  - Statelessness
  - Self-contained user info and roles
  - Security and expiration
  - Reusability (SSO, microservices)
  - Cross-platform compatibility

## Security Configuration

- **Session Management**:
  - `default` profile: Stateless (no JSESSIONID)
  - `prod` profile: Session-based
- **CSRF**: Enabled with cookie repository, ignored for `/contact`, `/register`, `/apiLogin`
- **JWT Filters**:
  - `JWTTokenGeneratorFilter`: Generates JWT after authentication
  - `JWTTokenValidatorFilter`: Validates JWT on incoming requests

## Endpoints

| Endpoint         | Method | Description                | Auth Required | Role/Authority |
|------------------|--------|----------------------------|---------------|---------------|
| `/`              | GET    | Home page                  | No            | -             |
| `/home`          | GET    | Home page                  | No            | -             |
| `/contact`       | GET/POST| Contact form              | No            | -             |
| `/register`      | POST   | User registration          | No            | -             |
| `/myAccount`     | GET    | Account info               | Yes           | USER          |
| `/myBalance`     | GET    | Balance info               | Yes           | USER, ADMIN   |
| `/myLoans`       | GET    | Loans info                 | Yes           | USER          |
| `/myCards`       | GET    | Cards info                 | Yes           | USER          |
| `/user`          | GET    | User info                  | Yes           | Authenticated |
| `/notices`       | GET    | Notices                    | No            | -             |
| `/apiLogin`      | POST   | JWT login endpoint         | No            | -             |

## JWT Authentication Flow

1. **Login**:  
   `POST /apiLogin`  
   ```json
   {
     "username": "user1@gmail.com",
     "password": "User_12345@@"
   }
   ```
   Response:
   ```json
   {
     "status": "OK",
     "jwtToken": "eyJhbGciOiJIUzI1..."
   }
   ```

2. **Authenticated Request**:  
   `GET /myAccount`  
   Headers:  
   `Authorization: <JWT_Token>`

## Configuration

- **Profiles**:  
  - `default`: Development settings, debug logging, stateless JWT
  - `prod`: Production settings, session-based, stricter security

- **Database**:  
  - MySQL (see [application.properties](src/main/resources/application.properties) and [application_prod.properties](src/main/resources/application_prod.properties))

## Running the Project

1. **Install dependencies**:
   ```sh
   mvn clean install
   ```
2. **Run the application**:
   ```sh
   mvn spring-boot:run
   ```
   Or use the provided `mvnw` script.

3. **Switch profiles**:
   - Default: No action needed
   - Production:  
     ```sh
     mvn spring-boot:run -Dspring.profiles.active=prod
     ```

## Testing

- Use Postman or curl to test endpoints.
- Example JWT-protected request:
  ```sh
  curl -H "Authorization: <JWT_Token>" http://localhost:8080/myAccount
  ```

## Notes

- See [src/main/resources/application.properties](src/main/resources/application.properties) for detailed comments and configuration tips.
- JWT secret key and header constants are defined in [`ApplicationConstants`](src/main/java/com/hegazy