package com.alquilaria.controller;

import com.alquilaria.dao.ViviendaDAO;
import com.alquilaria.model.Vivienda;

public class ViviendaController {

    private ViviendaDAO viviendaDAO;

    public ViviendaController() {
        viviendaDAO = new ViviendaDAO();
    }

    public String crear(Vivienda vivienda) {
        return viviendaDAO.crear(vivienda);
    }

    public String eliminar(int id) {
        return viviendaDAO.eliminar(id);
    }

    public String modificar(Vivienda vivienda) {
        return viviendaDAO.modificar(vivienda);
    }

    public Vivienda consultar(int id) {
        return viviendaDAO.consultar(id);
    }
}