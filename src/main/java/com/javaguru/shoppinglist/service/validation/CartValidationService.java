package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.ShoppingCart;

import java.util.Set;

public class CartValidationService {

    private Set<CartValidationRule> validationRules;

    public CartValidationService(Set<CartValidationRule> validationRules) {
        this.validationRules = validationRules;
    }

    public void validate(ShoppingCart cart) {
        validationRules.forEach(s -> s.validate(cart));
    }
}
