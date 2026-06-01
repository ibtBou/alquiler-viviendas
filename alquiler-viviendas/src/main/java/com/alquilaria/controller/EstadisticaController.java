package com.alquilaria.controller;

import com.alquilaria.dao.EstadisticaDAO;

public class EstadisticaController {

    private EstadisticaDAO estadisticaDAO;

    public EstadisticaController() {
        estadisticaDAO = new EstadisticaDAO();
    }

    public void historicoInquilino(int id) {
        estadisticaDAO.historicoInquilino(id);
    }

    public void viviendasPropietario(int id) {
        estadisticaDAO.viviendasPropietario(id);
    }
}