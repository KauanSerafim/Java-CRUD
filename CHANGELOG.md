# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/) and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [2.0.0] - 2025-07-01

### Added

### Added

* Initial release of the Dorama REST API.
* Core CRUD functionality for Dorama entities.
* Integration with MySQL database using Spring Data JPA.
* Docker Compose setup for easy database spin-up with persistent volume.
* API documentation generated with SpringDoc OpenAPI (Swagger UI).
* Comprehensive testing suite for Repository, Service, and Controller layers (JUnit 5, Mockito, BDDMockito).
* Clean Architecture principles applied for clear separation of concerns.
* DTO pattern implementation using MapStruct for data transfer.
* Global exception handling for consistent error responses.
* Logging implemented with Log4j2.
* Project structure defined and documented.
* Best practices followed and listed in `README.md`.
* License file (`LICENSE`) included.

- Implemented full REST CRUD API for the `Dorama` resource:
    - `GET /doramas/all` – Retrieve all doramas.
    - `GET /doramas/{id}` – Retrieve a dorama by its ID.
    - `GET /doramas/find?title=` – Search doramas by title.
    - `POST /doramas` – Create a new dorama.
    - `PUT /doramas` – Update an existing dorama.
    - `DELETE /doramas/{id}` – Delete a dorama by ID.

- Integrated MySQL database connection via `application.properties`.

- Configured Docker Compose with a MySQL container and a **named volume** to persist data:
    - Ensures data durability across container restarts.

- Created `Dorama` entity in the `domain` package, mapped to a MySQL table using JPA.

- Added business logic in `service/DoramaService.java`.

- Created REST controller in `controller/DoramaController.java` to expose endpoints.

- Added DTOs:
    - `DoramaPostDto` – Request model for creation.
    - `DoramaPutDto` – Request model for updates.

- Implemented `DoramaMapper` using MapStruct to map between DTOs and domain models.

- Introduced utility class `DateUtil` to format `LocalDateTime` to `"yyyy-MM-dd HH:mm:ss"` used in logs.

- Implemented centralized exception handling:
    - `RestExceptionGlobalHandler` for custom exception mapping.
    - Custom exceptions: `BadRequestException`, `NotFoundException`, `InternalServerErrorException`.
    - Detailed response models: `BadRequestExceptionDetails`, `NotFoundExceptionDetails`, `InternalServerErrorExceptionDetails`.

- Added API documentation using SpringDoc OpenAPI (Swagger UI):
    - Defined all OpenAPI metadata in `docs/DoramaControllerDocs.java` to keep controller annotations clean.

- Wrote unit and integration tests:
    - `DoramaRepositoryTest` – Unit tests for repository behavior.
    - `DoramaServiceTest` – Tests for business logic with `@Mock` and `BDDMockito`.
    - `DoramaControllerTest` – Endpoint tests with mocked service, util and `BDDMockito`.

---
