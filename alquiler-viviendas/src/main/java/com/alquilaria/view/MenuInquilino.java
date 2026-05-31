package com.alquilaria.view;

import com.alquilaria.controller.InquilinoController;
import com.alquilaria.model.Inquilino;
import com.alquilaria.util.InputUtil;

public class MenuInquilino {

    private InquilinoController controller;

    public MenuInquilino() {
        controller = new InquilinoController();
    }

    public void mostrar() {

        int opcion;

        do {

            System.out.println("\n===== INQUILINOS =====");
            System.out.println("1. Crear");
            System.out.println("2. Consultar");
            System.out.println("3. Modificar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");

            opcion = InputUtil.leerInt("Opción: ");

            switch (opcion) {

                case 1 -> crear();
                case 2 -> consultar();
                case 3 -> modificar();
                case 4 -> eliminar();
            }

        } while (opcion != 0);
    }
    // Métodos para cada operación CRUD
    //crear un nuevo inquilino
    private void crear() {
        // Leer los datos del inquilino desde la entrada del usuario
        String dni = InputUtil.leerString("DNI: ");
        String nombre = InputUtil.leerString("Nombre: ");
        String telefono = InputUtil.leerString("Teléfono: ");
        String email = InputUtil.leerString("Email: ");
        boolean mascota = InputUtil.leerBoolean("Tiene mascota");
        // Crear un objeto Inquilino con los datos ingresados
        Inquilino inquilino =
                new Inquilino(0, dni, nombre, telefono, email, mascota);

        System.out.println(controller.crear(inquilino));
    }
        // Consultar un inquilino por su ID
    private void consultar() {

        int id = InputUtil.leerInt("ID: ");

        Inquilino inquilino = controller.consultar(id);
        // Mostrar la información del inquilino o un mensaje si no existe
        if (inquilino == null)
            System.out.println("No existe");
        else
            System.out.println(inquilino);
    }
    // Modificar los datos de un inquilino existente
    private void modificar() {

        int id = InputUtil.leerInt("ID: ");
        String dni = InputUtil.leerString("DNI: ");
        String nombre = InputUtil.leerString("Nombre: ");
        String telefono = InputUtil.leerString("Teléfono: ");
        String email = InputUtil.leerString("Email: ");
        boolean mascota = InputUtil.leerBoolean("Tiene mascota");
        // Crear un objeto Inquilino con los datos ingresados, incluyendo el ID
        Inquilino inquilino =
                new Inquilino(id, dni, nombre, telefono, email, mascota);

        System.out.println(controller.modificar(inquilino));
    }
    // Eliminar un inquilino por su ID
    private void eliminar() {

        int id = InputUtil.leerInt("ID: ");

        System.out.println(controller.eliminar(id));
    }
}