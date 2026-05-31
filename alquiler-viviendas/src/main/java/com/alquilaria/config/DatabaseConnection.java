package com.alquilaria.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Configuración de la conexión a la base de datos
    
    private static final String URL =
            "jdbc:mysql://localhost:3306/alquiler_viviendas";

    private static final String USER =
            "alquilaria";

    private static final String PASSWORD =
            "alquilaria123";

    private DatabaseConnection() {
    }
    // Método para obtener una conexión a la base de datos
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}