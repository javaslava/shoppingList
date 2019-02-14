package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;

public class SameProductNameValidator implements ProductValidationRule{
    @Override
    public void validate(Product product, ProductRepository repo){
        checkNotNull(product);
        for (Product element : repo.getDatabaseValues()) {
            if (element.getName().equals(product.getName())) {
                throw new ValidationException("Database contains the product with the same name.");
            }
        }
    }
}
