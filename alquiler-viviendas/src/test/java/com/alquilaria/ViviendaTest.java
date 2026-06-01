package com.alquilaria;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.alquilaria.model.Vivienda;

public class ViviendaTest {

    @Test
    public void testCrearVivienda() {

        Vivienda vivienda =
                new Vivienda(
                        1,
                        2,
                        "V001",
                        1,
                        "Calle Mayor",
                        100,
                        new BigDecimal("750"),
                        "Apartamento",
                        true
                );

        assertEquals(1, vivienda.getId());
        assertEquals(2, vivienda.getIdPropietario());
        assertEquals("V001", vivienda.getCodigo());
        assertTrue(vivienda.isAceptaMascota());
    }
}
