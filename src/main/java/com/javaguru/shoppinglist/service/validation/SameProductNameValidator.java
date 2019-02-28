package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;

public class SameProductNameValidator {
    private final ProductRepository repo;

    public SameProductNameValidator(ProductRepository repo) {
        this.repo = repo;
    }

    public void checkForSameProductName(Product product) {
        if (repo.existsByName(product)) {
            throw new ValidationException("Database contains the product with the same name.");
        }
    }
}
