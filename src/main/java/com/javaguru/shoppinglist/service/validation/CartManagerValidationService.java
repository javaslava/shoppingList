package com.javaguru.shoppinglist.service.validation;

import java.util.Set;

public class CartManagerValidationService {
    private Set<CartManagerValidationRule> validationRules;

    public CartManagerValidationService(Set<CartManagerValidationRule> cartManagerRules) {
        this.validationRules = cartManagerRules;
    }

    public void validate(int response) {
        validationRules.forEach(s -> s.validate(response));
    }
}
