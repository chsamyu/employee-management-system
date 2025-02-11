# Employee management system implemented with Spring Boot 3

This project includes spring security with Basic Auth, in-memory database with H2, Spring data JPA. 
This code is implementation of the requirements as below:

## Requirements

Build a simple employee management system

Employee Entity:
Id: integer (Auto-generated)
Name: string
isActive: boolean
Salary: double

Create a restful employee management system where you can:
Add a new employee
Get all employees
Get an employee by ID
Delete an employee by ID

Technologies:
Java 17
Spring Boot
Spring Data JPA
H2 Database
Lombok
REST Client (for testing)

Bonus:
Add Logger
Add swagger
Implement unit tests
Add Spring security with basic authentication

## Prerequisites

- Java 17 or higher
- Maven 3 or higher

## Getting Started

1. Clone this repository.
   ```
   git clone https://github.com/chsamyu/employee-management-system.git
   ```

2. Navigate to the project folder.
    ```
   cd employee-management-system.git
   ```

3. Build the project.
    ```
   mvn clean install
   ```

4. Run the project.
    ```
   mvn spring-boot:run
   ```

5. H2 database can be accessed at  [http://localhost:8080/h2-console](http://localhost:8088/swagger-ui/index.html).
6. API doc can be accessed at  [http://localhost:8080/v3/api-docs](http://localhost:8088/swagger-ui/index.html).

## Features

- Spring Security for authentication and authorization.
- Swagger UI for API documentation using OpenAPI 3.

## Usage

1. Run the application using IDE or Maven
2. Run the client (EmployeeRestClient) to access all endpoints (getAllEmployees, getEmployeeById, addNewEmployee and deleteEmployeeById)
3. The postman collection is also included to run the application with Postman.

## Credentials

The Basic Auth credentials : user / password


