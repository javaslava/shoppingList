package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;

import java.math.BigDecimal;

public class DiscountValidator implements ProductValidationRule {
    @Override
    public void validate(Product product, ProductRepository repo) {
        if (product.getDiscount().signum() == -1) {
            throw new ValidationException("Product's discount must be positive number or zero");
        }
        BigDecimal bdFactor = new BigDecimal(product.getMaxDiscountLimit());
        if (product.getDiscount().compareTo(bdFactor) > 0) {
            throw new ValidationException("Product's discount possible 0 - "+ product.getMaxDiscountLimit() +"% ");
        }
    }
}
