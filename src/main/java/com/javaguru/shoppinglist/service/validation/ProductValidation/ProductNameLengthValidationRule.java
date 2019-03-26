package com.javaguru.shoppinglist.service.validation.ProductValidation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ProductNameLengthValidationRule implements ProductValidationRule {

    private final int MIN_NAME_LENGTH = 3;
    private final int MAX_NAME_LENGTH = 32;

    @Override
    public void validate(Product product) {
        if (product.getName().length() < MIN_NAME_LENGTH || product.getName().length() > MAX_NAME_LENGTH) {
            throw new ValidationException("The name's length have to be in range " + MIN_NAME_LENGTH + " - " + MAX_NAME_LENGTH + " symbols");
        }
    }
}