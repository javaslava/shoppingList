package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Category;
import com.javaguru.shoppinglist.domain.Product;

import java.util.Arrays;

public class CategoryValidator implements ProductValidationRule {

    @Override
    public void validate(Product product) {
        Category[] categoriesArray = Category.values();
        int count = 0;
        for (Category element : categoriesArray) {
            String categoryName = element.name();
            if (categoryName.equals(product.getCategoryStringName())) {
                count++;
                break;
            }
        }
        if (count == 0) {
            throw new ValidationException("Please, make a choice from: " + Arrays.toString(Category.values()));
        }
    }
}
