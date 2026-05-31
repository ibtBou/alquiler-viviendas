package com.alquilaria.model;

import java.math.BigDecimal;

public class Vivienda {
    private int id;
    private int idPropietario;
    private String codigo;
    private int tipo;
    private String direccion;
    private int superficie;
    private BigDecimal precioMes;
    private String descripcion;
    private boolean aceptaMascota;

    public Vivienda(int id, int idPropietario, String codigo, int tipo, String direccion, int superficie,
                    BigDecimal precioMes, String descripcion, boolean aceptaMascota) {
        this.id = id;
        this.idPropietario = idPropietario;
        this.codigo = codigo;
        this.tipo = tipo;
        this.direccion = direccion;
        this.superficie = superficie;
        this.precioMes = precioMes;
        this.descripcion = descripcion;
        this.aceptaMascota = aceptaMascota;
    }

    public int getId() {
        return id;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getTipo() {
        return tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getSuperficie() {
        return superficie;
    }

    public BigDecimal getPrecioMes() {
        return precioMes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isAceptaMascota() {
        return aceptaMascota;
    }

    @Override
    public String toString() {
        return "Vivienda{id=%d, idPropietario=%d, codigo='%s', tipo=%d, direccion='%s', superficie=%d, precioMes=%s, aceptaMascota=%s}"
                .formatted(id, idPropietario, codigo, tipo, direccion, superficie, precioMes, aceptaMascota);
    }
}
