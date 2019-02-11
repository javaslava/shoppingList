package com.javaguru.shoppinglist.validators;

public class NameLengthValidator {

    public void validate(String name) {

        if (name.length() < 3 || name.length() > 32) {
            throw new ValidationException("The name's length have to be in range 3 - 32 symbols");
        }

    }
}