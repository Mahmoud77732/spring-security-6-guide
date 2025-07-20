# Spring Security Custom DB Example

This project demonstrates how to implement Spring Security 6 with a custom database for user authentication and authorization using Spring Boot 3 and MySQL.

## Features
- Custom user authentication using a MySQL database
- Secure password storage with bcrypt
- RESTful API endpoints for account, balance, loans, cards, notices, contact, and user registration
- Role-based access control
- CSRF protection disabled for API testing
- Example database schema and seed data

## Technologies Used
- Java 17
- Spring Boot 3.5.x
- Spring Security 6
- Spring Data JPA
- MySQL 8+
- Maven

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- MySQL 8+

### Setup
1. **Clone the repository:**
   ```bash
   git clone <repo-url>
   cd ssecuritypart7
   ```
2. **Configure the database:**
   - Create a MySQL database named `springsecurity_db` (or update the name in `application.properties`).
   - Update `src/main/resources/application.properties` with your MySQL credentials if needed.
   - Run the SQL script at `src/main/resources/sql/scripts.sql` to create tables and insert sample data.

3. **Build and run the application:**
   ```bash
   ./mvnw spring-boot:run
   # or
   mvn spring-boot:run
   ```

The application will start on [http://localhost:8080](http://localhost:8080).

## Configuration
Key settings in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3307/springsecurity_db?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
logging.level.org.springframework.security=TRACE
```

## API Endpoints
| Method | Endpoint      | Description                | Auth Required |
|--------|--------------|----------------------------|---------------|
| GET    | /myAccount   | Get account details        | Yes           |
| GET    | /myBalance   | Get balance details        | Yes           |
| GET    | /myLoans     | Get loans details          | Yes           |
| GET    | /myCards     | Get cards details          | Yes           |
| GET    | /notices     | Get notices                | No            |
| GET    | /contact     | Save contact inquiry       | No            |
| POST   | /register    | Register a new user        | No            |

### Example: Register a User
```json
POST /register
{
  "email": "user4@gmail.com",
  "pwd": "User_12345@@",
  "role": "read"
}
```
- Passwords are automatically encrypted (bcrypt) before storage.

## Authentication & Roles
- Users are authenticated against the `customer` table.
- Roles are stored in the `role` column (e.g., `read`, `admin`).
- Endpoints `/myAccount`, `/myBalance`, `/myLoans`, `/myCards` require authentication.
- Use HTTP Basic or form login for authentication.

## Database Schema
See [`src/main/resources/sql/scripts.sql`](src/main/resources/sql/scripts.sql) for table definitions and sample data.

## Testing
- The project includes a basic context load test.
- You can use tools like Postman or curl to test the endpoints.

## References
- [Spring Security Reference](https://docs.spring.io/spring-security/reference/)
- [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- See `HELP.md` for more guides and documentation links.

## License
This project is for educational purposes. Add a LICENSE file if you intend to open source it.
