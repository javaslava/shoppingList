package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;


import java.math.BigDecimal;

public class ProductDiscountValidator implements ProductValidationRule {
    private final BigDecimal MAX_DISCOUNT_LIMIT = new BigDecimal("100");

    @Override
    public void validate(Product product) {
        if (product.getDiscount().signum() == -1) {
            throw new ValidationException("Product's discount must be positive number or zero");
        }
        if (product.getDiscount().compareTo(MAX_DISCOUNT_LIMIT) > 0) {
            throw new ValidationException("Product's discount possible 0 - " + MAX_DISCOUNT_LIMIT + "% ");
        }
    }
}
