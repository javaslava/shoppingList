package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@Profile("inmemory")
public class InMemoryProductRepository implements ProductRepository {
    private Map<Long, Product> database = new HashMap<>();
    private Long productIdSequence = 0L;

    @Override
    public Optional<Product> findProductById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public Long insert(Product product) {
        product.setId(productIdSequence);
        database.put(productIdSequence, product);
        productIdSequence++;
        return product.getId();
    }

    @Override
    public Optional<Product> getProductByName(String productName) {
        return database.values().stream()
                .filter((p) -> p.getName().compareToIgnoreCase(productName) == 0).findFirst();
    }

    @Override
    public boolean existsByName(Product product) {
        return database.values().stream()
                .anyMatch(p -> p.getName().compareToIgnoreCase(product.getName()) == 0);
    }
}
