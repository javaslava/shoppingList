package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;

public class DescriptionValidator implements ProductValidationRule{
    @Override
    public void validate(Product product, ProductRepository repo){
        if (product.getDescription().length() < product.getMinDescriptionLength() ||
                product.getDescription().length() > product.getMaxDescriptionLength()) {
            throw new ValidationException("Description's length have to be in range " +
                    product.getMinDescriptionLength() + " - " + product.getMaxDescriptionLength() + " symbols");
        }
    }
}
