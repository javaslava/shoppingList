package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import java.util.Optional;

public interface ProductRepository {

   Optional<Product> findProductById(Long id);

   Long insert(Product product);

   Optional<Product> getProductByName(String productName);

   boolean existsByName(Product product);
}
