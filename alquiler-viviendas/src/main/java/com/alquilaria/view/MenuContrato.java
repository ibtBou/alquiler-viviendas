package com.alquilaria.view;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.alquilaria.controller.ContratoController;
import com.alquilaria.model.Contrato;
import com.alquilaria.service.ExportacionService;
import com.alquilaria.util.InputUtil;

public class MenuContrato {

    private ContratoController controller;
    private ExportacionService exportacionService;

    public MenuContrato() {
        controller = new ContratoController();
        exportacionService = new ExportacionService();
    }

    public void mostrar() {

        int opcion;

        do {

            System.out.println("\n===== CONTRATOS =====");
            System.out.println("1. Crear");
            System.out.println("2. Consultar");
            System.out.println("3. Modificar");
            System.out.println("4. Eliminar");
            System.out.println("5. Exportar contratos a JSON");
            System.out.println("6. Exportar contratos a CSV");
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
    // Crear un nuevo contrato
    private void crear() {
        // Solicitar los datos del contrato al usuario
        int vivienda = InputUtil.leerInt("ID vivienda: ");
        int inquilino = InputUtil.leerInt("ID inquilino: ");

        LocalDate inicio = InputUtil.leerFecha("Fecha inicio");
        LocalDate fin = InputUtil.leerFecha("Fecha fin");

        BigDecimal precio = InputUtil.leerDecimal("Precio");

        int estado = InputUtil.leerInt("Estado (1 Pendiente, 2 Vencido, 3 Activo): ");
        // Crear un objeto Contrato con los datos ingresados
        Contrato contrato = new Contrato(
                0,
                vivienda,
                inquilino,
                inicio,
                fin,
                precio,
                estado);

        System.out.println(controller.crear(contrato));
    }
    // Consultar un contrato por su ID
    private void consultar() {

        int id = InputUtil.leerInt("ID: ");

        Contrato contrato = controller.consultar(id);
        // Mostrar los detalles del contrato o un mensaje si no existe
        if (contrato == null)
            System.out.println("No existe");
        else
            System.out.println(contrato);
    }

    // Modificar un contrato existente
    private void modificar() {
        // Solicitar los datos del contrato al usuario
        int id = InputUtil.leerInt("ID: ");
        int vivienda = InputUtil.leerInt("ID vivienda: ");
        int inquilino = InputUtil.leerInt("ID inquilino: ");

        LocalDate inicio = InputUtil.leerFecha("Fecha inicio");
        LocalDate fin = InputUtil.leerFecha("Fecha fin");

        BigDecimal precio = InputUtil.leerDecimal("Precio");

        int estado = InputUtil.leerInt("Estado: ");
        // Crear un objeto Contrato con los datos ingresados
        Contrato contrato = new Contrato(
                id,
                vivienda,
                inquilino,
                inicio,
                fin,
                precio,
                estado);

        System.out.println(controller.modificar(contrato));
    }

    // Eliminar un contrato por su ID
    private void eliminar() {

        int id = InputUtil.leerInt("ID: ");

        System.out.println(controller.eliminar(id));
    }
    private void exportarJSON() {

        System.out.println(
                exportacionService.exportarContratosJSON()
        );
}
    private void exportarCSV() {

        System.out.println(
                exportacionService.exportarContratosCSV()
        );
}
}