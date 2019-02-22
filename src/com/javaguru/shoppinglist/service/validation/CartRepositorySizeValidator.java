package com.javaguru.shoppinglist.service.validation;

public class CartRepositorySizeValidator {
    public void validate(int cartRepositorySize) {
        if (cartRepositorySize == 0) {
            throw new ValidationException("NO ONE CART IS CREATED");
        }
    }
}
