# 🛡️ Spring Boot OAuth2 with Keycloak - Project Documentation

## 📌 Overview

This application demonstrates secure authentication and authorization using **Spring Boot**, **Spring Security**, and **Keycloak** (as an OAuth2 Authorization Server). It replaces manual security configuration with centralized identity management via Keycloak.

---

## 🔧 Application Configuration

### 🔑 Security Setup
- Integrated with **Keycloak** for:
  - OAuth2 & OpenID Connect
  - SSO (Single Sign-On)
  - Role-based access control (RBAC)
  - Token-based security
- Uses **Keycloak client credentials** for authentication.
- Spring Security profile-specific config (`default` and `prod`).

### 🗂️ Profiles
- `default`: For local development with detailed logging.
- `prod`: For production-ready deployments (imported via `application_prod.properties`).

### 🧠 Session and CSRF
- Stateless session management (JWT-based).
- CSRF protection with token in cookie (except for certain public endpoints).
- CORS support configured for frontend origin (`localhost:4200`).

---

## 🗃️ Database Configuration

- **DB Engine**: MySQL 8
- **Database**: `springsecurity_db` on port `3307`
- **User**: root / root (local setup)
- **JPA**: Hibernate dialect, auto DDL update

---

## 🗂️ Project Structure

src/main/java
├── config/ → Security config
├── controller/ → REST Controllers (e.g. Account, Balance, etc.)
├── dto/ → Request/response DTOs
├── events/ → App-level event handling
├── exceptionhandling/ → Global exception handlers
├── filter/ → custom filters
├── model/ → Entity models
└── repo/ → Spring Data JPA repositories

src/main/resources
├── application.properties
├── application_prod.properties
└── templates/
├── home.html
└── login.html

---

## 🔐 Keycloak Integration Steps

### Step 1: Keycloak Setup via Docker

1. open "https://www.keycloak.org/" ->> Get Started ->> Docker
2. run "docker run -p 127.0.0.1:8180:8080 -e KC_BOOTSTRAP_ADMIN_USERNAME=admin -e KC_BOOTSTRAP_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:26.3.2 start-dev"
3. Run Keycloak container:
   - Exposes admin console at `http://localhost:8180`
   - Admin credentials: `admin / admin`
4. Create realm: `hegazyrealm1`
5. Create client: `hegazyclient1`
   - Enable "Client Authentication"
   - Use "Service accounts roles"
6. Get client secret from Credentials tab

---

### Step 2: Spring Boot Configuration

1. Add `spring-security-oauth2-resource-server` dependency
2. Delete the following:
   - All custom filters, providers, encoders
   - Security-related beans and config classes
   - Custom login and registration endpoints
   - `ApplicationConstants` values used for old login logic
3. Configure:
   - `application.properties` with client ID, secret, issuer URI
   - Resource server and JWT decoder
4. Update roles and access control to map with Keycloak roles

---

## ✅ Keycloak vs Other IAM Tools

| Tool          | Self-hosted | Protocols         | Highlights                                  |
|---------------|-------------|-------------------|---------------------------------------------|
| **Keycloak**  | ✅ Yes      | OAuth2, OIDC, SAML| Full control, open-source                   |
| **Auth0**     | ❌ No       | OAuth2, OIDC, SAML| Cloud-first, developer-friendly UI          |
| **Okta**      | ❌ No       | OAuth2, OIDC, SAML| Enterprise-grade, commercial                 |
| **Cognito**   | ❌ No       | OAuth2, OIDC, SAML| AWS-native, customizable but complex        |
| **FusionAuth**| ✅ Yes      | OAuth2, OIDC, SAML| Lightweight and dev-friendly                |
| **ORY Hydra** | ✅ Yes      | OAuth2, OIDC      | Modular and minimalist                      |

---

## 🌐 Local Environment

| Component     | URL                          |
|---------------|------------------------------|
| Application   | `http://localhost:8080`      |
| Keycloak UI   | `http://localhost:8180`      |
| Frontend (Angular)| `http://localhost:4200` |
| Database      | `localhost:3307` → `springsecurity_db` |

---

## 📁 Templates

- `home.html`: Default landing page
- `login.html`: Login UI (to be replaced if using Keycloak login)

---

## 📝 Notes

- HTTPS is disabled in local dev but should be enforced in prod.
- Public routes are whitelisted (e.g., `/`, `/home`, `/contact`, `/login/**`, etc.).
- Session management is stateless due to token-based auth.
- CORS enabled to allow frontend to communicate with backend.
- CSRF token set in cookies for secure form handling.

---

## 📚 Resources

- [Keycloak Official Docs](https://www.keycloak.org/documentation.html)
- [Spring Security OAuth2 Resource Server Guide](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/index.html)
- [Thymeleaf Documentation](https://www.thymeleaf.org/)

---

