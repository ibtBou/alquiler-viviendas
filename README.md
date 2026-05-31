# Alquiler Viviendas

Aplicación Java Maven para gestionar propietarios, viviendas, inquilinos y contratos de alquiler desde menús por terminal.

## Tecnologías

- Java 24
- Maven
- MySQL
- JDBC
- JUnit 5

## Configuración

Edita `src/main/resources/database.properties` con el usuario de MySQL que usará la aplicación:

```properties
db.url=jdbc:mysql://localhost:3306/alquiler_viviendas
db.user=alquilaria
db.password=alquilaria123
```

## Ejecutar

```bash
mvn clean compile
mvn exec:java
```

## Tests

```bash
mvn test
```

## Estructura

- `model`: clases que representan las tablas.
- `dao`: acceso a base de datos mediante JDBC y procedimientos almacenados.
- `controller`: conecta los menús con los servicios.
- `view`: menús por consola.
- `util`: utilidades de entrada y validación.
