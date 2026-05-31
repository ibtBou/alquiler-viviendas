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
        System.out.print(mensaje);
        return Integer.parseInt(sc.nextLine());
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