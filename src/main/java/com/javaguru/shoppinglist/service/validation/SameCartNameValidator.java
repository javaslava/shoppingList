package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.CartRepository;

public class SameCartNameValidator {

    public void checkForSameCartName(ShoppingCart cart, CartRepository repo) {
        if (repo.checkForCartByName(cart.getName())) {
            throw new ValidationException("Database contains the cart with the same name.");
        }
    }
}
