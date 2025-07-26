# Spring Security 6 - Custom Database Authentication Guide

## 📋 Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Security Configuration](#security-configuration)
- [Endpoints](#endpoints)
- [Database Setup](#database-setup)
- [Configuration](#configuration)
- [Setup & Running](#setup--running)
- [Development Notes](#development-notes)
- [Troubleshooting](#troubleshooting)

## 🎯 Overview

This project demonstrates **Spring Security 6** implementation with custom database authentication, HTTPS configuration, and comprehensive security features. It serves as a practical guide for implementing secure authentication and authorization in Spring Boot applications.

### Key Learning Objectives
- Custom authentication providers for different environments
- Role-based access control
- Session management and security events
- Custom login/logout functionality
- Dynamic UI based on authentication state
- Production-ready security configurations

## ✨ Features

### 🔐 Authentication & Authorization
- **Custom Database Authentication** - MySQL-based user management
- **Dual Profile Support** - Separate configurations for development and production
- **Role-Based Access Control** - Different access levels for users
- **Session Management** - Single session per user with timeout handling

### 🎨 User Interface
- **Custom Login Page** - Styled login form with error handling
- **Dynamic Header** - Shows username when logged in, login link when not
- **Responsive Navigation** - Public and protected links with proper redirects
- **Logout Functionality** - Secure logout with confirmation messages

### 🛡️ Security Features
- **HTTPS Enforcement** - Automatic HTTPS redirection in production
- **CSRF Protection** - Disabled for development, configurable for production
- **Password Security** - BCrypt encoding with compromised password checking
- **Authentication Events** - Success/failure logging and monitoring

### 🔧 Development Features
- **Hot Reload** - Spring Boot DevTools for development
- **SQL Logging** - Hibernate SQL output for debugging
- **Environment Variables** - Configurable via environment variables
- **Profile-Based Configuration** - Different settings per environment

## 📁 Project Structure

```
src/main/java/com/hegazy/ssecuritypart15/
├── config/                          # Security configurations
│   ├── ProjectSecurityConfig.java   # Default profile security
│   ├── ProjectSecurityProdConfig.java # Production profile security
│   ├── MyUserDetailsService.java    # Custom user details service
│   ├── MyUsernamePwdAuthenticationProvider.java # Default auth provider
│   └── MyProdUsernamePwdAuthenticationProvider.java # Prod auth provider
├── controller/                      # REST controllers
│   ├── HomeController.java          # Home page controller
│   ├── LoginController.java         # Login page controller
│   ├── AccountController.java       # Account details
│   ├── BalanceController.java       # Balance information
│   ├── CardsController.java         # Card details
│   ├── LoansController.java         # Loan information
│   ├── ContactController.java       # Contact form
│   ├── NoticesController.java       # Notices
│   └── UserController.java          # User registration
├── events/                          # Security events
│   └── AuthenticationEvents.java    # Login success/failure logging
├── exceptionhandling/               # Custom exception handlers
│   ├── CustomBasicAuthenticationEntryPoint.java
│   └── CustomAccessDeniedHandler.java
├── model/                           # Data models
│   └── Customer.java                # User entity
└── repo/                            # Data repositories
    └── CustomerRepo.java            # User repository
```

## 🔒 Security Configuration

### Profile-Based Security

#### Default Profile (`!prod`)
- **HTTP Allowed** - No HTTPS enforcement for development
- **Password Check Disabled** - Skips password validation for testing
- **Verbose Logging** - TRACE level security logging
- **Session Management** - 20-minute timeout, single session per user

#### Production Profile (`prod`)
- **HTTPS Enforcement** - All requests redirected to HTTPS
- **Password Validation** - Full password checking enabled
- **Error Logging** - ERROR level security logging only
- **Enhanced Exception Handling** - Custom access denied handler
- **Session Management** - 10-minute timeout, single session per user

### Authentication Flow
1. **User Registration** → POST `/register` with email/password/role
2. **Login** → Custom login page at `/login`
3. **Authentication** → Custom provider validates credentials
4. **Session Creation** → Single session per user enforced
5. **Access Control** → Role-based endpoint protection

## 🌐 Endpoints

| Endpoint | Method | Description | Access | Response |
|----------|--------|-------------|--------|----------|
| `/` | GET | Home page with navigation | Public | HTML page |
| `/login` | GET | Custom login page | Public | HTML form |
| `/login` | POST | Process login | Public | Redirect |
| `/logout` | POST | User logout | Authenticated | Redirect |
| `/contact` | GET | Contact information | Public | JSON |
| `/notices` | GET | System notices | Public | JSON |
| `/register` | POST | User registration | Public | JSON |
| `/myAccount` | GET | Account details | Authenticated | JSON |
| `/myBalance` | GET | Balance information | Authenticated | JSON |
| `/myCards` | GET | Card details | Authenticated | JSON |
| `/myLoans` | GET | Loan information | Authenticated | JSON |

## 🗄️ Database Setup

### Schema
```sql
CREATE TABLE `customer` (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(45) NOT NULL,
    pwd VARCHAR(1000) NOT NULL,
    role VARCHAR(45) NOT NULL
);
```

### Sample Data
```sql
INSERT INTO customer (email, pwd, role) VALUES 
('admin@example.com', '$2a$10$...', 'ADMIN'),
('user@example.com', '$2a$10$...', 'USER');
```

### Database Configuration
- **Driver**: MySQL 8.0
- **Port**: 3307 (configurable)
- **Database**: springsecurity_db
- **SSL**: Disabled for development
- **Timezone**: UTC

## ⚙️ Configuration

### Environment Variables
```bash
# Application
SPRING_APP_NAME=securitypart1
SPRING_SECURITY_LOG_LEVEL=TRACE  # or ERROR for production
SESSION_TIMEOUT=20m              # or 10m for production

# Database (if different from defaults)
DB_HOST=localhost
DB_PORT=3307
DB_NAME=springsecurity_db
DB_USERNAME=root
DB_PASSWORD=root
```

### Key Properties

#### Default Profile (`application.properties`)
```properties
# Application
spring.application.name=${SPRING_APP_NAME:securitypart1}
spring.profiles.active=default

# Security Logging
logging.level.org.springframework.security=${SPRING_SECURITY_LOG_LEVEL:TRACE}

# Database
spring.datasource.url=jdbc:mysql://localhost:3307/springsecurity_db?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.hibernate.show_sql=true
spring.jpa.hibernate.format_sql=true

# Session
server.servlet.session.timeout=${SESSION_TIMEOUT:20m}
```

#### Production Profile (`application_prod.properties`)
```properties
# Profile Activation
spring.config.activate.on-profile=prod

# Security Logging (ERROR only)
logging.level.org.springframework.security=${SPRING_SECURITY_LOG_LEVEL:ERROR}

# JPA/Hibernate (no SQL logging)
spring.jpa.hibernate.show_sql=false
spring.jpa.hibernate.format_sql=false

# Session (shorter timeout)
server.servlet.session.timeout=${SESSION_TIMEOUT:10m}
```

## 🚀 Setup & Running

### Prerequisites
- Java 17+
- Maven 3.6+
- MySQL 8.0+
- Git

### Quick Start
1. **Clone and Navigate**
   ```bash
   git clone <repository-url>
   cd ssecuritypart15
   ```

2. **Database Setup**
   ```bash
   # Start MySQL
   mysql -u root -p
   
   # Create database
   CREATE DATABASE springsecurity_db;
   USE springsecurity_db;
   
   # Run schema script
   source src/main/resources/sql/scripts.sql;
   ```

3. **Configure Database** (if needed)
   ```bash
   # Edit application.properties if using different credentials
   vim src/main/resources/application.properties
   ```

4. **Build and Run**
   ```bash
   # Default profile (development)
   ./mvnw spring-boot:run
   
   # Production profile
   ./mvnw spring-boot:run -Dspring.profiles.active=prod
   ```

5. **Access Application**
   - **Home**: http://localhost:8080/
   - **Login**: http://localhost:8080/login
   - **Protected**: http://localhost:8080/myBalance (requires login)

### Testing
1. **Public Access**: Visit `/`, `/contact`, `/notices`
2. **Protected Access**: Try `/myBalance` → redirects to login
3. **Login Flow**: Use credentials from database
4. **Logout**: Click logout button or visit `/logout`

## 📝 Development Notes

### Spring Security Form Login Implementation

#### Step-by-Step Process:
1. **Create Home Controller**
   ```java
   @Controller
   public class HomeController {
       @GetMapping("/")
       public String home() { return "home"; }
   }
   ```

2. **Create Home Template**
   - File: `src/main/resources/templates/home.html`
   - Include Thymeleaf security namespace
   - Add navigation with public/protected links

3. **Security Configuration**
   - Add `/` to permitted URLs
   - Configure form login with custom page
   - Set up logout functionality

4. **Custom Login Page**
   - Create `login.html` template
   - Add form with username/password fields
   - Configure security to use custom login page

5. **Login Controller**
   ```java
   @Controller
   public class LoginController {
       @GetMapping("/login")
       public String login() { return "login"; }
   }
   ```

6. **Logout Configuration**
   - Add logout button to templates
   - Configure logout URL and success redirect
   - Handle logout messages

7. **Dynamic Header**
   - Add Thymeleaf security extras dependency
   - Use `sec:authorize` for conditional content
   - Display username when authenticated

### Key Dependencies
```xml
<!-- Thymeleaf -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

<!-- Thymeleaf Security Integration -->
<dependency>
    <groupId>org.thymeleaf.extras</groupId>
    <artifactId>thymeleaf-extras-springsecurity6</artifactId>
</dependency>

<!-- Spring Security -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

### Template Security Expressions
```html
<!-- Show when authenticated -->
<span sec:authorize="isAuthenticated()">
    Welcome, <span th:text="${#authentication.name}">User</span>
</span>

<!-- Show when not authenticated -->
<a th:href="@{/login}" sec:authorize="!isAuthenticated()">Login</a>

<!-- Role-based access -->
<div sec:authorize="hasRole('ADMIN')">Admin content</div>
```

## 🔧 Troubleshooting

### Common Issues

#### 1. Login Redirects to CSS File
**Problem**: After login, redirects to `/assets/css/style.css?continue`
**Solution**: Add static resources to permitted URLs
```java
.requestMatchers("/assets/**", "/css/**", "/js/**").permitAll()
```

#### 2. Template Not Found
**Problem**: Whitelabel error for template pages
**Solution**: 
- Ensure Thymeleaf dependency is present
- Check template location (`src/main/resources/templates/`)
- Verify template name matches controller return value

#### 3. Logout Not Working
**Problem**: 404 error when accessing `/logout`
**Solution**:
- Use POST method for logout (form submit)
- Ensure CSRF is disabled or configured properly
- Add logout URL to permitted endpoints

#### 4. Authentication Not Working
**Problem**: Login fails or redirects incorrectly
**Solution**:
- Check database connection and user data
- Verify authentication provider configuration
- Check security logging for detailed errors

#### 5. HTTPS Issues in Production
**Problem**: HTTPS not enforced or certificate issues
**Solution**:
- Ensure production profile is active
- Configure proper SSL certificates
- Check HTTPS redirect configuration

### Debug Mode
Enable debug logging:
```properties
logging.level.org.springframework.security=DEBUG
logging.level.com.hegazy.ssecuritypart15=DEBUG
```

### Database Connection Issues
```bash
# Test MySQL connection
mysql -h localhost -P 3307 -u root -p springsecurity_db

# Check application logs for connection errors
tail -f logs/application.log
```

## 📚 Additional Resources

- [Spring Security Reference](https://docs.spring.io/spring-security/reference/)
- [Spring Boot Security](https://spring.io/guides/gs/securing-web/)
- [Thymeleaf Security](https://www.thymeleaf.org/doc/articles/springsecurity.html)
- [MySQL Documentation](https://dev.mysql.com/doc/)

## 📄 License

This project is for educational purposes. Feel free to use and modify for learning Spring Security concepts.

---

**Happy Learning! 🚀**
