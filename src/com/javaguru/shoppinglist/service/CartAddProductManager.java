package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.CartRepository;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductNotNullValidator;

import java.util.Optional;

public class CartAddProductManager implements CartManager {

    private final ProductRepository productRepository;

    CartAddProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void manageCart(String cartName, CartRepository shoppingCartRepository) {
        System.out.println("Enter product name to add to " + cartName);
        Optional<Product> productToAdd = productRepository.getProductByName(userStringInput());
        new ProductNotNullValidator().validate(productToAdd);
        shoppingCartRepository.addProductToCart(cartName, productToAdd.get());
    }
}
