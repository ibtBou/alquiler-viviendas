package com.alquilaria.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alquilaria.config.DatabaseConnection;

public class EstadisticaDAO {
    /*
     * Muestra el historial de alquileres de un inquilino.
     *
     * @param idInquilino ID del inquilino.
     */
    public void historicoInquilino(int idInquilino) {
        boolean encontrado = false;
        String sql =
                "{CALL sp_historico_alquileres_inquilino(?)}";

        try (
                Connection con = DatabaseConnection.getConnection();
                CallableStatement cs = con.prepareCall(sql)
        ) {

            cs.setInt(1, idInquilino);

            ResultSet rs = cs.executeQuery();

            System.out.println("\n===== HISTÓRICO ALQUILERES =====");

            while (rs.next()) {

                System.out.println("\n--------------------------------");
                System.out.println("Contrato: " + rs.getInt("id_contrato"));
                System.out.println("Código vivienda: " + rs.getString("codigo"));
                System.out.println("Dirección: " + rs.getString("direccion"));
                System.out.println("Fecha inicio: " + rs.getDate("fecha_inicio"));
                System.out.println("Fecha fin: " + rs.getDate("fecha_fin"));
                System.out.println("Precio: " + rs.getBigDecimal("precio") + " €");
                System.out.println("--------------------------------");
                encontrado = true;
            }

            if (!encontrado) {
                System.out.println(
                        "No se han encontrado alquileres para este inquilino."
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error JDBC: " + e.getMessage()
            );
        }
    }
        /*
        * Muestra las viviendas alquiladas por un propietario.
        *
        * @param idPropietario ID del propietario.
        */
    public void viviendasPropietario(int idPropietario) {
        boolean encontrado = false;
        String sql =
                "{CALL sp_viviendas_alquiladas_propietario(?)}";

        try (
                Connection con = DatabaseConnection.getConnection();
                CallableStatement cs = con.prepareCall(sql)
        ) {

            cs.setInt(1, idPropietario);

            ResultSet rs = cs.executeQuery();

            System.out.println(
                    "\n===== VIVIENDAS ALQUILADAS ====="
            );

            while (rs.next()) {
                System.out.println("\n--------------------------------");
                System.out.println("Código: " + rs.getString("codigo"));
                System.out.println("Dirección: " + rs.getString("direccion"));
                System.out.println("Inquilino: " + rs.getString("nombre"));
                System.out.println("Fecha inicio: " + rs.getDate("fecha_inicio"));
                System.out.println("Fecha fin: " + rs.getDate("fecha_fin"));
                System.out.println("--------------------------------");
                encontrado = true;
            }

            if (!encontrado) {
                System.out.println(
                        "No se han encontrado viviendas alquiladas para este propietario."
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error JDBC: " + e.getMessage()
            );
        }
    }
}