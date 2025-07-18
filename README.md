# Spring Security 6 Guide

A comprehensive guide and personal documentation for mastering Spring Security 6, including hands-on notes, key concepts, and implementations of JWT and OAuth2. This repository is organized into multiple demo projects, each focusing on different aspects of Spring Security.

---

## Table of Contents
- [Overview](#overview)
- [Repository Structure](#repository-structure)
- [Getting Started](#getting-started)
- [Project Summaries](#project-summaries)
- [Prerequisites](#prerequisites)
- [License](#license)

---

## Overview
This repository is a learning resource for Spring Security 6, featuring step-by-step demo projects. Each part demonstrates a specific security concept or configuration in Spring Boot 3, with practical code and detailed documentation.

---

## Repository Structure

```
ssecurity_part1/
  ├── ssecuritypart1/   # Part 1: Basic Security & Welcome App
  ├── ssecuritypart2/   # Part 2: Endpoint Protection & Custom Config
  ├── ssecuritypart3/   # Part 3: In-Memory Users & Role-Based Auth
  └── ssecuritypart4/   # Part 4: Advanced Config & Password Safety
```

Each subdirectory contains a standalone Spring Boot project with its own README and code.

---

## Getting Started

1. **Clone the repository:**
   ```bash
   git clone <repo-url>
   cd spring-security-6-guide/ssecurity_part1/<project-directory>
   ```
2. **Build and run any part:**
   ```bash
   ./mvnw spring-boot:run
   ```
   or
   ```bash
   mvn spring-boot:run
   ```
3. **Explore endpoints and security features as described in each part's README.**

---

## Project Summaries

### 1. ssecuritypart1: Welcome App
- Basic Spring Security integration in a Spring Boot app.
- Single secured endpoint (`/welcome`).
- Customizable credentials via environment variables or `application.properties`.
- [See detailed README](./ssecurity_part1/ssecuritypart1/README.md)

### 2. ssecuritypart2: Endpoint Protection & Custom Config
- Demonstrates endpoint protection, form login, and HTTP Basic authentication.
- Custom access rules for public and secured endpoints.
- Environment-based configuration for credentials.
- [See detailed README](./ssecurity_part1/ssecuritypart2/README.md)

### 3. ssecuritypart3: In-Memory Users & Role-Based Auth
- In-memory user management with roles (user, admin).
- Multiple REST endpoints with varying access levels.
- Demonstrates both authentication and authorization.
- [See detailed README](./ssecurity_part1/ssecuritypart3/README.md)

### 4. ssecuritypart4: Advanced Config & Password Safety
- Advanced security configuration with BCrypt password encoding.
- Password strength checking using HaveIBeenPwned API.
- In-memory users, form login, and HTTP Basic authentication.
- [See detailed README](./ssecurity_part1/ssecuritypart4/README.md)

---

## Prerequisites
- Java 21+
- Maven 3.8+

---

## License
This repository and all demo projects are for educational and demonstration purposes only.
