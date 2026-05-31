package com.alquilaria.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alquilaria.config.DatabaseConnection;
import com.alquilaria.model.Inquilino;

public class InquilinoDAO {
    // CRUD: Create, Read, Update, Delete
    // Create -> crear
    public String crear(Inquilino inquilino) {

        String sql = "{CALL sp_crear_inquilino(?,?,?,?,?)}";

        try (
                Connection con = DatabaseConnection.getConnection();
                CallableStatement cs = con.prepareCall(sql)
        ) {
            // Parámetros de entrada
            cs.setString(1, inquilino.getDni());
            cs.setString(2, inquilino.getNombre());
            cs.setString(3, inquilino.getTelefono());
            cs.setString(4, inquilino.getEmail());
            cs.setBoolean(5, inquilino.isTieneMascota());

            cs.execute();

            ResultSet rs = cs.getResultSet();
            // Parámetro de salida
            if (rs.next()) {
                return rs.getString("mensaje");
            }

        } catch (SQLException e) {
            return "Error JDBC: " + e.getMessage();
        }

        return "Error al crear inquilino";
    }
    // Delete -> eliminar
    public String eliminar(int id) {

        String sql = "{CALL sp_eliminar_inquilino(?)}";

        try (
                Connection con = DatabaseConnection.getConnection();
                CallableStatement cs = con.prepareCall(sql)
        ) {

            cs.setInt(1, id);

            cs.execute();

            ResultSet rs = cs.getResultSet();
            // Parámetro de salida
            if (rs.next()) {
                return rs.getString("mensaje");
            }

        } catch (SQLException e) {
            return "Error JDBC: " + e.getMessage();
        }

        return "Error al eliminar inquilino";
    }
    // Update -> modificar
    public String modificar(Inquilino inquilino) {

        String sql = "{CALL sp_modificar_inquilino(?,?,?,?,?,?)}";

        try (
                Connection con = DatabaseConnection.getConnection();
                CallableStatement cs = con.prepareCall(sql)
        ) {
            // Parámetros de entrada
            cs.setInt(1, inquilino.getId());
            cs.setString(2, inquilino.getDni());
            cs.setString(3, inquilino.getNombre());
            cs.setString(4, inquilino.getTelefono());
            cs.setString(5, inquilino.getEmail());
            cs.setBoolean(6, inquilino.isTieneMascota());

            cs.execute();

            ResultSet rs = cs.getResultSet();
            // Parámetro de salida
            if (rs.next()) {
                return rs.getString("mensaje");
            }

        } catch (SQLException e) {
            return "Error JDBC: " + e.getMessage();
        }

        return "Error al modificar inquilino";
    }
    // Read -> consultar
    public Inquilino consultar(int id) {

        String sql = "{CALL sp_consultar_inquilino(?)}";

        try (
                Connection con = DatabaseConnection.getConnection();
                CallableStatement cs = con.prepareCall(sql)
        ) {

            cs.setInt(1, id);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {

                return new Inquilino(
                        rs.getInt("id"),
                        rs.getString("DNI"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getBoolean("tiene_mascota")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error JDBC: " + e.getMessage());
        }
        // Si no se encuentra el inquilino, se devuelve null
        return null;
    }
}