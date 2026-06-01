package com.alquilaria;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.alquilaria.model.Contrato;

public class ContratoTest {

    @Test
    public void testCrearContrato() {

        Contrato contrato =
                new Contrato(
                        1,
                        1,
                        1,
                        LocalDate.of(2025,1,1),
                        LocalDate.of(2025,12,31),
                        new BigDecimal("700"),
                        3
                );

        assertEquals(1, contrato.getIdContrato());
        assertEquals(1, contrato.getIdVivienda());
        assertEquals(1, contrato.getIdInquilino());
        assertEquals(
                new BigDecimal("700"),
                contrato.getPrecio()
        );
    }
}