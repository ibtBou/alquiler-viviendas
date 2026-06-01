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


        private static final String URL =
        "jdbc:mysql://localhost:3306/alquiler_viviendas";

        private static final String USER =
        "alquilaria";

        private static final String PASSWORD =
        "alquilaria123";

        private DatabaseConnection() {
            // Constructor privado para evitar instanciación
        }
/**
     * Obtiene una conexión a la base de datos.
     *
     * @return Conexión JDBC activa.
     * @throws SQLException Si ocurre un error durante la conexión.
     */
        public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
}
}