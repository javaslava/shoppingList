package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.repository.CartManagerRepository;

public class CartManagerValidator {

    private CartManagerRepository cartManager;

    public CartManagerValidator(CartManagerRepository cartManager) {
        this.cartManager = cartManager;
    }

    public void validate(int response) {
        if (response < 1 || response > cartManager.getCartManagerSize()) {
            throw new ValidationException("Not available choice ");
        }
    }
}
