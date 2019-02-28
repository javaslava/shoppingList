package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class ProductDescriptionValidator implements ProductValidationRule {

    private final int MIN_DESCRIPTION_LENGTH = 10;
    private final int MAX_DESCRIPTION_LENGTH = 50;

    @Override
    public void validate(Product product) {
        if (product.getDescription().length() < MIN_DESCRIPTION_LENGTH || product.getDescription().length() > MAX_DESCRIPTION_LENGTH) {
            throw new ValidationException("Description's length have to be in range " + MIN_DESCRIPTION_LENGTH + " - " + MAX_DESCRIPTION_LENGTH + " symbols");
        }
    }
}
