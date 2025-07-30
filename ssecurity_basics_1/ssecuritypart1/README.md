# 🛡️ Spring Security Part 1: Welcome App

This project is a simple Spring Boot app demonstrating basic Spring Security integration. It contains a single secured endpoint and shows how to customize default credentials using environment variables or `application.properties`.

---

## 📂 Project Structure

```
src/
  └── main/
      ├── java/
      │   └── com/
      │       └── hegazy/
      │           └── ssecuritypart1/
      │               ├── Ssecuritypart1Application.java
      │               └── controller/
      │                   └── WelcomeController.java
      └── resources/
          └── application.properties
```

---

## 🚀 Endpoint

- **GET** `/welcome`
  - Returns a welcome message: `Hello from Welcome Controller!`
  - **Requires authentication** (see credentials below)

---

## 🔐 Security & Credentials

Spring Security is enabled by including `spring-boot-starter-security`.

- **All endpoints are secured by default.**
- Default credentials are set in `application.properties`:
  - **Username:** `admin`
  - **Password:** `admin123`
- You can override these using environment variables:
  - `SECURITY_USERNAME` (default: `admin`)
  - `SECURITY_PASSWORD` (default: `admin123`)

---

## ⚙️ Configuration Example

`src/main/resources/application.properties`:

```properties
spring.application.name=${SPRING_APP_NAME:securitypart1}
spring.security.user.name=${SECURITY_USERNAME:admin}
spring.security.user.password=${SECURITY_PASSWORD:admin123}
logging.level.org.springframework.security=${SPRING_SECURITY_LOG_LEVEL:TRACE}
```

---

## 🧪 Testing the Endpoint

You can test the secured endpoint using `curl` or Postman:

```bash
curl -u admin:admin123 http://localhost:8080/welcome
```

Or, if you set custom credentials via environment variables:

```bash
export SECURITY_USERNAME=myuser
export SECURITY_PASSWORD=mypassword
curl -u myuser:mypassword http://localhost:8080/welcome
```

---

## 🛠️ Build & Run

To build and run the application:

```bash
./mvnw spring-boot:run
```

The app will start on [http://localhost:8080](http://localhost:8080).

---

## 📄 License

This project is for educational/demo purposes.



