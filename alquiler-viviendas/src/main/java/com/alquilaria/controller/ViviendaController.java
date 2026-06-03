package com.alquilaria.controller;

import com.alquilaria.dao.ViviendaDAO;
import com.alquilaria.model.Vivienda;
import com.alquilaria.util.validador;
/**
 * Controlador encargado de gestionar
 * las operaciones de viviendas.
 *
 * @author Betty
 * @version 1.0
 */ 

public class ViviendaController {

    private ViviendaDAO viviendaDAO;

    public ViviendaController() {
        viviendaDAO = new ViviendaDAO();
    }

    public String crear(Vivienda vivienda) {
        if (!validador.positivo(vivienda.getIdPropietario())) {
            return "ID propietario no válido";
        }

        if (!validador.codigoViviendaValido(vivienda.getCodigo())) {
            return "Código de vivienda no válido";
    }

        if (!validador.tipoViviendaValido(vivienda.getTipo())) {
            return "Tipo de vivienda no válido";
        }

        if (!validador.direccionValida(vivienda.getDireccion())) {
            return "Dirección no válida";
        }

        if (!validador.superficieValida(vivienda.getSuperficie())) {
            return "La superficie debe ser mayor que cero";
        }

        if (!validador.positivo(vivienda.getPrecioMes())) {
            return "El precio debe ser positivo";
        }

        if (!validador.descripcionValida(vivienda.getDescripcion())) {
            return "Descripción no válida";
        }
        return viviendaDAO.crear(vivienda);
    }

    public String eliminar(int id) {
        return viviendaDAO.eliminar(id);
    }

    public String modificar(Vivienda vivienda) {
        if (!validador.positivo(vivienda.getId())) {
            return "ID de vivienda no válido";
        }

        if (!validador.positivo(vivienda.getIdPropietario())) {
            return "ID propietario no válido";
        }

        if (!validador.codigoViviendaValido(vivienda.getCodigo())) {
            return "Código de vivienda no válido";
        }

        if (!validador.tipoViviendaValido(vivienda.getTipo())) {
            return "Tipo de vivienda no válido";
        }

        if (!validador.direccionValida(vivienda.getDireccion())) {
            return "Dirección no válida";
        }

        if (!validador.superficieValida(vivienda.getSuperficie())) {
            return "La superficie debe ser mayor que cero";
        }

        if (!validador.positivo(vivienda.getPrecioMes())) {
            return "El precio debe ser positivo";
        }

        if (!validador.descripcionValida(vivienda.getDescripcion())) {
            return "Descripción no válida";
        }
        return viviendaDAO.modificar(vivienda);
    }

    public Vivienda consultar(int id) {
        return viviendaDAO.consultar(id);
    }
}