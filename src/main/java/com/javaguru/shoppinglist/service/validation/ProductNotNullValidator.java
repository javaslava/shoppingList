package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import java.util.Optional;

public class ProductNotNullValidator {
    public void validate(Optional<Product> product) {
        if (!product.isPresent()) {
            throw new ValidationException("No such named product");
        }
    }
}
