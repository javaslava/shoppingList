package com.javaguru.shoppinglist.service.converting;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DiscountConverterTest {
    private DiscountConverter victim = new DiscountConverter();

    @Test
    public void shouldFilterDiscount() {
        BigDecimal result = victim.discountFilter("25,9999", new BigDecimal(25));
        assertEquals(0, (new BigDecimal("26")).compareTo(result));
    }

    @Test
    public void shouldFilterDiscountMinPrice() {
        BigDecimal result = victim.discountFilter("25,9999", new BigDecimal(15));
        assertEquals(0, (new BigDecimal("0")).compareTo(result));
    }
}