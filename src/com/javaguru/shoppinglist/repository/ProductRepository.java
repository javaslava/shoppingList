package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private Map<Long, Product> database = new HashMap<>();
    private Long productIdSequence = 0L;

    public Product findBy(Long id) {
        return database.get(id);
    }

    public Long create(Product product) {
        product.setId(productIdSequence);
        database.put(productIdSequence, product);
        return productIdSequence++;
    }

    public Collection<Product> getDatabaseValues() {
        return database.values();
    }
}
