package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.ShoppingCart;

public class CartNameLengthValidator implements CartValidationRule {

    private final int MIN_NAME_LENGTH = 2;
    private final int MAX_NAME_LENGTH = 15;

    @Override
    public void validate(ShoppingCart cart) {
        checkNotNull(cart);
        if (cart.getName().length() < MIN_NAME_LENGTH || cart.getName().length() > MAX_NAME_LENGTH) {
            throw new ValidationException("The name's length have to be in " +
                    "range " + MIN_NAME_LENGTH + " - " + MAX_NAME_LENGTH + " " +
                    "symbols");
        }
    }
}
