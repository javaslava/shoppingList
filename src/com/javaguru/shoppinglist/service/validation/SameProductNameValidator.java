package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import java.util.Collection;

public class SameProductNameValidator {
    public void validate(Collection<Product> databaseValues, String name){
        for (Product element : databaseValues) {
            if (element.getName().equals(name)) {
                throw new ValidationException("Database contains the product with the same name.");

            }
        }
    }
}
