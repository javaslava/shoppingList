package com.javaguru.shoppinglist.service.converting;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class PriceConverterTest {
    private PriceConverter victim = new PriceConverter();

    @Test
    public void shouldFilterPrice() {
        BigDecimal result = victim.priceFilter("25.0999");
        assertEquals(0, (new BigDecimal("25.1")).compareTo(result));
    }
}