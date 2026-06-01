package com.alquilaria.view;

import java.math.BigDecimal;

import com.alquilaria.controller.ViviendaController;
import com.alquilaria.model.Vivienda;
import com.alquilaria.service.ExportacionService;
import com.alquilaria.util.InputUtil;

public class MenuVivienda {
    private ExportacionService exportacionService;

    private ViviendaController controller;

    public MenuVivienda() {
        controller = new ViviendaController();
        exportacionService = new ExportacionService();
    }

    public void mostrar() {

        int opcion;

        do {

            System.out.println("\n===== VIVIENDAS =====");
            System.out.println("1. Crear");
            System.out.println("2. Consultar");
            System.out.println("3. Modificar");
            System.out.println("4. Eliminar");
            System.out.println("5. Exportar viviendas a JSON");
            System.out.println("6. Exportar viviendas a CSV");
            System.out.println("0. Volver");

            opcion = InputUtil.leerInt("Opción: ");

            switch (opcion) {

                case 1 -> crear();
                case 2 -> consultar();
                case 3 -> modificar();
                case 4 -> eliminar();
                case 5 -> exportarJSON();
                case 6 -> exportarCSV();
            }

        } while (opcion != 0);
    }
    // Métodos para cada operación CRUD
    // Método para crear una nueva vivienda
    private void crear() {

        int propietario = InputUtil.leerInt("ID propietario: ");
        String codigo = InputUtil.leerString("Código: ");
        int tipo = InputUtil.leerInt("Tipo (1 Apartamento, 2 Ático, 3 Casa): ");
        String direccion = InputUtil.leerString("Dirección: ");
        int superficie = InputUtil.leerInt("Superficie: ");
        BigDecimal precio = InputUtil.leerDecimal("Precio mes: ");
        String descripcion = InputUtil.leerString("Descripción: ");
        boolean mascota = InputUtil.leerBoolean("Acepta mascota");
        // Crear un objeto Vivienda con los datos ingresados
        //id se asigna automáticamente en la base de datos, por lo que se puede usar 0 o no incluirlo en el constructor
        Vivienda vivienda = new Vivienda(
                0,
                propietario,
                codigo,
                tipo,
                direccion,
                superficie,
                precio,
                descripcion,
                mascota);

        System.out.println(controller.crear(vivienda));
    }
    // Método para consultar una vivienda por su ID
    private void consultar() {

        int id = InputUtil.leerInt("ID: ");

        Vivienda vivienda = controller.consultar(id);
        // Mostrar los detalles de la vivienda o un mensaje si no existe
        if (vivienda == null)
            System.out.println("No existe");
        else
            System.out.println(vivienda);
    }
    // Método para modificar los datos de una vivienda existente
    private void modificar() {

        int id = InputUtil.leerInt("ID: ");
        int propietario = InputUtil.leerInt("ID propietario: ");
        String codigo = InputUtil.leerString("Código: ");
        int tipo = InputUtil.leerInt("Tipo: ");
        String direccion = InputUtil.leerString("Dirección: ");
        int superficie = InputUtil.leerInt("Superficie: ");
        BigDecimal precio = InputUtil.leerDecimal("Precio mes: ");
        String descripcion = InputUtil.leerString("Descripción: ");
        boolean mascota = InputUtil.leerBoolean("Acepta mascota");
        // Crear un objeto Vivienda con los datos ingresados, incluyendo el ID para identificar cuál modificar
        Vivienda vivienda = new Vivienda(
                id,
                propietario,
                codigo,
                tipo,
                direccion,
                superficie,
                precio,
                descripcion,
                mascota);

        System.out.println(controller.modificar(vivienda));
    }
    // Método para eliminar una vivienda por su ID
    private void eliminar() {

        int id = InputUtil.leerInt("ID: ");

        System.out.println(controller.eliminar(id));
    }
    // Método para exportar viviendas a JSON
    private void exportarJSON() {

    System.out.println(
            exportacionService.exportarViviendasJSON()
    );
}
private void exportarCSV() {

    System.out.println(
            exportacionService.exportarViviendasCSV()
    );
}
}