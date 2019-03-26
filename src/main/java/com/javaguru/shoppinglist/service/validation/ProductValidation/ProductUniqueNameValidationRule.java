package com.javaguru.shoppinglist.service.validation.ProductValidation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductUniqueNameValidationRule implements ProductValidationRule {
    private final ProductRepository productRepository;

    @Autowired
    public ProductUniqueNameValidationRule(ProductRepository repo) {
        this.productRepository = repo;
    }

    public void validate(Product product) {
        if (productRepository.existsByName(product)) {
            throw new ValidationException("Database contains the product with the same name.");
        }
    }
}
