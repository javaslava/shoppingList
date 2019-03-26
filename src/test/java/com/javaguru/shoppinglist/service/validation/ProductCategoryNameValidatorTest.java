package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Category;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidation.ProductCategoryNameValidationRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

public class ProductCategoryNameValidatorTest {
    @Rule
    public final ExpectedException expectation = ExpectedException.none();
    ProductCategoryNameValidationRule victim = new ProductCategoryNameValidationRule();
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
        victim.validate(fake);
    }

    private Product createFake(String name) {
        Product product = new Product();
        product.setCategory(name);
        return product;
    }
}