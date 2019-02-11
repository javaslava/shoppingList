package com.javaguru.shoppinglist.validators;

import com.javaguru.shoppinglist.Category;

import java.util.Arrays;

public class CategoryValidator {
    public void validate(String category) {

        if (category.length() == 0) {
            throw new ValidationException("Please, make a choice from: " + Arrays.toString(Category.values()));
        }

        boolean categoryIsSet = false;

        Category[] categories = Category.values();
        for (Category element : categories) {

            String categoryName = element.name();

            if (categoryName.equals(category)) {
                categoryIsSet = true;
                break;
            }
        }

        if (!categoryIsSet) {
            throw new ValidationException("No such category. Choose one of: " + Arrays.toString(Category.values()));
        }
    }
}
