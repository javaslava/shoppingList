package com.javaguru.shoppinglist;

public interface ProductService {

    Long create(Product product);

    Product findBy(Long id);

    void checkForSameProductName(String name);

}
