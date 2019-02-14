package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;

public class PriceValidator implements ProductValidationRule{
    @Override
    public void validate(Product product, ProductRepository repo) {
        if (product.getPrice().signum() == -1) {
            throw new ValidationException("Product's price must be a positive number.");
        }else if(product.getPrice().signum() == 0){
            throw new ValidationException("Product must have a price.");
        }
    }
}
