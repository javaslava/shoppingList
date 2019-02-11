package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.validators.ValidationException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DefaultProductService implements ProductService {

    private Map<Long, Product> database = new HashMap<>();
    private Long productIdSequence = 0L;

    public Product findBy(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must be not null");
        }
        return database.get(id);
    }

    @Override
    public Long create(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Cannot be null");
        }
        product.setId(productIdSequence);

        database.put(productIdSequence, product);
        return productIdSequence++;
    }

    public void checkForSameProductName(String name) {
        Collection<Product> values = database.values();
        for (Product element : values) {
            if (element.getName().equals(name)) {
                throw new ValidationException("Database contains the product with the same name.");

            }
        }

    }

}