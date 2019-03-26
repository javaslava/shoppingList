package com.javaguru.shoppinglist.service.validation.ProductValidation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ValidationException;

public interface ProductValidationRule {

    void validate(Product product);

    default void checkNotNull(Product product) {
        if (product == null) {
            throw new ValidationException("Product must be not null");
        }
    }
}
