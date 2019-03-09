package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ProductNameLengthValidatorTest {
    @Rule
    public final ExpectedException expectation = ExpectedException.none();
    ProductNameLengthValidator victim = new ProductNameLengthValidator();
    private Product fake;

    @Test
    public void shouldThrowExceptionTooShortName() {
        fake = createFake("JG");
        expectation.expect(ValidationException.class);
        expectation.expectMessage("The name's length have to be in range 3 - 32 symbols");
        victim.validate(fake);
    }

    @Test
    public void shouldThrowExceptionTooLongName() {
        fake = createFake("JavaGuru_is_the_most_excited_in_Java_learning");
        expectation.expect(ValidationException.class);
        expectation.expectMessage("The name's length have to be in range 3 - 32 symbols");
        victim.validate(fake);
    }

    @Test
    public void shouldNotThrowException() {
        fake = createFake("JavaGuru");
        victim.validate(fake);
    }

    private Product createFake(String name) {
        Product product = new Product();
        product.setName(name);
        return product;
    }
}