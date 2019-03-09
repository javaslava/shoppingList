package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private Map<Long, Product> database = new HashMap<>();
    private Long productIdSequence = 0L;

    public Product findProductById(Long id) {
        return database.get(id);
    }

    public Product insert(Product product) {
        product.setId(productIdSequence);
        database.put(productIdSequence, product);
        productIdSequence++;
        return product;
    }

    public Collection<Product> getDatabaseValues() {
        return database.values();
    }

    public boolean existsByName(Product product) {
        return database.values().stream().anyMatch(p -> p.getName().compareToIgnoreCase(product.getName()) == 0);
    }
}
