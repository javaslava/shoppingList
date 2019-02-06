package com.javaguru.shoppinglist.validators;

import java.math.BigDecimal;

public class DiscountValidator {
    public void validate(BigDecimal discount) {

        if (discount.signum() == -1) {
            throw new ValidationException("Product's discount must be positive number or zero");
        }
        BigDecimal bdFactor = new BigDecimal("100");
        if (discount.compareTo(bdFactor) == 1) {
            throw new ValidationException("Product's discount possible 0 - 100% ");
        }
    }

}
