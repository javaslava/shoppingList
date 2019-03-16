package com.javaguru.shoppinglist.service.validation.CartManagerValidation;

import com.javaguru.shoppinglist.repository.CartManagerRepository;
import com.javaguru.shoppinglist.service.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartManagerValidator implements CartManagerValidationRule {

    private CartManagerRepository cartManager;

    @Autowired
    public CartManagerValidator(CartManagerRepository cartManager) {
        this.cartManager = cartManager;
    }

    @Override
    public void validate(int response) {
        if (response < 1 || response > cartManager.getCartManagerSize()) {
            throw new ValidationException("Not available choice ");
        }
    }
}
