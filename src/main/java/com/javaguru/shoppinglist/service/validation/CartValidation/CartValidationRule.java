package com.javaguru.shoppinglist.service.validation.CartValidation;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.service.validation.ValidationException;

public interface CartValidationRule {

    void validate(ShoppingCart cart);

    default void checkNotNull(ShoppingCart cart) {
        if (cart == null) {
            throw new ValidationException("Cart must be not null");
        }
    }
}
