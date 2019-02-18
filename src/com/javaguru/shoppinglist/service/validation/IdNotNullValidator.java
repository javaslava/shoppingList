package com.javaguru.shoppinglist.service.validation;

public class IdNotNullValidator {
    public void validate(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must be not null");
        }
    }
}
