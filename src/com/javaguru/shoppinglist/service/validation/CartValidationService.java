package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.ShoppingCart;

import java.util.HashSet;
import java.util.Set;

public class CartValidationService {

    private Set<CartValidationRule> validationRules = new HashSet<>();

    public CartValidationService() {
        validationRules.add(new CartNameLengthValidator());
    }

    public void validate(ShoppingCart cart) {
        validationRules.forEach(s -> s.validate(cart));
    }
}
