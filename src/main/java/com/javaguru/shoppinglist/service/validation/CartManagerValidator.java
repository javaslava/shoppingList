package com.javaguru.shoppinglist.service.validation;

import java.util.Map;

public class CartManagerValidator {
    public void validate(Map cartManager, int response) {
        if (response < 1 || response > cartManager.size()) {
            throw new ValidationException("Not available choice ");
        }
    }
}
