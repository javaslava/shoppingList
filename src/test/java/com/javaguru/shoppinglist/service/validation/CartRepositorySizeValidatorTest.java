package com.javaguru.shoppinglist.service.validation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CartRepositorySizeValidatorTest {
    @Rule
    public final ExpectedException expectation = ExpectedException.none();
    private CartRepositorySizeValidator victim = new CartRepositorySizeValidator();

    @Test
    public void shouldThrowException() {
        expectation.expect(ValidationException.class);
        expectation.expectMessage("NO ONE CART IS CREATED");
        victim.validate(0);
    }

    @Test
    public void shouldNotThrowException() {
        victim.validate(3);
    }
}