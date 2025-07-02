# 📚 Dorama REST API with MySQL and Docker

This repository contains a backend REST API developed in Java using Spring Boot. It implements a complete CRUD system for managing **Dorama** entities, integrated with a MySQL database and documented using **Swagger (OpenAPI)**. The project follows clean architecture principles and includes testing layers for repository, service, and controller components.

---

## 🚀 Technologies Used

- **Java 17** — Modern Java version with enhanced features and performance.
- **Spring Boot** — Simplifies application development with convention-over-configuration.
- **Spring Data JPA** — Abstracts data access with repository pattern.
- **MySQL** — Relational database to persist dorama records.
- **Docker + Docker Compose** — Containerization of the MySQL database with persistent volume to avoid data loss.
- **Lombok** — Reduces boilerplate with annotations like `@Getter`, `@Builder`, `@RequiredArgsConstructor`.
- **MapStruct** — Code generator for mapping between DTOs and domain models.
- **SpringDoc OpenAPI (Swagger UI)** — Automatically generates interactive API documentation.
- **JUnit 5 + Mockito + BDDMockito** — Testing framework for unit and integration testing.
- **Log4j2** — Logging system used to track application flow and events.

---

## 🧱 Project Structure

```
src/
├── controller/     # REST API endpoints
├── docs/           # Swagger documentation interface
├── domain/         # Entity
├── dto/            # DTO classes
├── exception/      # Custom exceptions and global handler
├── mapper/         # MapStruct mappers
├── repository/     # JPA repository
├── service/        # Business logic
├── util/           # Utility class
└── resources/
        └── application.properties
```

---

## 🗄️ Database with Docker

The application connects to a MySQL database running in a Docker container. A persistent volume is used to ensure that data is not lost when the container is stopped.

### Start the database:

```bash
docker-compose up -d
```
---

## 🔐 Database Configuration
The application.properties file includes the necessary configurations to connect to MySQL.
Use the `TemplateApplication.properties` as a template to create your **application.properties**

---

## 📦 Docker Compose File (Simplified)

```
services:
    mysql:
    image: mysql:9.0.1
    environment:
      MYSQL_DATABASE: ${ENV_MYSQL_DATABASE}
      MYSQL_ROOT_PASSWORD: ${ENV_DB_PASSWORD}
      MYSQL_USER: ${ENV_DB_USER}
    ports:
      - "3306:3306"
    volumes:
      - crud-api-db:/var/lib/mysql

volumes:
  crud-api-db:
```
---

## 📘 API Endpoints
The following REST endpoints are available:

| Method | Endpoint        | Description              |
|--------|------------------|--------------------------|
| GET    | /doramas/all     | List all doramas         |
| GET    | /doramas         | Paginated doramas        |
| GET    | /doramas/find    | Search dorama by title   |
| GET    | /doramas/{id}    | Get dorama by ID         |
| POST   | /doramas         | Create new dorama        |
| PUT    | /doramas         | Update existing dorama   |
| DELETE | /doramas/{id}    | Delete dorama by ID      |

## Swagger UI:
Access via: http://localhost:8080/swagger-ui.html

---

## 🧪 Testing

The project contains both unit and integration tests:

DoramaRepositoryTest — Tests database interactions.

DoramaServiceTest — Tests business logic with mocked repository, util and mapper.

DoramaControllerTest — Tests API endpoints with mocked service and util.

---

## ✅ Best Practices Followed

| Practice | Description |
|----------------|------|
| Clean Architecture | Separation of concerns between controller, service, repository, and domain. |
| DTO Pattern | Transfers data between layers without exposing entities. |
| Exception Handling | Global handler for structured error responses. |
| Logging | Logs timestamped operations with a custom DateUtil. |
| API Documentation | Clear, interactive docs with SpringDoc Swagger. |
| Unit & Integration Testing | Testing	Thorough testing of business logic and endpoints. |
| .env | Separates sensitive configurations from code. |

---

## 🪪 License
This project is licensed under the MIT License. See the `LICENSE` file for more details.

---

## 🙋 About the Developer
* 🎯 Backend Developer (Junior) | Open to Internship & Full-time Roles
* [![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/kauanserafim)