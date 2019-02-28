package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductDiscountValidatorTest {
    @Rule
    public final ExpectedException expectation = ExpectedException.none();
    ProductDiscountValidator victim = new ProductDiscountValidator();
    private Product fake;

    @Test
    public void shouldThrowExceptionOfNegativeValue() {
        fake = createFake(new BigDecimal(-5.00));
        expectation.expect(ValidationException.class);
        expectation.expectMessage("Product's discount must be positive number or zero");
        victim.validate(fake);
    }

    @Test
    public void shouldThrowExceptionOfOverValue() {
        fake = createFake(new BigDecimal(120));
        expectation.expect(ValidationException.class);
        expectation.expectMessage("Product's discount possible 0 - 100% ");
        victim.validate(fake);
    }

    @Test
    public void shouldNotThrowException() {
        fake = createFake(new BigDecimal(50.00));
        try {
            victim.validate(fake);
        } catch (Exception ex) {
            assertNull(ex);
        }
    }

    private Product createFake(BigDecimal discount) {
        Product product = new Product();
        product.setDiscount(discount);
        return product;
    }
}