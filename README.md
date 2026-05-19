# Sales KPI API
### Overview

A containerized Java Spring Boot application designed to parse, filter, and migrate raw sales data from CSV files into a persistent PostgreSQL database.

### Core Features

Automated CSV Inserts: Uses OpenCSV to parse and map raw data to a relational schema.

Dockerized Environment: Fully orchestrated with Docker Compose (Spring Boot + PostgreSQL + pgAdmin).

Business Intelligence: Custom native SQL queries to identify:

Top Products by revenue using high-precision casting.

Regional Analytics (EMEA, AMER) for targeted data migration.

Data Integrity: Implemented @Transactional logic to ensure robust batch processing.

### Tech Stack
Backend: Java 21, Spring Boot 3, Spring Data JPA

Database: PostgreSQL

Tools: Docker, pgAdmin, OpenCSV

### How to Run
Clone the repository.

Run docker-compose up to start the database and pgAdmin.

Run the Spring Boot application (The CSV will auto-import on startup).