package com.alquilaria.model;
/**
 * Representa un tipo de estado para una vivienda.
 *
 * @author Betty
 * @version 1.0
 */

public class TipoEstado {

    private int id;
    private String nombre;

    public TipoEstado() {
    }

    public TipoEstado(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}