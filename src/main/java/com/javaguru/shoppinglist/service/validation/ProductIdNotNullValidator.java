package com.javaguru.shoppinglist.service.validation;

public class ProductIdNotNullValidator {
    public void validate(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must be not null");
        }
    }
}
