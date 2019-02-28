package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class CartNameLengthValidatorTest {
    @Rule
    public final ExpectedException expectation = ExpectedException.none();
    CartNameLengthValidator victim = new CartNameLengthValidator();
    private ShoppingCart fake;

    @Test
    public void shouldThrowExceptionTooShortName() {
        fake = createFake("J");
        expectation.expect(ValidationException.class);
        expectation.expectMessage("The name's length have to be in range 2 - 15 symbols");
        victim.validate(fake);
    }

    @Test
    public void shouldThrowExceptionTooLongName() {
        fake = createFake("JavaCart for knowledge");
        expectation.expect(ValidationException.class);
        expectation.expectMessage("The name's length have to be in range 2 - 15 symbols");
        victim.validate(fake);
    }

    @Test
    public void shouldNotThrowException() {
        fake = createFake("JavaCart");
        try {
            victim.validate(fake);
        } catch (Exception ex) {
            assertNull(ex);
        }
    }

    private ShoppingCart createFake(String name) {
        ShoppingCart cart = new ShoppingCart();
        cart.setName(name);
        return cart;
    }
}