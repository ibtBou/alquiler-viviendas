package com.alquilaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.alquilaria.model.Inquilino;

public class InquilinoTest {

    @Test
    public void testCrearInquilino() {

        Inquilino inquilino =
                new Inquilino(
                        1,
                        "12345678A",
                        "Carlos López",
                        "644567567",
                        "carlos@gmail.com",
                        true
                );

        assertEquals(1, inquilino.getId());
        assertTrue(inquilino.isTieneMascota());
        assertEquals("Carlos López", inquilino.getNombre());
    }
}