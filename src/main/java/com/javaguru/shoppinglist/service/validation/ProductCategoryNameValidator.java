package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Category;
import com.javaguru.shoppinglist.domain.Product;

import java.util.Arrays;

public class ProductCategoryNameValidator implements ProductValidationRule {

    @Override
    public void validate(Product product){
        checkNotNull(product);
        Category[] categoriesArray = Category.values();
        try{
            Arrays.stream(categoriesArray).anyMatch(product.getCategory()::equals);
        }catch(IllegalArgumentException ex){
            throw new ValidationException("Please, make a choice from: " + Arrays.toString(Category.values()));
        }
    }
}
