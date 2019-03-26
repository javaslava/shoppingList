package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidation.ProductPriceValidationRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

public class ProductPriceValidatorTest {
    @Rule
    public final ExpectedException expectation = ExpectedException.none();
    private ProductPriceValidationRule victim = new ProductPriceValidationRule();
    private Product fake;

    @Test
    public void shouldThrowExceptionOfNegativeValue() {
        fake = createFake(new BigDecimal(-5.00));
        expectation.expect(ValidationException.class);
        expectation.expectMessage("Product's price must be a positive number.");
        victim.validate(fake);
    }

    @Test
    public void shouldThrowExceptionOfZeroValue() {
        fake = createFake(new BigDecimal(0));
        expectation.expect(ValidationException.class);
        expectation.expectMessage("Product must have a price above zero.");
        victim.validate(fake);
    }

    @Test
    public void shouldNotThrowException() {
        fake = createFake(new BigDecimal(5.00));
        victim.validate(fake);
    }

    private Product createFake(BigDecimal price) {
        Product product = new Product();
        product.setPrice(price);
        return product;
    }
}