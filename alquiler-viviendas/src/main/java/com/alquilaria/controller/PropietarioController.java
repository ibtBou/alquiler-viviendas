package com.alquilaria.controller;

import com.alquilaria.dao.PropietarioDAO;
import com.alquilaria.model.Propietario;

public class PropietarioController {
    private PropietarioDAO propietarioDAO;

    public PropietarioController() {
        propietarioDAO = new PropietarioDAO();
    }

    public String crear(Propietario propietario) {
        return propietarioDAO.crear(propietario);
    }

    public String eliminar(int id) {
        return propietarioDAO.eliminar(id);
    }

    public String modificar(Propietario propietario) {
        return propietarioDAO.modificar(propietario);
    }

    public Propietario consultar(int id) {
        return propietarioDAO.consultar(id);
    }
}
