package com.alquilaria.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alquilaria.config.DatabaseConnection;
import com.alquilaria.model.Contrato;

public class ContratoDAO {
    //funciones CRUD para Contrato
    //crear contrato
    public String crear(Contrato contrato) {

        String sql = "{CALL sp_crear_contrato(?,?,?,?,?,?)}";

        try (
                Connection con = DatabaseConnection.getConnection();
                CallableStatement cs = con.prepareCall(sql)
        ) {
            //pasar parametros al procedimiento almacenado
            cs.setInt(1, contrato.getIdVivienda());
            cs.setInt(2, contrato.getIdInquilino());
            cs.setDate(3, java.sql.Date.valueOf(contrato.getFechaInicio()));
            cs.setDate(4, java.sql.Date.valueOf(contrato.getFechaFin()));
            cs.setBigDecimal(5, contrato.getPrecio());
            cs.setInt(6, contrato.getEstado());

            cs.execute();

            ResultSet rs = cs.getResultSet();
            //obtener mensaje de la base de datos
            if (rs.next()) {
                return rs.getString("mensaje");
            }

        } catch (SQLException e) {
            return "Error JDBC: " + e.getMessage();
        }

        return "Error al crear contrato";
    }
    //eliminar contrato
    public String eliminar(int id) {

        String sql = "{CALL sp_eliminar_contrato(?)}";

        try (
                Connection con = DatabaseConnection.getConnection();
                CallableStatement cs = con.prepareCall(sql)
        ) {
            //pasar parametro al procedimiento almacenado
            cs.setInt(1, id);

            cs.execute();

            ResultSet rs = cs.getResultSet();
            //obtener mensaje de la base de datos
            if (rs.next()) {
                return rs.getString("mensaje");
            }

        } catch (SQLException e) {
            return "Error JDBC: " + e.getMessage();
        }

        return "Error al eliminar contrato";
    }

    //modificar contrato
    public String modificar(Contrato contrato) {

        String sql = "{CALL sp_modificar_contrato(?,?,?,?,?,?,?)}";

        try (
                Connection con = DatabaseConnection.getConnection();
                CallableStatement cs = con.prepareCall(sql)
        ) {
            //pasar parametros al procedimiento almacenado
            cs.setInt(1, contrato.getIdContrato());
            cs.setInt(2, contrato.getIdVivienda());
            cs.setInt(3, contrato.getIdInquilino());
            cs.setDate(4, java.sql.Date.valueOf(contrato.getFechaInicio()));
            cs.setDate(5, java.sql.Date.valueOf(contrato.getFechaFin()));
            cs.setBigDecimal(6, contrato.getPrecio());
            cs.setInt(7, contrato.getEstado());

            cs.execute();

            ResultSet rs = cs.getResultSet();
            //obtener mensaje de la base de datos
            if (rs.next()) {
                return rs.getString("mensaje");
            }

        } catch (SQLException e) {
            return "Error JDBC: " + e.getMessage();
        }

        return "Error al modificar contrato";
    }
    //consultar contrato por id
    public Contrato consultar(int id) {

        String sql = "{CALL sp_consultar_contrato(?)}";

        try (
                Connection con = DatabaseConnection.getConnection();
                CallableStatement cs = con.prepareCall(sql)
        ) {

            cs.setInt(1, id);

            ResultSet rs = cs.executeQuery();
            //obtener contrato de la base de datos
            if (rs.next()) {

                return new Contrato(
                        rs.getInt("id_contrato"),
                        rs.getInt("id_vivienda"),
                        rs.getInt("id_inquilino"),
                        rs.getDate("fecha_inicio").toLocalDate(),
                        rs.getDate("fecha_fin").toLocalDate(),
                        rs.getBigDecimal("precio"),
                        rs.getInt("estado")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error JDBC: " + e.getMessage());
        }
        //si no se encuentra el contrato o hay un error, retornar null
        return null;
    }
}