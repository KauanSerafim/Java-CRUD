# 📚 Java CRUD Project with JDBC and MySQL

This repository contains a pure Java application that implements a CRUD (Create, Read, Update, Delete) system using JDBC to integrate with a MySQL database. The project aims to demonstrate best practices in code organization, security, maintainability, and efficient use of Java resources.

---

## 🚀 Technologies Used

- **Java (Plain)** — Main language, without heavy frameworks, ideal for learning and understanding the basics of database interactions.
- **JDBC (Java Database Connectivity)** — Oracle's official API for connecting Java with relational databases.
- **MySQL** — Relational database used in the application.
- **Docker + Docker Compose** — Containerization of the database, with a secondary container for *backup*, ensuring data safety.
- **Maven** — Dependency management and build automation tool.
- **Log4j2** — Logging library, useful for debugging and error tracking.
- **Lombok** — Simplifies code with annotations like `@Builder`, `@Getter`, `@Value`, reducing boilerplate.
- **Java Dotenv** — Loads environment variables from a `.env` file to protect sensitive information like database credentials.
- **MySQL Workbench** — GUI tool used locally to model and manage the database.

---

## 🧱 Project Structure

```
src/
├── connection/     # Class responsible for JDBC connection
├── domain/         # Dorama entity
├── repository/     # CRUD operations using PreparedStatement
├── service/        # Simulates user interface through console
```

---

## 🗄️ Database with Docker

The database runs in a Docker container to simplify the development environment. A second container is used as an **automatic backup**, ensuring data loss prevention. Everything is orchestrated through `docker-compose.yml`.

### Start the database:
```bash
docker-compose up -d
```

---

## 🔐 Security with `.env`

The database connection is configured using environment variables stored in a `.env` file, including:

```env
DB_URL=jdbc:mysql://localhost:3306/your_database
DB_USER=your_user
DB_PASSWORD=your_password
```

This file is **not versioned on Git** (ignored via `.gitignore`) to protect sensitive data.

---

## 🧠 Organization and Best Practices

- **`Dorama` (Domain)**: Immutable entity with the attributes:
    - `Integer id`
    - `String title`
    - `int releaseYear`
    - `double score`
    - Uses `@Value` and `@Builder` (Lombok) for safety and clarity.

- **`Repository`**: Contains the database access logic using `PreparedStatement` to prevent **SQL Injection** and ensure performance.

- **`Service`**: Contains the `menu()` method to simulate user interaction through the console. The user selects operations using numbers and can exit by typing `0`.

- **CRUD Operations**:
    - **Create**: Inserts a new Dorama into the database.
    - **Read**: Retrieves all Doramas or searches for a Dorama by its title.
    - **Update**: Updates **only the title** of a Dorama (found by ID).
    - **Delete**: Deletes a Dorama only if found by ID.

---

## ✅ Why These Choices?

| Component/Tool | Why? |
|----------------|------|
| JDBC + PreparedStatement | Prevents SQL Injection and improves performance. |
| Docker + Backup | Provides a reproducible and secure environment. |
| .env + Java Dotenv | Separates sensitive configurations from code. |
| Lombok | Reduces repetitive code and improves readability. |
| Maven | Simplifies dependency and build management. |
| Modular Structure (Connection, Repository, Domain, Service) | Enhances maintainability, testing, and scalability. |
| Log4j2 | Enables error tracking and logging of important events. |

---

## 🪪 License

This project is licensed under the MIT License. See the `LICENSE` file for more details.