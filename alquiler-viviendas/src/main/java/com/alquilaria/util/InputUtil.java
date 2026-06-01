package com.alquilaria.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class InputUtil {

    private static final Scanner sc = new Scanner(System.in);

    private InputUtil() {
    }

    public static String leerString(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    public static int leerInt(String mensaje) {

    while (true) {

        try {

            System.out.print(mensaje);

            String texto = sc.nextLine();

            return Integer.parseInt(texto);

        } catch (NumberFormatException e) {

            System.out.println("Debes introducir un número.");
        }
    }
}

    public static BigDecimal leerDecimal(String mensaje) {
        System.out.print(mensaje);
        return new BigDecimal(sc.nextLine());
    }

    public static boolean leerBoolean(String mensaje) {
        System.out.print(mensaje + " (true/false): ");
        return Boolean.parseBoolean(sc.nextLine());
    }

    public static LocalDate leerFecha(String mensaje) {
        System.out.print(mensaje + " (yyyy-MM-dd): ");
        return LocalDate.parse(sc.nextLine());
    }
}