package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.CartRepository;
import com.javaguru.shoppinglist.service.validation.ProductNotNullValidator;

import java.util.Optional;

public class CartDeleteProductManager implements CartManager {

    @Override
    public void manageCart(String cartName, CartRepository shoppingCartRepository) {
        System.out.println("Enter product name to remove from " + cartName);
        Optional<Product> productToDelete = shoppingCartRepository.getProductByName(cartName, userStringInput());
        new ProductNotNullValidator().validate(productToDelete);
        shoppingCartRepository.deleteProductFromCart(cartName, productToDelete.get());
    }
}
