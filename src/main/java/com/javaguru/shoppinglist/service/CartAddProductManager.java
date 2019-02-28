package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.CartRepository;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductNotNullValidator;

import java.util.Optional;

public class CartAddProductManager implements CartManager {

    private final ProductRepository productRepository;
    private final CartRepository shoppingCartRepo;

    public CartAddProductManager(ProductRepository productRepository, CartRepository shoppingCartRepo) {
        this.productRepository = productRepository;
        this.shoppingCartRepo = shoppingCartRepo;
    }

    @Override
    public void manageCart(String cartName) {
        System.out.println("Enter product name to add to " + cartName);
        Optional<Product> productToAdd = productRepository.getProductByName(userStringInput());
        new ProductNotNullValidator().validate(productToAdd);
        shoppingCartRepo.addProductToCart(cartName, productToAdd.get());
    }
}
