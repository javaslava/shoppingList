package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class ProductNotNullValidator {
    public void validate(Product product) {
        if (product == null) {
            throw new NullPointerException("No such name product");
        }
    }
}
