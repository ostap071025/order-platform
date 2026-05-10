# Order Platform

Microservices-based backend application built with Java and Spring Boot.

## Overview

This project was created to practice backend development concepts such as:
- Microservices architecture
- Service-to-service communication
- REST APIs
- Shared libraries
- Docker
- Event-driven communication with Kafka

The project is currently in development and actively used as a learning project.

---

## Services

### order-service
Handles order creation and order management.

### payment-service
Processes payments and payment-related operations.

### delivery-service
Handles delivery logic and communication between services.

### common-libs
Shared module containing:
- DTOs
- Enums
- Kafka events
- Common classes used across services

---

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker
- Gradle
- Kafka (in progress)

---

## Architecture

```text
order-service <-> payment-service
        |
        v
delivery-service

shared communication via common-libs
```

---

## Current Features

- Multi-module Gradle project
- REST communication between services
- Shared DTO/event module
- PostgreSQL integration
- Docker Compose setup
- Basic microservices structure

---

## Planned Improvements

- Kafka event communication
- Spring Security + JWT
- API Gateway
- Service discovery
- Unit and integration tests
- Better exception handling
- Swagger/OpenAPI documentation

---

## Running the Project

### Clone repository

```bash
git clone <repository-url>
```

### Start database

```bash
docker compose up
```

### Run services

```bash
./gradlew bootRun
```

---

## Learning Goals

This project is mainly focused on improving skills in:
- Backend development
- Spring Boot
- Microservices
- Kafka
- Clean architecture
- Distributed systems basics
