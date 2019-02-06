package com.javaguru.shoppinglist.validators;

import java.math.BigDecimal;

public class PriceValidator {
    public void validate(BigDecimal price) {
        if (price.signum() != 1) {
            throw new ValidationException("Product's price must be a positive number");
        }
    }
}
