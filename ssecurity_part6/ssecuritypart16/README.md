# ssecuritypart16

A Spring Boot application demonstrating advanced Spring Security 6 features, including custom database authentication, CSRF and CORS configuration, and secure REST endpoints.

## Table of Contents

- [Features](#features)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Database Setup](#database-setup)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Security](#security)
- [Testing](#testing)
- [License](#license)

---

## Features

- Custom user authentication using MySQL database
- CSRF protection with custom filter and cookie-based tokens
- CORS configuration for frontend integration (e.g., Angular)
- RESTful endpoints for accounts, balances, loans, cards, notices, and contact messages
- Profile-based security configuration (`default` and `prod`)
- Password encoding and compromised password checking

## Project Structure

```
.
├── src/
│   ├── main/
│   │   ├── java/com/hegazy/ssecuritypart16/
│   │   │   ├── config/         # Security and app configuration
│   │   │   ├── controller/     # REST controllers
│   │   │   ├── exceptionhandling/
│   │   │   ├── filter/
│   │   │   ├── model/          # JPA entities
│   │   │   └── repo/           # Spring Data repositories
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application_prod.properties
│   │       ├── sql/scripts.sql
│   │       └── templates/      # Thymeleaf templates
│   └── test/
├── pom.xml
└── README.md
```

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.9+
- MySQL 8+

### Clone the Repository

```sh
git clone https://github.com/yourusername/ssecuritypart16.git
cd ssecuritypart16
```

## Database Setup

1. Create a MySQL database named `springsecurity_db`.
2. Run the SQL scripts in [`src/main/resources/sql/scripts.sql`](src/main/resources/sql/scripts.sql) to create tables and insert sample data.

## Configuration

Edit [`src/main/resources/application.properties`](src/main/resources/application.properties) to set your database credentials and other environment-specific settings.

## Running the Application

### Using Maven Wrapper

```sh
./mvnw spring-boot:run
```

Or with Maven:

```sh
mvn spring-boot:run
```

The application will start on [http://localhost:8080](http://localhost:8080).

## API Endpoints

| Endpoint         | Method | Description                | Auth Required |
|------------------|--------|----------------------------|--------------|
| `/` or `/home`   | GET    | Home page                  | No           |
| `/login`         | GET    | Login page                 | No           |
| `/register`      | POST   | Register new user          | No           |
| `/myAccount`     | GET    | Get account details        | Yes          |
| `/myBalance`     | GET    | Get balance details        | Yes          |
| `/myLoans`       | GET    | Get loan details           | Yes          |
| `/myCards`       | GET    | Get card details           | Yes          |
| `/notices`       | GET    | Get active notices         | No           |
| `/contact`       | POST   | Submit contact message     | No           |
| `/user`          | GET    | Get user details after login| Yes         |

## Security

- **CSRF**: Enabled by default. CSRF tokens are sent via cookies and must be included in requests.
- **CORS**: Configured to allow integration with frontend apps (e.g., Angular on `localhost:4200`).
- **Profiles**: Use `default` for development and `prod` for production. Profile-specific security settings are in [`ProjectSecurityConfig`](src/main/java/com/hegazy/ssecuritypart16/config/ProjectSecurityConfig.java) and [`ProjectSecurityProdConfig`](src/main/java/com/hegazy/ssecuritypart16/config/ProjectSecurityProdConfig.java).

## Testing

Run all tests with:

```sh
./mvnw test
```

Test reports are generated in `target/surefire-reports/`.

## License

This project is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for