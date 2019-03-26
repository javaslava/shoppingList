package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidation.ProductNameNotNullValidationRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ProductNameNotNullValidatorTest {
    @Rule
    public final ExpectedException expectation = ExpectedException.none();
    private ProductNameNotNullValidationRule victim = new ProductNameNotNullValidationRule();
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
        victim.validate(fake);
    }

    private Product product(String name) {
        Product product = new Product();
        product.setName(name);
        return product;
    }
}