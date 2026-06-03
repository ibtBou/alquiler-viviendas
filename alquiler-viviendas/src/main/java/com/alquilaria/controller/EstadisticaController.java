package com.alquilaria.controller;

import com.alquilaria.dao.EstadisticaDAO;

/**
 * Controlador encargado de gestionar
 * las operaciones de estadísticas.
 *
 * @author Betty
 * @version 1.0
 */
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