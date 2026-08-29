# API Testing Assignment (Java + RestAssured + JUnit)

## Requirements
- Java 17 (or update `pom.xml` compiler version to match your installed JDK)
- Maven installed (`mvn -version` to check)

## How to run

1. Open a terminal in this folder (the one containing `pom.xml`).
2. Run:
   ```
   mvn test
   ```
3. Maven will download dependencies (RestAssured, JUnit) automatically and run all tests in `src/test/java/ApiTest.java`.

## What it does

Tests a free public API (`https://jsonplaceholder.typicode.com`) covering:
- GET single resource
- GET all resources (list)
- POST (create)
- PUT (update)
- DELETE
- Negative test case (404 for invalid resource)

## To point it at your own API

In `ApiTest.java`, change this line:
```java
RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
```
to your assigned API's base URL, and update the endpoint paths (`/posts`, `/posts/{id}`, etc.) and request/response fields to match your API.

## Folder structure
```
api-testing-assignment/
├── pom.xml
├── README.md
└── src
    └── test
        └── java
            └── ApiTest.java
```
