package com.alquilaria.service;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.alquilaria.config.DatabaseConnection;
import com.alquilaria.dao.ContratoDAO;
import com.alquilaria.dao.InquilinoDAO;
import com.alquilaria.dao.PropietarioDAO;
import com.alquilaria.dao.ViviendaDAO;
import com.alquilaria.model.Contrato;
import com.alquilaria.model.Inquilino;
import com.alquilaria.model.Propietario;
import com.alquilaria.model.Vivienda;

public class ExportacionService {
    /*
     * Exporta los propietarios a un archivo JSON.
     *
     * @return Mensaje con la ubicación del archivo exportado.
     */
    public String exportarPropietariosJSON() {

        String sql =
                "{CALL sp_exportar_propietarios_json()}";

        try (
                Connection con = DatabaseConnection.getConnection();
                CallableStatement cs = con.prepareCall(sql)
        ) {

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {

                String json =
                        rs.getString("resultado");

                String ruta =
                        System.getProperty("user.home")
                                + "/Downloads/propietarios.json";

                FileWriter fw =
                        new FileWriter(ruta);

                fw.write(json);

                fw.close();

                return "Fichero guardado en:\n" + ruta;
            }

        } catch (Exception e) {

            return "Error: " + e.getMessage();
        }

        return "No hay datos";
    }

    /**
     * Exporta los inquilinos a un archivo JSON.
     *
     * @return Mensaje con la ubicación del archivo exportado.
     */
    public String exportarInquilinosJSON() {

    String sql =
            "{CALL sp_exportar_inquilinos_json()}";

    try (
            Connection con = DatabaseConnection.getConnection();
            CallableStatement cs = con.prepareCall(sql)
    ) {

        ResultSet rs = cs.executeQuery();

        if (rs.next()) {

            String json =
                    rs.getString("resultado");

            String ruta =
                    System.getProperty("user.home")
                            + "/Downloads/inquilinos.json";

            FileWriter fw =
                    new FileWriter(ruta);

            fw.write(json);

            fw.close();

            return "Fichero guardado en:\n" + ruta;
        }

    } catch (Exception e) {

        return "Error: " + e.getMessage();
    }

    return "No hay datos";
}
/**
     * Exporta las viviendas a un archivo JSON.
     *
     * @return Mensaje con la ubicación del archivo exportado.
     */
public String exportarViviendasJSON() {

    String sql =
            "{CALL sp_exportar_viviendas_json()}";

    try (
            Connection con = DatabaseConnection.getConnection();
            CallableStatement cs = con.prepareCall(sql)
    ) {

        ResultSet rs = cs.executeQuery();

        if (rs.next()) {

            String json =
                    rs.getString("resultado");

            String ruta =
                    System.getProperty("user.home")
                            + "/Downloads/viviendas.json";

            FileWriter fw =
                    new FileWriter(ruta);

            fw.write(json);

            fw.close();

            return "Fichero guardado en:\n" + ruta;
        }

    } catch (Exception e) {

        return "Error: " + e.getMessage();
    }

    return "No hay datos";
}

/**
     * Exporta los contratos a un archivo JSON.
     *
     * @return Mensaje con la ubicación del archivo exportado.
     */
public String exportarContratosJSON() {

    String sql =
            "{CALL sp_exportar_contratos_json()}";

    try (
            Connection con = DatabaseConnection.getConnection();
            CallableStatement cs = con.prepareCall(sql)
    ) {

        ResultSet rs = cs.executeQuery();

        if (rs.next()) {

            String json =
                    rs.getString("resultado");

            String ruta =
                    System.getProperty("user.home")
                            + "/Downloads/contratos.json";

            FileWriter fw =
                    new FileWriter(ruta);

            fw.write(json);

            fw.close();

            return "Fichero guardado en:\n" + ruta;
        }

    } catch (Exception e) {

        return "Error: " + e.getMessage();
    }

    return "No hay datos";
}

/**
     * Exporta los propietarios a un archivo CSV.
     *
     * @return Mensaje con la ubicación del archivo exportado.
     */
public String exportarPropietariosCSV() {

    try {

        PropietarioDAO dao =
                new PropietarioDAO();

        List<Propietario> lista =
                dao.listarTodos();

        String ruta =
                System.getProperty("user.home")
                        + "/Downloads/propietarios.csv";

        PrintWriter pw =
        new PrintWriter(
                new OutputStreamWriter(
                        new FileOutputStream(ruta),
                        StandardCharsets.UTF_8
                )
        );

        pw.println(
                "id;dni;nombre;telefono;email"
        );

        for (Propietario p : lista) {

            pw.println(
                    p.getId() + ";"
                            + p.getDni() + ";"
                            + p.getNombre() + ";"
                            + p.getTelefono() + ";"
                            + p.getEmail()
            );
        }

        pw.close();

        return "CSV generado en:\n" + ruta;

    } catch (IOException e) {

    return "El archivo está abierto. Ciérralo y vuelve a intentarlo.";
}
}
/**
     * Exporta los inquilinos a un archivo CSV.
     *
     * @return Mensaje con la ubicación del archivo exportado.
     */
public String exportarInquilinosCSV() {

    try {

        InquilinoDAO dao =
                new InquilinoDAO();

        List<Inquilino> lista =
                dao.listarTodos();

        String ruta =
                System.getProperty("user.home")
                        + "/Downloads/inquilinos.csv";

        PrintWriter pw =
                new PrintWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(ruta),
                                StandardCharsets.UTF_8
                        )
                );

        pw.println(
                "id;dni;nombre;telefono;email;tieneMascota"
        );

        for (Inquilino i : lista) {

            pw.println(
                    i.getId() + ";"
                            + i.getDni() + ";"
                            + i.getNombre() + ";"
                            + i.getTelefono() + ";"
                            + i.getEmail() + ";"
                            + i.isTieneMascota()
            );
        }

        pw.close();

        return "CSV generado en:\n" + ruta;

    } catch (Exception e) {

        return "Error: " + e.getMessage();
    }
}
/**
     * Exporta las viviendas a un archivo CSV.
     *
     * @return Mensaje con la ubicación del archivo exportado.
     */
public String exportarViviendasCSV() {

    try {

        ViviendaDAO dao =
                new ViviendaDAO();

        List<Vivienda> lista =
                dao.listarTodos();

        String ruta =
                System.getProperty("user.home")
                        + "/Downloads/viviendas.csv";

        PrintWriter pw =
                new PrintWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(ruta),
                                StandardCharsets.UTF_8
                        )
                );

        pw.println(
                "id;idPropietario;codigo;tipo;direccion;superficie;precioMes;descripcion;aceptaMascota"
        );

        for (Vivienda v : lista) {

            pw.println(
                    v.getId() + ";"
                            + v.getIdPropietario() + ";"
                            + v.getCodigo() + ";"
                            + v.getTipo() + ";"
                            + v.getDireccion() + ";"
                            + v.getSuperficie() + ";"
                            + v.getPrecioMes() + ";"
                            + v.getDescripcion() + ";"
                            + v.isAceptaMascota()
            );
        }

        pw.close();

        return "CSV generado en:\n" + ruta;

    } catch (Exception e) {

        return "Error: " + e.getMessage();
    }
}
/**
     * Exporta los contratos a un archivo CSV.
     *
     * @return Mensaje con la ubicación del archivo exportado.
     */
public String exportarContratosCSV() {

    try {

        ContratoDAO dao =
                new ContratoDAO();

        List<Contrato> lista =
                dao.listarTodos();

        String ruta =
                System.getProperty("user.home")
                        + "/Downloads/contratos.csv";

        PrintWriter pw =
                new PrintWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(ruta),
                                StandardCharsets.UTF_8
                        )
                );

        pw.println(
                "idContrato;idVivienda;idInquilino;fechaInicio;fechaFin;precio;estado"
        );

        for (Contrato c : lista) {

            pw.println(
                    c.getIdContrato() + ";"
                            + c.getIdVivienda() + ";"
                            + c.getIdInquilino() + ";"
                            + c.getFechaInicio() + ";"
                            + c.getFechaFin() + ";"
                            + c.getPrecio() + ";"
                            + c.getEstado()
            );
        }

        pw.close();

        return "CSV generado en:\n" + ruta;

    } catch (Exception e) {

        return "Error: " + e.getMessage();
    }
}
}