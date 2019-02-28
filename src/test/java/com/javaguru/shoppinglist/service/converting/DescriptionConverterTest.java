package com.javaguru.shoppinglist.service.converting;

import org.junit.Test;

import static org.junit.Assert.*;

public class DescriptionConverterTest {
    private DescriptionConverter victim = new DescriptionConverter();

    @Test
    public void shouldConvertDescription() {
        String result = victim.descriptionFilter("");
        assertEquals("NO DESCRIPTION", result);
    }
}