# 🏠 Alquilaria

### Rental Housing Management System

## Description

Alquilaria is a Java console application developed to manage rental housing information. The system allows users to manage property owners, tenants, properties, and rental contracts through a menu-driven interface connected to a MySQL database.

The project follows an MVC-inspired architecture and uses JDBC for database access, Maven for dependency management, stored procedures for database operations, and JUnit for unit testing.

---

## Features

### Property Owners Management
- Create property owners
- Update property owners
- Delete property owners
- Search property owners by ID
- Export data to CSV and JSON

### Properties Management
- Create properties
- Update properties
- Delete properties
- Search properties by ID
- Export data to CSV and JSON

### Tenants Management
- Create tenants
- Update tenants
- Delete tenants
- Search tenants by ID
- Export data to CSV and JSON

### Contracts Management
- Create rental contracts
- Update rental contracts
- Delete rental contracts
- Search contracts by ID
- Export data to CSV and JSON

### Advanced Queries
- Rental history by tenant
- Rented properties by owner

### Data Export
- CSV export
- JSON export
- Files are automatically generated in the user's Downloads folder

### Testing
- Unit testing with JUnit
- System testing
- Database connection testing

---

## Technologies Used

- Java 20
- Maven
- MySQL
- JDBC
- JUnit 4
- JSON
- Git & GitHub

---

## Project Structure

```text
src/main/java
├── model
├── dao
├── controller
├── service
├── view
├── util
└── config

src/test/java
└── Unit tests
```

## Database

The application uses a MySQL database named:

```sql
alquiler_viviendas
```

Main tables:

- propietario
- vivienda
- inquilino
- contrato
- tipovivienda
- tipoestado

The system uses stored procedures for CRUD operations, advanced queries and JSON exports.

---

## Installation

### Clone the repository

```bash
git clone https://github.com/ibtBou/alquiler-viviendas.git
```

### Open the project

Open the project using Visual Studio Code or any Java IDE.

### Configure the database

1. Create the database in MySQL.
2. Execute the SQL scripts included in the project.
3. Configure the database credentials in `DatabaseConnection.java`.

### Build the project

```bash
mvn clean install
```

### Run the application

```bash
mvn exec:java
```

---

## Generate JavaDoc

```bash
mvn javadoc:javadoc
```

Generated documentation:

```text
target/reports/apidocs
```

---

## Run Unit Tests

```bash
mvn test
```

---

## Author

**Betty**

Final Project – Web Application Development & Computer Engineering