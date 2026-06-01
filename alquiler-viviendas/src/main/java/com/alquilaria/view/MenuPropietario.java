package com.alquilaria.view;

import com.alquilaria.controller.PropietarioController;
import com.alquilaria.model.Propietario;
import com.alquilaria.service.ExportacionService;
import com.alquilaria.util.InputUtil;

public class MenuPropietario {

    private PropietarioController controller;
    private ExportacionService exportacionService;

    public MenuPropietario() {
        controller = new PropietarioController();
        exportacionService = new ExportacionService();
    }

    public void mostrar() {

        int opcion;

        do {

            System.out.println("\n===== PROPIETARIOS =====");
            System.out.println("1. Crear propietario");
            System.out.println("2. Consultar propietario");
            System.out.println("3. Modificar propietario");
            System.out.println("4. Eliminar propietario");
            System.out.println("5. Exportar propietarios a JSON");
            System.out.println("6. Exportar propietarios a CSV");
            System.out.println("0. Volver");

            opcion = InputUtil.leerInt("Opción: ");

            switch (opcion) {

                case 1:
                    crear();
                    break;

                case 2:
                    consultar();
                    break;

                case 3:
                    modificar();
                    break;

                case 4:
                    eliminar();
                    break;

                case 5:
                    exportarJSON();
                    break;

                case 6:
                    exportarCSV();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción incorrecta");
            }

        } while (opcion != 0);
    }


    private void exportarJSON() {

    System.out.println(
            exportacionService.exportarPropietariosJSON()
    );
}
// Métodos para cada opción del menú
//crear
    private void crear() {

        String dni = InputUtil.leerString("DNI: ");
        String nombre = InputUtil.leerString("Nombre: ");
        String telefono = InputUtil.leerString("Teléfono: ");
        String email = InputUtil.leerString("Email: ");
        // Crear un nuevo propietario con ID 0 (se asignará automáticamente en la base de datos)
        Propietario propietario =
                new Propietario(0, dni, nombre, telefono, email);

        System.out.println(controller.crear(propietario));
    }
//consultar
    private void consultar() {

        int id = InputUtil.leerInt("ID: ");

        Propietario propietario =
                controller.consultar(id);
        // Mostrar los datos del propietario o un mensaje si no existe
        if (propietario == null) {

            System.out.println("No existe");

        } else {

            System.out.println(propietario);
        }
    }
    //modificar
    private void modificar() {

        int id = InputUtil.leerInt("ID: ");
        String dni = InputUtil.leerString("DNI: ");
        String nombre = InputUtil.leerString("Nombre: ");
        String telefono = InputUtil.leerString("Teléfono: ");
        String email = InputUtil.leerString("Email: ");
        // Crear un nuevo propietario con los datos ingresados (ID incluido)
        Propietario propietario =
                new Propietario(id, dni, nombre, telefono, email);

        System.out.println(controller.modificar(propietario));
    }
    //eliminar
    private void eliminar() {

        int id = InputUtil.leerInt("ID: ");

        System.out.println(controller.eliminar(id));
    }
    private void exportarCSV() {

    System.out.println(
            exportacionService.exportarPropietariosCSV()
    );
}
}