package com.javaguru.shoppinglist.service.validation.CartValidation;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.CartRepository;
import com.javaguru.shoppinglist.service.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartUniqueNameValidator implements CartValidationRule {

    private final CartRepository cartRepo;

    @Autowired
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
