# Spring Security OAuth2 Example (Part 23)

This project demonstrates the use of **Spring Security 6** with **OAuth2** for authentication and authorization. The configuration and notes are primarily managed via the `application.properties` file.

---

## Table of Contents

- [Overview](#overview)
- [Key OAuth2 Concepts](#key-oauth2-concepts)
- [Notes](#notes)
- [How to Run](#how-to-run)
- [Endpoints](#endpoints)
- [References](#references)

---

## Overview

This project is part of a series exploring Spring Security 6 and OAuth2. It shows how to configure an OAuth2 Resource Server and/or Client, manage tokens, and secure endpoints using properties-based configuration.

---

## Key OAuth2 Concepts

- **Resource Server**: Hosts protected resources (APIs).
- **Authorization Server**: Issues tokens after authenticating users.
- **OAuth2 Client**: Requests access to protected resources using tokens.
- **JWT**: JSON Web Token, a compact way to represent claims securely.

---

## Notes

- `issuer-uri` and `jwk-set-uri` are used for validating JWT tokens.
- `client-id` and `client-secret` identify your app to the OAuth2 provider.
- `authorization-grant-type` is typically `authorization_code` for web apps.
- `redirect-uri` is where the OAuth2 provider will send the user after authentication.

---

## How to Run

1. **Clone the repository:**
   ```sh
   git clone <repo-url>
   cd ssecurityOAuth2_23
   ```

2. **Configure `application.properties`:**
   - Update the OAuth2 endpoints, client ID, and secret as per your provider.

3. **Build and run the application:**
   ```sh
   ./mvnw spring-boot:run
   ```

4. **Access the application:**
   - Open [http://localhost:8080](http://localhost:8080) in your browser.

---

## Endpoints

- `/login`: Initiates OAuth2 login flow.
- `/oauth2/authorization/{registrationId}`: Custom login page for OAuth2.
- `/api/**`: Example of a protected resource endpoint.

---

## References

- [Spring Security OAuth2 Docs](https://docs.spring.io/spring-security/reference/servlet/oauth2/index.html)
- [OAuth2 Specification](https://oauth.net/2/)
- [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#application-properties.security)

---

**Feel free to check the `application.properties` file for more detailed notes and