package com.alquilaria.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alquilaria.config.DatabaseConnection;
import com.alquilaria.model.Vivienda;

public class ViviendaDAO {
    // CRUD: Create, Read, Update, Delete
    // Create: crear vivienda
    public String crear(Vivienda vivienda) {
        
        String sql = "{CALL sp_crear_vivienda(?,?,?,?,?,?,?,?)}";

        try (
                Connection con = DatabaseConnection.getConnection();
                CallableStatement cs = con.prepareCall(sql)
        ) {

            cs.setInt(1, vivienda.getIdPropietario());
            cs.setString(2, vivienda.getCodigo());
            cs.setInt(3, vivienda.getTipo());
            cs.setString(4, vivienda.getDireccion());
            cs.setInt(5, vivienda.getSuperficie());
            cs.setBigDecimal(6, vivienda.getPrecioMes());
            cs.setString(7, vivienda.getDescripcion());
            cs.setBoolean(8, vivienda.isAceptaMascota());

            cs.execute();

            ResultSet rs = cs.getResultSet();
            // El procedimiento almacenado devuelve un mensaje indicando el resultado de la operación
            if (rs.next()) {
                return rs.getString("mensaje");
            }

        } catch (SQLException e) {
            return "Error JDBC: " + e.getMessage();
        }

        return "Error al crear vivienda";
    }
    // Delete: eliminar vivienda
    public String eliminar(int id) {

        String sql = "{CALL sp_eliminar_vivienda(?)}";

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
        // Si el procedimiento almacenado no devuelve un mensaje, se puede retornar un mensaje genérico
        return "Error al eliminar vivienda";
    }
    // Update: modificar vivienda
    public String modificar(Vivienda vivienda) {

        String sql = "{CALL sp_modificar_vivienda(?,?,?,?,?,?,?,?,?)}";

        try (
                Connection con = DatabaseConnection.getConnection();
                CallableStatement cs = con.prepareCall(sql)
        ) {

            cs.setInt(1, vivienda.getId());
            cs.setInt(2, vivienda.getIdPropietario());
            cs.setString(3, vivienda.getCodigo());
            cs.setInt(4, vivienda.getTipo());
            cs.setString(5, vivienda.getDireccion());
            cs.setInt(6, vivienda.getSuperficie());
            cs.setBigDecimal(7, vivienda.getPrecioMes());
            cs.setString(8, vivienda.getDescripcion());
            cs.setBoolean(9, vivienda.isAceptaMascota());

            cs.execute();

            ResultSet rs = cs.getResultSet();
            // El procedimiento almacenado devuelve un mensaje indicando el resultado de la operación
            if (rs.next()) {
                return rs.getString("mensaje");
            }

        } catch (SQLException e) {
            return "Error JDBC: " + e.getMessage();
        }

        return "Error al modificar vivienda";
    }
    // Read: consultar vivienda por id
    public Vivienda consultar(int id) {

        String sql = "{CALL sp_consultar_vivienda(?)}";

        try (
                Connection con = DatabaseConnection.getConnection();
                CallableStatement cs = con.prepareCall(sql)
        ) {

            cs.setInt(1, id);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {

                return new Vivienda(
                        rs.getInt("id"),
                        rs.getInt("id_propietario"),
                        rs.getString("codigo"),
                        rs.getInt("tipo"),
                        rs.getString("direccion"),
                        rs.getInt("superficie"),
                        rs.getBigDecimal("precio_mes"),
                        rs.getString("descripcion"),
                        rs.getBoolean("acepta_mascota")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error JDBC: " + e.getMessage());
        }
        // Si el procedimiento almacenado no devuelve una vivienda, se puede retornar null o lanzar una excepción
        return null;
    }
}