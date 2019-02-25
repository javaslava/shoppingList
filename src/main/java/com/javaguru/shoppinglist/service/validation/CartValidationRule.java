package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.ShoppingCart;

public interface CartValidationRule {

    void validate(ShoppingCart cart);

    default void checkNotNull(ShoppingCart cart) {
        if (cart == null) {
            throw new ValidationException("Product must be not null");
        }
    }
}
