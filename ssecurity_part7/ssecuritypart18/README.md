# ssecuritypart18

A Spring Boot application demonstrating advanced Spring Security concepts, focusing on:

- Using `hasRole()` and `hasAnyRole()` for method and URL authorization
- Custom database configuration for users and roles
- Implementing an `events` package for authorization and authentication event listeners

## Features

- **Role-Based Access Control:**  
  Secure endpoints and methods using `hasRole()` and `hasAnyRole()` expressions.
- **Custom Database Integration:**  
  User and authority data are managed in a MySQL database. See [`src/main/resources/sql/scripts.sql`](src/main/resources/sql/scripts.sql) for schema and sample data.
- **Event Listeners:**  
  The `events` package contains listeners for authentication and authorization events, enabling custom logging or auditing.
- **Thymeleaf Integration:**  
  Dynamic UI updates based on authentication status using Thymeleaf and Spring Security extras.

## Project Structure

- `src/main/java/com/hegazy/ssecuritypart18/`
  - `events/`  
    Contains custom event listener classes for authentication and authorization events.
  - `Ssecuritypart18Application.java`  
    Main application entry point.
- `src/main/resources/templates/`  
  Thymeleaf templates for UI.
- `src/main/resources/sql/scripts.sql`  
  SQL scripts for database schema and initial data.

## Security Highlights

- **hasRole() / hasAnyRole():**  
  Used in security configuration to restrict access based on user roles.
- **Event Listeners:**  
  Listen to Spring Security events such as authentication success/failure and authorization decisions.

## Getting Started

1. **Database Setup:**  
   Ensure MySQL is running and accessible. Update credentials in [`src/main/resources/application.properties`](src/main/resources/application.properties) if needed.
   Run the SQL script in [`src/main/resources/sql/scripts.sql`](src/main/resources/sql/scripts.sql) to create tables and insert sample data.

2. **Build and Run:**
   ```sh
   ./mvnw spring-boot:run
   ```

3. **Access the Application:**  
   Open [http://localhost:8080](http://localhost:8080) in your browser.

## Useful Endpoints

- `/` - Home (public)
- `/myBalance`, `/myLoans`, `/myCards`, `/myAccount` - Secured, require authentication and appropriate roles

## Customization

- **Roles and Authorities:**  
  Modify the `users` and `authorities` tables in the database to add or change users and roles.
- **Event Handling:**  
  Extend or modify classes in the `events` package to customize event handling logic.

## References

- [Spring Security Documentation](https://docs.spring.io/spring-security/site/docs/current/reference/html5/)
- [Thymeleaf + Spring Security Integration](https://www.thymeleaf.org/doc/articles/springsecurity.html)

---

**Note:**  
This project is for educational purposes and demonstrates security best practices using Spring