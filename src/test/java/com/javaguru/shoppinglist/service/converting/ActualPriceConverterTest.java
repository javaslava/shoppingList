package com.javaguru.shoppinglist.service.converting;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ActualPriceConverterTest {
    private ActualPriceConverter victim = new ActualPriceConverter();

    @Test
    public void shouldCalculateActualProductPrice() {
        BigDecimal result = victim.actualPriceCalculator(new BigDecimal("25"), new BigDecimal("50"));
        assertEquals(0, new BigDecimal("12.5").compareTo(result));
    }
}