package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProductRepository implements ProductService {

    private Map<Long, Product> database = new HashMap<>();
    private Long productIdSequence = 0L;

    @Override
    public Product findBy(Long id) {
        return database.get(id);
    }

    @Override
    public Long create(Product product) {
        product.setId(productIdSequence);
        database.put(productIdSequence, product);
        return productIdSequence++;
    }

    @Override
    public Collection<Product> getDatabaseValues() {
        return database.values();
    }
}
