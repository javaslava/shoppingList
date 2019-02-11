package com.javaguru.shoppinglist.validators;

public class DescriptionValidator {
    public void validate(String description){
        if (description.length() < 14 || description.length() > 50) {
            throw new ValidationException("Description's length have to be in range 14 - 50 symbols");
        }

    }
}
