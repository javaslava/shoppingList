package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidation.ProductDescriptionValidationRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ProductDescriptionValidatorTest {
    @Rule
    public final ExpectedException expectation = ExpectedException.none();
    ProductDescriptionValidationRule victim = new ProductDescriptionValidationRule();
    private Product fake;

    @Test
    public void shouldThrowExceptionTooShortDescription() {
        fake = createFake("NO");
        expectation.expect(ValidationException.class);
        expectation.expectMessage("Description's length have to be in range 10 - 50 symbols");
        victim.validate(fake);
    }

    @Test
    public void shouldThrowExceptionTooLongDescription() {
        fake = createFake("This product description is too very very very long!");
        expectation.expect(ValidationException.class);
        expectation.expectMessage("Description's length have to be in range 10 - 50 symbols");
        victim.validate(fake);
    }

    @Test
    public void shouldNotThrowException() {
        fake = createFake("Nice description");
        victim.validate(fake);
    }

    private Product createFake(String name) {
        Product product = new Product();
        product.setDescription(name);
        return product;
    }

}