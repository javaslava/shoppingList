package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import java.util.HashSet;
import java.util.Set;

public class ProductValidationService {
    private Set<ProductValidationRule> validationRules = new HashSet<>();

    public ProductValidationService() {
        validationRules.add(new NameLengthValidator());
        validationRules.add(new PriceValidator());
        validationRules.add(new DiscountValidator());
        validationRules.add(new DescriptionValidator());
        validationRules.add(new CategoryValidator());
    }

    public void validate(Product product) {
        validationRules.forEach(s -> s.validate(product));
    }
}
