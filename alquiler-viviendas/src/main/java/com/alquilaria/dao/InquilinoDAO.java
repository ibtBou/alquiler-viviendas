package com.alquilaria.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alquilaria.config.DatabaseConnection;
import com.alquilaria.model.Inquilino;

public class InquilinoDAO {
    // CRUD: Create, Read, Update, Delete
    /**
     * Crea un nuevo inquilino en la base de datos.
     *
     * @param inquilino Datos del inquilino a insertar.
     * @return Mensaje indicando el resultado de la operación.
     */
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
    /**
     * Elimina un inquilino de la base de datos.
     *
     * @param id ID del inquilino a eliminar.
     * @return Mensaje indicando el resultado de la operación.
     */
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
    /**
     * Modifica un inquilino en la base de datos.
     *
     * @param inquilino Datos del inquilino a modificar.
     * @return Mensaje indicando el resultado de la operación.
     */
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
    /**
     * Consulta un inquilino en la base de datos.
     *
     * @param id ID del inquilino a consultar.
     * @return El inquilino encontrado o null si no se encuentra.
     */
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
    /**
     * Lista todos los inquilinos en la base de datos.
     *
     * @return Una lista con todos los inquilinos.
     */
    public List<Inquilino> listarTodos() {

    List<Inquilino> inquilinos =
            new ArrayList<>();

    String sql =
            "SELECT * FROM inquilino";

    try (
            Connection con = DatabaseConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)
    ) {

        while (rs.next()) {

            inquilinos.add(
                    new Inquilino(
                            rs.getInt("id"),
                            rs.getString("DNI"),
                            rs.getString("nombre"),
                            rs.getString("telefono"),
                            rs.getString("email"),
                            rs.getBoolean("tiene_mascota")
                    )
            );
        }

    } catch (SQLException e) {

        System.out.println(
                "Error JDBC: " + e.getMessage()
        );
    }

    return inquilinos;
}
}