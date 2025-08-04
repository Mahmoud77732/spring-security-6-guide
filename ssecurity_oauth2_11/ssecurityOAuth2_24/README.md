# ssecurityOAuth2_24

A Spring Boot application demonstrating OAuth2 social logins using GitHub and Facebook.

## Features

- OAuth2 login with GitHub and Facebook
- Spring Security integration
- Secure endpoint at `/secure`
- User credentials for form login (for testing)

## Configuration

All OAuth2 client configuration is managed via `src/main/resources/application.properties`.  
You can override sensitive values using environment variables.

### Example `application.properties`

```properties
spring.application.name=ssecurity_OAuth2_23

# Default user credentials (for form login)
spring.security.user.name=${SECURITY_USERNAME:hegazy}
spring.security.user.password=${SECURITY_PASSWORD:12345}

# Logging
logging.pattern.console = ${LOGPATTERN_CONSOLE:%green(%d{HH:mm:ss.SSS}) %blue(%-5level) %red([%thread]) %yellow(%logger{15}) - %msg%n}
logging.level.org.springframework.security=${SPRING_SECURITY_LOG_LEVEL:TRACE}

# OAuth2 GitHub
spring.security.oauth2.client.registration.github.client-id=${GITHUB_CLIENT_ID:Ov23liOUPZnJim5TDE8w}
spring.security.oauth2.client.registration.github.client-secret=${GITHUB_CLIENT_SECRET:83c1d907516bc3096dfb5d85967cff3eddda16ee}

# OAuth2 Facebook
spring.security.oauth2.client.registration.facebook.client-id=${FACEBOOK_CLIENT_ID:690404263961116}
spring.security.oauth2.client.registration.facebook.client-secret=${FACEBOOK_CLIENT_SECRET:2ad005ba2617f03be1e5253c012c7102}
```

### Environment Variables

You can override any property using environment variables, for example:

- `GITHUB_CLIENT_ID`
- `GITHUB_CLIENT_SECRET`
- `FACEBOOK_CLIENT_ID`
- `FACEBOOK_CLIENT_SECRET`
- `SECURITY_USERNAME`
- `SECURITY_PASSWORD`

## Running the App

```sh
./mvnw spring-boot:run
```

Then visit [http://localhost:8080/secure](http://localhost:8080/secure)  
You will be redirected to login via GitHub, Facebook, or the default form login.

## Notes

- To use your own OAuth2 credentials, register your app with GitHub and Facebook and update the properties or environment variables.
- The `/secure` endpoint is protected and requires authentication.

## License