package com.alquilaria.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona las conexiones con la base de datos MySQL.
 *
 * @author Betty
 * @version 1.0
 */
public class DatabaseConnection {

        private DatabaseConnection() {
        }

        /**
     * Obtiene una conexión a la base de datos.
     *
     * @return conexión JDBC activa
     * @throws SQLException si ocurre un error de conexión
     */
        public static Connection getConnection() throws SQLException {

        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        return DriverManager.getConnection(
                url,
                user,
                password
        );
        }
}