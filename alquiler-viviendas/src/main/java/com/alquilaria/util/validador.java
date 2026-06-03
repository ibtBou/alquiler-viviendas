package com.alquilaria.util;

import java.math.BigDecimal;
import java.time.LocalDate;

public class validador {

    private validador() {
    }

    public static boolean dniValido(String dni) {
        return dni != null && dni.matches("\\d{8}[A-Za-z]");
    }

    public static boolean emailValido(String email) {
        return email != null
                && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public static boolean telefonoValido(String telefono) {
        return telefono != null
                && telefono.matches("\\d{9}");
    }

    public static boolean nombreValido(String nombre) {
        return nombre != null
                && nombre.trim().length() >= 2;
    }

    public static boolean positivo(int numero) {
        return numero > 0;
    }

    public static boolean positivo(BigDecimal numero) {
        return numero != null
                && numero.compareTo(BigDecimal.ZERO) > 0;
    }

    public static boolean rangoFechasValido(LocalDate inicio,
                                            LocalDate fin) {
        return inicio != null
                && fin != null
                && !fin.isBefore(inicio);
    }
    public static boolean codigoViviendaValido(String codigo) {
    return codigo != null
            && codigo.matches("[A-Za-z0-9]{3,10}");
}

public static boolean tipoViviendaValido(int tipo) {
    return tipo >= 1 && tipo <= 3;
}

public static boolean direccionValida(String direccion) {
    return direccion != null
            && direccion.trim().length() >= 5;
}

public static boolean superficieValida(int superficie) {
    return superficie > 0;
}

public static boolean descripcionValida(String descripcion) {
    return descripcion != null
            && descripcion.trim().length() >= 5;
}
}