# Spring Security Application

A comprehensive Spring Boot application demonstrating advanced Spring Security features with JDBC-based authentication, password encoding, and compromised password checking.

## 🏦 Project Overview

This is an application that showcases Spring Security 6.x implementation with the following features:

- **JDBC-based User Authentication**: User credentials stored in MySQL database
- **Password Security**: BCrypt password encoding with compromised password detection
- **Role-based Access Control**: Different endpoints for different user roles
- **Security Headers**: Built-in security headers and CSRF protection
- **Actuator Integration**: Health checks and application monitoring

## 🚀 Features

### Security Features
- ✅ JDBC User Details Manager for database authentication
- ✅ BCrypt password encoding with delegating password encoder
- ✅ Compromised password checking using HaveIBeenPwned API
- ✅ Role-based authorization (admin, read)
- ✅ Form-based and HTTP Basic authentication
- ✅ CSRF protection enabled
- ✅ Security headers automatically configured

### Endpoints
- **Account Management**: `/myAccount` - View account details
- **Balance Information**: `/myBalance` - Check account balance
- **Loan Services**: `/myLoans` - Access loan information
- **Card Services**: `/myCards` - Manage cards
- **Public Information**: `/notices`, `/contact` - Public access
- **Error Handling**: `/error` - Error pages

## 🛠️ Technology Stack

- **Java**: 21
- **Spring Boot**: 3.5.3
- **Spring Security**: 6.x
- **Database**: MySQL 8.0
- **Build Tool**: Maven
- **Container**: Docker (for MySQL)

## 📋 Prerequisites

- Java 21 or higher
- Maven 3.6+
- Docker (for MySQL container)
- MySQL Workbench (optional, for database management)

## 🚀 Quick Start

### 1. Database Setup

#### Option A: Using Docker (Recommended)
```bash
# Pull MySQL image
docker pull mysql:latest

# Run MySQL container
docker run --name mysql-container \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=springsecurity_db \
  -d -p 3307:3306 mysql:latest
```

#### Option B: Using Local MySQL
- Install MySQL 8.0+
- Create database: `springsecurity_db`
- Update `application.properties` with your MySQL credentials

### 2. Database Schema Setup

Execute the SQL scripts in `src/main/resources/sql/scripts.sql`:

```sql
-- create connection "springsecurity_connection" + "3307"

-- Create database: `springsecurity_db` schema

-- Create tables
CREATE TABLE `springsecurity_db`.`users` (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE `springsecurity_db`.`authorities` (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY(username) REFERENCES users(username)
);

CREATE UNIQUE INDEX ix_auth_username ON `springsecurity_db`.`authorities` (username,authority);

-- Insert default users
INSERT IGNORE INTO `springsecurity_db`.`users` VALUES('user', '{noop}User_12345@@', '1');
INSERT IGNORE INTO `springsecurity_db`.`authorities` VALUES('user', 'read');

INSERT IGNORE INTO `springsecurity_db`.`users` VALUES('admin', '{noop}Admin_12345@@', '1');
INSERT IGNORE INTO `springsecurity_db`.`authorities` VALUES('admin', 'admin');
```

### 3. Application Setup

```bash
# Clone the repository
git clone <repository-url>
cd ssecuritypart5

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 🔐 Default Users

| Username | Password | Role | Access |
|----------|----------|------|--------|
| `user` | `User_12345@@` | `read` | Basic operations |
| `admin` | `Admin_12345@@` | `admin` | Full access to all features |

## 📡 API Endpoints

### Public Endpoints (No Authentication Required)
- `GET /notices` - View notices
- `GET /contact` - Contact information
- `GET /error` - Error pages

### Protected Endpoints (Authentication Required)
- `GET /myAccount` - Account details
- `GET /myBalance` - Account balance
- `GET /myLoans` - Loan information
- `GET /myCards` - Card management

## 🔧 Configuration

### Security Configuration
The security configuration is in `ProjectSecurityConfig.java`:

```java
@Configuration
public class ProjectSecurityConfig {
    // Security filter chain configuration
    // JDBC user details manager
    // Password encoder with BCrypt
    // Compromised password checker
}
```

### Database Configuration
Key properties in `application.properties`:

```properties
# Database connection
spring.datasource.url=jdbc:mysql://localhost:3307/springsecurity_db
spring.datasource.username=root
spring.datasource.password=root

# JPA/Hibernate settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## 🧪 Testing

### Running Tests
```bash
mvn test
```

### Manual Testing
1. Access `http://localhost:8080`
2. Try accessing protected endpoints (will redirect to login)
3. Login with provided credentials
4. Test different user roles and permissions

## 📊 Monitoring

The application includes Spring Boot Actuator for monitoring:

- Health checks: `http://localhost:8080/actuator/health`
- Application info: `http://localhost:8080/actuator/info`

## 🔒 Security Features Explained

### 1. Password Encoding
- Uses `PasswordEncoderFactories.createDelegatingPasswordEncoder()`
- Supports multiple encoding algorithms (bcrypt, noop, etc.)
- Automatically detects encoding type from password prefix

### 2. Compromised Password Detection
- Integrates with HaveIBeenPwned API
- Checks passwords against known compromised password database
- Enhances security by preventing use of compromised passwords

### 3. JDBC Authentication
- User credentials stored in MySQL database
- Supports user roles and authorities
- Scalable for production use

## 🐛 Troubleshooting

### Common Issues

1. **Database Connection Error**
   - Ensure MySQL container is running
   - Check port 3307 is available
   - Verify database credentials

2. **Authentication Fails**
   - Ensure database tables are created
   - Check user credentials in database
   - Verify password encoding format

3. **Port Already in Use**
   - Change port in `application.properties`
   - Kill existing process on port 8080

## 📝 Development

### Project Structure
```
src/main/java/com/hegazy/ssecuritypart5/
├── config/
│   └── ProjectSecurityConfig.java    # Security configuration
├── controller/
│   ├── AccountController.java         # Account endpoints
│   ├── BalanceController.java         # Balance endpoints
│   ├── CardsController.java          # Card endpoints
│   ├── ContactController.java        # Contact endpoints
│   ├── LoansController.java          # Loan endpoints
│   └── NoticesController.java        # Notice endpoints
└── Ssecuritypart1Application.java    # Main application class
```

### Adding New Endpoints
1. Create controller in `controller/` package
2. Add security configuration in `ProjectSecurityConfig.java`
3. Test with different user roles

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📄 License

This project is for educational purposes and demonstrates Spring Security best practices.

## 🙏 Acknowledgments

- Spring Security team for the excellent framework
- HaveIBeenPwned for password security API
- Spring Boot team for the amazing developer experience

---

**Note**: This is a demonstration project. For production use, ensure proper security hardening, environment-specific configurations, and comprehensive testing.
