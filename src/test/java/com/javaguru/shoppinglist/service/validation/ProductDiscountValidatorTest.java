package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidation.ProductDiscountValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

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
        victim.validate(fake);
    }

    private Product createFake(BigDecimal discount) {
        Product product = new Product();
        product.setDiscount(discount);
        return product;
    }
}