package com.alquilaria.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alquilaria.config.DatabaseConnection;
import com.alquilaria.model.Propietario;

public class PropietarioDAO {

    // CRUD: Create, Read, Update, Delete
    // Create -> crear
    public String crear(Propietario propietario) {

        String sql = "{CALL sp_crear_propietario(?,?,?,?)}";

        try (
                Connection con = DatabaseConnection.getConnection();
                CallableStatement cs = con.prepareCall(sql)
        ) {

            cs.setString(1, propietario.getDni());
            cs.setString(2, propietario.getNombre());
            cs.setString(3, propietario.getTelefono());
            cs.setString(4, propietario.getEmail());

            cs.execute();

            ResultSet rs = cs.getResultSet();

            if (rs.next()) {
                return rs.getString("mensaje");
            }

        } catch (SQLException e) {
            return "Error JDBC: " + e.getMessage();
        }

        return "Error al crear propietario";
    }
    // Delete -> eliminar
    public String eliminar(int id) {

        String sql = "{CALL sp_eliminar_propietario(?)}";

        try (
                Connection con = DatabaseConnection.getConnection();
                CallableStatement cs = con.prepareCall(sql)
        ) {

            cs.setInt(1, id);

            cs.execute();

            ResultSet rs = cs.getResultSet();

            if (rs.next()) {
                return rs.getString("mensaje");
            }

        } catch (SQLException e) {
            return "Error JDBC: " + e.getMessage();
        }

        return "Error al eliminar propietario";
    }
    // Update -> modificar
    public String modificar(Propietario propietario) {

        String sql = "{CALL sp_modificar_propietario(?,?,?,?,?)}";

        try (
                Connection con = DatabaseConnection.getConnection();
                CallableStatement cs = con.prepareCall(sql)
        ) {

            cs.setInt(1, propietario.getId());
            cs.setString(2, propietario.getDni());
            cs.setString(3, propietario.getNombre());
            cs.setString(4, propietario.getTelefono());
            cs.setString(5, propietario.getEmail());

            cs.execute();

            ResultSet rs = cs.getResultSet();

            if (rs.next()) {
                return rs.getString("mensaje");
            }

        } catch (SQLException e) {
            return "Error JDBC: " + e.getMessage();
        }

        return "Error al modificar propietario";
    }
    // Read -> consultar
    public Propietario consultar(int id) {

        String sql = "{CALL sp_consultar_propietario(?)}";

        try (
                Connection con = DatabaseConnection.getConnection();
                CallableStatement cs = con.prepareCall(sql)
        ) {

            cs.setInt(1, id);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {

                return new Propietario(
                        rs.getInt("id"),
                        rs.getString("DNI"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error JDBC: " + e.getMessage());
        }
        // Si no se encuentra el propietario, se devuelve null
        return null;
    }
}