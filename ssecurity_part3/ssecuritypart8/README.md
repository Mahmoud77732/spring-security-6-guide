# Spring Security Custom DB Authentication Example

## Overview
This project demonstrates a Spring Boot application with custom authentication and authorization using Spring Security 6, backed by a MySQL database. It provides RESTful endpoints for account, balance, loans, cards, notices, and user registration, with role-based access control and password encoding.

## Features
- Custom authentication provider using database-backed user details
- Secure password storage with BCrypt
- RESTful API endpoints for banking operations
- Role-based access control for endpoints
- User registration with password hashing
- Example SQL scripts for database setup
- Spring Boot 3.5.x, Java 17

## Technologies Used
- Java 17
- Spring Boot 3.5.x
- Spring Security 6
- Spring Data JPA
- MySQL
- Maven

## Getting Started

### Prerequisites
- Java 17+
- Maven
- MySQL (or compatible database)

### Setup Steps
1. **Clone the repository:**
   ```bash
   git clone <repo-url>
   cd ssecuritypart8
   ```
2. **Configure the database:**
   - Ensure MySQL is running.
   - Update `src/main/resources/application.properties` if needed:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3307/springsecurity_db?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&serverTimezone=UTC
     spring.datasource.username=root
     spring.datasource.password=root
     ```
   - Run the SQL script in `src/main/resources/sql/scripts.sql` to create tables and seed data.
3. **Build and run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

## Database Structure
- **Tables:**
  - `users` (username, password, enabled)
  - `authorities` (username, authority)
  - `customer` (id, email, pwd, role)
- See `src/main/resources/sql/scripts.sql` for schema and sample data.

## API Endpoints
| Endpoint         | Method | Auth Required | Description                       |
|------------------|--------|---------------|-----------------------------------|
| `/register`      | POST   | No            | Register a new user               |
| `/myAccount`     | GET    | Yes           | Get account details               |
| `/myBalance`     | GET    | Yes           | Get balance details               |
| `/myLoans`       | GET    | Yes           | Get loans details                 |
| `/myCards`       | GET    | Yes           | Get cards details                 |
| `/notices`       | GET    | No            | Get notices                       |
| `/contact`       | GET    | No            | Contact endpoint                  |

### Example: Register User
```http
POST /register
Content-Type: application/json
{
  "email": "user@example.com",
  "pwd": "password123",
  "role": "read"
}
```

## Security
- **Authentication:**
  - Custom `AuthenticationProvider` (`MyUsernamePwdAuthenticationProvider`)
  - User details loaded from `customer` table
  - Passwords encoded with BCrypt
- **Authorization:**
  - `/myAccount`, `/myBalance`, `/myLoans`, `/myCards` require authentication
  - `/notices`, `/contact`, `/register` are public
- **CSRF:** Disabled for simplicity (not recommended for production)

## Customization
- Change database settings in `application.properties`
- Update roles and authorities in the database
- Extend endpoints/controllers as needed

## License
This project is for educational purposes.
