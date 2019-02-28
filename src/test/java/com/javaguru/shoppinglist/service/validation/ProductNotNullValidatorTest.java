package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;

public class ProductNotNullValidatorTest {

    @Rule
    public final ExpectedException expectation = ExpectedException.none();
    private ProductNotNullValidator victim = new ProductNotNullValidator();

    @Test
    public void shouldThrowException() {
        Product fake = null;
        Optional<Product> opt = Optional.ofNullable(fake);
        expectation.expect(ValidationException.class);
        expectation.expectMessage("No such named product");
        victim.validate(opt);
    }
}