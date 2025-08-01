## Security Annotations Used

- `@PreAuthorize`: Restricts method access before execution.
- `@PostAuthorize`: Checks conditions after method execution.
- `@Secured`: Allows access based on roles.
- `@RolesAllowed`: JSR-250 standard for role-based access.
- `@PreFilter`: Filters input collections before method execution.
- `@PostFilter`: Filters output collections after method execution.

## How to Run

1. **Database Setup**:

   - Ensure MySQL is running on `localhost:3307`.
   - Create the database and tables using [`src/main/resources/sql/scripts.sql`](src/main/resources/sql/scripts.sql).

2. **Configure Properties**:

   - Edit [`src/main/resources/application.properties`](src/main/resources/application.properties) and [`src/main/resources/application_prod.properties`](src/main/resources/application_prod.properties) as needed.

3. **Build and Run**:

   - Use Maven wrapper:
     ```sh
     ./mvnw clean install
     ./mvnw spring-boot:run
     ```
   - Or run the main class [Ssecuritypart21Application](http://_vscodecontentref_/3) from your IDE.

4. **Access the Application**:
   - Web UI: [http://localhost:8080](http://localhost:8080)
   - API endpoints: See below.

## API Endpoints

| Endpoint     | Method | Description                  | Security                  |
| ------------ | ------ | ---------------------------- | ------------------------- |
| `/login`     | GET    | Login page (Thymeleaf)       | Public                    |
| `/register`  | POST   | Register new user            | Public                    |
| `/apiLogin`  | POST   | API login, returns JWT       | Public                    |
| `/user`      | GET    | Get user details after login | Authenticated             |
| `/myAccount` | GET    | Get account details          | `ROLE_USER`               |
| `/myBalance` | GET    | Get account transactions     | `ROLE_USER`, `ROLE_ADMIN` |
| `/myLoans`   | GET    | Get loan details             | Authenticated             |
| `/myCards`   | GET    | Get card details             | `ROLE_USER`               |
| `/notices`   | GET    | Get active notices           | Public                    |
| `/contact`   | POST   | Submit contact inquiry       | Method-level filtering    |

## Method-Level Security Example

- [ContactController.saveContactInquiryDetails](http://_vscodecontentref_/4):  
  Uses `@PostFilter("filterObject.contactName != 'Test'")` to exclude contacts named "Test" from the response.

- [LoanRepository.findByCustomerIdOrderByStartDtDesc](http://_vscodecontentref_/5):  
  Uses Role`@PreAuthorize("has('USER')")` to restrict access to users with the `USER` role.

## Custom Filters

- [RequestValidationBeforeFilter](http://_vscodecontentref_/6): Validates requests before authentication.
- [JWTTokenGeneratorFilter](http://_vscodecontentref_/7): Generates JWT tokens for authenticated users.
- [JWTTokenValidatorFilter](http://_vscodecontentref_/8): Validates JWT tokens on incoming requests.
- [CsrfCookieFilter](http://_vscodecontentref_/9): Manages CSRF tokens in cookies.

## Profiles

- **Default**: Stateless JWT authentication, custom provider, HTTP allowed.
- **Prod**: Stateful sessions, stricter password checks, HTTPS enforced.

## Testing

- request: POST http://localhost:8080/contact
```
[
  {
    "contactName":"Mahmoud Hegazy",
    "contactEmail":"hegazy@gmail.com",
    "subject":"need a new saving account",
    "message":"I want to open a new saving account in HegazyApp"
    }
]
```

## License

This project is for educational purposes and does not include a specific license.

---

For more details, see the source code and
