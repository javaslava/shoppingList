package com.javaguru.shoppinglist.service.validation.ProductValidation;

import com.javaguru.shoppinglist.domain.Category;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ProductCategoryNameValidationRule implements ProductValidationRule {

    @Override
    public void validate(Product product) {
        Category[] categoriesArray = Category.values();
        try {
            Arrays.stream(categoriesArray).anyMatch(product.getCategory()::equals);
        } catch (IllegalArgumentException ex) {
            throw new ValidationException("Please, make a choice from: " + Arrays.toString(Category.values()));
        }
    }
}
