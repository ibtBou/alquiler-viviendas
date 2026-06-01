package com.alquilaria.view;

import com.alquilaria.util.InputUtil;

public class MenuPrincipal {

    public void mostrar() {

        int opcion;

        do {

            System.out.println("\n===== ALQUILARIA =====");
            System.out.println("1. Gestionar propietarios");
            System.out.println("\n==========================");
            System.out.println("2. Gestionar viviendas");
            System.out.println("\n==========================");
            System.out.println("3. Gestionar inquilinos");
            System.out.println("\n==========================");
            System.out.println("4. Gestionar contratos");
            System.out.println("\n==========================");
            System.out.println("5. Consultas avanzadas");
            System.out.println("\n==========================");
            System.out.println("0. Salir");

            opcion = InputUtil.leerInt("Opción: ");

            switch (opcion) {

                case 1:
                    new MenuPropietario().mostrar();
                    break;

                case 2:
                    new MenuVivienda().mostrar();
                    break;

                case 3:
                    new MenuInquilino().mostrar();
                    break;

                case 4:
                    new MenuContrato().mostrar();
                    break;

                case 5:
                    new MenuConsultas().mostrar();
                    break;

                case 0:
                    System.out.println("Hasta luego");
                    break;

                default:
                    System.out.println("Opción incorrecta");
            }

        } while (opcion != 0);
    }
}