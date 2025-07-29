# Spring Security 6 Guide - Custom Filters Demo

This project demonstrates advanced usage of custom filters in Spring Security 6, using a MySQL database for authentication and authorization. The main focus is on the implementation and integration of custom filters in the `filter` package.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Custom Filters](#custom-filters)
- [Database Schema](#database-schema)
- [Running the Application](#running-the-application)
- [Endpoints](#endpoints)
- [Profiles](#profiles)
- [References](#references)

## Overview

This application showcases how to use custom filters in Spring Security to enhance authentication and authorization flows. It includes examples of before, after, and at filters, as well as CSRF protection and custom error handling.

## Features

- Custom request validation filter
- Logging filters for authorities
- CSRF cookie filter
- Custom authentication and access denied handlers
- Role-based and authority-based access control
- Thymeleaf templates for login and home pages

## Project Structure

```
src/
  main/
    java/
      com/hegazy/ssecuritypart19/
        config/        # Security configuration classes
        controller/    # REST and MVC controllers
        events/        # Authentication/Authorization event listeners
        exceptionhandling/ # Custom error handlers
        filter/        # Custom filters (focus of this project)
        model/         # JPA entities
        repo/          # Spring Data repositories
    resources/
      templates/       # Thymeleaf HTML templates
      sql/             # Database schema and sample data
      application.properties
      application_prod.properties
```

## Custom Filters

The core of this project is the `filter` package:

- [`RequestValidationBeforeFilter`](src/main/java/com/hegazy/ssecuritypart19/filter/RequestValidationBeforeFilter.java): Validates requests before authentication. Rejects requests if the email contains "test".
- [`AuthoritiesLoggingAfterFilter`](src/main/java/com/hegazy/ssecuritypart19/filter/AuthoritiesLoggingAfterFilter.java): Logs user authorities after authentication.
- [`AuthoritiesLoggingAtFilter`](src/main/java/com/hegazy/ssecuritypart19/filter/AuthoritiesLoggingAtFilter.java): Logs authorities at the authentication filter position.
- [`CsrfCookieFilter`](src/main/java/com/hegazy/ssecuritypart19/filter/CsrfCookieFilter.java): Sets CSRF tokens in cookies for frontend use.

Filters are integrated in [`ProjectSecurityConfig`](src/main/java/com/hegazy/ssecuritypart19/config/ProjectSecurityConfig.java) and [`ProjectSecurityProdConfig`](src/main/java/com/hegazy/ssecuritypart19/config/ProjectSecurityProdConfig.java).

## Database Schema

The database schema is defined in [`src/main/resources/sql/scripts.sql`](src/main/resources/sql/scripts.sql). It includes tables for users, authorities, customers, accounts, transactions, loans, cards, notices, and contact messages.

## Running the Application

1. **Setup MySQL Database**  
   - Create a database named `springsecurity_db`.
   - Run the SQL script: [`src/main/resources/sql/scripts.sql`](src/main/resources/sql/scripts.sql).

2. **Configure Properties**  
   - Edit [`src/main/resources/application.properties`](src/main/resources/application.properties) and [`src/main/resources/application_prod.properties`](src/main/resources/application_prod.properties) for your DB credentials.

3. **Build and Run**  
   - Build:  
     ```sh
     ./mvnw clean install
     ```
   - Run:  
     ```sh
     ./mvnw spring-boot:run
     ```

## Endpoints

| Endpoint         | Method | Description                | Access         |
|------------------|--------|----------------------------|----------------|
| `/login`         | GET    | Login page                 | Public         |
| `/register`      | POST   | Register new user          | Public         |
| `/contact`       | POST   | Submit contact message     | Public         |
| `/notices`       | GET    | Get active notices         | Public         |
| `/myAccount`     | GET    | Get account details        | Authenticated  |
| `/myBalance`     | GET    | Get balance details        | Authenticated  |
| `/myLoans`       | GET    | Get loan details           | Authenticated  |
| `/myCards`       | GET    | Get card details           | Authenticated  |
| `/user`          | GET    | Get user details           | Authenticated  |

## Profiles

- **default**: Uses