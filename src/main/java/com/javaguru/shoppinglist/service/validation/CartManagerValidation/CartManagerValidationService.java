package com.javaguru.shoppinglist.service.validation.CartManagerValidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CartManagerValidationService {
    private Set<CartManagerValidationRule> validationRules;

    @Autowired
    public CartManagerValidationService(Set<CartManagerValidationRule> cartManagerRules) {
        this.validationRules = cartManagerRules;
    }

    public void validate(int response) {
        validationRules.forEach(s -> s.validate(response));
    }
}
