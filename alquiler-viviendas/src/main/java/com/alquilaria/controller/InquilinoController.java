package com.alquilaria.controller;

import com.alquilaria.dao.InquilinoDAO;
import com.alquilaria.model.Inquilino;

/**
 * Controlador encargado de gestionar
 * las operaciones de inquilinos.
 *
 * @author Betty
 * @version 1.0
 */
public class InquilinoController {

    private InquilinoDAO inquilinoDAO;

    public InquilinoController() {
        inquilinoDAO = new InquilinoDAO();
    }

    public String crear(Inquilino inquilino) {
        return inquilinoDAO.crear(inquilino);
    }

    public String eliminar(int id) {
        return inquilinoDAO.eliminar(id);
    }

    public String modificar(Inquilino inquilino) {
        return inquilinoDAO.modificar(inquilino);
    }

    public Inquilino consultar(int id) {
        return inquilinoDAO.consultar(id);
    }
}