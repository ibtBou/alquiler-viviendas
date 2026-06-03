package com.alquilaria.controller;

import com.alquilaria.dao.PropietarioDAO;
import com.alquilaria.model.Propietario;
import com.alquilaria.util.validador;
/**
 * Controlador encargado de gestionar
 * las operaciones de propietarios.
 *
 * @author Betty
 * @version 1.0
 */


public class PropietarioController {
    private PropietarioDAO propietarioDAO;

    public PropietarioController() {
        
        propietarioDAO = new PropietarioDAO();
    }

    public String crear(Propietario propietario) {
        if (!validador.dniValido(propietario.getDni())) {
            return "DNI no válido";
        }

        if (!validador.nombreValido(propietario.getNombre())) {
            return "Nombre no válido";
        }

        if (!validador.telefonoValido(propietario.getTelefono())) {
            return "Teléfono no válido";
        }

        if (!validador.emailValido(propietario.getEmail())) {
            return "Email no válido";
        }
        return propietarioDAO.crear(propietario);
    }

    public String eliminar(int id) {
        return propietarioDAO.eliminar(id);
    }

    public String modificar(Propietario propietario) {
        if (!validador.dniValido(propietario.getDni())) {
            return "DNI no válido";
        }

        if (!validador.nombreValido(propietario.getNombre())) {
            return "Nombre no válido";
        }

        if (!validador.telefonoValido(propietario.getTelefono())) {
            return "Teléfono no válido";
        }

        if (!validador.emailValido(propietario.getEmail())) {
            return "Email no válido";
        }
        return propietarioDAO.modificar(propietario);
    }

    public Propietario consultar(int id) {
        return propietarioDAO.consultar(id);
    }
}
