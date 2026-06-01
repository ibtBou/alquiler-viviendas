package com.alquilaria.view;

import com.alquilaria.controller.EstadisticaController;
import com.alquilaria.util.InputUtil;

public class MenuConsultas {

    private EstadisticaController controller;

    public MenuConsultas() {
        controller = new EstadisticaController();
    }

    public void mostrar() {

        int opcion;

        do {

            System.out.println("\n===== CONSULTAS AVANZADAS =====");
            System.out.println("1. Histórico alquileres por inquilino");
            System.out.println("2. Viviendas alquiladas por propietario");
            System.out.println("0. Volver");

            opcion = InputUtil.leerInt("Opción: ");

            switch (opcion) {

                case 1 -> historicoInquilino();
                case 2 -> viviendasPropietario();

            }

        } while (opcion != 0);
    }

    private void historicoInquilino() {

        int id =
                InputUtil.leerInt("ID Inquilino: ");

        controller.historicoInquilino(id);
    }

    private void viviendasPropietario() {

        int id =
                InputUtil.leerInt("ID Propietario: ");

        controller.viviendasPropietario(id);
    }
}