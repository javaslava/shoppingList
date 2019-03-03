package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertNull;

public class ProductNameNotNullValidatorTest {
    @Rule
    public final ExpectedException expectation = ExpectedException.none();
    private ProductNameNotNullValidator victim = new ProductNameNotNullValidator();
    private Product fake;

    @Test
    public void shouldThrowValidationException() {
        fake = product(null);
        expectation.expect(ValidationException.class);
        expectation.expectMessage("Product name must be not null");
        victim.validate(fake);
    }

    @Test
    public void shouldNotThrowException() {
        fake = product("JavaGuru");
        try {
            victim.validate(fake);
        } catch (Exception ex) {
            assertNull(ex);
        }
    }

    private Product product(String name) {
        Product product = new Product();
        product.setName(name);
        return product;
    }
}