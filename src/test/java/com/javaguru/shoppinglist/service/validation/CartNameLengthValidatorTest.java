package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CartNameLengthValidatorTest {
    @Rule
    public final ExpectedException expectation = ExpectedException.none();
    CartNameLengthValidator victim = new CartNameLengthValidator();
    private ShoppingCart fake;

    @Test
    public void shouldThrowExceptionTooShortName() {
        fake = createFake("J");
        expectation.expect(ValidationException.class);
        expectation.expectMessage("The name's length have to be in range 3 - 15 symbols");
        victim.validate(fake);
    }

    @Test
    public void shouldThrowExceptionTooLongName() {
        fake = createFake("JavaCart for knowledge");
        expectation.expect(ValidationException.class);
        expectation.expectMessage("The name's length have to be in range 3 - 15 symbols");
        victim.validate(fake);
    }

    @Test
    public void shouldNotThrowException() {
        fake = createFake("JavaCart");
        victim.validate(fake);
    }

    private ShoppingCart createFake(String name) {
        ShoppingCart cart = new ShoppingCart();
        cart.setName(name);
        return cart;
    }
}