package com.javaguru.shoppinglist.service.validation.CartManagerValidation;

import com.javaguru.shoppinglist.console.cartManager.CartManagerMenu;
import com.javaguru.shoppinglist.service.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartManagerValidator implements CartManagerValidationRule {

    private CartManagerMenu cartManager;

    @Autowired
    public CartManagerValidator(CartManagerMenu cartManager) {
        this.cartManager = cartManager;
    }

    @Override
    public void validate(int response) {
        if (response < 1 || response > cartManager.getCartManagerSize()) {
            throw new ValidationException("Not available choice ");
        }
    }
}
