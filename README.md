# 📚 Online Library System

An online library system built with Spring Boot and PostgreSQL. This application allows users to register, authenticate using JWT, explore books, borrow and return them, and track borrowing history.

---

## 🚀 Features

- 🧑 User Registration and Login (JWT Authentication)
- 🔐 Secure REST APIs with Spring Security
- 📚 Book Collection Browsing and Searching (by author and year)
- 📥 Borrow and 📤 Return Books (copies managed automatically)
- 🕓 Track Past Borrowed Books
- 🐘 PostgreSQL Database Integration (AWS-hosted)
- 📄 API Documentation with Swagger/OpenAPI

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Security + JWT**
- **Spring Data JPA**
- **PostgreSQL (AWS RDS)**
- **Swagger 3 / OpenAPI**
- **Lombok**

---

## ⚙️ Environment Variables

Set the following environment variables to be injected into `application.properties`:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
jwt.secret=${JWT_SECRET}
```

## 🔒 Security
* Registration, login and browse books endpoints are public.
* Other endpoints are protected with JWT.
* JWT must be attached to `Authorization` header as:

```
Authorization: Bearer <token>
```

## ⚡ Performance Improvements

To ensure the system remains fast, scalable, and responsive even as the dataset grows, the following optimizations have been implemented:

- **Database Indexing**  
  Key fields such as `author`, `published_year` in the `Book` entity and `user_id` in the `borrow_records` are indexed at the database level using PostgreSQL indexes.  
  - This significantly improves search and filtering performance, especially on large datasets.

- **Pagination for Book Collections**  
  All APIs returning lists of books implement pagination (`page`, `size` parameters) using Spring Data `PageRequest`.  
  - This reduces server load, minimizes memory usage, and ensures fast API responses by retrieving only a small subset of data per request.
 

## 📘 Swagger API Docs
Once the app is running, access Swagger UI at:

```
http://localhost:8080/swagger-ui/index.html
```

## 🧪 Endpoints Summary
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/users/register` | Register a new user |
| POST | `/api/auth/login` | Authenticate user (returns JWT) |
| GET | `/api/books` | List all available books |
| GET | `/api/books/search` | Search books by author/year |
| POST | `/api/borrow` | Borrow a book |
| POST | `/api/return` | Return a borrowed book |
| GET | `/api/history` | View borrowing history |

