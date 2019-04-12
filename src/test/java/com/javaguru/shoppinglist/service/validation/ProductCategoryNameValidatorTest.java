package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidation.ProductCategoryNameValidationRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ProductCategoryNameValidatorTest {
    @Rule
    public final ExpectedException expectation = ExpectedException.none();
    ProductCategoryNameValidationRule victim = new ProductCategoryNameValidationRule();

    @Test
    public void shouldNotThrowException() {
        Product fake = createFake("FRUITS");
        victim.validate(fake);
    }

    private Product createFake(String name) {
        Product product = new Product();
        product.setCategory(name);
        return product;
    }
}