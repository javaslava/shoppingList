package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;

import java.util.Collection;

public interface ProductService {

    Long create(Product product);

    Product findBy(Long id);

    Collection<Product> getDatabaseValues();
}
