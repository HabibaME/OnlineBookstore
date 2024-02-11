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
## Tools & Technologies💡

1. Programming Language: Java 17.

2. Backend Framework: Spring Boot v3.2.2.
 
3. Logger: SLF4J.
   
4. Actuator.
   
5. Junit testing.
 
6. Authentication & Authorization.
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
- /actuator/health: GET endpoint calculating the total memory and the free memory.
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
## <mark style="background: #FFB86CA6;">2- Updating an existing book</mark>

### Request Example :
```
PUT : http://localhost:8080/4
BODY :{
  "name": "Then She Was Gone",
  "author": "Lisa Jewell",
  "price": 90.0,
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
    "price": 90.0,
    "category": "Drama",
    "description": "Ellie Mack was the perfect daughter. She was fifteen, the youngest of three. She was beloved by her parents, friends, and teachers. She and her boyfriend made a 
    teenaged golden couple.",
    "quantity": 1
}
```

****
## <mark style="background: #FFB86CA6;">3- Getting the stock level</mark>

### Request Example :
```
GET : http://localhost:8080/stockLevel
```

### Response Example :
```
3
```

****
## <mark style="background: #FFB86CA6;">4- Check the availability of an existing book</mark>

### Request Example :
```
GET : http://localhost:8080/availability/Then She Was Gone
```

### Response Example :
```
This book is available
```

****
## <mark style="background: #FFB86CA6;">5- Browsing books by category</mark>

### Request Example :
```
GET : http://localhost:8080/books/drama
```

### Response Example :
```
[{
    "id": 4,
    "name": "Then She Was Gone",
    "author": "Lisa Jewell",
    "price": 90.0,
    "category": "Drama",
    "description": "Ellie Mack was the perfect daughter. She was fifteen, the youngest of three. She was beloved by her parents, friends, and teachers. She and her boyfriend made a 
    teenaged golden couple.",
    "quantity": 1
}]
```

****
## <mark style="background: #FFB86CA6;">6- Getting the book details of an existing book</mark>

### Request Example :
```
GET : http://localhost:8080/bookDetails/Then She Was Gone
```

### Response Example :
```
{
    "id": 4,
    "name": "Then She Was Gone",
    "author": "Lisa Jewell",
    "price": 90.0,
    "category": "Drama",
    "description": "Ellie Mack was the perfect daughter. She was fifteen, the youngest of three. She was beloved by her parents, friends, and teachers. She and her boyfriend made a 
    teenaged golden couple.",
    "quantity": 1
}
```

****
## <mark style="background: #FFB86CA6;">7-Borrowing book</mark>

### Request Example :
```
GET : http://localhost:8080/Mariam Mohamed/Then She Was Gone/1-2-2023/10-2-2023
```

### Response Example :
```
The book is borrowed to you , please return it before 10-2-2023
```

****
## <mark style="background: #FFB86CA6;">8- Calculating the free memory and the total memory</mark>

### Request Example :
```
GET : http://localhost:8080/actuator/health
```

### Response Example :
```{
    "status": "UP",
    "components": {
        "db": {
            "status": "UP",
            "details": {
                "database": "PostgreSQL",
                "validationQuery": "isValid()"
            }
        },
        "diskSpace": {
            "status": "UP",
            "details": {
                "total": 119356256256,
                "free": 3790245888,
                "threshold": 10485760,
                "path": "C:\\Users\\ww\\Downloads\\OnlineBookstore\\OnlineBookstore\\.",
                "exists": true
            }
        },
        "memory": {
            "status": "UP",
            "details": {
                "free_memory ": "82327968 bytes",
                "total_memory ": "180355072 bytes",
                "free_memory_percent ": "45.64771430436955 %"
            }
        },
        "ping": {
            "status": "UP"
        }
    }
}
```

****


