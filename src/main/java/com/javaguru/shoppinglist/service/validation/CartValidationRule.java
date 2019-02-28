package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.ShoppingCart;

public interface CartValidationRule {

    void validate(ShoppingCart cart);

    default void checkNotNull(ShoppingCart cart) {
        if (cart == null) {
            throw new ValidationException("Cart must be not null");
        }
    }
}
