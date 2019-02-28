package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import java.util.LinkedHashSet;
import java.util.Set;

public class ProductValidationService {

    private Set<ProductValidationRule> validationRules = new LinkedHashSet<>();

    public ProductValidationService() {
        validationRules.add(new ProductNameNotNullValidator());
        validationRules.add(new ProductNameLengthValidator());
        validationRules.add(new ProductPriceValidator());
        validationRules.add(new ProductDiscountValidator());
        validationRules.add(new ProductDescriptionValidator());
        validationRules.add(new ProductCategoryNameValidator());
    }

    public void validate(Product product) {
        validationRules.forEach(s -> s.validate(product));
    }
}
