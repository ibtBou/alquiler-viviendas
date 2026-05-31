package com.alquilaria.model;

public class Inquilino {
    private int id;
    private String dni;
    private String nombre;
    private String telefono;
    private String email;
    private boolean tieneMascota;

    public Inquilino(int id, String dni, String nombre, String telefono, String email, boolean tieneMascota) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.tieneMascota = tieneMascota;
    }

    public int getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public boolean isTieneMascota() {
        return tieneMascota;
    }

    @Override
    public String toString() {
        return "Inquilino{id=%d, dni='%s', nombre='%s', telefono='%s', email='%s', tieneMascota=%s}"
                .formatted(id, dni, nombre, telefono, email, tieneMascota);
    }
}
