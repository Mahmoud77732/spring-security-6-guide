# Spring Security Part 6 - Custom Database Authentication

A Spring Boot application demonstrating custom database authentication using Spring Security 6. This project showcases how to implement user authentication with a custom database schema and user details service.

## 🚀 Features

- **Custom Database Authentication**: Uses MySQL database with custom `customer` table
- **Spring Security 6**: Latest Spring Security implementation with modern configuration
- **JPA/Hibernate**: Database persistence with Spring Data JPA
- **Password Security**: BCrypt password encoding with compromised password checking
- **Role-based Access Control**: Different endpoints for different user roles
- **Form and HTTP Basic Authentication**: Multiple authentication methods
- **Actuator Integration**: Spring Boot Actuator for monitoring

## 🛠️ Technology Stack

- **Java 17**
- **Spring Boot 3.5.3**
- **Spring Security 6**
- **Spring Data JPA**
- **MySQL 8.0**
- **Maven**

## 📋 Prerequisites

- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6+

## 🗄️ Database Setup

### 1. Create Database
```sql
CREATE DATABASE springsecurity_db;
```

### 2. Create Customer Table
```sql
CREATE TABLE `springsecurity_db`.`customer` (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(45) NOT NULL,
    pwd VARCHAR(200) NOT NULL,
    role VARCHAR(45) NOT NULL
);
```

### 3. Insert Sample Users
```sql
-- User with 'read' role (password: User_12345@@)
INSERT INTO `springsecurity_db`.`customer` 
    (email, pwd, role) 
    VALUES('user1@gmail.com', '{noop}User_12345@@', 'read');

-- Admin with 'admin' role (password: admin123)
INSERT INTO `springsecurity_db`.`customer` 
    (email, pwd, role) 
    VALUES('admin1@gmail.com', '{bcrypt}$2a$12$UrZPFAis5fQB0fnX16Mm5OM76lXX2IjXTXX/4ZxlKiydgH1JJk6yK', 'admin');
```

## ⚙️ Configuration

### Database Configuration
Update `src/main/resources/application.properties` with your database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3307/springsecurity_db?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Security Configuration
The application uses custom security configuration in `ProjectSecurityConfig.java`:

- **Authenticated Endpoints**: `/myAccount`, `/myBalance`, `/myLoans`, `/myCards`
- **Public Endpoints**: `/notices`, `/contact`, `/error`
- **Authentication Methods**: Form login and HTTP Basic authentication
- **Password Encoding**: BCrypt with compromised password checking

## 🚀 Running the Application

### 1. Clone the Repository
```bash
git clone <repository-url>
cd ssecuritypart6
```

### 2. Build the Project
```bash
mvn clean install
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 📡 API Endpoints

### Public Endpoints (No Authentication Required)
- `GET /notices` - Display notices
- `GET /contact` - Contact information
- `GET /error` - Error page

### Protected Endpoints (Authentication Required)
- `GET /myAccount` - Account details
- `GET /myBalance` - Balance information
- `GET /myLoans` - Loan details
- `GET /myCards` - Card information

## 🔐 Authentication

### Login Credentials

#### User Account
- **Email**: `user1@gmail.com`
- **Password**: `User_12345@@`
- **Role**: `read`

#### Admin Account
- **Email**: `admin1@gmail.com`
- **Password**: `admin123`
- **Role**: `admin`

### Authentication Methods
1. **Form Login**: Navigate to any protected endpoint, you'll be redirected to the login form
2. **HTTP Basic**: Use browser's basic authentication prompt or API clients

## 🏗️ Project Structure

```
src/main/java/com/hegazy/ssecuritypart6/
├── config/
│   ├── ProjectSecurityConfig.java    # Security configuration
│   └── MyUserDetailsService.java     # Custom user details service
├── controller/
│   ├── AccountController.java         # Account endpoints
│   ├── BalanceController.java         # Balance endpoints
│   ├── CardsController.java          # Cards endpoints
│   ├── ContactController.java        # Contact endpoints
│   ├── LoansController.java          # Loans endpoints
│   └── NoticesController.java        # Notices endpoints
├── model/
│   └── Customer.java                 # Customer entity
├── repo/
│   └── CustomerRepo.java             # Customer repository
└── Ssecuritypart1Application.java    # Main application class
```

## 🔧 Key Components

### Custom User Details Service
The `MyUserDetailsService` class implements custom authentication by:
- Loading user details from the custom `customer` table
- Mapping database roles to Spring Security authorities
- Using email as the username for authentication

### Security Configuration
The `ProjectSecurityConfig` class provides:
- URL-based access control
- Multiple authentication methods
- Password encoding configuration
- Compromised password checking

## 🧪 Testing

### Manual Testing
1. Start the application
2. Navigate to `http://localhost:8080/myAccount`
3. You'll be redirected to the login form
4. Use the provided credentials to authenticate

### Automated Testing
```bash
mvn test
```

## 📝 Environment Variables

You can customize the application using environment variables:

```bash
export SPRING_APP_NAME=my-security-app
export SPRING_SECURITY_LOG_LEVEL=DEBUG
export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3307/springsecurity_db
export SPRING_DATASOURCE_USERNAME=your_username
export SPRING_DATASOURCE_PASSWORD=your_password
```

## 🔍 Monitoring

The application includes Spring Boot Actuator for monitoring:
- Health checks: `http://localhost:8080/actuator/health`
- Application info: `http://localhost:8080/actuator/info`

## 🛡️ Security Features

- **Password Encoding**: BCrypt with salt
- **Compromised Password Checking**: Integration with HaveIBeenPwned API
- **Role-based Authorization**: Different access levels for different roles
- **Session Management**: Secure session handling
- **CSRF Protection**: Built-in CSRF protection

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License.

## 🆘 Support

For support and questions, please open an issue in the repository.

---

**Note**: This is part 6 of a Spring Security tutorial series focusing on custom database authentication implementation.
