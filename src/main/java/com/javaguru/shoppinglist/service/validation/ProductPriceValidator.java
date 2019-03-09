package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class ProductPriceValidator implements ProductValidationRule {
    @Override
    public void validate(Product product) {
        if (product.getPrice().signum() == -1) {
            throw new ValidationException("Product's price must be a positive number.");
        } else if (product.getPrice().signum() == 0) {
            throw new ValidationException("Product must have a price above zero.");
        }
    }
}
