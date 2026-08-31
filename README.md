# Car Leasing Application

Microservice based backend project using Java 21, Spring Boot 4.1.1, REST APIs, Spring Security, JWT, JPA/Hibernate, MySQL, Docker, Maven, and GitHub.

## Project Setup

Open this folder in IntelliJ IDEA:

```text
car-leasing-application
```

Current root project contains:

```text
pom.xml
docker-compose.yml
docker/mysql/init/01-create-databases.sql
.gitignore
README.md
```

Service modules will be added one by one:

```text
auth-service
vehicle-service
customer-service
quotation-service
corporate-service
api-gateway
```

## Run MySQL With Docker

From the project root:

```bash
docker compose up -d
```

This starts MySQL on port `3306` with:

```text
username: root
password: root
```

It also creates these databases:

```text
car_lease_auth_db
car_lease_vehicle_db
car_lease_customer_db
car_lease_quote_db
car_lease_corporate_db
```

## Stop MySQL

```bash
docker compose down
```

## Maven Build

At this stage, only the parent Maven project exists:

```bash
mvn clean install
```

As each microservice is created, it will be added to the parent `pom.xml` modules section.
