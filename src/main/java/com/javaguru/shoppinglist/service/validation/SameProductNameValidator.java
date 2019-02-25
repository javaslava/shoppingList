package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;

public class SameProductNameValidator {
    public void checkForSameProductName(Product product, ProductRepository repo) {
        for (Product element : repo.getDatabaseValues()) {
            if (element.getName().equals(product.getName())) {
                throw new ValidationException("Database contains the product with the same name.");
            }
        }
    }
}
