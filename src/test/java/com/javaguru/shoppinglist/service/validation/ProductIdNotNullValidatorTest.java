package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ProductIdNotNullValidatorTest {
    @Rule
    public final ExpectedException expectation = ExpectedException.none();
    private ProductIdNotNullValidator victim = new ProductIdNotNullValidator();

    @Test
    public void shouldThrowException() {
        Product fake = product(null);
        expectation.expect(IllegalArgumentException.class);
        expectation.expectMessage("Id must be not null");
        victim.validate(fake.getId());
    }

    private Product product(Long id) {
        Product product = new Product();
        product.setId(id);
        return product;
    }
}