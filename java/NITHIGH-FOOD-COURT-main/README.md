# NitHigh Bites — Broken Debugging Challenge

This is the intentionally broken version of the NitHigh Bites food ordering website.

## Goal

Participants must debug and fix:

- Java compile errors
- Spring Boot bean errors
- JPA entity errors
- Repository method errors
- API endpoint errors
- Validation bugs
- Frontend-backend connection bugs
- Broken test cases

## Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- H2 Database
- Maven
- JUnit
- HTML, CSS, JavaScript

## Run Backend

```bash
cd backend
mvn spring-boot:run
```

## Run Tests

```bash
cd backend
mvn test
```

## Frontend

Open:

```text
frontend/index.html
```

## Nightmare Edition

This version includes extra hard test files:

```text
FoodNightmareValidationTest.java
FoodNightmareApiTest.java
FoodNightmareBrokenTest.java
```

See:

```text
NIGHTMARE_TEST_CASES.md
```
