package com.alquilaria.controller;

import com.alquilaria.dao.InquilinoDAO;
import com.alquilaria.model.Inquilino;
import com.alquilaria.util.validador;
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
        if (!validador.dniValido(inquilino.getDni())) {
            return "DNI no válido";
        }

        if (!validador.nombreValido(inquilino.getNombre())) {
            return "Nombre no válido";
        }

        if (!validador.telefonoValido(inquilino.getTelefono())) {
            return "Teléfono no válido";
        }

        if (!validador.emailValido(inquilino.getEmail())) {
            return "Email no válido";
        }
        return inquilinoDAO.crear(inquilino);
    }

    public String eliminar(int id) {
        return inquilinoDAO.eliminar(id);
    }

    public String modificar(Inquilino inquilino) {
        if (!validador.dniValido(inquilino.getDni())) {
            return "DNI no válido";
        }

        if (!validador.nombreValido(inquilino.getNombre())) {
            return "Nombre no válido";
        }

        if (!validador.telefonoValido(inquilino.getTelefono())) {
            return "Teléfono no válido";
        }

        if (!validador.emailValido(inquilino.getEmail())) {
            return "Email no válido";
        }
        return inquilinoDAO.modificar(inquilino);
    }

    public Inquilino consultar(int id) {
        return inquilinoDAO.consultar(id);
    }
}