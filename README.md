# OnlineBookStore
[Project Description]
## Overview
This project aims to offer customers the ability to browse through available books, request books for borrowing, and 
manage the borrowing and return dates. Additionally, it enables administrators to maintain the inventory by adding or 
deleting books as needed.
## Project Structure
The project is structured into different packages to maintain a modular and organized codebase:
- com.example.project.entity: Contains entity classes representing database tables.
- com.example.project.controller: Includes controllers handling HTTP requests and defining API endpoints.
- com.example.project.service: Contains service classes that handle business logic and interact with repositories.
- com.example.project.dto: Contains Data Transfer Objects used to transfer data between controllers and services.
- com.example.project.exception: Holds exception classes for handling specific scenarios.
- com.example.project.repository: Includes repositories for interacting with the database.
- com.example.project.mapper: Contains mapper classes.
- com.example.project.enum: Contains enum classes.
- com.example.project.config: Contains configration classes.
## Entities
1. User: Represents user information including authentication details
2. Book: Represents book details.
3. Booking: Represents the details of the booking made by the user.
## Controllers
1. AdminController: Manages CRUD operations of the admin.
2. CustomerController: Manages CRUD operations of the customer.
3. AuthenticationController: Manages user registration and login.
## Services
1. JwtAuthenticationService: Handles user registration and login logic.
2. AdminService: Handels admin operations like adding new book , update book details , get stock level , and check availability of the book.
3. CustomerService : Handels customer operations like browsing books by categories , view book details , and request to borrow a book.
## DTOs
1. RegisterUserRequestDto: Data Transfer Object for user registration.
2. LoginUserRequestDto: Data Transfer Object for user login.
3. BookRequestDTO: Data Transfer Object for creating or updating books.
## API Endpoints
- /api/auth/register: POST endpoint for user registration.
- /api/auth/login: POST endpoint for user login.
- /newBook: POST endpoint for adding new book.
- /{id}: PUT endpoint for updating book details for specific book.
- /stockLevel: GET endpoint for calculate the stock level.
- /availability/{name}: GET endpoint for checking the availability of specific book.
- /books/{category}: GET endpoint enables the user to browse books by category.
- /bookDetails/{name}: GET endpoint provides the user with book details by the book name.
- /{userName}/{bookName}/{borrowingDate}/{returnDate}: GET endpoint enables the user for borrowing specific book.
## How to Run
1. Clone the repository.
2. Configure the database connection in the application.properties file.
3. Run the application using your preferred IDE or build tools.
4. Access the API through the specified endpoints.
## Dependencies
If you are using Maven, add the following dependency.  


```xml
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-api</artifactId>
			<version>0.11.5</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-impl</artifactId>
			<version>0.11.5</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-jackson</artifactId>
			<version>0.11.5</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.12</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.junit.platform</groupId>
			<artifactId>junit-platform-launcher</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
			<version>2.0.4</version>
		</dependency>
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>
	</dependencies>
```
## Architecture of Currency Conversion API

## <mark style="background: #FFB86CA6;">1- Adding new book</mark>

### Request Example :
```
POST : http://localhost:8080/newBook
BODY : {
  "name": "Then She Was Gone",
  "author": "Lisa Jewell",
  "price": 80.0,
  "category": "Drama",
  "description": "Ellie Mack was the perfect daughter. She was fifteen, the youngest of three. She was beloved by her parents, friends, and teachers. She and her boyfriend made a teenaged golden couple."
}
```

### Response Example :
```
{
    "id": 4,
    "name": "Then She Was Gone",
    "author": "Lisa Jewell",
    "price": 80.0,
    "category": "Drama",
    "description": "Ellie Mack was the perfect daughter. She was fifteen, the youngest of three. She was beloved by her parents, friends, and teachers. She and her boyfriend made a teenaged golden couple.",
    "quantity": 1
}
```

****


