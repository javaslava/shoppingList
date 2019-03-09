package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;

public class ProductUniqueNameValidator implements ProductValidationRule {
    private final ProductRepository productRepository;

    public ProductUniqueNameValidator(ProductRepository repo) {
        this.productRepository = repo;
    }

    public void validate(Product product) {
        if (productRepository.existsByName(product)) {
            throw new ValidationException("Database contains the product with the same name.");
        }
    }
}
