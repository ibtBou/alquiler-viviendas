package com.alquilaria;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.alquilaria.model.Propietario;

public class PropietarioTest {

    @Test
    public void testCrearPropietario() {

        Propietario propietario =
                new Propietario(
                        1,
                        "12345678A",
                        "Juan Pérez",
                        "600123123",
                        "juan@gmail.com"
                );

        assertEquals(1, propietario.getId());
        assertEquals("12345678A", propietario.getDni());
        assertEquals("Juan Pérez", propietario.getNombre());
        assertEquals("600123123", propietario.getTelefono());
        assertEquals("juan@gmail.com", propietario.getEmail());
    }
}