package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.CartRepository;

public class CartUniqueNameValidator implements CartValidationRule {

    private final CartRepository cartRepo;

    public CartUniqueNameValidator(CartRepository cartRepo) {
        this.cartRepo = cartRepo;
    }

    @Override
    public void validate(ShoppingCart cart) {
        if (cartRepo.checkForCartByName(cart.getName())) {
            throw new ValidationException("Database contains the cart with the same name.");
        }
    }
}
