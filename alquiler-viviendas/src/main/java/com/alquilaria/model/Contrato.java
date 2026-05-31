package com.alquilaria.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Contrato {
    private int idContrato;
    private int idVivienda;
    private int idInquilino;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private BigDecimal precio;
    private int estado;

    public Contrato(int idContrato, int idVivienda, int idInquilino, LocalDate fechaInicio,
                    LocalDate fechaFin, BigDecimal precio, int estado) {
        this.idContrato = idContrato;
        this.idVivienda = idVivienda;
        this.idInquilino = idInquilino;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precio = precio;
        this.estado = estado;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public int getIdVivienda() {
        return idVivienda;
    }

    public int getIdInquilino() {
        return idInquilino;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public int getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Contrato{id=%d, vivienda=%d, inquilino=%d, inicio=%s, fin=%s, precio=%s, estado=%d}"
                .formatted(idContrato, idVivienda, idInquilino, fechaInicio, fechaFin, precio, estado);
    }
}
