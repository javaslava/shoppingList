package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Category;
import com.javaguru.shoppinglist.domain.Product;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ProductCategoryNameValidatorTest {
    @Rule
    public final ExpectedException expectation = ExpectedException.none();
    ProductCategoryNameValidator victim = new ProductCategoryNameValidator();
    private Product fake;

    @Test
    public void shouldThrowException() {
        fake = createFake("java");
        expectation.expect(ValidationException.class);
        expectation.expectMessage("Please, make a choice from: " + Arrays.toString(Category.values()));
        victim.validate(fake);
    }

    @Test
    public void shouldNotThrowException() {
        fake = createFake("fruits");
        try {
            victim.validate(fake);
        } catch (Exception ex) {
            assertNull(ex);
        }
    }

    private Product createFake(String name) {
        Product product = new Product();
        product.setCategory(name);
        return product;
    }
}