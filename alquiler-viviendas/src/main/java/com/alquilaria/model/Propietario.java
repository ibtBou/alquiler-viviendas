package com.alquilaria.model;

public class Propietario {
    private int id;
    private String dni;
    private String nombre;
    private String telefono;
    private String email;

    public Propietario(int id, String dni, String nombre, String telefono, String email) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
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

    @Override
    public String toString() {
        return "Propietario{id=%d, dni='%s', nombre='%s', telefono='%s', email='%s'}"
                .formatted(id, dni, nombre, telefono, email);
    }
}
