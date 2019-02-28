package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Category;
import com.javaguru.shoppinglist.domain.Product;

import java.util.Arrays;
import java.util.Optional;

public class ProductCategoryNameValidator implements ProductValidationRule {

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        Category[] categoriesArray = Category.values();
        long count =
                Arrays.stream(categoriesArray).filter((p) -> p.name().equals(product.getCategoryStringName())).count();
        if (count == 0) {
            throw new ValidationException("Please, make a choice from: " + Arrays.toString(Category.values()));
        }
    }
}
