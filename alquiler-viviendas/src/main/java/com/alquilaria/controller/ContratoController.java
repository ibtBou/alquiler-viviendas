package com.alquilaria.controller;

import com.alquilaria.dao.ContratoDAO;
import com.alquilaria.model.Contrato;

/**
 * Controlador encargado de gestionar
 * las operaciones de contratos.
 *
 * @author Betty
 * @version 1.0
 */
public class ContratoController {

    private ContratoDAO contratoDAO;

    public ContratoController() {
        contratoDAO = new ContratoDAO();
    }

    public String crear(Contrato contrato) {
        return contratoDAO.crear(contrato);
    }

    public String eliminar(int id) {
        return contratoDAO.eliminar(id);
    }

    public String modificar(Contrato contrato) {
        return contratoDAO.modificar(contrato);
    }

    public Contrato consultar(int id) {
        return contratoDAO.consultar(id);
    }
    
}