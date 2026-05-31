# HR Employee API

A RESTful API for managing employee records, built with Spring Boot and secured with JWT authentication.

## Technologies

- Java 17
- Spring Boot 4.0.5
- Spring Security + JWT (JJWT)
- Spring Data JPA
- SQL Server (SQLEXPRESS)
- Maven
- BCrypt password hashing

## Prerequisites

- SQL Server with a named instance (`SQLEXPRESS`)
- SQL Server Browser service enabled
- TCP/IP enabled in SQL Server Configuration Manager
- Maven installed

## Setup & Run

1. Clone the repository:
   ```
   git clone https://github.com/alexandrosmagn/employee-api.git
   cd employee-api
   ```

2. Create a database in SQL Server called `employeedb`

3. Update `src/main/resources/application.properties` with your credentials:
   ```
   spring.datasource.url=jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=employeedb;encrypt=false
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

4. Run the application:
   ```
   mvn spring-boot:run
   ```

5. API: `http://localhost:8080`

## Authentication

This API uses JWT authentication. All `/employees` endpoints are protected.

**Step 1 — Register a user:**
```
POST /auth/register
Content-Type: application/json

{
    "username": "alex",
    "password": "yourpassword",
    "role": "ADMIN"
}
```

**Step 2 — Login to get a token:**
```
POST /auth/login
Content-Type: application/json

{
    "username": "alex",
    "password": "yourpassword"
}
```
Returns a JWT token string.

**Step 3 — Use the token:**

Add the token to the `Authorization` header of every request:
```
Authorization: Bearer <your_token>
```

## API Endpoints

### Authentication (public)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /auth/register | Register a new user |
| POST | /auth/login | Login and receive JWT token |

### Employees (requires JWT)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /employees | Get all employees |
| GET | /employees/{id} | Get employee by ID |
| POST | /employees | Add a new employee |
| PUT | /employees/{id} | Update an employee |
| DELETE | /employees/{id} | Delete an employee |
| GET | /employees/department/{department} | Get employees by department |
| GET | /employees/score/greater/{score} | Get employees with score above threshold |
| GET | /employees/score/less/{score} | Get employees with score below threshold |

## Employee Object

```json
{
    "name": "Alex",
    "surname": "Magn",
    "role": "Developer",
    "department": "Engineering",
    "score": 80.0
}
```

## Validation Rules

- `name`, `surname`, `role`, `department` — cannot be blank
- `score` — must be zero or higher

## Error Handling

The API returns meaningful error responses:

- `404 Not Found` — employee does not exist
- `400 Bad Request` — validation failed, returns field-level error messages
- `401 Unauthorized` — missing or invalid JWT token
- `403 Forbidden` — authenticated but not allowed

## Project Structure

```
src/main/java/alex/pro/employeeapi/
├── Employee.java              # Employee entity
├── EmployeeRepository.java    # Database access layer
├── EmployeeService.java       # Business logic layer
├── EmployeeController.java    # REST endpoints
├── EmployeeNotFoundException.java
├── GlobalExceptionHandler.java
├── User.java                  # User entity for authentication
├── UserRepository.java
├── UserService.java
├── AuthController.java        # Register & login endpoints
├── JwtUtil.java               # Token generation & validation
├── JwtFilter.java             # JWT request filter
├── SecurityConfig.java        # Security configuration
└── PasswordConfig.java        # BCrypt password encoder
```
