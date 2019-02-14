package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;

public interface ProductValidationRule {

    void validate(Product product, ProductRepository repo);

    default void checkNotNull(Product product) {
        if (product == null) {
            throw new ValidationException("Product must be not null");
        }
    }
}
