package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;

public class NameLengthValidator implements ProductValidationRule {
    @Override
    public void validate(Product product, ProductRepository repo) {
        if (product.getName().length() < product.getMinNameLength() || product.getName().length() > product.getMaxNameLength()) {
            throw new ValidationException("The name's length have to be in range " + product.getMinNameLength() +
                    " - " + product.getMaxNameLength() + " symbols");
        }
    }
}